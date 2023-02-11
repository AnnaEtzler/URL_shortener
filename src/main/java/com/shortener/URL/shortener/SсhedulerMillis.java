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


    @Scheduled(cron="@daily") // каждый день
    public void wakeUp() {
        long l = 2_592_000_000l;
        long minMillis = System.currentTimeMillis() - l;  // 1 мин -> 60 000 ms, 60 000 * 60 * 24 (сутки) * 30 месяц
        List<URL> oldUrlsToDelete = urlRepository.findByMillisLessThan(minMillis);
        urlRepository.deleteAll(oldUrlsToDelete);
    }
}
