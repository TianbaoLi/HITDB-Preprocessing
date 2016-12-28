package com.company;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by TuringMac on 2016/12/27.
 */
public class JsonReader extends Reader {
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
    }

    public void read() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Gson gson = new Gson();
        TypeAdapter<District> typeAdapter = gson.getAdapter(District.class);
        String jsonString;
        while((jsonString = bufferedReader.readLine()) != null) {
            District district = typeAdapter.fromJson(jsonString);
            System.out.println(district.code + " " + district.sheng+ " " + district.di+ " " + district.xian+ " " + district.name+ " " + district.level);
        }
    }
}
