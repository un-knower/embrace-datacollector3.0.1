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
package com.streamsets.pipeline.stage.destination.jdbc;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ConfigGroups;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.HideConfigs;
import com.streamsets.pipeline.api.ListBeanModel;
import com.streamsets.pipeline.api.StageDef;
import com.streamsets.pipeline.api.Target;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.configurablestage.DTarget;
import com.streamsets.pipeline.lib.el.RecordEL;
import com.streamsets.pipeline.lib.el.TimeEL;
import com.streamsets.pipeline.lib.el.TimeNowEL;
import com.streamsets.pipeline.lib.operation.ChangeLogFormat;
import com.streamsets.pipeline.lib.jdbc.HikariPoolConfigBean;
import com.streamsets.pipeline.lib.jdbc.JdbcFieldColumnParamMapping;
import com.streamsets.pipeline.lib.jdbc.JDBCOperationType;
import com.streamsets.pipeline.lib.jdbc.JDBCOperationChooserValues;
import com.streamsets.pipeline.lib.operation.ChangeLogFormatChooserValues;
import com.streamsets.pipeline.lib.operation.UnsupportedOperationAction;
import com.streamsets.pipeline.lib.operation.UnsupportedOperationActionChooserValues;

import java.util.List;

@GenerateResourceBundle
@StageDef(
    version = 6,
	label = "JDBC 生产者",
	description = "插入,更新，删除数据到JDBC目的地",   
    upgrader = JdbcTargetUpgrader.class,
    icon = "rdbms.png",
    onlineHelpRefUrl = "index.html#Destinations/JDBCProducer.html#task_cx3_lhh_ht"
)
@ConfigGroups(value = Groups.class)
@HideConfigs(value = {
  "hikariConfigBean.readOnly",
  "hikariConfigBean.autoCommit",
})
public class JdbcDTarget extends DTarget {

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      label = "Schema名称",
      displayPosition = 20,
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
      description = "表名应该只包含表名.模式应该在连接字符串或模式配置中定义",
      displayPosition = 30,
      group = "JDBC"
  )
  public String tableNameTemplate;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "",
      label = "字段列映射",
      description = "当输入字段名和列名不匹配时，可选指定额外的字段映射.",
      displayPosition = 40,
      group = "JDBC"
  )
  @ListBeanModel
  public List<JdbcFieldColumnParamMapping> columnNames;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "附上对象名称",
      description = "用于低级或大小写混合的数据库、表和字段名.只在数据库或表的名称周围有引号时才选择.",
      displayPosition = 40,
      group = "JDBC",
      defaultValue = "false"
  )
  public boolean encloseTableName;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MODEL,
      label = "更改日志格式",
      defaultValue = "NONE",
      description = "如果输入是一个更改数据捕获日志，请指定数据格式.",
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
      description = "在数据采集服务操作中执行默认操作.类型不是在记录头中设置的.",
      displayPosition = 50,
      group = "JDBC"
  )
  @ValueChooserModel(JDBCOperationChooserValues.class)
  public JDBCOperationType defaultOperation;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue= "DISCARD",
      label = "不支持的操作类型",
      description = "不支持操作类型时采取的操作",
      displayPosition = 60,
      group = "JDBC"
  )
  @ValueChooserModel(UnsupportedOperationActionChooserValues.class)
  public UnsupportedOperationAction unsupportedAction;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "false",
      label = "使用多行操作",
      description = "选择生成多行插入和删除.显著提高性能，但并非所有数据库都支持语法.",
      displayPosition = 60,
      group = "JDBC"
  )
  public boolean useMultiRowInsert;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "-1",
      label = "声明参数的限制",
      description = "在使用多行插入时，每个批处理insert语句中允许的准备语句参数的最大数量.设置为- 1以禁用限制.",
      dependsOn = "useMultiRowInsert",
      triggeredByValue = "true",
      displayPosition = 60,
      group = "JDBC"
  )
  public int maxPrepStmtParameters;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "-1",
      label = "每批次最大缓存大小(实体)",
      description = "存储在缓存中的最大准备语句数.只有在使用多行操作复选框时才使用缓存.对于无限数量的条目使用- 1",
      triggeredByValue = "false",
      displayPosition = 60,
      group = "JDBC"
  )
  public int maxPrepStmtCache;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "false",
      label = "批处理错误回滚",
      description = "是否回滚整批错误.一些JDBC驱动程序提供关于单个失败行的信息，并且可以插入部分批次",
      displayPosition = 70,
      group = "JDBC"
  )
  public boolean rollbackOnError;

  @ConfigDefBean()
  public HikariPoolConfigBean hikariConfigBean;

  @Override
  protected Target createTarget() {
    return new JdbcTarget(
        schema,
        tableNameTemplate,
        columnNames, encloseTableName,
        rollbackOnError,
        useMultiRowInsert,
        maxPrepStmtParameters,
        maxPrepStmtCache,
        changeLogFormat,
        defaultOperation,
        unsupportedAction,
        hikariConfigBean
    );
  }
}
