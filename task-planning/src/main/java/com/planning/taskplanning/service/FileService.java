package com.planning.taskplanning.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public interface FileService {

    void addTeamName(Map<String, String> nomeTime);

    InputStreamResource returnPlanningPokerTxt() throws IOException;

    InputStreamResource returnJiraImporterTxt() throws IOException;

    InputStreamResource returnParametrizationFile() throws IOException;

    InputStreamResource downloadTextFile() throws IOException;

    String createFile(boolean jira) throws IOException;


}
