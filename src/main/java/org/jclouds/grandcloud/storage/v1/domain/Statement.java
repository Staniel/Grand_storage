package org.jclouds.grandcloud.storage.v1.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.beans.ConstructorProperties;
import java.util.List;

import com.google.common.collect.Lists;

public class Statement {
	private String Sid;
	private String Effect;
	private String Action;
	private String Resource;
	//private List<Condition> conditions = Lists.newArrayList();
	// @ConstructorProperties({ "Sid","Effect","Action","Resource","Condition" })
	   public Statement(String sid, String effect,String action,String resources) {
	      this.Sid=sid;
	      this.Effect=effect;
	      this.Action=action;
	      this.Resource=resources;
	   }
	 public Statement() {}
	public String getsid()
	{
		return Sid;
	}
	public String getEffect()
	{
		return Effect;
	}
	public String getaction()
	{
		return Action;
	}
	public String getresource()
	{
		return Resource;
	}
	public void setsid(String Sid)
	{
		this.Sid=Sid;
	}
	public void setaction(String action)
	{
		this.Action=action;
	}
	public void setEffect(String Effect)
	{
		this.Effect=Effect;
	}
	public void setResources(String Resources)
	{
		this.Resource=Resources;
	}
	
}
