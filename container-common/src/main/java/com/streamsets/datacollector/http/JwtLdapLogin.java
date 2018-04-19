package com.streamsets.datacollector.http;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.security.ServerAuthException;
import org.ldaptive.BindConnectionInitializer;
import org.ldaptive.Connection;
import org.ldaptive.ConnectionConfig;
import org.ldaptive.DefaultConnectionFactory;
import org.ldaptive.LdapAttribute;
import org.ldaptive.LdapEntry;
import org.ldaptive.LdapException;
import org.ldaptive.SearchOperation;
import org.ldaptive.SearchRequest;
import org.ldaptive.SearchScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.streamsets.datacollector.util.Configuration;

/**
 * jwt校验
 * 
 * @author bingo
 *
 */
public class JwtLdapLogin {

	private static final Logger LOG = LoggerFactory.getLogger(JwtLdapLogin.class);

	/**
	 * hostname of the ldap server
	 */
	private String _hostname;

	/**
	 * port of the ldap server
	 */
	private int _port;

	/**
	 * root DN used to connect to
	 */
	private String _bindDn;

	/**
	 * password used to connect to the root ldap context
	 */
	private String _bindPassword;

	/**
	 * object class of a user
	 */
	private String _userObjectClass = "inetOrgPerson";

	/**
	 * attribute that the principal is located
	 */
	private String _userRdnAttribute = "uid";

	/**
	 * attribute that the principal is located
	 */
	private String _userIdAttribute = "cn";

	/**
	 * name of the attribute that a users password is stored under
	 * <p/>
	 * NOTE: not always accessible, see force binding login
	 */
	private String _userPasswordAttribute = "userPassword";

	/**
	 * base DN where users are to be searched from
	 */
	private String _userBaseDn;

	/**
	 * base DN where role membership is to be searched from
	 */
	private String _roleBaseDn;

	/**
	 * object class of roles
	 */
	private String _roleObjectClass = "groupOfUniqueNames";

	/**
	 * name of the attribute that a username would be under a role class
	 */
	private String _roleMemberAttribute = "uniqueMember";

	/**
	 * the name of the attribute that a role would be stored under
	 */
	private String _roleNameAttribute = "roleName";

	/**
	 * if the getUserInfo can pull a password off of the user then password
	 * comparison is an option for authn, to force binding login checks, set
	 * this to true
	 */
	private boolean _forceBindingLogin = false;

	/**
	 * When true changes the protocol to ldaps
	 */
	private boolean _useLdaps = false;

	/**
	 * When true changes the protocol to ldaps
	 */
	private boolean _useStarttls = false;

	/**
	 * Default filter to do the user search.
	 */
	private String _userFilter = "%s={user}";

	/**
	 * Default filter to do the role search.
	 */
	private String _roleFilter = "%s={dn})";

	private static final String filterFormat = "(&(objectClass=%s)%s)";

	/**
	 * LDAP configuration.
	 */
	private ConnectionConfig connConfig;

	/**
	 * Connection to Ldap server.
	 */
	private Connection conn;

	/**
	 * jwt sign public key
	 */
	private RSAPublicKey rsaPublicKey;

	/**
	 * jwt cookie name of request
	 */
	private String jwtCookieName;

	public JwtLdapLogin(Map<String, ?> options, String pemPath, String jwtCookieName) {

		LOG.debug("Initializing Ldap configuration");

		_hostname = (String) options.get("hostname");
		_port = Integer.parseInt((String) options.get("port"));
		_bindDn = (String) options.get("bindDn");
		_bindPassword = (String) options.get("bindPassword");
		_userBaseDn = (String) options.get("userBaseDn");
		_roleBaseDn = (String) options.get("roleBaseDn");

		if (options.containsKey("forceBindingLogin")) {
			_forceBindingLogin = Boolean.parseBoolean((String) options.get("forceBindingLogin"));
		}

		if (options.containsKey("useLdaps")) {
			_useLdaps = Boolean.parseBoolean((String) options.get("useLdaps"));
		}

		if (options.containsKey("useStartTLS")) {
			_useStarttls = Boolean.parseBoolean((String) options.get("useStartTLS"));
		}

		_userObjectClass = getOption(options, "userObjectClass", _userObjectClass);
		_userRdnAttribute = getOption(options, "userRdnAttribute", _userRdnAttribute); // depricated
		_userIdAttribute = getOption(options, "userIdAttribute", _userIdAttribute);
		_userPasswordAttribute = getOption(options, "userPasswordAttribute", _userPasswordAttribute);
		_roleObjectClass = getOption(options, "roleObjectClass", _roleObjectClass);
		_roleMemberAttribute = getOption(options, "roleMemberAttribute", _roleMemberAttribute);
		_roleNameAttribute = getOption(options, "roleNameAttribute", _roleNameAttribute);
		_userFilter = getOption(options, "userFilter", _userFilter);
		_roleFilter = getOption(options, "roleFilter", _roleFilter);

		if (Configuration.FileRef.isValueMyRef(_bindPassword)) {
			Configuration.FileRef fileRef = new Configuration.FileRef(_bindPassword);
			_bindPassword = fileRef.getValue();
			if (_bindPassword != null) {
				_bindPassword = _bindPassword.trim();
			}
		}

		// Setup environment. If both useLdaps and useStartTLS are set to true,
		// apply useStartTLS
		String ldapUrl;
		if (_useStarttls) {
			ldapUrl = String.format("ldap://%s:%s", _hostname, _port);
		} else {
			ldapUrl = String.format("%s://%s:%s", _useLdaps ? "ldaps" : "ldap", _hostname, _port);
		}
		LOG.info("Accessing LDAP Server: {} startTLS: {}", ldapUrl, _useStarttls);
		connConfig = new ConnectionConfig(ldapUrl);
		connConfig.setUseStartTLS(_useStarttls);
		connConfig.setConnectionInitializer(
				new BindConnectionInitializer(_bindDn, new org.ldaptive.Credential(_bindPassword)));
		conn = DefaultConnectionFactory.getConnection(connConfig);
		try {
			conn.open();
		} catch (LdapException ex) {
			LOG.error("Failed to establish connection to the LDAP server {}. {}", ldapUrl, ex);
			// We don't throw exception here because there might be multiple
			// LDAP servers configured
		}
		this.jwtCookieName = jwtCookieName;
		try {
			byte[] pem = FileUtils.readFileToByteArray(new File(pemPath));
			rsaPublicKey = parseRSAPublicKey(pem);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public String getUserName(HttpServletRequest request) throws ServerAuthException {
		String jwtToken = null;
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(jwtCookieName)) {
					jwtToken = cookie.getValue();
					break;
				}
			}
		}
		if (StringUtils.isNotBlank(jwtToken)) {
			try {
				SignedJWT signerJWT = SignedJWT.parse(jwtToken);
				JWSVerifier verifier = new RSASSAVerifier(rsaPublicKey);
				boolean vetify = signerJWT.verify(verifier);
				if (vetify) {
					String username = signerJWT.getJWTClaimsSet().getSubject();
					return username;
				} else {
					throw new ServerAuthException("jwt token不正确");
				}
			} catch (Throwable e) {
				throw new ServerAuthException(e);
			}
		} else {
			return null;
		}

	}

	public String getUserCredential(String username) throws ServerAuthException {
		try {
			LdapEntry entry = getEntryWithCredential(username);
			if (entry == null) {
				return null;
			}
			String pwdCredential = getUserCredential(entry);
			return pwdCredential;
		} catch (LdapException e) {
			throw new ServerAuthException(e);
		}
	}

	private String getUserCredential(LdapEntry entry) {
		String credential = null;
		if (entry != null) {
			LdapAttribute attr = entry.getAttribute(_userPasswordAttribute);
			if (attr == null) {
				LOG.error("Failed to receive user password from LDAP server. Possibly userPasswordAttribute is wrong");
				return null;
			}
			byte[] value = attr.getBinaryValue();
			credential = new String(value, StandardCharsets.UTF_8);
		}
		return credential;
	}

	private RSAPublicKey parseRSAPublicKey(byte[] pem) {

		PublicKey key = null;
		try {
			CertificateFactory fact = CertificateFactory.getInstance("X.509");
			ByteArrayInputStream is = new ByteArrayInputStream(pem);
			X509Certificate cer = (X509Certificate) fact.generateCertificate(is);
			key = cer.getPublicKey();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return (RSAPublicKey) key;
	}

	private LdapEntry getEntryWithCredential(String username) throws LdapException {
		if (StringUtils.isBlank(_userObjectClass) || StringUtils.isBlank(_userIdAttribute)
				|| StringUtils.isBlank(_userBaseDn) || StringUtils.isBlank(_userPasswordAttribute)) {
			LOG.error("Failed to get user because at least one of the following is null : "
					+ "[_userObjectClass, _userIdAttribute, _userBaseDn, _userPasswordAttribute ]");
			return null;
		}

		// Create the format of
		// &(objectClass=_userObjectClass)(_userIdAttribute={user}))
		String userFilter = buildFilter(_userFilter, _userObjectClass, _userIdAttribute);
		if (userFilter.contains("{user}")) {
			userFilter = userFilter.replace("{user}", username);
		}
		LOG.debug("Searching user using the filter {} on user baseDn {}", userFilter, _userBaseDn);

		// Get the group names from each group, which is obtained from
		// roleNameAttribute attribute.
		SearchRequest request = new SearchRequest(_userBaseDn, userFilter, _userPasswordAttribute);
		request.setSearchScope(SearchScope.SUBTREE);
		request.setSizeLimit(1);

		try {
			SearchOperation search = new SearchOperation(conn);
			org.ldaptive.SearchResult result = search.execute(request).getResult();
			LdapEntry entry = result.getEntry();
			LOG.info("Found user?: {}", entry != null);
			return entry;
		} catch (LdapException ex) {
			LOG.error("{}", ex.toString(), ex);
			return null;
		}
	}

	private String buildFilter(String attrFilter, String objClass, String attrName) {
		// check if the filter has surrounding "()"
		if (!attrFilter.startsWith("(")) {
			attrFilter = "(" + attrFilter;
		}
		if (!attrFilter.endsWith(")")) {
			attrFilter = attrFilter + ")";
		}
		return String.format(filterFormat, objClass, String.format(attrFilter, attrName));
	}

	private String getOption(Map<String, ?> options, String key, String defaultValue) {
		Object value = options.get(key);

		if (value == null) {
			return defaultValue;
		}

		return (String) value;
	}

}