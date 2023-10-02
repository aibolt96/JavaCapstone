package com.javaCapstone.mentalHealthApp.controllers;

import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import com.javaCapstone.mentalHealthApp.services.EmotionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/emotions")
public class EmotionsController {

    @Autowired
    private EmotionsServiceImpl emotionsService;

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
    public void addEmotionsToEntry(@PathVariable Long entryId, @RequestBody Set<emotionsDto> emotionsDtoSet) {
        // Implement logic to add emotions to the specified entry
        emotionsService.addEmotionsToEntry(entryId, emotionsDtoSet);
    }
}
