package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel optionsPanel;
    private JPanel SQLPanel;
    private JPanel plainTextPanel;
    private JPanel excelPanel;
    private JPanel jsonPanel;
    private JPanel XMLPanel;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox1;
    private CardLayout cardLayout;

    public PreProcess() {
        cardLayout = (CardLayout)optionsPanel.getLayout();
        SQLDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card1");
            }
        });
        plainTextDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card2");
            }
        });
        excelDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card3");
            }
        });
        jsonDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card4");
            }
        });
        XMLDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card5");
            }
        });
    }

    public void init() {
        JFrame frame = new JFrame("PreProcess");
        frame.setContentPane(new PreProcess().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);






        frame.pack();
        frame.setVisible(true);
    }

}
