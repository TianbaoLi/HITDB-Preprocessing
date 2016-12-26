package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
    private ButtonGroup dataSourceButtonGroup;
    private CardLayout cardLayout;
    private int selected;

    public PreProcess() {
        cardLayout = (CardLayout)optionsPanel.getLayout();
        SQLDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card1");
                selectPathButton.setEnabled(false);
                selected = 0;
            }
        });
        plainTextDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card2");
                selectPathButton.setEnabled(true);
                selected = 1;
            }
        });
        excelDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card3");
                selectPathButton.setEnabled(true);
                selected = 2;
            }
        });
        jsonDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card4");
                selectPathButton.setEnabled(true);
                selected = 3;
            }
        });
        XMLDataRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(optionsPanel, "Card5");
                selectPathButton.setEnabled(true);
                selected = 4;
            }
        });
        selectPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser=new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jFileChooser.setCurrentDirectory(new File("."));
                jFileChooser.showDialog(new JLabel(), "Choose data source file");
                File file = jFileChooser.getSelectedFile();
                if(file != null)
                    filePath.setText(file.getAbsolutePath());
            }
        });
        savaDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(selected){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        File xmlFile = new File(filePath.getText());
                        XMLReader xmlReader = new XMLReader(xmlFile, displayTable);
                        try {
                            xmlReader.read();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                }
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
        frame.setSize(new Dimension((int)(screenSize.width * 0.5),(int)(screenSize.height * 0.5)));
    }
}
