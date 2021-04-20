package com.planning.projectissue.utils;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CsvUtils {
    public void escreverCsv(List<Task> tasks) {

        try {
            File file = new File(System.getProperty("user.home") + "\\downloads\\jiraImporter.txt");
            file.createNewFile();
            String[] headers = {"Issue Type", "Summary", "Description", "Hours(Planning)", "IssueID", "Parent ID/Jira Key", "Epic Link", "Complexity Points", "Priority", "Components", "Fix Versions", "Labels", "Due Date", "Team", "Original Estimate"};
            Writer writer = Files.newBufferedWriter(Paths.get(System.getProperty("user.home") + "\\downloads\\jiraImporter.txt"));
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> linhas = new ArrayList<>();

            for (Task task : tasks) {
                linhas.add(new String[]{task.getIssueType(), task.getSummary(), task.getDescription(), task.getHours().toString(), task.getIssueId().toString(), task.getJiraKey(), task.getEpicLink(), task.getComplexityPoints(), task.getPriority(), task.getComponents(), task.getFixVersions(), task.getLabels(), task.getDueDate(), task.getTeam(), task.getOriginalEstimate().toString()});
            }

            csvWriter.writeNext(headers);
            csvWriter.writeAll(linhas);

            csvWriter.flush();
            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
