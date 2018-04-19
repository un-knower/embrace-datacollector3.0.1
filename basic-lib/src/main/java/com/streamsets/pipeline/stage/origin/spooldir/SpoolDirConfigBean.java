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
package com.streamsets.pipeline.stage.origin.spooldir;

import com.streamsets.pipeline.api.ConfigDef;
import com.streamsets.pipeline.api.ConfigDefBean;
import com.streamsets.pipeline.api.ValueChooserModel;
import com.streamsets.pipeline.config.DataFormat;
import com.streamsets.pipeline.config.PostProcessingOptions;
import com.streamsets.pipeline.config.PostProcessingOptionsChooserValues;
import com.streamsets.pipeline.lib.dirspooler.PathMatcherMode;
import com.streamsets.pipeline.stage.origin.lib.DataParserFormatConfig;

public class SpoolDirConfigBean {

  @ConfigDefBean(groups = "FILES")
  public DataParserFormatConfig dataFormatConfig = new DataParserFormatConfig();

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      label = "Data Format",
//      description = "Format of data in the files",
//      displayPosition = 1,
//      group = "DATA_FORMAT"
          label = "数据格式",
          description = "文件内的数据格式",
          displayPosition = 1,
          group = "DATA_FORMAT"
  )
  @ValueChooserModel(DataFormatChooserValues.class)
  public DataFormat dataFormat;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
//      label = "Files Directory",
//      description = "Use a local directory",
//      displayPosition = 10,
//      group = "FILES"
          label = "文件目录",
          description = "使用本地文件路径",
          displayPosition = 10,
          group = "FILES"
  )
  public String spoolDir;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
//      defaultValue = "1",
//      label = "Number of Threads",
//      description = "Number of parallel threads that read data",
//      displayPosition = 11,
//      group = "FILES",
//      min = 1
          defaultValue = "1",
          label = "线程数",
          description = "读取文件所采用的并行处理线程数",
          displayPosition = 11,
          group = "FILES",
          min = 1
  )
  public int numberOfThreads = 1;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      label = "File Name Pattern Mode",
//      description = "Select whether the File Name Pattern specified uses glob pattern syntax or regex syntax.",
//      defaultValue = "GLOB",
//      displayPosition = 15,
//      group = "FILES"
          label = "文件名称匹配模式",
          description = "使用通配方式匹配或者正则表达式匹配文件名称",
          defaultValue = "GLOB",
          displayPosition = 15,
          group = "FILES"
  )
  @ValueChooserModel(PathMatcherModeChooserValues.class)
  public PathMatcherMode pathMatcherMode;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.STRING,
//      label = "File Name Pattern",
//      description = "A glob or regular expression that defines the pattern of the file names in the directory.",
//      displayPosition = 20,
//      group = "FILES"
          label = "文件名模式",
          description = "读取目录下各个文件名称的通配或者正则表达式",
          displayPosition = 20,
          group = "FILES"
  )
  public String filePattern;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.MODEL,
//      defaultValue = "LEXICOGRAPHICAL",
//      label = "Read Order",
//      description = "Read files based on the last-modified timestamp or lexicographically ascending file names. When timestamp ordering is used, files with the same timestamp are ordered based on file names.",
//      displayPosition = 30,
//      group = "FILES"
          defaultValue = "LEXICOGRAPHICAL",
          label = "读取顺序",
          description = "读取最后修改的时间戳或字典文件名的文件。时间戳排序使用，具有相同的时间戳文件是基于文件名顺序。",
          displayPosition = 30,
          group = "FILES"
  )
  @ValueChooserModel(FileOrderingChooseValues.class)
  public FileOrdering useLastModified = FileOrdering.LEXICOGRAPHICAL;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
//      defaultValue = "false",
//      label = "Process Subdirectories",
//      description = "Process files in subdirectories of Files Directory.  " +
//          "Only file names matching File Name Pattern will be processed.",
//      displayPosition = 40,
//      dependsOn = "useLastModified",
//      triggeredByValue = "TIMESTAMP",
//      group = "FILES"
          defaultValue = "false",
          label = "处理子目录",
          description = "出路文件目录下的子目录  " +
                  "仅有文件名称符合文件名读取模式的文件会被读取",
          displayPosition = 40,
          dependsOn = "useLastModified",
          triggeredByValue = "TIMESTAMP",
          group = "FILES"
  )
  public boolean processSubdirectories;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
//      label = "Allow Late Directory",
//      description = "Enables reading from a late-arriving directory." +
//          " When enabled, the origin does not validate the configured path.",
//      displayPosition = 50,
//      group = "FILES",
//      defaultValue = "false"
          label = "允许目录延迟",
          description = "允许读取延迟进入的目录" +
                  "启用后，数据源不验证配置的路径",
          displayPosition = 50,
          group = "FILES",
          defaultValue = "false"
  )
  public boolean allowLateDirectory = false;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
//      label = "Buffer Limit (KB)",
//      defaultValue = "128",
//      description = "Low level reader buffer limit to avoid out of Memory errors",
//      displayPosition = 70,
//      group = "FILES",
//      min = 1,
//      max = Integer.MAX_VALUE
          label = "缓存限制(KB)",
          defaultValue = "128",
          description = "缓冲区限制防止内存溢出",
          displayPosition = 70,
          group = "FILES",
          min = 1,
          max = Integer.MAX_VALUE
  )
  public int overrunLimit;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
//      label = "Batch Size (recs)",
//      defaultValue = "1000",
//      description = "Max number of records per batch",
//      displayPosition = 43,
//      group = "FILES",
//      min = 0,
//      max = Integer.MAX_VALUE
          label = "批量大小(recs)",
          defaultValue = "1000",
          description = "每批读取的最大记录数",
          displayPosition = 43,
          group = "FILES",
          min = 0,
          max = Integer.MAX_VALUE
  )
  public int batchSize;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.NUMBER,
//      defaultValue = "60",
//      label = "Batch Wait Time (secs)",
//      description = "Max time to wait for new files before sending an empty batch",
//      displayPosition = 48,
//      group = "FILES",
//      min = 1
          defaultValue = "60",
          label = "批处理等待时间(secs)",
          description = "等待新文件进入新批次的最大等待时间",
          displayPosition = 48,
          group = "FILES",
          min = 1
  )
  public long poolingTimeoutSecs;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.NUMBER,
//      defaultValue = "1000",
//      label = "Max Files in Directory",
//      description = "Max number of files in the directory waiting to be processed. Additional files cause the " +
//          "pipeline to fail.",
//      displayPosition = 60,
//      group = "FILES",
//      min = 1,
//      max = Integer.MAX_VALUE
          defaultValue = "1000",
          label = "最大文件数",
          description = "等待处理目录中最大文件数量，多余文件" +
                  "会导致任务失败.",
          displayPosition = 60,
          group = "FILES",
          min = 1,
          max = Integer.MAX_VALUE
  )
  public int maxSpoolFiles;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
      defaultValue = "",
//      label = "First File to Process",
//      description = "When configured, the Data Collector does not process earlier (naturally ascending order) file names",
//      displayPosition = 50,
//      group = "FILES"
          label = "初始文件",
          description = "当配置时，任务不会处理更早的文件(自然顺序)文件名",
          displayPosition = 50,
          group = "FILES"
  )
  public String initialFileToProcess;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
//      label = "Error Directory",
//      description = "Directory for files that could not be fully processed",
//      displayPosition = 100,
//      group = "POST_PROCESSING"
          label = "错误目录",
          description = "没有被全部处理的文件目录",
          displayPosition = 100,
          group = "POST_PROCESSING"
  )
  public String errorArchiveDir;

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.MODEL,
//      defaultValue = "NONE",
//      label = "File Post Processing",
//      description = "Action to take after processing a file",
//      displayPosition = 110,
//      group = "POST_PROCESSING"
          defaultValue = "NONE",
          label = "文件后处理",
          description = "处理文件后采取的动作",
          displayPosition = 110,
          group = "POST_PROCESSING"
  )
  @ValueChooserModel(PostProcessingOptionsChooserValues.class)
  public PostProcessingOptions postProcessing;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.STRING,
//      label = "Archive Directory",
//      description = "Directory to archive files after they have been processed",
//      displayPosition = 200,
//      group = "POST_PROCESSING",
//      dependsOn = "postProcessing",
//      triggeredByValue = "ARCHIVE"
          label = "归档目录",
          description = "文件处理完成后的归档目录",
          displayPosition = 200,
          group = "POST_PROCESSING",
          dependsOn = "postProcessing",
          triggeredByValue = "ARCHIVE"
  )
  public String archiveDir;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.NUMBER,
      defaultValue = "0",
//      label = "Archive Retention Time (mins)",
//      description = "How long archived files should be kept before deleting, a value of zero means forever",
//      displayPosition = 210,
//      group = "POST_PROCESSING",
//      dependsOn = "postProcessing",
//      triggeredByValue = "ARCHIVE",
//      min = 0
          label = "存档保留时间(mins)",
          description = "归档的目录文件再删除前的保留时间，0代表永久保留",
          displayPosition = 210,
          group = "POST_PROCESSING",
          dependsOn = "postProcessing",
          triggeredByValue = "ARCHIVE",
          min = 0
  )
  public long retentionTimeMins;
}
