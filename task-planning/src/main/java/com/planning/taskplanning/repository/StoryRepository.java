package com.planning.taskplanning.repository;

import com.planning.taskplanning.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoryRepository extends JpaRepository<Story, UUID> {
}
