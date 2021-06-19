package com.planning.taskplanning.model.dto;

import java.util.List;

public class StoryDTO {

    private String id;
    private String title;
    private String storyNumber;
    private UserDTO user;
    private List<TaskDTO> task;

    public StoryDTO(String id, String title, String storyNumber, UserDTO user, List<TaskDTO> tasks) {
        this.id = id;
        this.title = title;
        this.storyNumber = storyNumber;
        this.user = user;
        this.task = tasks;
    }

    public StoryDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStoryNumber() {
        return storyNumber;
    }

    public void setStoryNumber(String storyNumber) {
        this.storyNumber = storyNumber;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<TaskDTO> getTask() {
        return task;
    }

    public void setTask(List<TaskDTO> task) {
        this.task = task;
    }
}
