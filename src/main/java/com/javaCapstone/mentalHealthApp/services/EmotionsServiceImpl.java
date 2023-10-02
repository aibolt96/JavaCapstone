package com.javaCapstone.mentalHealthApp.services;

import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import com.javaCapstone.mentalHealthApp.entities.emotions;
import com.javaCapstone.mentalHealthApp.entities.entries;
import com.javaCapstone.mentalHealthApp.repositories.EmotionsRepository;
import com.javaCapstone.mentalHealthApp.repositories.EntryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addEmotionsToEntry(Long entryId, Set<emotionsDto> emotionsDtoSet) {
        Optional<entries> entryOptional = entryRepository.findById(entryId);
        if (entryOptional.isPresent()) {
            entries entry = entryOptional.get();

            Set<emotions> emotionsSet = emotionsDtoSet.stream()
                    .map(emotionsDto -> {
                        emotions emotion = new emotions();
                        emotion.setMood(emotionsDto.getMood());
                        emotion.setMoodReason(emotionsDto.getMoodReason());
                        emotion.setEntry(entry); // Associate the emotion with the entry
                        return emotion;
                    })
                    .collect(Collectors.toSet());

            emotionsRepository.saveAll(emotionsSet);

            entry.getEmotionsSet().addAll(emotionsSet);
            entryRepository.saveAndFlush(entry);
        }
    }
}
