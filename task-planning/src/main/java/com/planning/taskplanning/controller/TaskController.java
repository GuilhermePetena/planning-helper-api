package com.planning.taskplanning.controller;

import com.planning.taskplanning.controller.errors.BadRequestAlertException;
import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.repository.TaskRepository;
import com.planning.taskplanning.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/task")
public class TaskController {
    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    private static final String ENTITY_NAME = "planningAppTask";

    private final TaskService taskService;

    private final TaskRepository taskRepository;

    private File file;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    /**
     * {@code POST  /tasks} : Create a new task.
     *
     * @param task the task to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new task, or with status {@code 400 (Bad Request)} if the task has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to save Task : {}", task);
        if (task.getId() != null) {
            throw new BadRequestAlertException("A new task cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Task result = taskService.save(task);
        return ResponseEntity
                .created(new URI("/api/tasks/" + result.getId()))
                .body(result);
    }

    /**
     * {@code PUT  /tasks/:id} : Updates an existing task.
     *
     * @param id   the id of the task to save.
     * @param task the task to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated task,
     * or with status {@code 400 (Bad Request)} if the task is not valid,
     * or with status {@code 500 (Internal Server Error)} if the task couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id", required = false) final Long id, @RequestBody Task task)
            throws URISyntaxException, BadRequestAlertException {
        log.debug("REST request to update Task : {}, {}", id, task);
        if (task.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, task.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!taskRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Task result = taskService.save(task);
        return ResponseEntity
                .ok()
                .body(result);
    }

    /**
     * {@code GET  /tasks} : get all the tasks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}.
     */
    @GetMapping()
    public ResponseEntity getAll(@RequestParam(required = false) boolean exportJiraImporter,
                                 @RequestParam(required = false) boolean exportPlanningPoker, @RequestParam(required = false) Optional<String> nomeTime, @RequestParam(required = false) Optional<String> jiraKey) throws IOException {
        List<Object> list;
        if (exportJiraImporter) {
            log.debug("REST request to export jiraImporter");
            list = taskService.createJiraImporterTxt();
            InputStreamResource inputStreamResource = (InputStreamResource) list.get(0);
            file = inputStreamResource.getFile();
            String fileName = (String) list.get(1);
            try {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                        .contentType(MediaType.TEXT_PLAIN)
                        .contentLength(file.length())
                        .body(inputStreamResource);
            } finally {
                file.delete();
            }
        }
        if (exportPlanningPoker) {
            log.debug("REST request to export planningPoker");
            list = taskService.createPlanningPokerTxt();
            InputStreamResource inputStreamResource = (InputStreamResource) list.get(0);
            file = inputStreamResource.getFile();
            String fileName = (String) list.get(1);
            try {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                        .contentType(MediaType.TEXT_PLAIN)
                        .contentLength(file.length())
                        .body(inputStreamResource);
            } finally {
                file.delete();
            }
        }
        if (nomeTime.isPresent()) {
            log.debug("REST request to add teamName");
            taskService.addTeamName(nomeTime.get());
            return ResponseEntity.ok().build();
        }
        if (jiraKey.isPresent()) {
            log.debug("REST request to get task of a story");
            return ResponseEntity.ok(taskService.findAllByStoryNumber(jiraKey.get()));
        }
        return ResponseEntity.ok(taskService.findAll());
    }

    /**
     * {@code GET  /tasks/:id} : get the "id" task.
     *
     * @param id the id of the task to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the task, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) throws BadRequestAlertException {
        log.debug("REST request to get Task : {}", id);
        if(taskRepository.existsById(id)){
            return ResponseEntity.ok(taskService.findOne(id).get());
        } else {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
    }

    /**
     * {@code DELETE  /tasks/:id} : delete the "id" task.
     *
     * @param id the id of the task to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        log.debug("REST request to delete Task : {}", id);
        taskService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
