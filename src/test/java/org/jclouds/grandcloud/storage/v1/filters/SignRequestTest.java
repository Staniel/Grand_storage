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
package org.jclouds.grandcloud.storage.v1.filters;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.jclouds.ContextBuilder;
import org.jclouds.date.TimeStamp;
import org.jclouds.grandcloud.storage.v1.config.StorageHttpApiModule;
import org.jclouds.grandcloud.storage.v1.reference.GrandCloudHeaders;
import org.jclouds.http.HttpRequest;
import org.jclouds.logging.config.NullLoggingModule;
import org.jclouds.rest.ConfiguresRestClient;
import org.jclouds.rest.internal.BaseRestApiTest.MockModule;
import org.jclouds.util.Strings2;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * 
 * @author Changyuan Chen
 */
@Test(groups = "unit")
public class SignRequestTest {

   private static final String EXPECTED_SIGNATURE = "cSkSOzr6GJhykbWRo7CPfhihEmo=";
   private static final String UID = "2CKMD2SOZT0CC1INGWA4A0XC8";
   private static final String DEFAULT_DATE = "Thu, 05 Jun 2008 16:38:19 GMT";
   private static final String KEY = "NGEzN2Y4NGQtNGM1YS00YWE3LThlODEtNGY3OGQ5YmMzYTQ2";
   private static final String DEFAULT_AUTHENTICATION = "SNDA " + UID + ":" + EXPECTED_SIGNATURE;

   private SignRequest filter;

//   @Test
//   void testCreateStringToSign() throws IOException {
//      String expects = Strings2.toStringAndClose(getClass().getResourceAsStream("/stringToSign"));
//      HttpRequest request = newRequest(preconstructedHeaders().build());
//      String toSign = filter.createStringToSign(request);
//      assertEquals(toSign, expects);
//   }

   @Test
   void testSignString() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
      HttpRequest request = newRequest(preconstructedHeaders().build());
      String toSign = filter.createStringToSign(request);
      String signature = filter.calculate(toSign);
      assertEquals(signature, EXPECTED_SIGNATURE);
   }

   @Test
   void testFilter() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
      HttpRequest request = newRequest(inputHeaders().build());
      request = filter.filter(request);
      assertEquals(request.getFirstHeaderOrNull(HttpHeaders.AUTHORIZATION), DEFAULT_AUTHENTICATION);
   }

   @Test
   void testFilterReplacesOldValues() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
      HttpRequest request = newRequest(inputHeaders().put(HttpHeaders.DATE, "foo").build());
      request = filter.filter(request);
      assertEquals(request.getFirstHeaderOrNull(HttpHeaders.AUTHORIZATION), DEFAULT_AUTHENTICATION);
   }

   @BeforeClass
   protected void createFilter() {
      Injector injector = ContextBuilder
            .newBuilder("grandcloud-storage")
            .credentials(UID, KEY)
            .modules(
                  ImmutableSet.<Module> of(new MockModule(), new TestAtmosRestClientModule(), new NullLoggingModule()))
            .buildInjector();

      filter = injector.getInstance(SignRequest.class);

   }

      @ConfiguresRestClient
   private static final class TestAtmosRestClientModule extends StorageHttpApiModule {

      @Override
      protected void configure() {
         super.configure();
      }

      @Override
      protected String provideTimeStamp(@TimeStamp Supplier<String> cache) {
         return DEFAULT_DATE;
      }
   }

   public HttpRequest newRequest(Multimap<String, String> headers) {
      HttpRequest request = HttpRequest.builder()
                                       .method("GET")
                                       .endpoint("http://storage-huabei-1.sdcloud.cn/coopis")
                                       .headers(headers).build();
      request.setPayload("");
      request.getPayload().getContentMetadata().setContentLength(4286l);
      request.getPayload().getContentMetadata().setContentType(MediaType.APPLICATION_OCTET_STREAM);
      return request;
   }

   protected Builder<String, String> preconstructedHeaders() {
      Builder<String, String> builder = inputHeaders();
      builder.put(HttpHeaders.DATE, DEFAULT_DATE);
      builder.put(HttpHeaders.AUTHORIZATION, DEFAULT_AUTHENTICATION);
//      builder.put(GrandCloudHeaders.UID, UID);
      return builder;
   }

   protected Builder<String, String> inputHeaders() {
      Builder<String, String> builder = ImmutableMultimap.builder();
//      builder.put(GrandCloudHeaders.LISTABLE_META, "part4/part7/part8=quick");
//      builder.put(GrandCloudHeaders.META, "part1=buy");
      builder.put(HttpHeaders.ACCEPT, "*/*");
//      builder.put(GrandCloudHeaders.USER_ACL, "john=FULL_CONTROL,mary=WRITE");
//      builder.put(GrandCloudHeaders.GROUP_ACL, "other=NONE");
//      builder.put(GrandCloudHeaders.DATE, DEFAULT_DATE);
//      builder.put(HttpHeaders.HOST, "10.5.115.118");
      return builder;
   }
}