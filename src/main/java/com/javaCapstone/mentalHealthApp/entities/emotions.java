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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class emotions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emotionsId;

    @Column(columnDefinition = "varchar")
    private String mood;

    @Column(columnDefinition = "text")
    private String moodReason;

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
