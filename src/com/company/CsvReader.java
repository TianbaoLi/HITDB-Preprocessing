package com.company;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by TuringMac on 2016/12/27.
 */
public class CsvReader extends PlainTextReader {
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
        for(String s : schema)
            System.out.print(s + "\t");
        readContent(bufferedReader, regex);
    }
}
