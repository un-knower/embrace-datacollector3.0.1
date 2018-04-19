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
package com.streamsets.pipeline.stage.processor.selector;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  SELECTOR_00("流选择器必须包含默认输出流"),
  SELECTOR_01("条件'{}'数量与输出流'{}'数量不匹配"),
  SELECTOR_02("流选择器未定义输出流'{}'的条件'{}'"),
  SELECTOR_03("无效的条件'{}': {}"),
  SELECTOR_04("无效的常数'{}': {}"),
  SELECTOR_05("记录'{}'与所有条件均不匹配"),
  SELECTOR_06("记录'{}'不满足任何条件. 任务失败."),
  SELECTOR_07("最后的条件必须为'default'"),
  SELECTOR_08("定义条件 '{}'使用如下语法: ${<condition>}"),
  SELECTOR_09("评估记录'{}'错误，因为'{}'条件: {}"),
//  SELECTOR_00("The Stream Selector must include a default output stream"),
//  SELECTOR_01("The number of conditions '{}' does not match the number of output streams '{}'"),
//  SELECTOR_02("The Stream Selector does not define the output stream '{}' associated with condition '{}'"),
//  SELECTOR_03("Invalid condition '{}': {}"),
//  SELECTOR_04("Invalid constant '{}': {}"),
//  SELECTOR_05("Record '{}' does not match any condition"),
//  SELECTOR_06("Record '{}' does not satisfy any condition. Failing the pipeline."),
//  SELECTOR_07("The last condition must be 'default'"),
//  SELECTOR_08("Define the condition '{}' using the following syntax: ${<condition>}"),
//  SELECTOR_09("Error evaluating record '{}' for '{}' condition: {}"),
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
