package com.URLShortener.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("UrlMapping")
public class UrlMapping {
	
	@Id
	private String shortKey;
	private String longUrl;
	
    public UrlMapping(String shortKey, String longUrl) {
        this.shortKey = shortKey;
        this.longUrl = longUrl;
    }

	public String getShortKey() {
		return shortKey;
	}

	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
    
    @Override
    public String toString() {
    	return "UrlMapping{" + 
    		   "shortKey='" + shortKey + '\'' +
    		   ", longUrl='" + longUrl + '\'' +
    		   '}';
    }
}
