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
package com.streamsets.pipeline.lib.jms.config;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum JmsErrors implements ErrorCode {
  // Configuration errors
  JMS_00("无法创建初始化上下文'{}'，提供的地址：'{}' : {}"),
  JMS_01("无法创建连接工厂 '{}' : {}"),
  JMS_02("无法创建连接 '{}': {}"),
  JMS_03("无法使用'{}'及凭证: {}创建连接"),
  JMS_04("无法启动连接: {}"),
  JMS_05("无法找到目标 '{}': {}"),
  JMS_06("无法创建会话: {}"),
  JMS_07("消息消费异常已消除: {}"),
  JMS_08("提交出错: {}"),
  JMS_09("回滚出错: {}"),
  JMS_10("未知的消息类型 '{}'"),
  JMS_11("无法创建消费者: {}"),
  JMS_12("无法写入记录: {}"),
  JMS_13("无法生成记录: {}"),
  JSM_14("无法创建生产者: {}"),
//  JMS_00("Could not create initial context '{}' with provider URL '{}' : {}"),
//  JMS_01("Could not create connection factory '{}' : {}"),
//  JMS_02("Unable to create connection using '{}': {}"),
//  JMS_03("Unable to create connection using '{}' with credentials: {}"),
//  JMS_04("Unable to start connection: {}"),
//  JMS_05("Unable to find destination '{}': {}"),
//  JMS_06("Unable to create session: {}"),
//  JMS_07("Error relieved on message consume: {}"),
//  JMS_08("Commit threw error: {}"),
//  JMS_09("Rollback threw error: {}"),
//  JMS_10("Unknown message type '{}'"),
//  JMS_11("Unable to create consumer: {}"),
//  JMS_12("Could not write record: {}"),
//  JMS_13("Could not produce message: {}"),
//  JSM_14("Can't create producer: {}"),
  ;
  private final String msg;

  JmsErrors(String msg) {
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
