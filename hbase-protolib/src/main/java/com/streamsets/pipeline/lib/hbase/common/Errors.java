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
package com.streamsets.pipeline.lib.hbase.common;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  HBASE_00("Hadoop UserGroupInformation报告简单认证, 应该使用Kerberos"),
  HBASE_01("配置或连接HBase'{}'失败: {}"),
  HBASE_02("向HBase提交批处理出错"),
  HBASE_04("Zookeeper配置中成员不能为空"),
  HBASE_05("表名不能为空"),
  HBASE_06("无法连接集群: {}"),
  HBASE_07("表名不存在: {}"),
  HBASE_08("表已禁用: {}"),
  HBASE_09("Zookeeper根节点不能为空"),
  HBASE_10("写记录失败: {}"),
  HBASE_11("无法解析族和限定符，记录：{}，行键：{}"),
  HBASE_12("无法进行类型转换: {} 至 {}"),
  HBASE_13("Zookeeper客户端端口无效: {}"),
  HBASE_14("无效的行键存储类型: {}"),
  HBASE_15("无效的行存储类型: {}"),
  HBASE_16("Hadoop UserGroupInformation应返回Kerberos认证, 其设置为: {}"),
  HBASE_17("配置或连接HBase集群失败: {}"),
  HBASE_18("应定义HBase列映射或启用隐式字段映射"),
  HBASE_19("数据采集器资源目录下不存在HBase配置目录'{}'"),
  HBASE_20("数据采集器资源目录下的HBase配置目录 '{}' 不是一个目录"),
  HBASE_21("数据采集器资源目录下的HBase配置文件'{}/{}' 不是一个文件"),
  HBASE_22("无法解析默认Kerberos域，必须设置HBase的Master节点的'hbase.master.kerberos.principal'属性{}"),
  HBASE_23("无法解析默认Kerberos域，必须设置HBase的RegionServer的'hbase.regionserver.kerberos.principal'属性{}"),
  HBASE_24("集群模式下，HBase配置目录'{}' 须是相对目录"),
  HBASE_25("记录中列字段缺失'{}'"),
  HBASE_26("向HBase写入出错: '{}'"),
  HBASE_27("记录中行键缺失 '{}'"),
  HBASE_28("无法从'{}'构造HBase列，因其被 '{}' 分割"),
  HBASE_29("记录根类型: '{}' 不合法，无法插入HBase; 根类型必须是MAP或LIST_MAP"),
  HBASE_30("所有字段遇到错误: '{}'"),
  HBASE_31("从'{}'转换至'{}'出错"),
  HBASE_32("列族'{}'在表'{}'中不存在"),
  HBASE_33("无效的时间驱动表达式"),
  HBASE_34("无法评估时间驱动表达式: {}"),
  HBASE_35("行键字段为空值"),
  HBASE_36("读HBase出错: '{}'"),
  HBASE_37("从HBase读取无效列出错: '{}'"),
  HBASE_38("无法评估表达式: '{}'"),
  HBASE_39("无法从Zookeeper成员'{}'中选举出主机"),
  HBASE_40("输出字段含有空值"),
  HBASE_41("记录上{}无主键，主键:'{}', 列:'{}', 时间戳:'{}'"),
//  HBASE_00("Hadoop UserGroupInformation reports Simple authentication, it should be Kerberos"),
//  HBASE_01("Failed to configure or connect to the '{}' HBase: {}"),
//  HBASE_02("Error while writing batch of records to HBase"),
//  HBASE_04("Zookeeper quorum cannot be empty"),
//  HBASE_05("Table name cannot be empty "),
//  HBASE_06("Cannot connect to cluster: {}"),
//  HBASE_07("Table name doesn't exist: {}"),
//  HBASE_08("Table is not enabled: {}"),
//  HBASE_09("Zookeeper root znode cannot be empty "),
//  HBASE_10("Failed writing record: {}"),
//  HBASE_11("Cannot parse family and qualifier for record: {} and row key: {}"),
//  HBASE_12("Cannot convert type: {} to {}"),
//  HBASE_13("Zookeeper client port is invalid: {}"),
//  HBASE_14("Invalid row key storage type: {}"),
//  HBASE_15("Invalid column storage type: {}"),
//  HBASE_16("Hadoop UserGroupInformation should return Kerberos authentication, it is set to: {}"),
//  HBASE_17("Failed to configure or connect to the HBase cluster: {}"),
//  HBASE_18("HBase column mapping should be defined or implicit field mapping should be enabled"),
//  HBASE_19("HBase configuration directory '{}' under SDC resources does not exist"),
//  HBASE_20("HBase configuration directory '{}' path under  SDC resources is not a directory"),
//  HBASE_21("HBase configuration file '{}/{}' under SDC resources is not a file"),
//  HBASE_22("Could not resolve the default Kerberos realm, you must set the 'hbase.master.kerberos.principal' " +
//    "property to the HBase master principal name: {}"),
//  HBASE_23("Could not resolve the default Kerberos realm, you must set the 'hbase.regionserver.kerberos.principal' " +
//    "property to the HBase RegionServer principal name: {}"),
//  HBASE_24("HBase Configuration Directory '{}' must be relative to SDC resources directory in cluster mode"),
//  HBASE_25("Missing column field '{}' in record"),
//  HBASE_26("Error while writing to HBase: '{}'"),
//  HBASE_27("Missing row key field '{}' in record"),
//  HBASE_28("Cannot construct HBase column from '{}' as it is not separated by '{}'"),
//  HBASE_29("Record root type: '{}' is not eligible to be inserted in HBase; Root type must be a MAP or LIST_MAP "),
//  HBASE_30("All fields encountered error: '{}'"),
//  HBASE_31("Error converting '{}' to '{}'"),
//  HBASE_32("Column family '{}' doesn't exist for table '{}'"),
//  HBASE_33("Invalid time driver expression"),
//  HBASE_34("Could not evaluate time driver expression: {}"),
//  HBASE_35("Row key field has empty value"),
//  HBASE_36("Error while reading from HBase: '{}'"),
//  HBASE_37("Error while reading invalid column from HBase: '{}'"),
//  HBASE_38("Could not evaluate expression: '{}'"),
//  HBASE_39("Cannot resolve host '{}' from ZooKeeper quorum"),
//  HBASE_40("Output field has empty value"),
//  HBASE_41("No key on Record '{}' with key:'{}', column:'{}', timestamp:'{}'"),
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
