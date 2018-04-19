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
package com.streamsets.pipeline.stage.processor.fieldmerger;

import com.streamsets.pipeline.api.ListBeanModel;
import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDef.Type;
import com.streamsets.pipeline.api.ConfigGroups;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Processor;
import com.streamsets.pipeline.api.StageDef;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.OnStagePreConditionFailure;
import com.streamsets.pipeline.config.OnStagePreConditionFailureChooserValues;
import com.streamsets.pipeline.configurablestage.DProcessor;

import java.util.List;

@StageDef(
    version=1,
//    label="Field Merger",
//    description = "Merge fields of like types",
//    icon="merge.png",
//    onlineHelpRefUrl = "index.html#Processors/FieldMerger.html#task_ghx_5vl_gt"

    label="字段融合",
    description = "融合类似的字段",
    icon="merge.png",
    onlineHelpRefUrl = "index.html#Processors/FieldMerger.html#task_ghx_5vl_gt"
)
@ConfigGroups(Groups.class)
@GenerateResourceBundle
public class FieldMergerDProcessor extends DProcessor {

  @ConfigDef(
      required = true,
      type = Type.MODEL,
//      defaultValue="",
//      label = "Fields to merge",
//      description = "Fields to merge, and fields to merge into.",
//      displayPosition = 40,
//      group = "MERGE"

      defaultValue="",
      label = "融合字段",
      description = "待融合的字段",
      displayPosition = 40,
      group = "MERGE"
  )
  @ListBeanModel
  public List<FieldMergerConfig> mergeMapping;

  @ConfigDef(
    required = true,
    type = Type.MODEL,
//    defaultValue = "TO_ERROR",
//    label = "Source Field Does Not Exist",
//    description="Action for data that does not contain the specified source field",
//    displayPosition = 30,
//    group = "MERGE"

      defaultValue = "TO_ERROR",
      label = "源字段缺失",
      description="对缺少指定源字段的数据的操作",
      displayPosition = 30,
      group = "MERGE"
  )
  @ValueChooserModel(OnStagePreConditionFailureChooserValues.class)
  public OnStagePreConditionFailure onStagePreConditionFailure;

  @ConfigDef(
      required = true,
      type = Type.BOOLEAN,
//      defaultValue = "FALSE",
//      label = "Overwrite Fields",
//      description="Whether or not to overwrite fields if a target field already exists",
//      displayPosition = 30,
//      group = "MERGE"

      defaultValue = "FALSE",
      label = "覆盖字段",
      description="如果目标字段已经存在，是否覆盖字段",
      displayPosition = 30,
      group = "MERGE"
  )
  public boolean overwriteExisting;

  @Override
  protected Processor createProcessor() {
    return new FieldMergerProcessor(mergeMapping, onStagePreConditionFailure, overwriteExisting);
  }
}
