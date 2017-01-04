package com.company;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.ObjectConstructor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import static java.lang.Math.min;

/**
 * Created by TuringMac on 2016/12/27.
 */
public class JsonReader extends Reader implements Listable {
    public JsonReader(File file, JTable displayTable) {
        super(file, displayTable);
    }

    class District{
        private int code;
        private int sheng;
        private int di;
        private int xian;
        private String name;
        private int level;
        private int fieldNum = 6;
        private String[] schema = {"code", "sheng", "di", "xian", "name", "level"};
    }

    public void read() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Gson gson = new Gson();
        TypeAdapter<District> typeAdapter = gson.getAdapter(District.class);
        String jsonString;
        boolean schemaFound = false;
        ArrayList<Object> attrList;
        int rowCount = 0;
        while((jsonString = bufferedReader.readLine()) != null) {
            if(rowCount > Listable.itemAmount)
                break;
            District district = typeAdapter.fromJson(jsonString);
            System.out.println(district.code + " " + district.sheng+ " " + district.di+ " " + district.xian+ " " + district.name+ " " + district.level);
            attrList = new ArrayList<>();
            attrList.add(district.code);
            attrList.add(district.sheng);
            attrList.add(district.di);
            attrList.add(district.xian);
            attrList.add(district.name);
            attrList.add(district.level);
            if(schemaFound == false){
                schemaFound = true;
                schemaList(Listable.itemAmount, district.fieldNum, district.schema);
            }
            tableList(rowCount, attrList);
            rowCount++;
        }
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
