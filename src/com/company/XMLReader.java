package com.company;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.min;

/**
 * Created by TuringMac on 2016/12/26.
 */
public class XMLReader extends Reader implements Listable {
    private String[] schema = null;

    public XMLReader(File file, JTable displayTable) {
        super(file, displayTable);
    }

    public void read() throws Exception{
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        Iterator<Element> iterator = root.elementIterator();
        int rowCount = 0;
        while(iterator.hasNext()){
            if(rowCount >= Listable.itemAmount)
                break;
            Element e = iterator.next();
            readRow(e, rowCount);
            rowCount++;
        }
    }

    public void readRow(Element node, int rowCount){
        Iterator<Element> iterator = node.elementIterator();
        if(schema == null){
            int columnCount = 0;
            while(iterator.hasNext()){
                columnCount++;
                iterator.next();
            }
            schema = new String[columnCount];
            iterator = node.elementIterator();
            columnCount = 0;
            while(iterator.hasNext()){
                schema[columnCount] = iterator.next().getName();
                columnCount++;
            }
            schemaList(Listable.itemAmount, columnCount, schema);
            iterator = node.elementIterator();
        }
        ArrayList<Object> attrs = new ArrayList<>();
        while(iterator.hasNext())
            attrs.add(iterator.next().getText());
        tableList(rowCount, attrs);
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
