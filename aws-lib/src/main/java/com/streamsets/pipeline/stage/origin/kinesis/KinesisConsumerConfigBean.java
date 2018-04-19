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
package com.streamsets.pipeline.stage.origin.kinesis;

import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream;
import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.DataFormat;
import com.streamsets.pipeline.stage.lib.aws.ProxyConfig;
import com.streamsets.pipeline.stage.lib.kinesis.KinesisConfigBean;
import com.streamsets.pipeline.stage.origin.lib.DataParserFormatConfig;

public class KinesisConsumerConfigBean extends KinesisConfigBean {

  @ConfigDefBean(groups = "KINESIS")
  public DataParserFormatConfig dataFormatConfig;

  @ConfigDefBean(groups = "ADVANCED")
  public ProxyConfig proxyConfig = new ProxyConfig();

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "应用名称",
      description = "代表Kafka消费集群的Kinesis名称",
//      label = "Application Name",
//      description = "Kinesis equivalent of a Kafka Consumer Group",
      displayPosition = 30,
      group = "#0"
  )
  public String applicationName;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "初始位置",
//      label = "Initial Position",
      defaultValue = "LATEST",
      description = "当使用新的应用名称时，是否只获取新消息，或者从最早的消息开始读取。",
//      description = "When using a new application name, whether to get only new messages, or read from the oldest.",
      displayPosition = 40,
      group = "#0"
  )
  @ValueChooserModel(InitialPositionInStreamChooserValues.class)
  public InitialPositionInStream initialPositionInStream;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      min = 0,
      label = "初始时间戳",
      defaultValue = "",
      description = "开始读流的初始时间戳(ms)",
//      label = "Initial Timestamp",
//      defaultValue = "",
//      description = "Timestamp in milliseconds to set when using AT_TIMESTAMP for the initial position in a stream",
      dependsOn = "initialPositionInStream",
      triggeredByValue = "AT_TIMESTAMP",
      displayPosition = 45,
      group = "#0"
  )
  public long initialTimestamp;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "JSON",
      label = "数据格式",
      description = "从Kinesis接收数据的数据格式",
//      label = "Data Format",
//      description = "Data format to use when receiving records from Kinesis",
      displayPosition = 1,
      group = "DATA_FORMAT"
  )
  @ValueChooserModel(DataFormatChooserValues.class)
  public DataFormat dataFormat;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "1000",
      label = "最大批处理消息数",
      description = "每批次处理的最大记录数据. Kinesis返回结果不会超过2MB/s/shard.",
//      label = "Max Batch Size (messages)",
//      description = "Max number of records per batch. Kinesis will not return more than 2MB/s/shard.",
      displayPosition = 60,
      group = "#0",
      min = 1,
      max = Integer.MAX_VALUE
  )
  public int maxBatchSize;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "1000",
      label = "读间隔时间(ms)",
      description = "每次请求之间的间隔时间，不能低于200ms.建议大于250ms",
//      label = "Read Interval (ms)",
//      description = "Time KCL should wait between requests per shard. Cannot be set below 200ms. >250ms recommended.",
      displayPosition = 70,
      group = "#0",
      min = 200,
      max = Integer.MAX_VALUE
  )
  public long idleTimeBetweenReads;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "${runtime:availableProcessors()}",
      label = "最大线程数",
      description = "最大处理线程数",
//      label = "Max Threads",
//      description = "Maximum number of record processing threads to spawn",
      displayPosition = 80,
      group = "#0",
      min = 1,
      max = Integer.MAX_VALUE
  )
  public int maxRecordProcessors;
}
