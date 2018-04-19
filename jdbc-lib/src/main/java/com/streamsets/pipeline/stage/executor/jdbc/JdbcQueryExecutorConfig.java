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
package com.streamsets.pipeline.stage.executor.jdbc;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.Stage;
import com.streamsets.pipeline.api.StageException;
import com.streamsets.pipeline.lib.el.RecordEL;
import com.streamsets.pipeline.lib.jdbc.HikariPoolConfigBean;
import com.streamsets.pipeline.lib.jdbc.JdbcUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JdbcQueryExecutorConfig {

  private static final Logger LOG = LoggerFactory.getLogger(JdbcQueryExecutorConfig.class);

  @ConfigDefBean()
  public HikariPoolConfigBean hikariConfigBean;

  @ConfigDef(
    required = true,
    type = ConfigDef.Type.TEXT,
    mode = ConfigDef.Mode.SQL,
    label = "SQL查询",
    description = "对于每个传入记录应该执行的SQL查询",
    evaluation = ConfigDef.Evaluation.EXPLICIT,
    elDefs = { RecordEL.class },
    displayPosition = 20,
    group = "JDBC"
  )
  public String query;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "批量提交",
      description = "执行器是否应该提交每批处理",
      defaultValue = "true",
      displayPosition = 52,
      group = "ADVANCED"
  )
  public boolean batchCommit = true;

  private HikariDataSource dataSource = null;

  public void init(Stage.Context context, List<Stage.ConfigIssue> issues) {
    issues.addAll(hikariConfigBean.validateConfigs(context, issues));

    if(issues.isEmpty()) {
      try {
        dataSource = JdbcUtil.createDataSourceForRead(hikariConfigBean);
      } catch (StageException e) {
        LOG.error("Can't open connection", e);
        issues.add(
          context.createConfigIssue(
            Groups.JDBC.name(),
            "config.query",
            QueryExecErrors.QUERY_EXECUTOR_002,
            e.getMessage()
          )
      );
      }
    }
  }

  public void destroy() {
    if(dataSource != null) {
      dataSource.close();
    }
  }

  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }

}
