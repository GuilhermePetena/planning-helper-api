package com.planning.taskplanning.controller;

import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.repository.TaskRepository;
import com.planning.taskplanning.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/task")
public class TaskController {
    private final Logger log = LoggerFactory.getLogger(TaskController.class);


    private final TaskService taskService;

    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody(required = false) Task task) throws URISyntaxException {
        log.debug("REST request to save Task: {}", task);
        if (Objects.nonNull(task)) {
            Task result = taskService.save(task);
            return ResponseEntity
                    .created(new URI("/api/tasks/" + result.getId()))
                    .body(taskService.converteToDTO(result));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(value = "id", required = false) final String id, @RequestBody Task task) {
        log.debug("REST request to update Task : {}, {}", id, task);
        if (task.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (!Objects.equals(id, task.getId())) {
            return ResponseEntity.badRequest().build();
        }
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Task result = taskService.save(task);
        return ResponseEntity
                .ok()
                .body(taskService.converteToDTO(result));
    }

    @GetMapping()
    public ResponseEntity getAll(@RequestParam(required = false) Optional<String> jiraKey, @RequestParam(required = false) Optional<String> id) {
        log.debug("REST request to get task of a story", jiraKey, id);
        if (jiraKey.isPresent()) {
            return ResponseEntity.ok(taskService.converteToDTOList(taskService.findAllByStoryNumber(jiraKey.get())));
        }if (id.isPresent()){
            return ResponseEntity.ok(taskService.converteToDTOList(taskService.findAllByUserId(id.get())));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable String id) {
        log.debug("REST request to get Task : {}", id);
        if (taskRepository.existsById(id)) {
            return ResponseEntity.ok(taskService.converteToDTO(taskService.findOne(id).get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        log.debug("REST request to delete Task : {}", id);
        taskService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

}
