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
package com.streamsets.pipeline.stage.destination.flume;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.lib.FlumeUtil;
import com.streamsets.pipeline.lib.flume.FlumeErrors;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class FlumeConfig {

  private static final String HOSTS_KEY = "hosts";
  private static final String BATCH_SIZE_KEY = "batch-size";
  private static final String CONNECTION_TIMEOUT_KEY = "connect-timeout";
  private static final String REQUEST_TIMEOUT_KEY = "request-timeout";
  private static final String CLIENT_TYPE_KEY = "client.type";
  private static final String CLIENT_TYPE_DEFAULT_FAILOVER = "default_failover";
  private static final String MAX_ATTEMPTS_KEY = "max-attempts";
  private static final String CLIENT_TYPE_DEFAULT_LOADBALANCING = "default_loadbalance";
  private static final String BACKOFF_KEY = "backoff";
  private static final String MAX_BACKOFF_KEY = "maxBackoff";
  private static final String HOST_SELECTOR_KEY = "host-selector";
  private static final String HOST_SELECTOR_RANDOM = "random";
  private static final String HOST_SELECTOR_ROUND_ROBIN = "round_robin";
  private static final String FLUME_CONFIG_PREFIX = "flumeConfigBean.flumeConfig.";

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.MAP,
    defaultValue = "{ \"h1\" : \"localhost:41414\" }",
    label = "主机配置",
    description = "Flume主机别名和地址，格式：<HOST>:<PORT>",
//    label = "Hosts Configuration",
//    description = "Flume host alias and the address in the form <HOST>:<PORT>",
    displayPosition = 10,
    group = "FLUME"
  )
  public Map<String, String> flumeHostsConfig;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.MODEL,
    defaultValue = "AVRO_FAILOVER",
    label = "客户端类型",
//    label = "Client Type",
    displayPosition = 20,
    group = "FLUME"
  )
  @ValueChooserModel(ClientTypeChooserValues.class)
  public ClientType clientType;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.BOOLEAN,
    defaultValue = "false",
    label = "退避",
    description = "暂时避免向失败主机写数据",
//    label = "Backoff",
//    description = "Temporarily avoid writing to a failed host",
    displayPosition = 40,
    group = "FLUME",
    dependsOn = "clientType",
    triggeredByValue = "AVRO_LOAD_BALANCING"
  )
  public boolean backOff;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.NUMBER,
    defaultValue = "0",
    label = "最大退避时间(ms)",
    description = "客户端由于主机故障导致闲置的最长时间(ms)，默认值为0",
//    label = "Max Backoff (ms)",
//    description = "Max ms that a client will remain inactive due to a previous failure with that host " +
//      "(default: 0, which effectively becomes 30000)",
    displayPosition = 50,
    group = "FLUME",
    dependsOn = "clientType",
    triggeredByValue = "AVRO_LOAD_BALANCING",
    min = 0,
    max = Integer.MAX_VALUE
  )
  public int maxBackOff;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.MODEL,
    defaultValue = "ROUND_ROBIN",
    label = "主机路由策略",
    description = "主机间负载均衡的策略",
//    label = "Host Selection Strategy",
//    description = "Strategy used to load balance between hosts",
    displayPosition = 60,
    group = "FLUME",
    dependsOn = "clientType",
    triggeredByValue = "AVRO_LOAD_BALANCING"
  )
  @ValueChooserModel(HostSelectionStrategyChooserValues.class)
  public HostSelectionStrategy hostSelectionStrategy;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.NUMBER,
    defaultValue = "100",
    label = "批处理事件数",
//    label = "Flume Batch Size (events)",
    displayPosition = 70,
    group = "FLUME",
    min = 1,
    max = Integer.MAX_VALUE
  )
  public int batchSize;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.NUMBER,
    defaultValue = "20000",
    label = "客户端连接超时(ms)",
//    label = "Flume Client Connection Timeout (ms)",
    displayPosition = 80,
    group = "FLUME",
    min = 1,
    max = Integer.MAX_VALUE
  )
  public int connectionTimeout;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.NUMBER,
    defaultValue = "20000",
    label = "客户端请求超时(ms)",
//    label = "Flume Client Request Timeout (ms)",
    description = "",
    displayPosition = 90,
    group = "FLUME",
    min = 1,
    max = Integer.MAX_VALUE
  )
  public int requestTimeout;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.NUMBER,
    defaultValue = "5",
    label = "最大重试次数",
    description = "当向Flume代理发送数据失败时，重试的次数",
//    label = "Max Retry Attempts",
//    description = "Number of times to resend data to the Flume agent in case of failures",
    displayPosition = 100,
    group = "FLUME",
    min = 0,
    max = Integer.MAX_VALUE
  )
  public int maxRetryAttempts;

  @ConfigDef(
    required = false,
    type = ConfigDef.Type.NUMBER,
    defaultValue = "10000", // 10 seconds
    label = "重试等待时间(ms)",
    description = "重发数据的等待事件",
//    label = "Retry Wait Time (ms)",
//    description = "Time to wait before resending data to Flume",
    displayPosition = 110,
    group = "FLUME",
    min = 1,
    max = Integer.MAX_VALUE
  )
  public long waitBetweenRetries;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.BOOLEAN,
    defaultValue = "false",
    label = "每批次仅一个事件",
    description = "在批处理中，所有记录产生唯一的Flume事件",
//    label = "One Event per Batch",
//    description = "Generates a single Flume event with all records in the batch",
    displayPosition = 120,
    group = "FLUME"
  )
  public boolean singleEventPerBatch;

  public boolean init (
      Stage.Context context,
      List<Stage.ConfigIssue> issues) {
    boolean valid = true;

    valid &= FlumeUtil.validateHostConfig(
        issues,
        flumeHostsConfig,
        Groups.FLUME.name(),
        FLUME_CONFIG_PREFIX + "flumeHostsConfig",
        context
    );
    if(batchSize < 1) {
      issues.add(
          context.createConfigIssue(
              Groups.FLUME.name(),
              FLUME_CONFIG_PREFIX + "batchSize",
              FlumeErrors.FLUME_104,
              "batchSize",
              1
          )
      );
      valid = false;
    }
    if(clientType == ClientType.AVRO_LOAD_BALANCING && backOff && maxBackOff < 0) {
      issues.add(
          context.createConfigIssue(
              Groups.FLUME.name(),
              FLUME_CONFIG_PREFIX + "maxBackOff",
              FlumeErrors.FLUME_104,
              "maxBackOff",
              0
          )
      );
      valid = false;
    }
    if(connectionTimeout < 1000) {
      issues.add(
          context.createConfigIssue(
              Groups.FLUME.name(),
              FLUME_CONFIG_PREFIX + "connectionTimeout",
              FlumeErrors.FLUME_104,
              "connectionTimeout",
              1000
          )
      );
      valid = false;
    }
    if(requestTimeout < 1000) {
      issues.add(
          context.createConfigIssue(
              Groups.FLUME.name(),
              FLUME_CONFIG_PREFIX + "requestTimeout",
              FlumeErrors.FLUME_104,
              "requestTimeout",
              1000
          )
      );
      valid = false;
    }
    if(maxRetryAttempts < 0) {
      issues.add(
          context.createConfigIssue(
              Groups.FLUME.name(),
              FLUME_CONFIG_PREFIX + "maxRetryAttempts",
              FlumeErrors.FLUME_104,
              "maxRetryAttempts",
              0
          )
      );
      valid = false;
    }
    if(waitBetweenRetries < 0) {
      issues.add(
          context.createConfigIssue(
              Groups.FLUME.name(),
              FLUME_CONFIG_PREFIX + "waitBetweenRetries",
              FlumeErrors.FLUME_104,
              "waitBetweenRetries",
              0
          )
      );
      valid = false;
    }

    if(valid) {
      connect();
    }
    return valid;
  }

  public void destroy() {
    if (client != null) {
      client.close();
      client = null;
    }
  }

  private RpcClient client;

  public void connect() {
    Properties props = new Properties();
    StringBuilder hosts = new StringBuilder();
    int numFlumeHosts = 0;
    for(Map.Entry<String, String> e : flumeHostsConfig.entrySet()) {
      hosts.append(e.getKey()).append(" ");
      props.setProperty(HOSTS_KEY + "." + e.getKey(), e.getValue());
      numFlumeHosts++;
    }
    props.setProperty(HOSTS_KEY, hosts.toString().trim());
    props.setProperty(BATCH_SIZE_KEY, String.valueOf(batchSize));
    props.setProperty(CONNECTION_TIMEOUT_KEY, String.valueOf(connectionTimeout));
    props.setProperty(REQUEST_TIMEOUT_KEY, String.valueOf(requestTimeout));

    switch(clientType) {
      case THRIFT:
        this.client = RpcClientFactory.getThriftInstance(props);
        break;
      case AVRO_FAILOVER:
        props.put(CLIENT_TYPE_KEY, CLIENT_TYPE_DEFAULT_FAILOVER);
        props.setProperty(MAX_ATTEMPTS_KEY, String.valueOf(numFlumeHosts));
        this.client = RpcClientFactory.getInstance(props);
        break;
      case AVRO_LOAD_BALANCING:
        props.put(CLIENT_TYPE_KEY, CLIENT_TYPE_DEFAULT_LOADBALANCING);
        props.setProperty(BACKOFF_KEY, String.valueOf(backOff));
        props.setProperty(MAX_BACKOFF_KEY, String.valueOf(maxBackOff));
        props.setProperty(HOST_SELECTOR_KEY, getHostSelector(hostSelectionStrategy));
        this.client = RpcClientFactory.getInstance(props);
        break;
      default:
        throw new IllegalStateException("Unsupported client type - cannot happen");
    }
  }

  private String getHostSelector(HostSelectionStrategy hostSelectionStrategy) {
    switch(hostSelectionStrategy) {
      case RANDOM:
        return HOST_SELECTOR_RANDOM;
      case ROUND_ROBIN:
        return HOST_SELECTOR_ROUND_ROBIN;
      default :
        throw new IllegalStateException("Unexpected Host Selection Strategy");
    }
  }

  public RpcClient getRpcClient() {
    return client;
  }

}
