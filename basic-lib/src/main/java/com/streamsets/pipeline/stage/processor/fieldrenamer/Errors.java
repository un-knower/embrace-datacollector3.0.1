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
package com.streamsets.pipeline.stage.processor.fieldrenamer;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  FIELD_RENAMER_00("记录'{}'不包含'{}'字段"),
  FIELD_RENAMER_01("输出字段'{}'不能覆盖记录'{}'"),
  FIELD_RENAMER_02("无效字段表达式: {}"),
  FIELD_RENAMER_03("由多个表达式匹配的同一字段. {} "),
  FIELD_RENAMER_04("无法设置'{}'字段值. 原因: {}"),
  FIELD_RENAMER_05("缓存异常'{}'"),
//  FIELD_RENAMER_00("Record '{}' does not contain fields '{}'"),
//  FIELD_RENAMER_01("Target Fields '{}' cannot be overwritten for record '{}'"),
//  FIELD_RENAMER_02("Invalid From Field Expression : {}"),
//  FIELD_RENAMER_03("Same fields matched by multiple expressions. {} "),
//  FIELD_RENAMER_04("Cannot set value in field '{}'. Reason : {}"),
//  FIELD_RENAMER_05("Cache exception '{}'"),
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
