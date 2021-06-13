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

/**
 * Controller of the story routes
 * @Author Guilherme Maciel Petena
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/story")
public class StoryController {

    private final Logger log = LoggerFactory.getLogger(StoryController.class);


    private final StoryService storyService;

    private final StoryRepository storyRepository;

    /**
     * Constructor
     * @param storyService
     * @param storyRepository
     */
    public StoryController(StoryService storyService, StoryRepository storyRepository) {
        this.storyService = storyService;
        this.storyRepository = storyRepository;
    }

    /**
     * {@code POST  /story} : Create a new story.
     *
     * @param story the story to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new story, or with status {@code 400 (Bad Request)} if the story has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping
    public ResponseEntity<Story> createStory(@RequestBody Story story) throws URISyntaxException {
        log.debug("REST request to save Story : {}", story);
        if (Objects.isNull(story.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Story result = storyService.save(story);
        return ResponseEntity
                .created(new URI("/api/stories/" + result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /story/:id} : Updates an existing story.
     *
     * @param id    the id of the story to save.
     * @param story the story to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated story,
     * or with status {@code 400 (Bad Request)} if the story is not valid,
     * or with status {@code 500 (Internal Server Error)} if the story couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Story> updateStory(@PathVariable(value = "id", required = false) final String id, @RequestBody Story story) {
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
                .body(result);
    }

    /**
     * {@code GET  /story} : get all the stories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stories in body.
     */
    @GetMapping
    public ResponseEntity<List<Story>> getAllStories() {
        log.debug("REST request to get a page of Stories");
        List<Story> list = storyService.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * {@code GET  /story/:id} : get the "id" story.
     *
     * @param id the id of the story to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the story, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Story> getStory(@PathVariable String id) {
        log.debug("REST request to get Story : {}", id);
        if (storyRepository.existsById(id)) {
            return ResponseEntity.ok(storyService.findOne(id).get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code DELETE  /story/:id} : delete the "id" story.
     *
     * @param id the id of the story to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable String id) {
        log.debug("REST request to delete Story : {}", id);
        storyService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
