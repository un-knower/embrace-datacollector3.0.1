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
package com.streamsets.pipeline.stage.origin.spooldir;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  SPOOLDIR_00("无法归档文件'{}': {}"),
  SPOOLDIR_01("处理文件失败'{}'，出错位置'{}': {}"),

  SPOOLDIR_02("文件'{}'中偏移量为'{}'的对象长度超出最大长度限制"),
  SPOOLDIR_04("文件 '{}'不能完全处理, 在偏移量为'{}'处失败: {}"),

  SPOOLDIR_05("不支持的字符集'{}'"),

  SPOOLDIR_06("缓冲区限制必须大于等于64 KB"),

  SPOOLDIR_10("不支持的数据格式'{}'"),
  SPOOLDIR_11("目录路径不能为空"),
  SPOOLDIR_12("目录'{}'不存在"),
  SPOOLDIR_13("路径'{}'不是目录"),
  SPOOLDIR_14("批处理大小不能小于1"),
  SPOOLDIR_15("批处理等待时间'{}'不能小于1"),
  SPOOLDIR_16("无效的GLOB文件模式'{}': {}"),
  SPOOLDIR_17("目录最大文件数不能小于1"),
  SPOOLDIR_18("待处理的首文件'{}'不符合文件模式'{}'"),
  SPOOLDIR_19("归档保留时间不能小于0"),
  SPOOLDIR_20("数据对象最大长度不能小于1"),
  SPOOLDIR_23("无效的XML元素名'{}'"),
  SPOOLDIR_24("无法创建解析工厂实例: '{}'"),
  SPOOLDIR_25("低级别的读溢出限制'{} KB'必须大于'{} KB'"),
  SPOOLDIR_27("自定义日志格式字段不能为空"),
  SPOOLDIR_28("分析自定义日志格式字符串错误{}，原因：{}"),
  SPOOLDIR_29("解析表达式{}错误, 原因：{}"),
  SPOOLDIR_30("表达式{}包含{}组，但字段路径映射指定组是{}."),
  SPOOLDIR_31("解析grok模式错误，原因：{}"),
  SPOOLDIR_32("文件模式不能为空"),
  SPOOLDIR_33("无法序列化偏移量: {}"),
  SPOOLDIR_34("无法反序列化偏移量: {}"),
  SPOOLDIR_35("Spool目录运行失败. 原因：{}"),
//  SPOOLDIR_00("Could not archive file '{}': {}"),
//  SPOOLDIR_01("Failed to process file '{}' at position '{}': {}"),
//
//  SPOOLDIR_02("Object in file '{}' at offset '{}' exceeds maximum length"),
//  SPOOLDIR_04("File '{}' could not be fully processed, failed on '{}' offset: {}"),
//
//  SPOOLDIR_05("Unsupported charset '{}'"),
//
//  SPOOLDIR_06("Buffer Limit must be equal or greater than 64 KB"),
//
//  SPOOLDIR_10("Unsupported data format '{}'"),
//  SPOOLDIR_11("Directory path cannot be empty"),
//  SPOOLDIR_12("Directory '{}' does not exist"),
//  SPOOLDIR_13("Path '{}' is not a directory"),
//  SPOOLDIR_14("Batch size cannot be less than 1"),
//  SPOOLDIR_15("Batch wait time '{}' cannot be less than 1"),
//  SPOOLDIR_16("Invalid GLOB file pattern '{}': {}"),
//  SPOOLDIR_17("Max files in directory cannot be less than 1"),
//  SPOOLDIR_18("First file to process '{}' does not match the file pattern '{}"),
//  SPOOLDIR_19("Archive retention time cannot be less than 0"),
//  SPOOLDIR_20("Max data object length cannot be less than 1"),
//  SPOOLDIR_23("Invalid XML element name '{}'"),
//  SPOOLDIR_24("Cannot create the parser factory: '{}'"),
//  SPOOLDIR_25("Low level reader overrun limit '{} KB' must be at least '{} KB'"),
//  SPOOLDIR_27("Custom Log Format field cannot be empty"),
//  SPOOLDIR_28("Error parsing custom log format string {}, reason {}"),
//  SPOOLDIR_29("Error parsing regex {}, reason {}"),
//  SPOOLDIR_30("RegEx {} contains {} groups but the field Path to group mapping specifies group {}."),
//  SPOOLDIR_31("Error parsing grok pattern {}, reason {}"),
//  SPOOLDIR_32("File Pattern cannot be empty"),
//  SPOOLDIR_33("Cannot Serialize Offset: {}"),
//  SPOOLDIR_34("Cannot Deserialize Offset: {}"),
//  SPOOLDIR_35("Spool Directory Runner Failed. Reason {}"),
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
