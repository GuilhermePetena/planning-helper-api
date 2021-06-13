package com.planning.taskplanning.service.impl;

import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.repository.TaskRepository;
import com.planning.taskplanning.service.TaskService;
import com.planning.taskplanning.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Task}.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;

    @Autowired
    private StoryServiceImpl storyService;

    /**
     * Constructor
     * @param taskRepository
     */
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private static String teamName;
    public static File file;

    /**
     * Add team name
     * @param team
     */
    @Override
    public void addTeamName(String team) { teamName = team; }

    /**
     * Save the task
     * @param task the entity to save.
     * @return
     */
    @Override
    public Task save(Task task) {
        log.debug("Request to save Task : {}", task);
        Story story = storyService.findOne(task.getStory().getId()).get();
        task.setStory(story);
        return taskRepository.save(task);
    }

    /**
     * Find all tasks
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        log.debug("Request to get all Tasks");
        return taskRepository.findAll();
    }

    /**
     * Find All stories by JiraNumber
     * @param storyNumber the jira number information.
     * @return
     */
    @Override
    public List<Task> findAllByStoryNumber(String storyNumber) {
        log.debug("Request to get all Tasks of a story");
        return taskRepository.findAllByStory_storyNumber(storyNumber);
    }

    /**
     * Find one task
     * @param id the id of the entity.
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Task> findOne(String id) {
        log.debug("Request to get Task : {}", id);
        return taskRepository.findById(id);
    }

    /**
     * delete a task
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Task : {}", id);
        taskRepository.deleteById(id);
    }

    /**
     * delete All stories by jira number
     * @param storyNumber the id of the entity.
     */
    @Override
    public void deleteAllByStoryNumber(String storyNumber) {
        log.debug("Request to delete Task : {}", storyNumber);
        taskRepository.deleteAllByStory_storyNumber(storyNumber);
    }

    /**
     * return jiraImporter file
     * @return
     * @throws IOException
     */
    @Override
    public InputStreamResource returnJiraImporterTxt() throws IOException {
        return downloadTextFile();
    }

    @Override
    public InputStreamResource returnParametrizationFile() throws IOException {
        file = new FileUtils().returnParametrizationFile();
        return downloadTextFile();
    }

    /**
     * return planningPoker file
     * @return
     * @throws IOException
     */
    @Override
    public InputStreamResource returnPlanningPokerTxt() throws IOException {
        return downloadTextFile();
    }

    /**
     * Create jiraImporter file
     * @return
     */
    public File createJiraImporter() {
        int contador = 1;
        List<Task> list = taskRepository.findAll();
        for (Task task : list) {
            task.setTeam(teamName);
            task.setIssueId(contador++);
            task.setOriginalEstimate(task.getHours() * 3600);
        }
        return new FileUtils().writeJiraImporter(list);
    }

    /**
     * Create planninPoker file
     * @return
     */
    public File createPlanningPoker() {
        return new FileUtils().writePlanningPokerTxt(findAll());
    }

    /**
     * method to call the create methods file
     * @param jira flag to know if is jira file..
     * @return
     */
    @Override
    public String createFile(boolean jira) {
        String fileName;
        if (jira) {
            file = createJiraImporter();
            fileName = "jiraImporter.txt";
        } else {
            file = createPlanningPoker();
            fileName = "planningPoker.txt";
        }
        return fileName;
    }

    /**
     * method to do the download of the text file
     * @return
     * @throws IOException
     */
    @Override
    public InputStreamResource downloadTextFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);

        return inputStreamResource;
    }
}
