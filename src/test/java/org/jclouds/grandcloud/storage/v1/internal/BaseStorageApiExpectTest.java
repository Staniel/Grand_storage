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
package org.jclouds.grandcloud.storage.v1.internal;

import java.net.URI;
import java.util.Date;

import org.jclouds.grandcloud.storage.v1.StorageApi;
import org.jclouds.grandcloud.storage.v1.reference.GrandCloudHeaders;
import org.jclouds.http.HttpRequest;
import org.testng.annotations.Test;
import com.google.common.net.HttpHeaders;

/**
 * Base class for writing Flavor Rest Api Expect tests
 * 
 * @author Changyuan Chen
 */
public class BaseStorageApiExpectTest extends BaseStorageExpectTest<StorageApi> {
@Test	
	protected static HttpRequest buildGET() {
	      URI uri = URI.create("http://storage-huabei-1.sdcloud.cn/coopis");
	      HttpRequest mytest= HttpRequest
	            .builder()
	            .method("GET")
	            .addHeader(HttpHeaders.DATE, "Wed, 01 May 2013 15:48:12 GMT")
	            .addHeader(HttpHeaders.AUTHORIZATION, "SNDA 2CKMD2SOZT0CC1INGWA4A0XC8:ggenoQzPeLbIBaUbNfEZ3lE0t6g=")
	            .endpoint(uri)
	            .build();
	     // System.out.print(mytest);
	      return mytest;
	   }
@Test	
protected static HttpRequest buildGETLocation() {
      URI uri = URI.create("http://storage-huabei-1.sdcloud.cn/coopis?location");
      HttpRequest mytest= HttpRequest
            .builder()
            .method("GET")
            .addHeader(HttpHeaders.DATE, "Wed, 01 May 2013 15:48:12 GMT")
            .addHeader(HttpHeaders.AUTHORIZATION, "SNDA 2CKMD2SOZT0CC1INGWA4A0XC8:ggenoQzPeLbIBaUbNfEZ3lE0t6g=")
            .endpoint(uri)
            .build();
     // System.out.print(mytest);
      return mytest;
   }

//	protected static HttpRequest buildDelete() {
//	      URI uri = URI.create("http://storage-huabei-1.sdcloud.cn/coopis/a.txt");
//	      return HttpRequest
//	            .builder()
//	            .method("DELETE")
//	            .addHeader(HttpHeaders.DATE, "Wed, 01 May 2013 15:48:12 GMT")
//	            .addHeader(HttpHeaders.AUTHORIZATION, "SNDA 2CKMD2SOZT0CC1INGWA4A0XC8:ggenoQzPeLbIBaUbNfEZ3lE0t6g=")
//	            .endpoint(uri)
//	            .build();
//	   }
	
}
