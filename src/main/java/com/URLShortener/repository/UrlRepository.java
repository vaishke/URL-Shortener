package com.URLShortener.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.URLShortener.model.*;

@Repository
public interface UrlRepository extends CrudRepository<UrlMapping, String> {
	UrlMapping findByLongUrl(String longUrl);
}
