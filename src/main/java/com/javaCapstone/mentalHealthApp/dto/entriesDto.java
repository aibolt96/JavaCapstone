package com.javaCapstone.mentalHealthApp.dto;


import com.javaCapstone.mentalHealthApp.entities.entries;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class entriesDto implements Serializable {
    private static Long entryId;
    private static String journalEntry;
    private Integer dayRating;
    private Set<emotionsDto> emotionsDtoSet = new HashSet<>();

    public static Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public static String getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(String journalEntry) {
        entriesDto.journalEntry = journalEntry;
    }

    public entriesDto() {
    }

    public entriesDto(Long entryId, String journalEntry, Integer dayRating, Set<emotionsDto> emotionsDtoSet) {
        this.entryId = entryId;
        entriesDto.journalEntry = journalEntry;
        this.dayRating = dayRating;
        this.emotionsDtoSet = emotionsDtoSet;
    }

    public Integer getDayRating() {
        return dayRating;
    }

    public void setDayRating(Integer dayRating) {
        this.dayRating = dayRating;
    }

    public Set<emotionsDto> getEmotionsDtoSet() {
        return emotionsDtoSet;
    }

    public void setEmotionsDtoSet(Set<emotionsDto> emotionsDtoSet) {
        this.emotionsDtoSet = emotionsDtoSet;
    }

    public entriesDto(entries entries) {
        if (entries.getEntryId() != null){
            this.entryId = entries.getEntryId();
        }
        if (entries.getJournalEntry() != null){
            journalEntry = entries.getJournalEntry();
        }
        if (entries.getDayRating() != null){
            this.dayRating = entries.getDayRating();
        }
    }
}
