/*
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

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jclouds.Fallbacks.NullOnNotFoundOr404;

import org.jclouds.grandcloud.storage.v1.domain.Bucket;
import org.jclouds.grandcloud.storage.v1.domain.Location;
import org.jclouds.grandcloud.storage.v1.domain.MultiUploadResponse;
import org.jclouds.grandcloud.storage.v1.domain.Policy;
import org.jclouds.grandcloud.storage.v1.filters.SignRequest;
import org.jclouds.grandcloud.storage.v1.xml.LocationJAXBParser;
import org.jclouds.grandcloud.storage.v1.xml.MultipartResponseJAXBParser;
import org.jclouds.grandcloud.storage.v1.xml.StorageJAXBParser;
import org.jclouds.rest.annotations.BinderParam;
import org.jclouds.rest.annotations.EndpointParam;
//import org.jclouds.joyent.cloudapi.v6_5.domain.Dataset;
import org.jclouds.rest.annotations.Fallback;
import org.jclouds.rest.annotations.JAXBResponseParser;
import org.jclouds.rest.annotations.PayloadParam;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.ResponseParser;
import org.jclouds.rest.binders.BindToJsonPayload;

import com.google.common.util.concurrent.ListenableFuture;


/**
 * This API strictly for listing and retrieving Bucket.
 * 
 *      
 * @author Changyuan Chen
 */
public interface BucketApi {
   /**
    * Delete a Bucket by name.
    *
    * @return void
    */
   
	@Named("bucket:delete/{name}")
	@DELETE
	@Path("/{name}")
	@Fallback(NullOnNotFoundOr404.class)
	@JAXBResponseParser
	@RequestFilters(SignRequest.class)
	void delete(@PathParam("name") String bucketName);
	
	
	/**
	 * Delete a Bucket by name.
	 *
	 * @return void
	 */
	   
	@Named("bucket:put/{name}")
	@PUT
	@Path("/{name}")
	
	@Fallback(NullOnNotFoundOr404.class)
	@JAXBResponseParser
	@RequestFilters(SignRequest.class)
	void create(@PathParam("name") String bucketName);
   
   
	/**
	 * Returns a Bucket by name
	 *
	 * @return Bucket
	 */
	@Named("bucket:get/{name}")
	@GET
	@Path("/{name}")
	@Fallback(NullOnNotFoundOr404.class)
	@JAXBResponseParser(StorageJAXBParser.class)
	@RequestFilters(SignRequest.class)
	Bucket get(@PathParam("name") String bucketName);
   
  /**
   * Return the location of the bucket
   * @return Location
   * @author Lixin Yao
   * **/
	@Named("bucket:getLocation/{name}")
	@GET
	@Path("/{name}?location")
	@Fallback(NullOnNotFoundOr404.class)
	@JAXBResponseParser(LocationJAXBParser.class)
	@RequestFilters(SignRequest.class)
	Location getLocation(@PathParam("name") String bucketName);
	
	/**
	 * delete a Bucket policy 
	 *@author Lixin Yao
	 * 
	 */
	@Named("bucket policy:delete/{name}")
	@DELETE
	@Path("/{name}?policy")
	@Fallback(NullOnNotFoundOr404.class)
	@JAXBResponseParser
	@RequestFilters(SignRequest.class)
	void deletepolicy(@PathParam("name") String bucketName);
   
	/**
	 * put a Bucket policy 
	 *@author Lixin Yao
	 * 
	 */
	@Named("bucket policy:put/{name}")
	@PUT
	@Path("/{name}?policy")
	@Fallback(NullOnNotFoundOr404.class)
	 @Produces(MediaType.APPLICATION_JSON)
	@RequestFilters(SignRequest.class)
	void putpolicy(@PathParam("name") String bucketName, @BinderParam(BindToJsonPayload.class) Policy p);
	
	/**
	 * List Multipart Upload
	 *@author Lixin Yao
	 * @return 
	 * 
	 */
	@Named("bucket ListMultipartUpload/{name}")
	@GET
	@Path("/{name}?uploads")
	@Fallback(NullOnNotFoundOr404.class)
	@JAXBResponseParser(MultipartResponseJAXBParser.class)
	@RequestFilters(SignRequest.class)
	MultiUploadResponse ListMultiUpload(@PathParam("name") String bucketName);
	
	
	/*
	 * Get policy
	 * @author Lixin Yao
	 * */
	   @Named("bucket Getpolicy/{name}")
	   @GET
	   @Path("/{name}?policy")
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Fallback(NullOnNotFoundOr404.class)
	   @RequestFilters(SignRequest.class)
	   Policy getpolicy(@PathParam("name") String bucketname);
	
	
	
	
	
}
