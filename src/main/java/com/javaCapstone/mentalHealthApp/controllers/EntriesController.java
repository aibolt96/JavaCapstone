package com.javaCapstone.mentalHealthApp.controllers;

import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import com.javaCapstone.mentalHealthApp.dto.entriesDto;
import com.javaCapstone.mentalHealthApp.entities.entries;
import com.javaCapstone.mentalHealthApp.services.EntryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/entries")
public class EntriesController {
    @Autowired
    private EntryServiceImpl entryService;

    @PostMapping("/add/{userId}")
    public void addEntry(@RequestBody entriesDto entryDto, @PathVariable Long userId) {
        System.out.println(entryDto);
        entryService.addEntry(entryDto, userId);
    }

    @DeleteMapping("/{entryId}")
    public void deleteEntryById(@PathVariable Long entryId) {
        entryService.deleteEntryById(entryId);
    }

    @PutMapping("/{entryId}")
    public void updateEntryDayRating(
            @PathVariable Long entryId,
            @RequestParam Integer newDayRating,
            @RequestBody Set<emotionsDto> emotionsDtoSet) {
        entryService.updateEntryDayRating(entryId, newDayRating, emotionsDtoSet);
    }

    @GetMapping("/find")
    public List<entries> findEntriesByDayRating(@RequestParam Integer dayRating) {
        return entryService.findEntriesByDayRating(dayRating);
    }

    @PostMapping("/all")
    public List<entries> getAllEntries() {
        return entryService.getAllEntries();
    }

}
