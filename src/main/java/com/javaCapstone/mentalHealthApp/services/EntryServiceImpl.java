package com.javaCapstone.mentalHealthApp.services;

import com.javaCapstone.mentalHealthApp.dto.entriesDto;
import com.javaCapstone.mentalHealthApp.entities.user;
import com.javaCapstone.mentalHealthApp.repositories.EntryRepository;
import com.javaCapstone.mentalHealthApp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntryServiceImpl {
    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addEntry(entriesDto entryDto, Long userId){
        Optional<user> userOptional = userRepository.findById(userId);
        Entry entry = new Entry(entryDto);
        userOptional.ifPresent(entry::setUser);
        entryRepository.saveAndFlush(entry);
    }

    @Transactional
    public void deleteEntryById(Long entryId){
        Optional<Entry> entryOptional = entryRepository.findById(entriesDto.getId());
        entryOptional.ifPresent(entry -> {
            entry.setBody(entriesDto.getBody());
            entryRepository.saveAndFlush(entry);
        });
    }

    @Transactional
    public void updateEntryDayRating(Long entryId, Integer newDayRating) {
        Optional<Entry> entryOptional = entryRepository.findById(entryId);
        entryOptional.ifPresent(entry -> {
            entry.setDayRating(newDayRating);
            entryRepository.saveAndFlush(entry);
        });
    }

    @Transactional
    public List<Entry> findEntriesByDayRating(Integer dayRating) {
        return entryRepository.findByDayRating(dayRating);
    }
}
