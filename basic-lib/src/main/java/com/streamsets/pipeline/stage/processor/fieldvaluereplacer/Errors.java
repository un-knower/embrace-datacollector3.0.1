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
package com.streamsets.pipeline.stage.processor.fieldvaluereplacer;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  VALUE_REPLACER_00("'{}'类型转换失败'{}': {}"),
  VALUE_REPLACER_01("记录'{}'不包含字段'{}'"),
  VALUE_REPLACER_02("不能条件替换数据类型{}"),
  VALUE_REPLACER_03("无法将字符串值转换为{}类型，所在字段：{}"),
  VALUE_REPLACER_04("以下操作符需数字值：< = >"),
  VALUE_REPLACER_05("需要替换值"),
  VALUE_REPLACER_06("评估条件错误{}. 原因{}"),
  VALUE_REPLACER_07("评估字段表达式错误{}. 原因: {}"),
//  VALUE_REPLACER_00("Failed to convert value '{}' to type '{}': {}"),
//  VALUE_REPLACER_01("Record '{}' does not contain fields '{}'"),
//  VALUE_REPLACER_02("Cannot Conditionally Replace Data Type {}"),
//  VALUE_REPLACER_03("Cannot convert String value to type {} in field {}"),
//  VALUE_REPLACER_04("Literal value required for < = > operands"),
//  VALUE_REPLACER_05("Replacement value required"),
//  VALUE_REPLACER_06("Error in evaluating condition {}. Reason : {}"),
//  VALUE_REPLACER_07("Error in evaluating field expression {}. Reason : {}"),
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
