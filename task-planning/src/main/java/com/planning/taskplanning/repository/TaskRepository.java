package com.planning.taskplanning.repository;

import com.planning.taskplanning.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
  void deleteAllByStory_storyNumber(String jiraKey);
  List<Task> findAllByStory_storyNumber(String jiraKey);
}
