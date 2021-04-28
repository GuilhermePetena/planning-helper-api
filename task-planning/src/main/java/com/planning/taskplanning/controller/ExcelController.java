package com.planning.taskplanning.controller;

import com.planning.taskplanning.model.Excel;
import com.planning.taskplanning.service.ExcelService;
import com.planning.taskplanning.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
