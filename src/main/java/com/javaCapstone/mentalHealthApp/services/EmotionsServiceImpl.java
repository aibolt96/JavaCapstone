package com.javaCapstone.mentalHealthApp.services;

import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import com.javaCapstone.mentalHealthApp.entities.emotions;
import com.javaCapstone.mentalHealthApp.repositories.EmotionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmotionsServiceImpl implements EmotionsService {
    @Autowired
    private EmotionsRepository emotionsRepository;

    @Override
    @Transactional
    public void createEmotion(emotionsDto emotionsDto) {
        emotions emotion = new Emotion();
        emotion.setMood(emotionsDto.getMood());
        emotion.setReason(emotionsDto.getReason());
        emotionsRepository.saveAndFlush(emotion);
    }

    @Override
    @Transactional
    public void updateEmotion(Long emotionId, emotionsDto emotionsDto) {
        Optional<emotions> emotionOptional = emotionsRepository.findById(emotionId);
        emotionOptional.ifPresent(emotion -> {
            emotion.setMood(emotionsDto.getMood());
            emotion.setReason(emotionsDto.getReason());
            emotionsRepository.saveAndFlush(emotion);
        });
    }
}
