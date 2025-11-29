# URL-Shortener

A simple URL shortener built with Spring Boot, Maven, and Redis.

## Features
- Shorten long URLs to 8-character short keys using Murmur3 32-bit hashing
- Redirect short keys to original URLs using HTTP 302
- Temporary storing of URL mappings in Redis for fast retrieval