package com.planning.taskplanning.service;

import com.planning.taskplanning.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    private static String nomeDoTime;

    public boolean taskExiste(UUID id){
        return repository.existsById(id);
    }

    public void adicionarNomeDoTime(String time){
        nomeDoTime = time;
    }

    public List<Task> listarTasks(){
        return repository.findAll();
    }

    public Optional<Task> obterTask(UUID id){
        return repository.findById(id);
    }
    
    public Task criarTask(Task task){
        return repository.save(task);
    }

        public Optional<Task> atualizarTask(UUID id, Task taskAtualizada){
            Optional<Task> task = repository.findById(id);
            task.get().setComplexityPoints(taskAtualizada.getComplexityPoints());
            task.get().setComponents(taskAtualizada.getComponents());
            task.get().setDescription(taskAtualizada.getDescription());
            task.get().setDueDate(taskAtualizada.getDueDate());
            task.get().setIssueId(taskAtualizada.getIssueId());
            task.get().setEpicLink(taskAtualizada.getEpicLink());
            task.get().setFixVersions(taskAtualizada.getFixVersions());
            task.get().setJiraKey(taskAtualizada.getJiraKey());
            task.get().setHours(taskAtualizada.getHours());
            task.get().setPriority(taskAtualizada.getPriority());
            task.get().setLabels(taskAtualizada.getLabels());
            task.get().setOriginalEstimate(taskAtualizada.getOriginalEstimate());
            task.get().setTeam(taskAtualizada.getTeam());
            task.get().setSummary(taskAtualizada.getSummary());
            criarTask(task.get());
            return task;
        }

    public void delete(UUID id){
        repository.deleteById(id);
    }

    public void criarJiraImporter() {
        int contador = 0;
        List<Task> list = repository.findAll();
        for (Task task: list) {
            task.setTeam(nomeDoTime);
            task.setIssueId(contador++);
            task.setOriginalEstimate(task.getHours()*3600);
        }
        new CsvUtils().escreverCsv(list);
    }
}
