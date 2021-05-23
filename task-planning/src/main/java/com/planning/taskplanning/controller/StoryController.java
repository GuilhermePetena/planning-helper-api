package com.planning.taskplanning.controller;

import com.planning.taskplanning.controller.errors.BadRequestAlertException;
import com.planning.taskplanning.model.Story;
import com.planning.taskplanning.repository.StoryRepository;
import com.planning.taskplanning.service.StoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/story")
public class StoryController {

    private final Logger log = LoggerFactory.getLogger(StoryController.class);

    private static final String ENTITY_NAME = "planningAppStory";

    private final StoryService storyService;

    private final StoryRepository storyRepository;

    public StoryController(StoryService storyService, StoryRepository storyRepository) {
        this.storyService = storyService;
        this.storyRepository = storyRepository;
    }

    /**
     * {@code POST  /stories} : Create a new story.
     *
     * @param story the story to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new story, or with status {@code 400 (Bad Request)} if the story has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping
    public ResponseEntity<Story> createStory(@RequestBody Story story) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to save Story : {}", story);
        if (story.getId() != null) {
            throw new BadRequestAlertException("A new story cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Story result = storyService.save(story);
        return ResponseEntity
                .created(new URI("/api/stories/" + result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /stories/:id} : Updates an existing story.
     *
     * @param id    the id of the story to save.
     * @param story the story to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated story,
     * or with status {@code 400 (Bad Request)} if the story is not valid,
     * or with status {@code 500 (Internal Server Error)} if the story couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Story> updateStory(@PathVariable(value = "id", required = false) final Long id, @RequestBody Story story)
            throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to update Story : {}, {}", id, story);
        if (story.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, story.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!storyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Story result = storyService.save(story);
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * {@code GET  /stories} : get all the stories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stories in body.
     */
    @GetMapping
    public ResponseEntity<List<Story>> getAllStories(Pageable pageable) {
        log.debug("REST request to get a page of Stories");
        Page<Story> page = storyService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /stories/:id} : get the "id" story.
     *
     * @param id the id of the story to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the story, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Story> getStory(@PathVariable Long id) throws BadRequestAlertException {
        log.debug("REST request to get Story : {}", id);
        if (storyRepository.existsById(id)) {
            return ResponseEntity.ok(storyService.findOne(id).get());
        } else {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
    }

    /**
     * {@code DELETE  /stories/:id} : delete the "id" story.
     *
     * @param id the id of the story to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable Long id) {
        log.debug("REST request to delete Story : {}", id);
        storyService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
