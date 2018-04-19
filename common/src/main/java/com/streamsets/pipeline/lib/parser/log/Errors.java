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
package com.streamsets.pipeline.lib.parser.log;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  LOG_PARSER_00("无法读取文件'{}'偏移位置'{}'"),
  LOG_PARSER_01("解析日志文件出错'{}', 原因: '{}'"),
  LOG_PARSER_02("不支持的格式{}"),
  LOG_PARSER_03("日志行'{}'不符'{}'"),

  LOG_PARSER_04("最大数据对象长度可以为-1或大于0"),
  LOG_PARSER_05("自定义日志格式字段不能为空"),
  LOG_PARSER_06("解析自定义日志格式字符串出错{}，原因：{}"),
  LOG_PARSER_07("解析语法出错{}, 原因：{}"),
  LOG_PARSER_08("正则{}包含{}组，但是字段路径映射到指定组{}."),
  LOG_PARSER_09("解析grok模式出错{}, 原因：{}"),
  LOG_PARSER_10("最大堆栈行数不能小于0"),
  LOG_PARSER_11("编译grok模式定义出错{}, 原因：{}"),
//  LOG_PARSER_00("Cannot advance file '{}' to offset '{}'"),
//  LOG_PARSER_01("Error parsing log line '{}', reason : '{}'"),
//  LOG_PARSER_02("Unsupported format {} encountered"),
//  LOG_PARSER_03("Log line '{}' does not conform to '{}'"),
//
//  LOG_PARSER_04("Max data object length can be -1 or greater than 0"),
//  LOG_PARSER_05("Custom Log Format field cannot be empty"),
//  LOG_PARSER_06("Error parsing custom log format string {}, reason {}"),
//  LOG_PARSER_07("Error parsing regex {}, reason {}"),
//  LOG_PARSER_08("RegEx {} contains {} groups but the field Path to group mapping specifies group {}."),
//  LOG_PARSER_09("Error parsing grok pattern {}, reason {}"),
//  LOG_PARSER_10("Max stack trace lines field cannot be less than 0"),
//  LOG_PARSER_11("Error compiling grok pattern definition {}, reason {}"),
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
