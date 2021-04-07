package com.planning.taskplanning.service;


import com.planning.taskplanning.model.Excel;
import com.planning.taskplanning.model.Task;
import com.planning.taskplanning.utils.ExcelUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {
    public void processar(Excel excel, List<Task> list){
        ExcelUtils.inicializarExcel(excel.getCaminho(),"Task");
        escreverNoExcel(list);
        ExcelUtils.finalizarExcel(excel.getCaminho());
    }

    public void escreverNoExcel(List<Task> list){
        int numRow = 1;
        for (Task task: list) {
            ExcelUtils.criarLinha(numRow++);
            int cellNum = 0;
            ExcelUtils.criarCelula(cellNum++);
            ExcelUtils.escreverNaCelula(task.getIssueType());
            ExcelUtils.criarCelula(cellNum++);
            ExcelUtils.escreverNaCelula(task.getTitle());
            ExcelUtils.criarCelula(cellNum++);
            ExcelUtils.escreverNaCelula(task.getDescription());
        }
    }

    public static void main(String[] args) {
        Excel excel = new Excel();
        excel.setCaminho("/Users/gui.petena/downloads/planning.xlsm");
        Task task = new Task("sub-dev","historia para teste","testando pra ver como é");
        Task task1 = new Task("sub-teste","historia para dev","testando pra saber como é");
        List<Task> list = new ArrayList<>();
        list.add(0,task);
        list.add(1,task1);
        new ExcelService().processar(excel, list);
    }
}
