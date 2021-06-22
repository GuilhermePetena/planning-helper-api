package com.planning.taskplanning.service.impl;

import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.model.User;
import com.planning.taskplanning.model.dto.StoryDTO;
import com.planning.taskplanning.repository.StoryRepository;
import com.planning.taskplanning.service.StoryService;
import com.planning.taskplanning.service.TaskService;
import com.planning.taskplanning.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class StoryServiceImpl implements StoryService {

    private final Logger log = LoggerFactory.getLogger(StoryServiceImpl.class);

    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;


    @Override
    public Story save(Story story) {
        log.debug("Request to save Story : {}", story);
        User user = userService.findOne(story.getUser().getId()).get();
        story.setUser(user);
        return storyRepository.save(story);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Story> findAll() {
        log.debug("Request to get all Stories");
        return storyRepository.findAll();
    }

    @Override
    public List<Story> findAllByUserId(String id) {
        log.debug("Request to get all Stories by id");
        return storyRepository.findAllByUser_Id(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Story> findOne(String id) {
        log.debug("Request to get Story : {}", id);
        return storyRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Story : {}", id);
        taskService.deleteAllByStoryNumber(findOne(id).get().getStoryNumber());
        storyRepository.deleteById(id);
    }

    @Override
    public StoryDTO converteToDTO(Story story) {
        log.debug("Convert object to DTO");
        StoryDTO storyDTO = new StoryDTO();
        storyDTO.setId(story.getId());
        storyDTO.setStoryNumber(story.getStoryNumber());
        storyDTO.setUser(userService.converteToDTO(story.getUser()));
        storyDTO.setTitle(story.getTitle());
        storyDTO.setTask(taskService.converteToDTOList(story.getTasks()));
        return storyDTO;
    }

    @Override
    public List<StoryDTO> converteToDTOList(List<Story> stories) {
        log.debug("Convert object to DTO");
        List<StoryDTO> dtoList = new ArrayList<>();
        for (Story story : stories) {
            dtoList.add(converteToDTO(story));
        }
        return dtoList;
    }
}
