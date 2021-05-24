package com.planning.taskplanning.repository;

import com.planning.taskplanning.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Story - repository class
 * @Author Guilherme Maciel Petena
 */
public interface StoryRepository extends JpaRepository<Story, Long> {
}
