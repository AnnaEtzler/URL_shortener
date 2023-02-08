package com.shortener.URL.shortener;

import com.shortener.URL.shortener.models.URL;
import com.shortener.URL.shortener.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SсhedulerMillis {
    @Autowired
    URLRepository urlRepository;


    @Scheduled(cron="@monthly") // каждый месяц
    public void wakeUp() {
        long minMillis = System.currentTimeMillis() - 5;  // 30*24*3600
        List<URL> oldUrlsToDelete = urlRepository.findByMillisLessThan(minMillis);
        urlRepository.deleteAll(oldUrlsToDelete);
    }
}
