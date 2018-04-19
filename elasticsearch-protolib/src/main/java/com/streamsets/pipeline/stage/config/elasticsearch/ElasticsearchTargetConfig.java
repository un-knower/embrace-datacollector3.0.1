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
package com.streamsets.pipeline.stage.config.elasticsearch;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.CharsetChooserValues;
import com.streamsets.pipeline.config.TimeZoneChooserValues;
import com.streamsets.pipeline.lib.el.DataUtilEL;
import com.streamsets.pipeline.lib.el.RecordEL;
import com.streamsets.pipeline.lib.el.TimeEL;
import com.streamsets.pipeline.lib.el.TimeNowEL;
import com.streamsets.pipeline.stage.destination.elasticsearch.ElasticsearchOperationChooserValues;
import com.streamsets.pipeline.stage.destination.elasticsearch.ElasticsearchOperationType;
import com.streamsets.pipeline.stage.destination.elasticsearch.UnsupportedOperationAction;
import com.streamsets.pipeline.stage.destination.elasticsearch.UnsupportedOperationActionChooserValues;

public class ElasticsearchTargetConfig extends ElasticsearchConfig {

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      defaultValue = "${time:now()}",
      label = "时间基准",
      description = "记录使用的时间基准，请输入评估时间表达式。" +
              "当前时间表达式：${time:now()}，记录值表达式：'${record:value(\"<field path>\")}'",
//      label = "Time Basis",
//      description = "Time basis to use for a record. Enter an expression that evaluates to a datetime. To use the " +
//          "processing time, enter ${time:now()}. To use field values, use '${record:value(\"<field path>\")}'.",
      displayPosition = 40,
      group = "ELASTIC_SEARCH",
      elDefs = {RecordEL.class, TimeNowEL.class},
      evaluation = ConfigDef.Evaluation.EXPLICIT
  )
  public String timeDriver;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "UTC",
      label = "时区",
      description = "用于计算时区相关的索引的时间",
//      label = "Data Time Zone",
//      description = "Time zone to use to resolve the datetime of a time based index",
      displayPosition = 50,
      group = "ELASTIC_SEARCH"
  )
  @ValueChooserModel(TimeZoneChooserValues.class)
  public String timeZoneID;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "索引",
      description = "",
      displayPosition = 60,
      group = "ELASTIC_SEARCH",
      elDefs = {RecordEL.class, TimeEL.class, TimeNowEL.class},
      evaluation = ConfigDef.Evaluation.EXPLICIT
  )
  public String indexTemplate;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "映射",
      description = "",
      displayPosition = 70,
      group = "ELASTIC_SEARCH",
      elDefs = {RecordEL.class, TimeNowEL.class},
      evaluation = ConfigDef.Evaluation.EXPLICIT
  )
  public String typeTemplate;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      label = "文档ID",
      description = "用于创建/更新/删除所需的文档ID的表达式，可选项，留空则使用自动生成的ID。",
//      label = "Document ID",
//      description = "An expression which evaluates to a document ID. Required for create/update/delete. " +
//          "Optional for index, leave blank to use auto-generated IDs.",
      displayPosition = 80,
      group = "ELASTIC_SEARCH",
      elDefs = {RecordEL.class, DataUtilEL.class},
      evaluation = ConfigDef.Evaluation.EXPLICIT
  )
  public String docIdTemplate;

  @ConfigDef(
          required = false,
          type = ConfigDef.Type.STRING,
          label = "父ID",
          description = "在父/子层次结构中计算父文档ID的表达式",
//          label = "Parent ID",
//          description = "An expression which evaluates to a document ID for the parent in a parent/child hierarchy.",
          displayPosition = 85,
          group = "ELASTIC_SEARCH",
          elDefs = {RecordEL.class, DataUtilEL.class},
          evaluation = ConfigDef.Evaluation.EXPLICIT
  )
  public String parentIdTemplate;

  @ConfigDef(
          required = false,
          type = ConfigDef.Type.STRING,
          label = "路由",
          description = "用于确定文档ID所编入的索引分片的表达式",
//          label = "Routing",
//          description = "An expression which evaluates to a document ID whose shard will be indexed on.",
          displayPosition = 87,
          group = "ELASTIC_SEARCH",
          elDefs = {RecordEL.class, DataUtilEL.class},
          evaluation = ConfigDef.Evaluation.EXPLICIT
  )
  public String routingTemplate;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "UTF-8",
      label = "数据字符集",
//      label = "Data Charset",
      displayPosition = 90,
      group = "ELASTIC_SEARCH"
  )
  @ValueChooserModel(CharsetChooserValues.class)
  public String charset;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "INDEX",
      label = "默认操作",
      description = "如果记录头中未设置sdc.operation.type时执行的默认操作",
//      label = "Default Operation",
//      description = "Default operation to perform if sdc.operation.type is not set in record header.",
      displayPosition = 100,
      group = "ELASTIC_SEARCH"
  )
  @ValueChooserModel(ElasticsearchOperationChooserValues.class)
  public ElasticsearchOperationType defaultOperation = ElasticsearchOperationType.INDEX;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue= "DISCARD",
      label = "操作不支持时",
      description = "操作类型不支持时采取的操作",
//      label = "Unsupported Operation Handling",
//      description = "Action to take when operation type is not supported",
      displayPosition = 110,
      group = "ELASTIC_SEARCH"
  )
  @ValueChooserModel(UnsupportedOperationActionChooserValues.class)
  public UnsupportedOperationAction unsupportedAction = UnsupportedOperationAction.DISCARD;
}
