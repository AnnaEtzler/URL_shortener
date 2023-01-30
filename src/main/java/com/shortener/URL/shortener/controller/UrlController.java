package com.shortener.URL.shortener.controller;

import com.shortener.URL.shortener.models.URL;
import com.shortener.URL.shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UrlController {

    @GetMapping("/start")
    public String getHelloPage(){
        return "hello";
    }

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity shortenUrl(@PathVariable String url) {
        URL shortUrlEntry = urlService.generateShortUrl(url);

        return ResponseEntity.ok(shortUrlEntry);

    }
}
