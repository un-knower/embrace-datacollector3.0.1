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
package com.streamsets.pipeline.stage.origin.jdbc;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ConfigGroups;
import com.streamsets.pipeline.api.ExecutionMode;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.HideConfigs;
import com.streamsets.pipeline.api.Source;
import com.streamsets.pipeline.api.StageDef;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.configurablestage.DSource;
import com.streamsets.pipeline.lib.el.OffsetEL;
import com.streamsets.pipeline.lib.el.TimeEL;
import com.streamsets.pipeline.lib.jdbc.HikariPoolConfigBean;
import com.streamsets.pipeline.lib.jdbc.UnknownTypeAction;
import com.streamsets.pipeline.lib.jdbc.UnknownTypeActionChooserValues;

@StageDef(
    version = 10,
    label = "JDBC查询消费者",
    description = "使用查询从jdbc数据源中读取数据",
    icon = "rdbms.png",
    execution = ExecutionMode.STANDALONE,
    upgrader = JdbcSourceUpgrader.class,
    recordsByRef = true,
    resetOffset = true,
    producesEvents = true,
    onlineHelpRefUrl = "index.html#Origins/JDBCConsumer.html#task_ryz_tkr_bs"
)
@ConfigGroups(value = Groups.class)
@GenerateResourceBundle
@HideConfigs({
    "commonSourceConfigBean.allowLateTable",
    "commonSourceConfigBean.enableSchemaChanges",
    "commonSourceConfigBean.queriesPerSecond"
})
public class JdbcDSource extends DSource {

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "true",
      label = "增量方式",
      description = "增量方式",
      displayPosition = 15,
      group = "JDBC"
  )
  public boolean isIncrementalMode;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.TEXT,
      mode = ConfigDef.Mode.SQL,
      		label = "SQL查询",
      description ="SELECT <offset column>, ... FROM <table name> WHERE <offset column>  >  ${OFFSET} ORDER BY <offset column>",
      elDefs = {OffsetEL.class},
      evaluation = ConfigDef.Evaluation.IMPLICIT,
      displayPosition = 20,
      group = "JDBC"
  )
  public String query;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
    		  label = "初始化偏移量",
    	      description = "为$ {offset}插入的初始值.将使用下一个偏移查询的结果",
      displayPosition = 40,
      group = "JDBC"
  )
  public String initialOffset;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
    	      label = "偏移列",
    	      description = "偏移列",
      displayPosition = 50,
      group = "JDBC"
  )
  public String offsetColumn;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "LIST_MAP",
    	      label = "根字段类型",
      displayPosition = 130,
      group = "JDBC"
  )
  @ValueChooserModel(JdbcRecordTypeChooserValues.class)
  public JdbcRecordType jdbcRecordType;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "${10 * SECONDS}",
    	      label = "查询时间间隔",
      displayPosition = 140,
      elDefs = {TimeEL.class},
      evaluation = ConfigDef.Evaluation.IMPLICIT,
      group = "JDBC"
  )
  public long queryInterval;

  @ConfigDefBean
  public CommonSourceConfigBean commonSourceConfigBean;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
    	      label = "事务id列名",
    	      description = "事务id列名",
      displayPosition = 180,
      group = "CDC"
  )
  public String txnIdColumnName;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
    	      label = "最大事务大小",
    	      description = "最大事务大小",
      defaultValue = "10000",
      displayPosition = 190,
      group = "CDC"
  )
  public int txnMaxSize;

  @ConfigDefBean()
  public HikariPoolConfigBean hikariConfigBean;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
    	      label = "创建JDBC头属性",
    	      description = "创建JDBC头属性",
      defaultValue = "true",
      displayPosition = 200,
      group = "ADVANCED"
  )
  public boolean createJDBCNsHeaders = true;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
    		  label = "JDBC头前缀",
    	      description = "JDBC头前缀: <prefix>.<field name>.<type of information>. 比如: jdbc.<field name>.precision 和 jdbc.<field name>.scale",
      defaultValue = "jdbc.",
      displayPosition = 210,
      group = "ADVANCED",
      dependsOn = "createJDBCNsHeaders",
      triggeredByValue = "true"
  )
  public String jdbcNsHeaderPrefix = "jdbc.";

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.BOOLEAN,
    	      label = "不使用查询验证",
    	      description = "不使用查询验证",
      defaultValue = "false",
      displayPosition = 220,
      group = "ADVANCED"
  )
  public boolean disableValidation = false;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
    	      label = "未知类型处理方式",
    	      description = "在结果集中检测到未知类型时应该执行的操作",
      defaultValue = "STOP_PIPELINE",
      displayPosition = 230,
      group = "ADVANCED"
  )
  @ValueChooserModel(UnknownTypeActionChooserValues.class)
  public UnknownTypeAction unknownTypeAction = UnknownTypeAction.STOP_PIPELINE;

  @Override
  protected Source createSource() {
    return new JdbcSource(
        isIncrementalMode,
        query,
        initialOffset,
        offsetColumn,
        disableValidation,
        txnIdColumnName,
        txnMaxSize,
        jdbcRecordType,
        commonSourceConfigBean,
        createJDBCNsHeaders,
        jdbcNsHeaderPrefix,
        hikariConfigBean,
        unknownTypeAction,
        queryInterval
      );
  }
}
