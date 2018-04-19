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

package com.streamsets.pipeline.stage.processor.jsongenerator;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  JSON_00("字段'{}'不存在于记录'{}'中. 无法序列化字段."),
  JSON_01("字段'{}'设置为NULL. 无法序列化字段."),
  JSON_02("字段'{}'的类型为'{}'，但应该是MAP, LIST_MAP或LIST. 无法序列化字段."),
  JSON_03("序列化字段失败: '{}'"),
//  JSON_00("Field '{}' does not exist in record '{}'. Cannot serialize the field."),
//  JSON_01("Field '{}' is set to NULL. Cannot serialize the field."),
//  JSON_02("Field '{}' is a '{}' but must be a MAP, LIST_MAP, or LIST. Cannot serialize the field."),
//  JSON_03("Failed to serialize field: '{}'"),
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
