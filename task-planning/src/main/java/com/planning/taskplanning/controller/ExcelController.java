package com.planning.projectissue.controller;

import com.planning.projectissue.model.Excel;
import com.planning.projectissue.model.Task;
import com.planning.projectissue.service.ExcelService;
import com.planning.projectissue.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(Excel excel) {
         excelService.processar(excel, TaskService.taskList);
    }
}
