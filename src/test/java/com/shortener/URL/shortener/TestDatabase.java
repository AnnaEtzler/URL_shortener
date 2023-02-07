package com.shortener.URL.shortener;

import com.shortener.URL.shortener.controller.UrlController;
import com.shortener.URL.shortener.models.URL;
import com.shortener.URL.shortener.repository.URLRepository;
import com.shortener.URL.shortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
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
    public void urlInDatabase() throws Exception {
        URL url = new URL();
        url.setLongUrl("https://github.com/AnnaEtzler/URL_shortener");
        url.setShortUrl( urlService.generateShortUrl("https://github.com/AnnaEtzler/URL_shortener"));
        urlRepository.save(url);
        assertEquals(url, urlRepository.findByLongUrl("https://github.com/AnnaEtzler/URL_shortener"));
    }




}
