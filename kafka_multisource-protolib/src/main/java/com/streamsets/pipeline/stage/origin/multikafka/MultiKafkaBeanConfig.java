/**
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
package com.streamsets.pipeline.stage.origin.multikafka;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.DataFormat;
import com.streamsets.pipeline.stage.origin.lib.DataParserFormatConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiKafkaBeanConfig {
  @ConfigDefBean(groups = "KAFKA")
  public DataParserFormatConfig dataFormatConfig = new DataParserFormatConfig();

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "数据格式",
      description = "主题(Topic)中数据的格式",
//      label = "Data Format",
//      description = "Format of data in the topic",
      displayPosition = 1,
      group = "DATA_FORMAT"
  )
  @ValueChooserModel(DataFormatChooserValues.class)
  public DataFormat dataFormat;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      defaultValue = "localhost:9092",
      label = "服务端地址",
      description = "以“,”分割的消息服务器连接地址，连接地址格式：<HOST>:<PORT>",
//      label = "Broker URI",
//      description = "Comma-separated list of Kafka brokers. Use format <HOST>:<PORT>",
      displayPosition = 10,
      group = "KAFKA"
  )
  public String brokerURI = "localhost:9092";

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      defaultValue = "streamsetsDataCollector",
      label = "消费者分组",
      description = "消费者分组",
//      label = "Consumer Group",
//      description = "Consumer Group",
      displayPosition = 20,
      group = "KAFKA"
  )
  public String consumerGroup;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.LIST,
      defaultValue = "[\"myTopic\"]",
      label = "主题名称列表",
      description = "消费的订阅主题名称列表",
//      label = "Topic List",
//      description = "List of topics to consume",
      displayPosition = 30,
      group = "KAFKA"
  )
  public List<String> topicList = new ArrayList<>();

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "false",
      label = "生成单条记录",
      description = "为包含多个对象的一条消息生成一条记录",
//      label = "Produce Single Record",
//      description = "Generates a single record for multiple objects within a message",
      displayPosition = 40,
      group = "KAFKA"
  )
  public boolean produceSingleRecordPerMessage;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "1",
          label = "线程数",
          description = "消费全部主题(Topic)的线程数",
//      label = "Number of Threads",
//      description = "Number of threads to allocate for consuming all topics",
      displayPosition = 50,
      group = "KAFKA"
  )
  public int numberOfThreads;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "1000",
      label = "最大记录数",
      description = "每批次中最大的记录数",
//      label = "Max Match Size (records)",
//      description = "Maximum number of records per batch",
      displayPosition = 60,
      group = "KAFKA"
  )
  public int maxBatchSize;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "2000",
      label = "批处理等待时间(ms)",
      description = "发送部分或空的批处理前等待的最大时间",
//      label = "Batch Wait Time (ms)",
//      description = "Max time to wait for data before sending a partial or empty batch",
      displayPosition = 70,
      group = "KAFKA"
  )
  public int batchWaitTime;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MAP,
      defaultValue = "",
      label = "配置参数",
      description = "消费者配置参数",
      displayPosition = 80,
      group = "KAFKA"
  )
  public Map<String, String> kafkaOptions;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "主键反序列化",
      description = "Kafka消息主键的反序列化方式. 若消息中含有Avro数据源ID，请选择Confluent.",//      label = "Key Deserializer",
//      description = "Method used to deserialize the Kafka message key. Set to Confluent when the Avro schema ID is embedded in each message.",
      defaultValue = "STRING",
      dependsOn = "dataFormat",
      triggeredByValue = "AVRO",
      displayPosition = 90,
      group = "KAFKA"
  )
  @ValueChooserModel(KeyDeserializerChooserValues.class)
  public Deserializer keyDeserializer = Deserializer.STRING;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "值反序列化",
      description = "反序列化Kafka消息的方式. 若消息中含有Avro数据源ID，请选择Confluent.",
//      label = "Value Deserializer",
//      description = "Method used to deserialize the Kafka message value. Set to Confluent when the Avro schema ID is embedded in each message.",
      defaultValue = "DEFAULT",
      dependsOn = "dataFormat",
      triggeredByValue = "AVRO",
      displayPosition = 100,
      group = "KAFKA"
  )
  @ValueChooserModel(ValueDeserializerChooserValues.class)
  public Deserializer valueDeserializer = Deserializer.DEFAULT;

  public void init(Stage.Context context, List<Stage.ConfigIssue> issues) {

  }
}
