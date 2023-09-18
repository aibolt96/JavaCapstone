package com.javaCapstone.mentalHealthApp.dto;


import com.javaCapstone.mentalHealthApp.entities.entries;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class entriesDto implements Serializable {
    private Long entryId;
    private String journalEntry;
    private Integer dayRating;
    private Set<emotionsDto> emotionsDtoSet = new HashSet<>();

    public entriesDto(entries entries) {
        if (entries.getEntryId() != null){
            this.entryId = entries.getEntryId();
        }
        if (entries.getJournalEntry() != null){
            this.journalEntry = entries.getJournalEntry();
        }
        if (entries.getDayRating() != null){
            this.dayRating = entries.getDayRating();
        }
    }
}
