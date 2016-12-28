package com.company;

import org.apache.poi.ss.usermodel.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by TuringMac on 2016/12/28.
 */
public class XlsxReader extends Reader implements Listable {
    private String[] schema;

    public XlsxReader(File file, JTable displayTable) {
        super(file, displayTable);
        this.schema = null;
    }

    public void read() throws Exception {
        Workbook book = getExcelWorkbook();
        Sheet sheet = book.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        Row row = sheet.getRow(0);
        Cell cell;
        int lastCellNum = row.getLastCellNum();
        ArrayList<String> schemaList = new ArrayList<>();
        for(int j = 0; j <= lastCellNum; j++){
            cell = row.getCell(j);
            if(cell != null)
                schemaList.add(cell.getStringCellValue().toString());
        }
        schema = schemaList.toArray(new String[0]);
        for(String s : schema)
            System.out.print(s + "\t");
        System.out.println();
        schemaList(schema);
        ArrayList<Object> attrList = null;
        CellType cellType;
        for(int i = 1; i <= lastRowNum; i++){
            row = sheet.getRow(i);
            if(row != null ){
                lastCellNum = row.getLastCellNum();
                attrList = new ArrayList<>();
                for(int j = 0; j <= lastCellNum; j++){
                    cell = row.getCell(j);
                    if(cell != null) {
                        cellType = cell.getCellTypeEnum();
                        if(cellType == CellType.BOOLEAN)
                            attrList.add(cell.getBooleanCellValue());
                        else if(cellType == CellType.NUMERIC)
                            attrList.add(cell.getNumericCellValue());
                        else if(cellType == CellType.STRING)
                            attrList.add(cell.getStringCellValue());
                        else if(cellType == CellType.FORMULA)
                            attrList.add(cell.getCellFormula());
                    }
                }
            }
            for(Object o : attrList)
                System.out.print(o + "\t");
            System.out.println();
        }
    }

    public Workbook getExcelWorkbook() throws IOException {
        Workbook workbook = null;
        FileInputStream fileInputStream = null;
        try {
            if(!file.exists()){
                throw new RuntimeException("Excel file does not exist!");
            }else{
                fileInputStream = new FileInputStream(file);
                workbook = WorkbookFactory.create(fileInputStream);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            if(fileInputStream != null){
                fileInputStream.close();
            }
        }
        return workbook;
    }

    @Override
    public void schemaList(String[] list) {
        String[][] row = new String[Listable.itemAmount][list.length];
        DefaultTableModel defaultTableModel = new DefaultTableModel(row, list);
        displayTable.setModel(defaultTableModel);
    }

    @Override
    public void tableList() {

    }
}
