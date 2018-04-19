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
package com.streamsets.pipeline.stage.origin.sqs;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.DataFormat;
import com.streamsets.pipeline.stage.lib.aws.AWSConfig;
import com.streamsets.pipeline.stage.lib.aws.AWSRegionChooserValues;
import com.streamsets.pipeline.stage.lib.aws.AWSRegions;
import com.streamsets.pipeline.stage.lib.aws.ProxyConfig;
import com.streamsets.pipeline.stage.origin.kinesis.DataFormatChooserValues;
import com.streamsets.pipeline.stage.origin.lib.DataParserFormatConfig;

import java.util.List;

public class SqsConsumerConfigBean {

  @ConfigDefBean(groups = "SQS")
  public AWSConfig awsConfig;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "US_WEST_2",
      label = "域",
//      label = "Region",
      displayPosition = 100,
      group = "SQS"
  )
  @ValueChooserModel(AWSRegionChooserValues.class)
  public AWSRegions region;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      label = "端点",
//      label = "Endpoint",
      description = "",
      defaultValue = "",
      displayPosition = 100,
      dependsOn = "region",
      triggeredByValue = "OTHER",
      group = "SQS"
  )
  public String endpoint;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.LIST,
      label = "队列名前缀",
      description = "提取消息的队列的名称前缀。所有具有这些前缀的名称唯一的队列，将以轮询方式分配给可用线程处理。",
//      label = "Queue Name Prefixes",
//      description = "The name prefixes of queues to fetch messages from. All unique queue names having these prefixes" +
//          " will be assigned to all available threads in a round-robin fashion.",
      displayPosition = 110,
      group = "SQS"
  )
  public List<String> queuePrefixes;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "10",
      label = "请求消息数",
      description = "每个请求应获取的最大消息数",
//      label = "Number of Messages per Request",
//      description = "The max number of messages that should be retrieved per request.",
      displayPosition = 120,
      group = "SQS",
      min = 1
  )
  public int numberOfMessagesPerRequest;

  @ConfigDefBean(groups = "DATA_FORMAT")
  public DataParserFormatConfig dataFormatConfig;

  @ConfigDefBean(groups = "ADVANCED")
  public ProxyConfig proxyConfig = new ProxyConfig();

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "JSON",
      label = "数据格式",
      description = "从SQS中接收记录时的数据格式",
//      label = "Data Format",
//      description = "Data format to use when receiving records from SQS",
      displayPosition = 1,
      group = "DATA_FORMAT"
  )
  @ValueChooserModel(DataFormatChooserValues.class)
  public DataFormat dataFormat;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "1000",
      label = "最大批处理消息个数",
      description = "每批次中消息的最大个数",
//      label = "Max Batch Size (messages)",
//      description = "Max number of records per batch.",
      displayPosition = 160,
      group = "SQS",
      min = 1,
      max = Integer.MAX_VALUE
  )
  public int maxBatchSize;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "1000",
      label = "批处理等待时间(ms)",
      description = "批处理提交前的最大等待时间（毫秒）.",
//      label = "Max Batch Wait Time (ms)",
//      description = "The maximum time a partial batch will remain open before being flushed (milliseconds).",
      displayPosition = 170,
      group = "SQS",
      min = 0,
      max = Integer.MAX_VALUE
  )
  public long maxBatchTimeMs;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "${runtime:availableProcessors()}",
      label = "最大线程数",
      description = "记录处理线程的最大数目。",
//      label = "Max Threads",
//      description = "Maximum number of record processing threads to spawn.",
      displayPosition = 190,
      group = "SQS",
      min = 1,
      max = Integer.MAX_VALUE
  )
  public int numThreads;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "-1",
      label = "轮询等待时间(秒)",
      description = "当返回结果不为空时，轮询等待的最大时间。支持长轮询。设置为-1表示不使用长轮询。",
//      label = "Poll Wait Time (Seconds)",
//      description = "Maximum length of time to wait for messages to arrive when polling before returning" +
//          " an empty response. Provides long polling functionality. Leave as -1 to not set parameter (and" +
//          " thus not use long polling).",
      displayPosition = 190,
      group = "SQS",
      min = -1,
      max = 20
  )
  public int pollWaitTimeSeconds;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "BASIC",
      label = "SQS消息属性等级",
      description = "SQS消息元数据和消息属性的等级",
//      label = "SQS Message Attribute Level",
//      description = "Level of SQS message metadata and attributes to include as SDC record attributes.",
      displayPosition = 200,
      group = "SQS"
  )
  @ValueChooserModel(SqsAttributesOptionChooserValues.class)
  public SqsAttributesOption sqsAttributesOption;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.LIST,
      label = "包含SQS生产端属性",
      description = "需要作为记录头属性的任何生产端属性。",
//      label = "Include SQS Sender Attributes",
//      description = "Required for any sender attributes that you want to include as record header attributes.",
      displayPosition = 210,
      dependsOn = "sqsAttributesOption",
      triggeredByValue = "ALL",
      group = "SQS"
  )
  public List<String> sqsMessageAttributeNames;

}
