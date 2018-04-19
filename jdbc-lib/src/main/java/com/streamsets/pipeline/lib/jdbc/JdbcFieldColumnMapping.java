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
package com.streamsets.pipeline.lib.jdbc;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.FieldSelectorModel;
import com.streamsets.pipeline.api.ValueChooserModel;

public class JdbcFieldColumnMapping {

  /**
   * Constructor used for unit testing purposes
   * @param columnName
   * @param field
   */
  public JdbcFieldColumnMapping(final String columnName, final String field) {
    this(columnName, field, "", DataType.USE_COLUMN_TYPE);
  }

  /**
   * Constructor used for unit testing purposes
   * @param columnName
   * @param field
   * @param defaultValue
   * @param dataType
   */
  public JdbcFieldColumnMapping(final String columnName, final String field, final String defaultValue, final DataType dataType) {
    this.columnName = columnName;
    this.field = field;
    this.defaultValue = defaultValue;
    this.dataType = dataType;
  }

  /**
   * Parameter-less constructor required.
   */
  public JdbcFieldColumnMapping() {}

  @ConfigDef(
          required = true,
          type = ConfigDef.Type.STRING,
          defaultValue="",
          label = "列名",
          description = "数据库列名",
          displayPosition = 10
  )
  public String columnName;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "",
      label = "SDC 字段",
      description = "记录中的字段接收值.",
      displayPosition = 20
  )
  @FieldSelectorModel(singleValued = true)
  public String field;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      defaultValue = "",
      label = "默认值",
      description = "当数据库返回no行时使用的默认值.如果未设置，则在这种情况下将记录发送到错误",
      displayPosition = 30
  )
  public String defaultValue;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
      defaultValue = "USE_COLUMN_TYPE",
      label = "数据类型",
      description = "字段类型.默认情况下，将使用来自数据库的列类型.但是如果提供字段类型，它将覆盖列类型.注意，如果提供了默认值，则还必须提供字段类型",
      displayPosition = 40
  )
  @ValueChooserModel(DataTypeChooserValues.class)
  public DataType dataType;
}
