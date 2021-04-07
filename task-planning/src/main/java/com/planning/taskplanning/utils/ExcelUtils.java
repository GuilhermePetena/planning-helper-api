package com.planning.taskplanning.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtils {
    private static XSSFWorkbook xssfWorkbook;
    private static XSSFSheet xssfSheet;
    private static FileInputStream fileInputStream;
    private static FileOutputStream fileOutputStream;
    private static XSSFRow linha;
    private static XSSFCell celula;

    public static void inicializarExcel(String path, String nomePlanilha){
        try {
            fileInputStream = new FileInputStream(path);
            xssfWorkbook =  new XSSFWorkbook(fileInputStream);
            xssfSheet = xssfWorkbook.getSheet(nomePlanilha);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void criarLinha(int index){
        linha = xssfSheet.createRow(index);
    }
    public static void criarCelula(int index){
        celula = linha.createCell(index);
    }

    public static void escreverNaCelula(String valor){
        celula.setCellValue(valor);
    }


    public static void finalizarExcel(String path){
        try {
            fileOutputStream = new FileOutputStream(path);
            xssfWorkbook.write(fileOutputStream);
            xssfWorkbook.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
