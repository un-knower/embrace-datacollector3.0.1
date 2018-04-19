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
package com.streamsets.pipeline.stage.lib.kudu;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  KUDU_00("连接Kudu主节点错误'{}': {}"),
  KUDU_01("'{}'表不存在"),
  KUDU_02("参数无效"),
  KUDU_03("Kudu通信错误: {}"),
  KUDU_04("列或字段'{}'不是'{}'类型"),
  KUDU_05("'{}'列不存在"),
  KUDU_06("'{}'列映射的'{}'字段不能为空"),
  KUDU_08("'{}'行已存在"),
  KUDU_09("'{}'字段与目标类型不匹配'{}': {}"),
  KUDU_10("列/字段'{}'是'{}'类型,在数据采集器中没有对应的类型"),
  KUDU_11("节点未正确初始化，无法批量写入"),
  KUDU_12("无效的表名模板表达式'{}': {}"),
  KUDU_13("操作不支持: {}"),
  KUDU_14("未知的对不支持操作的处理: {}"),
  KUDU_15("未找到行键'{}'"),

  KUDU_30("查找处理器必须指定列映射: {}"),
  KUDU_31("未找到行"),
  KUDU_32("在记录中未找到主键字段'{}'"),
  KUDU_33("不支持的列类型: {}"),
  KUDU_34("键-列映射中未配置主键'{}'"),
  KUDU_35("'{}'列缺失值"),
//  KUDU_00("Error connecting to kudu master '{}': {}"),
//  KUDU_01("Table '{}' does not exist"),
//  KUDU_02("Parameter is not valid"),
//  KUDU_03("Errors while interacting with Kudu: {}"),
//  KUDU_04("Column or field '{}' is not type '{}'"),
//  KUDU_05("Column '{}' does not exist"),
//  KUDU_06("Column '{}' mapped from field '{}' is not nullable"),
//  KUDU_08("Row '{}' already exists"),
//  KUDU_09("Field '{}' does not match destination type '{}': {}"),
//  KUDU_10("Column/field '{}' is type '{}' which doesn't have an associated StreamSets type"),
//  KUDU_11("Stage not initialized correctly, cannot write batch"),
//  KUDU_12("Invalid table name template expression '{}': {}"),
//  KUDU_13("Operation not supported: {}"),
//  KUDU_14("Unknown action for unsupported operation: {}"),
//  KUDU_15("Row key '{}' not found"),
//
//  KUDU_30("Column mappings must be specified for lookup processor: {}"),
//  KUDU_31("No rows found"),
//  KUDU_32("Primary key field '{}' not found in record"),
//  KUDU_33("Unsupported column type: {}"),
//  KUDU_34("Primary key '{}' is not configured in Key Column Mapping"),
//  KUDU_35("Missing a value for column '{}'"),
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
