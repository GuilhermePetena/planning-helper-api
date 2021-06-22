package com.planning.taskplanning.mock;

import com.planning.taskplanning.model.Task;

public class TaskMock {
    public static Task getMockTask1() {
        Task task = new Task();
        task.setId("ABCD");
        task.setIssueType("Sub-test");
        task.setDescription("Criar teste 2xx");
        task.setSummary("Resumo resumido");
        task.setHours(5L);
        task.setIssueId(1);
        task.setEpicLink("www.link.com.br");
        task.setComplexityPoints("5");
        task.setPriority("HIGH");
        task.setComponents("");
        task.setFixVersions("");
        task.setLabels("teste_integracao");
        task.dueDate("");
        task.team("Contratacao");
        task.originalEstimate(null);
        task.setStory(StoryMock.getStoryMock().get(0));
        return task;
    }
    public static Task getMockTask2() {
        Task task = new Task();
        task.setId("EFGH");
        task.setIssueType("Sub-Development");
        task.setDescription("Criar classe service");
        task.setSummary("Resumo resumido");
        task.setHours(5L);
        task.setIssueId(1);
        task.setEpicLink("www.link.com.br");
        task.setComplexityPoints("5");
        task.setPriority("HIGH");
        task.setComponents("");
        task.setFixVersions("");
        task.setLabels("");
        task.dueDate("");
        task.team("");
        task.originalEstimate(null);
        task.setStory(StoryMock.getStoryMock().get(1));
        return task;
    }
}
