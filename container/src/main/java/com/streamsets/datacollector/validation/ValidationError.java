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
package com.streamsets.datacollector.validation;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

// we are using the annotation for reference purposes only.
// the annotation processor does not work on this maven project
// we have a hardcoded 'datacollector-resource-bundles.json' file in resources
@GenerateResourceBundle
public enum ValidationError implements ErrorCode {
	VALIDATION_0000("不支持的任务模式版本"),

	  VALIDATION_0001("这个任务是空的!"),
	  VALIDATION_0002("任务包括未连接的阶段，不能达到此阶段"),
	  VALIDATION_0003("第一阶段必须是起源"),
	  VALIDATION_0004("这个阶段不能成为起源"),
	  VALIDATION_0005("节点名称已经定义"),
	  VALIDATION_0006("节点定义不存在, 库 '{}', 名字 '{}', 版本 '{}'"),
	  VALIDATION_0007("此选项是必填的"),
	  VALIDATION_0008("无效的配置"),
	  VALIDATION_0009("配置应该为'{}'"),
	  VALIDATION_0010("输出流'{}'已在'{}'阶段来定义"),
	  VALIDATION_0011("此节点未配置输出流"),
	  // Issues are augmented with an array of the open streams in the additionalInfo map property

	  VALIDATION_0012("不能有输入流"),
	  VALIDATION_0013("不能有输出流"),
	  VALIDATION_0014("必须有输入流"),
	  VALIDATION_0015("此阶段必须保存'{}'内，输出流有 '{}'"),
	  VALIDATION_0016("无效的此阶段的名字,名称可以包括以下字符 '{}'"),
	  VALIDATION_0017("无效的输入的流的名字， 名称可以包括以下字符 '{}'"),
	  VALIDATION_0018("无效的输出的流的名字， 名称可以包括以下字符 '{}'"),

	  VALIDATION_0019("索引流条件 '{}' 不是一个map"),
	  VALIDATION_0020("索引流条件 '{}' 必须有一个 '{}' 项"),
	  VALIDATION_0021("索引流条件 '{}' 项 '{}' 不能为空"),
	  VALIDATION_0022("索引流条件 '{}' 项 '{}' 必须是字符串"),
	  VALIDATION_0023("索引流条件 '{}' 项 '{}' 不能为空"),
	  
	  VALIDATION_0024("配置Map类型为键值对列表时不能包含有空元素 列表中元素索引项 '{}' 为空"),
	  VALIDATION_0025("配置Map类型为键值对列表时必须有键和值" +
	                  "键和值包含在列表的Map元素中. 索引中的元素 '{}' 不包含这两项"),
	  VALIDATION_0026("配置Map类型为键值对列表时列表中必须包含Map项,元素在索引处 '{}' 有 '{}'"),

	  VALIDATION_0027("数据规则 '{}' 指向的一个流 '{}' 没有在任务配置中找到"),
	  VALIDATION_0028("数据规则 '{}' 指向的一个阶段 '{}' 没有在任务配置中找到"),

	  VALIDATION_0029("配置必须是一个字符串, 而不是一个 '{}'"),
	  VALIDATION_0030("表达式的值 '{}' 必须包含在 '${...}'"),

	  VALIDATION_0031("属性 '{}' 必须是单个字符"),
	  VALIDATION_0032("此阶段必须至少有一个输出流"),
	  VALIDATION_0033("无效的配置, {}"),

	  VALIDATION_0034("配置项 '{}' 不能大于 '{}'"),
	  VALIDATION_0035("配置项 '{}' 不能小于 '{}'"),
	  VALIDATION_0036("{} 不能有事件流 '{}'"),
	  //Rule Validation Errors
	  VALIDATION_0040("数据规则属性 '{}' 必须定义"),
	  VALIDATION_0041("抽样百分比属性必须有一个介于0到100的值"),
	  VALIDATION_0042("电子警报已启用, 但是没有指定电子邮件"),
	  VALIDATION_0043("阈值定义的值不是数字"),
	  VALIDATION_0044("阈值属性必须有一个介于0到100的值"),
	  VALIDATION_0045("此项 '{}' 定义对于数据规则是无效的: {}"),
	  VALIDATION_0046("此项必须使用以下的格式: '${value()<operator><number>}'"),
	  VALIDATION_0047("此项 '{}' 定义对于公制警报是无效的"),
	  VALIDATION_0050("此属性 '{}' 必须为公制警报定义"),

	  VALIDATION_0060("定义任务操作的错误记录"),
	  VALIDATION_0061("定义错误记录文件的目录"),
	  VALIDATION_0062("配置的内存限制 '{}' 不是一个整数"),
	  VALIDATION_0063("配置的内存限制 '{}' 大于允许的最大值 '{}'"),
	  VALIDATION_0064("解析内存限制出错: {}"),

	  VALIDATION_0070("任务没有定义其执行模式"),
	  VALIDATION_0071("阶段 '{}' 来自于 '{}' 库不支持 '{}' 执行模式"),
	  VALIDATION_0072("数据采集器处于单机模式, 无法运行任务集群模式"),
	  VALIDATION_0073("数据采集器处于集群模式, 无法运行任务单机模式"),
	  VALIDATION_0074("错误阶段 '{}' 来自于 '{}' 库不支持的 '{}' 执行模式"),
	  VALIDATION_0080("预处理 '{}' 必须以 '${' 开始，以 '}' 结束"),
	  VALIDATION_0081("无效预处理 '{}': {}"),
	  VALIDATION_0082("无法使用执行模式创建运行器 '{}', 另外一个使用执行模式的运行器 '{}'是活动的"),
	  VALIDATION_0090("验证配置时遇到异常 : {}"),
	  VALIDATION_0091("发现多个触发偏移提交的目标阶段"),
	  VALIDATION_0092("传送担保只能用于 {} 如果任务包含触发偏移提交的操作"),
	  VALIDATION_0093("任务标题为空"),

	  // Event related validations
	  VALIDATION_0100("无效的事件流名称 '{}'. 流可以包含以下字符 '{}'"),
	  VALIDATION_0101("此阶段包含不止一个事件行."),
	  VALIDATION_0102("此阶段已经配置了事件行即使它不产生事件."),
	  VALIDATION_0103("此阶段 '{}' 已经合并了任务的数据和事件的部分输入数据."),
	  VALIDATION_0104("此阶段有开放的事件流"),
      VALIDATION_0105("无效的任务生命周期规范: {}"),
      VALIDATION_0106("任务生命周期事件在模式中不受支持: {}"),

     // Service related validations
      VALIDATION_0200("无效的服务声明, 预期的定义 '{}', 但有'{}'"),
     ;
//	VALIDATION_0000("Unsupported pipeline schema version '{}'"),
//
//	VALIDATION_0001("The pipeline is empty"),
//	VALIDATION_0002("The pipeline includes unconnected stages. Cannot reach the following stages: {}"),
//	VALIDATION_0003("The first stage must be an origin"),
//	VALIDATION_0004("This stage cannot be an origin"),
//	VALIDATION_0005("Stage name already defined"),
//	VALIDATION_0006("Stage definition does not exist, library '{}', name '{}', version '{}'"),
//	VALIDATION_0007("Configuration value is required"),
//	VALIDATION_0008("Invalid configuration: '{}'"),
//	VALIDATION_0009("Configuration should be a '{}'"),
//	VALIDATION_0010("Output streams '{}' are already defined by stage '{}'"),
//	VALIDATION_0011("Stage has open output streams"),
//	// Issues are augmented with an array of the open streams in the additionalInfo map property
//
//	VALIDATION_0012("{} cannot have input streams '{}'"),
//	VALIDATION_0013("{} cannot have output streams '{}'"),
//	VALIDATION_0014("{} must have input streams"),
//	VALIDATION_0015("Stage must have '{}' output stream(s) but has '{}'"),
//	VALIDATION_0016("Invalid stage name. Names can include the following characters '{}'"),
//	VALIDATION_0017("Invalid input stream names '{}'. Streams can include the following characters '{}'"),
//	VALIDATION_0018("Invalid output stream names '{}'. Streams can include the following characters '{}'"),
//
//	VALIDATION_0019("Stream condition at index '{}' is not a map"),
//	VALIDATION_0020("Stream condition at index '{}' must have a '{}' entry"),
//	VALIDATION_0021("Stream condition at index '{}' entry '{}' cannot be NULL"),
//	VALIDATION_0022("Stream condition at index '{}' entry '{}' must be a string"),
//	VALIDATION_0023("Stream condition at index '{}' entry '{}' cannot be empty"),
//
//	VALIDATION_0024("Configuration of type Map expressed as a list of key/value pairs cannot have null elements in the " +
//							"list. The element at index '{}' is NULL."),
//	VALIDATION_0025("Configuration of type Map expressed as a list of key/value pairs must have the 'key' and 'value'" +
//							"entries in the List's Map elements. The element at index '{}' is does not have those 2 entries"),
//	VALIDATION_0026("Configuration of type Map expressed as List of key/value pairs must have Map entries in the List," +
//							"element at index '{}' has '{}'"),
//
//	VALIDATION_0027("Data rule '{}' refers to a stream '{}' which is not found in the pipeline configuration"),
//	VALIDATION_0028("Data rule '{}' refers to a stage '{}' which is not found in the pipeline configuration"),
//
//	VALIDATION_0029("Configuration must be a string, instead of a '{}'"),
//	VALIDATION_0030("The expression value '{}' must be within '${...}'"),
//
//	VALIDATION_0031("The property '{}' should be a single character"),
//	VALIDATION_0032("Stage must have at least one output stream"),
//	VALIDATION_0033("Invalid Configuration, {}"),
//
//	VALIDATION_0034("Value for configuration '{}' cannot be greater then '{}'"),
//	VALIDATION_0035("Value for configuration '{}' cannot be less then '{}'"),
//	VALIDATION_0036("{} cannot have event streams '{}'"),
//
//	//Rule Validation Errors
//	VALIDATION_0040("The data rule property '{}' must be defined"),
//	VALIDATION_0041("The Sampling Percentage property must have a value between 0 and 100"),
//	VALIDATION_0042("Email alert is enabled, but no email is specified"),
//	VALIDATION_0043("The value defined for Threshold Value is not a number"),
//	VALIDATION_0044("The Threshold Value property must have a value between 0 and 100"),
//	VALIDATION_0045("The condition '{}' defined for the data rule is not valid: {}"),
//	VALIDATION_0046("The condition must use the following format: '${value()<operator><number>}'"),
//	VALIDATION_0047("The condition '{}' defined for the metric alert is not valid"),
//	VALIDATION_0050("The property '{}' must be defined for the metric alert"),
//	VALIDATION_0051("Unsupported rule definition schema version '{}'"),
//
//	VALIDATION_0060("Define the error record handling for the pipeline"),
//	VALIDATION_0061("Define the directory for error record files"),
//	VALIDATION_0062("Configured memory limit '{}' is not an integer"),
//	VALIDATION_0063("Configured memory limit '{}' is above the maximum allowed '{}'"),
//	VALIDATION_0064("Error resolving memory limit: {}"),
//
//	VALIDATION_0070("Pipeline does not define its execution mode"),
//	VALIDATION_0071("Stage '{}' from '{}' library does not support '{}' execution mode"),
//	VALIDATION_0072("Data collector is in standalone mode, cannot run pipeline cluster mode"),
//	VALIDATION_0073("Data collector is in cluster mode, cannot run pipeline standalone mode"),
//	VALIDATION_0080("Precondition '{}' must begin with '${' and end with '}'"),
//	VALIDATION_0081("Invalid precondition '{}': {}"),
//	VALIDATION_0082("Cannot create runner with execution mode '{}', another runner with execution mode '{}'"
//							+ " is active"),
//	VALIDATION_0090("Encountered exception while validating configuration : {}"),
//	VALIDATION_0091("Found more than one Target stage that triggers offset commit"),
//	VALIDATION_0092("Delivery Guarantee can only be {} if pipeline contains a Target that triggers offset commit"),
//	VALIDATION_0093("The pipeline title is empty"),
//
//	// Event related validations
//	VALIDATION_0100("Invalid event stream name '{}'. Streams can include the following characters '{}'"),
//	VALIDATION_0101("Stage have more then one event lane."),
//	VALIDATION_0102("Stage have configured event lane even though that it doesn't produce events."),
//	VALIDATION_0103("Stage '{}' have merged input from both data and event part of the pipeline."),
//	VALIDATION_0104("Stage has open event streams"),
//	VALIDATION_0105("Invalid pipeline lifecycle specification: {}"),
//	VALIDATION_0106("Pipeline lifecycle events are not supported in mode: {}"),
//
//	// Service related validations
//	VALIDATION_0200("Invalid services declaration, expected definition for '{}', but got '{}'"),
//	;

  private final String msg;

  ValidationError(String msg) {
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
