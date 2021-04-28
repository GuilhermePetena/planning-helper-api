package com.planning.taskplanning.service;


import com.planning.projectissue.utils.ExcelUtils;
import com.planning.taskplanning.model.Excel;
import com.planning.taskplanning.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
