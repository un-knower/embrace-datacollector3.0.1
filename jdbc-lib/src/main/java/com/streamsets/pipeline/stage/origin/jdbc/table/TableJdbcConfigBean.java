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
package com.streamsets.pipeline.stage.origin.jdbc.table;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ListBeanModel;
import com.streamsets.pipeline.api.PushSource;
import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.TimeZoneChooserValues;
import com.streamsets.pipeline.lib.jdbc.JdbcErrors;
import com.streamsets.pipeline.lib.jdbc.UnknownTypeAction;
import com.streamsets.pipeline.lib.jdbc.UnknownTypeActionChooserValues;
import com.streamsets.pipeline.lib.jdbc.multithread.BatchTableStrategy;
import com.streamsets.pipeline.lib.jdbc.multithread.BatchTableStrategyChooserValues;
import com.streamsets.pipeline.lib.jdbc.multithread.TableOrderStrategy;
import com.streamsets.pipeline.lib.jdbc.multithread.TableOrderStrategyChooserValues;

import java.util.List;

public class TableJdbcConfigBean {
  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "表配置",
      displayPosition  = 20,
      group = "TABLE"
  )
  @ListBeanModel
  public List<TableConfigBean> tableConfigs;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "1",
      label = "线程数量",
      description = "读取数据的并行线程数",
      displayPosition = 80,
      group = "JDBC",
      min = 1
  )
  public int numberOfThreads;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "SWITCH_TABLES",
      label = "每批策略",
      description = "确定每批生成记录的策略",
      displayPosition = 90,
      group = "JDBC"
  )
  @ValueChooserModel(BatchTableStrategyChooserValues.class)
  public BatchTableStrategy batchTableStrategy;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "-1",
      label = "批次的结果集",
      description = "确定在关闭结果集之后可以从所获取的结果集生成的批次的数量.保持- 1，保持结果设置尽可能长",
      min = -1,
      max = Integer.MAX_VALUE,
      displayPosition = 142,
      group = "JDBC",
      dependsOn = "batchTableStrategy",
      triggeredByValue = "SWITCH_TABLES"
  )
  public int numberOfBatchesFromRs;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "-1",
      label = "结果集缓存大小",
      description = "确定可以缓存多少开放语句/结果集.让- 1选择退出，每个表打开一个语句",
      displayPosition = 143,
      group = "JDBC",
      dependsOn = "batchTableStrategy",
      triggeredByValue = "SWITCH_TABLES"
  )
  public int resultCacheSize;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "UTC",
      label = "数据的时区",
      description = "时区用来解析基于时间的表达式",
      displayPosition = 200,
      group = "JDBC"
  )
  @ValueChooserModel(TimeZoneChooserValues.class)
  public String timeZoneID;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "NONE",
      label = "引用字符",
      description = "确定在查询时使用在表/模式/列名上的引号字符",
      displayPosition = 205,
      group = "JDBC"
  )
  @ValueChooserModel(QuoteCharChooserValues.class)
  public QuoteChar quoteChar;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "NONE",
      label = "初始表顺序的策略",
      description = "确定初始表排序的策略",
      displayPosition = 210,
      group = "ADVANCED"
  )
  @ValueChooserModel(TableOrderStrategyChooserValues.class)
  public TableOrderStrategy tableOrderStrategy;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "未知类型处理",
      description = "在检测到未知类型时执行的操作",
      defaultValue = "STOP_PIPELINE",
      displayPosition = 220,
      group = "ADVANCED"
  )
  @ValueChooserModel(UnknownTypeActionChooserValues.class)
  public UnknownTypeAction unknownTypeAction = UnknownTypeAction.STOP_PIPELINE;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "1000",
      label = "取行数量",
      description = "为JDBC语句获取大小.不应该是0",
      displayPosition = 220,
      group = "JDBC"
  )
  public int fetchSize;

  public static final String TABLE_JDBC_CONFIG_BEAN_PREFIX = "tableJdbcConfigBean.";
  public static final String TABLE_CONFIG = TABLE_JDBC_CONFIG_BEAN_PREFIX + "tableConfigs";
  public static final String BATCHES_FROM_THE_RESULT_SET = "numberOfBatchesFromRs";
  public static final String NUMBER_OF_THREADS = "numberOfThreads";
  public static final String QUOTE_CHAR = "quoteChar";

  public List<Stage.ConfigIssue> validateConfigs(PushSource.Context context, List<Stage.ConfigIssue> issues) {
    if (tableConfigs.isEmpty()) {
      issues.add(context.createConfigIssue(Groups.TABLE.name(), TABLE_CONFIG, JdbcErrors.JDBC_66));
    }
    if (batchTableStrategy == BatchTableStrategy.SWITCH_TABLES && numberOfBatchesFromRs == 0) {
      issues.add(
          context.createConfigIssue(
              Groups.JDBC.name(),
              TABLE_JDBC_CONFIG_BEAN_PREFIX +"numberOfBatchesFromRs",
              JdbcErrors.JDBC_76
          )
      );
    }
    return issues;
  }
}
