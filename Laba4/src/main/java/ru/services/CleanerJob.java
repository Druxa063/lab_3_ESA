package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableScheduling
public class CleanerJob {
    @Autowired
    private LessonService service;

    @Scheduled(fixedRate = 5000)
    public void clean(){
        service.findAll().stream().filter(lesson -> {
            return lesson.getStartTime().isBefore(LocalDateTime.now());
        }).forEach(lesson -> {
            service.delete(lesson.getId());
        });
    }
}
