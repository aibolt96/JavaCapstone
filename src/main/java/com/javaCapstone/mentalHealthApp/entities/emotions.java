package com.javaCapstone.mentalHealthApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long emotions_id;

    @Column(columnDefinition = "varchar")
    private String mood;

    @Column(columnDefinition = "text")
    private Long moodReason;

    @ManyToOne
    @JsonBackReference
    private entries entries;
}
