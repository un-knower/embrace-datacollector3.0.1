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
package com.streamsets.pipeline.stage.executor.shell.config;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.lib.el.RecordEL;
import com.streamsets.pipeline.lib.el.TimeEL;

import java.util.Collections;
import java.util.Map;

public class ShellConfig {

  // ENVIRONMENT tab

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.MAP,
//    label = "Environment variables",
//    description = "Variables that will be provided to the script as environment variables.",
//    group = "ENVIRONMENT",
//    evaluation = ConfigDef.Evaluation.EXPLICIT,
//    elDefs = {RecordEL.class},
//    displayPosition = 10

          label = "环境变量配置",
          description = "将作为脚本的环境变量",
          group = "ENVIRONMENT",
          evaluation = ConfigDef.Evaluation.EXPLICIT,
          elDefs = {RecordEL.class},
          displayPosition = 10
  )
  public Map<String, String> environmentVariables = Collections.emptyMap();

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.STRING,
//    label = "Timeout (ms)",
//    defaultValue = "1000",
//    description = "How long will the script be allowed to run. The time is in milliseconds.",
//    displayPosition = 20,
//    evaluation = ConfigDef.Evaluation.EXPLICIT,
//    elDefs = {TimeEL.class},
//    group = "ENVIRONMENT"

          label = "超时处理(ms)",
          defaultValue = "1000",
          description = "脚本将允许运行多长时间。时间以毫秒为单位。",
          displayPosition = 20,
          evaluation = ConfigDef.Evaluation.EXPLICIT,
          elDefs = {TimeEL.class},
          group = "ENVIRONMENT"
  )
  public String timeout = "1000";

  // SCRIPT tab

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.TEXT,
//    label = "Script",
//    group = "SCRIPT",
//    mode = ConfigDef.Mode.SHELL,
//    evaluation = ConfigDef.Evaluation.EXPLICIT,
//    displayPosition = 10

          label = "脚本",
          group = "SCRIPT",
          mode = ConfigDef.Mode.SHELL,
          evaluation = ConfigDef.Evaluation.EXPLICIT,
          displayPosition = 10
  )
  public String script;
}
