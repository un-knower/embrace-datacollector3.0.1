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
package com.streamsets.pipeline.stage.destination.hdfs.metadataexecutor;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum HdfsMetadataErrors implements ErrorCode {
  HDFS_METADATA_000("保存元数据修改出错: {}"),
  HDFS_METADATA_001("无法解析默认Kerberos域, 必须将HDFS主节点名设置为'dfs.namenode.kerberos.principal'属性: {}"),
  HDFS_METADATA_002("Hadoop配置目录'{}'不存在"),
  HDFS_METADATA_003("Hadoop配置目录'{}'不是目录"),
  HDFS_METADATA_004("Hadoop配置文件'{}'不是文件"),
  HDFS_METADATA_005("配置或连接Hadoop文件系统失败: {}"),
  HDFS_METADATA_006("不能同时设置权限和ACLs. 使用ACLs也能设置标准权限."),
  HDFS_METADATA_007("无效的EL表达式: {}"),
//  HDFS_METADATA_000("Error when applying metadata changes: {}"),
//  HDFS_METADATA_001("Could not resolve the default Kerberos realm, you must set the 'dfs.namenode.kerberos.principal' property to the HDFS principal name: {}"),
//  HDFS_METADATA_002("Hadoop configuration directory '{}' does not exist"),
//  HDFS_METADATA_003("Hadoop configuration directory '{}' is not a directory"),
//  HDFS_METADATA_004("Hadoop configuration file '{}' is not a file"),
//  HDFS_METADATA_005("Failed to configure or connect to the Hadoop file system: {}"),
//  HDFS_METADATA_006("Can't set permissions and ACLs at the same time. Use ACLs to set standard permissions as well."),
//  HDFS_METADATA_007("Invalid EL expression: {}"),
  ;

  private final String msg;

  HdfsMetadataErrors(String msg) {
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
