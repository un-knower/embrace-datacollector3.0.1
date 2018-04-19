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
package com.streamsets.pipeline.stage.processor.jdbclookup;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ConfigGroups;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.ListBeanModel;
import com.streamsets.pipeline.api.Processor;
import com.streamsets.pipeline.api.StageDef;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.configurablestage.DProcessor;
import com.streamsets.pipeline.lib.el.RecordEL;
import com.streamsets.pipeline.lib.el.StringEL;
import com.streamsets.pipeline.lib.jdbc.HikariPoolConfigBean;
import com.streamsets.pipeline.lib.jdbc.JdbcFieldColumnMapping;
import com.streamsets.pipeline.stage.common.MultipleValuesBehavior;
import com.streamsets.pipeline.stage.destination.jdbc.Groups;
import com.streamsets.pipeline.stage.processor.kv.CacheConfig;

import java.util.List;

@StageDef(
    version = 2,
    label = "JDBC查找处理器",
    description = "通过JDBC查找值来充实记录",	
    icon = "rdbms.png",
    upgrader = JdbcLookupProcessorUpgrader.class,
    onlineHelpRefUrl = "index.html#Processors/JDBCLookup.html#task_kbr_2cy_hw"
)
@ConfigGroups(Groups.class)
@GenerateResourceBundle
public class JdbcLookupDProcessor extends DProcessor {

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.TEXT,
      mode = ConfigDef.Mode.SQL,
    	      label = "SQL查询",
      description = "SELECT <column>, ... FROM <table name> WHERE <column> <operator>  <expression>",
      elDefs = {StringEL.class, RecordEL.class},
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      displayPosition = 20,
      group = "JDBC"
  )
  public String query;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
	      label = "列映射",
	      defaultValue = "",
	      description = "从列名到字段名的映射",
      displayPosition = 30,
      group = "JDBC"
  )
  @ListBeanModel
  public List<JdbcFieldColumnMapping> columnMappings;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
	      label = "多个值的行为",
	      description = "如何处理多个值",
      defaultValue = "FIRST_ONLY",
      displayPosition = 35,
      group = "JDBC"
  )
  @ValueChooserModel(JdbcLookupMultipleValuesBehaviorChooserValues.class)
  public MultipleValuesBehavior multipleValuesBehavior = MultipleValuesBehavior.DEFAULT;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "1000",
      label = "最大Clob大小(字符)",
      displayPosition = 40,
      group = "JDBC"
  )
  public int maxClobSize;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "1000",
    	      label = "最大Blob大小(字节)",
      displayPosition = 50,
      group = "JDBC"
  )
  public int maxBlobSize;

  @ConfigDefBean()
  public HikariPoolConfigBean hikariConfigBean;

  @ConfigDefBean(groups = "JDBC")
  public CacheConfig cacheConfig = new CacheConfig();

  @Override
  protected Processor createProcessor() {
    return new JdbcLookupProcessor(
      query,
      columnMappings,
      multipleValuesBehavior,
      maxClobSize,
      maxBlobSize,
      hikariConfigBean,
      cacheConfig
    );
  }
}
