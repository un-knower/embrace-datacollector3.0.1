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
package com.streamsets.pipeline.stage.processor.identity;

import com.streamsets.pipeline.api.ExecutionMode;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Record;
import com.streamsets.pipeline.api.StageDef;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.api.base.SingleLaneRecordProcessor;

@GenerateResourceBundle
@StageDef(
    version = 1,
    label = "模拟效仿数据",
    description = "模仿每条接收到的记录，不含节点头信息",
//    label = "Dev Identity",
//    description = "It echoes every record it receives without changing, other than stage header information",
    icon="dev.png",
    execution = {
        ExecutionMode.STANDALONE,
        ExecutionMode.CLUSTER_BATCH,
        ExecutionMode.CLUSTER_YARN_STREAMING,
        ExecutionMode.CLUSTER_MESOS_STREAMING,
        ExecutionMode.EDGE
    },
    onlineHelpRefUrl = "index.html#Pipeline_Design/DevStages.html"
)
public class IdentityProcessor extends SingleLaneRecordProcessor {

  @Override
  protected void process(Record record, SingleLaneBatchMaker batchMaker) throws StageException {
    batchMaker.addRecord(record);
  }

}