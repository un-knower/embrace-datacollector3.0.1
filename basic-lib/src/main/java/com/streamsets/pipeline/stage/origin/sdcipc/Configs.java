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
package com.streamsets.pipeline.stage.origin.sdcipc;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.credential.CredentialValue;
import com.streamsets.pipeline.lib.http.HttpConfigs;
import com.streamsets.pipeline.lib.tls.TlsConfigBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configs extends HttpConfigs {
  private static final Logger LOG = LoggerFactory.getLogger(Configs.class);

  private static final String CONFIG_PREFIX = "config.";
  private static final String PORT = CONFIG_PREFIX + "port";

  @ConfigDefBean(groups = "TLS")
  public TlsConfigBean tlsConfigBean = new TlsConfigBean();

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
//      defaultValue = "20000",
//      label = "SDC RPC Listening Port",
//      description = "Port number to listen for data. Must match one of the port numbers used by the SDC RPC destination of the origin pipeline.",
//      displayPosition = 10,
//      group = "RPC",
//      min = 1,
//      max = 65535

      defaultValue = "20000",
      label = "远程数据采集服务监听端口",
      description = "监听数据端口号.必须与源任务的远程数据采集服务输出端使用的端口相匹配",
      displayPosition = 10,
      group = "RPC",
      min = 1,
      max = 65535
  )
  public int port;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.CREDENTIAL,
//      label = "SDC RPC ID",
//      description = "User-defined ID. Must match the SDC RPC ID used by the SDC RPC destination of the origin pipeline.",
//      displayPosition = 20,
//      group = "RPC"

      label = "远程数据采集服务标识",
      description = "用户定义的标识ID。必须与输出端配置的源任务标识匹配",
      displayPosition = 20,
      group = "RPC"
  )
  public CredentialValue appId = () -> "";

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
//      defaultValue = "5",
//      label = "Batch Wait Time (secs)",
//      description = "Maximum amount of time to wait for a batch before sending an empty one",
//      displayPosition = 30,
//      group = "RPC",
//      min = 1,
//      max = Integer.MAX_VALUE

      defaultValue = "5",
      label = "批处理等待时间(秒)",
      description = "在发送空文件之前等待批量的最长时间",
      displayPosition = 30,
      group = "RPC",
      min = 1,
      max = Integer.MAX_VALUE
  )
  public int maxWaitTimeSecs;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
//      defaultValue = "10",
//      label = "Max Record Size (MB)",
//      description = "",
//      displayPosition = 10,
//      group = "ADVANCED",
//      min = 1,
//      max = 100

          defaultValue = "10",
          label = "最大数据大小 (MB)",
          description = "",
          displayPosition = 10,
          group = "ADVANCED",
          min = 1,
          max = 100
  )
  public int maxRecordSize;

  public Configs() {
    super("RPC", "configs.");
  }

  @Override
  public int getPort() {
    return port;
  }

  @Override
  public int getMaxConcurrentRequests() {
    return 100;
  }

  @Override
  public CredentialValue getAppId() {
    return appId;
  }

  @Override
  public int getMaxHttpRequestSizeKB() {
    return 10000;
  }

  @Override
  public boolean isTlsEnabled() {
    return tlsConfigBean.isEnabled();
  }

  @Override
  public boolean isAppIdViaQueryParamAllowed() {
    return false;
  }

  @Override
  public TlsConfigBean getTlsConfigBean() {
    return tlsConfigBean;
  }
}
