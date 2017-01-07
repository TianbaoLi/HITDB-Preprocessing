package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import static java.lang.Math.min;

/**
 * Created by TuringMac on 2016/12/27.
 */
public class CsvReader extends PlainTextReader implements Listable {
    private String[] schema;

    public CsvReader(File file, JTable displayTable, String separator) {
        super(file, displayTable, separator);
        this.schema = null;
    }

    public void read() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String textString;
        String regex = transRegexSeparator();
        textString = bufferedReader.readLine();
        schema = textString.split(regex);
        /*for(String s : schema)
            System.out.print(s + "\t");*/
        schemaList(Listable.itemAmount, schema.length, schema);
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
