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
package com.streamsets.pipeline.stage.processor.spark;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.lib.el.VaultEL;

import java.util.ArrayList;
import java.util.List;

@GenerateResourceBundle
public class SparkProcessorConfigBean {

  public static final String DEFAULT_THREAD_COUNT = "4";
  public static final String DEFAULT_APP_NAME = "SDC Spark App";

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.NUMBER,
      defaultValue = DEFAULT_THREAD_COUNT,
      label = "并发性(仅单机模式)",
      description = "每次批处理创建的分区并发数。群集模式无效",
//      label = "Parallelism (Standalone Mode Only)",
//      description = "Number of partitions to create per batch of records. Ignored in Cluster Mode.",
      group = "SPARK",
      displayPosition = 10
  )
  public int threadCount;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      defaultValue = DEFAULT_APP_NAME,
      label = "应用程序名称(仅单机模式)",
      description = "向Spark提交的应用程序名称. 集群模式下此项无效.",
//      label = "Application Name  (Standalone Mode Only)",
//      description = "Name of the Application submitted to Spark. Ignored in Cluster Mode.",
      group = "SPARK",
      displayPosition = 20
  )
  public String appName;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "Spark转换器类",
      description = "SparkTransformer API的实现类.",
//      label = "Spark Transformer Class",
//      description = "Class that implements SparkTransformer API.",
      group = "SPARK",
      displayPosition = 30
  )
  public String transformerClass;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.LIST,
      label = "初始化方法参数",
      description = "传递给转换器初始化方法的参数。用于外部连接或者从外部系统读取配置或已有数据",
//      label = "Init Method Arguments",
//      description = "Arguments to pass to the init method of the Transformer. " +
//          "Use to make external connections or to read configuration or pre-existing data from external systems.",
      elDefs = VaultEL.class,
      group = "SPARK",
      displayPosition = 40
  )
  public List<String> preprocessMethodArgs = new ArrayList<>();

}
