package com.javaCapstone.mentalHealthApp.services;

import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import com.javaCapstone.mentalHealthApp.dto.entriesDto;
import com.javaCapstone.mentalHealthApp.entities.entries;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;

public interface EntryService {

    @Transactional
    void addEntry(entriesDto entryDto, Set<emotionsDto> emotionsDtoSet, Long userId);

    @Transactional
    void deleteEntryById(Long entryId);

    @Transactional
    void updateEntryDayRating(Long entryId, Integer newDayRating, Set<emotionsDto> emotionsDtoSet);

    @Transactional
    List<entries> findEntriesByDayRating(Integer dayRating);
}
