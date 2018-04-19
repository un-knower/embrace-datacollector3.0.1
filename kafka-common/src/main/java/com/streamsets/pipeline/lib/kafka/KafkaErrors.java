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
package com.streamsets.pipeline.lib.kafka;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum KafkaErrors implements ErrorCode {

  KAFKA_03("无法找到主题元数据'{}'，服务端地址：'{}'"),
  KAFKA_04("主题'{}'不存在"),
  KAFKA_05("主题不能为空"),
  KAFKA_06("Zookeeper和服务端地址不能为空"),
  KAFKA_07("无效的服务端地址'{}'"),
  KAFKA_09("无效的Zookeeper连接地址'{}' : {}"),
  KAFKA_10("无法验证配置: {}"),
  KAFKA_11("无法恢复主题元数据'{}'，服务端：'{}': {}"),
//  KAFKA_03("Cannot find metadata for topic '{}' from the broker list '{}'"),
//  KAFKA_04("Topic '{}' does not exist"),
//  KAFKA_05("Topic cannot be empty"),
//  KAFKA_06("Zookeeper and broker URIs cannot be empty"),
//  KAFKA_07("Invalid broker URI '{}'"),
//  KAFKA_09("Invalid Zookeeper connect string '{}' : {}"),
//  KAFKA_10("Cannot validate configuration: {}"),
//  KAFKA_11("Cannot retrieve metadata for topic '{}' from broker '{}': {}"),

  //Kafka source messages
  KAFKA_21("Kakfa服务故障后无法找到可用的Leader"),
  KAFKA_22("从服务器获取偏移量(offset)出错'{}': {}"),
  KAFKA_23("无法找到主题元数据'{}', 分区：'{}'"),
  KAFKA_24("无法找到主题Leader'{}', 分区：'{}'"),
  KAFKA_25("与服务器通信出错'{}'，主题Leader：'{}', 分区：'{}': {}"),
  KAFKA_26("从Kafka主题获取数据出错'{}', 分区：'{}', 偏移量：'{}"),
  KAFKA_27("找到历史偏移量：'{}'. 期望的偏移量：'{}'. 丢弃消息."),
  KAFKA_28("从Kafka获取数据时发生SocketTimeoutException异常"),
  KAFKA_29("从Kafka获取数据出错: {}"),
  KAFKA_30("从Kafka获取偏移量出错: {}"),
  KAFKA_31("连接ZooKeeper异常'{}': {}"),
  KAFKA_32("从Kafka获取迭代器出错: {}"),
  KAFKA_33("定义消费者分组"),
  KAFKA_35("批处理等待事件不能小与1"),
  KAFKA_37("无法解析记录'{}': {}"),
  KAFKA_40("一条XML消息数据中不能具有多个XML文档"),
  KAFKA_41("无法获取主题的分区数'{}' : {}"),
  KAFKA_42("无法获取主题的分区数'{}'"),
  KAFKA_43("模式注册地址必须使用Confluent反序列化器"),
  KAFKA_44("该版本Kfaka不支持Confluent Avro反序列化器"),
  KAFKA_74("消息体不能为空"),
//  KAFKA_21("Cannot find a new leader after a Kafka broker failure"),
//  KAFKA_22("Error fetching offset data from the broker '{}': {}"),
//  KAFKA_23("Cannot find metadata for topic '{}', partition '{}'"),
//  KAFKA_24("Cannot find leader for topic '{}', partition '{}'"),
//  KAFKA_25("Error communicating with broker '{}' to find leader for topic '{}', partition '{}': {}"),
//  KAFKA_26("Error fetching data from Kafka topic '{}', partition '{}', offset '{}"),
//  KAFKA_27("Found old offset '{}'. Expected offset '{}'. Discarding message."),
//  KAFKA_28("SocketTimeoutException encountered while fetching message from Kafka"),
//  KAFKA_29("Error fetching data from Kafka: {}"),
//  KAFKA_30("Error fetching offset from Kafka: {}"),
//  KAFKA_31("Error connecting to ZooKeeper with connection string '{}': {}"),
//  KAFKA_32("Error getting iterator from KafkaStream: {}"),
//  KAFKA_33("Define the consumer group"),
//  KAFKA_35("Batch wait time cannot be less than 1"),
//  KAFKA_37("Cannot parse record from message '{}': {}"),
//  KAFKA_40("Messages with XML data cannot have multiple XML documents in a single message"),
//  KAFKA_41("Could not get partition count for topic '{}' : {}"),
//  KAFKA_42("Could not get partition count for topic '{}'"),
//  KAFKA_43("Schema Registry URLs must be configured to use Confluent Deserializer"),
//  KAFKA_44("Confluent Avro Deserializer not supported by this version of Kafka."),
//  KAFKA_74("Message payload cannot be null"),

  //Kafka target messages
  KAFKA_50("向Kafka写入数据出错: {}"),
  KAFKA_51("序列化记录出错'{}': {}"),
  KAFKA_54("评估分区表达式出错'{}'，记录：'{}': {}"),
  KAFKA_55("转换分区表达式出错'{}'，主题ID：'{}': {}"),
  KAFKA_56("分区表达式生成无效分区ID'{}'. 主题：'{}' 有{}个分区. 记录：'{}'."),
  KAFKA_57("无效的分区表达式'{}': {}"),
  KAFKA_58("字段不能为空"),
  KAFKA_59("字段不能为空"),
  KAFKA_60("无法序列化记录'{}'. 批处理中具有实体'{}'、偏移量'{}'，并在分区'{}'的记录全部发送至错误处理节点: {}"),
  KAFKA_61("无效的主题表达式'{}': {}"),
  KAFKA_62("主题表达式'{}'生成了空的主题名，记录：'{}'"),
  KAFKA_63("评估主题表达式出错'{}'，记录：'{}': {}"),
  KAFKA_64("若勾选运行时解析主题，则主题白名单不能为空"),
  KAFKA_65("主题'{}'从记录'{}'中解析得到，但不是被允许的主题"),
  KAFKA_66("Kafka生产者配置'{}'设定值{}必须大于0"),
  KAFKA_67("连接Kafka服务端出错'{}'"),
  KAFKA_68("获取主题元数据出错'{}'， 服务端：'{}'，原因: {}"),
  KAFKA_69("消息大小超过Kafka服务端配置限制"),
  KAFKA_70("包含模式不能与Confluent序列化器同时使用"),
  KAFKA_71("元注册地址必须使用Confluent序列化器配置"),
  KAFKA_72("主题或模式ID必须使用Confluent序列化器定义"),
  KAFKA_73("该版本Kafka不支持Confluent Avro序列化器."),
//  KAFKA_50("Error writing data to the Kafka broker: {}"),
//  KAFKA_51("Error serializing record '{}': {}"),
//  KAFKA_54("Error evaluating the partition expression '{}' for record '{}': {}"),
//  KAFKA_55("Error converting the partition expression '{}' to a partition ID for topic '{}': {}"),
//  KAFKA_56("Partition expression generated an invalid partition ID '{}'. Topic '{}' has {} partitions. Record '{}'."),
//  KAFKA_57("Invalid partition expression '{}': {}"),
//  KAFKA_58("Field cannot be empty"),
//  KAFKA_59("Fields cannot be empty"),
//  KAFKA_60("Cannot serialize record '{}'. All records from batch with entity '{}' and offset '{}' for partition '{}' are sent to error: {}"),
//  KAFKA_61("Invalid topic expression '{}': {}"),
//  KAFKA_62("Topic expression '{}' generated a null or empty topic for record '{}'"),
//  KAFKA_63("Error evaluating topic expression '{}' for record '{}': {}"),
//  KAFKA_64("Topic White List cannot be empty if topic is resolved at runtime"),
//  KAFKA_65("Topic '{}' resolved from record '{}' is not among the allowed topics"),
//  KAFKA_66("Kafka Producer configuration '{}' must be specified a valid {} value greater than or equal to 0"),
//  KAFKA_67("Error connecting to Kafka Brokers '{}'"),
//  KAFKA_68("Error getting metadata for topic '{}' from broker '{}' due to error: {}"),
//  KAFKA_69("Message is larger than the maximum allowed size configured in Kafka Broker"),
//  KAFKA_70("Include Schema cannot be used in conjunction with Confluent Serializer"),
//  KAFKA_71("Schema Registry URLs must be configured to use Confluent Serializer"),
//  KAFKA_72("Subject or Schema ID must be defined to use Confluent Serializer"),
//  KAFKA_73("Confluent Avro Serializer not supported by this version of Kafka."),
  ;

  private final String msg;

  KafkaErrors(String msg) {
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
