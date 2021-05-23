package com.planning.taskplanning.service.impl;

import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.repository.StoryRepository;
import com.planning.taskplanning.service.StoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Story}.
 */
@Service
@Transactional
public class StoryServiceImpl implements StoryService {

    private final Logger log = LoggerFactory.getLogger(StoryServiceImpl.class);

    private final StoryRepository storyRepository;

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    public StoryServiceImpl(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public Story save(Story story) {
        log.debug("Request to save Story : {}", story);
        return storyRepository.save(story);
    }

    @Override
    public Optional<Story> partialUpdate(Story story) {
        log.debug("Request to partially update Story : {}", story);

        return storyRepository
                .findById(story.getId())
                .map(
                        existingStory -> {
                            if (story.getTitle() != null) {
                                existingStory.setTitle(story.getTitle());
                            }
                            if (story.getStoryNumber() != null) {
                                existingStory.setStoryNumber(story.getStoryNumber());
                            }

                            return existingStory;
                        }
                )
                .map(storyRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Story> findAll(Pageable pageable) {
        log.debug("Request to get all Stories");
        return storyRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Story> findOne(Long id) {
        log.debug("Request to get Story : {}", id);
        return storyRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Story : {}", id);
        taskServiceImpl.deleteAllByStoryNumber(findOne(id).get().getStoryNumber());
        storyRepository.deleteById(id);
    }
}
