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
package com.streamsets.pipeline.stage.origin.jdbc.cdc.sqlserver;

import com.streamsets.pipeline.api.ConfigDef;

public class CDCTableConfigBean {
  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "捕获实例名",
      description = "如果在启用CDC表时没有指定捕获实例，那么捕获实例名的默认值是< schema>_<table>",
      displayPosition =  10,
      defaultValue = "dbo_%",
      group = "TABLE"
  )
  public String capture_instance;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      label = "表排除模式",
      description = "表名称的模式,不被读取.使用Java 正则语法.如果不需要排除,则空出.",
      displayPosition = 40,
      group = "TABLE"
  )
  public String tableExclusionPattern;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      label = "初始偏移量",
      description = "使用- 1处理所有数据或使用最后保存的偏移量",
      displayPosition =  50,
      group = "TABLE"
  )
  public String initialOffset;
}
