package com.planning.taskplanning.controller;

import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/story")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @GetMapping()
    public ResponseEntity<List<Story>> getAll() {
        return ResponseEntity.ok(storyService.listarStories());
    }

    @GetMapping("/{jiraKey}")
    public ResponseEntity<Story> findOne(@PathVariable("jiraKey") String jiraKey) {
        if(storyService.storyExiste(jiraKey)){
            return ResponseEntity.ok(storyService.obterStory(jiraKey));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Story> create(@RequestBody Story story) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storyService.criarStory(story));
    }

    @PutMapping("/{jiraKey}")
    public ResponseEntity<Story> update(@PathVariable("jiraKey") String jiraKey, @RequestBody Story story) {
        if(storyService.storyExiste(jiraKey)){
            return ResponseEntity.ok().body(storyService.atualizarStory(jiraKey, story));
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{jiraKey}")
    public ResponseEntity<Story> delete(@PathVariable("jiraKey") String jiraKey) {
        if (storyService.storyExiste(jiraKey)) {
            storyService.removerStory(jiraKey);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
