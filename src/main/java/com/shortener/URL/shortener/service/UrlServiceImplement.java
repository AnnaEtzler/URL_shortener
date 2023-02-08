package com.shortener.URL.shortener.service;

import com.shortener.URL.shortener.models.URL;
import com.shortener.URL.shortener.repository.URLRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class UrlServiceImplement implements UrlService{

    @Autowired
    private URLRepository urlRepository;


    @Override
    public String generateShortUrl(String url) {
        String shortUrl = Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString();
        return shortUrl;
    }

    @Override
    public URL getEncodedUrl(String shortUrl) {
        URL longUrlToRet = urlRepository.findByShortUrl(shortUrl);
        return longUrlToRet;
    }

    @Override
    public void deleteShortLink(URL url) {

        urlRepository.delete(url);
    }

    @Override
    public boolean checkShortUrl(String shortUrl) {

        return urlRepository.findByShortUrl(shortUrl) != null;
    }

    @Override
    public String changeShortUrl(String shortUrl) {
        StringBuilder sb = new StringBuilder(shortUrl).delete(1,3).append((int)(Math.random()*100));
        return sb.toString();
    }
}
