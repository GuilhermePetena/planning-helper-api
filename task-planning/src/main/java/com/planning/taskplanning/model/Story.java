package com.planning.taskplanning.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Story implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(name = "title")
    private String title;

    @Column(name = "story_number")
    private String storyNumber;

    @ManyToOne
    private User user;

    @OneToMany
    @JsonIgnoreProperties(value = {"story"}, allowSetters = true)
    private List<Task> task = new ArrayList<>();

    public Story() {
    }

    public Story(String id, String title, String storyNumber, User user, List<Task> tasks) {
        this.id = id;
        this.title = title;
        this.storyNumber = storyNumber;
        this.user = user;
        this.task = tasks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Story id(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public Story title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStoryNumber() {
        return this.storyNumber;
    }

    public Story storyNumber(String storyNumber) {
        this.storyNumber = storyNumber;
        return this;
    }

    public void setStoryNumber(String storyNumber) {
        this.storyNumber = storyNumber;
    }

    public List<Task> getTasks() {
        return task;
    }

    public void setTasks(List<Task> tasks) {
        this.task = tasks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Story)) {
            return false;
        }
        return id != null && id.equals(((Story) o).id);
    }

    @Override
    public String toString() {
        return "Story{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", storyNumber='" + storyNumber + '\'' +
                ", user=" + user +
                ", task=" + task +
                '}';
    }
}

