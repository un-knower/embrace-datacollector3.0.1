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
package com.streamsets.pipeline.stage.processor.fieldhasher;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.FieldSelectorModel;
import com.streamsets.pipeline.api.ValueChooserModel;

public class RecordHasherConfig {

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue="false",
//      label = "Hash Entire Record",
//      description = "",
//      displayPosition = 10,
//      group = "RECORD_HASHING"

      label = "哈希整条记录",
      description = "",
      displayPosition = 10,
      group = "RECORD_HASHING"
  )
  public boolean hashEntireRecord;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
//      defaultValue = "false",
//      label = "Include Record Header",
//      description = "Include the record header for hashing",
//      displayPosition = 20,
//      dependsOn = "hashEntireRecord",
//      triggeredByValue = "true",
//      group = "RECORD_HASHING"

          defaultValue = "false",
          label = "包含记录头部",
          description = "哈希包含记录头部",
          displayPosition = 20,
          dependsOn = "hashEntireRecord",
          triggeredByValue = "true",
          group = "RECORD_HASHING"
  )
  public boolean includeRecordHeaderForHashing;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
//      defaultValue = "false",
//      label = "Use Field Separator",
//      description = "Separate fields with null before hashing",
//      displayPosition = 25,
//      dependsOn = "hashEntireRecord",
//      triggeredByValue = "true",
//      group = "RECORD_HASHING"

          defaultValue = "false",
          label = "使用字段分隔符",
          description = "散列前用空值分隔字段",
          displayPosition = 25,
          dependsOn = "hashEntireRecord",
          triggeredByValue = "true",
          group = "RECORD_HASHING"
  )
  public boolean useSeparator;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      defaultValue="MD5",
//      label = "Hash Type",
//      description = "",
//      displayPosition = 30,
//      dependsOn = "hashEntireRecord",
//      triggeredByValue = "true",
//      group = "RECORD_HASHING"

          defaultValue="MD5",
          label = "哈希类型",
          description = "",
          displayPosition = 30,
          dependsOn = "hashEntireRecord",
          triggeredByValue = "true",
          group = "RECORD_HASHING"
  )
  @ValueChooserModel(HashTypeChooserValues.class)
  public HashType hashType;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MODEL,
//      defaultValue = "",
//      label = "Target Field",
//      description = "String field to store the hashed value. Creates the field if it does not exist.",
//      group = "RECORD_HASHING",
//      dependsOn = "hashEntireRecord",
//      triggeredByValue = "true",
//      displayPosition = 40

          defaultValue = "",
          label = "目标字段",
          description = "存储哈希值的字符串字段。如果该字段不存在，则创建该字段。",
          group = "RECORD_HASHING",
          dependsOn = "hashEntireRecord",
          triggeredByValue = "true",
          displayPosition = 40
  )
  @FieldSelectorModel(singleValued = true)
  public String targetField;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      defaultValue = "",
//      label = "Header Attribute",
//      description = "Header attribute to store the hashed value. Creates the attribute if it does not exist.",
//      group = "RECORD_HASHING",
//      dependsOn = "hashEntireRecord",
//      triggeredByValue = "true",
//      displayPosition = 50

          label = "头属性",
          description = "标题属性存储哈希值。如果该属性不存在，则创建该属性。",
          group = "RECORD_HASHING",
          dependsOn = "hashEntireRecord",
          triggeredByValue = "true",
          displayPosition = 50
  )
  public String headerAttribute;

}
