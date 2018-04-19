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
package com.streamsets.pipeline.stage.origin.hdfs.cluster;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {

  HADOOPFS_00("Hadoop的UserGroupInformation认证'{}' 应为 '{}'"),
  HADOOPFS_02("Hadoop FS地址必须以<scheme>://<path>开头"),
  HADOOPFS_08("无法解析记录'{}': {}"),
  HADOOPFS_09("无法获取分割值'{}': {}"),
  HADOOPFS_10("Hadoop FS位置不存在: '{}'"),
  HADOOPFS_11("无法连接到文件系统。检查Hadoop FS位置：“{}”是否有效：“{}”"),
  HADOOPFS_19("Hadoop FS地址未设置且无法通过集群配置获取"),
  HADOOPFS_13("地址权限不能为空"),
  HADOOPFS_15("Hadoop FS位置不是一个目录：“{}”"),
  HADOOPFS_16("读文件出错'{}',原因:'{}'" ),
  HADOOPFS_17("目录'{}'下无文件"),
  HADOOPFS_18("未指定目录"),
  HADOOPFS_22("无效的地址'{}': {}"),
  HADOOPFS_25("Hadoop配置目录'{}'不存在"),
  HADOOPFS_26("Hadoop配置目录'{}'不是目录"),
  HADOOPFS_27("Hadoop配置文件'{}'不是文件"),
  HADOOPFS_28("无法解析默认Kerberos域, 需要设置'dfs.namenode.kerberos.principal'为Hadoop FS主名称: {}"),
  HADOOPFS_29("Hadoop的配置目录“{}”（解析为“{}”）不在数据采集器资源目录“{}”。"),
  HADOOPFS_30("Hadoop配置文件'{}'不存在"),
  HADOOPFS_31("未指定Hadoop配置文件core-site.xml, hdfs-site.xml, yarn-site.xml和mapred-site.xml所在目录");
//  HADOOPFS_00("Hadoop UserGroupInformation reports '{}' authentication, it should be '{}'"),
//  HADOOPFS_02("Hadoop FS URI must start with <scheme>://<path>"),
//  HADOOPFS_08("Cannot parse record '{}': {}"),
//  HADOOPFS_09("Cannot obtain splits for '{}': {}"),
//  HADOOPFS_10("Hadoop FS location doesn't exist: '{}'"),
//  HADOOPFS_11("Cannot connect to the filesystem. Check if the Hadoop FS location: '{}' is valid or not: '{}'"),
//  HADOOPFS_19("Hadoop FS URI is not set and is also not available through the cluster configs"),
//  HADOOPFS_13("Authority of URI cannot be null"),
//  HADOOPFS_15("Hadoop FS location is not a directory: '{}'"),
//  HADOOPFS_16("Error reading file '{}' due to '{}'" ),
//  HADOOPFS_17("Directory '{}' doesn't have any files"),
//  HADOOPFS_18("No directories specified"),
//  HADOOPFS_22("Invalid URI '{}': {}"),
//  HADOOPFS_25("Hadoop configuration directory '{}' does not exist"),
//  HADOOPFS_26("Hadoop configuration directory '{}' is not a directory"),
//  HADOOPFS_27("Hadoop configuration file '{}'  is not a file"),
//  HADOOPFS_28("Could not resolve the default Kerberos realm, you must set the 'dfs.namenode.kerberos.principal' " +
//    "property to the Hadoop FS principal name: {}"),
//  HADOOPFS_29("Hadoop configuration directory '{}' (resolved to '{}') is not inside SDC resources directory '{}'."),
//  HADOOPFS_30("Hadoop configuration file '{}' does not exist"),
//  HADOOPFS_31("Hadoop configuration dir with config files core-site.xml, hdfs-site.xml, yarn-site.xml and " +
//      "mapred-site.xml is required");
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
