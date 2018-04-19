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
package com.streamsets.pipeline.stage.destination.kinesis;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.DataFormat;
import com.streamsets.pipeline.lib.el.RecordEL;
import com.streamsets.pipeline.stage.destination.lib.DataGeneratorFormatConfig;
import com.streamsets.pipeline.stage.lib.kinesis.KinesisConfigBean;

import java.util.Map;

public class KinesisProducerConfigBean extends KinesisConfigBean {

  @ConfigDefBean(groups = "KINESIS")
  public DataGeneratorFormatConfig dataFormatConfig;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "JSON",
      label = "数据格式",
      description = "从Kinesis读取记录的数据格式",
//      label = "Data Format",
//      description = "Data format to use when receiving records from Kinesis",
      displayPosition = 1,
      group = "DATA_FORMAT"
  )
  @ValueChooserModel(DataFormatChooserValues.class)
  public DataFormat dataFormat;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "RANDOM",
      label = "分区策略",
      description = "生成分区密钥的分区策略",
//      label = "Partitioning Strategy",
//      description = "Partitioning strategy for partition key generation",
      displayPosition = 40,
      group = "#0"
  )
  @ValueChooserModel(PartitionStrategyChooserValues.class)
  public PartitionStrategy partitionStrategy;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      defaultValue = "${0}",
      label = "分区表达式",
      description = "在生成消息时，用于评估分区键的EL表达式.",
//      label = "Partition Expression",
//      description = "EL that is used to evaluate the partitionKey when producing messages.",
      displayPosition = 45,
      group = "#0",
      dependsOn = "partitionStrategy",
      triggeredByValue = "EXPRESSION",
      elDefs = {RecordEL.class},
      evaluation = ConfigDef.Evaluation.EXPLICIT
  )
  public String partitionExpression;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MAP,
      label = "配置属性",
      description = "Kinesis输出端配置属性",
//      label = "Kinesis Producer Configuration",
//      description = "Additional Kinesis Producer properties to set.",
      displayPosition = 50,
      group = "#0"
  )
  public Map<String, String> producerConfigs;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "保存记录顺序",
      defaultValue = "false",
      description = "启用此选项将保证记录按顺序写入流中。但是，启用此选项将禁用批处理，并以牺牲吞吐量为代价。",
//      label = "Preserve Record Order",
//      defaultValue = "false",
//      description = "Enabling this option will guarantee records will be written in-order to the stream. However, " +
//          "enabling this option will disable batching and comes at the expense of throughput.",
      displayPosition = 60,
      group =  "#0"
  )
  public boolean preserveOrdering;
}
