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

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum CreationError implements ErrorCode {

	  CREATION_000("实例化节点失败 '{}' [错误]: {}"),
	  CREATION_001("实例化配置失败 '{}' [错误]: {}"),
	  CREATION_002("配置定义缺失 '{}', 库/节点不匹配 [错误]"),
	  CREATION_003("访问配置失败 [错误]: {}"),

	  CREATION_004("无法使用默认配置值 [错误]: {}"),

	  CREATION_005("无法解析隐式EL表达式 '{}': {}"),

	  CREATION_006("节点库缺失 '{}' 库 '{}' 版本 '{}'"),

	  CREATION_007("节点库 '{}' 节点 '{}' 版本 '{}' 仅用于错误节点"),
	  CREATION_008("节点库 '{}' 节点 '{}' 版本 '{}' 不适用于错误节点"),

	  CREATION_009("未配置任务错误处理节点"),

	  CREATION_010("配置值 '{}' 无效 '{}' 枚举值: {}"),
	  CREATION_011("配置值 '{}' 不是一个字符串, 它是 '{}'"),
	  CREATION_012("配置值 '{}' 不是一个字符型, 它是 '{}'"),
	  CREATION_013("配置值 '{}' 不是一个布尔值, 它是 '{}'"),
	  CREATION_014("配置值 '{}' 不是一个号码值, 它是 '{}'"),
	  CREATION_015("配置值 '{}' 不能转换为 '{}': {}"),

	  CREATION_016("无法获取JAVA默认值: {}"),
	  CREATION_017("节点库 '{}' 节点 '{}' 版本 '{}' 不能用于任务生命周期事件处理"),
	  CREATION_018("配置值 '{}' 应该是一个字符串或一个密码串, 实际值为 '{}'"),
	  
	  CREATION_020("配置值不是List"),
	  CREATION_021("List配置中有空值"),

	  CREATION_030("配置值不是一个Map"),
	  CREATION_031("Map配置值含有无效的值类型'{}'"),
	  CREATION_032("Map配置中键为空"),
	  CREATION_033("Map配置是值为空"),

	  CREATION_040("ComplexField配置不是一个 LIST, 它是一个 '{}'"),
	  CREATION_041("无法实例化 ComplexField 实体 '{}' [错误]: {}"),
	  CREATION_042("ComplexField 配置的值是一个无效的: {}"),
	  CREATION_043("ComplexField 不能装入到类 '{}' 在这个类中 {}"),

	  CREATION_050("配置值不能为空"),
	  CREATION_051("配置的类型 '{}' 是无效的 [错误]"),

	  CREATION_060("无法设置配置值 '{}' [错误]: {}"),

	  CREATION_070("无效的执行模式 '{}'"),
	  CREATION_071("未设置执行模式"),

	  CREATION_080("未配置Webhook地址"),
//	CREATION_000("Failed to instantiate {} '{}' [ERROR]: {}"),
//	CREATION_001("Failed to instantiate config bean '{}' [ERROR]: {}"),
//	CREATION_002("Configuration definition missing '{}', there is a library/stage mismatch [ERROR]"),
//	CREATION_003("Failed to access config bean [ERROR]: {}"),
//
//	CREATION_004("Could not set default value to configuration [ERROR]: {}"),
//
//	CREATION_005("Could not resolve implicit EL expression '{}': {}"),
//
//	CREATION_006("Stage definition not found Library '{}' Stage '{}' Version '{}'"),
//
//	CREATION_007("Stage definition Library '{}' Stage '{}' Version '{}' is for error stages only"),
//	CREATION_008("Stage definition Library '{}' Stage '{}' Version '{}' is not for error stages"),
//
//	CREATION_009("Pipeline error handling is not configured"),
//
//	CREATION_010("Configuration value '{}' is not a valid '{}' enum value: {}"),
//	CREATION_011("Configuration value '{}' is not string, it is a '{}'"),
//	CREATION_012("Configuration value '{}' is not character, it is a '{}'"),
//	CREATION_013("Configuration value '{}' is not boolean, it is a '{}'"),
//	CREATION_014("Configuration value '{}' is not number, it is a '{}'"),
//	CREATION_015("Configuration value '{}' cannot be converted to '{}': {}"),
//
//	CREATION_016("Could not obtain Java default value: {}"),
//	CREATION_017("Stage definition Library '{}' Stage '{}' Version '{}' can not be used for pipeline lifecycle event handling"),
//	CREATION_018("Configuration value '{}' should be a String or a CredentialValue, it is a '{}'"),
//
//	CREATION_020("Configuration value is not a LIST"),
//	CREATION_021("LIST configuration has a NULL value"),
//
//	CREATION_030("Configuration value is not a MAP"),
//	CREATION_031("MAP configuration value has invalid values type '{}'"),
//	CREATION_032("MAP configuration has a NULL key"),
//	CREATION_033("MAP configuration has a NULL value"),
//
//	CREATION_040("ComplexField configuration is not a LIST, it is a '{}'"),
//	CREATION_041("Failed to instantiate ComplexField bean '{}' [ERROR]: {}"),
//	CREATION_042("ComplexField configuration value is invalid: {}"),
//	CREATION_043("ComplexField could not load class '{}' in class loader {}"),
//
//	CREATION_050("Configuration value cannot be NULL"),
//	CREATION_051("Configuration type '{}' is invalid [ERROR]"),
//
//	CREATION_060("Could not set configuration value '{}' [ERROR]: {}"),
//
//	CREATION_070("Invalid execution mode '{}'"),
//	CREATION_071("Execution mode not set"),
//
//	CREATION_080("Configuration value is required for Webhook URL"),
	  ;

  private final String msg;

  CreationError(String msg) {
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
