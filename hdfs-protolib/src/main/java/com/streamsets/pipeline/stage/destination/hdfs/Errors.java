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
package com.streamsets.pipeline.stage.destination.hdfs;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  HADOOPFS_00("Hadoop UserGroupInformation应返回Kerberos认证, 其设置为: {}"),
  HADOOPFS_01("配置或连接'{}' Hadoop文件系统失败: {}"),
  HADOOPFS_02("无效的目录模板 '{}', {}"),

  HADOOPFS_03("序列文件键表达式'{}'无效: {}"),
    /* LC - okay to add "valid" below? */
  HADOOPFS_04("自定义压缩编解码器“{}”无效"),
  HADOOPFS_05("自定义压缩编解码器'{}'无法加载: {}"),
  HADOOPFS_06("延迟记录时限表达式'{}'无效: {}"),
  HADOOPFS_07("时间驱动表达式'{}'无效: {}"),
  HADOOPFS_08("最大文件大小必须是正整数或零，以退出选项"),
  HADOOPFS_09("文件中的最大记录必须是正整数或零，以退出选项"),
  HADOOPFS_10("延迟记录时限表达式必须大于零"),

  HADOOPFS_11("无法初始化写管理器: {}"),

  HADOOPFS_12("记录'{}'延迟到达"),
  HADOOPFS_13("写入HDFS出错: {}"),
  HADOOPFS_14("无法写记录: {}"),
  HADOOPFS_15("异常记录: {}"),
  HADOOPFS_16("不支持的数据格式 '{}'"),
  HADOOPFS_17("不能初始化延迟记录写管理器: {}"),
  HADOOPFS_18("地址'{}'必须以<scheme>://<path>开头"),
  HADOOPFS_19("无效的时间基准表达式'{}': {}"),
  HADOOPFS_20("无效的目录模板: {}"),
  HADOOPFS_21("无效的延迟记录目录模板: {}"),
  HADOOPFS_22("无效的地址'{}': {}"),
  HADOOPFS_23("无法提交历史文件: {}"),

  HADOOPFS_24("无法评估目录路径的EL表达式: {}"),

  HADOOPFS_25("Hadoop配置目录'{}'不存在"),
  HADOOPFS_26("Hadoop配置目录'{}'不是文件目录"),
  HADOOPFS_27("Hadoop配置文件'{}'不是文件"),
  HADOOPFS_28("无法解析默认Kerberos域，必须设置HDFS的主节点的'dfs.namenode.kerberos.principal'属性{}"),

  HADOOPFS_29("路径模板使用的'{}'函数必须是'{}'函数"),
  HADOOPFS_30("'every(<UNIT>, <VALUE>)'函数仅能在路径中使用一次"),
  HADOOPFS_31("'every(<UNIT>, <VALUE>)'函数中的<UNIT>必须使用hh(), mm()或ss()"),
  HADOOPFS_32("'every(<UNIT>, <VALUE>)'函数中<VALUE>参数超出限制, 其必须在'1'和'{}'之间"),
  HADOOPFS_33("'every(<UNIT>, <VALUE>)'函数必须在路径模板中使用最小的单位"),
  HADOOPFS_34("'every(<UNIT>, <VALUE>)'函数值必须是<UNIT>最大值的子倍数"),
  HADOOPFS_35("无法从路径模板中检索增量时间单元和值：{}"),
  HADOOPFS_36("'ss()'函数不能在'every()'函数内部和外部同时使用"),
  HADOOPFS_37("'mm()'函数不能在'every()'函数内部和外部同时使用"),
  HADOOPFS_38("'hh()'函数不能在'every()'函数内部和外部同时使用"),

  HADOOPFS_40("基准目录路径必须是绝对路径"),
  HADOOPFS_41("无法创建基准目录路径"),
  HADOOPFS_42("无法创建基准目录路径: '{}'"),
  HADOOPFS_43("无法在目录'{}'中创建文件/目录"),
  HADOOPFS_44("无法验证基准目录: '{}'"),
  HADOOPFS_45("集群模式下，Hadoop配置目录'{}'必须是数据采集器资源目录下的相对路径"),

  HADOOPFS_46("压缩编码器'{}'需要安装本地包: {}"),
  HADOOPFS_47("时间基础表达式'{}'评估得到空值"),
  HADOOPFS_48("由于异常而无法实例化压缩编解码器: {}"),

  HADOOPFS_49("HDFS地址未设置且无法通过'fs.defaultFS'属性得到"),
  HADOOPFS_50("目录模板头属性'" + HdfsTarget.TARGET_DIRECTORY_HEADER + "'缺失"),
  HADOOPFS_51("缺少头属性名roll"),
  HADOOPFS_52("空闲超时配置无效"),
  HADOOPFS_53("文件类型‘{}’配置无效, 应设置为{}，因为数据格式为{}."),
  HADOOPFS_54("路径{}已存在"),
  HADOOPFS_55("无效的文件权限EL表达式{}"),
  HADOOPFS_56("无效的文件权限值{}"),
  HADOOPFS_57("文件后缀包含'/'或以'.'开头"),
  HADOOPFS_58("文件flush失败:'{}'，因为：'{}'"),
  HADOOPFS_59("恢复重命名的_tmp_文件名失败: {}"),
  HADOOPFS_60("无效的数据格式{}, 应为{}，因为文件类型是{}."),
  HADOOPFS_61("您必须指定至少一个Hadoop FS地址：Hadoop FS配置路径或fs.defaultFS"),
  HADOOPFS_62("无法解析凭证: {}"),
//  HADOOPFS_00("Hadoop UserGroupInformation reports '{}' authentication, it should be '{}'"),
//  HADOOPFS_01("Failed to configure or connect to the '{}' Hadoop file system: {}"),
//  HADOOPFS_02("Invalid dir path template '{}', {}"),
//
//  HADOOPFS_03("The sequence file key expression '{}' is invalid: {}"),
//    /* LC - okay to add "valid" below? */
//  HADOOPFS_04("The custom compression codec '{}' is not a valid compression codec"),
//  HADOOPFS_05("The custom compression codec '{}' cannot be loaded: {}"),
//  HADOOPFS_06("The late record time limit expression '{}' is invalid: {}"),
//  HADOOPFS_07("The time driver expression '{}' is invalid: {}"),
//  HADOOPFS_08("The maximum file size must be a positive integer or zero to opt out of the option"),
//  HADOOPFS_09("The maximum records in a file must be a positive integer or zero to opt out of the option"),
//  HADOOPFS_10("The late record time limit expression must be greater than zero"),
//
//  HADOOPFS_11("Cannot initialize the writer manager: {}"),
//
//  HADOOPFS_12("The record '{}' is late"),
//  HADOOPFS_13("Error while writing to HDFS: {}"),
//  HADOOPFS_14("Cannot write record: {}"),
//  HADOOPFS_15("Record in error: {}"),
//  HADOOPFS_16("Unsupported data format '{}'"),
//  HADOOPFS_17("Cannot initialize the late records writer manager: {}"),
//  HADOOPFS_18("URI '{}' must start with <scheme>://<path>"),
//  HADOOPFS_19("Invalid time basis expression '{}': {}"),
//  HADOOPFS_20("Invalid directory template: {}"),
//  HADOOPFS_21("Invalid late record directory template: {}"),
//  HADOOPFS_22("Invalid URI '{}': {}"),
//  HADOOPFS_23("Could not commit old files: {}"),
//
//  HADOOPFS_24("Could not evaluate EL for directory path: {}"),
//
//  HADOOPFS_25("Hadoop configuration directory '{}' does not exist"),
//  HADOOPFS_26("Hadoop configuration directory '{}' is not a directory"),
//  HADOOPFS_27("Hadoop configuration file '{}' is not a file"),
//  HADOOPFS_28("Could not resolve the default Kerberos realm, you must set the 'dfs.namenode.kerberos.principal' " +
//              "property to the HDFS principal name: {}"),
//
//  HADOOPFS_29("Path template uses the '{}' function, it must use the '{}' function"),
//  HADOOPFS_30("The 'every(<UNIT>, <VALUE>)' function can be used only once in the path"),
//  HADOOPFS_31("The 'every(<UNIT>, <VALUE>)' function must use hh(), mm() or ss() as <UNIT>"),
//  HADOOPFS_32("The 'every(<UNIT>, <VALUE>)' function has the <VALUE> argument out of range, it must be between '1' and '{}'"),
//  HADOOPFS_33("The 'every(<UNIT>, <VALUE>)' function must use the smallest unit in the path template"),
//  HADOOPFS_34("The 'every(<UNIT>, <VALUE>)' function value must be a sub-multiple of the maximum value of the <UNIT>"),
//  HADOOPFS_35("Failed to retrieve increment time unit and value from the path template: {}"),
//  HADOOPFS_36("The 'ss()' function cannot be used within and outside of the 'every()' function at the same time"),
//  HADOOPFS_37("The 'mm()' function cannot be used within and outside of the 'every()' function at the same time"),
//  HADOOPFS_38("The 'hh()' function cannot be used within and outside of the 'every()' function at the same time"),
//
//  HADOOPFS_40("Base directory path must be absolute"),
//  HADOOPFS_41("Base directory path could not be created"),
//  HADOOPFS_42("Base directory path could not be created: '{}'"),
//  HADOOPFS_43("Could not create a file/directory under base directory: '{}'"),
//  HADOOPFS_44("Could not verify the base directory: '{}'"),
//  HADOOPFS_45("Hadoop configuration directory '{}' must be relative to SDC resources directory in cluster mode"),
//
//  HADOOPFS_46("The compression codec '{}' requires native libraries to be installed: {}"),
//  HADOOPFS_47("Time basis expression '{}' evaluated to NULL for this record"),
//  HADOOPFS_48("Failed to instantiate compression codec due to error: {}"),
//
//  HADOOPFS_49("HDFS URI is not set and is also not available through 'fs.defaultFS' config"),
//  HADOOPFS_50("Directory template header '" + HdfsTarget.TARGET_DIRECTORY_HEADER + "' missing"),
//  HADOOPFS_51("Missing roll header name"),
//  HADOOPFS_52("Invalid setting for idle timeout"),
//  HADOOPFS_53("Invalid Setting for File Type {}, should be {} for Data Format {}."),
//  HADOOPFS_54("The path {} already exists."),
//  HADOOPFS_55("Invalid permission EL {} for the file"),
//  HADOOPFS_56("Invalid permission value {} for the file"),
//  HADOOPFS_57("Files Suffix contains '/' or starts with '.'"),
//  HADOOPFS_58("Flush failed on file: '{}' due to '{}'"),
//  HADOOPFS_59("Recovery failed to rename old _tmp_ files: {}"),
//  HADOOPFS_60("Invalid Data Format {}, should be {} for File Type {}."),
//  HADOOPFS_61("You must specify at least one of Hadoop FS URI, Hadoop FS Configuration Directory or fs.defaultFS"),
//  HADOOPFS_62("Can't resolve credential: {}"),

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
