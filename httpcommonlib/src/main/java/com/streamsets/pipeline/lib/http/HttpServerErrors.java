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
public enum HttpServerErrors implements ErrorCode {
  HTTP_SERVER_ORIG_00("端口超限"),
  HTTP_SERVER_ORIG_01("端口无效: {}"),

  HTTP_SERVER_ORIG_07("文件不存在"),
  HTTP_SERVER_ORIG_08("指定路径不是文件"),
  HTTP_SERVER_ORIG_09("数据采集器无法读取文件"),
  HTTP_SERVER_ORIG_10("无法加载密钥库: {}"),
  HTTP_SERVER_ORIG_11("配置值为空"),

  HTTP_SERVER_ORIG_20("无法启动HTTP服务器: {}"),;
//  HTTP_SERVER_ORIG_00("Port out of range"),
//  HTTP_SERVER_ORIG_01("Port not available: {}"),
//
//  HTTP_SERVER_ORIG_07("File does not exist"),
//  HTTP_SERVER_ORIG_08("Path is not a file"),
//  HTTP_SERVER_ORIG_09("File is not readable by the Data Collector"),
//  HTTP_SERVER_ORIG_10("Could not load key store: {}"),
//  HTTP_SERVER_ORIG_11("Configuration value is empty"),
//
//  HTTP_SERVER_ORIG_20("Could not start HTTP server: {}"),;

  private final String msg;

  HttpServerErrors(String msg) {
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
