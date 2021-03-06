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
package com.streamsets.pipeline.solr.api;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  SOLR_00("Solr连接地址不能为空"),
  SOLR_01("ZooKeeper连接串不能为空"),
  SOLR_02("字段值不能为空"),
  SOLR_03("无法连接Solr实例: {}"),
  SOLR_04("无法写入记录'{}': {}"),
  SOLR_05("无法索引'{}'记录: {}"),
  SOLR_06("记录缺少映射的字段{}"),
//  SOLR_00("Solr URI cannot be empty"),
//  SOLR_01("ZooKeeper Connection String cannot be empty"),
//  SOLR_02("Fields value cannot be empty"),
//  SOLR_03("Could not connect to the Solr instance: {}"),
//  SOLR_04("Could not write record '{}': {}"),
//  SOLR_05("Could not index '{}' records: {}"),
//  SOLR_06("Record is missing mapped field {}"),
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
