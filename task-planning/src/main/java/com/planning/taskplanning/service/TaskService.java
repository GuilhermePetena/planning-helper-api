package com.planning.taskplanning.service;

import com.planning.taskplanning.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskService {

    @Autowired
    private TaskRepository repository;

    private static String nomeDoTime;

    public void adicionarNomeDoTime(String time){
        nomeDoTime = time;
    }

    public List<Task> listarTasks(){
        return repository.findAll();
    }

    public Task obterTask(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido"));
    }
    
    public Object criarTask(Task task){
        if(task.getId() != null){
            return repository.save(task);
        }
        else {
            return new IllegalArgumentException("Objeto inválido");
        }
    }

    public Object atualizarTask(UUID id, Task taskAtualizada){
        Optional<Task> task = repository.findById(id);
        if (!task.isPresent()){
            return new IllegalArgumentException("Objeto inválido");
        } else {
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
            return task;
        }
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
