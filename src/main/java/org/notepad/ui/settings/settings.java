package org.notepad.ui.settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class settings {
    private static JFrame frame;
    private JPanel jp;
    private JScrollPane jsp;
    private JPanel list;
    private JButton UIButton;
    private JLabel 设置Label;

    public settings() {
        UIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI.main();
                frame.dispose();
            }
        });
    }

    public static void main() {
        frame = new JFrame("设置");
        frame.setContentPane(new settings().jp);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
