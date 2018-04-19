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
package com.streamsets.pipeline.stage.origin.remote;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  REMOTE_01("地址无效{}"),
  REMOTE_02("文件'{}'在'{}': {}处发生异常"),

  REMOTE_03("'{}'文件中偏移量为'{}'的对象超过最大长度限制"),
  REMOTE_04("从文件'{}'的'{}'处读取数据失败，原因: {}"),
  REMOTE_05("读取数据失败: {}"),

  REMOTE_06("known_hosts文件:{}不存在或不可访问"),
  REMOTE_07("已启用严格主机检查，但未指定known_hosts"),
  REMOTE_08("无法使用设定的凭证从远程主机下载文件: {}. " +
      "请确认主机是否可以访问, 以及凭证是否有效."),

  REMOTE_09("轮询间隔必须是正数值"),
  REMOTE_10("私钥文件: {}不存在或无法访问"),
  REMOTE_11("私人密钥认证仅支持SFTP"),
  REMOTE_12("严格主机检查仅支持SFTP"),
  REMOTE_13("文件模式不能为空"),
  REMOTE_14("无效的GLOB文件模式'{}': {}"),
  REMOTE_15("地址: '{}'无效. 必须以'ftp://'或'sftp://'开头"),
  REMOTE_16("初始文件'{}'无效: {}"),
  REMOTE_17("无法解析凭证: {}"),
//  REMOTE_01("Given URI is invalid {}"),
//  REMOTE_02("Failed to process file '{}' at position '{}': {}"),
//
//  REMOTE_03("Object in file '{}' at offset '{}' exceeds maximum length"),
//  REMOTE_04("Failed to read data from file '{}' at position '{}' due to: {}"),
//  REMOTE_05("Failed to read data due to: {}"),
//
//  REMOTE_06("known_hosts file: {} does not exist or is not accessible"),
//  REMOTE_07("Strict Host Checking is enabled and known_hosts file not specified"),
//  REMOTE_08("Unable to download files from remote host: {} with given credentials. " +
//      "Please verify if the host is reachable, and the credentials are valid."),
//
//  REMOTE_09("Poll Interval must be positive"),
//  REMOTE_10("Private Key file: {} does not exist or is not accessible"),
//  REMOTE_11("Private Key authentication is supported only with SFTP"),
//  REMOTE_12("Strict Host Checking is supported only with SFTP"),
//  REMOTE_13("File Pattern cannot be empty"),
//  REMOTE_14("Invalid GLOB file pattern '{}': {}"),
//  REMOTE_15("URI: '{}' is invalid. Must begin with 'ftp://' or 'sftp://'"),
//  REMOTE_16("Initial file '{}' is invalid: {}"),
//  REMOTE_17("Can't resolve credential: {}"),
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
