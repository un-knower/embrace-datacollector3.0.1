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
package com.streamsets.pipeline.stage.origin.s3;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.common.DataFormatConstants;
import com.streamsets.pipeline.common.InterfaceAudience;
import com.streamsets.pipeline.common.InterfaceStability;

import java.util.List;

@InterfaceAudience.LimitedPrivate
@InterfaceStability.Unstable
public class S3FileConfig {

  private static final int MIN_OVERRUN_LIMIT = 64 * 1024;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.STRING,
    label = "前缀模式",
    description = "Ant样式的路径模式，定义前缀的剩余部分，不包括公共前缀。",
//    label = "Prefix Pattern",
//    description = "An Ant-style path pattern that defines the remaining portion of prefix excluding the common prefix",
    displayPosition = 100,
    group = "#0"
  )
  public String prefixPattern;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "LEXICOGRAPHICAL",
      label = "读顺序",
      description = "读对象的顺序基于最后修改的时间戳或字典升序键名称。" +
              "当使用时间戳顺序时，具有相同时间戳的对象则根据密钥名称排序。",
//      label = "Read Order",
//      description = "Read objects based on the last-modified timestamp or lexicographically ascending key names. " +
//          "When timestamp ordering is used, objects with the same timestamp are ordered based on key names.",
      displayPosition = 110,
      group = "#0"
  )
  @ValueChooserModel(ObjectOrderingChooseValues.class)
  public ObjectOrdering objectOrdering = ObjectOrdering.LEXICOGRAPHICAL;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.NUMBER,
    label = "文件池大小",
//    label = "File pool size",
    defaultValue = "100",
    description = "在列出并排序处理文件时，此属性控制将对多少文件进行排序和持久化。" +
            "当池中所有文件均转换完成后,数据采集器将才会处理新的文件",
//    description = "When listing and sorting files for processing, this property controls how many files will be sorted and persisted." +
//      " SDC will perform new file listing only after all the files in the pool have been transferred.",
    displayPosition = 115,
    group = "#0",
    min = 1,
    max = Integer.MAX_VALUE
  )
  public int poolSize = 100;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.NUMBER,
    label = "缓冲大小(KB)",
    defaultValue = "128",
    description = "限制缓冲区大小,以避免内存溢出",
//    label = "Buffer Limit (KB)",
//    defaultValue = "128",
//    description = "Low level reader buffer limit to avoid out of Memory errors",
    displayPosition = 120,
    group = "#0",
    min = 1,
    max = Integer.MAX_VALUE
  )
  public int overrunLimit;

  public void init(Stage.Context context, List<Stage.ConfigIssue> issues) {
    validate(context, issues);
  }

  private void validate(Stage.Context context, List<Stage.ConfigIssue> issues) {
    overrunLimit = overrunLimit * 1024; //convert to KB
    if (overrunLimit < MIN_OVERRUN_LIMIT || overrunLimit >= DataFormatConstants.MAX_OVERRUN_LIMIT) {
      issues.add(context.createConfigIssue(Groups.S3.name(), "overrunLimit", Errors.S3_SPOOLDIR_04, MIN_OVERRUN_LIMIT/1024 /* KB */, DataFormatConstants.MAX_OVERRUN_LIMIT/1024/1024 /* MB */));
    }
    if (prefixPattern == null || prefixPattern.isEmpty()) {
      issues.add(context.createConfigIssue(Groups.S3.name(), "prefixPattern", Errors.S3_SPOOLDIR_06));
    }
  }
}
