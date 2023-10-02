package com.javaCapstone.mentalHealthApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.javaCapstone.mentalHealthApp.dto.entriesDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Entries")
public class entries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;

    @Column(name = "journal_entry", columnDefinition = "text")
    private String journalEntry;

    @Column(name = "day_rating", columnDefinition = "integer")
    private Integer dayRating;

//    @Column(columnDefinition = "date")
//    private Date date;

    public entries() {
    }

    public entries(Long entryId, String journalEntry, Integer dayRating, com.javaCapstone.mentalHealthApp.entities.user user, Set<emotions> emotionsSet) {
        this.entryId = entryId;
        this.journalEntry = journalEntry;
        this.dayRating = dayRating;
//        this.date = date;
        this.user = user;
        this.emotionsSet = emotionsSet;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public String getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(String journalEntry) {
        this.journalEntry = journalEntry;
    }

    public Integer getDayRating() {
        return dayRating;
    }

    public void setDayRating(Integer dayRating) {
        this.dayRating = dayRating;
    }

//    public Date getDate() {
//        return date;
//    }

//    public void setDate(Date date) {
//        this.date = date;
//    }

    public com.javaCapstone.mentalHealthApp.entities.user getUser() {
        return user;
    }

    public void setUser(com.javaCapstone.mentalHealthApp.entities.user user) {
        this.user = user;
    }

    public Set<emotions> getEmotionsSet() {
        return emotionsSet;
    }

    public void setEmotionsSet(Set<emotions> emotionsSet) {
        this.emotionsSet = emotionsSet;
    }

    @ManyToOne
    @JsonBackReference
    private user user;

    @OneToMany(mappedBy = "entries", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<emotions> emotionsSet = new HashSet<>();

    public entries(entriesDto entriesDto) {
        if (entriesDto.getJournalEntry() != null){
            this.journalEntry = entriesDto.getJournalEntry();
        }
        if (entriesDto.getDayRating() != null){
            this.dayRating = entriesDto.getDayRating();
        }
    }
}
