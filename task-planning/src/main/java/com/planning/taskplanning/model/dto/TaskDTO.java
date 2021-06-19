package com.planning.taskplanning.model.dto;

public class TaskDTO {

    private String id;
    private String issueType;
    private String description;
    private String summary;
    private Long hours;
    private Integer issueId;
    private String epicLink;
    private String complexityPoints;
    private String priority;
    private String components;
    private String fixVersions;
    private String labels;
    private String dueDate;
    private String team;
    private Long originalEstimate;
    private StoryDTO story;

    public TaskDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public String getEpicLink() {
        return epicLink;
    }

    public void setEpicLink(String epicLink) {
        this.epicLink = epicLink;
    }

    public String getComplexityPoints() {
        return complexityPoints;
    }

    public void setComplexityPoints(String complexityPoints) {
        this.complexityPoints = complexityPoints;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getFixVersions() {
        return fixVersions;
    }

    public void setFixVersions(String fixVersions) {
        this.fixVersions = fixVersions;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Long getOriginalEstimate() {
        return originalEstimate;
    }

    public void setOriginalEstimate(Long originalEstimate) {
        this.originalEstimate = originalEstimate;
    }

    public StoryDTO getStory() {
        return story;
    }

    public void setStory(StoryDTO story) {
        this.story = story;
    }
}
