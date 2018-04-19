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
package com.streamsets.datacollector.creation;

import com.streamsets.datacollector.config.DeliveryGuaranteeChooserValues;
import com.streamsets.datacollector.config.ErrorHandlingChooserValues;
import com.streamsets.datacollector.config.ErrorRecordPolicy;
import com.streamsets.datacollector.config.ErrorRecordPolicyChooserValues;
import com.streamsets.datacollector.config.ExecutionModeChooserValues;
import com.streamsets.datacollector.config.MemoryLimitExceeded;
import com.streamsets.datacollector.config.MemoryLimitExceededChooserValues;
import com.streamsets.datacollector.config.PipelineGroups;
import com.streamsets.datacollector.config.PipelineLifecycleStageChooserValues;
import com.streamsets.datacollector.config.PipelineState;
import com.streamsets.datacollector.config.PipelineStateChooserValues;
import com.streamsets.datacollector.config.PipelineWebhookConfig;
import com.streamsets.datacollector.config.StatsTargetChooserValues;
import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigGroups;
import com.streamsets.pipeline.api.DeliveryGuarantee;
import com.streamsets.pipeline.api.ExecutionMode;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.ListBeanModel;
import com.streamsets.pipeline.api.MultiValueChooserModel;
import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.StageDef;
import com.streamsets.pipeline.api.ValueChooserModel;

import java.util.Collections;
import java.util.List;
import java.util.Map;

// we are using the annotation for reference purposes only.
// the annotation processor does not work on this maven project
// we have a hardcoded 'datacollector-resource-bundles.json' file in resources
@GenerateResourceBundle
@StageDef(
    version = PipelineConfigBean.VERSION,
    label = "任务任务",
    upgrader = PipelineConfigUpgrader.class,
    onlineHelpRefUrl = "not applicable"
)
@ConfigGroups(PipelineGroups.class)
public class PipelineConfigBean implements Stage {

  public static final int VERSION = 7;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "执行模式",
      defaultValue= "STANDALONE",
      displayPosition = 10
  )
  @ValueChooserModel(ExecutionModeChooserValues.class)
  public ExecutionMode executionMode;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue="AT_LEAST_ONCE",
      label = "可靠性",
      displayPosition = 20
  )
  @ValueChooserModel(DeliveryGuaranteeChooserValues.class)
  public DeliveryGuarantee deliveryGuarantee;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MODEL,
      label = "开始事件",
      description = "应该处理任务启动事件的阶段",
      defaultValue = "streamsets-datacollector-basic-lib::com_streamsets_pipeline_stage_destination_devnull_ToErrorNullDTarget::1",
      displayPosition = 23
  )
  @ValueChooserModel(PipelineLifecycleStageChooserValues.class)
  public String startEventStage;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MODEL,
      label = "结束事件",
      description = "在任务结束时执行的事件",
      defaultValue = "streamsets-datacollector-basic-lib::com_streamsets_pipeline_stage_destination_devnull_ToErrorNullDTarget::1",
      displayPosition = 26
  )
  @ValueChooserModel(PipelineLifecycleStageChooserValues.class)
  public String stopEventStage;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.BOOLEAN,
    defaultValue = "true",
    label = "错误重试",
    displayPosition = 30)
  public boolean shouldRetry;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.NUMBER,
    defaultValue = "-1",
    label = "重试次数",
    dependsOn = "shouldRetry",
    triggeredByValue = "true",
    description = "若要无限期重试,请使用-1。重试之间的等待时间从15秒到5分钟",
    displayPosition = 30)
  public int retryAttempts;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.NUMBER,
    label = "最大任务内存(MB)",
    defaultValue = "${jvm:maxMemoryMB() * 0.85}",
    description = "最大内存量的任务可以使用配置关系到SDC java堆大小。默认值是堆的85%，0的值禁用该限制",
    displayPosition = 60,
    min = 0,
    group = ""
  )
  public long memoryLimit;


  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue="LOG",
      label = "内存溢出处理",
      description = "当任务超出内存限制时的操作" ,
      displayPosition = 70,
      group = ""
  )
  @ValueChooserModel(MemoryLimitExceededChooserValues.class)
  public MemoryLimitExceeded memoryLimitExceeded;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.MODEL,
    defaultValue = "[\"RUN_ERROR\", \"STOPPED\", \"FINISHED\"]",
    label = "任务状态变化通知",
    description = "当任务到达指定状态时，通过电子邮件通知",
    displayPosition = 75,
    group = "NOTIFICATIONS"
  )
  @MultiValueChooserModel(PipelineStateChooserValues.class)
  public List<PipelineState> notifyOnStates;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.LIST,
    defaultValue = "[]",
    label = "邮箱",
    description = "邮箱地址",
    displayPosition = 76,
    group = "NOTIFICATIONS"
  )
  public List<String> emailIDs;

  @ConfigDef(
      required = false,
      defaultValue = "{}",
      type = ConfigDef.Type.MAP,
      label = "参数",
      displayPosition = 80,
      group = "PARAMETERS"
  )
  public Map<String, Object> constants;


  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "错误记录处理",
      displayPosition = 90,
      group = "BAD_RECORDS"
  )
  @ValueChooserModel(ErrorHandlingChooserValues.class)
  public String badRecordsHandling;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue="ORIGINAL_RECORD",
      label = "处理方式",
      description = "错误记录处理方式",
      displayPosition = 93,
      group = "BAD_RECORDS"
  )
  @ValueChooserModel(ErrorRecordPolicyChooserValues.class)
  public ErrorRecordPolicy errorRecordPolicy = ErrorRecordPolicy.ORIGINAL_RECORD;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.MODEL,
    label = "数据聚合器",
    defaultValue = "streamsets-datacollector-basic-lib::com_streamsets_pipeline_stage_destination_devnull_StatsNullDTarget::1",
    displayPosition = 95,
    group = "STATS"
  )
  @ValueChooserModel(StatsTargetChooserValues.class)
  public String statsAggregatorStage;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      label = "工作进程个数",
      description = "工作进程的数量，0表示启动与kafka分区一样多的topic",
      defaultValue = "0",
      min = 0,
      displayPosition = 100,
      group = "CLUSTER",
      dependsOn = "executionMode",
      triggeredByValue = {"CLUSTER_YARN_STREAMING"}
  )
  public long workerCount;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      label = "工作进程内存(MB)",
      defaultValue = "2048",
      displayPosition = 150,
      group = "CLUSTER",
      dependsOn = "executionMode",
      triggeredByValue = {"CLUSTER_BATCH", "CLUSTER_YARN_STREAMING"}
  )
  public long clusterSlaveMemory;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.STRING,
    label = "工作进程Java参数",
    defaultValue = "-XX:+UseConcMarkSweepGC -XX:+UseParNewGC -Dlog4j.debug",
    description = "添加需要的属性",
//    description = "Add properties as needed. Changes to default settings are not recommended.",
    displayPosition = 110,
    group = "CLUSTER",
    dependsOn = "executionMode",
    triggeredByValue = {"CLUSTER_BATCH", "CLUSTER_YARN_STREAMING"}
  )
  public String clusterSlaveJavaOpts;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.MAP,
    defaultValue = "{}",
    label = "运行环境",
    description = "为集运行设置附加的环境变量",
    displayPosition = 120,
    group = "CLUSTER",
    dependsOn = "executionMode",
    triggeredByValue = {"CLUSTER_BATCH", "CLUSTER_YARN_STREAMING"}
  )
  public Map<String, String> clusterLauncherEnv;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.STRING,
    label = "Mesos调度服务地址",
    description = "Mesos框架的服务地址",
//    label = "Mesos Dispatcher URL",
//    description = "URL for service which launches Mesos framework",
    displayPosition = 130,
    group = "CLUSTER",
    dependsOn = "executionMode",
    triggeredByValue = {"CLUSTER_MESOS_STREAMING"}
  )
  public String mesosDispatcherURL;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.STRING,
    label = "配置文件目录",
    description = "HDFS/S3配置文件core-site.xml和hdfs-site.xml所在的SDC资源文件目录或软连接",
//    label = "Checkpoint Configuration Directory",
//    description = "An SDC resource directory or symbolic link with HDFS/S3 configuration files core-site.xml and hdfs-site.xml",
    displayPosition = 150,
    group = "CLUSTER",
    dependsOn = "executionMode",
    triggeredByValue = {"CLUSTER_MESOS_STREAMING"}
  )
  public String hdfsS3ConfDir;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "0",
      label = "速度限制(个数/秒)",
      description = "每秒应被接受到任务中的最大记录数，如果没有设置或设置为0，则不限制速率",
      displayPosition = 180
  )
  public long rateLimit;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "0",
      label = "最大运行个数",
      description = "应该为该任务创建的最大运行数，设置为0表示不限制",
      min = 0,
      displayPosition = 190
  )
  public int maxRunners = 0;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.BOOLEAN,
    defaultValue = "true",
    label = "创建失败快照",
    description = "若勾选，当任务执行失败且不可恢复时，SDC将尝试创造未处理记录的局部快照",
//    label = "Create Failure Snapshot",
//    description = "When selected and the pipeline execution fails with unrecoverable exception, SDC will attempt to create" +
//            "partial snapshot with records that have not been processed yet.",
    displayPosition = 200
  )
  public boolean shouldCreateFailureSnapshot;

  @ConfigDef(required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "[]",
      label = "Webhook",
      description = "Webhook",
      displayPosition = 210,
      group = "NOTIFICATIONS"
  )
  @ListBeanModel
  public List<PipelineWebhookConfig> webhookConfigs = Collections.emptyList();

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MAP,
      defaultValue = "{}",
      label = "其他的Spark配置",
      description = "添加spark参数传递给spark脚本执行, 参数将被传递--conf <key>=<value>",
      displayPosition = 220,
      group = "CLUSTER",
      dependsOn = "executionMode",
      triggeredByValue = {"CLUSTER_YARN_STREAMING"})
  public Map<String, String> sparkConfigs;

  @Override
  public List<ConfigIssue> init(Info info, Context context) {
    return Collections.emptyList();
  }

  @Override
  public void destroy() {
  }

}
