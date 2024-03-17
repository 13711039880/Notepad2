package org.notepad.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.notepad.Main;
import org.notepad.MainUI;
import org.util.file.ChangeFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class add {
    protected static final Logger log = LogManager.getLogger(add.class);
    private static JFrame frame;
    private JPanel jp;
    private JLabel 创建事情Label;
    private JTextField 内容TextField;
    private JButton 确定Button;
    private JButton 取消Button;

    public add() {
        取消Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        确定Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = 内容TextField.getText();
                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");
                String time = currentTime.format(formatter);
                ChangeFile.proceed(Main.file, text + "," + time + "\n");
                
                JOptionPane.showMessageDialog(null, "创建成功,需要刷新");
                log.info("开始刷新");
                frame.dispose();
                MainUI.frame.dispose();
                Main.main(Main.parameter);
            }
        });
    }

    public static void main() {
        frame = new JFrame("确定删除");
        frame.setContentPane(new add().jp);
        frame.setSize(400, 300);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
