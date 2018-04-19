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
package com.streamsets.pipeline.lib.util;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum CommonError implements ErrorCode {

  CMN_0100("不支持的字段类型'{}'，值：'{}'，记录：'{}'"),
  CMN_0101("将记录'{}'转换成字符串出错: {}"),
  CMN_0102("必须对列名映射的字段路径进行配置，以便将记录转换为CSV"),
  CMN_0103("将记录'{}'转换成字符串出错: {}"),
  CMN_0104("评估表达式出错{}: {}"),
  CMN_0105("解析表达式出错{}: {}"),
  CMN_0106("解析'{}'字段单位出错，类型：{} (java类：{}): {}"),
//  CMN_0100("Unsupported field type '{}' with value '{}' encountered in record '{}'"),
//  CMN_0101("Error converting record '{}' to string: {}"),
//  CMN_0102("Field Path to Column Name mapping must be configured to convert records to CSV."),
//  CMN_0103("Error converting record '{}' to string: {}"),
//  CMN_0104("Error evaluating expression {}: {}"),
//  CMN_0105("Error parsing expression {}: {}"),
//  CMN_0106("Error resolving union for field '{}' of SDC Type {} (java class {}): {}"),

  ;
  private final String msg;

  CommonError(String msg) {
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
