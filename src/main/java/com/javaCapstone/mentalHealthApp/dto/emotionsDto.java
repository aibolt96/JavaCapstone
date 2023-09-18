package com.javaCapstone.mentalHealthApp.dto;

import com.javaCapstone.mentalHealthApp.entities.emotions;

import java.io.Serializable;

public class emotionsDto implements Serializable {
    private Long emotionsId;
    private String mood;
    private String moodReason;

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
