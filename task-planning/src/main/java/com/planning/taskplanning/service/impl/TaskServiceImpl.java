package com.planning.taskplanning.service.impl;

import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.repository.TaskRepository;
import com.planning.taskplanning.service.TaskService;
import com.planning.taskplanning.utils.CsvUtils;
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

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private static String teamName;
    private static File file;

    @Override
    public void addTeamName(String team) {
        teamName = team;
    }

    @Override
    public Task save(Task task) {
        log.debug("Request to save Task : {}", task);
        Story story = storyService.findOne(task.getStory().getId()).get();
        task.setStory(story);
        return taskRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        log.debug("Request to get all Tasks");
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAllByStoryNumber(String storyNumber) {
        log.debug("Request to get all Tasks of a story");
        return taskRepository.findAllByStory_storyNumber(storyNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Task> findOne(Long id) {
        log.debug("Request to get Task : {}", id);
        return taskRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Task : {}", id);
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteAllByStoryNumber(String storyNumber) {
        log.debug("Request to delete Task : {}", storyNumber);
        taskRepository.deleteAllByStory_storyNumber(storyNumber);
    }

    @Override
    public List<Object> createJiraImporterTxt() throws IOException {
        createJiraImporter();
        return downloadTextFile(true);
    }

    @Override
    public List<Object> createPlanningPokerTxt() throws IOException {
        createPlanningPoker();
        return downloadTextFile(false);
    }

    public File createJiraImporter() {
        int contador = 1;
        List<Task> list = taskRepository.findAll();
        for (Task task : list) {
            task.setTeam(teamName);
            task.setIssueId(contador++);
            task.setOriginalEstimate(task.getHours() * 3600);
        }
        return new CsvUtils().escreverJiraImporter(list);
    }

    public File createPlanningPoker() {
        return new CsvUtils().escreverPlanningPokerTxt(findAll());
    }

    @Override
    public String createFile(boolean jira) throws IOException {
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

    @Override
    public List<Object> downloadTextFile(boolean jira) throws IOException {
        String fileName = createFile(jira);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);

        return List.of(inputStreamResource, fileName);
    }
}
