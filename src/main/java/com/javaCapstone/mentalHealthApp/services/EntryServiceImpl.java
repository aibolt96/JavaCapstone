package com.javaCapstone.mentalHealthApp.services;

import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import com.javaCapstone.mentalHealthApp.dto.entriesDto;
import com.javaCapstone.mentalHealthApp.entities.entries;
import com.javaCapstone.mentalHealthApp.entities.user;
import com.javaCapstone.mentalHealthApp.repositories.EntryRepository;
import com.javaCapstone.mentalHealthApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EntryServiceImpl implements EntryService {
    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addEntry(entriesDto entryDto, Long userId){
        Optional<user> userOptional = userRepository.findById(userId);
        Entry entry = new Entry(entryDto);
        userOptional.ifPresent(entry::setUser);

        Set<Emotion> emotions = emotionsDtoSet.stream()
                .map(Emotion::new)
                .collect(Collectors.toSet());

        entry.setEmotionsSet(emotions);
        entryRepository.saveAndFlush(entry);
    }

    @Override
    @Transactional
    public void deleteEntryById(Long entryId){
        Optional<entries> entryOptional = entryRepository.findById(entriesDto.getId());
        entryOptional.ifPresent(entry -> {
            entry.setBody(entriesDto.getBody());

            entry.getEmotionsSet().clear();
            entryRepository.delete(entry);
        });
    }

    @Override
    @Transactional
    public void updateEntryDayRating(Long entryId, Integer newDayRating, Set<emotionsDto> emotionsDtoSet) {
        Optional<entries> entryOptional = entryRepository.findById(entryId);
        entryOptional.ifPresent(entry -> {
            entry.setDayRating(newDayRating);

            Set<Emotion> emotions = emotionsDtoSet.stream()
                    .map(Emotion::new)
                    .collect(Collectors.toSet());

            entry.setEmotionsSet(emotions);
            entryRepository.saveAndFlush(entry);
        });

        if (!entryOptional.isPresent()) {
            Entry newEntry = new Entry();
            newEntry.setEntryId(entryId); // Set the ID if needed
            newEntry.setDayRating(newDayRating);
            entryRepository.saveAndFlush(newEntry);
        }
    }

    @Override
    @Transactional
    public List<entries> findEntriesByDayRating(Integer dayRating) {
        return entryRepository.findByDayRating(dayRating);
    }
}