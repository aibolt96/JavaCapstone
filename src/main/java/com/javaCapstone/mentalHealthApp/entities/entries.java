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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class entries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entryId;

    @Column(columnDefinition = "text")
    private String journalEntry;

    @Column(columnDefinition = "integer")
    private Integer dayRating;

    @Column(columnDefinition = "date")
    private Date date;

    public Long getEntryId() {
        return entryId;
    }

    // Getter for journalEntry
    public String getJournalEntry() {
        return journalEntry;
    }

    // Getter for dayRating
    public Integer getDayRating() {
        return dayRating;
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
