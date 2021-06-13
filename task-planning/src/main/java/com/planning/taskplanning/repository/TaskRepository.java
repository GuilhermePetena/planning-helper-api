package com.planning.taskplanning.repository;

import com.planning.taskplanning.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

  void deleteAllByStory_storyNumber(String jiraKey);

  List<Task> findAllByStory_storyNumber(String jiraKey);
}
