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
package com.streamsets.pipeline.stage.destination.mongodb;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.FieldSelectorModel;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.stage.common.mongodb.MongoDBConfig;

public class MongoTargetConfigBean {

  @ConfigDefBean(groups = {"MONGODB", "CREDENTIALS", "ADVANCED"})
  public MongoDBConfig mongoConfig;

  @ConfigDef(
      type = ConfigDef.Type.MODEL,
      label = "唯一索引字段",
      description = "唯一索引字段在更新和替换时必需，而插入和删除时是非必需的",
//      label = "Unique Key Field",
//      description = "Unique key field is required for update and replace while optional for inserts and deletes",
      required = false,
      displayPosition = 1000,
      group = "MONGODB"
  )
  @FieldSelectorModel(singleValued = true)
  public String uniqueKeyField;

  @ConfigDef(
      type = ConfigDef.Type.BOOLEAN,
      label = "Upsert",
      defaultValue = "false",
      description = "更新和替换操作时设置Upsert标识",
//      label = "Upsert",
//      defaultValue = "false",
//      description = "Sets the Upsert flag for Update and Replace operations",
      required = true,
      displayPosition = 1010,
      group = "MONGODB"
  )
  public boolean isUpsert;

  @ConfigDef(
      type = ConfigDef.Type.MODEL,
      label = "写安全级别",
      description = "设置写安全级别",
//      label = "Write Concern",
//      description = "Sets the write concern",
      defaultValue = "JOURNALED",
      required = true,
      displayPosition = 1020,
      group = "MONGODB"
  )
  @ValueChooserModel(WriteConcernChooserValues.class)
  public WriteConcernLabel writeConcern = WriteConcernLabel.JOURNALED;

}
