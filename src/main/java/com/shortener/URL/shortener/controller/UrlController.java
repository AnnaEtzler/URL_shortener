package com.shortener.URL.shortener.controller;

import com.shortener.URL.shortener.models.URL;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping(value = "/start") страничка выдает ошибку но без start работает
public class UrlController {

    @GetMapping("/guest")
    public String getUrlShortnerPage(){
        return "guest";
    }

    @PostMapping("/shortner/{url}")
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





}
