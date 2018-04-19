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
package com.streamsets.pipeline.stage.origin.tcp;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;


@GenerateResourceBundle
public enum Errors implements ErrorCode {
  TCP_00("无法绑定端口{}: {}"),
  TCP_01("位置的TCP模式: {}"),
  TCP_02("未指定端口"),
  TCP_03("端口'{}'无效"),
  TCP_04("侦听特权端口权限不足{}"),
  TCP_05("TCP服务器的本地传输在平台上不可用."),
  TCP_06("节点配置导致任务失败: {}"),
  TCP_07("Netty通道抛出异常{}，任务: {}"),
  TCP_08("Netty通道任务的DataFormatParserDecoder抛出DataParserException异常: {}"),
  TCP_09("没有可供TCP服务器监听的地址"),
  TCP_10("无法识别的字符集: {}"),
  TCP_20("未知的Syslog消息构架模式: {}"),
  TCP_30("记录处理应答消息表达式无效\"{}\"，消息: {}"),
  TCP_31("批处理完成应答消息表达式无效\"{}\"，消息: {}"),
  TCP_35("评估{}表达式错误: {}"),
  TCP_40("解释分隔符后的空结果（即字节长度为0）"),
  TCP_41("未指定分隔字符串表达式"),
//  TCP_00("Cannot bind to port {}: {}"),
//  TCP_01("Unknown TCP mode: {}"),
//  TCP_02("No ports specified"),
//  TCP_03("Port '{}' is invalid"),
//  TCP_04("Insufficient permissions to listen on privileged port {}"),
//  TCP_05("Native transports for TCP server are not available on your platform."),
//  TCP_06("Failing pipeline on error as per stage configuration: {}"),
//  TCP_07("{} thrown in Netty channel pipeline: {}"),
//  TCP_08("DataParserException thrown in Netty channel pipeline from DataFormatParserDecoder: {}"),
//  TCP_09("No addresses available for TCP server to listen on"),
//  TCP_10("Unrecognized charset: {}"),
//  TCP_20("Unknown Syslog message framing mode: {}"),
//  TCP_30("Invalid expression \"{}\" for record processed ack message: {}"),
//  TCP_31("Invalid expression \"{}\" for batch completed ack message: {}"),
//  TCP_35("Error evaluating {} expression: {}"),
//  TCP_40("Empty result (i.e. length of bytes was zero) after interpreting separator"),
//  TCP_41("Separator string expression was not specified"),
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
