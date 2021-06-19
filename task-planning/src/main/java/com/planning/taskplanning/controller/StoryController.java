package com.planning.taskplanning.controller;

import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.repository.StoryRepository;
import com.planning.taskplanning.service.StoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/story")
public class StoryController {

    private final Logger log = LoggerFactory.getLogger(StoryController.class);

    private final StoryService storyService;

    private final StoryRepository storyRepository;

    public StoryController(StoryService storyService, StoryRepository storyRepository) {
        this.storyService = storyService;
        this.storyRepository = storyRepository;
    }

    @PostMapping
    public ResponseEntity<?> createStory(@RequestBody Story story) throws URISyntaxException {
        log.debug("REST request to save Story : {}", story);
        if (Objects.isNull(story.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Story result = storyService.save(story);
        return ResponseEntity
                .created(new URI("/api/stories/" + result.getId()))
                .body(storyService.converteToDTO(result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStory(@PathVariable(value = "id", required = false) final String id, @RequestBody Story story) {
        log.debug("REST request to update Story : {}, {}", id, story);
        if (Objects.isNull(story.getId())) {
            return ResponseEntity.notFound().build();
        }
        if (!Objects.equals(id, story.getId())) {
            return ResponseEntity.badRequest().build();
        }

        if (!storyRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Story result = storyService.save(story);
        return ResponseEntity
                .ok()
                .body(storyService.converteToDTO(result));
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllStories(@RequestParam String id) {
        log.debug("REST request to get a page of Stories");
        if(Objects.isNull(id)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(storyService.converteToDTOList(storyService.findAllByUserId(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStory(@PathVariable String id) {
        log.debug("REST request to get Story : {}", id);
        if (storyRepository.existsById(id)) {
            return ResponseEntity.ok(storyService.converteToDTO(storyService.findOne(id).get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable String id) {
        log.debug("REST request to delete Story : {}", id);
        storyService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
