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
package com.streamsets.pipeline.stage.origin.logtail;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  TAIL_01("至少指定一个目录"),
  TAIL_02("无法初始化多目录数据源: {}"),
  TAIL_03("无效的数据格式'{}'"),
  TAIL_04("不能同时指定同一个文件路径（和模式）: '{}'"),
  TAIL_05("归档目录不能为空"),
  TAIL_06("归档目录不存在"),
  TAIL_07("归档目录不是一个目录"),
  TAIL_08("'{}'配置项需要'{}'在'{}'文件名中存在"),
  TAIL_09("'{}'配置在'{}'模式中含有无效的正则表达式: {}"),
  TAIL_15("文件路径'{}'必须包含'{}'标记"),
  TAIL_16("'{}'配置在目录元素中不能包含'{}'标记"),
  TAIL_17("'{}'配置使用了'文件匹配模式', 不能包含通配符"),
  TAIL_18("'{}'配置包含无效的表达式: {}"),
  TAIL_19("'{}'配置中'首文件'无效"),
  TAIL_20("文件路径不能为空或NULL"),

  TAIL_10("无法反序列号偏移量: {}"),
  TAIL_11("读取文件发生异常 '{}': {}"),
  TAIL_12("无法解析记录'{}': {}"),
  TAIL_13("服务序列化偏移量: {}"),
  TAIL_14("无法获取文件开始/结束事件: {}"),
//  TAIL_01("At least one directory must be specified"),
//  TAIL_02("Could not initialize multi-directory reader: {}"),
//  TAIL_03("Invalid data format '{}'"),
//  TAIL_04("The same file path (and pattern) cannot be specified more than once: '{}'"),
//  TAIL_05("Archive directory cannot be empty"),
//  TAIL_06("Archive directory does not exist"),
//  TAIL_07("Archive directory path is not a directory"),
//  TAIL_08("The configuration for '{}' requires the '{}' token in the '{}' file name"),
//  TAIL_09("The configuration for '{}' has an invalid regular expression in the '{}' pattern: {}"),
//  TAIL_15("The file path '{}' must have the '{}' token in it"),
//  TAIL_16("The configuration for '{}' cannot have the '{}' token in a directory element"),
//  TAIL_17("The configuration for '{}' uses 'Files matching a pattern', it cannot have wildcards"),
//  TAIL_18("The configuration for '{}' is an invalid expression: {}"),
//  TAIL_19("The configuration for '{}' has an invalid 'First File'"),
//  TAIL_20("File path cannot be null or empty"),
//
//  TAIL_10("Could not deserialize offset: {}"),
//  TAIL_11("Error reading file '{}': {}"),
//  TAIL_12("Cannot parse record '{}': {}"),
//  TAIL_13("Could not serialize offset: {}"),
//  TAIL_14("Could not get file start/end events: {}"),
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
