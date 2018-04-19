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
package com.streamsets.pipeline.lib.http;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  HTTP_00("无法解析记录: {}"),
  HTTP_01("获取资源出错. 状态: {},原因: {}"),
  HTTP_02("JSON解析器在块中发现多个记录。验证是否配置了正确的分隔符。"),
  HTTP_03("获取资源出错. 原因: {}"),
  HTTP_04("文件'{}'不存在或无法访问."),
  HTTP_05("密钥库/信任库需要密码."),
  HTTP_06("评估表达式出错: {}"),
  HTTP_07("Vault EL仅在资源模式为HTTPS时才可使用."),
  HTTP_08("当使用分页时,结果字段必须是列表,但是结果中发现{}"),
  HTTP_09("当使用分页时,不支持块传输编码."),
  HTTP_10("当使用分页时,数据格式不支持{}"),
  HTTP_11("记录已包含字段{}, 无法写入响应头"),
  HTTP_12("记录不包含结果字段路径'{}'"),
  HTTP_13("无效的代理地址.原因: {}"),
  HTTP_14("按状态配置时节点失败,状态:{}. 原因: {}"),
  HTTP_15("使用退避时，间隔时间必须大于0。"),
  HTTP_16("操作仅在非ok状态生效（即不在[200-300)范围内）"),
  HTTP_17("一个状态代码只能映射到一个动作。代码{}映射不止一次。"),
  HTTP_18("按读超时配置使节点失败"),
  HTTP_19("由于请求重试的次数超过配置的最大值{},节点失败"),
  HTTP_20("Content-Type头属性存在，但不是字符串,而是{}。"),
  HTTP_21("OAuth2认证失败。请确认凭据有效"),
  HTTP_22("oauth2认证响应不包含访问令牌"),
  HTTP_23("授权服务返回的令牌没有访问服务的权限。请验证提供的凭据。"),
  HTTP_24("找不到令牌URL。请验证URL:“{}”是否正确，且传输编码：“{}“可被接受。"),
  HTTP_25("无法解析表达式"), // Don't log expression as it could contain secure data
  HTTP_26("算法'{}'无效"),
  HTTP_27("密钥无效: {}"),
  HTTP_28("后置处理/清理出错: {}"),
  HTTP_29("无法解析凭证值{}: {}"),
  HTTP_30("无法解析OAuth2凭证: {}"),
  HTTP_31("无法解析OAuth1凭证: {}"),
  HTTP_32("请求异常: {}"),


  // HTTP Target
  HTTP_40("发送资源异常. 状态: {} 原因: {}"),
  HTTP_41("发送资源异常. 原因: {}"),

  // WebSocket Target
  HTTP_50("发送资源异常. 原因: {}"),
  HTTP_51("无效的资源地址."),
  HTTP_52("无效的资源地址,原因: {}"),
//  HTTP_00("Cannot parse record: {}"),
//  HTTP_01("Error fetching resource. Status: {} Reason: {}"),
//  HTTP_02("JSON parser found more than one record in chunk. Verify that the correct delimiter is configured."),
//  HTTP_03("Error fetching resource. Reason: {}"),
//  HTTP_04("The file '{}' does not exist or is inaccessible."),
//  HTTP_05("Password is required for Key Store/Trust Store."),
//  HTTP_06("Error evaluating expression: {}"),
//  HTTP_07("Vault EL is only available when the resource scheme is https."),
//  HTTP_08("When using pagination, the results field must be a list but a {} was found"),
//  HTTP_09("Chunked transfer encoding is not supported when using pagination."),
//  HTTP_10("{} is not a supported data format when using pagination"),
//  HTTP_11("Record already contains field {}, cannot write response header."),
//  HTTP_12("Record does not contain result field path '{}'"),
//  HTTP_13("Invalid Proxy URI. Reason : {}"),
//  HTTP_14("Failing stage as per configuration for status {}. Reason : {}"),
//  HTTP_15("When using backoff, base interval must be greater than 0"),
//  HTTP_16("Actions can only be configured for non-OK statuses (i.e. not in the [200,300) range)"),
//  HTTP_17("A particular status code can only be mapped to one action.  Code {} was mapped more than once."),
//  HTTP_18("Failing stage as per configuration for read timeout"),
//  HTTP_19("Failing stage because number of request retries exceeded configured maximum of {}"),
//  HTTP_20("Content-Type header was present but was a {}, not a String"),
//  HTTP_21("OAuth2 authentication failed. Please make sure the credentials are valid."),
//  HTTP_22("OAuth2 authentication response does not contain access token"),
//  HTTP_23("Token returned by authorization service does not have the authority to access the service. Please verify the credentials provided."),
//  HTTP_24("Token URL was not found. Please verify that the URL: '{}' is correct, and the transfer encoding: '{}' is accepted"),
//  HTTP_25("Unable to parse expression"), // Don't log expression as it could contain secure data
//  HTTP_26("Algorithm '{}' is unavailable"),
//  HTTP_27("Key is invalid: {}"),
//  HTTP_28("Exception in post processing or cleanup: {}"),
//  HTTP_29("Can't resolve credential value for {}: {}"),
//  HTTP_30("Can't resolve OAuth2 credentials: {}"),
//  HTTP_31("Can't resolve OAuth1 credentials: {}"),
//  HTTP_32("Error executing request: {}"),
//
//
//  // HTTP Target
//  HTTP_40("Error sending resource. Status: {} Reason: {}"),
//  HTTP_41("Error sending resource. Reason: {}"),
//
//  // WebSocket Target
//  HTTP_50("Error sending resource. Reason: {}"),
//  HTTP_51("Invalid Resource URI."),
//  HTTP_52("Invalid Resource URI. Reason : {}"),

  ;

  private final String msg;

  Errors(String msg) {
    this.msg = msg;
  }

  @Override
  public String getCode() {
    return name();
  }

  @Override
  public String getMessage() {
    return msg;
  }
}

