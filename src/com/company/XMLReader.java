package com.company;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TuringMac on 2016/12/26.
 */
public class XMLReader {
    private File file;
    private JTable displayTable;

    public XMLReader(File file, JTable displayTable) {
        this.file = file;
        this.displayTable = displayTable;
    }

    public void read() throws Exception{
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element root = document.getRootElement();
        listNodes(root);
    }

    public void listNodes(Element node){
        System.out.println("当前节点的名称：" + node.getName());
        List<Attribute> list = node.attributes();
        for(Attribute attribute : list){
            System.out.println("属性"+attribute.getName() +":" + attribute.getValue());
        }
        if(!(node.getTextTrim().equals(""))){
            System.out.println( node.getName() + "：" + node.getText());
        }
        Iterator<Element> iterator = node.elementIterator();
        while(iterator.hasNext()){
            Element e = iterator.next();
            listNodes(e);
        }
    }
}
