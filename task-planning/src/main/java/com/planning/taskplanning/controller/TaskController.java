package com.planning.taskplanning.controller;

import com.planning.projectissue.model.Task;
import com.planning.projectissue.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    public static File file;

    public ResponseEntity addTeam(Optional<String> nomeTime) {
            taskService.adicionarNomeDoTime(nomeTime.get());
            return ResponseEntity.ok().build();
    }

    public ResponseEntity createJiraImporter() throws FileNotFoundException {
        return downloadTextFile(true);
    }

    public ResponseEntity createPlanningPokerTxt() throws FileNotFoundException {
        return downloadTextFile(false);
    }

    private String createFile(boolean jira) {
        String fileName;
        if (jira){
            file = taskService.criarJiraImporter();
            fileName = "jiraImporter.txt";
        }
        else {
            file = taskService.criarPlanningPoker();
            fileName = "planningPoker.txt";
        }
        return fileName;
    }

    public ResponseEntity<InputStreamResource> downloadTextFile(boolean jira) throws FileNotFoundException {
        String fileName = createFile(jira);

        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(file.length())
                .body(inputStreamResource);
    }

    @GetMapping()
    public ResponseEntity<List<?>> getAll(@RequestParam(required = false)  boolean exportJiraImporter,
                                              @RequestParam(required = false) boolean exportPlanningPoker, @RequestParam(required = false) Optional<String> nomeTime) throws FileNotFoundException {
        if(exportJiraImporter){
            try { return createJiraImporter(); }
            finally { file.delete(); }
        }if(exportPlanningPoker){
            try { return createPlanningPokerTxt();}
            finally { file.delete(); }
        }if(nomeTime.isPresent()){
            return addTeam(nomeTime);
        }
        return ResponseEntity.ok(taskService.listarTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findOne(@PathVariable("id") UUID id) {
        if (taskService.taskExiste(id)) {
            return ResponseEntity.ok(taskService.obterTask(id).get());
        } else {
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
