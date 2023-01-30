package com.shortener.URL.shortener.controller;

import com.shortener.URL.shortener.models.URL;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
// @RequestMapping(value = "/start") страничка выдает ошибку но без start работает
public class UrlController {

    @GetMapping("/guest")
    public String getUrlShortnerPage(Model model){
        model.addAttribute("url", new URL());

        return "guest";
    }
/*
    @PostMapping
    public String makeShortUrl(@PathVariable(name = "url") String url,
        //  @Valid URL url, нам нужен только Coffee соответсвующий проверкам
       // BindingResult result, // содержит результаты проверки
        // должен быть после @Valid
        Model model){

//<span th:if="${#fields.hasErrors('name')}" th:errors="*{name}"></span>     <br/>
//    <input type="text" th:field="*{url}" id="url" placeholder="url">
//
//    <br/>
//    <input type="submit" value="long URL">

        return "redirect:/guest";
    }
*/
    @PostMapping("/shorter")
    public String shortUrl(@ModelAttribute("url") @Valid URL url, BindingResult bindingResult, Model model) {
        //model.addAttribute("message", url);

        URL u = (URL) model.getAttribute("url");

        if (u != null) {
            u.setLongUrl("long12345");
        }

        System.out.println("ok");

        return "shorter";

    }





}
