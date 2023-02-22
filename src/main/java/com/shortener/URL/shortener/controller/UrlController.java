package com.shortener.URL.shortener.controller;

import com.shortener.URL.shortener.models.URL;
import com.shortener.URL.shortener.repository.URLRepository;

import jakarta.validation.Valid;

import com.shortener.URL.shortener.service.UrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.sql.SQLException;


@Controller
//@RequestMapping(value = "")
public class UrlController extends SQLException{

    @Autowired
    private URLRepository urlRepository;
    @Autowired
    private UrlService urlService;

    @GetMapping("/guest")
    public String getUrlShortnerPage(Model model) {
        model.addAttribute("url", new URL());
        return "guest";
    }

    @PostMapping("/shorter")
    public String shortUrl(@ModelAttribute("url") @Valid URL url, BindingResult bindingResult, Model model) {
        URL u = (URL) model.getAttribute("url");

        if (bindingResult.hasErrors() || u == null) {
            return "redirect:/guest";
        }
        if (u.getShortUrl().equals("")) {
            String shortUrl = urlService.generateShortUrl(u.getLongUrl());
            while (urlService.checkShortUrl(shortUrl)) {
                shortUrl = urlService.changeShortUrl(shortUrl);
            }
            u.setShortUrl(shortUrl);
        }
        if (urlRepository.findByShortUrl(u.getShortUrl()) != null){
            model.addAttribute("message", "this name is not available");
            return "/guest";
        }

        model.addAttribute("short", u.getShortUrl());
        urlRepository.save(u);

        return "redirect:/your_urls/"+u.getShortUrl();
    }

    @GetMapping("/guest/{link}")
    public String getPageWithShortURL(@PathVariable(name = "link") String link) {
        URL url = urlRepository.findByShortUrl(link);
        return "redirect:" + url.getLongUrl();
    }


    @GetMapping("/your_urls/{shortUrl}")
    public String getAllUrl(@PathVariable(name = "shortUrl") String shortUrl, Model model) {
        URL url = urlRepository.findByShortUrl(shortUrl);

        if(url != null)
        {
            model.addAttribute("shortUrl", url.getShortUrl());
            model.addAttribute("longUrl", url.getLongUrl());
        }

        return "your_urls";
    }


}
