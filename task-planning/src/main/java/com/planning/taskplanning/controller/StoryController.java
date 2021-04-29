package com.planning.taskplanning.controller;

import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/story")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @GetMapping()
    public ResponseEntity<List<Story>> getAll() {
        return ResponseEntity.ok(storyService.listarStories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Story> findOne(@PathVariable("id") UUID id) {
        if(storyService.storyExiste(id)){
            return ResponseEntity.ok(storyService.obterStory(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Story> create(@RequestBody Story story) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storyService.criarStory(story));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Story> update(@PathVariable("id") UUID id, @RequestBody Story story) {
    if(storyService.storyExiste(id)){
        return ResponseEntity.ok().body(storyService.atualizarStory(id, story));
    }else {
        return ResponseEntity.notFound().build();
    }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Story> delete(@PathVariable("id") UUID id) {
    if (storyService.storyExiste(id)) {
        storyService.removerStory(id);
        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.notFound().build();
    }
    }
}
