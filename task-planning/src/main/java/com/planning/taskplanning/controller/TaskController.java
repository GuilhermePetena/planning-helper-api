package com.planning.taskplanning.controller;

import com.planning.taskplanning.model.Excel;
import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.service.ExcelService;
import com.planning.taskplanning.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
            redirectAttributes.addFlashAttribute("mensagemTask", "Issue cadastrada com sucesso");
            model.addAttribute("issueType", task.getIssueType());
            model.addAttribute("title", task.getTitle());
            model.addAttribute("description", task.getDescription());
            taskService.adicionarTaskNaLista(task);
            return "redirect:/task";
        }else {
            return "task";
        }
    }

    @PostMapping("/addExcel")
    public String adiconaListaNoExcel(@ModelAttribute("excel") Excel excel, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (!bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensagemExcel", "Issues cadastrada no Excel com sucesso");
            model.addAttribute("caminho", excel.getCaminho());
            excelService.processar(excel, TaskService.taskList);
            TaskService.taskList.clear();
            return "redirect:/task-list";
        }
        else{
            return "task-list";
        }


    }

    @GetMapping("/showList")
    public String mostraLista(Model model1, Model model2) {
        Excel excel = new Excel();
        model1.addAttribute("excel", excel);
        model2.addAttribute("tasks", TaskService.taskList);
        return "task-list";
    }
}
