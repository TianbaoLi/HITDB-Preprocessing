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

class District{
    private int code;
    private int sheng;
    private int di;
    private int xian;
    private String name;
    private int level;
    private static int fieldNum = 6;
    private static String[] schema = {"code", "sheng", "di", "xian", "name", "level"};

    public int getCode() {
        return code;
    }

    public int getSheng() {
        return sheng;
    }

    public int getDi() {
        return di;
    }

    public int getXian() {
        return xian;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public static int getFieldNum() {
        return fieldNum;
    }

    public static String[] getSchema() {
        return schema;
    }

}

public class JsonReader extends Reader implements Listable {
    public JsonReader(File file, JTable displayTable) {
        super(file, displayTable);
    }

    public void read() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Gson gson = new Gson();
        TypeAdapter<District> typeAdapter = gson.getAdapter(District.class);
        String jsonString;
        schemaList(Listable.itemAmount, District.getFieldNum(), District.getSchema());
        ArrayList<Object> attrList;
        int rowCount = 0;
        while((jsonString = bufferedReader.readLine()) != null) {
            if(rowCount >= Listable.itemAmount)
                break;
            District district = typeAdapter.fromJson(jsonString);
            //System.out.println(district.getCode() + " " + district.getSheng()+ " " + district.getDi()+ " " + district.getXian()+ " " + district.getName()+ " " + district.getLevel());
            attrList = new ArrayList<>();
            attrList.add(district.getCode());
            attrList.add(district.getSheng());
            attrList.add(district.getDi());
            attrList.add(district.getXian());
            attrList.add(district.getName());
            attrList.add(district.getLevel());
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
