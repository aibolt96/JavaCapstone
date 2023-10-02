package com.javaCapstone.mentalHealthApp.services;

import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import com.javaCapstone.mentalHealthApp.entities.emotions;
import com.javaCapstone.mentalHealthApp.entities.entries;
import com.javaCapstone.mentalHealthApp.repositories.EmotionsRepository;
import com.javaCapstone.mentalHealthApp.repositories.EntryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmotionsServiceImpl implements EmotionsService {

    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private EmotionsRepository emotionsRepository;

    @Override
    @Transactional
    public void createEmotion(emotionsDto emotionsDto) {
        emotions emotion = new emotions();
        emotion.setMood(emotionsDto.getMood());
        emotion.setMoodReason(emotionsDto.getMoodReason());
        emotionsRepository.saveAndFlush(emotion);
    }

    @Override
    @Transactional
    public void updateEmotion(Long emotionId, emotionsDto emotionsDto) {
        Optional<emotions> emotionOptional = emotionsRepository.findById(emotionId);
        emotionOptional.ifPresent(emotion -> {
            emotion.setMood(emotionsDto.getMood());
            emotion.setMoodReason(emotionsDto.getMoodReason());
            emotionsRepository.saveAndFlush(emotion);
        });
    }

    @Override
    @Transactional
    public void addEmotionsToEntry(Long entryId,emotionsDto emotionsDto) {
        Optional<entries> entryOptional = entryRepository.findById(entryId);
        emotions emotion = new emotions(emotionsDto);
        entryOptional.ifPresent(emotion::setEntry);
        emotionsRepository.saveAndFlush(emotion);
    }

    public void addEmotionsToEntry(Set<emotionsDto> emotionsDtoSet) {
    }

    public List<emotionsDto> getEmotions() {
        // Retrieve emotions from the database or any other data source
        List<emotions> emotions = emotionsRepository.findAll();

        // Convert the list of Emotion entities to a list of emotionsDto
        List<emotionsDto> emotionDtos = mapEmotionsToDtos(emotions);

        return emotionDtos;
    }

    private List<emotionsDto> mapEmotionsToDtos(List<emotions> emotions) {
        List<emotionsDto> emotionDtos = new ArrayList<>();
        for (emotions emotion : emotions) {
            emotionsDto dto = new emotionsDto();
            dto.setMood(emotion.getMood());
            dto.setMoodReason(emotion.getMoodReason());
            emotionDtos.add(dto);
        }
        return emotionDtos;
    }
}
