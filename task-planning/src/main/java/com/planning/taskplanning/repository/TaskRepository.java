package com.planning.taskplanning.repository;

import com.planning.taskplanning.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Task - repository class
 * @Author Guilherme Maciel Petena
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

  /**
   * Delete all stories by jiraKey
   * @param jiraKey
   */
  void deleteAllByStory_storyNumber(String jiraKey);

  /**
   * Find all stories by jiraKey
   * @param jiraKey
   */
  List<Task> findAllByStory_storyNumber(String jiraKey);
}
