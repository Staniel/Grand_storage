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
package org.jclouds.grandcloud.storage.v1.features;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.util.Set;

import org.jclouds.http.HttpRequest;
import org.jclouds.http.HttpResponse;
import org.jclouds.grandcloud.storage.v1.StorageApi;
import org.jclouds.grandcloud.storage.v1.domain.Bucket;
import org.jclouds.grandcloud.storage.v1.domain.Location;
import org.jclouds.grandcloud.storage.v1.internal.BaseStorageApiExpectTest;
import org.jclouds.grandcloud.storage.v1.reference.GrandCloudHeaders;
import org.testng.annotations.Test;

/**
 * Tests FlavorApi Guice wiring and parsing
 *
 * @author Changyuan Chen
 */
@Test(groups = "unit", testName = "BucketApiExpectTest")
public class BucketApiExpectTest extends BaseStorageApiExpectTest {

	
	
//   public void testListBuckets() {
//      URI endpoint = URI.create("http://172.16.0.1:8776/v1/3456/flavors");
//      BucketApi api = requestsSendResponses(
//            keystoneAuthWithUsernameAndPasswordAndTenantName,
//            responseWithKeystoneAccess,
//            authenticatedGET().endpoint(endpoint).build(),
//            HttpResponse.builder().statusCode(200).payload(payloadFromResource("/flavor_list.json")).build()
//      ).getStorageApiForZone("RegionOne");
//
//      Set<? extends Bucket> flavors = api.list().toSet();
//      assertEquals(flavors.size(),6);
//      assertEquals(flavors.iterator().next().getRam(), 512);
//   }

//   public void testListBucketsFail() {
//      URI endpoint = URI.create("http://172.16.0.1:8776/v1/3456/flavors");
//      BucketApi api = requestsSendResponses(
//            keystoneAuthWithUsernameAndPasswordAndTenantName,
//            responseWithKeystoneAccess,
//            authenticatedGET().endpoint(endpoint).build(),
//            HttpResponse.builder().statusCode(404).build()
//      ).getFlavorApiForZone("RegionOne");
//
//      Set<? extends Bucket> flavors = api.list().toSet();
//      assertTrue(flavors.isEmpty());
//   }   
//
//   public void testGetBucket() {
//	   
//	   HttpRequest request = buildGET();
//	   
//	   HttpResponse response = HttpResponse
//	            .builder()
//	            .statusCode(200)
//	            .payload(
//	                  payloadFromResource("/bucket_get.xml"))	            
//	            .build();
//	   
//	   BucketApi api = requestSendsResponse(request, response)
//	            .getStorageApi();
//	   
//	   Bucket bucket = api.get("coopis");
//	   
//	   assertEquals(bucket.getName(), "coopis");
//	   assertEquals(bucket.getMaxKey(), 1000);
//	   assertEquals(bucket.isTruncated(), false);
//	   
//   }
//   public void testGetBucketLocation() {
//	   
//	   HttpRequest request = buildGETLocation();
//	   
//	   HttpResponse response = HttpResponse
//	            .builder()
//	            .statusCode(200)
//	            .payload(
//	                  payloadFromResource("/bucket_location.xml"))	            
//	            .build();
//	   
//	   BucketApi api = requestSendsResponse(request, response)
//	            .getStorageApi();
//	   
//	   Location answer = api.getLocation("coopis");
//	   
////	   assertEquals(bucket.getName(), "coopis");
////	   assertEquals(bucket.getMaxKey(), 1000);
////	   assertEquals(bucket.isTruncated(), false);
//	   assertEquals(answer.getLocation(), "huadong-1");
//	   
//   }
//   public void testDeleteBucket() {
//	   
//	   HttpRequest request = buildGET();
//	   
//	   HttpResponse response = HttpResponse
//	            .builder()
//	            .statusCode(200)
//	            .payload(
//	                  payloadFromResource("/bucket_get.xml"))	            
//	            .build();
//	   
//	   BucketApi api = requestSendsResponse(request, response)
//	            .getStorageApi();
//	   
//	   Bucket bucket = api.get("coopis");
//	   
//	   assertEquals(bucket.getName(), "coopis");
//	   assertEquals(bucket.getMaxKey(), 1000);
//	   assertEquals(bucket.isTruncated(), false);
//	   
//   }
   
//   public void testGetBucketByAccountId() {
//	      URI endpoint = URI.create("http://172.16.0.1:8776/v1/3456/flavors/40806637803162");
//	      StorageApi redDwarfApi = requestsSendResponses(
//               keystoneAuthWithUsernameAndPasswordAndTenantName,
//               responseWithKeystoneAccess,
//               authenticatedGET().endpoint(endpoint).build(),
//               HttpResponse.builder().statusCode(200).payload(payloadFromResource("/flavor_list.json")).build() ); 
//	      BucketApi api = redDwarfApi.getFlavorApiForZone("RegionOne");
//
//	      Set<? extends Bucket> flavors = api.list( redDwarfApi.getCurrentTenantId().get().getId() ).toSet();
//	      Bucket flavor = flavors.iterator().next();
//	      assertEquals(flavor.getName(), "512MB Instance");
//	      assertEquals(flavor.getId(), 1);
//	      assertEquals(flavor.getRam(), 512);
//	      assertEquals(flavor.getLinks().size(), 2);
//	   }
//
//   public void testGetBucketFail() {
//      URI endpoint = URI.create("http://172.16.0.1:8776/v1/3456/flavors/12312");
//      BucketApi api = requestsSendResponses(
//            keystoneAuthWithUsernameAndPasswordAndTenantName,
//            responseWithKeystoneAccess,
//            authenticatedGET().endpoint(endpoint).build(),
//            HttpResponse.builder().statusCode(404).build()
//      ).getFlavorApiForZone("RegionOne");
//
//      assertNull(api.get(12312));
//   }   
}
