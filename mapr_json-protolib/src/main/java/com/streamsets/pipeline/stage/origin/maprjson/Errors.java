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
package com.streamsets.pipeline.stage.origin.maprjson;
import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum Errors implements ErrorCode {
  MAPR_JSON_ORIGIN_01("表名不能为空"),
  MAPR_JSON_ORIGIN_03("创建文档流失败. 主键：'{}' "),
  MAPR_JSON_ORIGIN_04("执行解析异常()"),
  MAPR_JSON_ORIGIN_05("创建解析工厂类时发生异常"),
  MAPR_JSON_ORIGIN_06("获取数据表引用时发生异常'{}'"),
  MAPR_JSON_ORIGIN_07("Thread.sleep()抛出异常"),
  MAPR_JSON_ORIGIN_08("重启文档流时发生异常. 主键值：'{}'"),
  MAPR_JSON_ORIGIN_09("关闭数据表时发生异常"),
  MAPR_JSON_ORIGIN_10("从输入中获取下一个文档时发生异常."),
  MAPR_JSON_ORIGIN_11("初始化数据格式配置失败"),
  MAPR_JSON_ORIGIN_12("获取文档样本失败. "),
  MAPR_JSON_ORIGIN_13("解析二进制主键发生异常.  宽度：'{}'，值：'{}' "),
//  MAPR_JSON_ORIGIN_01("Table name cannot be blank"),
//  MAPR_JSON_ORIGIN_03("Failed to create documentStream. key '{}' "),
//  MAPR_JSON_ORIGIN_04("Exception calling parse()"),
//  MAPR_JSON_ORIGIN_05("Exception creating parserFactory()"),
//  MAPR_JSON_ORIGIN_06("Exception getting reference to table '{}'"),
//  MAPR_JSON_ORIGIN_07("Exception from Thread.sleep()"),
//  MAPR_JSON_ORIGIN_08("Exception trying to restart documentStream. Key value '{}'"),
//  MAPR_JSON_ORIGIN_09("Exception Closing table"),
//  MAPR_JSON_ORIGIN_10("Exception fetching next document from input."),
//  MAPR_JSON_ORIGIN_11("Failed to initialize dataFormatConfig"),
//  MAPR_JSON_ORIGIN_12("Failed to fetch a sample document. "),
//  MAPR_JSON_ORIGIN_13("Exception parsing binary key.  width '{}' value '{}' "),
  ;

  private final String msg;

  Errors(String msg) {
    this.msg = msg;
  }

  @Override
  public String getCode() {
    return name();
  }

  @Override
  public String getMessage() {
    return msg;
  }
}
