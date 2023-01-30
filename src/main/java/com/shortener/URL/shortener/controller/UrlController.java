package com.shortener.URL.shortener.controller;

import com.shortener.URL.shortener.models.URL;
import com.shortener.URL.shortener.repository.URLRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping(value = "")
public class UrlController {

    @Autowired
    private URLRepository urlRepository ;

    @GetMapping("/guest")
    public String getUrlShortnerPage(Model model){
        model.addAttribute("url", new URL());
        Iterable<URL> all = urlRepository.findAll();
        model.addAttribute("allUrls", all);

        return "guest";
    }

    @PostMapping("/shorter")
    public String shortUrl( @ModelAttribute("url") @Valid URL url, BindingResult bindingResult, Model model) {
        URL u = (URL) model.getAttribute("url");
        if (bindingResult.hasErrors()) {
            return "redirect:/guest" ;
        }
        String s = null;
        if (u != null) {
            //s = u.getLongUrl() + "now_short";
            u.setShortUrl(u.generateShortUrl(url.getLongUrl()));
            urlRepository.save(u);
        }
        return "redirect:/guest";
    }





}
