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
package com.streamsets.pipeline.stage.processor.kudulookup;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ListBeanModel;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.lib.el.RecordEL;
import com.streamsets.pipeline.stage.common.MultipleValuesBehavior;
import com.streamsets.pipeline.stage.lib.kudu.KuduFieldMappingConfig;
import com.streamsets.pipeline.stage.processor.kv.CacheConfig;

import java.util.List;

public class KuduLookupConfig {
  public static final String CONF_PREFIX = "kuduLookupConfig.";

  // kudu tab
  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "Kudu主节点",
      description = "主节点地址列表,以','分割的\"host:port\"",
//      label = "Kudu Masters",
//      description = "Comma-separated list of \"host:port\" pairs of the masters",
      displayPosition = 10,
      group = "KUDU"
  )
  public String kuduMaster;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      elDefs = {RecordEL.class},
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      defaultValue = "${record:attribute('tableName')}",
      label = "Kudu数据表名",
      description = "执行查找的数据表名",
//      label = "Kudu Table Name",
//      description = "Table name to perform lookup",
      displayPosition = 20,
      group = "KUDU"
  )
  public String kuduTableTemplate;

  @ConfigDef(required = true,
      type = ConfigDef.Type.MODEL,
      label = "键映射",
      description = "指定查找键的列. 该映射必须包含主键列",
//      label = "Key Columns Mapping",
//      description = "Specify the columns used as keys for the lookup. The mapping must include a primary key column",
      displayPosition = 30,
      group = "KUDU"
  )
  @ListBeanModel
  public List<KuduFieldMappingConfig> keyColumnMapping;

  @ConfigDef(required = true,
      type = ConfigDef.Type.MODEL,
      label = "输出字段映射",
      description = "列名-数据采集器字段名映射",
//      label = "Column to Output Field Mapping",
//      description = "Map column names to SDC field names",
      displayPosition = 40,
      group = "KUDU"
  )
  @ListBeanModel
  public List<KuduOutputColumnMapping> outputColumnMapping;

  @ConfigDef(required = false,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "false",
      label = "大小写敏感",
      description = "如果未勾选, 表名和所有列名都用小写字母处理",
//      label = "Case Sensitive",
//      description = "If not set, the table name and all column names are processed in lowercase",
      displayPosition = 50,
      group = "KUDU"
  )
  @ListBeanModel
  public boolean caseSensitive;

  @ConfigDef(required = false,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "true",
      label = "忽略缺失值",
      description = "若勾选,即使列值缺失，也可以处理记录。如果未设置，则将记录发送至错误节点",
//      label = "Ignore Missing Value",
//      description = "If set, process records even if column values are missing. If not set, send records to error",
      displayPosition = 60,
      group = "KUDU"
  )
  @ListBeanModel
  public boolean ignoreMissing;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "重复值处理",
      description = "如何处理重复值",
//      label = "Multiple Values Behavior",
//      description = "How to handle multiple values ",
      defaultValue = "FIRST_ONLY",
      displayPosition = 10,
      group = "ADVANCED"
  )
  @ValueChooserModel(KuduLookupMultipleValuesBehaviorChooserValues.class)
  public MultipleValuesBehavior multipleValuesBehavior = MultipleValuesBehavior.DEFAULT;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "10000",
      label = "操作超时事件(ms)",
      description = "设置用户操作（使用会话和扫描器）的默认超时时间",
//      label = "Operation Timeout Milliseconds",
//      description = "Sets the default timeout used for user operations (using sessions and scanners)",
      displayPosition = 30,
      group = "ADVANCED"
  )
  public int operationTimeout;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "true",
      label = "缓存数据表",
      description = "勾选后启用数据表信息缓存,以提高性能,但只适用于表模式不经常变更的场景。",
//      label = "Enable Table Caching",
//      description = "Select to enable caching of table information. This improves performance, " +
//          "but should only be used when the table schema does not change often",
      displayPosition = 10,
      group = "LOOKUP"
  )
  public boolean enableTableCache;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "-1",
      label = "最大缓存表个数",
      description = "缓存表的最大数目。如果超过，最旧的值将被逐出房间。默认值是-1，表示不限制。",
//      label = "Maximum Table Entries to Cache",
//      description = "Maximum number of table entries to cache. If exceeded, oldest values are evicted to make room. " +
//          "Default value is -1 which is unlimited",
      dependsOn = "enableTableCache",
      triggeredByValue = "true",
      displayPosition = 20,
      group = "LOOKUP"
  )
  public int cacheSize = -1;


  @ConfigDefBean(groups = "LOOKUP")
  public CacheConfig cache = new CacheConfig();

}
