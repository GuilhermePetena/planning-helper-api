package com.planning.taskplanning.utils;

import com.planning.taskplanning.model.Task;

import java.util.UUID;

public class TaskMockTestUtils {
    public static Task mockTask(){
        Task task = new Task();
        task.setId(UUID.fromString("27414992-14b7-4182-a199-3fed4b205586"));
        task.setIssueId(1);
        task.setTeam("Contratação");
        task.setIssueType("Sub-Test");
        task.setSummary("Testar funcionalidade Y");
        task.setLabels("teste_unit");
        task.setPriority("ALTA");
        task.setJiraKey("CSA-1234");
        task.setHours(5);
        task.setEpicLink("");
        task.setFixVersions("");
        task.setDescription("Teste unitarios das camadas X Y Z B A D");
        task.setDueDate("");
        task.setComplexityPoints("8");
        task.setComponents("");
        task.setOriginalEstimate(task.getHours() * 3600);
        return task;
    }
}
