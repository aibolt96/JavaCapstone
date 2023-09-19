package com.javaCapstone.mentalHealthApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaCapstone.mentalHealthApp.entities.entries;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<entries, Long> {
}
