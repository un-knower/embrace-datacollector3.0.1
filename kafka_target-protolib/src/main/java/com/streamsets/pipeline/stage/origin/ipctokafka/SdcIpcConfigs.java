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
package com.streamsets.pipeline.stage.origin.ipctokafka;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.credential.CredentialValue;
import com.streamsets.pipeline.lib.http.HttpConfigs;
import com.streamsets.pipeline.lib.tls.TlsConfigBean;

public class SdcIpcConfigs extends HttpConfigs {

  public SdcIpcConfigs() {
    super(Groups.RPC.name(), "configs.");
  }

  @ConfigDefBean(groups = "TLS")
  public TlsConfigBean tlsConfigBean = new TlsConfigBean();

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "20000",
      label = "远程服务监听端口",
      description = "数据监听端口.必须与所有数据源任务的远程数据采集服务输出端的端口之一相同",
//      label = "RPC Listening Port",
//      description = "Port number to listen for data. Must match one of the port numbers used by the SDC RPC " +
//          "destination of the origin pipeline.",
      displayPosition = 10,
      group = "RPC",
      min = 1,
      max = 65535
  )
  public int port;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "10",
      label = "最大请求并发数",
      description = "数据源允许的并发请求的最大数量。根据传入任务数、数据量和数据采集服务资源进行配置。",
//      label = "Max Concurrent Requests",
//      description = "Maximum number of concurrent requests allowed by the origin. Configure based on the number " +
//          "of incoming pipelines, volume of data, and Data Collector resources.",
      displayPosition = 15,
      group = "RPC",
      min = 1,
      max = 200
  )
  public int maxConcurrentRequests;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.CREDENTIAL,
      label = "远程数据采集服务标识",
      description = "用户定义的标识ID. 必须与数据源任务的远程服务输出端的标识ID相同.",
//      label = "RPC ID",
//      description = "User-defined ID. Must match the RPC ID used by the RPC destination of the origin pipeline.",
      displayPosition = 20,
      group = "RPC"
  )
  public CredentialValue appId;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "100000",
      label = "最大批处理请求大小(KB)",
      description = "可以在一个RPC调用中传输的数据的最大大小(KB)。",
//      label = "Max Batch Request Size (KB)",
//      description = "Maximum batch request size in KB. This is the maximum size of data that can be transferred " +
//          "in one RPC call.",
      displayPosition = 30,
      group = "RPC",
      min = 1,
      max = 500000
  )
  public int maxRpcRequestSize;

  @Override
  public int getPort() {
    return port;
  }

  @Override
  public int getMaxConcurrentRequests() {
    return maxConcurrentRequests;
  }

  @Override
  public CredentialValue getAppId() {
    return appId;
  }

  @Override
  public int getMaxHttpRequestSizeKB() {
    return maxRpcRequestSize;
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
