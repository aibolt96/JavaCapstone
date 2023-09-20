package com.javaCapstone.mentalHealthApp.services;

import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import jakarta.transaction.Transactional;

public interface EmotionsService {
    @Transactional
    void createEmotion(emotionsDto emotionsDto);

    @Transactional
    void updateEmotion(Long emotionId, emotionsDto emotionsDto);
}
