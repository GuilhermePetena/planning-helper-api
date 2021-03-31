package com.task.service;

import com.task.model.Excel;
import com.task.model.Task;
import com.task.utils.ExcelUtils;
import java.util.ArrayList;
import java.util.List;

public class ExcelService {

    private int numRow = 1;

    public void processar(Excel excel, List<Task> list){
        ExcelUtils.inicializarExcel(excel.getPath(),"Task");
        escreverNoExcel(list);
        ExcelUtils.finalizarExcel(excel.getPath());
    }

    public void escreverNoExcel(List<Task> list){
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
        excel.setPath("/Users/gui.petena/downloads/planning.xlsm");
        Task task = new Task("sub-dev","historia para teste","testando pra ver como é");
        Task task1 = new Task("sub-teste","historia para dev","testando pra saber como é");
        List<Task> list = new ArrayList<>();
        list.add(0,task);
        list.add(1,task1);
        new ExcelService().processar(excel, list);
    }

}
