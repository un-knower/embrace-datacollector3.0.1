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
package com.streamsets.pipeline.stage.origin.mysql;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDef.Type;

public class MysqlSourceConfig {
  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      defaultValue = "localhost",
      label = "主机名",
      description = "MySql服务器的主机名",
//      label = "Hostname",
//      description = "MySql server hostname",
      displayPosition = 10,
      group = "MYSQL"
  )
  public String hostname;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      defaultValue = "3306",
      label = "端口",
      description = "MySql服务端口",
//      label = "Port",
//      description = "MySql server port",
      displayPosition = 20,
      group = "MYSQL"
  )
  public String port;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "用户名",
      description = "MySql用户名. 必须具有REPLICATION SLAVE权限",
//      label = "Username",
//      description = "MySql username. User must have REPLICATION SLAVE privilege",
      displayPosition = 30,
      group = "CREDENTIALS"
  )
  public String username;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "密码",
      description = "MySql用户密码.",
//      label = "Password",
//      description = "MySql user password.",
      displayPosition = 40,
      group = "CREDENTIALS"
  )
  public String password;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      defaultValue = "999",
      label = "服务器ID",
      description = "二进制日志客户端使用的服务器ID. 所有从节点的服务器ID不能重复(数据源也作为一个从节点).",
//      label = "Server ID",
//      description = "ServerId used by binlog client. Must be unique among all replication slaves " +
//          "(origin acts as a replication slave itself).",
      displayPosition = 50,
      group = "MYSQL"
  )
  public String serverId;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "1000",
      label = "最大批处理大小(记录数)",
      description = "每批次中最大的记录数.",
//      label = "Max Batch Size (records)",
//      description = "Maximum number of records in a batch.",
      displayPosition = 60,
      group = "ADVANCED"
  )
  public int maxBatchSize;


  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "1000",
      label = "批处理等待时间(ms)",
      description = "未完成或空批处理返回记录的最大等待时间.",
//      label = "Batch Wait Time (ms)",
//      description = "Maximum timeout millis to wait for batch records before returning " +
//          "incomplete or empty batch.",
      displayPosition = 50,
      group = "ADVANCED"
  )
  public int maxWaitTime;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "5000",
      label = "连接超时时间(ms)",
      description = "MySql连接超时时间.",
//      label = "Connect Timeout (ms)",
//      description = "MySql connection timeout millis.",
      displayPosition = 60,
      group = "ADVANCED"
  )
  public int connectTimeout;

  @ConfigDef(
      required = true,
      type = Type.BOOLEAN,
      defaultValue = "false",
      label = "启用SSL",
      description = "是否使用SSL连接MySQL",
//      label = "Use SSL",
//      description = "Whether to use SSL for the MySQL connection",
      displayPosition = 65,
      group = "ADVANCED"
  )
  public boolean useSsl;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "false",
      label = "从头开始",
      description = "若勾选,首次启动数据源时,从二进制日志的初始位置开始读取事件;未勾选则从当前位置.如果启用GTID，所有服务器执行配置的gtid.",
//      label = "Start From Beginning",
//      description = "On first origin start read events from beginning of binlog. " +
//          "When 'false' - start from current binlog position. " +
//          "In case when GTID-enabled this records all server executed gtids as applied.",
      displayPosition = 70,
      group = "MYSQL"
  )
  public boolean startFromBeginning;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      label = "初始偏移量",
      description = "首次启动数据源,从指定的偏移位置开始读取事件." +
          "偏移量格式依赖GTID模式. 当启用GTID时,它应为需要跳过的事务GTID集合;当未启用GTID时,它应为二进制日志文件名+二进制日志位置,格式:" +
          "'${binlog-filename}:${binlog-position}'. " +
          "注意:该配置于'从头开始'配置有冲突,若两项均配置,本配置优先生效",
//      label = "Initial offset",
//      description = "On first origin start read events starting from given offset. " +
//          "Offset format depends on GTID mode. When GTID is enabled - it should be a GTID-set of " +
//          "transactions that should be skipped. When GTID is disabled - it should be binlog filename + binlog " +
//          "position to start from in format '${binlog-filename}:${binlog-position}'. " +
//          "Note - this setting conflicts with 'Start from beginning' setting, " +
//          "if both are set - this takes precedence.",
      displayPosition = 80,
      group = "MYSQL"
  )
  public String initialOffset;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      label = "包括数据表",
      description = "包括的数据表列表,以','分割. " +
          "数据库和表名支持通配符'%'(匹配任意字符). " +
          "数据库和表名以'.'分割. 例如:'db%sales.sales_%_dep,db2.orders'. " +
          "未在此列表内的表将被忽略.",
//      label = "Include Tables",
//      description = "Comma-delimited list of database and table names to include. " +
//          "Database and table names support wildcards - special character '%' match any number of any chars. " +
//          "DB and table name are delimited by dot. Example - 'db%sales.sales_%_dep,db2.orders'. " +
//          "All tables that are not included are ignored.",
      displayPosition = 90,
      group = "ADVANCED"
  )
  public String includeTables;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      label = "忽略数据表",
      description = "忽略的数据表列表,以','分割. " +
              "数据库和表名支持通配符'%'(匹配任意字符). " +
              "数据库和表名以'.'分割. 例如:'db%sales.sales_%_dep,db2.orders'. " +
          "忽略的数据表优先级高于包括的数据表,即对于两个配置项均存在的数据表,将被忽略.",
//      label = "Ignore Tables",
//      description = "Comma-delimited list of database and table names to ignore. " +
//          "Database and table names support wildcards - special character '%' match any number of any chars. " +
//          "DB and table name are delimited by dot. Example - 'db%sales.sales_%_dep,db2.orders'. " +
//          "Ignore tables have precedence over include tables - if some table is both included " +
//          "and ignored - it will be ignored.",
      displayPosition = 100,
      group = "ADVANCED"
  )
  public String ignoreTables;

  @Override
  public String toString() {
    return "MysqlSourceConfig{" +
        "hostname='" + hostname + '\'' +
        ", port=" + port +
        ", username='" + username + '\'' +
        ", password='**********'" +
        ", serverId=" + serverId +
        ", maxBatchSize=" + maxBatchSize +
        ", maxWaitTime=" + maxWaitTime +
        ", startFromBeginning=" + startFromBeginning +
        ", offset=" + initialOffset +
        '}';
  }
}
