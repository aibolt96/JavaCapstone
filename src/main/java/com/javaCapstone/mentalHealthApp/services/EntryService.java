package com.javaCapstone.mentalHealthApp.services;

import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import com.javaCapstone.mentalHealthApp.dto.entriesDto;
import com.javaCapstone.mentalHealthApp.entities.entries;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;

public interface EntryService {
    @Transactional
    entries addEntry(entriesDto entryDto, Long userId);


    @Transactional
    void updateEntryDayRating(Long entryId, Integer newDayRating, Set<emotionsDto> emotionsDtoSet);

    @Transactional
    List<entries> findEntriesByDayRating(Integer dayRating);

    @Transactional
    List<entries> getAllEntries();

    void deleteEntryById(Long entryId);
}
