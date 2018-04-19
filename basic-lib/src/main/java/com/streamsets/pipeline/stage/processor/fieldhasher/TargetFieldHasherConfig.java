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

public class TargetFieldHasherConfig extends FieldHasherConfig {
    @ConfigDef(
        required = false,
        type = ConfigDef.Type.MODEL,
//        defaultValue = "",
//        label = "Target Field",
//        description = "String field to store the hashed value. Creates the field if it does not exist.",
//        group = "RECORD_HASHING",
//        displayPosition = 30

            defaultValue = "",
            label = "目标字段",
            description = "存储哈希值的字符串字段。如果该字段不存在，则创建该字段。",
            group = "RECORD_HASHING",
            displayPosition = 30
    )
    @FieldSelectorModel(singleValued = true)
    public String targetField;

    @ConfigDef(
        required = false,
        type = ConfigDef.Type.STRING,
//        defaultValue = "",
//        label = "Header Attribute",
//        description = "Header attribute to store the hashed value. Creates the attribute if it does not exist.",
//        group = "RECORD_HASHING",
//        displayPosition = 40

            defaultValue = "",
            label = "头属性",
            description = "标题属性存储哈希值。如果该属性不存在，则创建该属性。",
            group = "RECORD_HASHING",
            displayPosition = 40
    )
    public String headerAttribute;
}
