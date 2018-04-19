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
package com.streamsets.pipeline.stage.processor.scripting;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
//  SCRIPTING_00("Scripting engine '{}' not found"),
//  SCRIPTING_01("Could not load scripting engine '{}': {}"),
//  SCRIPTING_02("Script cannot be empty"),
//  SCRIPTING_03("Script failed to compile: '{}'"),
//  SCRIPTING_04("Script sent record to error: {}"),
//  SCRIPTING_05("Script error while processing record: {}"),
//  SCRIPTING_06("Script error while processing batch: {}"),
//  SCRIPTING_07("Sending normal record to event stream: {}"),
//  SCRIPTING_08("Script error while running init script: {}"),
//  SCRIPTING_09("Script error while running destroy script: {}"),
  SCRIPTING_00("未找到'{}'脚本引擎"),
  SCRIPTING_01("无法加载脚本引擎'{}': {}"),
  SCRIPTING_02("脚本不能为空"),
  SCRIPTING_03("编译脚本失败: '{}'"),
  SCRIPTING_04("发送到异常处理: {}"),
  SCRIPTING_05("处理记录时脚本执行异常: {}"),
  SCRIPTING_06("批处理脚本执行异常: {}"),
  SCRIPTING_07("发送正常记录至事件流: {}"),
  SCRIPTING_08("初始化脚本运行异常: {}"),
  SCRIPTING_09("销毁脚本运行异常: {}"),
  ;
  private final String msg;

  Errors(String msg) {
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
