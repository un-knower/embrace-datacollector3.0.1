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
import com.streamsets.pipeline.api.FieldSelectorModel;
import com.streamsets.pipeline.api.ValueChooserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bean for configuring HTTP request pagination.
 */
public class PaginationConfigBean {
  private static final Logger LOG = LoggerFactory.getLogger(PaginationConfigBean.class);

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      label = "Pagination Mode",
//      defaultValue = "NONE",
//      group = "#0",
//      displayPosition = 20
      label = "分页方式",
      defaultValue = "NONE",
      group = "#0",
      displayPosition = 20
  )
  @ValueChooserModel(PaginationModeChooserValues.class)
  public PaginationMode mode = PaginationMode.NONE;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
//      label = "Initial Page/Offset",
//      description = "Value of ${startAt} variable the first time the pipeline is run or Reset Origin is invoked",
//      group = "#0",
//      displayPosition = 40,
//      dependsOn = "mode",
//      triggeredByValue = { "BY_PAGE", "BY_OFFSET" }
          label = "初始页码/偏移量",
          description = "在第一次运行任务或者重置数据源后的起始偏移量",
          group = "#0",
          displayPosition = 40,
          dependsOn = "mode",
          triggeredByValue = { "BY_PAGE", "BY_OFFSET" }
  )
  @FieldSelectorModel(singleValued = true)
  public int startAt = 0;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      label = "Result Field Path",
//      description = "Field path to parse as Records. The field type must be a list.",
//      group = "#0",
//      displayPosition = 40,
//      dependsOn = "mode",
//      triggeredByValue = { "LINK_HEADER", "BY_PAGE", "BY_OFFSET" }
      label = "结果字段位置",
      description = "解析为记录的字段所在位置. 字段类型必须为List",
      group = "#0",
      displayPosition = 40,
      dependsOn = "mode",
      triggeredByValue = { "LINK_HEADER", "BY_PAGE", "BY_OFFSET" }
  )
  @FieldSelectorModel(singleValued = true)
  public String resultFieldPath;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
//      label = "Keep All Fields",
//      description = "Includes all fields in the output record, rather than only fields from Result Field Path",
//      defaultValue = "false",
//      group = "#0",
//      displayPosition = 50,
//      dependsOn = "mode",
//      triggeredByValue = { "LINK_HEADER", "BY_PAGE", "BY_OFFSET" }
          label = "保留全部字段",
          description = "输出记录包含全部的字段",
          defaultValue = "false",
          group = "#0",
          displayPosition = 50,
          dependsOn = "mode",
          triggeredByValue = { "LINK_HEADER", "BY_PAGE", "BY_OFFSET" }
  )
  public boolean keepAllFields;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
//      label = "Wait Time Between Pages (ms)",
//      defaultValue = "2000",
//      description = "Time to wait between requests when paging (rate limit). E.g. 2000 would be 30 requests per minute",
//      min = 0,
//      group = "#0",
//      displayPosition = 50,
//      dependsOn = "mode",
//      triggeredByValue = { "LINK_HEADER", "BY_PAGE", "BY_OFFSET" }
          label = "翻页等待时间(ms)",
          defaultValue = "2000",
          description = "每页请求数据需要等待的时间. 例如： 2000代表每分钟30次",
          min = 0,
          group = "#0",
          displayPosition = 50,
          dependsOn = "mode",
          triggeredByValue = { "LINK_HEADER", "BY_PAGE", "BY_OFFSET" }
  )
  public long rateLimit = 2000;
}
