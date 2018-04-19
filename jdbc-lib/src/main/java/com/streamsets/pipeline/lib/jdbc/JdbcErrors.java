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
package com.streamsets.pipeline.lib.jdbc;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.stage.processor.jdbclookup.JdbcLookupLoader;

@GenerateResourceBundle
public enum JdbcErrors implements ErrorCode {

  JDBC_00("无法连接指定的数据库: {}"),
  JDBC_01("评估表达式异常: '{}'"),
  JDBC_02("查询异常: '{}' - '{}'"),
  JDBC_03("字段解析异常'{}'，其值为{}."),
  JDBC_04("查询无结果: '{}'"),
  JDBC_05("记录中不支持的数据类型: {}"),
  JDBC_06("初始化连接池失败: {}"),
  JDBC_07("字段中的无效列映射 '{}' 在这个字段中 '{}'"),
  JDBC_08("记录缺少必填字段 {} 用于更改日志类型 {}"),
  JDBC_09("无效操作 '{}' 用于更改日志类型 {}"),
  JDBC_10("'{}' 小于最小值 '{}'"),
  JDBC_11("最小空闲连接 ({}) 必须小于或等于最大池大小 ({})"),
  JDBC_13("转换CLOB串失败: {}"),
  JDBC_14("批处理时出错.\n{}"),
  JDBC_15("无效的jdbc命名空间前缀,应以'.'结尾"),
  JDBC_16("表 '{}' 不存在或PDB是错误的，确保指定了正确的PDB"),
  JDBC_17("未能查找表的主键 '{}' : {}"),
  JDBC_19("记录没有包含映射到主键列的主键字段 '{}'"),
  JDBC_20("无法解析表名模板表达式: {}"),
  JDBC_21("无法对表名模板表达式进行评估: {}"),
  JDBC_22("记录中没有匹配目标表中列的字段"),
  JDBC_23("这个记录中 '{}' 来自于这个类型 '{}' 与目标列的类型不匹配"),
  JDBC_24("没有插入的结果"),
  JDBC_25("列无列映射 '{}'"),
  JDBC_26("无效的表名称模板表达式 '{}': {}"),
  JDBC_27("查询间隔表达式必须大于或等于零"),
  JDBC_28("创建JDBC driver失败, JDBC driver JAR 时必须的: {}"),
  JDBC_29("查询必须包括 '{}' 在 WHERE 和 ORDER BY 其他列之前的子句"),
  JDBC_30("这个 JDBC driver 该数据库不支持可滚动游标,指定事务ID列名时需要哪些"),
  JDBC_31("查询结果有重复的列标签 '{}'. 在查询中使用“AS”创建别名."),
  JDBC_32("Offset 列 '{}' 不能包含 '.' 或前缀."),
  JDBC_33("Offset 列 '{}' 查询中找不到 '{}' 结果"),
  JDBC_34("查询无法执行: '{}' 错误: {}"),
  JDBC_35("解析记录有 {} 列必须SDC解析 {}."),
  JDBC_36("Column 的索引 {} 无效."),
  JDBC_37("不支持的类型 {} 来自于这个列 {}"),
  JDBC_38("查询必须包括 '{}' 从句."),
  JDBC_39("Oracle SID 必须指定为 Oracle 12c"),
  JDBC_40("切换到容器时出错 {} 使用提供的凭据"),
  JDBC_41("获取DB版本时出错"),
  JDBC_42("获取初始SCN时出错，请验证用户的权限"),
  JDBC_43("无法解析重做日志语句: {}"),
  JDBC_44("由于错误而获取更改时出错: {}"),
  JDBC_45("指定的起始日期不可用重做日志，提供一个较新的开始日期"),
  JDBC_46("对于指定的初始SCN，重做日志不可用,提供一个更新的初始SCN"),
  JDBC_47("当前最新的SCN {} 小于初始SCN"),
  JDBC_48("这个时开始日期"),
  JDBC_49("日期时无效的，请按照格式:DD-MM-YYYY HH24:MM:SS"),
  JDBC_50("获取表的schema时出错: '{}', 请验证对数据库的连接和用户的权限"),
  JDBC_51("无效的值: {}"),
  JDBC_52("错误启动日志分析"),
  JDBC_53("这个默认值 '{}' 不是空, 它的数据类型不能 '" + DataType.USE_COLUMN_TYPE.getLabel() + "'."),
  JDBC_54("列: '{}' 不在这个表中: '{}'. 这可能是由于在这个表上执行DDL"),
  JDBC_55("这个默认的值 '{}' 必须是这个格式 '" + JdbcLookupLoader.DATE_FORMAT + "': {}"),
  JDBC_56("这个默认的值 '{}' 必须是 '" + JdbcLookupLoader.DATETIME_FORMAT + "': {}"),
  JDBC_57("SQL Server不支持的多行操作"),

  JDBC_60("无法序列化 Offset: {}"),
  JDBC_61("不能反序列化 Offset: {}"),
  JDBC_62("表 {} 没有定义主分区或无分区配置."),
  JDBC_63("表 {} 不包含指定的分区列 {}"),
  JDBC_64("无效的初始偏移配置，列: {}, 不存在的列: {} ."),
  JDBC_66("在这个数据源中没有匹配的表"),
  JDBC_67("内部 Error : {}"),
  JDBC_68("以循环方式相互引用的表"),
  JDBC_69("不受支持的偏移列类型{}"),
  JDBC_70("记录header中不支持的操作: {}"),
  JDBC_71("无效的状态，表中偏移列的不匹配 {}， 在offset列: {}, 规定 Offset 列: {}"),
  JDBC_72("无效开始的 offset(s) 在这个表中 '{}'. 带值的无效偏移列 '{}'"),
  JDBC_73("错误的求值正则 {}. 原因 : {}"),
  JDBC_74("最大线程池的大小 '{}' 应该等于线程数 '{}'"),
  JDBC_75("Jdbc 转换失败，原因 {}"),
  JDBC_76("初始化值 '0' 来自于批处理的集合中"),
  JDBC_77("{} 尝试执行查询 '{}'，放弃后 {} e误差为每级配置，第一个错误: {}"),
  JDBC_78("重试次数用尽, 按阶段放弃配置， 第一个错误: {}"),

  JDBC_80("初始化事务的窗口，必须是有效的long或EL"),
  JDBC_81("LogMiner会话窗口必须超过最大事务的长度"),
  JDBC_82("无法在没有缓冲的以前启动的任务中缓冲内存中的数据"),
  JDBC_83("无法从内存缓冲区切换到仅读取提交数据"),
  JDBC_84("事务Id: '{}' 开始在: '{}' 在正在处理的当前事务窗口之前," +
      "事务比事务窗口长"),
  JDBC_85("以下字段具有不受支持的字段类型: '{}' 在这个表中 '{}'"),
  JDBC_86("当前会话窗口的重做日志文件不再可用"),
  JDBC_87("等待读取数据时中断"),
  JDBC_88("'{}' 不是有效的decimal number的类型"),

  JDBC_100("无法启用表分区 {}: {}"),
  JDBC_101("表的无效分区大小 {}: {}"),
  JDBC_102("无效的最大分区数 ({}) 在这个表中 {}; 这个时否定的 (来自于默认的操作) 或大于1确保进展"),
  JDBC_200("表未启用跟踪更改: {}"),
  JDBC_201("无效的更改跟踪当前版本: {}"),
  JDBC_202("获取最小有效版本时出错: {}"),
  JDBC_203("从元数据获取表时出错: {}"),
  JDBC_204("十六进制 '{}' 不能转换为字节数组"),
  ;

  //  JDBC_00("Cannot connect to specified database: {}"),
//  JDBC_01("Failed to evaluate expression: '{}'"),
//  JDBC_02("Exception executing query: '{}' - '{}'"),
//  JDBC_03("Failed to parse column '{}' to field with value {}."),
//  JDBC_04("No results for query: '{}'"),
//  JDBC_05("Unsupported data type in record: {}"),
//  JDBC_06("Failed to initialize connection pool: {}"),
//  JDBC_07("Invalid column mapping from field '{}' to column '{}'"),
//  JDBC_08("Record missing required field {} for change log type {}"),
//  JDBC_09("Invalid operation '{}' for change log type {}"),
//  JDBC_10("'{}' is less than the minimum value of '{}'"),
//  JDBC_11("Minimum Idle Connections ({}) must be less than or equal to Maximum Pool Size ({})"),
//  JDBC_13("Failed to convert CLOB to string: {}"),
//  JDBC_14("Error processing batch.\n{}"),
//  JDBC_15("Invalid JDBC Namespace prefix, should end with '.'"),
//  JDBC_16("Table '{}' does not exist or PDB is incorrect. Make sure the correct PDB was specified"),
//  JDBC_17("Failed to lookup primary keys for table '{}' : {}"),
//  JDBC_19("Record did not contain primary key field mapped to primary key column '{}'"),
//  JDBC_20("Could not parse the table name template expression: {}"),
//  JDBC_21("Could not evaluate the table name template expression: {}"),
//  JDBC_22("The record had no fields that matched the columns in the destination table."),
//  JDBC_23("The field '{}' of type '{}' doesn't match the destination column's type."),
//  JDBC_24("No results from insert"),
//  JDBC_25("No column mapping for column '{}'"),
//  JDBC_26("Invalid table name template expression '{}': {}"),
//  JDBC_27("The query interval expression must be greater than or equal to zero."),
//  JDBC_28("Failed to create JDBC driver, JDBC driver JAR may be missing: {}"),
//  JDBC_29("Query must include '{}' in WHERE clause and in ORDER BY clause before other columns."),
//  JDBC_30("The JDBC driver for this database does not support scrollable cursors, " +
//          "which are required when Transaction ID Column Name is specified."),
//  JDBC_31("Query result has duplicate column label '{}'. Create an alias using 'AS' in your query."),
//  JDBC_32("Offset Column '{}' cannot contain a '.' or prefix."),
//  JDBC_33("Offset column '{}' not found in query '{}' results."),
//  JDBC_34("Query failed to execute: '{}' Error: {}"),
//  JDBC_35("Parsed record had {} columns but SDC expected {}."),
//  JDBC_36("Column index {} is not valid."),
//  JDBC_37("Unsupported type {} for column {}"),
//  JDBC_38("Query must include '{}' clause."),
//  JDBC_39("Oracle SID must be specified for Oracle 12c"),
//  JDBC_40("Error while switching to container {} using given credentials"),
//  JDBC_41("Error while getting DB version"),
//  JDBC_42("Error while getting initial SCN. Please verify the privileges for the user"),
//  JDBC_43("Could not parse redo log statement: {}"),
//  JDBC_44("Error while getting changes due to error: {}"),
//  JDBC_45("Redo logs are not available for the specified start date. " +
//          "Provide a more recent start date"),
//  JDBC_46("Redo logs are not available for the specified initial SCN. " +
//          "Provide a more recent initial SCN"),
//  JDBC_47("The current latest SCN {} is less than the initial SCN"),
//  JDBC_48("The start date is in the future"),
//  JDBC_49("Date is invalid. Please use format DD-MM-YYYY HH24:MM:SS"),
//  JDBC_50("Error while getting schema for table: '{}'. Please verify the connectivity to the DB and the privileges for the user"),
//  JDBC_51("Invalid value: {}"),
//  JDBC_52("Error starting LogMiner"),
//  JDBC_53("Since the default value of '{}' is not empty, its data type cannot be '" + DataType.USE_COLUMN_TYPE.getLabel() + "'."),
//  JDBC_54("Column: '{}' does not exist in table: '{}'. This is likely due to a DDL being performed on this table"),
//  JDBC_55("The default value of '{}' must be in the format '" + JdbcLookupLoader.DATE_FORMAT + "': {}"),
//  JDBC_56("The default value of '{}' must be in the format '" + JdbcLookupLoader.DATETIME_FORMAT + "': {}"),
//  JDBC_57("Unsupported Multi-Row Operation to SQL Server"),
//
//  JDBC_60("Cannot Serialize Offset: {}"),
//  JDBC_61("Cannot Deserialize Offset: {}"),
//  JDBC_62("Table {} does not have a primary or no partition configuration defined."),
//  JDBC_63("Table {} does not contain the specified partition column {}."),
//  JDBC_64("Invalid intial Offset configuration. Missed Columns: {}, Non Existing Columns: {} ."),
//  JDBC_66("No Tables matches the configuration in the origin."),
//  JDBC_67("Internal Error : {}"),
//  JDBC_68("Tables Referring to each other in cyclic fashion."),
//  JDBC_69("Unsupported Offset Column Types. {}"),
//  JDBC_70("Unsupported operation in record header: {}"),
//  JDBC_71("Invalid State. Mismatch in offset columns for table {}. Stored offset columns: {}, Specified Offset Columns: {}"),
//  JDBC_72("Invalid Start offset(s) for table '{}'. Invalid Offset Columns With Values '{}'"),
//  JDBC_73("Error Evaluating Expression {}. Reason : {}"),
//  JDBC_74("Max Pool Size '{}' should be equal to Number Of Threads '{}'"),
//  JDBC_75("Jdbc Runner Failed. Reason {}"),
//  JDBC_76("Invalid value '0' for Batches From Result Set"),
//  JDBC_77("{} attempting to execute query '{}'. Giving up after {} errors as per stage configuration. First error: {}"),
//  JDBC_78("Retries exhausted, giving up after as per stage configuration. First error: {}"),
//
//  JDBC_80("Invalid transaction window. Must be a valid long or EL"),
//  JDBC_81("LogMiner Session Window must be longer than Maximum Transaction Length"),
//  JDBC_82("Cannot buffer data in memory for a pipeline previously started without buffering"),
//  JDBC_83("Cannot switch from in-memory buffering to reading committed data only"),
//  JDBC_84("Transaction Id: '{}' started at: '{}' which is before the current transaction window being processed. " +
//          "The transaction was longer than transaction window"),
//  JDBC_85("Following fields have unsupported field types: '{}' in table '{}'"),
//  JDBC_86("Redo log files for the current session window are no longer available"),
//  JDBC_87("Interrupted while waiting to read data"),
//  JDBC_88("'{}' is not a valid decimal number"),
//
//  JDBC_100("Could not enable partitioning for table {}: {}"),
//  JDBC_101("Invalid partition size for table {}: {}"),
//  JDBC_102("Invalid max number of partitions ({}) for table {}; this must be negative (for default behavior) or" +
//          " greater than 1 to ensure progress"),
//  JDBC_200("Tables are not change tracking enabled: {}"),
//  JDBC_201("Invalid Change Tracking Current Version: {}"),
//  JDBC_202("Error while getting min valid version: {}"),
//  JDBC_203("Error while fetch tables from metadata: {}"),
//  JDBC_204("Hex '{}' cannot be converted into a byte array"),
//  ;

  private final String msg;

  JdbcErrors(String msg) {
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
