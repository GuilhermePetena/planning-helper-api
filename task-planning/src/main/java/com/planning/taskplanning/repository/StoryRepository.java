package com.planning.taskplanning.repository;

import com.planning.taskplanning.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StoryRepository extends JpaRepository<Story, Long> {
}
