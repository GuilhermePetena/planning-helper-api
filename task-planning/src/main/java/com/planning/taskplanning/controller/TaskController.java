package com.planning.projectissue.controller;

import com.planning.projectissue.model.Excel;
import com.planning.projectissue.model.Task;
import com.planning.projectissue.service.ExcelService;
import com.planning.projectissue.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskController {

    @Autowired
    private ExcelService excelService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/task")
    public String mostraFormulario(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "task";
    }

    @PostMapping("/task")
    public String adicionaTaskNaLista(@ModelAttribute("task") Task task, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensagemTask", "Task cadastrada com sucesso");
            model.addAttribute("issueType", task.getIssueType());
            model.addAttribute("title", task.getTitle());
            model.addAttribute("description", task.getDescription());
            taskService.adicionarTaskNaLista(task);
            return "redirect:/task";
        }else {
            return "task";
        }
    }

    @PostMapping("/task-list")
    public String adiconaListaNoExcel(@ModelAttribute("excel") Excel excel, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (!bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensagemExcel", "Tasks cadastrada no Excel com sucesso");
            model.addAttribute("caminho", excel.getCaminho());
            excelService.processar(excel, TaskService.taskList);
            return "redirect:/task-list";
        }
        else{
            return "task-list";
        }
    }

    @GetMapping("/task-list")
    public String mostraLista(Model model1, Model model2) {
        Excel excel = new Excel();
        model1.addAttribute("excel", excel);
        model2.addAttribute("tasks", TaskService.taskList);
        return "task-list";
    }
    @GetMapping("/task-edit/{index}")
    public String mostraFormulario(@PathVariable("index") int index, Model model) {
        TaskService.taskList.get(index);
        model.addAttribute("task", TaskService.taskList.get(index));
        return "task-edit";
    }

    @PutMapping("/task-edit")
    public String alterarTask(Task task, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (!bindingResult.hasErrors()) {
            TaskService.taskList.remove(TaskService.taskList.indexOf(task));
            TaskService.taskList.add(task);
            redirectAttributes.addFlashAttribute("mensagemTaskedit", "Task atualizada com sucesso");
            return "redirect:/task-list";
        }
        return "task-edit";
    }
}
