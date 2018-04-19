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
package com.streamsets.pipeline.lib.jms.config;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ValueChooserModel;

import java.util.HashMap;
import java.util.Map;

public class BaseJmsConfig {
  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "初始上下文工厂类",
      description = "ActiveMQ示例: org.apache.activemq.jndi.ActiveMQInitialContextFactory",
//      label = "JMS Initial Context Factory",
//      description = "ActiveMQ example: org.apache.activemq.jndi.ActiveMQInitialContextFactory",
      displayPosition = 10,
      group = "JMS"
  )
  public String initialContextFactory;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "JNDI连接工厂",
      description = "ActiveMQ示例: ConnectionFactory",
//      label = "JNDI Connection Factory",
//      description = "ActiveMQ example: ConnectionFactory",
      displayPosition = 20,
      group = "JMS"
  )
  public String connectionFactory;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
      label = "服务地址",
      description = "ActiveMQ示例: tcp://mqserver:61616",
//      label = "JMS Provider URL",
//      description = "ActiveMQ example: tcp://mqserver:61616",
      displayPosition = 30,
      group = "JMS"
  )
  public String providerURL;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MODEL,
      defaultValue = "UNKNOWN",
      label = "目标类型",
      description = "当验证失败(NamingException或目标未找到)时,说明JMS目标类型",
//      label = "JMS Destination Type",
//      description = "Specify the JMS destination type when validation fails with NamingException, destination not found",
      displayPosition = 70,
      group = "JMS"
  )
  @ValueChooserModel(DestinationTypeChooserValues.class)
  public DestinationType destinationType = DestinationType.UNKNOWN; // NOTE: same as above

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MAP,
      defaultValue = "",
      label = "配置参数",
      description = "JMS上下文配置参数",
//      label = "Additional JMS Configuration Properties",
//      description = "Additional properties to pass to the underlying JMS context.",
      displayPosition = 999,
      group = "JMS"
  )
  public Map<String, String> contextProperties = new HashMap<>();
}