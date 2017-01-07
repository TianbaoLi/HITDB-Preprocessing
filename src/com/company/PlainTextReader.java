package com.company;

import com.google.gson.internal.ObjectConstructor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.min;

/**
 * Created by TuringMac on 2016/12/27.
 */
public class PlainTextReader extends Reader implements Listable {
    protected char[] separators;
    private String[] schema;

    public PlainTextReader(File file, JTable displayTable, String separator) {
        super(file, displayTable);
        this.separators = separator.toCharArray();
    }

    protected String transRegexSeparator(){
        String reg = "";
        for(int i = 0; i < separators.length; i++) {
            reg += "|";
            if (separators[i] == '.' || separators[i] == '|' || separators[i] == '*' || separators[i] == '+')
                reg += "\\\\";
            else if (separators[i] == '\\'){
                reg += separators[i];
                i++;
            }
            reg += separators[i];
        }
        reg = reg.substring(1, reg.length());
        return reg;
    }

    protected void readContent(BufferedReader bufferedReader, String regex) throws IOException {
        String textString;
        int rowCount = 0;
        while((textString = bufferedReader.readLine()) != null) {
            String[] attributeList = textString.split(regex);
            ArrayList<Object> attrs = new ArrayList<>();
            if(rowCount >= Listable.itemAmount)
                break;
            if(rowCount == 0){
                schema = new String[attributeList.length];
                schemaList(Listable.itemAmount, attributeList.length, schema);
            }
            /*for(String a : attributeList)
                System.out.println(a);*/
            for(String a : attributeList)
                attrs.add(a);
            tableList(rowCount, attrs);
            rowCount++;
        }
    }

    public void read() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String regex = transRegexSeparator();
        readContent(bufferedReader, regex);
    }

    @Override
    public void schemaList(int rowRange, int columnRange, String[] list) {
        int rowCount = min(Listable.itemAmount, rowRange);
        String[][] row = new String[rowCount][columnRange];
        DefaultTableModel defaultTableModel = new DefaultTableModel(row, list);
        displayTable.setModel(defaultTableModel);
    }

    @Override
    public void tableList(int row, ArrayList<Object> list) {
        for(int i = 0; i < list.size(); i++)
            displayTable.setValueAt(list.get(i), row, i);
    }
}
