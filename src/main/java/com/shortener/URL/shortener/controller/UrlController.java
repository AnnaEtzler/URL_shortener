package com.shortener.URL.shortener.controller;

import com.shortener.URL.shortener.models.URL;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping(value = "")
public class UrlController {

    @GetMapping("/guest")
    public String getUrlShortnerPage(Model model){
        model.addAttribute("url", new URL());

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
            s = u.getLongUrl() + "now_short";
            u.setShortUrl(s);
        }

        return "shorter";

    }





}
