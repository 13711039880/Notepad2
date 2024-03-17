package org.notepad.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.notepad.Main;
import org.notepad.MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.notepad.Main.file;

public class delete {
    protected static final Logger log = LogManager.getLogger(delete.class);
    private static JFrame frame;
    private static Object[] list;
    private JPanel jp;
    private JLabel 确认删除Label;
    private JButton 删除Button;
    private JButton 取消Button;
    private JScrollPane listjsp;
    private JList ThingList;

    public delete() {
        ThingList.setListData(list);

        取消Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        删除Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame DeleteText = text.main("正在删除...", 70, 40);

                for (Object o : list) {
                    int LineCount = 0;

                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;

                        while ((line = reader.readLine()) != null) {
                            LineCount++;

                            if (line.split(",")[0].equals(o)) {
                                log.info("删除事情: " + o);
                                reader.close();
                                List<String> lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
                                if (LineCount >= 1 && LineCount <= lines.size()) {
                                    lines.remove(LineCount - 1);
                                } else {
                                    return;
                                }
                                Path tempFile = Files.createTempFile("temp", ".txt");
                                Files.write(tempFile, lines, StandardCharsets.UTF_8);
                                Files.delete(Paths.get(file));
                                Files.move(tempFile, Paths.get(file));
                                break;
                            }
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                DeleteText.dispose();
                JOptionPane.showMessageDialog(null, "删除成功,需要刷新");
                log.info("开始刷新");
                frame.dispose();
                MainUI.frame.dispose();
                Main.main(Main.parameter);
            }
        });
    }

    public static void main(Object[] list) {
        delete.list = list;

        frame = new JFrame("确定删除");
        frame.setContentPane(new delete().jp);
        frame.setSize(400, 300);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
