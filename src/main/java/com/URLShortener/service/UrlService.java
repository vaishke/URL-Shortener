package com.URLShortener.service;

import java.nio.charset.StandardCharsets;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.URLShortener.model.*;
import com.URLShortener.repository.*;

@Service
public class UrlService {
	
	@Autowired
	private com.URLShortener.repository.UrlRepository urlRepo;
	
	private final Random random = new Random();
	
	public UrlMapping shortenUrl(String longUrl) {
		
        UrlMapping existingMapping = urlRepo.findByLongUrl(longUrl);
        if (existingMapping != null) {
            return existingMapping;
        }

        String shortKey = generateShortKey(longUrl);
        while (urlRepo.findById(shortKey).isPresent()) {
            longUrl += random.nextInt(1000);
            shortKey = generateShortKey(longUrl);
        }

        UrlMapping mapping = new UrlMapping(shortKey, longUrl);
        urlRepo.save(mapping);
        return mapping;
	}
	
	public String getLongUrl(String shortKey) {
        Optional<UrlMapping> mapping = urlRepo.findById(shortKey);
        return mapping.map(UrlMapping::getLongUrl).orElse(null);
    }
	
	private String generateShortKey(String input) {
	    HashFunction hashFunction = Hashing.goodFastHash(32);
	    return hashFunction.hashString(input, StandardCharsets.UTF_8).toString();
	}
}
