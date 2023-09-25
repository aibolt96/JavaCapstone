package com.javaCapstone.mentalHealthApp.dto;

import com.javaCapstone.mentalHealthApp.entities.emotions;

import java.io.Serializable;

public class emotionsDto implements Serializable {
    private Long emotionsId;
    private String mood;
    private String moodReason;

    public Long getEmotionsId() {
        return emotionsId;
    }

    public void setEmotionsId(Long emotionsId) {
        this.emotionsId = emotionsId;
    }

    public emotionsDto() {
    }

    public emotionsDto(Long emotionsId, String mood, String moodReason) {
        this.emotionsId = emotionsId;
        this.mood = mood;
        this.moodReason = moodReason;
    }

    public String getMood() {
        return mood;
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

    public emotionsDto (emotions emotions){
        if (emotions.getEmotionsId() != null){
            this.emotionsId = emotions.getEmotionsId();
        }
        if (emotions.getMood() != null){
            this.mood = emotions.getMood();
        }
        if (emotions.getMoodReason() != null){
            this.moodReason = emotions.getMoodReason();
        }
    }
}
