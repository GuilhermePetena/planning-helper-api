package com.planning.taskplanning.service.impl;

import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.model.User;
import com.planning.taskplanning.repository.StoryRepository;
import com.planning.taskplanning.service.StoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Story}.
 * @Author Guilherme Maciel Petena
 */
@Service
@Transactional
public class StoryServiceImpl implements StoryService {

    private final Logger log = LoggerFactory.getLogger(StoryServiceImpl.class);

    private final StoryRepository storyRepository;

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @Autowired
    private UserServiceImpl userService;

    /**
     * Constructor
     * @param storyRepository
     */
    public StoryServiceImpl(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    /**
     * save the story
     * @param story the entity to save.
     * @return
     */
    @Override
    public Story save(Story story) {
        log.debug("Request to save Story : {}", story);
        User user = userService.findOne(story.getUser().getId()).get();
        story.setUser(user);
        return storyRepository.save(story);
    }

    /**
     * find All stories
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Story> findAll() {
        log.debug("Request to get all Stories");
        return storyRepository.findAll();
    }

    /**
     * find one story
     * @param id the id of the entity.
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Story> findOne(String id) {
        log.debug("Request to get Story : {}", id);
        return storyRepository.findById(id);
    }

    /**
     * delete story
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Story : {}", id);
        taskServiceImpl.deleteAllByStoryNumber(findOne(id).get().getStoryNumber());
        storyRepository.deleteById(id);
    }
}
