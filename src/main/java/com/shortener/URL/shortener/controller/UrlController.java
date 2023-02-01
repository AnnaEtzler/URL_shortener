package com.shortener.URL.shortener.controller;

import com.shortener.URL.shortener.models.URL;
import com.shortener.URL.shortener.repository.URLRepository;
<<<<<<< HEAD
import jakarta.validation.Valid;
=======
import com.shortener.URL.shortener.service.UrlService;
>>>>>>> 666b0e7c44f01bebc289a5be6754b5ab9ce3cefe
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
>>>>>>> 666b0e7c44f01bebc289a5be6754b5ab9ce3cefe

@Controller
//@RequestMapping(value = "")
public class UrlController {

    @Autowired
    private URLRepository urlRepository ;

<<<<<<< HEAD
    @GetMapping("/guest")
    public String getUrlShortnerPage(Model model){
        model.addAttribute("url", new URL());
        Iterable<URL> all = urlRepository.findAll();
        model.addAttribute("allUrls", all);

        return "guest";
    }

    @PostMapping("/shorter") // спросить про модель атрибут ????
    public String shortUrl( @ModelAttribute("url") @Valid URL url, BindingResult bindingResult, Model model) {
        URL u = (URL) model.getAttribute("url");
        if (bindingResult.hasErrors() || u == null) {
            return "redirect:/guest" ;
        }
        if(u.getShortUrl().equals("")){  // норм так делать?
            u.setShortUrl(u.generateShortUrl(url.getLongUrl()));
        }
        urlRepository.save(u);
        return "redirect:/guest";
    }

    @GetMapping("/guest/{link}")
    public String getPageWithShortURL(@PathVariable(name = "link") String link){
        URL url = urlRepository.findByShortUrl(link);
        return "redirect:" + url.getLongUrl();
    }





=======
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

>>>>>>> 666b0e7c44f01bebc289a5be6754b5ab9ce3cefe
}
