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
package com.streamsets.pipeline.stage.origin.jdbc.cdc.oracle;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.TimeZoneChooserValues;
import com.streamsets.pipeline.lib.el.TimeEL;
import com.streamsets.pipeline.stage.origin.jdbc.cdc.CDCSourceConfigBean;

public class OracleCDCConfigBean {

  @ConfigDefBean
  public CDCSourceConfigBean baseConfigBean = new CDCSourceConfigBean();

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      label = "PDB",
      description = "包含数据库的可插入数据库.如果使用Oracle 12c则需要设置PDB",
//      description = "The pluggable database containing the database. Required for Oracle 12c if PDB is used.",
      displayPosition = 50,
      group = "JDBC"
  )
  public String pdb;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "变更数据起始位置",
      description = "确定读取数据的起始位置",
//      label = "Initial Change",
//      description = "Determines where to start reading",
      displayPosition = 40,
      group = "CDC",
      defaultValue = "LATEST"
  )
  @ValueChooserModel(StartChooserValues.class)
  public StartValues startValue;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "开始日期",
      description = "用于初始更改的日期时间.使用以下格式:dd -MM- yyyy HH24:MM:SS.",
      displayPosition = 50,
      group = "CDC",
      dependsOn = "startValue",
      triggeredByValue = "DATE"
  )
  public String startDate;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "开始SCN",
      description = "系统变更号码用于初始变更",
      displayPosition = 60,
      group = "CDC",
      dependsOn = "startValue",
      triggeredByValue = "SCN"
  )
  public String startSCN;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "Dictionary数据源",
      description = "LogMiner dictionary的位置",
      displayPosition = 70,
      group = "CDC"
  )
  @ValueChooserModel(DictionaryChooserValues.class)
  public DictionaryValues dictionary;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "本地缓冲变化",
      description = "在SDC内存或磁盘上的缓冲区更改.使用此方法可以减少DB上的PGA内存使用量",
      displayPosition = 80,
      group = "CDC",
      defaultValue = "true"
  )
  public boolean bufferLocally;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "缓冲区的位置",
      displayPosition = 90,
      group = "CDC",
      defaultValue = "IN_MEMORY",
      dependsOn = "bufferLocally",
      triggeredByValue = "true"
  )
  @ValueChooserModel(BufferingChooserValues.class)
  public BufferingValues bufferLocation;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "丢弃旧的未提交的事务",
      description = "如果未提交的事务经过事务窗口，则丢弃它们。如果未选中，此类事务将被发送到错误",
      displayPosition = 100,
      group = "CDC",
      dependsOn = "bufferLocally",
      triggeredByValue = "true",
      defaultValue = "false"
  )
  public boolean discardExpired;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "不支持的字段类型",
      description = "如果遇到不支持的字段类型，则采取行动.当在本地缓存时，当读取记录不等待提交时立即触发该操作",
      displayPosition = 110,
      group = "CDC",
      defaultValue = "TO_ERROR"
  )
  @ValueChooserModel(UnsupportedFieldTypeChooserValues.class)
  public UnsupportedFieldTypeValues unsupportedFieldOp;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "将不支持的字段添加到记录",
      description = "将不支持的字段的值作为未解析字符串添加到记录",
      displayPosition = 115,
      group = "CDC",
      defaultValue = "false"
  )
  public boolean sendUnsupportedFields;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "将不支持的字段添加到记录",
      description = "将不支持的字段的值作为未解析字符串添加到记录",
      displayPosition = 120,
      group = "CDC",
      defaultValue = "false"
  )
  public boolean allowNulls;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      label = "最大事务长度",
      description = "在提交(秒)前查看事务内更改的时间窗口",
      displayPosition = 130,
      group = "CDC",
      elDefs = TimeEL.class,
      defaultValue = "${1 * HOURS}"
  )
  public long txnWindow;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      label = "LogMiner会话窗口",
      description = "时间窗口，一个LogMiner会话应该保持开放.必须大于或等于最大事务长度.保持这个小的可以减少Oracle上的内存使用.",
      displayPosition = 140,
      group = "CDC",
      elDefs = TimeEL.class,
      defaultValue = "${2 * HOURS}"
  )
  public long logminerWindow;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      label = "查询超时",
      description = "在超时一个LogMiner查询和返回批处理之前等待时间.",
      displayPosition = 140,
      group = "CDC",
      elDefs = TimeEL.class,
      defaultValue = "${5 * MINUTES}"
  )
  public int queryTimeout;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      label = "JDBC取行数",
      description = "为了减少延迟，如果表的写速率很低，则设置这个值.",
      displayPosition = 145,
      group = "CDC",
      min = 1,
      defaultValue = "1"
  )
  public int jdbcFetchSize;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "发送重新查询",
      description = "发送日志记录头返回的实际重做查询",
      displayPosition = 150,
      group = "CDC",
      defaultValue = "false"
  )
  public boolean keepOriginalQuery;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "数据库时区",
      description = "数据库运行的时区",
      displayPosition = 160,
      group = "CDC"
  )
  @ValueChooserModel(TimeZoneChooserValues.class)
  public String dbTimeZone;

}
