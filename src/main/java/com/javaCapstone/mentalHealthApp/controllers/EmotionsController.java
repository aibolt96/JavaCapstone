package com.javaCapstone.mentalHealthApp.controllers;

import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import com.javaCapstone.mentalHealthApp.services.EmotionsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
