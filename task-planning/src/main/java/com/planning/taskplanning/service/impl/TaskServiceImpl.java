package com.planning.taskplanning.service.impl;

import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.model.dto.TaskDTO;
import com.planning.taskplanning.repository.TaskRepository;
import com.planning.taskplanning.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;

    @Autowired
    private StoryServiceImpl storyService;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        log.debug("Request to save Task : {}", task);
        Story story = storyService.findOne(task.getStory().getId()).get();
        task.setStory(story);
        return taskRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAll() {
        log.debug("Request to get all Tasks");
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAllByStoryNumber(String storyNumber) {
        log.debug("Request to get all Tasks of a story");
        return taskRepository.findAllByStory_storyNumber(storyNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Task> findOne(String id) {
        log.debug("Request to get Task : {}", id);
        return taskRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Task : {}", id);
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteAllByStoryNumber(String storyNumber) {
        log.debug("Request to delete Task : {}", storyNumber);
        taskRepository.deleteAllByStory_storyNumber(storyNumber);
    }

    @Override
    public List<Task> findAllByUserId(String id) {
        log.debug("Request to get all Tasks of a story : {}");
        return taskRepository.findAllByStory_User_id(id);
    }

    @Override
    public TaskDTO converteToDTO(Task task) {
        log.debug("Convert object to DTO");
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setComplexityPoints(task.getComplexityPoints());
        taskDTO.setComponents(task.getComponents());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setEpicLink(task.getEpicLink());
        taskDTO.setHours(task.getHours());
        taskDTO.setFixVersions(task.getFixVersions());
        taskDTO.setIssueId(task.getIssueId());
        taskDTO.setOriginalEstimate(task.getOriginalEstimate());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setSummary(task.getSummary());
        taskDTO.setLabels(task.getLabels());
        taskDTO.setTeam(task.getTeam());
        taskDTO.setIssueType(task.getIssueType());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setStory(storyService.converteToDTO(task.getStory()));
        return taskDTO;
    }

    @Override
    public List<TaskDTO> converteToDTOList(List<Task> tasks) {
        log.debug("Convert object to DTO");
        List<TaskDTO> dtoList = new ArrayList<>();
        for (Task task : tasks) {
            dtoList.add(converteToDTO(task));
        }
        return dtoList;
    }
}
