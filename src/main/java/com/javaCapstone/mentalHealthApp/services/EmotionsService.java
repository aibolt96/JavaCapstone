package com.javaCapstone.mentalHealthApp.services;

import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import jakarta.transaction.Transactional;

import java.util.Set;

public interface EmotionsService {
    @Transactional
    void createEmotion(emotionsDto emotionsDto);

    @Transactional
    void updateEmotion(Long emotionId, emotionsDto emotionsDto);

    @Transactional
    void addEmotionsToEntry(Long entryId, emotionsDto emotionsDto);


}
