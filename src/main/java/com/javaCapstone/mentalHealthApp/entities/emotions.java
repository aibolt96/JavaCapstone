package com.javaCapstone.mentalHealthApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.javaCapstone.mentalHealthApp.dto.emotionsDto;
import com.javaCapstone.mentalHealthApp.dto.entriesDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Emotion")

public class emotions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emotionsId;

    @Column(columnDefinition = "varchar")
    private String mood;

    @Column(columnDefinition = "text")
    private String moodReason;

    public Long getEmotionsId() {
        return emotionsId;
    }

    public void setEmotionsId(Long emotionsId) {
        this.emotionsId = emotionsId;
    }

    public String getMood() {
        return mood;
    }

    public emotions() {
    }

    public emotions(Long emotionsId, String mood, String moodReason, com.javaCapstone.mentalHealthApp.entities.entries entries) {
        this.emotionsId = emotionsId;
        this.mood = mood;
        this.moodReason = moodReason;
        this.entries = entries;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getMoodReason() {
        return moodReason;
    }

    public void setMoodReason(String moodReason) {
        this.moodReason = moodReason;
    }

    public com.javaCapstone.mentalHealthApp.entities.entries getEntries() {
        return entries;
    }

    public void setEntries(com.javaCapstone.mentalHealthApp.entities.entries entries) {
        this.entries = entries;
    }

    @ManyToOne
    @JsonBackReference
    private entries entries;

    public emotions(emotionsDto emotionsDto) {
        if (emotionsDto.getMood() != null){
            this.mood = emotionsDto.getMood();
        }
        if (emotionsDto.getMoodReason() != null){
            this.moodReason = emotionsDto.getMoodReason();
        }
    }
}
