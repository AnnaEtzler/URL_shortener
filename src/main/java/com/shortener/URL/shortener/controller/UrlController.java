package com.shortener.URL.shortener.controller;

import com.shortener.URL.shortener.models.URL;
import com.shortener.URL.shortener.repository.URLRepository;
import com.shortener.URL.shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorter")
    public String shortenUrl(String url, Model model) {
        URL shortUrlEntry = urlService.generateShortUrl(url);
        model.addAttribute("short", shortUrlEntry);
        return "short-created";
    }

    @GetMapping("/{shortUrl}")
    public String redirect(@PathVariable String shortUrl)
    {
        URL longUrl = urlService.getEncodedUrl(shortUrl);
        return "redirect:" + longUrl;
    }

}
