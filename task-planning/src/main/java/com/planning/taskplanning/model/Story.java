package com.planning.taskplanning.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Story {

    @Id
    private String jiraKey;
    private String title;


    public Story() {
    }

    public Story(String title, String storyNumber) {
        this.title = title;
        this.jiraKey = storyNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJiraKey() {
        return jiraKey;
    }

    public void setJiraKey(String jiraKey) {
        this.jiraKey = jiraKey;
    }

    @Override
    public String toString() {
        return "Story{" +
                "jiraKey='" + jiraKey + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

