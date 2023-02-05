package com.shortener.URL.shortener.repository;

import com.shortener.URL.shortener.models.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface URLRepository extends JpaRepository<URL, String> {
     URL findByShortUrl (String url);
     List<URL> findByMillisLessThan(long time);
}
