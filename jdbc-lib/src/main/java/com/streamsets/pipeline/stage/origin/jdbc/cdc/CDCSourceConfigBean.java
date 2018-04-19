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
package com.streamsets.pipeline.stage.origin.jdbc.cdc;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.MultiValueChooserModel;

import java.util.List;

public class CDCSourceConfigBean {

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      label = "最大批大小(记录)",
      description = "批中记录的最大数量",
      displayPosition = 30,
      defaultValue = "100",
      group = "JDBC"
  )
  public int maxBatchSize;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "模式名称",
      displayPosition = 10,
      group = "CDC"
  )
  public String database;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.LIST,
      label = "所有表",
      displayPosition = 20,
      group = "CDC"
  )
  public List<String> tables;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "[\"INSERT\", \"UPDATE\", \"DELETE\", \"SELECT_FOR_UPDATE\"]",
      label = "变更类型",
      description = "作为记录捕获的操作.所有其他操作都被忽略",
      displayPosition = 70,
      group = "CDC"
  )
  @MultiValueChooserModel(ChangeTypesChooserValues.class)
  public List<ChangeTypeValues> changeTypes;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "大小写敏感的名字",
      description = "用于较低或混合的数据库、表和字段名.默认情况下,将名称更改为所有大写.只在数据库或表的名称周围有引号时才选择",
      displayPosition = 30,
      group = "CDC",
      defaultValue = "false"
  )
  public boolean caseSensitive;
}
