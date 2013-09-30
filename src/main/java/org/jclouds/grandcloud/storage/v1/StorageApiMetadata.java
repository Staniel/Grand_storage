/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jclouds.grandcloud.storage.v1;


import java.net.URI;
import java.util.Properties;

import org.jclouds.apis.ApiMetadata;
import org.jclouds.grandcloud.storage.v1.config.MultipartResponseHttpApiModule;
import org.jclouds.grandcloud.storage.v1.config.MultipartResponseParserModule;
import org.jclouds.grandcloud.storage.v1.config.PolicyHttpApiModule;
import org.jclouds.grandcloud.storage.v1.config.PolicyParserModule;
import org.jclouds.grandcloud.storage.v1.config.StorageParserModule;
import org.jclouds.grandcloud.storage.v1.config.StorageHttpApiModule;
import org.jclouds.grandcloud.storage.v1.config.LocationParserModule;
import org.jclouds.grandcloud.storage.v1.config.LocationHttpApiModule;
import org.jclouds.grandcloud.storage.v1.StorageApi;
import org.jclouds.rest.internal.BaseHttpApiMetadata;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Module;

/**
 * Implementation of {@link ApiMetadata} for GrandCloud API
 * 
 * @author Changyuan Chen
 */
public class StorageApiMetadata extends BaseHttpApiMetadata<StorageApi> {
      
   @Override
   public Builder toBuilder() {
      return new Builder().fromApiMetadata(this);
   }

   public StorageApiMetadata() {
      this(new Builder());
   }

   protected StorageApiMetadata(Builder builder) {
      super(builder);
   }

   public static Properties defaultProperties() {
      Properties properties = BaseHttpApiMetadata.defaultProperties();
      return properties;
   }

   public static class Builder extends BaseHttpApiMetadata.Builder<StorageApi, Builder> {

      protected Builder() {         
          id("grandcloud-storage")
         .name("GrandCloud Storage API")
         .identityName("	${tenantName}:${userName} or ${userName}, if your keystone supports a default tenant")
         .credentialName("${password}")
         .endpointName("Keystone base URL ending in /v2.0/")
         .documentation(URI.create("http://api.openstack.org/"))
         .version("1.0")
         .defaultEndpoint("http://storage-huabei-1.sdcloud.cn/")
         .defaultProperties(StorageApiMetadata.defaultProperties())
         .defaultModules(ImmutableSet.<Class<? extends Module>>builder()
                                     .add(StorageParserModule.class)
                                     .add(StorageHttpApiModule.class)
                                   //  .add(LocationParserModule.class)
                                    // .add(LocationHttpApiModule.class)
                                     //MultipartResponse
                          //           .add(MultipartResponseParserModule.class)
                           //          .add(MultipartResponseHttpApiModule.class)
                                   //  .add(PolicyParserModule.class)
                                    // .add(PolicyHttpApiModule.class)
                                     .build());
      }
      
      @Override
      public StorageApiMetadata build() {
         return new StorageApiMetadata(this);
      }

      @Override
      protected Builder self() {
         return this;
      }
   }
}
