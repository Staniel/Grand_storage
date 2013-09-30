/*
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
package org.jclouds.grandcloud.storage.v1.domain;

import static com.google.common.base.Preconditions.checkNotNull;
import java.beans.ConstructorProperties;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.collect.ImmutableList;

/**
 * A GrandCloud Storage Bucket.
 * 
 * @author Changyuan Chen
 */
//LocationConstraint
@XmlRootElement(name = "ListBucketResult")

public class Bucket implements Comparable<Bucket>{

	@XmlElement(name = "Name")
	private String name;
	@XmlElement(name = "MaxKeys")
    private int maxkey;
	@XmlElement(name = "IsTruncated")
    private boolean istruncated;
	
	@XmlElement(name = "Contents")
    private List<StorageObject> contents;

    @ConstructorProperties({
        "name", "maxkey", "istruncated", "contents"
    })
    protected Bucket(String name, int maxkey, boolean istruncated, List<StorageObject> contents) {
        this.name = checkNotNull(name, "name required");
        this.maxkey = maxkey;
        this.istruncated = istruncated;
        this.contents = contents;
    }
    
    protected Bucket(){}

    /**
     * @return the id of this flavor
     */
    public int getMaxKey() {
        return this.maxkey;
    }

    /**
     * @return the name of this flavor
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the RAM amount for this flavor
     */
    public boolean isTruncated() {
        return this.istruncated;
    }

    /**
     * @return the flavor links for this flavor. These are used during database instance creation.
     */
    public List<StorageObject> getContents() {
        return this.contents;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bucket that = Bucket.class.cast(obj);
        return Objects.equal(this.name, that.name);
    }
////
    protected ToStringHelper string() {
        return Objects.toStringHelper(this)
                .add("name", name).add("maxkey", maxkey).add("istruncated", istruncated);
    }

    @Override
    public String toString() {
        return string().toString();
    }

    @Override
    public int compareTo(Bucket that) {
        if (that == null)
            return 1;
        if (this == that)
            return 0;
        //return this.getName() > that.getName() ? +1 : this.getId() < this.getId() ? -1 : 0;
        return 1;
    }

    public static Builder builder() { 
        return new Builder();
    }

    public Builder toBuilder() { 
        return new Builder().fromBucket(this);
    }    
    
    public static class Builder {
        protected String name;
        protected int maxkey;
        protected boolean istruncated;
        protected List<StorageObject> contents;


        /** 
         * @see Flavor#getName()
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /** 
         * @see Flavor#getRam()
         */
        public Builder maxkey(int maxkey) {
            this.maxkey = maxkey;
            return this;
        }
        
        /** 
         * @see Flavor#isTruncated()
         */
        public Builder isTruncated(boolean istruncated) {
            this.istruncated = istruncated;
            return this;
        }

        /** 
         * @see Flavor#getLinks()
         */
        public Builder contents(List<StorageObject> contents) {
            this.contents = ImmutableList.copyOf(contents);
            return this;
        }

        public Bucket build() {
            return new Bucket(name, maxkey, istruncated, contents);
        }

        public Builder fromBucket(Bucket in) {
            return this
                    .name(in.getName())
                    .maxkey(in.getMaxKey())
                    .isTruncated(in.istruncated)
                    .contents(in.getContents());
        }
    }    
}
