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
package com.streamsets.pipeline.stage.processor.splitter;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  SPLITTER_00("定义至少一个新的拆分字段"),
  SPLITTER_01("字段不能拆分。记录'{}'不包含字段'{}'."),
  SPLITTER_02("记录'{}'拆分项不足"),
  SPLITTER_03("索引'{}'中的字段路径不能为空"),
  SPLITTER_04("字段'{}'无法拆分，因为它是类型是'{}'"),
  SPLITTER_05("无法为字段'{}'赋值，记录：'{}', 原因: {}"),
//  SPLITTER_00("Define at least one new split fields"),
//  SPLITTER_01("Field cannot split. The record '{}' does not include the field '{}'."),
//  SPLITTER_02("The record '{}' does not have enough splits"),
//  SPLITTER_03("Field Path at index '{}' cannot be empty"),
//  SPLITTER_04("Field '{}' cannot be split because it is of type '{}'"),
//  SPLITTER_05("Cannot set field '{}' for record '{}', reason : {}"),
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
