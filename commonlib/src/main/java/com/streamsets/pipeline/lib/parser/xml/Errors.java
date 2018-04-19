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
package com.streamsets.pipeline.lib.parser.xml;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  XML_PARSER_00("无法推进文件'{}'至偏移量为'{}'的位置"),
  XML_PARSER_01("无法获得当前读取位置: {}"),
  XML_PARSER_02("XML对象超过最大长度限制： readerId '{}', 偏移量'{}', 最大长度'{}'"),
  XML_PARSER_03("无法解析XML: {}"),
//  XML_PARSER_00("Cannot advance reader '{}' to offset '{}'"),
//  XML_PARSER_01("Cannot obtain current reader position: {}"),
//  XML_PARSER_02("XML object exceeded maximum length: readerId '{}', offset '{}', maximum length '{}'"),
//  XML_PARSER_03("Can't parse XML: {}"),
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
