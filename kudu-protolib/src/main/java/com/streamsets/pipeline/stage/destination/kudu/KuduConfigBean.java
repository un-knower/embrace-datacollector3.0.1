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
package com.streamsets.pipeline.stage.destination.kudu;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ListBeanModel;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.lib.el.RecordEL;
import com.streamsets.pipeline.lib.el.TimeEL;
import com.streamsets.pipeline.lib.el.TimeNowEL;
import com.streamsets.pipeline.lib.operation.ChangeLogFormat;
import com.streamsets.pipeline.lib.operation.ChangeLogFormatChooserValues;
import com.streamsets.pipeline.lib.operation.UnsupportedOperationAction;
import com.streamsets.pipeline.lib.operation.UnsupportedOperationActionChooserValues;
import com.streamsets.pipeline.stage.lib.kudu.KuduFieldMappingConfig;

import java.util.List;

public class KuduConfigBean {

  public static final String CONF_PREFIX = "kuduConfigBean.";

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
      elDefs = {RecordEL.class, TimeEL.class, TimeNowEL.class},
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      defaultValue = "${record:attribute('tableName')}",
      label = "数据表名",
      description = "写入的Kudu数据表名. 如果数据表不存在, 记录将视为错误记录.",
//      label = "Table Name",
//      description = "Kudu table to write to. If table doesn't exist, records will be treated as error records.",
      displayPosition = 20,
      group = "KUDU"
  )
  public String tableNameTemplate;

  @ConfigDef(required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "",
      label = "字段-列映射",
      description = "当输入字段名和列名不匹配时，可在此设置字段映射.",
//      label = "Field to Column Mapping",
//      description = "Optionally specify additional field mappings when input field name and column name don't match.",
      displayPosition = 30,
      group = "KUDU"
  )
  @ListBeanModel
  public List<KuduFieldMappingConfig> fieldMappingConfigs;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "",
      label = "默认操作",
      description = "记录头信息未设置sdc.operation.type属性时的默认操作.",
//      label = "Default Operation",
//      description = "Default operation to perform if sdc.operation.type is not set in record header.",
      displayPosition = 40,
      group = "KUDU"
  )
  @ValueChooserModel(KuduOperationChooserValues.class)
  public KuduOperationType defaultOperation;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MODEL,
      label = "更改日志格式",
      defaultValue = "NONE",
      description = "如果输入是数据更改捕获日志，请指定格式",
//      label = "Change Log Format",
//      defaultValue = "NONE",
//      description = "If input is a change data capture log, specify the format.",
      displayPosition = 40,
      group = "KUDU"
  )
  @ValueChooserModel(ChangeLogFormatChooserValues.class)
  public ChangeLogFormat changeLogFormat = ChangeLogFormat.NONE;

  // advanced tab
  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "CLIENT_PROPAGATED",
      label = "外部一致性",
      description = "外部一致性方式",
//      label = "External Consistency",
//      description = "The external consistency mode",
      displayPosition = 10,
      group = "ADVANCED"
  )
  @ValueChooserModel(ConsistencyModeChooserValues.class)
  public ConsistencyMode consistencyMode;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      label = "缓冲区大小(记录数)",
      description = "设置Kudu客户端每次批处理的缓冲区大小. 应大于等于向任务传递的记录数.",
//      label = "Mutation Buffer Space (records)",
//      description = "Sets the buffer size that Kudu client uses for a single batch. Should be greater than or" +
//        " equal to the number of records in the batch passed from the pipeline.",
      defaultValue = "1000",
      displayPosition = 15,
      group = "ADVANCED"
  )
  public int mutationBufferSpace;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "10000",
      label = "操作超时时间(ms)",
      description = "设置用户操作（使用会话和扫描器）的默认超时时间",
//      label = "Operation Timeout Milliseconds",
//      description = "Sets the default timeout used for user operations (using sessions and scanners)",
      displayPosition = 20,
      group = "ADVANCED"
  )
  public int operationTimeout;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue= "DISCARD",
      label = "操作不支持时",
      description = "当操作类型不支持的执行的处理",
//      label = "Unsupported Operation Handling",
//      description = "Action to take when operation type is not supported",
      displayPosition = 30,
      group = "ADVANCED"
  )
  @ValueChooserModel(UnsupportedOperationActionChooserValues.class)
  public UnsupportedOperationAction unsupportedAction;
}
