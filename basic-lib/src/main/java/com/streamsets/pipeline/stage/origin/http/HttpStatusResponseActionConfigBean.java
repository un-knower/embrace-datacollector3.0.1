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
package com.streamsets.pipeline.stage.origin.http;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ValueChooserModel;

public class HttpStatusResponseActionConfigBean extends HttpResponseActionConfigBean {

    public static final ResponseAction DEFAULT_ACTION = ResponseAction.RETRY_EXPONENTIAL_BACKOFF;
    public static final int DEFAULT_STATUS_CODE = 500;

    public HttpStatusResponseActionConfigBean(int statusCode, int maxNumRetries, long backoffInterval, ResponseAction action) {
        this.statusCode = statusCode;
        this.maxNumRetries = maxNumRetries;
        this.backoffInterval = backoffInterval;
        this.action = action;
    }

    @SuppressWarnings("unused")
    public HttpStatusResponseActionConfigBean() {
        // needed for UI
    }

    @ConfigDef(
        required = true,
        type = ConfigDef.Type.NUMBER,
//        label = "HTTP Status Code",
//        description = "Status code for which the other settings apply.  Only non-2xx (i.e. not OK) codes are permitted.",
//        defaultValue = ""+DEFAULT_STATUS_CODE,
//        group = "#0",
//        displayPosition = 1
            label = "HTTP状态代码",
            description = "在切换到其他设置时生效的状态码。仅准许non-2xx (i.e. not OK)",
            defaultValue = ""+DEFAULT_STATUS_CODE,
            group = "#0",
            displayPosition = 1
    )
    public int statusCode = DEFAULT_STATUS_CODE;

    @ConfigDef(
        required = true,
        type = ConfigDef.Type.MODEL,
//        label = "Action for status",
//        description = "Action to take when the configured status code is received from the HTTP server",
//        group = "#0",
//        defaultValue = "RETRY_EXPONENTIAL_BACKOFF",
//        displayPosition = 50
            label = "状态动作",
            description = "在接收到HTTP服务器的状态码所执行的动作",
            group = "#0",
            defaultValue = "RETRY_EXPONENTIAL_BACKOFF",
            displayPosition = 50
    )
    @ValueChooserModel(ResponseActionChooserValues.class)
    public ResponseAction action = ResponseAction.RETRY_EXPONENTIAL_BACKOFF;

    @ConfigDef(
        required = true,
        type = ConfigDef.Type.NUMBER,
//        label = "Base Backoff Interval (ms)",
//        description = "Base backoff interval in milliseconds.  Defaults to 1000 (1 second).",
//        defaultValue = ""+DEFAULT_BACKOFF_INTERVAL_MS,
//        group = "#0",
//        displayPosition = 100,
//        dependsOn = "action",
//        triggeredByValue = { "RETRY_LINEAR_BACKOFF", "RETRY_EXPONENTIAL_BACKOFF" }
            label = "基础backoff间隔 (ms)",
            description = "以毫秒为单位的基础backoff间隔.  默认为 1000 (1 秒).",
            defaultValue = ""+DEFAULT_BACKOFF_INTERVAL_MS,
            group = "#0",
            displayPosition = 100,
            dependsOn = "action",
            triggeredByValue = { "RETRY_LINEAR_BACKOFF", "RETRY_EXPONENTIAL_BACKOFF" }
    )
    public long backoffInterval = DEFAULT_BACKOFF_INTERVAL_MS;

    @ConfigDef(
        required = true,
        type = ConfigDef.Type.NUMBER,
//        label = "Max Retries",
//        description = "The maximum number of times to retry the request before failing the stage.  A negative value" +
//                " will be treated as unlimited (i.e. infinite retries).",
//        defaultValue = ""+DEFAULT_MAX_NUM_RETRIES,
//        group = "#0",
//        displayPosition = 150,
//        dependsOn = "action",
//        triggeredByValue = { "RETRY_LINEAR_BACKOFF", "RETRY_EXPONENTIAL_BACKOFF", "RETRY_IMMEDIATELY" }
            label = "最大重试次数",
            description = "在终止状态前最大重试次数.  负值将会被视为无限次 (i.e. 无限次重试).",
            defaultValue = ""+DEFAULT_MAX_NUM_RETRIES,
            group = "#0",
            displayPosition = 150,
            dependsOn = "action",
            triggeredByValue = { "RETRY_LINEAR_BACKOFF", "RETRY_EXPONENTIAL_BACKOFF", "RETRY_IMMEDIATELY" }
    )
    public int maxNumRetries = DEFAULT_MAX_NUM_RETRIES;

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public long getBackoffInterval() {
        return backoffInterval;
    }

    @Override
    public int getMaxNumRetries() {
        return maxNumRetries;
    }

    @Override
    public ResponseAction getAction() {
        return action;
    }
}
