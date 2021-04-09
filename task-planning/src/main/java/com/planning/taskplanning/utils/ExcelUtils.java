package com.planning.projectissue.utils;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelUtils {
    private static XSSFWorkbook xssfWorkbook;
    private static XSSFSheet xssfSheet;
    private static FileInputStream fileInputStream;
    private static FileOutputStream fileOutputStream;
    private static XSSFRow linha;
    private static XSSFCell celula;
    private static XSSFCellStyle cellStyle;
    private static XSSFFont font;

    public static void inicializarExcel(String path, String nomePlanilha){
        try {
            fileInputStream = new FileInputStream(path);
            xssfWorkbook =  new XSSFWorkbook(fileInputStream);
            xssfSheet = xssfWorkbook.getSheet(nomePlanilha);
            cellStyle = xssfWorkbook.createCellStyle();
            font = xssfWorkbook.createFont();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void setarEspecificoesDaFonte(){
        font.setFontName("Trebuchet MS");
        font.setFontHeightInPoints((short)9);
        cellStyle.setFont(font);
    }
    public static void criarLinha(int index){
        linha = xssfSheet.createRow(index);
    }
    public static void criarCelula(int index){ celula = linha.createCell(index); }

    public static void escreverNaCelula(String valor){
        colocarBordaNaCelula();
        celula.setCellValue(valor);
        setarEspecificoesDaFonte();
    }
    private static void colocarBordaNaCelula(){
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        celula.setCellStyle(cellStyle);
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
