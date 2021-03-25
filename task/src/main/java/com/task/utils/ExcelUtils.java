package com.task.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet planilha;
    private static FileInputStream fileInputStream;
    private static FileOutputStream fileOutputStream;
    private static Row linha;
    private static Cell celula;

    public static void inicializarExcel(String path, String nomePlanilha){
        try {
            fileInputStream = new FileInputStream(path);
            workbook = WorkbookFactory.create(fileInputStream);

            planilha = workbook.getSheet(nomePlanilha);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void criarLinha(int index){
        linha = planilha.createRow(index);
    }
    public static void criarCelula(int index){
        celula = linha.createCell(index);
    }

    public static void escreverNaCelula(String valor){
        celula.setCellValue(valor);
    }

    public static int obterNumeroDeLinhas(){
        return linha.getRowNum();
    }

    public static void finalizarExcel(String path){
        try {
            fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
