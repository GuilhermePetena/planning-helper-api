package com.planning.taskplanning.service;

import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StoryService {

    @Autowired
    private StoryRepository repository;

    @Autowired
    private TaskService taskService;

    public List<Story> listarStories(){
        return repository.findAll();
    }
    public Story obterStory(String jiraKey){
        return repository.findById(jiraKey).get();
    }
    public Story criarStory(Story story){
        return repository.save(story);
    }

    public void removerStory(String jiraKey){
        taskService.deleteAll(jiraKey);
        repository.deleteById(jiraKey);
    }

    public Story atualizarStory(String jiraKey, Story story){
        Story storyAntiga = obterStory(jiraKey);
        storyAntiga.setJiraKey(story.getJiraKey());
        storyAntiga.setTitle(story.getTitle());
        criarStory(storyAntiga);
        return storyAntiga;
    }
    public boolean storyExiste(String jiraKey){
        return repository.existsById(jiraKey);
    }
}
