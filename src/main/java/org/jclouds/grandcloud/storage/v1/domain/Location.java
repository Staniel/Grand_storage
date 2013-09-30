package org.jclouds.grandcloud.storage.v1.domain;


import java.beans.ConstructorProperties;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

//import org.jclouds.grandcloud.storage.v1.domain.Bucket.Builder;


@XmlRootElement(name = "LocationConstraint")
public class Location {
	@XmlValue
	protected String Loca;
	
	 protected Location(String Loca)
	 {
		 this.Loca=Loca;	 
	 }
	 protected Location() {}
	 
	 public String getLocation()
	 {
		 return Loca;
	 }

	  @Override
	    public String toString() {
	        return Loca;
	    }	
	    
}

