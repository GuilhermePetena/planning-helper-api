package com.planning.taskplanning.model;

@Entity
public class Task {

    @Id
    private UUID id = UUID.randomUUID();
    private String issueType;
    private String description;
    private String summary;
    private Long hours;
    private Integer issueId;
    private String jiraKey;
    private String epicLink;
    private String complexityPoints;
    private String priority;
    private String components;
    private String fixVersions;
    private String labels;
    private String dueDate;
    private String team;
    private Long originalEstimate;

    public Task() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public void setHours(long hours) {
        this.hours = hours;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public String getJiraKey() {
        return jiraKey;
    }

    public void setJiraKey(String jiraKey) {
        this.jiraKey = jiraKey;
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

    public void setOriginalEstimate(long originalEstimate) {
        this.originalEstimate = originalEstimate;
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

    public Task(UUID id, String issueType, String description, String summary, Long hours, Integer issueId, String jiraKey, String epicLink, String complexityPoints, String priority, String components, String fixVersions, String labels, String dueDate, String team, Long originalEstimate) {
        this.id = id;
        this.issueType = issueType;
        this.description = description;
        this.summary = summary;
        this.hours = hours;
        this.issueId = issueId;
        this.jiraKey = jiraKey;
        this.epicLink = epicLink;
        this.complexityPoints = complexityPoints;
        this.priority = priority;
        this.components = components;
        this.fixVersions = fixVersions;
        this.labels = labels;
        this.dueDate = dueDate;
        this.team = team;
        this.originalEstimate = originalEstimate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return originalEstimate == task.originalEstimate &&
                Objects.equals(id, task.id) &&
                Objects.equals(issueType, task.issueType) &&
                Objects.equals(description, task.description) &&
                Objects.equals(summary, task.summary) &&
                Objects.equals(hours, task.hours) &&
                Objects.equals(issueId, task.issueId) &&
                Objects.equals(jiraKey, task.jiraKey) &&
                Objects.equals(epicLink, task.epicLink) &&
                Objects.equals(complexityPoints, task.complexityPoints) &&
                Objects.equals(priority, task.priority) &&
                Objects.equals(components, task.components) &&
                Objects.equals(fixVersions, task.fixVersions) &&
                Objects.equals(labels, task.labels) &&
                Objects.equals(dueDate, task.dueDate) &&
                Objects.equals(team, task.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issueType, description, summary, hours, issueId, jiraKey, epicLink, complexityPoints, priority, components, fixVersions, labels, dueDate, team, originalEstimate);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", issueType='" + issueType + '\'' +
                ", description='" + description + '\'' +
                ", summary='" + summary + '\'' +
                ", hours='" + hours + '\'' +
                ", issueId=" + issueId +
                ", jiraKey='" + jiraKey + '\'' +
                ", epicLink='" + epicLink + '\'' +
                ", complexityPoints='" + complexityPoints + '\'' +
                ", priority='" + priority + '\'' +
                ", components='" + components + '\'' +
                ", fixVersions='" + fixVersions + '\'' +
                ", labels='" + labels + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", team='" + team + '\'' +
                ", originalEstimate=" + originalEstimate +
                '}';
    }
}
