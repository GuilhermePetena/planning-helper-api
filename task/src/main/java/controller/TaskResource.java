package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Excel;
import model.Task;

@RestController
@RequestMapping("/task")
public class TaskResource {
	
	public static String path = "";
	
	@PostMapping("/path")
	public ResponseEntity<Excel> setaPath(@RequestBody Excel excel){
		
		path = excel.getPath();
		
		return ResponseEntity.status(HttpStatus.OK).body(excel);
	}
	
	@PostMapping
	public ResponseEntity<Task> criaTask(@RequestBody Task task){
		
//		taskService.metodo(task, path);
		
		System.out.println(path);
		
		return ResponseEntity.status(HttpStatus.OK).body(task);
	}

}
