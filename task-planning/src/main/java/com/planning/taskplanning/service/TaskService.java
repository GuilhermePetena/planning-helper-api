package com.planning.taskplanning.service;

import com.planning.taskplanning.model.Task;
import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface TaskService {

    Task save(Task task);

    void addTeamName(String teamName);

    List<Task> findAll();

    List<Task> findAllByStoryNumber(String storyNumber);

    Optional<Task> findOne(String id);

    void delete(String id);

    void deleteAllByStoryNumber(String storyNumber);

    InputStreamResource downloadTextFile() throws IOException;

    String createFile(boolean jira) throws IOException;

    InputStreamResource returnPlanningPokerTxt() throws IOException;

    InputStreamResource returnJiraImporterTxt() throws IOException;

    InputStreamResource returnParametrizationFile() throws IOException;
}
