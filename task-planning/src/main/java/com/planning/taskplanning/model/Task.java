package com.planning.taskplanning.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Task - domain class
 * @Author Guilherme Maciel Petena
 */

@Entity
@Table(name = "task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "issue_type")
    private String issueType;

    @Column(name = "description")
    private String description;

    @Column(name = "summary")
    private String summary;

    @Column(name = "hours")
    private Long hours;

    @Column(name = "issue_id")
    private Integer issueId;

    @Column(name = "epic_link")
    private String epicLink;

    @Column(name = "complexity_points")
    private String complexityPoints;

    @Column(name = "priority")
    private String priority;

    @Column(name = "components")
    private String components;

    @Column(name = "fix_versions")
    private String fixVersions;

    @Column(name = "labels")
    private String labels;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "team")
    private String team;

    @Column(name = "original_estimate")
    private Long originalEstimate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "tasks" }, allowSetters = true)
    private Story story;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task id(Long id) {
        this.id = id;
        return this;
    }

    public String getIssueType() {
        return this.issueType;
    }

    public Task issueType(String issueType) {
        this.issueType = issueType;
        return this;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getDescription() {
        return this.description;
    }

    public Task description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return this.summary;
    }

    public Task summary(String summary) {
        this.summary = summary;
        return this;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getHours() {
        return this.hours;
    }

    public Task hours(Long hours) {
        this.hours = hours;
        return this;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public Integer getIssueId() {
        return this.issueId;
    }

    public Task issueId(Integer issueId) {
        this.issueId = issueId;
        return this;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public String getEpicLink() {
        return this.epicLink;
    }

    public Task epicLink(String epicLink) {
        this.epicLink = epicLink;
        return this;
    }

    public void setEpicLink(String epicLink) {
        this.epicLink = epicLink;
    }

    public String getComplexityPoints() {
        return this.complexityPoints;
    }

    public Task complexityPoints(String complexityPoints) {
        this.complexityPoints = complexityPoints;
        return this;
    }

    public void setComplexityPoints(String complexityPoints) {
        this.complexityPoints = complexityPoints;
    }

    public String getPriority() {
        return this.priority;
    }

    public Task priority(String priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComponents() {
        return this.components;
    }

    public Task components(String components) {
        this.components = components;
        return this;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getFixVersions() {
        return this.fixVersions;
    }

    public Task fixVersions(String fixVersions) {
        this.fixVersions = fixVersions;
        return this;
    }

    public void setFixVersions(String fixVersions) {
        this.fixVersions = fixVersions;
    }

    public String getLabels() {
        return this.labels;
    }

    public Task labels(String labels) {
        this.labels = labels;
        return this;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public Task dueDate(String dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTeam() {
        return this.team;
    }

    public Task team(String team) {
        this.team = team;
        return this;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Long getOriginalEstimate() {
        return this.originalEstimate;
    }

    public Task originalEstimate(Long originalEstimate) {
        this.originalEstimate = originalEstimate;
        return this;
    }

    public void setOriginalEstimate(Long originalEstimate) {
        this.originalEstimate = originalEstimate;
    }

    public Story getStory() {
        return this.story;
    }

    public Task story(Story story) {
        this.setStory(story);
        return this;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        return id != null && id.equals(((Task) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Task{" +
                "id=" + getId() +
                ", issueType='" + getIssueType() + "'" +
                ", description='" + getDescription() + "'" +
                ", summary='" + getSummary() + "'" +
                ", hours=" + getHours() +
                ", issueId=" + getIssueId() +
                ", story='" + getStory() + "'" +
                ", epicLink='" + getEpicLink() + "'" +
                ", complexityPoints='" + getComplexityPoints() + "'" +
                ", priority='" + getPriority() + "'" +
                ", components='" + getComponents() + "'" +
                ", fixVersions='" + getFixVersions() + "'" +
                ", labels='" + getLabels() + "'" +
                ", dueDate='" + getDueDate() + "'" +
                ", team='" + getTeam() + "'" +
                ", originalEstimate=" + getOriginalEstimate() +
                "}";
    }
}
