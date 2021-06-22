package com.planning.taskplanning.model.dto;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoryDTO storyDTO = (StoryDTO) o;
        return Objects.equals(id, storyDTO.id) && Objects.equals(title, storyDTO.title) && Objects.equals(storyNumber, storyDTO.storyNumber) && Objects.equals(user, storyDTO.user) && Objects.equals(task, storyDTO.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, storyNumber, user, task);
    }
}
