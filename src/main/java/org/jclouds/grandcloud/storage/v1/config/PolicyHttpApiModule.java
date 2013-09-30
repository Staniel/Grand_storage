package org.jclouds.grandcloud.storage.v1.config;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import org.jclouds.Constants;
import org.jclouds.date.DateService;
import org.jclouds.date.TimeStamp;
import org.jclouds.grandcloud.storage.v1.StorageApi;
import org.jclouds.grandcloud.storage.v1.handlers.StorageErrorHandler;
import org.jclouds.grandcloud.storage.v1.xml.LocationJAXBParser;
import org.jclouds.http.HttpErrorHandler;
import org.jclouds.http.annotation.ClientError;
import org.jclouds.http.annotation.Redirection;
import org.jclouds.http.annotation.ServerError;
import org.jclouds.json.config.GsonModule.DateAdapter;
import org.jclouds.json.config.GsonModule.Iso8601DateAdapter;
import org.jclouds.rest.ConfiguresHttpApi;
import org.jclouds.rest.config.HttpApiModule;
import org.jclouds.xml.XMLParser;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Provides;

@ConfiguresHttpApi
public class PolicyHttpApiModule extends HttpApiModule<StorageApi> {
   
   @Override
   protected void configure() {
	   bind(DateAdapter.class).to(Iso8601DateAdapter.class);
      super.configure();
   }
   
   @Provides
   @TimeStamp
   protected String provideTimeStamp(@TimeStamp Supplier<String> cache) {
      return cache.get();
//	   return "Wed, 01 May 2013 15:48:12 GMT";
   }
   
   /**
    * borrowing concurrency code to ensure that caching takes place properly
    */
   @Provides
   @TimeStamp
   Supplier<String> provideTimeStampCache(@Named(Constants.PROPERTY_SESSION_INTERVAL) long seconds,
            final DateService dateService) {
      return Suppliers.memoizeWithExpiration(new Supplier<String>() {
         public String get() {
            return dateService.rfc822DateFormat();
         }
      }, seconds, TimeUnit.SECONDS);
   }
   
   @Provides
   @Singleton
   public Multimap<URI, URI> aliases() {
      return ImmutableMultimap.<URI, URI>builder().build();
   }

   @Override
   protected void bindErrorHandlers() {
      bind(HttpErrorHandler.class).annotatedWith(Redirection.class).to(StorageErrorHandler.class);
      bind(HttpErrorHandler.class).annotatedWith(ClientError.class).to(StorageErrorHandler.class);
      bind(HttpErrorHandler.class).annotatedWith(ServerError.class).to(StorageErrorHandler.class);
   }
   
//   @Provides
//   Supplier<Optional<Tenant>> supplyTenant(Supplier<Access> access) {
//      return Suppliers.compose(GetTenant.INSTANCE, access);
//   }
//   
//   private static enum GetTenant implements Function<Access, Optional<Tenant>> {
//      INSTANCE;
//      public Optional<Tenant> apply(Access in){
//         return in.getToken().getTenant();
//      }
//   }
}
