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
package com.streamsets.pipeline.stage.processor.listpivot;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigGroups;
import com.streamsets.pipeline.api.FieldSelectorModel;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Processor;
import com.streamsets.pipeline.api.StageDef;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.OnStagePreConditionFailure;
import com.streamsets.pipeline.config.OnStagePreConditionFailureChooserValues;
import com.streamsets.pipeline.configurablestage.DProcessor;

@StageDef(
    version=2,
//    label="Field Pivoter",
//    description = "Produce new records for each element of a list or map field",
//    icon="pivoter.png",
//    upgrader = ListPivotProcessorUpgrader.class,
//    onlineHelpRefUrl = "index.html#Processors/ListPivoter.html#task_dn1_k13_qw"

        label="字段转置",
        description = "为列表或地图字段的每个元素生成新记录",
        icon="pivoter.png",
        upgrader = ListPivotProcessorUpgrader.class,
        onlineHelpRefUrl = "index.html#Processors/ListPivoter.html#task_dn1_k13_qw"
)
@ConfigGroups(Groups.class)
@GenerateResourceBundle
public class ListPivotDProcessor extends DProcessor {

  @ConfigDef(
      required = true,
      group = "PIVOT",
      type = ConfigDef.Type.MODEL,
//      defaultValue = "",
//      label = "Field To Pivot",
//      description = "Path to the field that will be exploded into multiple records (supported types are LIST and LIST_MAP).",
//      displayPosition = 10

          defaultValue = "",
          label = "需转置的字段",
          description = "将被分解成多个记录的字段的路径（支持的类型是LIST和LIST_MAP）。",
          displayPosition = 10
  )
  @FieldSelectorModel(singleValued = true)
  public String listPath;

  @ConfigDef(
      required = true,
      group = "PIVOT",
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "true",
//      label = "Copy All Fields",
//      description = "Copy all fields (including the original list) to each resulting record. " +
//          "If this is not set, then the pivoted value is set as the root field of the record.",
//      displayPosition = 20

          label = "复制全部字段",
          description = "将所有字段（包括原始列表）复制到每个结果记录。如果没有设置，那么被转置值被设置为记录的根字段。",
          displayPosition = 20
  )
  public boolean copyFields;

  @ConfigDef(
      required = false,
      group = "PIVOT",
      type = ConfigDef.Type.STRING,
      defaultValue = "",
      dependsOn = "copyFields",
      triggeredByValue = "true",
//      label = "Pivoted Items Path",
//      description = "Path in the new record where the pivoted list items are written to. Each record will contain one" +
//          "item from the original list at this path. If this is not specified, the path of the original list is used. " +
//          "If there is data at this field path, it will be overwritten.",

          label = "转置元素路径",
          description = "新的记录中的旋转列表项被写入的路径。每个记录将包含此路径的原始列表中的一个项目。如果没有指定，则使用原始列表的路径。如果在此字段路径中有数据，则将被覆盖。",
      displayPosition = 30
  )
  public String newPath;

  @ConfigDef(
      required = true,
      group = "PIVOT",
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "false",
//      label = "Save Original Field Name",
//      description = "Specifies whether or not to save the original field name of the pivoted field.",
//      displayPosition = 40

    label = "保存原字段名",
    description = "指定是否保存原字段名称。",
    displayPosition = 40
  )
  public boolean saveOriginalFieldName;

  @ConfigDef(
      required = true,
      group = "PIVOT",
      type = ConfigDef.Type.STRING,
      defaultValue = "",
//      dependsOn = "saveOriginalFieldName",
//      triggeredByValue = "true",
//      label = "Original Field Name Path",
//      description = "Path in the new record to store the name of the field that was pivoted.",
//      displayPosition = 41

          dependsOn = "saveOriginalFieldName",
          triggeredByValue = "true",
          label = "原始字段名称路径",
          description = "新记录中的路径，用于存储已旋转字段的名称。",
          displayPosition = 41
  )
  public String originalFieldNamePath;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "TO_ERROR",
//      label = "Field Does Not Exist",
//      description="Action for data that does not contain the specified fields",
//      displayPosition = 50,
//      group = "PIVOT"

          label = "非法字段处理",
          description="对不包含指定字段的数据采取的措施",
          displayPosition = 50,
          group = "PIVOT"
  )
  @ValueChooserModel(OnStagePreConditionFailureChooserValues.class)
  public OnStagePreConditionFailure onStagePreConditionFailure;

  @Override
  protected Processor createProcessor() {
    return new ListPivotProcessor(
        listPath,
        newPath,
        copyFields,
        saveOriginalFieldName,
        originalFieldNamePath,
        onStagePreConditionFailure
    );
  }
}
