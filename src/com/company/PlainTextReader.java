package com.company;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by TuringMac on 2016/12/27.
 */
public class PlainTextReader {
    protected File file;
    protected JTable displayTable;
    protected char[] separators;

    public PlainTextReader(File file, JTable displayTable, String separator) {
        this.file = file;
        this.displayTable = displayTable;
        this.separators = separator.toCharArray();
    }

    protected String transRegexSeparator(){
        String reg = "";
        for(char c : separators) {
            reg += "|";
            if (c == '.' || c == '|' || c == '*' || c == '+')
                reg += "\\\\";
            else if (c == '\\')
                reg += "\\\\";
            reg += c;
        }
        reg = reg.substring(1, reg.length());
        return reg;
    }

    protected void readContent(BufferedReader bufferedReader, String regex) throws IOException {
        String textString;
        while((textString = bufferedReader.readLine()) != null) {
            String[] attributeList = textString.split(regex);
            for(String a : attributeList)
                System.out.print(a + "\t");
            System.out.println();
        }
    }

    public void read() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String regex = transRegexSeparator();
        readContent(bufferedReader, regex);
    }
}
