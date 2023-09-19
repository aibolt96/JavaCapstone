package com.javaCapstone.mentalHealthApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javaCapstone.mentalHealthApp.entities.emotions;
@Repository
public interface EmotionsRepository extends JpaRepository<emotions, Long> {
}
