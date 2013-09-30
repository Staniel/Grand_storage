package org.jclouds.grandcloud.storage.v1.config;

import org.jclouds.json.config.GsonModule;
import org.jclouds.json.config.GsonModule.DateAdapter;

import com.google.inject.AbstractModule;

public class PolicyParserModule extends AbstractModule {
	   @Override
	   protected void configure() {
	      bind(DateAdapter.class).to(GsonModule.Iso8601DateAdapter.class);
	   }
	}
