package com.planning.taskplanning.controller;


import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.service.ExcelService;
import com.planning.taskplanning.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/addTeam")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTime(@RequestParam String nomeTime) {
        taskService.adicionarNomeDoTime(nomeTime);
    }

    @PostMapping("/exportJiraImporter")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarJiraImporter() {
        taskService.criarJiraImporter();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAll() {
        return taskService.listarTasks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Task findOne(@PathVariable("id") UUID id) {
        return taskService.obterTask(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@RequestBody Task task) {
         return taskService.criarTask(task);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object update(@PathVariable("id") UUID id, @RequestBody Task task) {
        return taskService.atualizarTask(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id){
         taskService.delete(id);
    }
}
