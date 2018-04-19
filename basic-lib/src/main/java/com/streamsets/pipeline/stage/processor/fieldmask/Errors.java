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
package com.streamsets.pipeline.stage.processor.fieldmask;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  MASK_00("字段{}不是字符串类型，记录：'{}'"),
  MASK_01("{}不是有效的组编号. 正则表达式{}支持的组编号为[1 - {}]"),
  MASK_02("必须指定组编号"),
  MASK_03("正则表达式{}没有组"),
//  MASK_00("Field(s) {} are not String in record '{}'"),
//  MASK_01("{} is not a valid group number. The regular expression {} supports group numbers in range [1 - {}]"),
//  MASK_02("Group numbers to show must be specified"),
//  MASK_03("Regular Expression {} has no groups"),
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
