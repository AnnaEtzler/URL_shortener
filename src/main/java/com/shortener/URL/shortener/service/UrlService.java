package com.shortener.URL.shortener.service;

import com.shortener.URL.shortener.models.URL;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {
    public URL generateShortUrl(String url);
    public URL saveShortUrl(URL url);
    public URL getEncodedUrl(String url);
    public  void  deleteShortLink(URL url);
}
