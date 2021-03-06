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
package org.jclouds.grandcloud.storage.v1.xml;

import java.io.IOException;
import java.io.StringReader;

import javax.inject.Singleton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.jclouds.grandcloud.storage.v1.domain.Bucket;
import org.jclouds.grandcloud.storage.v1.domain.Location;
import org.jclouds.grandcloud.storage.v1.domain.MultiUploadResponse;
import org.jclouds.http.HttpException;
import org.jclouds.xml.XMLParser;

/**
 * Parses XML documents using JAXB.
 * 
 * @author Changyuan Chen
 * @see org.jclouds.http.functions.ParseXMLWithJAXB
 */
@Singleton
public class StorageJAXBParser implements XMLParser {
   JAXBContext context;	

   public StorageJAXBParser() throws JAXBException {
     //context = JAXBContext.newInstance(Bucket.class.getPackage().getName(),Bucket.class.getClassLoader());
   }

   @Override
   public String toXML(final Object src) throws IOException {
      return toXML(src, src.getClass());
   }

   @Override
   public <T> String toXML(final Object src, final Class<T> type)
         throws IOException {
      throw new UnsupportedOperationException(
            "only marshaling from XML is implemented");
   }

   @SuppressWarnings("unchecked")
   @Override
   public <T> T fromXML(final String xml, final Class<T> type)
         throws IOException {
      T response = null;
      try {
         StringReader reader = new StringReader(xml);
         if (xml.contains("Location"))
         context = JAXBContext.newInstance(Location.class);
         else if (xml.contains("ListMultipartUploadsResult"))
        	 context = JAXBContext.newInstance(MultiUploadResponse.class);	 
         else
        	 context = JAXBContext.newInstance(Bucket.class.getPackage().getName(),Bucket.class.getClassLoader());
         Unmarshaller unmarshaller = context.createUnmarshaller();

         response = (T) unmarshaller.unmarshal(reader);
      } catch (Exception ex) {
         throw new IOException("Could not unmarshal document", ex);
      }

//      if (((StatusQuerable) response).isError()) {
//         throw new HttpException(
//               ((StatusQuerable) response).getResponseMessage());
//      }

      return response;
   }

}
