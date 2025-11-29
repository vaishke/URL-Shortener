package com.URLShortener.dto;

public class UrlResponse {
	private String key;
    private String long_url;
    private String short_url;

    public UrlResponse() {}

    public UrlResponse(String key, String long_url, String short_url) {
        this.key = key;
        this.long_url = long_url;
        this.short_url = short_url;
    }

    public String getKey() {
        return key;
    }

    public String getLong_url() {
        return long_url;
    }

    public String getShort_url() {
        return short_url;
    }
}
