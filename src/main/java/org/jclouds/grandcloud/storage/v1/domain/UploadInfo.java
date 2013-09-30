package org.jclouds.grandcloud.storage.v1.domain;

import javax.xml.bind.annotation.XmlElement;

public class UploadInfo {
	@XmlElement(name = "Key")
	private String key;
	@XmlElement(name = "UploadId")
	private String Id;
	@XmlElement(name = "Initiated")
	private String Initiated;
	protected UploadInfo(String key,String Id,String Initiated)
	{
		this.key=key;
		this.Id=Id;
		this.Initiated=Initiated;
	}
	protected UploadInfo() {}
	public String getkey()
	{return key;}
	public String getId()
	{return Id;}
	public String Initiated()
	{return Initiated;}
	
}
