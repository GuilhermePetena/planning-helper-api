package com.planning.taskplanning.controller;


import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/addTeam")
    public ResponseEntity addTime(@RequestParam String nomeTime) {
        if(nomeTime.isBlank()){
            return ResponseEntity.badRequest().build();
        }else {
            taskService.adicionarNomeDoTime(nomeTime);
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping("/exportJiraImporter")
    public ResponseEntity<String> criarJiraImporter() {
        taskService.criarJiraImporter();
        return ResponseEntity.ok().body(System.getProperty("user.home")+"\\downloads\\jiraImporter.txt");
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(taskService.listarTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findOne(@PathVariable("id") UUID id) {
        if(taskService.taskExiste(id)){
            return ResponseEntity.ok(taskService.obterTask(id).get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Task> create(@RequestBody Task task) {
         return ResponseEntity.status(HttpStatus.CREATED).body(taskService.criarTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable("id") UUID id, @RequestBody Task task) {
        if(taskService.taskExiste(id) && !task.getIssueType().isBlank()){
            return  ResponseEntity.ok().body(taskService.atualizarTask(id, task).get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") UUID id){
        if (taskService.taskExiste(id)){
            taskService.delete(id);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
}
