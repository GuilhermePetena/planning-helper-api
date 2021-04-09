package com.planning.projectissue.service;

import com.planning.projectissue.model.Excel;
import com.planning.projectissue.model.Task;
import com.planning.projectissue.utils.ExcelUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {
    public void processar(Excel excel, List<Task> list){
        ExcelUtils.inicializarExcel(excel.getCaminho(),"ProductBacklog");
        escreverNoExcel(list);
        ExcelUtils.finalizarExcel(excel.getCaminho());
    }

    public void escreverNoExcel(List<Task> list){
        int numRow = 4;
        for (Task task: list) {
            ExcelUtils.criarLinha(numRow++);
            int cellNum = 0;
            ExcelUtils.criarCelula(cellNum++);
            ExcelUtils.escreverNaCelula(task.getIssueType());
            ExcelUtils.criarCelula(cellNum++);
            ExcelUtils.escreverNaCelula(task.getTitle());
            ExcelUtils.criarCelula(cellNum++);
            ExcelUtils.escreverNaCelula(task.getDescription());
            criarCelulas(cellNum);
        }
    }
    private void criarCelulas(int cellNum) {
        for (int i = 0; i < 12; i++) {
            ExcelUtils.criarCelula(cellNum++);
            ExcelUtils.escreverNaCelula(null);
        }
    }

    public static void main(String[] args) {
        Excel excel = new Excel();
        excel.setCaminho("C:\\Users\\petenag\\Downloads\\issue.xlsm");
        Task task = new Task("Story","historia para teste","testando pra ver como é");
        Task task1 = new Task("sub-teste","Story","Story");
        List<Task> list = new ArrayList<>();
        list.add(0,task);
        list.add(1,task1);
        new ExcelService().processar(excel, list);
    }
}
