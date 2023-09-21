package com.javaCapstone.mentalHealthApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaCapstone.mentalHealthApp.entities.entries;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<entries, Long> {
    List<entries> findByDayRating(Integer dayRating);
}
