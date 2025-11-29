package com.URLShortener.controller;

import java.net.URI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.URLShortener.dto.*;
import com.URLShortener.model.*;
import com.URLShortener.service.*;

@RestController
@RequestMapping("/")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<?> shortenUrl(@RequestBody UrlRequest request) {
        if (request.getUrl() == null || request.getUrl().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Missing field: url");
        }

        UrlMapping mapping = urlService.shortenUrl(request.getUrl());
        UrlResponse response = new UrlResponse(
                mapping.getShortKey(),
                mapping.getLongUrl(),
                "http://localhost:8080/" + mapping.getShortKey()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortKey}")
    public ResponseEntity<Void> getLongUrl(@PathVariable String shortKey) {
        String longUrl = urlService.getLongUrl(shortKey);
        if (longUrl == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                            .location(URI.create(longUrl))
                            .build();
    }

}