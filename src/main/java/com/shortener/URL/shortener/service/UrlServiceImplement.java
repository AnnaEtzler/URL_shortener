package com.shortener.URL.shortener.service;

import com.shortener.URL.shortener.dto.UrlDto;
import com.shortener.URL.shortener.models.URL;
import com.shortener.URL.shortener.repository.URLRepository;
import io.micrometer.common.util.StringUtils;
import com.google.common.hash.Hashing;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
public class UrlServiceImplement implements UrlService{
    //private static final Logger logger = (Logger) LoggerFactory.getLogger(UrlServiceImplement.class);

    @Autowired
    private URLRepository urlRepository;


    @Override
    public URL generateShortUrl(String url) {
        // generating murmur3 based hash key as short URL
        String key = Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString();
        return URL.builder().id(key).creation_date(LocalDateTime.now()).shortUrl(url).build();
    }


    private String getExpirationDate(LocalDateTime expirationDate, LocalDateTime creationDate)
    {
        return null;
    }


    @Override
    public URL saveShortUrl(URL url) {
        URL urlToRet = urlRepository.save(url);
        return urlToRet;
    }


    @Override
    public URL getEncodedUrl(String url) {

        URL urlToRet = urlRepository.findByShortUrl(url);
        return urlToRet;
    }


    @Override
    public void deleteShortLink(URL url) {

        urlRepository.delete(url);
    }
}
