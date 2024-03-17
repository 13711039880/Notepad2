package org.notepad.ui.settings;

import org.notepad.Main;
import org.notepad.MainUI;
import org.util.properties.ReadPro;
import org.util.properties.SetPro;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static org.notepad.Main.parameter;

public class UI {
    private JPanel jp;
    private JLabel UI设置Label;
    private JScrollPane jsp;
    private JPanel list;
    private JTextField 背景颜色TextField;
    private JLabel 背景颜色Label;
    private JLabel 选中颜色Label;
    private JTextField 选中颜色TextField;

    public UI() {
        背景颜色TextField.setText(ReadPro.ReadPro(".\\set.properties", "ui.BackGroundColor"));
        选中颜色TextField.setText(ReadPro.ReadPro(".\\set.properties", "ui.SelectedColor"));

        背景颜色TextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SetPro.SetPro(".\\set.properties", "ui.BackGroundColor", 背景颜色TextField.getText(), "notepad");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SetPro.SetPro(".\\set.properties", "ui.BackGroundColor", 背景颜色TextField.getText(), "notepad");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SetPro.SetPro(".\\set.properties", "ui.BackGroundColor", 背景颜色TextField.getText(), "notepad");
            }
        });

        选中颜色TextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SetPro.SetPro(".\\set.properties", "ui.SelectedColor", 选中颜色TextField.getText(), "notepad");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SetPro.SetPro(".\\set.properties", "ui.SelectedColor", 选中颜色TextField.getText(), "notepad");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SetPro.SetPro(".\\set.properties", "ui.SelectedColor", 选中颜色TextField.getText(), "notepad");
            }
        });
    }

    public static void main() {
        JFrame frame = new JFrame("UI设置");
        frame.setContentPane(new UI().jp);
        frame.setSize(400, 300);


        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "需要刷新");
                MainUI.frame.dispose();
                Main.main(parameter);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }

        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
