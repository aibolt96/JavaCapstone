package com.javaCapstone.mentalHealthApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaCapstone.mentalHealthApp.entities.user;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<user, Long> {
    Optional<user> findByUsername(String username);
}
