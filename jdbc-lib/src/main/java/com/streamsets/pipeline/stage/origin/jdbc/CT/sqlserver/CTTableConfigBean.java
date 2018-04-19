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

package com.streamsets.pipeline.stage.origin.jdbc.CT.sqlserver;

import com.streamsets.pipeline.api.ConfigDef;

public class CTTableConfigBean {
  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "Schema",
      description = "Schema 名称",
      displayPosition = 20,
      defaultValue = "dbo",
      group = "TABLE"
  )
  public String schema;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "表名的模式",
      description = "要读取的表名的模式.使用类似于SQL的语法.",
      displayPosition = 30,
      defaultValue = "%",
      group = "TABLE"
  )
  public String tablePattern;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      label = "表排除模式",
      description = "表名称的模式，不被读取.使用Java regex语法。如果不需要排除，则空出.",
      displayPosition = 40,
      group = "TABLE"
  )
  public String tableExclusionPattern;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.NUMBER,
      label = "初始偏移量",
      description = "使用- 1选择退出此选项",
      displayPosition =  50,
      defaultValue = "-1",
      group = "TABLE"
  )
  public long initialOffset = -1;
}
