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
package com.streamsets.pipeline.stage.origin.mongodb;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.lib.el.TimeEL;
import com.streamsets.pipeline.stage.common.mongodb.MongoDBConfig;

public class MongoSourceConfigBean {

  @ConfigDefBean(groups = {"MONGODB", "CREDENTIALS", "ADVANCED"})
  public MongoDBConfig mongoConfig;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "true",
      label = "固定集合",
      description = "查询非固定集合时请取消勾选.",
//      label = "Capped Collection",
//      description = "Un-check this box if querying an uncapped collection.",
      displayPosition = 1001,
      group = "MONGODB"
  )
  public boolean isCapped;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      defaultValue = "2015-01-01 00:00:00",
      label = "初始偏移量",
      description = "若偏移量字段是ObjectId类型，则必须以YYYY-MM-DD HH:mm:ss时间格式填写；若是String类型，则填写初始字符串。",
//      label = "Initial Offset",
//      description = "Must be provided in timestamp format: YYYY-MM-DD HH:mm:ss if offset field is ObjectId type. " +
//                    "If offset field is String type, provide an initial string. Oldest data to be retrieved.",
      displayPosition = 1002,
      group = "MONGODB"
  )
  public String initialOffset;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MODEL,
      defaultValue = "OBJECTID",
      label = "偏移量字段类型",
      description = "偏移量字段的类型. 目前支持ObjectId和String类型.",
//      label = "Offset Field Type",
//      description = "Offset field type. Currently ObjectId and String types are supported.",
      displayPosition = 1003,
      group = "MONGODB"
  )
  @ValueChooserModel(OffsetFieldTypeChooserValues.class)
  public OffsetFieldType offsetType;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      defaultValue = "_id",
      label = "偏移量字段",
      description = "标识当前偏移量的字段",
//      label = "Offset Field",
//      description = "Field checked to track current offset.",
      displayPosition = 1010,
      group = "MONGODB"
  )
  public String offsetField;

  @ConfigDef(
      type = ConfigDef.Type.NUMBER,
      label = "批处理大小(记录数)",
//      label = "Batch Size (records)",
      defaultValue = "1000",
      required = true,
      min = 2, // Batch size of 1 in MongoDB is special and analogous to LIMIT 1
      displayPosition = 1011,
      group = "MONGODB"
  )
  public int batchSize;

  @ConfigDef(
      type = ConfigDef.Type.NUMBER,
      label = "最大批处理等待时间",
//      label = "Max Batch Wait Time",
      defaultValue = "${5 * SECONDS}",
      required = true,
      elDefs = {TimeEL.class},
      evaluation = ConfigDef.Evaluation.IMPLICIT,
      displayPosition = 1012,
      group = "MONGODB"
  )
  public long maxBatchWaitTime;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "SECONDARY_PREFERRED",
      label = "读优先设置",
      description = "设置读节点优先权",
//      label = "Read Preference",
//      description = "Sets the read preference",
      group = "MONGODB",
      displayPosition = 1013
  )
  @ValueChooserModel(ReadPreferenceChooserValues.class)
  public ReadPreferenceLabel readPreference;
}
