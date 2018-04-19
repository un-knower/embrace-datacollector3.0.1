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
package com.streamsets.pipeline.stage.origin.lib;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum ParserErrors implements ErrorCode {
  // Configuration errors
//  PARSER_01("Unsupported charset '{}'"),
//  PARSER_02("Invalid XML element name or XPath expression '{}': {}"),
//  PARSER_03("Cannot parse record from message '{}': {}"),
//  PARSER_04("Max data object length cannot be less than 1"),
//  PARSER_05("Unsupported data format '{}'"),
//  PARSER_06("Messages with XML data cannot have multiple XML documents in a single message"),
//  PARSER_07("Avro Schema must be specified"),
//  PARSER_08("After error '{}' parsing message, another error '{}' was encountered while trying to " +
//    "serialize message '{}' of container '{}'"),
//  PARSER_09("Unmapped XPath namespace prefixes defined in record separator {}"),
  PARSER_01("不支持的字符集 '{}'"),
  PARSER_02("非法XML元素名或XPath表达式 '{}': {}"),
  PARSER_03("无法从消息中解析记录'{}': {}"),
  PARSER_04("最大数据对象长度不能小于1"),
  PARSER_05("不支持的数据格式'{}'"),
  PARSER_06("消息中的XML数据不能存在多个"),
  PARSER_07("未声明Avro元信息"),
  PARSER_08("在'{}'解析消息出错后, 遇到另一个错误'{}'发生在序列化消息'{}'，容器：'{}'"),
  PARSER_09("记录分割器未配置XPath命名空间前缀映射{}"),
  ;
  private final String msg;

  ParserErrors(String msg) {
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
