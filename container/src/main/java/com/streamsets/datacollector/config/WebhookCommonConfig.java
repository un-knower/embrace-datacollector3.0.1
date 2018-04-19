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
package com.streamsets.datacollector.config;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.lib.el.RecordEL;

import java.util.HashMap;
import java.util.Map;

public abstract class WebhookCommonConfig {
	  private static final String DEFAULT_CONTENT_TYPE = "application/json";

	  @ConfigDef(
	      required = false,
	      type = ConfigDef.Type.STRING,
	      label = "Webhook地址",
//	      label = "Webhook URL",
	      description = "",
	      elDefs = RecordEL.class,
	      displayPosition = 200
	  )
	  public String webhookUrl = "";

	  @ConfigDef(
	      required = false,
	      type = ConfigDef.Type.MAP,
	      label = "请求头",
//	      label = "Headers",
	      defaultValue = "{}",
	      description = "请求包含请求头",
	      displayPosition = 210,
	      group = "NOTIFICATIONS"
	  )
	  public Map<String, String> headers = new HashMap<>();

	  @ConfigDef(
	      required = true,
	      type = ConfigDef.Type.MODEL,
	      label = "HTTP方法",
	      defaultValue = "POST",
	      description = "发送至HTTP的方法",
	      displayPosition = 220,
	      group = "NOTIFICATIONS"
	  )
	  @ValueChooserModel(HttpMethodChooserValues.class)
	  public HttpMethod httpMethod = HttpMethod.POST;

	  @ConfigDef(
	      required = false,
	      type = ConfigDef.Type.STRING,
	      label = "内容类型",
	      defaultValue = DEFAULT_CONTENT_TYPE,
	      description = "如果该标头未出现，则使用要与请求一起发送的内容类型标头",
	      displayPosition = 250,
	      dependsOn = "httpMethod",
	      evaluation = ConfigDef.Evaluation.EXPLICIT,
	      triggeredByValue = { "POST", "PUT", "DELETE"},
	      group = "NOTIFICATIONS"
	  )
	  public String contentType = DEFAULT_CONTENT_TYPE;

	  @ConfigDef(
	      required = true,
	      type = ConfigDef.Type.MODEL,
	      label = "认证类型",
	      defaultValue = "NONE",
	      displayPosition = 260,
	      group = "NOTIFICATIONS"
	  )
	  @ValueChooserModel(AuthenticationTypeChooserValues.class)
	  public AuthenticationType authType = AuthenticationType.NONE;

	  @ConfigDef(
	      required = true,
	      type = ConfigDef.Type.STRING,
	      label = "用户名",
	      displayPosition = 270,
	      group = "NOTIFICATIONS",
	      dependsOn = "authType",
	      triggeredByValue = { "BASIC", "DIGEST", "UNIVERSAL" }
	  )
	  public String username;

	  @ConfigDef(
	      required = true,
	      type = ConfigDef.Type.STRING,
	      label = "密码",
	      displayPosition = 280,
	      group = "NOTIFICATIONS",
	      dependsOn = "authType",
	      triggeredByValue = { "BASIC", "DIGEST", "UNIVERSAL" }
	  )
	  public String password;
	}

