package com.planning.projectissue.controller;

import com.planning.projectissue.model.Task;
import com.planning.projectissue.service.ExcelService;
import com.planning.projectissue.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ExcelService excelService;
    @Autowired
    private TaskService taskService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{index}")
    @ResponseStatus(HttpStatus.OK)
    public Task findOne(@PathVariable("index") int index) {
        return taskService.getTaskByIndex(index);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
         taskService.addList(task);
         return taskService.getTaskByObject(task);
    }

    @PutMapping("/{index}")
    @ResponseStatus(HttpStatus.OK)
    public Task update(@PathVariable("index") int index, @RequestBody Task task) {
        taskService.remove(index);
        taskService.addListWithPosition(task, index);
        return taskService.getTaskByIndex(index);
    }

    @DeleteMapping("/{index}")
    @ResponseStatus(HttpStatus.OK)
    public Task delete(@PathVariable("index") int index){
        return taskService.remove(index);
    }
}
