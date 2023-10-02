package com.javaCapstone.mentalHealthApp.controllers;

import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import com.javaCapstone.mentalHealthApp.entities.entries;
import com.javaCapstone.mentalHealthApp.repositories.EntryRepository;
import com.javaCapstone.mentalHealthApp.services.EmotionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/emotions")
public class EmotionsController {

    private final EntryRepository entryRepository;

    @Autowired
    private EmotionsServiceImpl emotionsService;

    public EmotionsController(EntryRepository entryRepository, EmotionsServiceImpl emotionsService) {
        this.entryRepository = entryRepository;
        this.emotionsService = emotionsService;
    }

    @PostMapping("/create")
    public void createEmotion(@RequestBody emotionsDto emotionsDto) {
        emotionsService.createEmotion(emotionsDto);
    }

    @PutMapping("/{emotionId}")
    public void updateEmotion(
            @PathVariable Long emotionId,
            @RequestBody emotionsDto emotionsDto) {
        emotionsService.updateEmotion(emotionId, emotionsDto);
    }


    @PostMapping("/entries/{entryId}/emotions/add")
    public void addEmotionsToEntry(@PathVariable Long entryId, @RequestBody emotionsDto emotionsDto) {
        emotionsService.addEmotionsToEntry(entryId, emotionsDto);

    }

    @GetMapping("/emotions")
    public List<emotionsDto> getEmotions(){
        return emotionsService.getEmotions();
    }
}
