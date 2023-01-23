package com.shortener.URL_shortener.Repository;

import com.shortener.URL_shortener.Entities.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {
    public URL findByShortURL (String short_url);
}
