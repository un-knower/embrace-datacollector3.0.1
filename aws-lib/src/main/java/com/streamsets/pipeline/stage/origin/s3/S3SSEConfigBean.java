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
package com.streamsets.pipeline.stage.origin.s3;

import com.streamsets.pipeline.api.credential.CredentialValue;
import com.streamsets.pipeline.api.ConfigDef;

public class S3SSEConfigBean {

  @ConfigDef(
      required = true,
      type = ConfigDef.Type.BOOLEAN,
      label = "使用用户密钥",
      description = "是否使用客户提供的密钥启用服务器端加密",
//      label = "Use Customer-Provided Encryption Keys",
//      description = "Whether or not server-side encryption is enabled with customer-provided keys",
      defaultValue = "false",
      displayPosition = 10,
      group = "#0"
  )
  public boolean useCustomerSSEKey;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.CREDENTIAL,
      label = "用户密钥",
      description = "256-bit、Base64编码的加密密钥,用于加密/解密数据",
//      label = "Customer Encryption Key",
//      description = "256-bit, base64-encoded encryption key for Amazon S3 to use to encrypt or decrypt your data",
      defaultValue = "",
      displayPosition = 20,
      dependsOn = "useCustomerSSEKey",
      triggeredByValue = "true",
      group = "#0"
  )
  public CredentialValue customerKey;

  @ConfigDef(
      required = false,
      type = ConfigDef.Type.CREDENTIAL,
      label = "用户密钥MD5",
      description = "Base64编码的128位MD5摘要加密密钥(RFC 1321)",
//      label = "Customer Encryption Key MD5",
//      description = "Base64-encoded 128-bit MD5 digest of the encryption key according to RFC 1321",
      defaultValue = "",
      displayPosition = 30,
      dependsOn = "useCustomerSSEKey",
      triggeredByValue = "true",
      group = "#0"
  )
  public CredentialValue customerKeyMd5;
}