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
package com.streamsets.pipeline.stage.processor.hive;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  HIVE_METADATA_01("分区值表达式缺失"),
  HIVE_METADATA_02("记录缺少必要的数据{}"),
  HIVE_METADATA_03("验证失败{} : 无效的Hive字符'{}'"),
  HIVE_METADATA_04("无效的时间基准表达式'{}': {}"),
  HIVE_METADATA_05("配置文件缺少{}"),
  HIVE_METADATA_06("外部表需要{}"),
  HIVE_METADATA_07("无效的值{}，{} 字段：{}, 最小值: {}, 最大值: 38"),
  HIVE_METADATA_08("刻度值{}无效，字段{}中值应该小于或等于精度值: {}"),
  HIVE_METADATA_09("无效的分区类型: {}"),
  HIVE_METADATA_10("字符不可用于分区值: {}"),
  HIVE_METADATA_11("无效的列注释'{}': {}"),
  HIVE_METADATA_12("无法评估表达式'{}'，记录：'{}': {}")
//  HIVE_METADATA_01("Value Expression for partition value is missing"),
//  HIVE_METADATA_02("Record is missing necessary data {}"),
//  HIVE_METADATA_03("Failed validation on {} : Invalid character for Hive '{}'"),
//  HIVE_METADATA_04("Invalid time basis expression '{}': {}"),
//  HIVE_METADATA_05("{} is missing in the configuration file"),
//  HIVE_METADATA_06("{} is required for external table"),
//  HIVE_METADATA_07("Invalid value {} for {} in field {}, minimum: {}, maximum: 38"),
//  HIVE_METADATA_08("Invalid value {} for scale in field {}, should be less than or equal to precision's value: {}"),
//  HIVE_METADATA_09("Invalid type for partition: {}"),
//  HIVE_METADATA_10("Unsupported character to use for partition value: {}"),
//  HIVE_METADATA_11("Invalid comment for column '{}': {}"),
//  HIVE_METADATA_12("Cannot evaluate expression '{}' for record '{}': {}")
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

