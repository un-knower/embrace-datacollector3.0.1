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
package com.streamsets.pipeline.stage.destination.mongodb;

import com.mongodb.WriteConcern;
import com.streamsets.pipeline.api.GenerateResourceBundle;
import com.streamsets.pipeline.api.Label;

@GenerateResourceBundle
public enum WriteConcernLabel implements Label {
  ACKNOWLEDGED("已应答", WriteConcern.ACKNOWLEDGED),
  UNACKNOWLEDGED("未应答", WriteConcern.UNACKNOWLEDGED),
  FSYNCED("已同步", WriteConcern.FSYNCED),
  JOURNALED("日志", WriteConcern.JOURNALED),
  REPLICA_ACKNOWLEDGED("副本应答", WriteConcern.REPLICA_ACKNOWLEDGED),
  NORMAL("正常", WriteConcern.NORMAL),
  SAFE("安全", WriteConcern.SAFE),
  MAJORITY("多数已写入", WriteConcern.MAJORITY),
  FSYNC_SAFE("同步安全", WriteConcern.FSYNC_SAFE),
  JOURNAL_SAFE("日志安全", WriteConcern.JOURNAL_SAFE),
  REPLICAS_SAFE("副本安全", WriteConcern.REPLICAS_SAFE),
//  ACKNOWLEDGED("Acknowledged", WriteConcern.ACKNOWLEDGED),
//  UNACKNOWLEDGED("Unacknowledged", WriteConcern.UNACKNOWLEDGED),
//  FSYNCED("FSynced", WriteConcern.FSYNCED),
//  JOURNALED("Journaled", WriteConcern.JOURNALED),
//  REPLICA_ACKNOWLEDGED("Replica Acknowledged", WriteConcern.REPLICA_ACKNOWLEDGED),
//  NORMAL("Normal", WriteConcern.NORMAL),
//  SAFE("Safe", WriteConcern.SAFE),
//  MAJORITY("Majority", WriteConcern.MAJORITY),
//  FSYNC_SAFE("FSync Safe", WriteConcern.FSYNC_SAFE),
//  JOURNAL_SAFE("Journal Safe", WriteConcern.JOURNAL_SAFE),
//  REPLICAS_SAFE("Replicas Safe", WriteConcern.REPLICAS_SAFE),
  ;

  private final String label;
  private final WriteConcern writeConcern;

  WriteConcernLabel(String label, WriteConcern writeConcern) {
    this.label = label;
    this.writeConcern = writeConcern;
  }

  @Override
  public String getLabel() {
    return label;
  }

  public WriteConcern getWriteConcern() {
    return writeConcern;
  }

}
