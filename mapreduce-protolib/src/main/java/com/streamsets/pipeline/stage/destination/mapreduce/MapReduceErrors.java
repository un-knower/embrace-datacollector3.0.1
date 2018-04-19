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
package com.streamsets.pipeline.stage.destination.mapreduce;

import com.streamsets.pipeline.api.ErrorCode;

public enum MapReduceErrors implements ErrorCode {
  MAPREDUCE_0000("未知错误: {}"),
  MAPREDUCE_0001("无法加载类'{}'"),
  MAPREDUCE_0002("配置文件{}不存在"),
  MAPREDUCE_0003("配置目录{}不存在"),
  MAPREDUCE_0004("类{}未实现JobCreator接口"),
  MAPREDUCE_0005("无法提交并行计算作业: {}"),
  MAPREDUCE_0006("Hadoop的UserGroupInformation呈报'{}'认证, 应为：'{}'"),
//  MAPREDUCE_0000("Unexpected error: {}"),
//  MAPREDUCE_0001("Can't load class '{}'"),
//  MAPREDUCE_0002("Configuration file {} doesn't exists"),
//  MAPREDUCE_0003("Configuration directory {} doesn't exists"),
//  MAPREDUCE_0004("Class {} doesn't implement JobCreator interface"),
//  MAPREDUCE_0005("Can't submit MapReduce job: {}"),
//  MAPREDUCE_0006("Hadoop UserGroupInformation reports '{}' authentication, it should be '{}'"),
  ;

  private final String msg;

  MapReduceErrors(String msg) {
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
