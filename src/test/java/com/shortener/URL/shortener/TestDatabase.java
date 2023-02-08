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
    public void urlInDatabase() throws Exception {  // проверяет есть ли в базе даннх объект url
        URL url = new URL();
        url.setLongUrl("https://github.com/AnnaEtzler/URL_shortener");
        url.setShortUrl( urlService.generateShortUrl("https://github.com/AnnaEtzler/URL_shortener"));
        urlRepository.save(url);
        assertEquals(url, urlRepository.findByLongUrl("https://github.com/AnnaEtzler/URL_shortener"));
    }


    @Test
    public void theSameLengthUrlInDatabase() throws Exception { // проверяем все ли сгенерированные URL сокращаются до 8 знаков и уникальны ли они
        URL url = new URL();
        url.setLongUrl("https://github.com/AnnaEtzler/URL_shortener");
        url.setShortUrl( urlService.generateShortUrl("https://github.com/AnnaEtzler/URL_shortener"));
        while (urlService.checkShortUrl(url.getShortUrl())) {
            url.setShortUrl(urlService.changeShortUrl(url.getShortUrl()));
        }
        urlRepository.save(url);
        URL url2 = new URL();
        url2.setLongUrl("https://github.com/AnnaEtzler/URL_shortener");
        url2.setShortUrl( urlService.generateShortUrl("https://github.com/AnnaEtzler/URL_shortener"));
        while (urlService.checkShortUrl(url2.getShortUrl())) {
            url2.setShortUrl(urlService.changeShortUrl(url2.getShortUrl()));
        }
        urlRepository.save(url2);
        List<URL> list = urlRepository.findAllByLongUrl("https://github.com/AnnaEtzler/URL_shortener");
        list.forEach(u -> Assertions.assertEquals(8, url.getShortUrl().length()));  // все ли url имеют длинну 8
        Assertions.assertEquals(list.size(), list.stream().map(URL::getShortUrl).collect(Collectors.toSet()).size()); // каждый обьект имеет уникальный shortUrl

    }


}
