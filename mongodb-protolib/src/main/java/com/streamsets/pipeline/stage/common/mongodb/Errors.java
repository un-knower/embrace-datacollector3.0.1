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
package com.streamsets.pipeline.stage.common.mongodb;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  MONGODB_00("创建MongoClientURI实例失败: {}"),
  MONGODB_01("创建MongoClient实例失败: {}"),
  MONGODB_02("获取数据库失败: '{}'. {}"),
  MONGODB_03("获取集合失败: '{}'. {}"),
  MONGODB_04("集合无法尾读，因为'{}'不是固定集合."),
  MONGODB_05("偏移量字段'{}'必须是{}的实例"),
  MONGODB_06("从集合中检索文档出错: '{}'. {}"),
  MONGODB_07("无法获取'{}'的<host:port>"),
  MONGODB_08("无法解析端口: '{}'"),
  MONGODB_09("未知主机: '{}'"),
  MONGODB_10("解析实体失败: {}"),
  MONGODB_11("偏移量追踪字段: '{}'在文档中不存在: '{}'"),
  MONGODB_12("写入数据库出错: {}"),
  MONGODB_13("序列化记录出错'{}': {}"),
  MONGODB_14("不支持的操作类型'{}'出现在记录{}中"),
  MONGODB_15("操作类型(insert, update或delete)未在记录{}的头信息中指定"),
  MONGODB_16("记录{}不包含期望的唯一键字段{}"),
  MONGODB_17("向Mongo写入数据出错: {}"),
  MONGODB_18("操作'{}'需要配置唯一键"),
  MONGODB_19("当偏移量字段为ObjectId类型时，必须指定初始偏移量"),

  MONGODB_30("Oplog文档缺失以下必填字段'{}'"),
  MONGODB_31("Oplog偏移量无效，不能解析'{}'偏移量，偏移量格式应为'time_t::ordinal'。因为{}"),
  MONGODB_32("无效的初始偏移量值'{}', 当'{}'不是-1时，值应大于-1"),
  MONGODB_33("无效的Oplog集合名称'{}', Oplog集合应以'oplog.'开头"),
  MONGODB_34("无法创建凭证对象: {}"),
//  MONGODB_00("Failed to create MongoClientURI: {}"),
//  MONGODB_01("Failed to create MongoClient: {}"),
//  MONGODB_02("Failed to get database: '{}'. {}"),
//  MONGODB_03("Failed to get collection: '{}'. {}"),
//  MONGODB_04("Collection isn't tailable because '{}' is not a capped collection."),
//  MONGODB_05("Offset Field '{}' must be an instance of {}"),
//  MONGODB_06("Error retrieving documents from collection: '{}'. {}"),
//  MONGODB_07("Failed to get <host:port> for '{}'"),
//  MONGODB_08("Failed to parse port: '{}'"),
//  MONGODB_09("Unknown host: '{}'"),
//  MONGODB_10("Failed to parse entry: {}"),
//  MONGODB_11("Offset tracking field: '{}' missing from document: '{}'"),
//  MONGODB_12("Error writing to database: {}"),
//  MONGODB_13("Error serializing record '{}': {}"),
//  MONGODB_14("Unsupported operation type '{}' found in record {}"),
//  MONGODB_15("Operation type (insert, update or delete) is not specified in the header for record {}"),
//  MONGODB_16("Record {} does not contain the expected unique key field {}"),
//  MONGODB_17("Error writing records to Mongo : {}"),
//  MONGODB_18("Operation '{}' requires unique key to be configured"),
//  MONGODB_19("Initial Offset is required when the offset field is ObjectId type"),
//
//  MONGODB_30("Oplog Document Missing the follow mandatory fields '{}'"),
//  MONGODB_31("Oplog Offset Invalid, Cannot parse offset '{}'," +
//      " offset should be of the form 'time_t::ordinal'. Reason {}"),
//  MONGODB_32("Invalid Initial Offset Value for '{}', should be greater than -1 if '{}' is not -1"),
//  MONGODB_33("Invalid Oplog Collection Name '{}', Oplog collection should start with 'oplog.'"),
//  MONGODB_34("Can't create credential object: {}"),

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
