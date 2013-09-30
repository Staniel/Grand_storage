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
package org.jclouds.grandcloud.storage.v1.config;

import org.jclouds.grandcloud.storage.v1.xml.MultipartResponseJAXBParser;
import org.jclouds.grandcloud.storage.v1.xml.StorageJAXBParser;
import org.jclouds.json.config.GsonModule;
import org.jclouds.json.config.GsonModule.DateAdapter;
import org.jclouds.xml.XMLParser;

import com.google.inject.AbstractModule;

/**
 * @author Lixin Yao
 */
public class MultipartResponseParserModule extends AbstractModule {
   @Override
   protected void configure() {
      //bind(DateAdapter.class).to(GsonModule.Iso8601DateAdapter.class);
	   bind(XMLParser.class).to(MultipartResponseJAXBParser.class);
	   bind(DateAdapter.class).to(GsonModule.Iso8601DateAdapter.class);
   }
}
