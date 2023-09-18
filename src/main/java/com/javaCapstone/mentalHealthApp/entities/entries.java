package com.javaCapstone.mentalHealthApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long entries_id;

    @Column(columnDefinition = "text")
    private Long journalEntry;

    @Column(columnDefinition = "integer")
    private Integer dayRating;

    @Column(columnDefinition = "date")
    private Date date;

    @ManyToOne
    @JsonBackReference
    private user user;

    @OneToMany(mappedBy = "entries", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<emotions> emotionsSet = new HashSet<>();
}
