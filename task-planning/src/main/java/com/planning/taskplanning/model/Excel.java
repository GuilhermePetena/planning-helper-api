package com.planning.taskplanning.model;

@Entity
public class Story {

    @Id
    private UUID id = UUID.randomUUID();
    private String title;
    private String storyNumber;

    public Story() {
    }

    public Story(UUID id, String title, String storyNumber) {
        this.id = id;
        this.title = title;
        this.storyNumber = storyNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", storyNumber='" + storyNumber + '\'' +
                '}';
    }

