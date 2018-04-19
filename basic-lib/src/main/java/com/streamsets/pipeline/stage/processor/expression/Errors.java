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
package com.streamsets.pipeline.stage.processor.expression;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  EXPR_00("无效的表达式'{}': {}"),
  EXPR_01("无效的常数'{}': {}"),
  EXPR_02("记录'{}'无法设置'{}'字段中的值"),
  EXPR_03("无法评估表达式'{}'，记录：'{}': {}"),
  EXPR_04("记录'{}'无法设置'{}'字段中的值. 原因: {}"),
  EXPR_05("记录'{}'不包含'{}'字段, 设置'{}'属性，使用的表达式: {}"),
//  EXPR_00("Invalid expression '{}': {}"),
//  EXPR_01("Invalid constant '{}': {}"),
//  EXPR_02("Record '{}' cannot set value in field '{}'"),
//  EXPR_03("Cannot evaluate expression '{}' for record '{}': {}"),
//  EXPR_04("Record '{}' cannot set value in field '{}'. Reason : {}"),
//  EXPR_05("Record '{}' does not contain a field with path '{}', for setting an '{}' attribute with expression: {}"),
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
