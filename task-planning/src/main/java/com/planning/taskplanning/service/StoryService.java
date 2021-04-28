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

    public List<Story> listarStories(){
        return repository.findAll();
    }
    public Story obterStory(UUID id){
        return repository.findById(id).get();
    }
    public Story criarStory(Story story){
        return repository.save(story);
    }
    public boolean storyExiste(UUID id){
        return repository.existsById(id);
    }
}
