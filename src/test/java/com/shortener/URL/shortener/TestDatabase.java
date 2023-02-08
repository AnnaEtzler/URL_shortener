package com.shortener.URL.shortener;

import com.shortener.URL.shortener.controller.UrlController;
import com.shortener.URL.shortener.models.URL;
import com.shortener.URL.shortener.repository.URLRepository;
import com.shortener.URL.shortener.service.UrlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class TestDatabase {



    @Autowired
    URLRepository urlRepository;

    @Autowired
    UrlService urlService;


    @Test
    public void urlInDatabase() throws Exception {  // проверяет записывает ли в базе даннх объект url
        URL url = new URL();
        url.setLongUrl("https://github.com/AnnaEtzler/URL_shortener");
        String shortUrl = urlService.generateShortUrl(url.getLongUrl());
        while (urlService.checkShortUrl(shortUrl)) {
            shortUrl = urlService.changeShortUrl(shortUrl);
        }
        url.setShortUrl(shortUrl);
        urlRepository.save(url);
        System.out.println(urlRepository.findByShortUrl(shortUrl));

        assertEquals(url, urlRepository.findByShortUrl(shortUrl));
    }


    @Test
    public void theSameLengthUrlInDatabase() throws Exception { // проверяем все ли сгенерированные URL сокращаются до 8 знаков и уникальны ли они
        URL url = new URL();
        url.setLongUrl("https://habr.com/ru/post/5862/");
        String shortUrl = urlService.generateShortUrl(url.getLongUrl());
        while (urlService.checkShortUrl(shortUrl) ){
            shortUrl = urlService.changeShortUrl(shortUrl);
        }
        url.setShortUrl(shortUrl);
        urlRepository.save(url);

        URL url2 = new URL();
        url2.setLongUrl("https://github.com/AnnaEtzler/URL_shortener");
        String shortUrl2 = urlService.generateShortUrl(url2.getLongUrl());
        while (urlService.checkShortUrl(shortUrl2) ){
            shortUrl2 = urlService.changeShortUrl(shortUrl2);
        }
        url2.setShortUrl(shortUrl2);
        urlRepository.save(url2);
        List<URL> list = urlRepository.findAllByLongUrl("https://github.com/AnnaEtzler/URL_shortener");
        Assertions.assertEquals(list.size(), list.stream().map(URL::getShortUrl).collect(Collectors.toSet()).size()); // каждый обьект имеет уникальный shortUrl

    }


}
