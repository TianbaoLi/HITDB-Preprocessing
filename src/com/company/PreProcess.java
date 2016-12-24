package com.company;

import javax.swing.*;

/**
 * Created by TuringMac on 2016/12/23.
 */
public class PreProcess {
    private JPanel Panel;
    private JTextField textField1;
    private JButton selectPathButton;
    private JButton savaDataButton;
    private JTable table1;
    private JRadioButton SQLDataRadioButton;
    private JRadioButton plainTextDataRadioButton;
    private JRadioButton excelDataRadioButton;
    private JRadioButton jsonDataRadioButton;
    private JRadioButton XMLDataRadioButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("PreProcess");
        frame.setContentPane(new PreProcess().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
