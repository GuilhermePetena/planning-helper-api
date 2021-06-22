package com.planning.taskplanning.service;

import com.planning.taskplanning.mock.StoryMock;
import com.planning.taskplanning.mock.TaskMock;
import com.planning.taskplanning.mock.UserMock;
import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.model.User;
import com.planning.taskplanning.model.dto.StoryDTO;
import com.planning.taskplanning.repository.StoryRepository;
import com.planning.taskplanning.service.impl.StoryServiceImpl;
import com.planning.taskplanning.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StoryServiceTest {

    @Mock
    private StoryRepository storyRepository;
    @Mock
    private UserService userService;
    @Mock
    private TaskService taskService;
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @InjectMocks
    private StoryServiceImpl storyServiceImpl;

    private final User user = UserMock.getUserMock();
    private final List<Story> stories = StoryMock.getStoryMock();
    private final Task task = TaskMock.getMockTask1();

    @Test
    public void saveStoryTest() {
        stories.get(0).setTasks(List.of(task));
        when(userService.findOne(Mockito.anyString())).thenReturn(Optional.of(user));
        when(storyRepository.save(Mockito.any())).thenReturn(stories.get(0));
        Story story = storyServiceImpl.save(stories.get(0));
        assertNotNull(story);
        assertEquals("1234", story.getId());
        assertEquals("Mock da historia", story.getTitle());
        assertEquals("CSEG-1234", story.getStoryNumber());
        assertEquals(user, story.getUser());
        assertEquals(List.of(task), story.getTasks());
    }

    @Test
    public void findAllStoriesTest() {
        stories.get(0).setTasks(List.of(task));
        when(storyRepository.findAll()).thenReturn(stories);
        List<Story> story = storyServiceImpl.findAll();
        assertNotNull(story);
        assertEquals("1234", story.get(0).getId());
        assertEquals("Mock da historia", story.get(0).getTitle());
        assertEquals("CSEG-1234", story.get(0).getStoryNumber());
        assertEquals(user, story.get(0).getUser());
        assertEquals(List.of(task), story.get(0).getTasks());
    }

    @Test
    public void findAllStoriesByUserIdTest() {
        stories.get(0).setTasks(List.of(task));
        when(storyRepository.findAllByUser_Id(Mockito.anyString())).thenReturn(stories);
        List<Story> story = storyServiceImpl.findAllByUserId("1234-ABCD");
        assertNotNull(story);
        assertEquals("1234", story.get(0).getId());
        assertEquals("Mock da historia", story.get(0).getTitle());
        assertEquals("CSEG-1234", story.get(0).getStoryNumber());
        assertEquals(user, story.get(0).getUser());
        assertEquals(List.of(task), story.get(0).getTasks());
    }

    @Test
    public void findOneStoryTest() {
        stories.get(0).setTasks(List.of(task));
        when(storyRepository.findById(Mockito.any())).thenReturn(Optional.of(stories.get(0)));
        Optional<Story> story = storyServiceImpl.findOne(stories.get(0).getId());
        assertNotNull(story);
        assertEquals("1234", story.get().getId());
        assertEquals("Mock da historia", story.get().getTitle());
        assertEquals("CSEG-1234", story.get().getStoryNumber());
        assertEquals(user, story.get().getUser());
        assertEquals(List.of(task), story.get().getTasks());
    }
    @Test
    public void deleteTest(){
        when(storyServiceImpl.findOne(Mockito.anyString())).thenReturn(Optional.ofNullable(stories.get(0)));
        storyServiceImpl.delete("1234");
        verify(storyRepository, times(1)).deleteById("1234");
    }

    @Test
    public void convertToDtoTest() {
        StoryDTO storyDTO = storyServiceImpl.converteToDTO(stories.get(0));
        storyDTO.setUser(userServiceImpl.converteToDTO(user));
        assertNotNull(storyDTO);
        assertEquals("1234", storyDTO.getId());
        assertEquals("Mock da historia", storyDTO.getTitle());
        assertEquals("CSEG-1234", storyDTO.getStoryNumber());
        assertEquals(user.getId(), storyDTO.getUser().getId());
        assertEquals(user.getEmail(), storyDTO.getUser().getEmail());
    }

    @Test
    public void convertToDtoListTest() {
        List<StoryDTO> storyDTO = storyServiceImpl.converteToDTOList(stories);
        storyDTO.get(0).setUser(userServiceImpl.converteToDTO(user));
        assertNotNull(storyDTO);
        assertEquals("1234", storyDTO.get(0).getId());
        assertEquals("Mock da historia", storyDTO.get(0).getTitle());
        assertEquals("CSEG-1234", storyDTO.get(0).getStoryNumber());
        assertEquals(user.getId(), storyDTO.get(0).getUser().getId());
        assertEquals(user.getEmail(), storyDTO.get(0).getUser().getEmail());
    }

}
