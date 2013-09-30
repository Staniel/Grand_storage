package org.jclouds.grandcloud.storage.v1.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.beans.ConstructorProperties;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;


/**
 * An GrandCloud Storage Object.
 * 
 * @author Changyuan Chen
 */
public class StorageObject implements Comparable<StorageObject>{

	@XmlElement(name = "Key")
	private String key;
	
	@XmlElement(name = "LastModified")
	private Date lastModified;
	
	@XmlElement(name = "ETag")
	private String ETag;
	
	@XmlElement(name = "Size")
	private long size;

    @ConstructorProperties({
        "key", "lastModified", "ETag", "size"
    })
    protected StorageObject(String key, Date lastModified, String ETag, long size) {
        this.key = checkNotNull(key, "key required");
        this.lastModified = lastModified;
        this.ETag = ETag;
        this.size = size;
    }
    
    protected StorageObject(){}

    /**
     * @return the id of this flavor
     */
    public Date getLastModified() {
        return this.lastModified;
    }

    /**
     * @return the name of this flavor
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @return the RAM amount for this flavor
     */
    public String getETag() {
        return this.ETag;
    }

    /**
     * @return the flavor links for this flavor. These are used during database instance creation.
     */
    public long getSize() {
        return this.size;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.key);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StorageObject that = StorageObject.class.cast(obj);
        return Objects.equal(this.key, that.key);
    }

    protected ToStringHelper string() {
        return Objects.toStringHelper(this)
                .add("key", key).add("lastModified", lastModified).add("ETag", ETag).add("size", size);
    }

    @Override
    public String toString() {
        return string().toString();
    }

    @Override
    public int compareTo(StorageObject that) {
        if (that == null)
            return 1;
        if (this == that)
            return 0;
        //return this.getKey()() > that.getName() ? +1 : this.getId() < this.getId() ? -1 : 0;
        return 1;
    }

    public static Builder builder() { 
        return new Builder();
    }

    public Builder toBuilder() { 
        return new Builder().fromContent(this);
    }    
    
    public static class Builder {
    	
    	private String key;
    	private Date lastModified;
    	private String ETag;
    	private long size;


        /** 
         * @see Flavor#getName()
         */
        public Builder key(String key) {
            this.key = key;
            return this;
        }

        /** 
         * @see Flavor#getRam()
         */
        public Builder lastModified(Date lastModified) {
            this.lastModified = lastModified;
            return this;
        }
        
        /** 
         * @see Flavor#isTruncated()
         */
        public Builder ETag(String ETag) {
            this.ETag = ETag;
            return this;
        }

        /** 
         * @see Flavor#getLinks()
         */
        public Builder size(long size) {
            this.size = size;
            return this;
        }

        public StorageObject build() {
            return new StorageObject(key, lastModified, ETag, size);
        }

        public Builder fromContent(StorageObject in) {
            return this
                    .key(in.getKey())
                    .lastModified(in.getLastModified())
                    .ETag(in.getETag())
                    .size(in.getSize());
        }
    }    
}