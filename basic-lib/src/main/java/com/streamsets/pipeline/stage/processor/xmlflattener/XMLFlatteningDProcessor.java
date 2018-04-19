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
package com.streamsets.pipeline.stage.processor.xmlflattener;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigGroups;
import com.streamsets.pipeline.api.FieldSelectorModel;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Processor;
import com.streamsets.pipeline.api.StageDef;
import com.streamsets.pipeline.configurablestage.DProcessor;

@StageDef(
    version = 3,
//    label = "XML Flattener",
//    description = "Flatten XML data into fields of a record",
//    upgrader = XMLFlatteningProcessorUpgrader.class,
//    icon = "xmlparser.png",
//    onlineHelpRefUrl = "index.html#Processors/XMLFlattener.html#task_pmb_l55_sv"

    label = "XML平铺",
    description = "将XML数据平铺处理作为字段",
    upgrader = XMLFlatteningProcessorUpgrader.class,
    icon = "xmlparser.png",
    onlineHelpRefUrl = "index.html#Processors/XMLFlattener.html#task_pmb_l55_sv"
)
@ConfigGroups(value = Groups.class)
@GenerateResourceBundle
public class XMLFlatteningDProcessor extends DProcessor {

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue="",
//      label = "Field to Flatten",
//      description = "The field containing XML to flatten.",
//      displayPosition = 10,
//      group = "XML"

      label = "字段平铺",
      description = "包含XML的字段平铺",
      displayPosition = 10,
      group = "XML"
  )
  @FieldSelectorModel(singleValued = true)
  public String fromField;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "true",
//      label = "Keep Original Fields",
//      description = "Whether all fields in original record should be kept. " +
//          "If this is set, the root field of the record must be a Map or List Map.",
//      displayPosition = 20,
//      group = "XML"

      label = "保存原有字段",
      description = "是否保留原始记录中的所有字段。如果设置了，则记录的根字段必须是映射或列表映射。",
      displayPosition = 20,
      group = "XML"
  )
  public boolean keepOriginalFields;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "false",
//      label = "Overwrite Existing Fields",
//      displayPosition = 30,
//      group = "XML",
//      dependsOn = "keepOriginalFields",
//      triggeredByValue = "true"

          label = "覆盖现有字段",
          displayPosition = 30,
          group = "XML",
          dependsOn = "keepOriginalFields",
          triggeredByValue = "true"
  )
  public boolean newFieldOverwrites;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      defaultValue = "",
//      label = "Output Field",
//      description = "Output field into which the XML will be flattened. Use empty value to write directly to root of the record.",
//      displayPosition = 35,
//      group = "XML",
//      dependsOn = "keepOriginalFields",
//      triggeredByValue = "true"

          label = "输出字段",
          description = "XML将被放到其中的输出字段。使用空值直接写入记录的根目录",
          displayPosition = 35,
          group = "XML",
          dependsOn = "keepOriginalFields",
          triggeredByValue = "true"
  )
  public String outputField;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      defaultValue="",
//      label = "Record Delimiter",
//      description = "XML element used to delimit records. If this is not specified, only a single record is generated.",
//      displayPosition = 40,
//      group = "XML"

          label = "记录分隔符",
          description = "用于分隔记录的XML元素。如果未指定，则只生成一条记录。",
          displayPosition = 40,
          group = "XML"
  )
  public String recordDelimiter;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      defaultValue=".",
//      label = "Field Delimiter",
//      description = "The string used to separate entity names in the flattened field names.",
//      displayPosition = 50,
//      group = "XML"
          label = "字段分隔符",
          description = "用于分隔平整字段名称中的实体名称的字符串。",
          displayPosition = 50,
          group = "XML"
  )
  public String fieldDelimiter;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      defaultValue="#",
//      label = "Attribute Delimiter",
//      description = "The string used to separate attributes in the flattened field names.",
//      displayPosition = 60,
//      group = "XML"

          label = "属性分隔符",
          description = "用于分隔拼合字段名称中的属性的字符串。",
          displayPosition = 60,
          group = "XML"
  )
  public String attrDelimiter;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "false",
//      label = "Ignore Attributes",
//      description = "Whether attributes of elements should be ignored.",
//      displayPosition = 70,
//      group = "XML"

          label = "忽略属性",
          description = "元素的属性是否应该被忽略。",
          displayPosition = 70,
          group = "XML"
  )
  public boolean ignoreAttributes;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.BOOLEAN,
      defaultValue = "false",
//      label = "Ignore Namespace URI",
//      description = "Whether namespace URIs should be ignored.",
//      displayPosition = 80,
//      group = "XML"

          label = "忽略命名空间URI",
          description = "是否应该忽略名称空间URI。",
          displayPosition = 80,
          group = "XML"
  )
  public boolean ignoreNamespace;

  @Override
  protected Processor createProcessor() {
    return new XMLFlatteningProcessor(
      fromField,
      keepOriginalFields,
      newFieldOverwrites,
      outputField,
      recordDelimiter,
      fieldDelimiter,
      attrDelimiter,
      ignoreAttributes,
      ignoreNamespace
    );
  }
}
