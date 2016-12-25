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
    private JTextField filePath;
    private JButton selectPathButton;
    private JButton savaDataButton;
    private JTable displayTable;
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
    private JTextField SQLIp;
    private JTextField SQLPort;
    private JTextField SQLTable;
    private JTextField textSeperator;
    private CardLayout cardLayout;

    public PreProcess() {
        cardLayout = (CardLayout)optionsPanel.getLayout();
        SQLDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card1");
                selectPathButton.setEnabled(false);
            }
        });
        plainTextDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card2");
                selectPathButton.setEnabled(true);
            }
        });
        excelDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card3");
                selectPathButton.setEnabled(true);
            }
        });
        jsonDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card4");
                selectPathButton.setEnabled(true);
            }
        });
        XMLDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card5");
                selectPathButton.setEnabled(true);
            }
        });
    }

    public void init() {
        JFrame frame = new JFrame("PreProcess");
        frame.setContentPane(new PreProcess().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            System.out.println("Substance Raven Graphite failed to initialize");
        }
        frame.pack();
        frame.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize.width + " " + screenSize.height);
        frame.setSize(new Dimension((int)(screenSize.width * 0.5),(int)(screenSize.height * 0.5)));
    }
}
