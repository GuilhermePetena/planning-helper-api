package com.planning.taskplanning.service;

import com.planning.taskplanning.model.Task;
import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Task}.
 */
public interface TaskService {
    /**
     * Save a task.
     *
     * @param task the entity to save.
     * @return the persisted entity.
     */
    Task save(Task task);

    /**
     * Add the team name.
     *
     * @param teamName the entity to save.
     */
    void addTeamName(String teamName);

    /**
     * Get all the tasks.
     *
     * @return the list of entities.
     */
    List<Task> findAll();

    /**
     * Get all the tasks of a story.
     *
     * @param storyNumber the jira number information.
     * @return the list of entities.
     */
    List<Task> findAllByStoryNumber(String storyNumber);

    /**
     * Get the "id" task.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Task> findOne(Long id);

    /**
     * Delete the "id" task.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Delete task using the storyNumberField.
     *
     * @param storyNumber the id of the entity.
     */
    void deleteAllByStoryNumber(String storyNumber);

    /**
     * Download of the text files
     *
     * @return
     */
    InputStreamResource downloadTextFile() throws IOException;

    /**
     * Create the text files
     *
     * @param jira flag to know if is jira file..
     */
    String createFile(boolean jira) throws IOException;

    /**
     * Create the planningPoker text file
     *
     * @return
     */
    InputStreamResource returnPlanningPokerTxt() throws IOException;

    /**
     * Create the jiraImporter text file
     *
     * @return
     */
    InputStreamResource returnJiraImporterTxt() throws IOException;
}
