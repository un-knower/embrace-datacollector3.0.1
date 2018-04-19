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
package com.streamsets.datacollector.pipeline.executor.spark.yarn;

import com.streamsets.datacollector.pipeline.executor.spark.DeployMode;
import com.streamsets.datacollector.pipeline.executor.spark.DeployModeChooserValues;
import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.api.el.ELEval;
import com.streamsets.pipeline.api.el.ELEvalException;
import com.streamsets.pipeline.api.el.ELVars;
import com.streamsets.pipeline.lib.el.RecordEL;
import com.streamsets.pipeline.lib.el.TimeEL;
import com.streamsets.pipeline.lib.el.VaultEL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YarnConfigBean {
  @ConfigDef(
      type = ConfigDef.Type.MODEL,
      required = true,
      label = "部署模式",
//      label = "Deploy Mode",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN", // Applies only to YARN, not Databricks
      group = "SPARK",
      displayPosition = 20
  )
  @ValueChooserModel(DeployModeChooserValues.class)
  public DeployMode deployMode;

  @ConfigDef(
      type = ConfigDef.Type.STRING,
      required = true,
      label = "驱动内存",
//      label = "Driver Memory",
      group = "SPARK",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN",
      displayPosition = 30
  )
  public String driverMemory = "";

  @ConfigDef(
      type = ConfigDef.Type.STRING,
      required = true,
      label = "执行内存",
//      label = "Executor Memory",
      group = "SPARK",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN",
      displayPosition = 40

  )
  public String executorMemory = "";

  @ConfigDef(
      type = ConfigDef.Type.BOOLEAN,
      required = true,
      defaultValue = "true",
      label = "动态分配",
      description = "启用动态分配Spark工作节点",
//      label = "Dynamic Allocation",
//      description = "Enable the dynamic allocation of Spark worker nodes",
      group = "SPARK",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN",
      displayPosition = 50
  )
  public boolean dynamicAllocation = true;

  @ConfigDef(
      type = ConfigDef.Type.NUMBER,
      required = true,
      min = 0,
      label = "最小工作节点数",
//      label = "Minimum Number of Worker Nodes",
      group = "SPARK",
      dependsOn = "dynamicAllocation",
      triggeredByValue = "true",
      displayPosition = 60

  )
  public int minExecutors;

  @ConfigDef(
      type = ConfigDef.Type.NUMBER,
      required = true,
      min = 0,
      label = "最大工作节点数",
//      label = "Maximum Number of Worker Nodes",
      group = "SPARK",
      dependsOn = "dynamicAllocation",
      triggeredByValue = "true",
      displayPosition = 70
  )
  public int maxExecutors;

  @ConfigDef(
      type = ConfigDef.Type.NUMBER,
      required = true,
      min = 1,
      label = "工作节点数",
//      label = "Number of Worker Nodes",
      group = "SPARK",
      dependsOn = "dynamicAllocation",
      triggeredByValue = "false",
      displayPosition = 80
  )
  public int numExecutors;

  @ConfigDef(
      type = ConfigDef.Type.STRING,
      required = false,
      label = "代理用户",
//      label = "Proxy User",
      group = "SPARK",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN",
      displayPosition = 110
  )
  public String proxyUser = "";

  @ConfigDef(
      type = ConfigDef.Type.LIST,
      required = false,
      label = "Spark参数",
      description = "向Spark的加载/提交传入参数。覆盖其他参数",
//      label = "Additional Spark Arguments",
//      description = "Use this to pass any additional arguments to Spark Launcher/Spark Submit. Overrides other parameters",
      group = "SPARK",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN",
      displayPosition = 140
  )
  public List<String> noValueArgs = new ArrayList<>();

  @ConfigDef(
      type = ConfigDef.Type.MAP,
      required = false,
      label = "Spark参数和值",
      description = "向Spark的加载/提交传入参数。覆盖其他参数",
//      label = "Additional Spark Arguments and Values",
//      description = "Use this to pass any additional arguments to Spark Launcher/Spark Submit. Overrides other parameters",
      group = "SPARK",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN",
      displayPosition = 150
  )
  public Map<String, String> args = new HashMap<>();

  @ConfigDef(
      type = ConfigDef.Type.MAP,
      required = false,
      label = "环境变量",
//      label = "Environment Variables",
      group = "SPARK",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN",
      displayPosition = 160
  )
  public Map<String, String> env = new HashMap<>();

  /*
   * APPLICATION group.
   */
  @ConfigDef(
      type = ConfigDef.Type.MODEL,
      required = true,
      label = "语言",
//      label = "Language",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN", // Applies only to YARN, not Databricks
      group = "APPLICATION",
      displayPosition = 10
  )
  @ValueChooserModel(LanguageChooserValues.class)
  public Language language;

  @ConfigDef(
      type = ConfigDef.Type.STRING,
      required = true,
      label = "应用程序名称",
//      label = "Application Name",
      group = "APPLICATION",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN",
      displayPosition = 20
  )
  public String appName = "";

  @ConfigDef(
      type = ConfigDef.Type.STRING,
      required = true,
      label = "应用程序资源",
//      label = "Application Resource",
      group = "APPLICATION",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN",
      displayPosition = 30
  )
  public String appResource = "";

  @ConfigDef(
      type = ConfigDef.Type.STRING,
      required = true,
      label = "主类",
//      label = "Main Class",
      group = "APPLICATION",
      dependsOn = "language",
      triggeredByValue = "JVM",
      displayPosition = 40
  )
  public String mainClass = "";

  @ConfigDef(
      type = ConfigDef.Type.LIST,
      required = false,
      label = "应用程序参数",
//      label = "Application Arguments",
      elDefs = {RecordEL.class, VaultEL.class},
      evaluation = ConfigDef.Evaluation.EXPLICIT,
      group = "APPLICATION",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN",
      displayPosition = 50
  )
  public List<String> appArgs = new ArrayList<>();

  @ConfigDef(
      type = ConfigDef.Type.LIST,
      required = false,
      label = "附加JAR包",
//      label = "Additional JARs",
      group = "APPLICATION",
      dependsOn = "language",
      triggeredByValue = "JVM",
      displayPosition = 60
  )
  public List<String> additionalJars = new ArrayList<>();

  @ConfigDef(
      type = ConfigDef.Type.LIST,
      required = false,
      label = "依赖",
      description = "应用程序资源所需的其他Python文件的完整路径",
//      label = "Dependencies",
//      description = "Full path to additional Python files required by the application resource",
      group = "APPLICATION",
      dependsOn = "language",
      triggeredByValue = "PYTHON",
      displayPosition = 70
  )
  public List<String> pyFiles = new ArrayList<>();

  @ConfigDef(
      type = ConfigDef.Type.LIST,
      required = false,
      label = "附加文件",
//      label = "Additional Files",
      group = "APPLICATION",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN",
      displayPosition = 80
  )
  public List<String> additionalFiles = new ArrayList<>();

  @ConfigDef(
      type = ConfigDef.Type.BOOLEAN,
      required = true,
      defaultValue = "false",
      label = "等待完成",
//      label = "Wait for Completion",
      dependsOn = "deployMode",
      triggeredByValue = "CLUSTER",
      group = "APPLICATION",
      displayPosition = 90
  )
  public boolean waitForCompletion;

  @ConfigDef(
      type = ConfigDef.Type.NUMBER,
      required = true,
      defaultValue = "0",
      min = 0,
      label = "最大等待时间(ms)",
      description = "等待应用程序执行完成的时间. 0表示始终等待.",
//      label = "Maximum Time to Wait (ms)",
//      description = "Time to wait for the app to complete. 0 to wait forever.",
      elDefs = TimeEL.class,
      dependsOn = "waitForCompletion",
      triggeredByValue = "true",
      displayPosition = 100
  )
  public long waitTimeout;

  @ConfigDef(
      type = ConfigDef.Type.BOOLEAN,
      required = true,
      defaultValue = "false",
      label = "启用详细记录",
      description = "仅用于测试时启用，因为会向sdc.log写入很多日志",
//      label = "Enable Verbose Logging",
//      description = "Enable only for testing, as a lot of additional log data is written to sdc.log",
      group = "APPLICATION",
      dependsOn = "clusterManager^",
      triggeredByValue = "YARN",
      displayPosition = 110
  )
  public boolean verbose;

  private ELEval elEval;
  private ELVars elVars;

  public List<Stage.ConfigIssue> init(Stage.Context context, String prefix) {
    List<Stage.ConfigIssue> issues = new ArrayList<>();

    elVars = context.createELVars();
    elEval = context.createELEval("appArgs");

    for (String arg : appArgs) {
      try {
        context.parseEL(arg);
      } catch (ELEvalException ex) { // NOSONAR
        issues.add(context.createConfigIssue(
            "APPLICATION", prefix + "appArgs", ex.getErrorCode(), ex.getParams()));
      }
    }
    return issues;
  }

  List<String> evaluateArgsELs(Record record) throws ELEvalException {
    RecordEL.setRecordInContext(elVars, record);
    List<String> evaluatedArgs = new ArrayList<>(appArgs.size());
    for (String arg : appArgs) {
      evaluatedArgs.add(elEval.eval(elVars, arg, String.class));
    }
    return evaluatedArgs;
  }
}
