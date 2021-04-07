package com.planning.taskplanning.model;

public class Task {
    private String issueType;
    private String description;
    private String title;

    public Task() {
    }
    public Task(String issueType, String description, String title) {
        this.issueType = issueType;
        this.description = description;
        this.title = title;
    }
    public String getIssueType() {
        return issueType;
    }
    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
