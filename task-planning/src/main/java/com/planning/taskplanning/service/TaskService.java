package com.planning.taskplanning.service;

import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.repository.TaskRepository;
import com.planning.taskplanning.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private StoryService storyService;

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
    
    public List<Task> listarTasksByJiraKey(String jiraKey){
        return repository.findAllByJiraKey_JiraKey(jiraKey);
    }

    public Optional<Task> obterTask(UUID id){
        return repository.findById(id);
    }
    
    public Task criarTask(Task task){
        Story story = storyService.obterStory(task.getJiraKey().getJiraKey());
        task.setJiraKey(story);
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
            task.get().setIssueType(taskAtualizada.getIssueType());
            criarTask(task.get());
            return task;
        }

    public void delete(UUID id){
        repository.deleteById(id);
    }

    @Transactional
    public void deleteAll(String jiraKey){
        repository.deleteAllByJiraKey_JiraKey(jiraKey);
    }

    public File criarJiraImporter() {
        int contador = 1;
        List<Task> list = repository.findAll();
        for (Task task: list) {
            task.setTeam(nomeDoTime);
            task.setIssueId(contador++);
            task.setOriginalEstimate(task.getHours()*3600);
        }
       return new CsvUtils().escreverJiraImporter(list);
    }

    public File criarPlanningPoker(){
        return new CsvUtils().escreverPlanningPokerTxt(repository.findAll());
    }
}
