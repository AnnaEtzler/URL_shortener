package com.shortener.URL.shortener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UrlController {

    @GetMapping("/start")
    public String getHelloPage(){
        return "hello";
    }

}
