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
package com.streamsets.pipeline.stage.lib.hive;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  HIVE_00("同一列不能有多个字段映射: '{}'"),
  HIVE_01("异常: {}"),
  HIVE_02("模式'{}'不存在"),
  HIVE_03("数据表'{}.{}'不存在"),
  HIVE_04("Thrift协议异常{}"),
  HIVE_05("Hive元数据异常: {}"),
  HIVE_06("配置文件'{}'缺失'{}'"),
  HIVE_07("配置目录'{}'不存在"),
  HIVE_08("记录中没有分区字段'{}'"),
  HIVE_09("Hive Streaming异常: {}"),
  HIVE_11("查询登录用户失败"),
  HIVE_12("创建Hive端点失败: {}"),
  HIVE_13("未指定Hive源存储Thrift地址或Hive配置目录"),
  HIVE_14("Hive源存储Thrift地址{}无效"),
  HIVE_15("Classpath下未找到Hive JDBC驱动{}"),
  HIVE_16("不支持的HMS缓存类型: {}"),
  HIVE_17("元数据记录中不存在或无效 {} : {}"),
  HIVE_18("将AVRO模式保存至HDFS出错.目录位置: {}. 原因: {}"),
  HIVE_19("不支持的类型: {}"),
  HIVE_20("执行SQL异常: {}, 原因:{}"),
  HIVE_21("列类型缺失'{}', 期望值: {}, 实际值: {}"),
  HIVE_22("无法使用Hive默认连接地址(以{}开头)创建连接。原因：{}"),
  HIVE_23("TBL属性“{}”匹配,实际值：{}，期望值：{}"),
  HIVE_24("不支持Hive {} 到Avro类型转换"),
  HIVE_25("正在不存在的表上创建分区: {}"),
  HIVE_26("无效的小数值 {},字段:{}: {} {}大于期望值{} "),
  HIVE_27("数据表分区信息缺失{}"),
  HIVE_28("无法匹配分区列{},表:{}. 期望类型: {}, 实际类型: {}"),
  HIVE_29("{}无法在字段'{}'上计算 - 表达式'{}'评估为'{}'"),
  HIVE_30("无效的列名{}"),
  HIVE_31("分区位置不匹配. 实际值: {}, 期望值: {}"),
  HIVE_32("表{}使用Storage格式类型{}创建, 但是需要{}"),
  HIVE_33("记录 {} 包含不支持的根类型 {}"),
  HIVE_34("Hive连接失败: {}"),
  HIVE_35("无法找到数据库位置: {}"),
  HIVE_36("不能将分区位置解析为地址: {}"),
  HIVE_37("不支持的数据格式: {}"),
  HIVE_38("意外的授权方法{}, 期望值:{}"),
  HIVE_39("无法评估表达式'{}',记录:'{}': {}"),
  HIVE_40("'{}'列用作分区列,同时出现在输入数据中"),
//  HIVE_00("Cannot have multiple field mappings for the same column: '{}'"),
//  HIVE_01("Error: {}"),
//  HIVE_02("Schema '{}' does not exist."),
//  HIVE_03("Table '{}.{}' does not exist."),
//  HIVE_04("Thrift protocol error: {}"),
//  HIVE_05("Hive Metastore error: {}"),
//  HIVE_06("Configuration file '{}' is missing from '{}'"),
//  HIVE_07("Configuration dir '{}' does not exist"),
//  HIVE_08("Partition field paths '{}' missing from record"),
//  HIVE_09("Hive Streaming Error: {}"),
//  HIVE_11("Failed to get login user"),
//  HIVE_12("Failed to create Hive Endpoint: {}"),
//  HIVE_13("Hive Metastore Thrift URL or Hive Configuration Directory is required."),
//  HIVE_14("Hive Metastore Thrift URL {} is not a valid URI"),
//  HIVE_15("Hive JDBC Driver {} not present in the class Path"),
//  HIVE_16("Unsupported HMS Cache Type: {}"),
//  HIVE_17("Information {} missing or invalid in the metadata record: {}"),
//  HIVE_18("Error when serializing AVRO schema to HDFS folder location: {}. Reason: {}"),
//  HIVE_19("Unsupported Type: {}"),
//  HIVE_20("Error executing SQL: {}, Reason:{}"),
//  HIVE_21("Type Mismatch for column '{}', Expected: {}, Actual: {}"),
//  HIVE_22("Cannot make connection with default hive database starting with URL: {}. Reason:{}"),
//  HIVE_23("TBL Properties '{}' Mismatch: Actual: {} , Expected: {}"),
//  HIVE_24("Type conversion from Hive.{} to Avro Type is not supported"),
//  HIVE_25("Trying to create partition for non existing table: {}"),
//  HIVE_26("Invalid decimal value {} in field {}: {} {} is more then expected {} "),
//  HIVE_27("Partition Information mismatch for the table {}"),
//  HIVE_28("Partition Column {} has Type Mismatch in table {}. Expected Type: {}, Actual Type: {}"),
//  HIVE_29("Can't calculate {} for field '{}' - expression '{}' evaluated to '{}'"),
//  HIVE_30("Invalid column name {}"),
//  HIVE_31("Partition Location mismatch. Actual : {}, Expected: {}"),
//  HIVE_32("Table {} is created using Storage Format Type {}, but {} requested instead "),
//  HIVE_33("Record {} have unsupported root type {}"),
//  HIVE_34("Connection to Hive have failed: {}"),
//  HIVE_35("Can't find database location: {}"),
//  HIVE_36("Can't parse partition location as URI: {}"),
//  HIVE_37("Unsupported Data Format: {}"),
//  HIVE_38("Unexpected authorization method {}, expected {}"),
//  HIVE_39("Cannot evaluate expression '{}' for record '{}': {}"),
//  HIVE_40("Column '{}' is used for partition column and at the same time appears in input data"),
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
