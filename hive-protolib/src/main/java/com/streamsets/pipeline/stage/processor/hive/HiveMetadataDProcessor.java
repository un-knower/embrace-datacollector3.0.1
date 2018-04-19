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

import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ConfigGroups;
import com.streamsets.pipeline.api.Processor;
import com.streamsets.pipeline.api.StageDef;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.TimeZoneChooserValues;
import com.streamsets.pipeline.configurablestage.DProcessor;
import com.streamsets.pipeline.api.ListBeanModel;
import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.lib.el.RecordEL;
import com.streamsets.pipeline.lib.el.TimeEL;
import com.streamsets.pipeline.lib.el.TimeNowEL;
import com.streamsets.pipeline.stage.lib.hive.FieldPathEL;
import com.streamsets.pipeline.stage.lib.hive.HiveConfigBean;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@StageDef(
    version = 2,
    label="Hive元数据处理器",
    description = "生成、写入Hive元信息",
//    label="Hive Metadata",
//    description = "Generates Hive metadata and write information for HDFS",
    icon="metadata.png",
    outputStreams = HiveMetadataOutputStreams.class,
    privateClassLoader = true,
    onlineHelpRefUrl = "index.html#Processors/HiveMetadata.html#task_hpg_pft_zv",
    upgrader = HiveMetadataProcessorUpgrader.class
)

@ConfigGroups(Groups.class)
public class HiveMetadataDProcessor extends DProcessor {

  @ConfigDefBean
  public HiveConfigBean hiveConfigBean;

  @ConfigDef(
      required = false,
      label = "数据库表达式",
//      label = "Database Expression",
      type = ConfigDef.Type.STRING,
      defaultValue = "${record:attribute('database')}",
      description = "通过表达式从记录中获取数据库名，默认为\"default\"",
//      description = "Use an expression language to obtain database name from record. If not set, \"default\" will be applied",
      displayPosition = 10,
      group = "TABLE",
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      elDefs = {RecordEL.class}
  )
  public String dbNameEL;

  @ConfigDef(
      required = true,
      label = "表名",
//      label = "Table Name",
      type = ConfigDef.Type.STRING,
      defaultValue = "${record:attribute('table_name')}",
      description = "使用表达式从记录中获取表名。注意，在Hive建表时会将名称更改为小写",
//      description = "Use an expression to obtain the table name from the record. Note that Hive changes the name to lowercase when creating a table.",
      displayPosition = 20,
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      elDefs = {RecordEL.class},
      group = "TABLE"
  )
  public String tableNameEL;

  @ConfigDef(
      required = true,
      label = "分区配置",
//      label = "Partition Configuration",
      type = ConfigDef.Type.MODEL,
      defaultValue = "",
      description = "分区信息，通常用于创建查询中的分区子句。",
//      description = "Partition information, often used in PARTITION BY clause in CREATE query.",
      displayPosition = 30,
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      elDefs = {RecordEL.class},
      group = "TABLE"
  )
  @ListBeanModel
  public List<PartitionConfig> partitionList;

  @ConfigDef(
      required = true,
//      label = "External Table",
      label = "写入外部表",
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "false",
      description = "是否将数据写入外部表，" +
              "若勾选，Hive存储不使用默认位置，" +
              "否则，则使用默认配置(在hive-site.xml中hive.metastore.warehouse.dir配置)",
//      description = "Will data be stored in external table? If checked, Hive will not use the default location. " +
//              "Otherwise, Hive will use the default location at hive.metastore.warehouse.dir in hive-site.xml",
      displayPosition = 40,
      group = "TABLE"
  )
  public boolean externalTable;

  /* Only when internal checkbox is set to NO */
  @ConfigDef(
      required = false,
      label = "表路径模板",
//      label = "Table Path Template",
      type = ConfigDef.Type.STRING,
      defaultValue = "/user/hive/warehouse/${record:attribute('database')}.db/${record:attribute('table_name')}",
      description = "表路径表达式",
//      description = "Expression for table path",
      displayPosition = 50,
      group = "TABLE",
      dependsOn = "externalTable",
      triggeredByValue = "true",
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      elDefs = {RecordEL.class}
  )
  public String tablePathTemplate;

  @ConfigDef(
      required = false,
      label = "分区路径模板",
//      label = "Partition Path Template",
      type = ConfigDef.Type.STRING,
      defaultValue = "dt=${record:attribute('dt')}",
      description = "分区路径模板",
//      description = "Expression for partition path",
      displayPosition = 60,
      group = "TABLE",
      dependsOn = "externalTable",
      triggeredByValue = "true",
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      elDefs = {RecordEL.class, TimeEL.class, TimeNowEL.class}
  )
  public String partitionPathTemplate;

  @ConfigDef(
      required = false,
      label = "列注释",
//      label = "Column comment",
      type = ConfigDef.Type.STRING,
      defaultValue = "",
      description = "用于评估列注释的表达式",
//      description = "Expression that will evaluate to column comment.",
      displayPosition = 70,
      group = "TABLE",
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      elDefs = {RecordEL.class, TimeEL.class, TimeNowEL.class, FieldPathEL.class}
  )
  public String commentExpression;

  @ConfigDefBean
  public DecimalDefaultsConfig decimalDefaultsConfig;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      defaultValue = "${time:now()}",
      label = "时间基准",
      description = "记录的时间基准表达式. 1 当前时间：${time:now()}；2 属性值：'${record:value(\"<filepath>\")}'.",
//      label = "Time Basis",
//      description = "Time basis to use for a record. Enter an expression that evaluates to a datetime. To use the " +
//          "processing time, enter ${time:now()}. To use field values, use '${record:value(\"<filepath>\")}'.",
      displayPosition = 100,
      group = "ADVANCED",
      elDefs = {RecordEL.class, TimeEL.class, TimeNowEL.class},
      evaluation = ConfigDef.Evaluation.EXPLICIT
  )
  public String timeDriver;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "UTC",
      label = "时区",
      description = "用于记录的时区.",
//      label = "Data Time Zone",
//      description = "Time zone to use for a record.",
      displayPosition = 110,
      group = "ADVANCED"
  )
  @ValueChooserModel(TimeZoneChooserValues.class)
  public String timeZoneID;

  @ConfigDef(
      required = false,
      defaultValue = "{}",
      type = ConfigDef.Type.MAP,
      label = "头属性表达式",
      description = "写入到元数据记录输出中的头属性",
//      label = "Header Attribute Expressions",
//      description = "Header attributes to insert into the metadata record output",
      displayPosition = 120,
      elDefs = {RecordEL.class, TimeEL.class, TimeNowEL.class},
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      group = "ADVANCED"
  )
  @ListBeanModel
  public Map<String, String> metadataHeaderAttributeConfigs;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "数据格式",
//      label = "Data Format",
      displayPosition = 10,
      group = "DATA_FORMAT"
  )
  @ValueChooserModel(HMPDataFormatChooserValues.class)
  public HMPDataFormat dataFormat = HMPDataFormat.AVRO;

  @Override
  protected Processor createProcessor() {
    return new HiveMetadataProcessor(
      dbNameEL,
      tableNameEL,
      partitionList,
      externalTable,
      tablePathTemplate,
      partitionPathTemplate,
      hiveConfigBean,
      timeDriver,
      decimalDefaultsConfig,
      TimeZone.getTimeZone(ZoneId.of(timeZoneID)),
      dataFormat,
      commentExpression,
      metadataHeaderAttributeConfigs
    );
  }

}
