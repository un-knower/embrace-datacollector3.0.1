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
package com.streamsets.pipeline.stage.destination.hive;

import com.streamsets.pipeline.api.ListBeanModel;
import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigGroups;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.StageDef;
import com.streamsets.pipeline.api.Target;
import com.streamsets.pipeline.configurablestage.DTarget;

import java.util.List;
import java.util.Map;

@StageDef(
    version = 1,
    label = "Hive流存储",
    description = "使用streaming API将数据写入Hive数据表，需要ORC存储格式",
//    label = "Hive Streaming",
//    description = "Writes data to Hive tables using the streaming API. Requires ORC storage format.",
    icon = "hive.png",
    privateClassLoader = true,
    onlineHelpRefUrl = "index.html#Destinations/Hive.html#task_cx3_lhh_ht"
)
@ConfigGroups(value = Groups.class)
@GenerateResourceBundle
public class HiveDTarget extends DTarget {

  @ConfigDef(
      required = true,
      label = "Hive元数据Thrift地址",
//      label = "Hive Metastore Thrift URL",
      type = ConfigDef.Type.STRING,
      description = "Hive元数据Thrift连接地址，格式：thrift://<host>:<port>",
//      description = "Hive Metastore Thrift URL in the form: thrift://<host>:<port>",
      displayPosition = 10,
      group = "HIVE"
  )
  public String hiveUrl;

  @ConfigDef(
      required = true,
      label = "模式",
//      label = "Schema",
      type = ConfigDef.Type.STRING,
      defaultValue = "default",
      description = "Hive数据表所在模式/数据库",
//      description = "The Hive schema of the target table. Sometimes also called \"database\".",
      displayPosition = 20,
      group = "HIVE"
  )
  public String schema;

  @ConfigDef(
      required = true,
      label = "数据表",
//      label = "Table",
      type = ConfigDef.Type.STRING,
      displayPosition = 30,
      group = "HIVE"
  )
  public String table;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      defaultValue = "/etc/hive/conf",
      label = "Hive配置文件目录",
      description = "配置文件core-site.xml和hive-site.xml所在的绝对路径或SDC资源目录下的相对目录",
//      label = "Hive Configuration Directory",
//      description = "An absolute path or a directory under SDC resources directory to load core-site.xml and" +
//          " hive-site.xml files to configure the Hive.",
      displayPosition = 40,
      group = "HIVE"
  )
  public String hiveConfDir;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue="",
      label = "字段-列映射",
      description = "当字段名与列名不一致时，在此配置映射关系.",
//      label = "Field to Column Mapping",
//      description = "Use to specify additional field mappings when input field name and column name don't match.",
      displayPosition = 50,
      group = "HIVE"
  )
  @ListBeanModel
  public List<FieldMappingConfig> columnMappings;

  @ConfigDef(
      required = true,
      label = "创建分区",
//      label = "Create Partitions",
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "true",
      description = "若分区不存在则自动创建",
//      description = "Automatically create partitions if they do not exist.",
      displayPosition = 60,
      group = "HIVE"
  )
  public boolean autoCreatePartitions;

  @ConfigDef(
      required = true,
      label = "批处理事务数",
//      label = "Transaction Batch Size",
      type = ConfigDef.Type.NUMBER,
      description = "每个分区批处理请求的事务数。",
//      description = "Number of transactions to request per partition batch.",
      defaultValue = "1000",
      min = 2,
      displayPosition = 70,
      group = "ADVANCED"
  )
  public int txnBatchSize;

  @ConfigDef(
      required = true,
      label = "最大记录大小(KB)",
//      label = "Max Record Size (KB)",
      type = ConfigDef.Type.NUMBER,
      description = "超限的记录将被发送至错误处理节点",
//      description = "Larger records are sent to error.",
      defaultValue = "128",
      min = 1,
      displayPosition = 80,
      group = "ADVANCED"
  )
  public int bufferLimitKb;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MAP,
      label = "Hive配置属性",
      description = "Hive配置属性。此处配置将覆盖从配置文件中加载的配置。",
//      label = "Hive Configuration",
//      description = "Additional configuration properties. Values here override values loaded from config files.",
      displayPosition = 90,
      group = "ADVANCED"
  )
  public Map<String, String> additionalHiveProperties;

  @Override
  protected Target createTarget() {
    return new HiveTarget(
        hiveUrl,
        schema,
        table,
        hiveConfDir,
        columnMappings,
        autoCreatePartitions,
        txnBatchSize,
        bufferLimitKb,
        additionalHiveProperties
    );
  }
}
