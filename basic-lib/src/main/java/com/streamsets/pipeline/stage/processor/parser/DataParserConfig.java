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
package com.streamsets.pipeline.stage.processor.parser;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.FieldSelectorModel;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.DataFormat;
import com.streamsets.pipeline.stage.common.MultipleValuesBehavior;
import com.streamsets.pipeline.stage.common.MultipleValuesBehaviorChooserValues;
import com.streamsets.pipeline.stage.origin.lib.DataParserFormatConfig;

public class DataParserConfig {

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "",
//      label = "Field to Parse",
//      description = "String field that contains the data to parse",
//      displayPosition = 10,
//      group = "PARSER"

          label = "解析字段",
          description = "包含要解析的数据的字符串字段",
          displayPosition = 10,
          group = "PARSER"
  )
  @FieldSelectorModel(singleValued = true)
  public String fieldPathToParse;



  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      label = "Data Format",
//      displayPosition = 1,
//      group = "DATA_FORMAT"

          label = "数据格式",
          displayPosition = 1,
          group = "DATA_FORMAT"
  )
  @ValueChooserModel(DataFormatChooserValues.class)
  public DataFormat dataFormat;

  @ConfigDefBean(groups = "PARSER")
  public DataParserFormatConfig dataFormatConfig;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "",
//      label = "Target Field",
//      description = "Name of the field to set the parsed data to",
//      displayPosition = 50,
//      group = "PARSER"

          label = "目标字段名",
          description = "设置解析数据的字段的名称",
          displayPosition = 50,
          group = "PARSER"
  )
  @FieldSelectorModel(singleValued = true)
  public String parsedFieldPath;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      label = "Multiple Values Behavior",
//      description = "How to handle multiple values produced by the parser",
//      defaultValue = "FIRST_ONLY",
//      displayPosition = 60,
//      group = "PARSER"

          label = "多值处理",
          description = "如何处理解析器产生的多个值",
          defaultValue = "FIRST_ONLY",
          displayPosition = 60,
          group = "PARSER"
  )
  @ValueChooserModel(MultipleValuesBehaviorChooserValues.class)
  public MultipleValuesBehavior multipleValuesBehavior = MultipleValuesBehavior.DEFAULT;

}
