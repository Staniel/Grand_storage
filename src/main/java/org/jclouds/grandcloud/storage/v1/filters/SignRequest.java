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

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.io.BaseEncoding.base64;
import static com.google.common.io.ByteStreams.readBytes;
import static org.jclouds.Constants.LOGGER_SIGNATURE;
import static org.jclouds.crypto.Macs.asByteProcessor;
import static org.jclouds.util.Patterns.NEWLINE_PATTERN;
import static org.jclouds.util.Strings2.toInputStream;

import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.ws.rs.core.HttpHeaders;

import org.jclouds.crypto.Crypto;
import org.jclouds.date.TimeStamp;
import org.jclouds.domain.Credentials;
import org.jclouds.grandcloud.storage.v1.reference.GrandCloudHeaders;
import org.jclouds.http.HttpException;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.HttpRequestFilter;
import org.jclouds.http.HttpUtils;
import org.jclouds.http.internal.SignatureWire;
import org.jclouds.logging.Logger;
import org.jclouds.util.Strings2;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import com.google.common.io.ByteProcessor;

/**
 * Signs the GrandCloud Online Storage request.
 * 
 * @author Changyuan Chen
 * 
 */
@Singleton
public class SignRequest implements HttpRequestFilter {

   private final SignatureWire signatureWire;
   private final Supplier<Credentials> creds;
   private final Provider<String> timeStampProvider;
   private final Crypto crypto;
   private final HttpUtils utils;

   @Resource
   Logger logger = Logger.NULL;

   @Resource
   @Named(LOGGER_SIGNATURE)
   Logger signatureLog = Logger.NULL;

   @Inject
   public SignRequest(SignatureWire signatureWire, @org.jclouds.location.Provider Supplier<Credentials> creds,
         @TimeStamp Provider<String> timeStampProvider, Crypto crypto, HttpUtils utils) {
      this.signatureWire = signatureWire;
      this.creds = creds;
      this.timeStampProvider = timeStampProvider;
      this.crypto = crypto;
      this.utils = utils;
   }

   @Override
   public HttpRequest filter(HttpRequest request) throws HttpException {
	   String temp= request.getEndpoint().toString().replaceFirst("%3F", "?");
		  URI uri = URI.create(temp);
		  request=request.toBuilder().endpoint(uri).build();
		//  request.toBuilder().
      Builder<String, String> builder = ImmutableMap.builder();
      String date = timeStampProvider.get();
      builder.put(HttpHeaders.DATE, date);
      if (request.getHeaders().containsKey(GrandCloudHeaders.DATE))
         builder.put(GrandCloudHeaders.DATE, date);
      request = request.toBuilder().replaceHeaders(Multimaps.forMap(builder.build())).build();
      
      String stringToSign = createStringToSign(request);
      //System.out.println(stringToSign);
      String signature = calculateSignature(stringToSign);
      builder.put(HttpHeaders.AUTHORIZATION, "SNDA " + creds.get().identity + ":" + signature);
      request = request.toBuilder().replaceHeaders(Multimaps.forMap(builder.build())).build();
      utils.logRequest(signatureLog, request, "<<");
     
      return request;
   }

   public String createStringToSign(HttpRequest request) {
      utils.logRequest(signatureLog, request, ">>");
      StringBuilder buffer = new StringBuilder();
      // re-sign the request
      appendMethod(request, buffer);
      appendPayloadMetadata(request, buffer);
      appendHttpHeaders(request, buffer);
      appendCanonicalizedResource(request, buffer);
      appendCanonicalizedHeaders(request, buffer);
      
     
      if (signatureWire.enabled())
         signatureWire.output(buffer.toString());
      return buffer.toString();
   }

   private String calculateSignature(String toSign) {
      String signature = calculate(toSign);
      if (signatureWire.enabled())
         signatureWire.input(Strings2.toInputStream(signature));
      return signature;
   }
   
   public String calculate(String data) {
		checkNotNull(creds.get().credential);
		checkNotNull(data);
		try {
			SecretKeySpec signingKey = new SecretKeySpec(creds.get().credential.getBytes(Charsets.UTF_8), "HmacSHA1");

			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(signingKey);

			byte[] rawHmac = mac.doFinal(data.getBytes(Charsets.UTF_8));
			return new String(base64().encode(rawHmac));  //Base64.encodeBase64(rawHmac), Charsets.UTF_8);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		} catch (InvalidKeyException e) {
			throw new IllegalStateException(e);
		}
	}

   public String signString(String toSign) {
      try {
         ByteProcessor<byte[]> hmacSHA1 = asByteProcessor(crypto.hmacSHA1(base64().decode(creds.get().credential)));
         return base64().encode(readBytes(toInputStream(toSign), hmacSHA1));
      } catch (Exception e) {
         throw new HttpException("error signing request", e);
      }
   }

   private void appendMethod(HttpRequest request, StringBuilder toSign) {
      toSign.append(request.getMethod()).append("\n");
   }
   
   private static final Pattern TWO_SPACE_PATTERN = Pattern.compile("  ");

   private void appendCanonicalizedHeaders(HttpRequest request, StringBuilder toSign) {
      // TreeSet == Sort the headers alphabetically.
      Set<String> headers = Sets.newTreeSet(request.getHeaders().keySet());
      for (String header : headers) {
         if (header.startsWith("x-snda-") && !header.equals(GrandCloudHeaders.SIGNATURE)) {
            // Convert all header names to lowercase.
            toSign.append(header.toLowerCase()).append(":");
            // For headers with values that span multiple lines, convert them into one line by
            // replacing any
            // newline characters and extra embedded white spaces in the value.
            for (String value : request.getHeaders().get(header)) {
               value = Strings2.replaceAll(value, TWO_SPACE_PATTERN, " ");
               value = Strings2.replaceAll(value, NEWLINE_PATTERN, "");
               toSign.append(value).append(' ');
            }
            toSign.deleteCharAt(toSign.lastIndexOf(" "));
            // Concatenate all headers together, using newlines (\n) separating each header from the
            // next one.
            toSign.append("\n");
         }
      }
      // There should be no terminating newline character at the end of the last header.
      if (toSign.charAt(toSign.length() - 1) == '\n')
         toSign.deleteCharAt(toSign.length() - 1);
   }

   private void appendPayloadMetadata(HttpRequest request, StringBuilder buffer) {
	   for (String header : new String[] { "Range" })
	         buffer.append(HttpUtils.nullToEmpty(request.getHeaders().get(header)).toLowerCase()).append("\n");
	   buffer.append(
            Strings.nullToEmpty(request.getPayload() == null ? null : request.getPayload().getContentMetadata()
                  .getContentType())).append("\n");
   }

   @VisibleForTesting
   void appendHttpHeaders(HttpRequest request, StringBuilder toSign) {
      // Only the value is used, not the header
      // name. If a request does not include the header, this is an empty string.
     
      // Standard HTTP header, in UTC format. Only the date value is used, not the header name.
      toSign.append(request.getFirstHeaderOrNull(HttpHeaders.DATE)).append("\n");
   }

   @VisibleForTesting
   void appendCanonicalizedResource(HttpRequest request, StringBuilder toSign) {
      // Path portion of the HTTP request URI, in lowercase.
	   //toSign.append("\n");
	//   String temp=request.getEndpoint().getRawPath().toLowerCase();
	 toSign.append(request.getEndpoint().getRawPath().toLowerCase());
	   // temp=temp.replaceFirst("%3f", "?");
	//   toSign.append(temp);//.append("\n");
     if (request.getEndpoint().toString().toLowerCase().contains("?location"))
	 toSign.append("?location");
     if (request.getEndpoint().toString().toLowerCase().contains("?policy"))
    	 toSign.append("?policy");
     if (request.getEndpoint().toString().toLowerCase().contains("?uploads"))
    	 toSign.append("?uploads");
   }
}