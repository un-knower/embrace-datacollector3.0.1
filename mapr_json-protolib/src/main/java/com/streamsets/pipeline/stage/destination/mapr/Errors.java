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
package com.streamsets.pipeline.stage.destination.mapr;
import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  MAPR_JSON_01("表名不能为空"),
  MAPR_JSON_02("表'{}'不存在或无法访问"),
  MAPR_JSON_03("创建表'{}'出错"),
  MAPR_JSON_04("刷新'{}'时发生异常"),
  MAPR_JSON_05("关闭表'{}'时发生异常 "),
  MAPR_JSON_06("执行InsertOrReplace '{}'时发生异常 "),
  MAPR_JSON_07("插入记录时发生异常. '{}' "),
  MAPR_JSON_08("_id列不能为空"),
  MAPR_JSON_09("创建、写入、关闭JSON文档时出现异常: '{}'"),
  MAPR_JSON_10("新建MapRDB文档发生异常. '{}'"),
  MAPR_JSON_11("文档主键字段'{}'不存在或在记录中为空."),
  MAPR_JSON_12("二进制主键错误-无效值或{}字段类型为{}，不是byte数组. 是否使用字段类型转换器?"),
  MAPR_JSON_13("转换主键字段出错'{}'"),
  MAPR_JSON_14("行键转换为byte数组失败 - 类型为'{}' "),
  MAPR_JSON_15("记录主键字段'{}'不存在."),
  MAPR_JSON_16("验证表名中的UI字段EL'{}'出错."),
  MAPR_JSON_17("不支持的操作类型{}"),
  MAPR_JSON_18("更新记录时发生异常. '{}' "),
  MAPR_JSON_19("删除记录时发生异常. '{}' "),
//  MAPR_JSON_01("Table Name cannot be blank"),
//  MAPR_JSON_02("Table '{}' does not exist or cannot access table"),
//  MAPR_JSON_03("Error creating table '{}'"),
//  MAPR_JSON_04("Exception while flushing '{}' "),
//  MAPR_JSON_05("Exception while closing table '{}' "),
//  MAPR_JSON_06("Exception while calling InsertOrReplace '{}' "),
//  MAPR_JSON_07("Exception while Inserting record. '{}' "),
//  MAPR_JSON_08("Field to use as _id column cannot be blank"),
//  MAPR_JSON_09("Exception while creating, writing or closing JSON document: '{}'"),
//  MAPR_JSON_10("Exception creating new MapRDB document. '{}'"),
//  MAPR_JSON_11("Document key field '{}' does not exist in the record or is empty (or null)."),
//  MAPR_JSON_12("Binary key error - invalid value or Field {} is type {} - not byte array. Use a FieldTypeConverter?"),
//  MAPR_JSON_13("Exception converting key field '{}'"),
//  MAPR_JSON_14("Conversion to byte array failed for Row Key - type '{}' "),
//  MAPR_JSON_15("Field selected for record key '{}' does not exist."),
//  MAPR_JSON_16("Error Validating EL '{}' in Table Name UI field. "),
//  MAPR_JSON_17("Unsupported operation type {}"),
//  MAPR_JSON_18("Exception while Updating record. '{}' "),
//  MAPR_JSON_19("Exception while Deleting record. '{}' "),
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
