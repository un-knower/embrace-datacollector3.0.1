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
package com.streamsets.pipeline.stage.processor.fieldtypeconverter;

import com.google.common.base.Preconditions;
import com.streamsets.pipeline.ZonedDateTimeFormatChooserValues;
import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.Field;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.CharsetChooserValues;
import com.streamsets.pipeline.config.DateFormat;
import com.streamsets.pipeline.config.DecimalScaleRoundingStrategy;
import com.streamsets.pipeline.config.DecimalScaleRoundingStrategyChooserValues;
import com.streamsets.pipeline.config.LocaleChooserValues;
import com.streamsets.pipeline.config.DateFormatChooserValues;
import com.streamsets.pipeline.config.PrimitiveFieldTypeChooserValues;
import com.streamsets.pipeline.config.ZonedDateTimeFormat;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Base configuration for target type
 */
public class BaseConverterConfig {

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      defaultValue="INTEGER",
//      label = "Convert to Type",
//      description = "Select a compatible data type",
//      displayPosition = 20

          defaultValue="INTEGER",
          label = "转换类型",
          description = "选择一个兼容的数据类型",
          displayPosition = 20
  )
  @ValueChooserModel(PrimitiveFieldTypeChooserValues.class)
  public Field.Type targetType;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
//      defaultValue = "false",
//      label = "Treat Input Field as Date",
//      description = "Select to convert input Long to DateTime before converting to a String",
//      displayPosition = 20,
//      dependsOn = "targetType",
//      triggeredByValue = "STRING"

      defaultValue = "false",
      label = "将输入字段视为日期",
      description = "在转换为字符串之前选择将输入Long转换为DateTime",
      displayPosition = 20,
      dependsOn = "targetType",
      triggeredByValue = "STRING"
  )
  public boolean treatInputFieldAsDate;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      defaultValue = "en,US",
//      label = "Data Locale",
//      description = "Affects the interpretation of locale sensitive data, such as using the comma as a decimal " +
//                    "separator",
//      displayPosition = 30,
//      dependsOn = "targetType",
//      triggeredByValue = {"BYTE", "INTEGER", "LONG", "DOUBLE", "DECIMAL", "FLOAT", "SHORT", "ZONED_DATETIME"}
      defaultValue = "en,US",
      label = "地域",
      description = "影响对语言环境敏感的数据解析，例如使用逗号作为小数点分隔符",
      displayPosition = 30,
      dependsOn = "targetType",
      triggeredByValue = {"BYTE", "INTEGER", "LONG", "DOUBLE", "DECIMAL", "FLOAT", "SHORT", "ZONED_DATETIME"}

  )
  @ValueChooserModel(LocaleChooserValues.class)
  public String dataLocale;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.NUMBER,
//      defaultValue = "-1",
//      label = "Scale",
//      description = "Decimal Value Scale",
//      displayPosition = 40,
//      dependsOn = "targetType",
//      triggeredByValue = {"DECIMAL"}

    defaultValue = "-1",
    label = "精度",
    description = "十进制值标度",
    displayPosition = 40,
    dependsOn = "targetType",
    triggeredByValue = {"DECIMAL"}
  )
  public int scale;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MODEL,
//      defaultValue = "ROUND_UNNECESSARY",
//      label = "Rounding Strategy",
//      description = "Rounding strategy during scale conversion",
//      displayPosition = 50,
//      dependsOn = "targetType",
//      triggeredByValue = {"DECIMAL"}

      defaultValue = "ROUND_UNNECESSARY",
      label = "舍入策略",
      description = "规模转换时的舍入策略",
      displayPosition = 50,
      dependsOn = "targetType",
      triggeredByValue = {"DECIMAL"}
  )
  @ValueChooserModel(DecimalScaleRoundingStrategyChooserValues.class)
  public DecimalScaleRoundingStrategy decimalScaleRoundingStrategy;

  private Locale locale;

  public Locale getLocale() {
    if (locale == null) {
      locale = LocaleChooserValues.getLocale(dataLocale);
    }
    return locale;
  }

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      defaultValue="YYYY_MM_DD",
//      label = "Date Format",
//      description="Select or enter any valid date or datetime format",
//      displayPosition = 40,
//      dependsOn = "targetType",
//      triggeredByValue = {"DATE", "DATETIME", "TIME", "STRING"}

      defaultValue="YYYY_MM_DD",
      label = "时间格式",
      description="选择或输入任何有效的日期或日期时间格式",
      displayPosition = 40,
      dependsOn = "targetType",
      triggeredByValue = {"DATE", "DATETIME", "TIME", "STRING"}
  )
  @ValueChooserModel(DateFormatChooserValues.class)
  public DateFormat dateFormat;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
//      defaultValue = "",
//      label = "Other Date Format",
//      displayPosition = 50,
//      dependsOn = "dateFormat",
//      triggeredByValue = "OTHER"
      defaultValue = "",
      label = "其他日期格式",
      displayPosition = 50,
      dependsOn = "dateFormat",
      triggeredByValue = "OTHER"
  )
  public String otherDateFormat;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      defaultValue="ISO_ZONED_DATE_TIME",
//      label = "Zoned DateTime Format",
//      description="Select or enter any valid date or datetime format",
//      displayPosition = 60,
//      dependsOn = "targetType",
//      triggeredByValue = {"ZONED_DATETIME", "STRING"}

      defaultValue="ISO_ZONED_DATE_TIME",
      label = "分区日期时间格式",
      description="选择或输入任何有效的日期或日期时间格式",
      displayPosition = 60,
      dependsOn = "targetType",
      triggeredByValue = {"ZONED_DATETIME", "STRING"}
  )
  @ValueChooserModel(ZonedDateTimeFormatChooserValues.class)
  public ZonedDateTimeFormat zonedDateTimeFormat;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
//      defaultValue = "",
//      label = "Other Zoned DateTime Format",
//      displayPosition = 70,
//      dependsOn = "zonedDateTimeFormat",
//      triggeredByValue = "OTHER"

      defaultValue = "",
      label = "其他分区日期时间格式",
      displayPosition = 70,
      dependsOn = "zonedDateTimeFormat",
      triggeredByValue = "OTHER"
  )
  public String otherZonedDateTimeFormat;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      defaultValue = "UTF-8",
//      label = "CharSet",
//      displayPosition = 80,
//      dependsOn = "targetType",
//      triggeredByValue = "STRING"

      defaultValue = "UTF-8",
      label = "字符集",
      displayPosition = 80,
      dependsOn = "targetType",
      triggeredByValue = "STRING"
  )
  @ValueChooserModel(CharsetChooserValues.class)
  public String encoding = "UTF-8";

  private DateTimeFormatter dateTimeFormatter;
  /**
   * Return configured date mask.
   */
  public String getDateMask() {
    return dateFormat != DateFormat.OTHER ? dateFormat.getFormat() : otherDateFormat;
  }

  public DateTimeFormatter getFormatter() {
    if (!targetType.isOneOf(Field.Type.STRING, Field.Type.ZONED_DATETIME)) {
      return null;
    }
    return zonedDateTimeFormat.getFormatter().orElseGet(() -> {
      Preconditions.checkNotNull(otherZonedDateTimeFormat,
          "Zoned Datetime format cannot be null");
      if (dateTimeFormatter == null) {
        dateTimeFormatter = DateTimeFormatter.ofPattern(otherZonedDateTimeFormat, getLocale());
      }
      return dateTimeFormatter;
    });
  }

}
