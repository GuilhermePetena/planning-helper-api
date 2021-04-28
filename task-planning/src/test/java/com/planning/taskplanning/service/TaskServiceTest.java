package com.planning.taskplanning.service;

import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.utils.TaskMockTestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
public class TaskServiceTest {

        @TestConfiguration
        static class TaskServiceTestContextConfiguration {

            @Bean
            public TaskService taskServiceService() {
                return new TaskService();
            }
        }

        @Autowired
        private TaskService taskService;

        @MockBean
        private com.planning.taskplanning.repository.TaskRepository taskRepository;

        private List<Task> taskList = new ArrayList<>();

        @Before
        public void setUp(){
            Task task = TaskMockTestUtils.mockTask();
            taskList.add(task);
        }

        @Test
        public void testeListarTask(){

            Mockito.when(taskRepository.findAll())
                    .thenReturn(taskList);

            List<Task> lista = new ArrayList<>();
            lista.add(taskService.listarTasks().get(0));

            Assert.assertEquals("Sub-Test", lista.get(0).getIssueType());
        }

        @Test
        public void testeObterTask(){

            Mockito.when(taskRepository.findById(UUID.fromString("27414992-14b7-4182-a199-3fed4b205586")))
                    .thenReturn(Optional.of(taskList.get(0)));

            Task taskNova = taskService.obterTask(UUID.fromString("27414992-14b7-4182-a199-3fed4b205586")).get();

            Assert.assertEquals("Sub-Test", taskNova.getIssueType());
        }

        @Test
        public void testeCriarTask(){

            Mockito.when(taskRepository.save(taskList.get(0)))
                    .thenReturn(taskList.get(0));

            Task taskNova = taskService.criarTask(taskList.get(0));

            Assert.assertEquals("Sub-Test", taskNova.getIssueType());
        }

        @Test
        public void testeAtualizarTask(){

            Mockito.when(taskRepository.save(taskList.get(0)))
                    .thenReturn(taskList.get(0));

            Mockito.when(taskRepository.findById(UUID.fromString("27414992-14b7-4182-a199-3fed4b205586")))
                    .thenReturn(Optional.of(taskList.get(0)));

            Task taskAtualizada = taskService.atualizarTask(UUID.fromString("27414992-14b7-4182-a199-3fed4b205586"),taskList.get(0)).get();

            Assert.assertEquals("Sub-Test", taskAtualizada.getIssueType());
        }
    }

