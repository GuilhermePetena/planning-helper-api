package com.planning.taskplanning.repository;

import com.planning.taskplanning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByQuestionAndAnswer(String question, String answer);
}
