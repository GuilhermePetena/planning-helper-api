package com.planning.taskplanning.service;

import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.model.dto.StoryDTO;
import com.planning.taskplanning.model.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StoryService {

    Story save(Story story);

    List<Story> findAll();

    List<Story> findAllByUserId(String id);

    Optional<Story> findOne(String id);

    void delete(String id);

    StoryDTO converteToDTO(Story story);

    List<StoryDTO> converteToDTOList(List<Story> stories);
}

