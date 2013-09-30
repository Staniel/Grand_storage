package org.jclouds.grandcloud.storage.v1.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.beans.ConstructorProperties;
import java.util.Date;
import java.util.List;

import org.testng.collections.Lists;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class Policy {
	protected String Id;
	protected List<Statement> Statement;
	   public Policy(String Id, List<Statement> Statement) {
	      this.Id = checkNotNull(Id, "Id");
	      this.Statement=Statement;
	   }
	   public Policy() {}
//	public static Builder builder() {
//	      return new Builder();
//	   }
//
//	   public Builder toBuilder() {
//	      return builder().fromPolicy(this);
//	   }
//	   
//	   public static class Builder {
//	      private String Id;
//	      protected List<Statement> Statement;
//
//	      /**
//	       * @see Key#getName()
//	       */
//	      public Builder ID(String ID) {
//	         this.Id = ID;
//	         return this;
//	      }
//	      public Builder Statement(List<Statement> statements) {
//		         this.Statement = statements;		         
//		         return this;
//		      }
//	      public Policy build() {
//	         return new Policy(Id,Statement);//here is some problem
//	      }
//
//	      public Builder fromPolicy(Policy in) {
//	         return ID(in.getID()).Statement(in.getStatement());
//	      }
//	   }
	   /**
	    * Name for this key
	    */
	   public String getID() {
	      return Id;
	   }
	   public List<Statement> getStatement()
	   {
		   return Statement;
	   }
	   public void setId(String Id)
	   {
		   this.Id=Id;
	   }
	   public void setstatement(List<Statement> s)
	   {
		   this.Statement=s;
	   }

}
