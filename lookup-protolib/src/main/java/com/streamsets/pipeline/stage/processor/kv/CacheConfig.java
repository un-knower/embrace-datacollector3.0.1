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
package com.streamsets.pipeline.stage.processor.kv;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ValueChooserModel;

import java.util.concurrent.TimeUnit;

public class CacheConfig {
  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
//      label = "Enable Local Caching",
//      description = "Select to enable caching of lookups. This improves performance, but should only be used when values rarely change",
      label = "启用本地缓存",
      description = "勾选启用查询缓存. 用于提高性能.适用于值很少更新的场景",
      displayPosition = 100,
      group = "#0"
  )
  public boolean enabled = false;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
//      label = "Maximum Entries to Cache",
      label = "缓存中的最大实体数",
      min = -1,
      defaultValue = "-1",
//      description = "Maximum number of values to cache. If exceeded, oldest values are evicted to make room. Default value is -1 which is unlimited",
      description = "缓存中保留的最大实体个数,如果超限,则将最旧的数据逐出. 默认值-1,表示不限制",
          dependsOn = "enabled",
      triggeredByValue = "true",
      displayPosition = 110,
      group = "#0"
  )
  public long maxSize = -1;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      label = "Eviction Policy Type",
//      description = "Policy type used to evict values from the local cache. " +
//          "Select whether to reset the expiration time after the last write or after the last access of the value.",
      label = "逐出策略类型",
      description = "本地缓存数据逐出策略:每次写后重置超时时长;每次读后重置超时时长",
      dependsOn = "enabled",
      triggeredByValue = "true",
      displayPosition = 120,
      group = "#0"
  )
  @ValueChooserModel(EvictionPolicyTypeChooserValues.class)
  public EvictionPolicyType evictionPolicyType;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
//      label = "Expiration Time",
      label = "超时时长",
      min = 0,
      defaultValue = "1",
      dependsOn = "enabled",
      triggeredByValue = "true",
      displayPosition = 130,
      group = "#0"
  )
  public long expirationTime;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      label = "Time Unit",
      label = "时长单位",
      defaultValue = "SECONDS",
      dependsOn = "enabled",
      triggeredByValue = "true",
      displayPosition = 140,
      group = "#0"
  )
  @ValueChooserModel(TimeUnitChooserValues.class)
  public TimeUnit timeUnit;
}
