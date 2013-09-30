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
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.jclouds.grandcloud.storage.v1.domain.Bucket;
import org.jclouds.grandcloud.storage.v1.domain.MultiUploadResponse;
import org.jclouds.grandcloud.storage.v1.domain.Policy;
import org.jclouds.grandcloud.storage.v1.domain.Statement;
import org.jclouds.grandcloud.storage.v1.internal.BaseStorageApiLiveTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.FluentIterable;
import com.google.common.util.concurrent.ListenableFuture;

import org.jclouds.grandcloud.storage.v1.domain.Location;


/**
 * @author Changyuan Chen
 */
@Test(groups = "live", testName = "BucketApiLiveTest")
public class BucketApiLiveTest extends BaseStorageApiLiveTest {
	
	

    @Override
    @BeforeClass(groups = { "integration", "live" })
    public void setup() {
        super.setup();
    }

    private void checkBucket(Bucket bucket) {
        assertNotNull(bucket.getName(), "Name cannot be null for " + bucket);
    }

//    @Test
//    public void testListFlavorsByAccount() {
//        for (String zone : api.getConfiguredZones()) {
//        	BucketApi flavorApi = api.getFlavorApiForZone(zone);
//
//            FluentIterable<Bucket> response = flavorApi.list( api.getCurrentTenantId().get().getId() );  tenant id, but referred to as account id. 
//            for (Bucket flavor : response) {
//                checkBucket(flavor);
//            }  
//        }   
//    }

//    @Test
//    public void testListFlavorsByAccountWhenAccountIdNotFound() {
//        for (String zone : api.getConfiguredZones()) {
//        	BucketApi flavorApi = api.getFlavorApiForZone(zone);
//            assertTrue(flavorApi.list("9999").isEmpty());
//        }
//    }
    
    
    //Useful Test//
/*
 * Overall test about bucket api
 * contains get bucket, get bucket location, list multipartupload,get bucket policy
 * notice: create and delete is not included in auto test, but they are workable by manual test.
 * Author: Lixin Yao
 * */    
@Test
public void AllTest()
{
	BucketApi bucketApi = api.getStorageApi();
	
	//test get bucket
	Bucket bucket = bucketApi.get("yourbucket");
  assertEquals(bucket.getName(), "yourbucket");
	assertEquals(bucket.getMaxKey(), 1000);
	assertEquals(bucket.isTruncated(), false);
	
	//test get location
	Location location= bucketApi.getLocation("yourbucket");
	Location location1= bucketApi.getLocation("coopis");
	assertEquals(location.getLocation(), "huabei-1");
	assertEquals(location1.getLocation(), "");
	
	//test list Multipart
	MultiUploadResponse response=bucketApi.ListMultiUpload("yourbucket");
	assertEquals(response.getbucketname(), "yourbucket");
	
	//test get bucket policy
    Policy my=bucketApi.getpolicy("yourbucket");
    assertEquals(my.getID(), "a206cf64-e02a-4170-b95b-1211fc4d72b4");
    
    //test put policy
    Policy p=new Policy();
    p.setId("yaolixinpolicy");
	List<Statement> s=new ArrayList<Statement>();
	for (int i=0;i<1;i++)
	{
		Statement bb=new Statement();
		bb.setaction("storage:GetObject");
		bb.setEffect("Allow");
		bb.setsid("public-get-object");
		bb.setResources("*");
		s.add(bb);
	}
	p.setstatement(s);
	bucketApi.putpolicy("coopis", p);
	Policy ptest=bucketApi.getpolicy("coopis");
	 assertEquals(ptest.getID(), "yaolixinpolicy");
}



//@Test
//public void testDeleteBucketPolicy()
//{
//	BucketApi bucketApi = api.getStorageApi();
//	bucketApi.deletepolicy("yourbucket");
//}

//    @Test
//    public void testDeleteBucket() {
//        BucketApi bucketApi = api.getStorageApi();
//	    bucketApi.delete("zhigehaoshuai");
//    }  
//    
//    @Test
//    public void testCreateBucket() {
//        BucketApi bucketApi = api.getStorageApi();
//	    bucketApi.create("yao_test");
//    }


}