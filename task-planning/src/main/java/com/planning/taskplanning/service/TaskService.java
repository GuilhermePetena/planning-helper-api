package com.planning.taskplanning.service;

import com.planning.taskplanning.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    public static List<Task> taskList = new ArrayList<>();

    public List<Task> adicionarTaskNaLista(Task task){
        taskList.add(task);
        return taskList;
    }
}
