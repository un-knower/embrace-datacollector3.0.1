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
package com.streamsets.pipeline.stage.processor.geolocation;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  GEOIP_00("数据库文件'{}'不存在"),
  GEOIP_01("读取数据库文件错误: '{}'"),
  GEOIP_02("地址'{}' 未在'{}'字段找到: '{}'"),
  GEOIP_03("未知的地理位置: '{}'"),
  GEOIP_04("至少需要一个字段"),
  GEOIP_05("提供的数据库（{}）不兼容({})类型"),
  GEOIP_06("'{}'不是有效的IP地址"),
  GEOIP_07("初始化期间发生未知错误: '{}'"),
  GEOIP_08("输入字段名为空"),
  GEOIP_09("输出字段名为空"),
  GEOIP_10("集群模式下，数据库文件'{}'必须是资源目录下相对路径"),
  GEOIP_11("记录'{}'不包含输入字段'{}'"),
  GEOIP_12("字段类型'{}'仅在如下类型数据库中支持: {}"),
  GEOIP_13("IP不能为null"),
//  GEOIP_00("Database file '{}' does not exist"),
//  GEOIP_01("Error reading database file: '{}'"),
//  GEOIP_02("Address '{}' from field '{}' not found: '{}'"),
//  GEOIP_03("Unknown geolocation occurred: '{}'"),
//  GEOIP_04("At least one field is required"),
//  GEOIP_05("Supplied database ({}) is not compatible with database type ({})"),
//  GEOIP_06("'{}' is not a valid IP address"),
//  GEOIP_07("Unknown error occurred during initialization: '{}'"),
//  GEOIP_08("Input field name is empty"),
//  GEOIP_09("Output field name is empty"),
//  GEOIP_10("Database file '{}' must be relative to SDC resources directory in cluster mode"),
//  GEOIP_11("Record '{}' does not contain input field '{}'"),
//  GEOIP_12("Field type '{}' is only supported for the following database types: {}"),
//  GEOIP_13("IP cannot be null"),
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
