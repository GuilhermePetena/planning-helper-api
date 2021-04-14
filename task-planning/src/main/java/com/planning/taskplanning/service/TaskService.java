package com.planning.taskplanning.service;

import com.planning.taskplanning.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskService {

    public static List<Task> taskList = new ArrayList<>();

    public List<Task> addList(Task task){
        taskList.add(task);
        return taskList;
    }

    public List<Task> addListWithPosition(Task task, int index){
        taskList.add(index, task);
        return taskList;
    }
    public List<Task> getAll(){
        return taskList;
    }
    public Task getTaskByIndex(int index){
        return taskList.get(index);
    }
    public Task getTaskByObject(Task task){
        return taskList.get(taskList.indexOf(task));
    }

    public Task remove(int index){
        return taskList.remove(index);
    }
}
