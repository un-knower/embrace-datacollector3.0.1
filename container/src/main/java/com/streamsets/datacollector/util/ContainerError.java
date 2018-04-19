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
package com.streamsets.datacollector.util;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

// we are using the annotation for reference purposes only.
// the annotation processor does not work on this maven project
// we have a hardcoded 'datacollector-resource-bundles.json' file in resources
@GenerateResourceBundle
public enum ContainerError implements ErrorCode {
  // Unchecked exception
  CONTAINER_0000("运行时异常: {}"),

  // StageContext
  CONTAINER_0001("{}"),
  CONTAINER_0002("{}"),

  CONTAINER_0010("验证节点配置出错: {}"),
  CONTAINER_0011("任务内存占用{}MB，超出限制{}MB. 占用最多的是{}，占用{}MB. " +
          "其余节点: {}"),

  // RequiredFieldsErrorPredicateSink
  CONTAINER_0050("该节点中记录需要包含以下字段: '{}'"),

  // PreconditionsErrorPredicateSink
  CONTAINER_0051("前提条件不满足'{}'"),
  CONTAINER_0052("评估前提条件出错'{}': {}"),

  // PipelineManager
  CONTAINER_0100("无法设置状态: {}"),
  CONTAINER_0101("无法获取状态: {}"),
  CONTAINER_0102("无法将状态{}转换为{}"),
  CONTAINER_0103("无法在运行期间设置数据源偏移量"),
  CONTAINER_0104("无法在任务运行时重置源偏移量"),
  CONTAINER_0105("无法捕获快照，因为任务没有运行"),
  CONTAINER_0106("由于任务没有运行，所以无法获得错误记录"),
  CONTAINER_0107("无效的批处理大小: {}"),
  CONTAINER_0108("无法启动任务管理器: {}"),
  CONTAINER_0109("任务{}不存在"),
  CONTAINER_0110("无法创建任务'{}': {}"),
  CONTAINER_0111("无法删除任务'{}'的错误,任务正在运行"),
  CONTAINER_0112("数据源并发数不能小于1"),
  CONTAINER_0113("无法删除任务'{}'的历史,任务正在运行"),
  CONTAINER_0114("检索缓存中状态出错: {}"),
  CONTAINER_0115("获取任务历史失败: '{}', '{}'由于: {}"),
  CONTAINER_0116("无法加载任务'{}:{}'配置: {}"),
  CONTAINER_0117("无法确定并发数: {}"),
  CONTAINER_0118("无法在任务运行时更新源偏移量"),

  // PipelineRunners
  CONTAINER_0150("任务配置出错: {}"),
  CONTAINER_0151("任务构建出错: {}"),
  CONTAINER_0152("节点'{}', 实例'{}', 变量'{}', 值'{}', 配置注入出错: {}"),
  CONTAINER_0153("节点'{}', 实例'{}', 属性'{}'未配置"),
  CONTAINER_0154("由于下列配置问题无法预览: {}"),
  CONTAINER_0155("实例'{}'必填字段配置必须是一个列表而不是一个'{}'"),
  CONTAINER_0156("无效的实例'{}'"),
  CONTAINER_0157("无法在数据源实例上预览节点，实例'{}'"),
  CONTAINER_0158("无法启动任务: {}"),
  CONTAINER_0159("无法预览原始数据源，因为管道'{}'是空的"),
  CONTAINER_0160("预览原始数据源之前,必须配置以下所需参数: '{}'"),
  CONTAINER_0161("节点'{}', 实例'{}', 变量'{}', 配置注入出错: 值List中有非字符串元素"),
  CONTAINER_0162("节点'{}', 实例'{}', 变量'{}', 配置注入出错: 值Map中有非字符串键"),
  CONTAINER_0163("节点'{}', 实例'{}', 变量'{}', 配置注入出错: 值Map中有非字符串值"),
  CONTAINER_0164("节点'{}', 实例'{}', 变量'{}', 配置注入出错: 值Map或List中有非字符串元素"),
  CONTAINER_0165("节点配置验证问题: {}"),
  CONTAINER_0166("由于可用线程不够,无法启动任务'{}'. 线程池大小由$SDC_CONF/sdc.properties文件中的runner.thread.pool.size属性控制."),

  //PipelineStore
  CONTAINER_0200("任务'{}'不存在"),
  CONTAINER_0201("任务'{}'已存在"),
  CONTAINER_0202("无法创建任务'{}': {}"),
  CONTAINER_0203("无法删除任务'{}': {}"),
  CONTAINER_0204("无法保存任务'{}': {}"),
  CONTAINER_0205("任务'{}'已修改. 请刷新页面预览或修改最新的任务."),
  CONTAINER_0206("无法加载任务详情'{}': {}"),
  CONTAINER_0207("节点定义无效,节点:'{}',库:'{}',版本:'{}'"),
  CONTAINER_0208("'{}'状态任务无法保存"),
  CONTAINER_0209("任务状态文件'{}'不存在"),
  CONTAINER_0210("无法获取JSON字符串: {}"),
  CONTAINER_0211("任务状态'{}::{}'在'{}'执行模式下不存在"),
  CONTAINER_0212("任务状态'{}::{}'在'{}'模式下无法保存,因为'{}::{}'任务已存在"),
  CONTAINER_0213("无法从任务存储中检索任务。详情:'{}'"),

  //Previewr
  CONTAINER_0250("无法创建预览页: '{}'"),

  // AdminResource
  CONTAINER_0300("达到最大并发客户端数'{}'. 请通过其他Rest API追踪日志."),

  //Observer
  CONTAINER_0400("求值失败'{}',记录:'{}': {}"),
  CONTAINER_0401("评估表达式失败'{}': {}"),
  CONTAINER_0402("无法访问警报，因为任务没有运行"),
  CONTAINER_0403("无法加载任务规则定义'{}': {}"),
  CONTAINER_0404("无法保存任务规则定义'{}': {}"),
  CONTAINER_0405("无法保存任务UI信息'{}': {}"),
  CONTAINER_0406("无法保存任务ACLs'{}': {}"),

  CONTAINER_0500("邮件发送出错: {}"),

  //Snapshot
  CONTAINER_0600("检索快照'{}'失败,任务名称:'{}',版本号:'{}' : '{}'"),
  CONTAINER_0601("删除快照'{}'失败,任务名称:'{}',版本号:'{}'"),
  CONTAINER_0602("持久化快照信息'{}'失败,任务名称:'{}',版本号:'{}' : '{}'"),
  CONTAINER_0603("持久化快照'{}'失败,任务名称:'{}',版本号:'{}' : '{}'"),
  CONTAINER_0604("检索快照信息'{}'失败,任务名称:'{}',版本号:'{}' : '{}'"),
  CONTAINER_0605("创建快照后才能保存"),

  CONTAINER_0700("节点初始化出错: {}"),
  CONTAINER_0701("节点'{}'初始化出错: {}"),
  CONTAINER_0702("任务初始化出错: {}"),
  CONTAINER_0703("聚合器节点初始化出错: {}"),
  CONTAINER_0704("不能创建额外的任务运行线程: {}"),
  CONTAINER_0705("需要的运行线程数{}超过所允许的最大数量{}"),

  //Pipeline Lifecycle events
  CONTAINER_0790("任务生命周期事件节点初始化出错{}"),
  CONTAINER_0791("任务生命周期事件阶段运行出错: {}"),
  CONTAINER_0792("任务生命周期事件阶段生成错误记录: {}"),

  //Runner
  CONTAINER_0800("任务'{}'验证出错: {}"),
  CONTAINER_0801("线程意外中断"),
  CONTAINER_0802("检测到异常运行线程(完成数仅{}/{})"),
  CONTAINER_0803("线程池销毁后,尝试获取任务执行线程"),

  //PipelineConfigurationUpgrader
  CONTAINER_0900("将节点配置从版本{}升级版本{}时出错'{}': {}"),
  CONTAINER_0901("无法找到节点定义'{}:{}'"),
  CONTAINER_0902("'{}:{}'节点定义版本'{}'早于节点配置'{}'指定的版本"),

  //Email Notifier
  CONTAINER_01000("加载邮件模版出错, 原因: {}"),
  CONTAINER_01001("发送邮件出错: {}"),

  // Remote Control pipelines
  CONTAINER_01100("无法执行'{}'操作处理本地任务'{}'"),
  CONTAINER_01101("无法执行'{}'操作处理远程任务'{}'"),

  // ACL
  CONTAINER_01200("{} '{}'没有{}权限访问任务{}"),
  CONTAINER_01201("只有任务的拥有着{}或管理员用户允许修改ACL信息"),

  // misc
  CONTAINER_01300("未设置环境变量'STREAMSETS_LIBRARIES_EXTRA_DIR'"),
  CONTAINER_01301("无效的节点库名称'{}'"),

  //LineageEvent problems:
  CONTAINER_01401("无法在节点上创建框架级事件'{}'"),
  CONTAINER_01402("无法在框架上创建节点级事件'{}'"),
  CONTAINER_01403("特定属性缺失或空白'{}'"),
  CONTAINER_01404("将未知的LineageEventType传递给missingSpecificAttributes()方法'{}'"),

  CONTAINER_01500("'{}'节点配置'{}', EL必须解析为字符串或凭证值'{}'"),


  CONTAINER_01600("'{}'任务执行模式下不支持下载可执行文件: "),
  CONTAINER_01601("任务'{}'在'{}'执行模式不支持启动操作"),

//  CONTAINER_0000("Runtime exception: {}"),

//  // StageContext
//  CONTAINER_0001("{}"),
//  CONTAINER_0002("{}"),
//
//  CONTAINER_0010("Stage configuration validation error: {}"),
//  CONTAINER_0011("Pipeline memory consumption {} MB exceeded allowed memory {} MB. Largest consumer is {} at {} MB. " +
//    "Remaining stages: {}"),
//
//  // RequiredFieldsErrorPredicateSink
//  CONTAINER_0050("The stage requires records to include the following required fields: '{}'"),
//
//  // PreconditionsErrorPredicateSink
//  CONTAINER_0051("Unsatisfied precondition(s) '{}'"),
//  CONTAINER_0052("Failed to evaluate precondition '{}': {}"),
////  CONTAINER_0051("Unsatisfied precondition(s) '{}'"),
////  CONTAINER_0052("Failed to evaluate precondition '{}': {}"),
//
//  // PipelineManager
//  CONTAINER_0100("Cannot set state: {}"),
//  CONTAINER_0101("Cannot get state: {}"),
//  CONTAINER_0102("Cannot change state from {} to {}"),
//  CONTAINER_0103("Cannot set the source offset during a run"),
//  CONTAINER_0104("Cannot reset the source offset when the pipeline is running"),
//  CONTAINER_0105("Cannot capture a snapshot because the pipeline is not running"),
//  CONTAINER_0106("Cannot get error records because the pipeline is not running"),
//  CONTAINER_0107("Invalid batch size: {}"),
//  CONTAINER_0108("Cannot start the pipeline manager: {}"),
//  CONTAINER_0109("Pipeline {} does not exist"),
//  CONTAINER_0110("Cannot create pipeline '{}': {}"),
//  CONTAINER_0111("Cannot delete errors for pipeline '{}' when the pipeline is running"),
//  CONTAINER_0112("Origin Parallelism cannot be less than 1"),
//  CONTAINER_0113("Cannot delete history for pipeline '{}' when the pipeline is running"),
//  CONTAINER_0114("Error while retrieving state from cache: {}"),
//  CONTAINER_0115("Failed to fetch history for pipeline: '{}', '{}' due to: {}"),
//  CONTAINER_0116("Cannot load pipeline '{}:{}' configuration: {}"),
//  CONTAINER_0117("Could not determine parallelism: {}"),
//  CONTAINER_0118("Cannot update the source offset when the pipeline is running"),
//
//  // PipelineRunners
//  CONTAINER_0150("Pipeline configuration error: {}"),
//  CONTAINER_0151("Pipeline build error: {}"),
//  CONTAINER_0152("Stage '{}', instance '{}', variable '{}', value '{}', configuration injection error: {}"),
//  CONTAINER_0153("Stage '{}', instance '{}', property '{}' is not configured"),
//  CONTAINER_0154("Cannot preview due to the following configuration issues: {}"),
//  CONTAINER_0155("Instance '{}' required fields configuration must be a list instead of a '{}'"),
//  CONTAINER_0156("Invalid instance '{}'"),
//  CONTAINER_0157("Cannot do a preview stage run on an origin, instance '{}'"),
//  CONTAINER_0158("Cannot run the pipeline: {}"),
//  CONTAINER_0159("Cannot perform raw source preview because pipeline '{}' is empty"),
//  CONTAINER_0160("Cannot perform raw source preview until the following required parameters are configured: '{}'"),
//  CONTAINER_0161("Stage '{}', instance '{}', variable '{}', configuration injection error: Value List has non-string elements"),
//  CONTAINER_0162("Stage '{}', instance '{}', variable '{}', configuration injection error: Value Map has non-string keys"),
//  CONTAINER_0163("Stage '{}', instance '{}', variable '{}', configuration injection error: Value Map has non-string values"),
//  CONTAINER_0164("Stage '{}', instance '{}', variable '{}', configuration injection error: Value Map as List has non-string elements"),
//  CONTAINER_0165("Stage configuration validation issues: {}"),
//  CONTAINER_0166("Cannot start pipeline '{}' as there are not enough threads available. The runner.thread.pool.size property in the Data Collector configuration file, $SDC_CONF/sdc.properties, determines the number of threads in the pool that are available to run pipelines."),
//
//  //PipelineStore
//  CONTAINER_0200("Pipeline '{}' does not exist"),
//  CONTAINER_0201("Pipeline '{}' already exists"),
//  CONTAINER_0202("Cannot create pipeline '{}': {}"),
//  CONTAINER_0203("Cannot delete pipeline '{}': {}"),
//  CONTAINER_0204("Cannot save pipeline '{}': {}"),
//  CONTAINER_0205("The pipeline '{}' has been changed. Reload the page to view or edit the latest version of the pipeline."),
//  CONTAINER_0206("Cannot load details for pipeline '{}': {}"),
//  CONTAINER_0207("Definition for Stage '{}' from library '{}' with version '{}' is not available"),
//  CONTAINER_0208("Pipeline in state '{}' cannot be saved"),
//  CONTAINER_0209("Pipeline state file '{}' doesn't exist"),
//  CONTAINER_0210("Cannot fetch JSON string: {}"),
//  CONTAINER_0211("Pipeline state doesn't exist for pipeline '{}::{}' in execution mode: '{}'"),
//  CONTAINER_0212("Cannot save state of pipeline '{}::{}' in execution mode: '{}' as there is already an existing"
//    + "pipeline '{}::{}'"),
//  CONTAINER_0213("Could not retrieve pipelines from Pipeline Store. See stacktrace for additional details: '{}'"),
//
//  //Previewr
//  CONTAINER_0250("Cannot create previewer: '{}'"),
//
//  // AdminResource
//  CONTAINER_0300("Reached maximum number of concurrent clients '{}'. Tailing the log through the REST API."),
//
//  //Observer
//  CONTAINER_0400("Failed to evaluate expression '{}' for record '{}': {}"),
//  CONTAINER_0401("Failed to evaluate expression '{}': {}"),
//  CONTAINER_0402("Cannot access alerts because the pipeline is not running"),
//  CONTAINER_0403("Cannot load rule definitions for pipeline '{}': {}"),
//  CONTAINER_0404("Cannot store rule definitions for pipeline '{}': {}"),
//  CONTAINER_0405("Cannot store UI info for pipeline '{}': {}"),
//  CONTAINER_0406("Cannot store ACL for pipeline '{}': {}"),
//
//  CONTAINER_0500("EmailSender error: {}"),
//
//  //Snapshot
//  CONTAINER_0600("Error retrieving snapshot '{}' for pipeline with name '{}' and revision '{}' : '{}'"),
//  CONTAINER_0601("Error deleting snapshot '{}' for pipeline with name '{}' and revision '{}'"),
//  CONTAINER_0602("Error persisting snapshot info '{}' for pipeline with name '{}' and revision '{}' : '{}'"),
//  CONTAINER_0603("Error persisting snapshot '{}' for pipeline with name '{}' and revision '{}' : '{}'"),
//  CONTAINER_0604("Error retrieving snapshot info '{}' for pipeline with name '{}' and revision '{}' : '{}'"),
//  CONTAINER_0605("Snapshot must be created before saving"),
//
//  CONTAINER_0700("Error stage initialization error: {}"),
//  CONTAINER_0701("Stage '{}' initialization error: {}"),
//  CONTAINER_0702("Pipeline initialization error: {}"),
//  CONTAINER_0703("Stats Aggregator stage initialization error: {}"),
//  CONTAINER_0704("Can't create additional pipeline runners: {}"),
//  CONTAINER_0705("Requested number of runners {} is higher than allowed maximum of {}"),
//
//  //Pipeline Lifecycle events
//  CONTAINER_0790("Pipeline lifecycle event stage initialization error: {}"),
//  CONTAINER_0791("Pipeline lifecycle event stage run error: {}"),
//  CONTAINER_0792("Pipeline lifecycle event stage generated error record: {}"),
//
//  //Runner
//  CONTAINER_0800("Pipeline '{}' validation error : {}"),
//  CONTAINER_0801("Thread unexpectedly interrupted"),
//  CONTAINER_0802("Detected run away pipeline runners (only {} out of {} runners have finished)"),
//  CONTAINER_0803("Trying to acquire pipeline runner after the pool was destroyed."),
//
//  //PipelineConfigurationUpgrader
//  CONTAINER_0900("Error while upgrading stage configuration from version '{}' to version '{}': {}"),
//  CONTAINER_0901("Could not find stage definition for '{}:{}'"),
//  CONTAINER_0902("Stage definition '{}:{}' version '{}' is older than the version specified in the configuration '{}' for stage '{}'"),
//
//  //Email Notifier
//  CONTAINER_01000("Error loading email template, reason : {}"),
//  CONTAINER_01001("Error sending email : {}"),
//
//  // Remote Control pipelines
//  CONTAINER_01100("Cannot perform operation: '{}' on local pipeline '{}'"),
//  CONTAINER_01101("Cannot perform operation: '{}' on remote pipeline '{}'"),
//
//  // ACL
//  CONTAINER_01200("{} '{}' doesn't have permissions {} on pipeline {}"),
//  CONTAINER_01201("Only owner of the pipeline {} or admin is allowed to updated the ACL information"),
//
//  // misc
//  CONTAINER_01300("Environment variable 'STREAMSETS_LIBRARIES_EXTRA_DIR' is not set"),
//  CONTAINER_01301("Invalid library name '{}'"),
//
//  //LineageEvent problems:
//  CONTAINER_01401("Cannot create framework-level event '{}' in a stage"),
//  CONTAINER_01402("Cannot create stage-level event '{}' in the framework"),
//  CONTAINER_01403("Missing or Empty SpecificAttributes '{}'"),
//  CONTAINER_01404("Unknown LineageEventType passed to missingSpecificAttributes() '{}'"),
//
//  CONTAINER_01500("Stage '{}' configuration '{}', EL must resolve to String or to a CredentialValue resolved to '{}'"),
//
//
//  CONTAINER_01600("Download executable not supported for pipeline execution mode: '{}'"),
//  CONTAINER_01601("Start operation is not supported for pipeline '{}' with execution mode: '{}'"),
  ;

  private final String msg;

  ContainerError(String msg) {
    this.msg = msg;
  }


  @Override
  public String getCode() {
    return name();
  }

  @Override
  public String getMessage() {
    return msg;
  }

}
