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
package com.streamsets.datacollector.pipeline.executor.spark;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {

  SPARK_EXEC_00("加载应用程序失败,异常: '{}'"),
  SPARK_EXEC_01("未设置SPARK_HOME. " +
      "请设置SPARK_HOME环境变量或配置参数"),
  SPARK_EXEC_02("未设置JAVA_HOME. " +
      "请设置JAVA_HOME环境变量或配置参数"),
  SPARK_EXEC_03("'{}'不是有效的内存参数. " +
      "有效的内存参数格式:数字+'g'/'G'/'m'/'M'/'k'/'K'. 例如: 4048m"),
  SPARK_EXEC_04("文件: '{}'不存在, 或该用户无法读取"),
  SPARK_EXEC_05("设置凭证时必须指定密钥表"),
  SPARK_EXEC_06("在等待作业完成时被中断"),
  SPARK_EXEC_07("系统JAVA_HOME: '{}'不存在, 或该用户无法读取"),
  SPARK_EXEC_08("系统SPARK_HOME: '{}'不存在, 或该用户无法读取"),
  SPARK_EXEC_09("键期望的值: '{}'"),
  SPARK_EXEC_10("值期望的键: '{}'"),
  SPARK_EXEC_11("ID为'{}'的作业不存在"),
  SPARK_EXEC_12("地址无效"),
  SPARK_EXEC_13("请求作业列表时出错"),
  SPARK_EXEC_14("ID为'{}'的作业的作业类型错误. 期望值: '{}', 实际值: '{}'"),
  SPARK_EXEC_15("无效的凭证"),
  SPARK_EXEC_16("ID为'{}'的作业运行失败,异常: '{}'"),
//  SPARK_EXEC_00("Application Launch failed due to exception: '{}'"),
//  SPARK_EXEC_01("SPARK_HOME is not set. " +
//      "Please set SPARK_HOME environment variable or this configuration parameter"),
//  SPARK_EXEC_02("JAVA_HOME is not set. " +
//      "Please set JAVA_HOME environment variable or this configuration parameter"),
//  SPARK_EXEC_03("'{}' is not a valid memory string. " +
//      "Valid memory strings are an integer followed by 'g', 'G', 'm', 'M', 'k' or 'K'. Example: 4048m"),
//  SPARK_EXEC_04("File: '{}' does not exist, or cannot be read by this user"),
//  SPARK_EXEC_05("Keytab is required when principal is specified"),
//  SPARK_EXEC_06("Interrupted while waiting for job to complete"),
//  SPARK_EXEC_07("System JAVA_HOME: '{}' does not exist, or cannot be read by this user"),
//  SPARK_EXEC_08("System SPARK_HOME: '{}' does not exist, or cannot be read by this user"),
//  SPARK_EXEC_09("Value expected for key: '{}'"),
//  SPARK_EXEC_10("Key expected for value: '{}'"),
//  SPARK_EXEC_11("Job with ID: '{}' does not exist"),
//  SPARK_EXEC_12("Base URL is invalid"),
//  SPARK_EXEC_13("Error while requesting job listing"),
//  SPARK_EXEC_14("Incorrect job type for job ID: '{}'. Expected: '{}', found: '{}'"),
//  SPARK_EXEC_15("Invalid credentials"),
//  SPARK_EXEC_16("Running Job with ID: '{}' failed with error: '{}'"),
  ;

  private final String msg;

  Errors(String msg) {
    this.msg = msg;
  }

  /** {@inheritDoc} */
  @Override
  public String getCode() {
    return name();
  }

  /** {@inheritDoc} */
  @Override
  public String getMessage() {
    return msg;
  }
}
