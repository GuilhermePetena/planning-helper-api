package com.planning.taskplanning.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Story - domain class
 * @Author Guilherme Maciel Petena
 */

@Entity
@Table(name = "story")
public class Story implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "story_number")
    private String storyNumber;

    @OneToMany(mappedBy = "story")
    @JsonIgnoreProperties(value = { "story" }, allowSetters = true)
    private Set<Task> tasks = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Story id(Long id) {
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

    public Set<Task> getTasks() {
        return this.tasks;
    }

    public Story tasks(Set<Task> tasks) {
        this.setTasks(tasks);
        return this;
    }

    public Story addTask(Task task) {
        this.tasks.add(task);
        task.setStory(this);
        return this;
    }

    public Story removeTask(Task task) {
        this.tasks.remove(task);
        task.setStory(null);
        return this;
    }

    public void setTasks(Set<Task> tasks) {
        if (this.tasks != null) {
            this.tasks.forEach(i -> i.setStory(null));
        }
        if (tasks != null) {
            tasks.forEach(i -> i.setStory(this));
        }
        this.tasks = tasks;
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
                "id=" + getId() +
                ", title='" + getTitle() + "'" +
                ", storyNumber='" + getStoryNumber() + "'" +
                "}";
    }
}

