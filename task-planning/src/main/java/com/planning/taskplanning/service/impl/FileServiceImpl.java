package com.planning.taskplanning.service.impl;

import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.service.FileService;
import com.planning.taskplanning.service.TaskService;
import com.planning.taskplanning.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    private static String teamName;
    public static File file;

    @Autowired
    private TaskService taskService;

    @Override
    public InputStreamResource returnJiraImporterTxt() throws IOException {
        return downloadTextFile();
    }

    @Override
    public InputStreamResource returnParametrizationFile() throws IOException {
        file = new FileUtils().returnParametrizationFile();
        return downloadTextFile();
    }

    @Override
    public void addTeamName(Map<String, String> nomeTime) {
        teamName = nomeTime.get("nomeTime");
    }

    @Override
    public InputStreamResource returnPlanningPokerTxt() throws IOException {
        return downloadTextFile();
    }

    public File createJiraImporter() {
        int contador = 1;
        List<Task> list = taskService.findAll();
        for (Task task : list) {
            task.setTeam(teamName);
            task.setIssueId(contador++);
            task.setOriginalEstimate(task.getHours() * 3600);
        }
        return new FileUtils().writeJiraImporter(list);
    }

    public File createPlanningPoker() {
        return new FileUtils().writePlanningPokerTxt(taskService.findAll());
    }

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

    @Override
    public InputStreamResource downloadTextFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);

        return inputStreamResource;
    }
}
