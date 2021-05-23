package com.planning.taskplanning.service;

import com.planning.taskplanning.model.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


/**
 * Service Interface for managing {@link Story}.
 */
public interface StoryService {
    /**
     * Save a story.
     *
     * @param story the entity to save.
     * @return the persisted entity.
     */
    Story save(Story story);

    /**
     * Partially updates a story.
     *
     * @param story the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Story> partialUpdate(Story story);

    /**
     * Get all the stories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Story> findAll(Pageable pageable);

    /**
     * Get the "id" story.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Story> findOne(Long id);

    /**
     * Delete the "id" story.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

