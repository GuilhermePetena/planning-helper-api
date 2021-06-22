package com.planning.taskplanning.mock;

import com.planning.taskplanning.model.Story;

import java.util.List;

public class StoryMock {

    public static List<Story> getStoryMock(){
        return List.of(
                new Story("1234", "Mock da historia", "CSEG-1234", UserMock.getUserMock(), null),
                new Story("5678", "Mock do teste", "CSEG-5678", UserMock.getUserMock(), null));
    }
}
