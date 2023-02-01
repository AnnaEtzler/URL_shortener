package com.shortener.URL.shortener.service;

import com.shortener.URL.shortener.models.URL;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {
    public String generateShortUrl(String url);
    public URL getEncodedUrl(String shortUrl);
    public  void  deleteShortLink(URL url);
}
