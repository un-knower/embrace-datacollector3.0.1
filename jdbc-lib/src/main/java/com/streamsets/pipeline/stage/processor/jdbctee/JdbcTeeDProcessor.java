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
package com.streamsets.pipeline.stage.processor.jdbctee;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ConfigGroups;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.HideConfigs;
import com.streamsets.pipeline.api.ListBeanModel;
import com.streamsets.pipeline.api.Processor;
import com.streamsets.pipeline.api.StageDef;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.configurablestage.DProcessor;
import com.streamsets.pipeline.lib.el.RecordEL;
import com.streamsets.pipeline.lib.el.TimeEL;
import com.streamsets.pipeline.lib.el.TimeNowEL;
import com.streamsets.pipeline.lib.jdbc.HikariPoolConfigBean;
import com.streamsets.pipeline.lib.jdbc.JdbcFieldColumnParamMapping;
import com.streamsets.pipeline.lib.jdbc.JDBCOperationChooserValues;
import com.streamsets.pipeline.lib.operation.ChangeLogFormat;
import com.streamsets.pipeline.lib.jdbc.JdbcFieldColumnMapping;
import com.streamsets.pipeline.lib.jdbc.JDBCOperationType;
import com.streamsets.pipeline.lib.operation.UnsupportedOperationAction;
import com.streamsets.pipeline.lib.operation.UnsupportedOperationActionChooserValues;
import com.streamsets.pipeline.lib.operation.ChangeLogFormatChooserValues;
import com.streamsets.pipeline.stage.destination.jdbc.Groups;
import java.util.List;

@StageDef(
    version = 2,
    label = "JDBC连接器",
    description = "将记录写入JDBC，并使用生成的列来充实记录",
    upgrader = JdbcTeeUpgrader.class,
    icon = "rdbms.png",
    onlineHelpRefUrl = "index.html#Processors/JDBCTee.html#task_qpj_ncy_hw"
)
@ConfigGroups(Groups.class)
@GenerateResourceBundle
@HideConfigs(value = {
  "hikariConfigBean.readOnly",
  "hikariConfigBean.autoCommit"
})
public class JdbcTeeDProcessor extends DProcessor {
  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
    	      label = "Schema名称",
      displayPosition = 10,
      group = "JDBC"
  )
  public String schema;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      elDefs = {RecordEL.class, TimeEL.class, TimeNowEL.class},
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      defaultValue = "${record:attribute('tableName')}",
    	      label = "表名",
    	      description = "表名应该只包含表名.模式应该在连接字符串或schema配置中定义",
      displayPosition = 20,
      group = "JDBC"
  )
  public String tableNameTemplate;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "",
    	      label = "字段列映射",
    	      description = "当输入字段名和列名不匹配时，可选指定额外的字段映射",
      displayPosition = 30,
      group = "JDBC"
  )
  @ListBeanModel
  public List<JdbcFieldColumnParamMapping> customMappings;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
    	      label = "生成的列映射",
    	      defaultValue = "",
    	      description = "从生成的列映射到字段名",
      displayPosition = 40,
      group = "JDBC"
  )
  @ListBeanModel
  public List<JdbcFieldColumnMapping> generatedColumnMappings;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "锁定表名",
      description = "勾选后，会在数据库名、表名前后附加引号，适用于数据库名、表名和字段名为小写或大小写混合的情况.",
//      label = "Enclose Table Name",
//      description = "Use for lower or mixed-case database, table and field names. " +
//              "Select only when the database or tables were created with quotation marks around the names.",
      displayPosition = 40,
      group = "JDBC",
      defaultValue = "false"
  )
  public boolean encloseTableName;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MODEL,
      label = "指定日志格式",
      defaultValue = "NONE",
      description = "当数据库变更日志作为输入数据时，请指定日志格式",
//      label = "Change Log Format",
//      defaultValue = "NONE",
//      description = "If input is a change data capture log, specify the format.",
      displayPosition = 40,
      group = "JDBC"
  )
  @ValueChooserModel(ChangeLogFormatChooserValues.class)
  public ChangeLogFormat changeLogFormat;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "",
      label = "默认操作",
      description = "记录头信息中未设置sdc操作类型时的默认操作.",
//      label = "Default Operation",
//      description = "Default operation to perform if sdc.operation.type is not set in record header.",
      displayPosition = 40,
      group = "JDBC"
  )
  @ValueChooserModel(JDBCOperationChooserValues.class)
  public JDBCOperationType defaultOperation;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue= "DISCARD",
      label = "操作类型不支持时",
      description = "对不支持的操作类型所采取的处理方式",
//      label = "Unsupported Operation Handling",
//      description = "Action to take when operation type is not supported",
      displayPosition = 50,
      group = "JDBC"
  )
  @ValueChooserModel(UnsupportedOperationActionChooserValues.class)
  public UnsupportedOperationAction unsupportedAction;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "true",
      label = "启用多行操作",
      description = "是否生成多行插入语句而不是单行插入",
//      label = "Use Multi-Row Operation",
//      description = "Whether to generate multi-row INSERT statements instead of batches of single-row INSERTs",
      displayPosition = 60,
      group = "JDBC"
  )
  public boolean useMultiRowOp;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "-1",
      label = "参数个数限制",
      description = "批处理插入多行时允许的声明参数最大个数，-1表示不限制",
//      label = "Statement Parameter Limit",
//      description = "The maximum number of prepared statement parameters allowed in each batch insert statement when " +
//              "" + "using multi-row inserts. Set to -1 to disable limit.",
      dependsOn = "useMultiRowOp",
      triggeredByValue = "true",
      displayPosition = 60,
      group = "JDBC"
  )
  public int maxPrepStmtParameters = -1;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "-1",
      label = "每批次的最大缓存大小（条目数）",
      description = "缓存中保留的最大预处理语句数。当“启用多行操作”被勾选时缓存生效。-1表示不限制条数",
//      label = "Max Cache Size Per Batch (Entries)",
//      description = "The maximum number of prepared statement stored in cache. Cache is used only when " +
//              "'Use Multi-Row Operation' checkbox is unchecked. Use -1 for unlimited number of entries.",
      dependsOn = "useMultiRowOp",
      triggeredByValue = "false",
      displayPosition = 60,
      group = "JDBC"
  )
  public int maxPrepStmtCache = -1;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "false",
      label = "整批回滚",
      description = "当发生异常时，是否回滚批处理中所有行操作.某些JDBC驱动程序支持部分提交，并提供单个失败行信息",
//      label = "Rollback Batch on Error",
//      description = "Whether or not to rollback the entire batch on error. Some JDBC drivers provide information" +
//              "about individual failed rows, and can insert partial batches.",
      displayPosition = 70,
      group = "JDBC"
  )
  public boolean rollbackOnError;

  @ConfigDefBean()
  public HikariPoolConfigBean hikariConfigBean;

  @Override
  protected Processor createProcessor() {
    return new JdbcTeeProcessor(
        schema,
        tableNameTemplate,
        customMappings,
        generatedColumnMappings, encloseTableName,
        rollbackOnError,
        useMultiRowOp,
        maxPrepStmtParameters,
        maxPrepStmtCache,
        changeLogFormat,
        hikariConfigBean,
        defaultOperation,
        unsupportedAction
    );
  }
}
