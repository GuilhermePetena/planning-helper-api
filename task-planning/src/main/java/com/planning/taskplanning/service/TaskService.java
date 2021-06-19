package com.planning.taskplanning.service;

import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.model.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskService {

    Task save(Task task);

    List<Task> findAll();

    List<Task> findAllByStoryNumber(String storyNumber);

    Optional<Task> findOne(String id);

    void delete(String id);

    void deleteAllByStoryNumber(String storyNumber);

    List<Task> findAllByUserId(String id);

    TaskDTO converteToDTO(Task task);

    List<TaskDTO> converteToDTOList(List<Task> tasks);

}
