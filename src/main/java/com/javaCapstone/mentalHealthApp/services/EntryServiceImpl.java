package com.javaCapstone.mentalHealthApp.services;


import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import com.javaCapstone.mentalHealthApp.dto.entriesDto;
import com.javaCapstone.mentalHealthApp.entities.emotions;
import com.javaCapstone.mentalHealthApp.entities.entries;
import com.javaCapstone.mentalHealthApp.entities.user;
import com.javaCapstone.mentalHealthApp.repositories.EmotionsRepository;
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
    @Autowired
    private EmotionsRepository emotionsRepository;


    @Override
    public entries addEntry(entriesDto entryDto, Long userId) {
        System.out.println(entryDto);
        Optional<user> userOptional = userRepository.findById(userId);
        entries entry = new entries(entryDto);
        userOptional.ifPresent(entry::setUser);

        entryRepository.saveAndFlush(entry);
        return entry;
    }
    

    @Override
    @Transactional
    public void updateEntryDayRating(Long entryId, Integer newDayRating, Set<emotionsDto> emotionsDtoSet) {
        Optional<entries> entryOptional = entryRepository.findById(entryId);
        entryOptional.ifPresent(entry -> {
            entry.setDayRating(newDayRating);

            Set<emotions> emotions = emotionsDtoSet.stream()
                    .map(emotions::new)
                    .collect(Collectors.toSet());

            entry.setEmotionsSet(emotions);
            entryRepository.saveAndFlush(entry);
        });

        if (entryOptional.isEmpty()) {
            entries newEntry = new entries();
            newEntry.setEntryId(entryId);
            newEntry.setDayRating(newDayRating);
            entryRepository.saveAndFlush(newEntry);
        }
    }

    @Override
    @Transactional
    public List<entries> findEntriesByDayRating(Integer dayRating) {
        return entryRepository.findByDayRating(dayRating);
    }

    @Override
    @Transactional
    public List<entries> getAllEntries() {
        return entryRepository.findAll();
    }

    @Override
    public void deleteEntryById(Long entryId) {

    }
}
