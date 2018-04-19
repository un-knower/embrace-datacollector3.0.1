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
package com.streamsets.datacollector.definition;

import com.streamsets.pipeline.api.ErrorCode;
import com.streamsets.pipeline.api.GenerateResourceBundle;

@GenerateResourceBundle
public enum DefinitionError implements ErrorCode {
  //ConfigValueExtractor
  DEF_000("{}, 触发值不能包含EL表达式'{}'"),
  DEF_001("{}, 配置字段'{}'必须是boolean类型"),
  DEF_002("{}, 配置字段是不是数字类型"),
  DEF_003("{}, 配置字段'{}'必须是字符串或枚举类型"),
  DEF_004("{}, 配置字段'{}'的值'{}'是无效的枚举"),
  DEF_005("{}, 配置字段'{}'必须是'java.lang.List'类型"),
  DEF_006("{}, 无法解析默认值'{}'为JSON数组: {}"),
  DEF_007("{}, 配置字段'{}'必须是'java.lang.Map'类型"),
  DEF_008("{}, 无法解析默认值'{}'为JSON集合"),
  DEF_009("{}, 配置字段不是字符类型"),
  DEF_010("{}, 值'{}'不是一个字符"),
  DEF_011("{}, 配置字段'{}'必须是字符串类型"),

  DEF_012("{}, 配置字段是'{}'枚举, 列表集合中含有无效的枚举值: {}"),
  DEF_013("{}, 配置字段'{}'必须有一个数字值"),

  DEF_014("{}, 配置字段'{}'必须是凭证值"),
//  DEF_000("{}, trigger values cannot have EL expressions '{}'"),
//  DEF_001("{}, configuration field is '{}', it must be a boolean type"),
//  DEF_002("{}, configuration field is not a numeric type"),
//  DEF_003("{}, configuration field is '{}', it must be 'String' or an enum"),
//  DEF_004("{}, configuration field is '{}', the value '{}' is an invalid enum"),
//  DEF_005("{}, configuration field is '{}', it must be 'java.lang.List'"),
//  DEF_006("{}, could not parse default value '{}' as a JSON array: {}"),
//  DEF_007("{}, configuration field is '{}', it must be 'java.lang.Map'"),
//  DEF_008("{}, could not parse default value '{}' as a JSON map"),
//  DEF_009("{}, configuration field is not a character type"),
//  DEF_010("{}, the value '{}' is not a character"),
//  DEF_011("{}, configuration field is '{}', it must be 'String'"),
//
//  DEF_012("{}, configuration field is a '{}' enum, the list has an invalid enum value: {}"),
//  DEF_013("{}, configuration field is '{}', must have a numeric value"),
//
//  DEF_014("{}, configuration field is '{}', it must be 'CredentialValue'"),

  //ELDefinitionExtractor
  DEF_050("{} 类='{}' 方法='{}', 方法必须是public的或EL函数"),
  DEF_051("{} 类='{}' 方法='{}', EL函数名不能为空"),
  DEF_052("{} 类='{}' 函数='{}', 方法必须是静态的"),
  DEF_053("{} 类='{}' 方法='{}', 无效的名称'{}'"),
  DEF_054("{} 类='{}' 方法='{}', 无效的前缀'{}'"),
  DEF_055("{} 类='{}' 方法='{}', 参数位置'{}'缺少'@ElParam'注解"),

  DEF_060("{} 类='{}' 字段='{}', 字段必须是对EL常数公开"),
  DEF_061("{} 类='{}' 字段='{}', EL常数名不能为空"),
  DEF_062("{} 类='{}' 函数='{}', 无效的名称'{}'"),
  DEF_063("{} 类='{}' 字段='{}', 无效的名称'{}'"),
//  DEF_050("{} Class='{}' Method='{}', method must be public to be an EL function"),
//  DEF_051("{} Class='{}' Method='{}', EL function name cannot be empty"),
//  DEF_052("{} Class='{}' Function='{}', method must be static"),
//  DEF_053("{} Class='{}' Method='{}', invalid name '{}'"),
//  DEF_054("{} Class='{}' Method='{}', invalid prefix '{}'"),
//  DEF_055("{} Class='{}' Method='{}', parameter at position '{}' has '@ElParam' annotation missing"),
//
//  DEF_060("{} Class='{}' Field='{}', field must public to be an EL constant"),
//  DEF_061("{} Class='{}' Field='{}', EL constant name cannot be empty"),
//  DEF_062("{} Class='{}' Function='{}', invalid name '{}'"),
//  DEF_063("{} Class='{}' Field='{}', invalid name '{}'"),

  //ConfigGroupExtractor
  DEF_100("{} 配置组='{}'不是枚举"),
  DEF_101("{} 多次定义组'{}'"),
//  DEF_100("{} ConfigGroup='{}' is not an enum"),
//  DEF_101("{} group '{}' defined more than once"),

  //ConfigDefinitionExtractor
  DEF_150("{} 类='{}' 字段='{}', field must public to be a configuration"),
  DEF_151("{} 类='{}' 字段='{}', field cannot be static to be a configuration"),
  DEF_152("{} 类='{}' 字段='{}', field cannot have both '@ConfigDef' and '@ConfigDefBean' annotations"),
  DEF_153("{}, 配置'{}'取决于一个不存在的配置 '{}'"),
  DEF_154("{} 类='{}' 字段='{}', 配置字段不能是final字段"),
  DEF_155("{} 类='{}' 字段='{}', 字段类型不是NUMBER, 不能定义最小或最大值"),
  DEF_156("{} 类'{}'没有公共默认构造函数"),
  DEF_157("{} dependsOn名称以单个'^'开头"),
  DEF_158("{} dependsOn名以多个'^'结尾，且中间不能有其他字符"),
  DEF_159("{} 字段有 {} '^' 但是它的bean深度仅 '{}' ('{}')"),
  DEF_160("{} bean没有任何配置属性"),
  DEF_161("{} 字段='{}'不能有@ComplexField配置"),
  DEF_162("{} ConfigDefBean不能是原型'{}'"),
  DEF_163("{} 组序号'{}'超出范围，有效范围'0..{}'"),
  DEF_164("{} 无效的组序号值: {}"),
  DEF_165("{} 组'{}'不在父组'{}'中"),
  DEF_166("{} 字段有显示评估,但定义中仅为隐式EL函数'{}'"),
//  DEF_150("{} Class='{}' Field='{}', field must public to be a configuration"),
//  DEF_151("{} Class='{}' Field='{}', field cannot be static to be a configuration"),
//  DEF_152("{} Class='{}' Field='{}', field cannot have both '@ConfigDef' and '@ConfigDefBean' annotations"),
//  DEF_153("{}, configuration '{}' depends on an non-existing configuration '{}'"),
//  DEF_154("{} Class='{}' Field='{}', field cannot be final to be a configuration"),
//  DEF_155("{} Class='{}' Field='{}', field type is not NUMBER, cannot define min or max"),
//  DEF_156("{} class '{}' does not have a public default constructor"),
//  DEF_157("{} dependsOn name starting with '^' can have only one '^'"),
//  DEF_158("{} dependsOn name ending with multiple '^' cannot have other characters in between"),
//  DEF_159("{} field has {} '^' but its bean depth is only '{}' ('{}')"),
//  DEF_160("{} bean does not have any configuration properties"),
//  DEF_161("{} Field='{}' there cannot be nested @ComplexField configs"),
//  DEF_162("{} a ConfigDefBean cannot be a primitive type '{}'"),
//  DEF_163("{} group ordinal '{}' out of range, valid range '0..{}'"),
//  DEF_164("{} invalid group ordinal value: {}"),
//  DEF_165("{} group '{}' not in parent groups '{}'"),
//  DEF_166("{} field has explicit evaluation but defines the EL function '{}' which is implicitOnly"),

  //ModelDefinitionExtractor
  DEF_200("{}, 模型注解缺失'"),
  DEF_201("{}, 只允许一个模式注解, {}"),
  DEF_202("{}, 未找到模型解析器'{}'"),
  DEF_210("{}, 无法评估ChooserValue: {}"),
  DEF_220("{}, 无法评估ChooserValue: {}"),
  DEF_230("{}, ComplexField配置必须是列表"),
//  DEF_200("{}, Model annotation missing'"),
//  DEF_201("{}, only one Model annotation is allowed, {}"),
//  DEF_202("{}, parser for Model '{}' not found"),
//  DEF_210("{}, could not evaluate ChooserValue: {}"),
//  DEF_220("{}, could not evaluate ChooserValue: {}"),
//  DEF_230("{}, ComplexField configuration must be a list"),

  //StageDefinitionExtractor
  DEF_300("{}没有正确注解"),
  DEF_301("{}版本号不能为空"),
  DEF_302("{}没有实现Source, Processor或Target接口"),
  DEF_303("{}数据源不能是错误处理节点"),
  DEF_304("{}只有数据源可以有RawSourcePreviewer"),
  DEF_305("{} outputStreams '{}'必须是枚举"),
  DEF_306("{} 数据源和执行器不能有输出流"),
  DEF_307("{} 节点至少支持一种执行模式"),
  DEF_308("{} 具有变量输出流的节点必须有'outputStreamsDrivenByConfig'配置"),
  DEF_309("{} outputStreamsDrivenByConfig='{}' 没有定义为配置"),
  DEF_310("{} 配置'{}' 有一个未定义的组 '{}'"),
  DEF_311("{} 类路径下无法找到图标文件 '{}'"),
  DEF_312("{} 仅输出端可以触发提交偏移量"),
  DEF_313("{} 隐藏了不存在的配置 {}"),
  DEF_314("{} OffsetCommitter值能是(拉取)数据源"),

  DEF_400("未找到节点库'{}', 文件'{}'"),
  DEF_401("节点库'{}',无法读取文件'{}': {}"),
//  DEF_300("{} does not have a proper annotation"),
//  DEF_301("{} version cannot be empty"),
//  DEF_302("{} does not implement Source, Processor nor Target"),
//  DEF_303("{} a SOURCE cannot be an ErrorStage"),
//  DEF_304("{} only a SOURCE can have a RawSourcePreviewer"),
//  DEF_305("{} outputStreams '{}' must be an enum"),
//  DEF_306("{} a TARGET and EXECUTOR cannot have an OutputStreams"),
//  DEF_307("{} the Stage must support at least one execution mode"),
//  DEF_308("{} A stage with VariableOutputStreams must have  Stage define a 'outputStreamsDrivenByConfig' config"),
//  DEF_309("{} outputStreamsDrivenByConfig='{}' not defined as configuration"),
//  DEF_310("{} configuration '{}' has an undefined group '{}'"),
//  DEF_311("{} icon file '{}' not found in the classpath"),
//  DEF_312("{} only a TARGET can trigger offset commit"),
//  DEF_313("{} is hiding non-existing config {}"),
//  DEF_314("{} OffsetCommitter can only be a (Pull) Source"),
//
//  DEF_400("Stage library '{}', file '{}' not found"),
//  DEF_401("Stage library '{}', could not read file '{}': {}"),

  // ServiceDefinitionExtractor
  DEF_500("{} 没有实现服务接口 {}"),
//  DEF_500("{} does not implement service interface {}"),

  ;

  private final String msg;

  DefinitionError(String msg) {
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
