package com.shortener.URL.shortener.repository;

import com.shortener.URL.shortener.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {

    public URL findByShortURL (String shortUrl);
}
