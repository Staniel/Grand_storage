package org.jclouds.grandcloud.storage.v1.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * List the multipart upload
 * @author Lixin Yao
 * */
@XmlRootElement(name = "ListMultipartUploadsResult")
public class MultiUploadResponse {
	@XmlElement(name="Bucket")
	private String Bucket;
	@XmlElement(name="NextKeyMarker")
	private String NextKeyMarker;
	@XmlElement(name="NextUploadIdMarker")
	private String NextUploadIdMarker;
	//
	@XmlElement(name="MaxUploads")
	private int MaxUploads;
	@XmlElement(name="IsTruncated")
	private boolean IsTruncated;
	@XmlElement(name = "Upload")
    private List<UploadInfo> Uploads;
	protected MultiUploadResponse(String Bucket, String NextKeyMarker,
			String NextUploadIdMarker, int MaxUploads, boolean IsTruncated )
	{
		this.Bucket=Bucket;
		this.NextKeyMarker=NextKeyMarker;
		this.NextUploadIdMarker=NextUploadIdMarker;
		this.MaxUploads=MaxUploads;
		this.IsTruncated=IsTruncated;
	}
	protected MultiUploadResponse ()
	{}
	public String getbucketname()
	{
		return Bucket;
	}
	public String getNextKeyMarker()
	{
		return NextKeyMarker;
	}
	public String getNextUploadIdMarker()
	{
		return NextUploadIdMarker;
	}
	public int getmax()
	{
		return MaxUploads;
	}
	public boolean getTruncated()
	{
		return IsTruncated;
	}
	public List<UploadInfo> getUpload()
	{
		return Uploads;
	}
	
}
