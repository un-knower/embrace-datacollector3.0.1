/*
 * Copyright 2017 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.pipeline.lib.tls;

import com.google.common.base.Strings;
import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.api.credential.CredentialValue;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TlsConfigBean {

  public static final String DEFAULT_KEY_MANAGER_ALGORITHM = "SunX509";

  // this is to push all display positions to a high number so they appear together at the bottom of the
  // ToErrorSdcIpcDTarget (where all properties are combined into a single group)
  private static final int DISPLAY_POSITION_OFFSET = 1000;

  private static final String[] MODERN_PROTOCOLS = {"TLSv1.2"};
  private static final String[] MODERN_CIPHER_SUITES = {
      "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
      "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256",
      "TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384",
      "TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384",
      "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256",
      "TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256",
      "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384",
      "TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384"
  };

  private static final Logger LOG = LoggerFactory.getLogger(TlsConfigBean.class);

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "false",
      label = "启用TLS",
      description = "启用该结点的加密传输",
//      label = "Use TLS",
//      description = "Enable transport layer security for this stage.",
      displayPosition = DISPLAY_POSITION_OFFSET + 0,
      group = "#0"
  )
  public boolean tlsEnabled;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      description = "密钥文件路径(绝对路径或相对于数据采集服务资源路径的相对路径)",
      label = "证书文件",
//      description = "The path to the keystore file.  Absolute path, or relative to the Data Collector resources "
//          + "directory.",
//      label = "Keystore File",
      displayPosition = DISPLAY_POSITION_OFFSET + 20,
      group = "#0",
      dependsOn = "tlsEnabled",
      triggeredByValue = "true"
  )
  public String keyStoreFilePath;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "JKS",
      label = "证书类型",
      description = "证书使用的证书/密钥模式类型。",
//      label = "Keystore Type",
//      description = "The type of certificate/key scheme to use for the key tore.",
      displayPosition = DISPLAY_POSITION_OFFSET + 50,
      group = "#0",
      dependsOn = "tlsEnabled",
      triggeredByValue = "true"
  )
  @ValueChooserModel(KeyStoreTypeChooserValues.class)
  public KeyStoreType keyStoreType = KeyStoreType.JKS;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.CREDENTIAL,
      description = "证书访问密码，建议设置",
      label = "证书密码",
//      description = "The password to the keystore file, if applicable.  Using a password is highly recommended for "
//          + "security reasons.",
//      label = "Keystore Password",
      displayPosition = DISPLAY_POSITION_OFFSET + 70,
      group = "#0",
      dependsOn = "tlsEnabled",
      triggeredByValue = "true"
  )
  public CredentialValue keyStorePassword = () -> "";

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "证书算法",
      description = "证书使用的密钥管理算法",
//      label = "Keystore Key Algorithm",
//      description = "The key manager algorithm to use with the keystore.",
      defaultValue = DEFAULT_KEY_MANAGER_ALGORITHM,
      displayPosition = DISPLAY_POSITION_OFFSET + 80,
      group = "#0",
      dependsOn = "tlsEnabled",
      triggeredByValue = "true"
  )
  public String keyStoreAlgorithm = DEFAULT_KEY_MANAGER_ALGORITHM;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      description = "对该文件的路径。绝对路径，或相对于数据收集器资源目录",
      label = "可信任证书",
//      description = "The path to the truststore file.  Absolute path, or relative to the Data Collector resources "
//          + "directory.",
//      label = "Truststore File",
      displayPosition = DISPLAY_POSITION_OFFSET + 120,
      group = "#0",
      dependsOn = "tlsEnabled",
      triggeredByValue = "true"
  )
  public String trustStoreFilePath;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "JKS",
      label = "可信任证书类型",
      description = "可信任证书的类型",
//      label = "Truststore Type",
//      description = "The type of certificate/key scheme to use for the truststore.",
      displayPosition = DISPLAY_POSITION_OFFSET + 150,
      group = "#0",
      dependsOn = "tlsEnabled",
      triggeredByValue = "true"
  )
  @ValueChooserModel(KeyStoreTypeChooserValues.class)
  public KeyStoreType trustStoreType = KeyStoreType.JKS;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.CREDENTIAL,
      description = "可信任证书密码，建议设置",
      label = "可信任证书密码",
//      description = "The password to the truststore file, if applicable.  Using a password is highly recommended for "
//          + "security reasons.",
//      label = "Truststore Password",
      displayPosition = DISPLAY_POSITION_OFFSET + 170,
      group = "#0",
      dependsOn = "tlsEnabled",
      triggeredByValue = "true"
  )
  public CredentialValue trustStorePassword;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "可信任证书算法",
      description = "可信任证书的算法",
//      label = "Truststore Trust Algorithm",
//      description = "The key manager algorithm to use with the truststore.",
      defaultValue = DEFAULT_KEY_MANAGER_ALGORITHM,
      displayPosition = DISPLAY_POSITION_OFFSET + 180,
      group = "#0",
      dependsOn = "tlsEnabled",
      triggeredByValue = "true"
  )
  public String trustStoreAlgorithm = DEFAULT_KEY_MANAGER_ALGORITHM;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "使用默认协议",
      description = "使用TLS协议（TLSv1.2）。" +
              "出于安全考虑，这是非常推荐的，" +
              "特殊情况下可对此进行重写。",
//      label = "Use Default Protocols",
//      description = "Use only modern TLS protocols (TLSv1.2).  This is highly recommended for security reasons, but " +
//          "can be overridden if special circumstances require it.",
      defaultValue = "true",
      displayPosition = DISPLAY_POSITION_OFFSET + 300,
      group = "#0",
      dependsOn = "tlsEnabled",
      triggeredByValue = "true"
  )
  public boolean useDefaultProtocols = true;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.LIST,
      label = "传输协议",
      description = "连接的传输协议，如：TLSv1.2或TLSv1.1",
//      label = "Transport Protocols",
//      description = "The transport protocols to enable for connections (ex: TLSv1.2, TLSv1.1, etc.).",
      displayPosition = DISPLAY_POSITION_OFFSET + 310,
      group = "#0",
      defaultValue = "[]",
      dependsOn = "useDefaultProtocols",
      triggeredByValue = "false"
  )
  public List<String> protocols = new LinkedList<>();

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "使用默认密码套件",
      description = "使用流行的密码套件。出于安全考虑，" +
              "这是非常推荐的，但如果特殊情况需要，则可以对此进行重写。",
//      label = "Use Default Cipher Suites",
//      description = "Use only modern cipher suites.  This is highly recommended for security reasons, but can be " +
//          "overridden if special circumstances require it.",
      defaultValue = "true",
      displayPosition = DISPLAY_POSITION_OFFSET + 350,
      group = "#0",
      dependsOn = "tlsEnabled",
      triggeredByValue = "true"
  )
  public boolean useDefaultCiperSuites = true;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.LIST,
      label = "密码套件",
      description = "连接使用的密码套件，如TLS、ECDHE、ECDSA_WITH_AES_256、GCM、SHA384等",
//      label = "Cipher Suites",
//      description = "The cipher suites for connections (ex: TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, etc.).",
      displayPosition = DISPLAY_POSITION_OFFSET + 360,
      group = "#0",
      defaultValue = "[]",
      dependsOn = "useDefaultCiperSuites",
      triggeredByValue = "false"
  )
  public List<String> cipherSuites = new LinkedList<>();

  public boolean pathRelativeToResourcesDir = true;

  private SSLEngine sslEngine;
  private SSLContext sslContext;
  private KeyStore keyStore;
  private KeyStore trustStore;

  private Set<String> getSupportedValuesFromSpecified(
      Collection<String> supportedValues, Collection<String> specifiedValues, String type
  ) {
    Set<String> returnSet = new HashSet<>();
    final Set<String> supportedSet = new HashSet<>(supportedValues);

    for (String specified : specifiedValues) {
      if (supportedSet.contains(specified)) {
        returnSet.add(specified);
      } else {
        if (LOG.isWarnEnabled()) {
          LOG.warn(String.format(
              "%s %s was specified, but is not supported within the JVM; disabling",
              type,
              specified
          ));
        }
      }
    }
    return returnSet;
  }

  private static Path getFilePath(String resourcesDir, String path, boolean pathRelativeToResourcesDir) {
    if (Strings.isNullOrEmpty(path)) {
      return null;
    }
    final Path p = Paths.get(path);
    if (p.isAbsolute() || !pathRelativeToResourcesDir) {
      return p;
    } else {
      return Paths.get(resourcesDir, path);
    }
  }

  public boolean hasKeyStore() {
    return !Strings.isNullOrEmpty(keyStoreFilePath);
  }

  public boolean hasTrustStore() {
    return !Strings.isNullOrEmpty(trustStoreFilePath);
  }

  public boolean isEnabled() {
    return tlsEnabled;
  }

  public boolean isEitherStoreEnabled() {
    return hasKeyStore() || hasTrustStore();
  }

  public boolean isInitialized() {
    return sslEngine != null;
  }

  public boolean init(
      Stage.Context context, String groupName, String configPrefix, List<Stage.ConfigIssue> issues
  ) {
    KeyManagerFactory keyStoreFactory = null;
    TrustManagerFactory trustStoreFactory = null;

    if (isEnabled() && !isEitherStoreEnabled()) {
      issues.add(context.createConfigIssue(
          groupName,
          configPrefix + "tlsEnabled",
          TlsConfigErrors.TLS_05
      ));
      return false;
    }

    if (hasKeyStore()) {
      keyStoreFactory = initializeKeyStore(context, groupName, configPrefix, issues);
      if (keyStoreFactory == null) {
        return false;
      }
    }

    if (hasTrustStore()) {
      trustStoreFactory = initializeTrustStore(context, groupName, configPrefix, issues);
      if (trustStoreFactory == null) {
        return false;
      }
    }

    try {
      sslContext = SSLContext.getInstance("TLS");
      sslContext.init(keyStoreFactory != null ? keyStoreFactory.getKeyManagers() : null,
          trustStoreFactory != null ? trustStoreFactory.getTrustManagers() : null,
          null
      );
    } catch (KeyManagementException | NoSuchAlgorithmException e) {
      issues.add(context.createConfigIssue(
          groupName,
          "trustStoreFilePath",
          TlsConfigErrors.TLS_51,
          e.getMessage(),
          e
      ));
      return false;
    }

    sslEngine = sslContext.createSSLEngine();
    sslEngine.setUseClientMode(false);
    sslEngine.setNeedClientAuth(false);

    sslEngine.setEnabledProtocols(getFinalProtocols());

    sslEngine.setEnabledCipherSuites(getFinalCipherSuites());

    sslEngine.setEnableSessionCreation(true);
    sslEngine.setUseClientMode(isClientMode());

    return true;
  }

  @NotNull
  public String[] getFinalCipherSuites() {
    Collection<String> filteredCipherSuites;
    if (useDefaultCiperSuites) {
      filteredCipherSuites = getSupportedValuesFromSpecified(
          Arrays.asList(sslEngine.getSupportedCipherSuites()),
          Arrays.asList(MODERN_CIPHER_SUITES),
          "Cipher suite"
      );
    } else {
      filteredCipherSuites = getSupportedValuesFromSpecified(Arrays.asList(sslEngine.getSupportedCipherSuites()),
          cipherSuites,
          "Cipher suite"
      );
    }
    return filteredCipherSuites.toArray(new String[0]);
  }

  @NotNull
  public String[] getFinalProtocols() {
    Collection<String> filteredProtocols;
    if (useDefaultProtocols) {
      filteredProtocols = getSupportedValuesFromSpecified(
          Arrays.asList(sslEngine.getSupportedProtocols()),
          Arrays.asList(MODERN_PROTOCOLS),
          "Protocol"
      );
    } else {
      filteredProtocols = getSupportedValuesFromSpecified(Arrays.asList(sslEngine.getSupportedProtocols()),
          protocols,
          "Protocol"
      );
    }
    return filteredProtocols.toArray(new String[0]);
  }

  private KeyManagerFactory initializeKeyStore(
      Stage.Context context,
      String groupName,
      String configPrefix,
      List<Stage.ConfigIssue> issues
  ) {
    final Path keyStorePath = getFilePath(
        context.getResourcesDirectory(),
        keyStoreFilePath,
        pathRelativeToResourcesDir
    );

    keyStore = initializeKeyStoreFromConfig(context,
        groupName,
        configPrefix,
        issues,
        keyStorePath,
        keyStorePassword,
        keyStoreType,
        "Key"
    );
    if (keyStore == null) {
      return null;
    }

    KeyManagerFactory kmf;
    try {
      kmf = KeyManagerFactory.getInstance(keyStoreAlgorithm);
    } catch (NoSuchAlgorithmException e) {
      issues.add(context.createConfigIssue(
          groupName,
          configPrefix + "keyStoreAlgorithm",
          TlsConfigErrors.TLS_22,
          keyStoreAlgorithm,
          e.getMessage(),
          e
      ));
      return null;
    }

    try {
      kmf.init(keyStore, getPasswordChars(keyStorePassword.get()));
    } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | StageException e) {
      issues.add(context.createConfigIssue(
          groupName,
          configPrefix + "keyStoreFilePath",
          TlsConfigErrors.TLS_23,
          e.getMessage(),
          e
      ));
      return null;
    }
    return kmf;
  }

  private TrustManagerFactory initializeTrustStore(
      Stage.Context context,
      String groupName,
      String configPrefix,
      List<Stage.ConfigIssue> issues
  ) {
    final Path trustStorePath = getFilePath(
        context.getResourcesDirectory(),
        trustStoreFilePath,
        pathRelativeToResourcesDir
    );

    trustStore = initializeKeyStoreFromConfig(
        context,
        groupName,
        configPrefix,
        issues,
        trustStorePath,
        trustStorePassword,
        trustStoreType,
        "Trust"
    );
    if (trustStore == null) {
      return null;
    }

    TrustManagerFactory tmf;
    try {
      tmf = TrustManagerFactory.getInstance(trustStoreAlgorithm);
    } catch (NoSuchAlgorithmException e) {
      issues.add(context.createConfigIssue(
          groupName,
          configPrefix + "trustStoreAlgorithm",
          TlsConfigErrors.TLS_50,
          trustStoreAlgorithm,
          e.getMessage(),
          e
      ));
      return null;
    }

    try {
      tmf.init(trustStore);
    } catch (KeyStoreException e) {
      issues.add(context.createConfigIssue(
          groupName,
          configPrefix + "trustStoreFilePath",
          TlsConfigErrors.TLS_51,
          e.getMessage(),
          e
      ));
      return null;
    }
    return tmf;
  }

  public boolean isClientMode() {
    return hasTrustStore() && !hasKeyStore();
  }

  public KeyStore getKeyStore() {
    return keyStore;
  }

  public KeyStore getTrustStore() {
    return trustStore;
  }

  public SSLContext getSslContext() {
    return sslContext;
  }

  public SSLEngine getSslEngine() {
    return sslEngine;
  }

  private static KeyStore initializeKeyStoreFromConfig(
      Stage.Context context,
      String groupName,
      String configPrefix,
      List<Stage.ConfigIssue> issues,
      Path keyStorePath,
      CredentialValue password,
      KeyStoreType type,
      String storeCategory
  ) {
    if (!keyStorePath.toFile().exists()) {
      issues.add(context.createConfigIssue(
          groupName,
          configPrefix + storeCategory.toLowerCase() + "StoreFilePath",
          TlsConfigErrors.TLS_01,
          storeCategory.toLowerCase(),
          keyStorePath
      ));
      return null;
    }

    KeyStore ks;
    try {
      ks = KeyStore.getInstance(type.getJavaValue());
    } catch (KeyStoreException e) {
      issues.add(context.createConfigIssue(
          groupName,
          configPrefix + storeCategory.toLowerCase() + "StoreType",
          TlsConfigErrors.TLS_20,
          storeCategory.toLowerCase(),
          e.getMessage(),
          e
      ));
      return null;
    }

    try (final InputStream keyStoreIs = Files.newInputStream(keyStorePath)) {
      ks.load(keyStoreIs, getPasswordChars(password.get()));
    } catch (IOException | NoSuchAlgorithmException | CertificateException | StageException e) {
      issues.add(context.createConfigIssue(
          groupName,
          configPrefix + storeCategory.toLowerCase() + "StoreFilePath",
          TlsConfigErrors.TLS_21,
          storeCategory.toLowerCase(),
          keyStorePath,
          e.getMessage(),
          e
      ));
      return null;
    }

    return ks;
  }

  private static char[] getPasswordChars(String password) {
    if (Strings.isNullOrEmpty(password)) {
      return new char[0];
    } else {
      return password.toCharArray();
    }
  }
}
