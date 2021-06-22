package com.planning.taskplanning.service;

import com.planning.taskplanning.mock.StoryMock;
import com.planning.taskplanning.mock.TaskMock;
import com.planning.taskplanning.mock.UserMock;
import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.model.User;
import com.planning.taskplanning.repository.TaskRepository;
import com.planning.taskplanning.service.impl.TaskServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private StoryService storyService;
    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    private User user = UserMock.getUserMock();
    private List<Story> stories = StoryMock.getStoryMock();
    private Task task = TaskMock.getMockTask1();
    private Task task2 = TaskMock.getMockTask2();

    @Test
    public void saveTaskTest() {
        task.setStory(stories.get(0));
        when(storyService.findOne(any())).thenReturn(java.util.Optional.ofNullable(stories.get(0)));
        when(taskRepository.save(any())).thenReturn(task);
        when(taskServiceImpl.save(task)).thenReturn(task);
        Task taskNew = taskServiceImpl.save(task);
        assertNotNull(taskNew);
        assertEquals("ABCD", taskNew.getId());
        assertEquals("Sub-test", taskNew.getIssueType());
        assertEquals("Criar teste 2xx", taskNew.getDescription());
        assertEquals(Long.valueOf(5), taskNew.getHours());
        assertEquals(Integer.valueOf(1), taskNew.getIssueId());
        assertEquals("www.link.com.br", taskNew.getEpicLink());
        assertEquals("5", taskNew.getComplexityPoints());
        assertEquals("HIGH", taskNew.getPriority());
        assertEquals("teste_integracao", taskNew.getLabels());
        assertEquals("Contratacao", taskNew.getTeam());
        assertEquals(stories.get(0), taskNew.getStory());
    }
}
