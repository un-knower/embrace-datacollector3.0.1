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
package com.streamsets.pipeline.lib.mqtt;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.api.credential.CredentialValue;
import com.streamsets.pipeline.lib.tls.TlsConfigBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttClientConfigBean {
  private static final Logger LOG = LoggerFactory.getLogger(MqttClientConfigBean.class);

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "服务端地址",
      defaultValue = "tcp://localhost:1883",
      description = "指定MQTT服务地址",
//      label = "Broker URL",
//      defaultValue = "tcp://localhost:1883",
//      description = "Specify the MQTT Broker URL",
      displayPosition = 10,
      group = "MQTT"
  )
  public String brokerUrl = "";

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "客户端ID",
      defaultValue = "${pipeline:id()}",
      description = "指定MQTT客户端ID.它必须在连接到同一服务器的所有客户机中唯一.",
//      label = "Client ID",
//      defaultValue = "${pipeline:id()}",
//      description = "Specify the MQTT Client ID. It must be unique across all clients connecting to the same server.",
      displayPosition = 20,
      group = "MQTT"
  )
  public String clientId = "";

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "服务数量",
      defaultValue = "AT_MOST_ONCE",
      description = "指定发布消息服务的数量.",
//      label = "Quality of Service",
//      defaultValue = "AT_MOST_ONCE",
//      description = "Specify the quality of service to publish the message.",
      displayPosition = 40,
      group = "MQTT"
  )
  @ValueChooserModel(QualityOfServiceChooserValues.class)
  public QualityOfService qos = QualityOfService.AT_MOST_ONCE;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      label = "客户端持久化机制",
      defaultValue = "MEMORY",
      description = "指定消息可靠性存储机制. 对于‘至少一次’或‘保证一次’，消息必须同时保存在客户端和服务端，直到消息提交完成.",
//      label = "Client Persistence Mechanism",
//      defaultValue = "MEMORY",
//      description = "Specify the persistence mechanism used to enable reliable messaging. For messages sent " +
//          "at least once (1) or exactly once (2) to be reliably delivered, messages must be stored on both the " +
//          "client and server until the delivery of the message is complete.",
      displayPosition = 50,
      group = "MQTT"
  )
  @ValueChooserModel(MqttPersistenceMechanismChooserValues.class)
  public MqttPersistenceMechanism persistenceMechanism = MqttPersistenceMechanism.MEMORY;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "客户端数据存储目录",
      defaultValue = "/tmp",
      description = "指定文件持久化机制的目录",
//      label = "Client Persistence Data Directory",
//      defaultValue = "/tmp",
//      description = "Specify the directory for file-based Persistence Mechanism",
      displayPosition = 51,
      group = "MQTT",
      dependsOn = "persistenceMechanism",
      triggeredByValue = {"FILE"}
  )
  public String dataDir = "";

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "60",
      label = "保持间隔(s)",
      description = "此值定义发送或接收的消息之间的最大时间间隔. ",
//      label = "Keep Alive Interval (secs)",
//      description = "This value defines the maximum time interval between messages sent or received. ",
      displayPosition = 60,
      group = "MQTT"
  )
  public int keepAlive = 60;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "使用凭证",
      description = "使用用户名密码认证",
//      label = "Use Credentials",
//      description = "Use Username and Password Authentication",
      defaultValue = "false",
      displayPosition = 70,
      group = "MQTT"
  )
  public boolean useAuth = false;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.CREDENTIAL,
      label = "用户名",
//      label = "Username",
      displayPosition = 71,
      group = "CREDENTIALS",
      dependsOn = "useAuth",
      triggeredByValue = { "true" }
  )
  public CredentialValue username;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.CREDENTIAL,
      label = "密码",
//      label = "Password",
      displayPosition = 72,
      group = "CREDENTIALS",
      dependsOn = "useAuth",
      triggeredByValue = { "true" }
  )
  public CredentialValue password;

  @ConfigDefBean(groups = "TLS")
  public TlsConfigBean tlsConfig = new TlsConfigBean();

}
