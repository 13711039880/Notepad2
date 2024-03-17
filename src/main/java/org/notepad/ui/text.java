package org.notepad.ui;

import javax.swing.*;

public class text {
    private static String text;
    private JPanel jp;
    private JLabel label;

    private text() {
        label.setText(text);
    }

    public static JFrame main(String text, int SizeW, int SizeH) {
        org.notepad.ui.text.text = text;

        JFrame frame = new JFrame("text");
        frame.setContentPane(new text().jp);
        frame.setSize(SizeW, SizeH);

        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return frame;
    }
}
