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
package com.streamsets.pipeline.stage.lib.kinesis;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {

  KINESIS_00("PUT记录失败: {}"),
  KINESIS_01("指定的流名称无效. 请确认已指定正确的AWS域. 原因: {}"),
  KINESIS_02("不支持的分区策略: '{}'"),
  KINESIS_03("解析Kinesis输入记录失败，顺序号: {}"),
  KINESIS_04("结束批处理错误"),
  KINESIS_05("序列化记录失败: '{}' - {}"),
  KINESIS_06("评估分区表达式失败'{}'，记录：'{}': {}"),
  KINESIS_07("异常JSON内容-Firehose不支持JSON数组对象"),
  KINESIS_08("序列化记录大小共{}bytes, 大于1MB"),
  KINESIS_09("端点不能为空"),
  KINESIS_10("获取预览数据错误: '{}'"),
  KINESIS_11("无法删除DynamoDB表'{}'. 请确认您具有该权限"),
  KINESIS_12("无法解析凭证: {}"),
//  KINESIS_00("Failed to put record: {}"),
//  KINESIS_01("Specified stream name is not available. Ensure you've specified the correct AWS Region. Cause: {}"),
//  KINESIS_02("Unsupported partition strategy: '{}'"),
//  KINESIS_03("Failed to parse incoming Kinesis record w/ sequence number: {}"),
//  KINESIS_04("Error completing batch"),
//  KINESIS_05("Failed to serialize record: '{}' - {}"),
//  KINESIS_06("Error evaluating the partition expression '{}' for record '{}': {}"),
//  KINESIS_07("Error JSON Content - JSON array of objects not supported for Firehose Target"),
//  KINESIS_08("Serialized record is {} bytes, which is larger than the allowed 1MB"),
//  KINESIS_09("Endpoint cannot be empty"),
//  KINESIS_10("Error fetching preview data: '{}'"),
//  KINESIS_11("Unable to delete DynamoDB table '{}'. Please verify that you have sufficient privileges"),
//  KINESIS_12("Can't resolve credentials: {}"),
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
