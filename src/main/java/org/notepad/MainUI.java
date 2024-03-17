package org.notepad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.notepad.search.SearchStart;
import org.notepad.search.search;
import org.notepad.ui.add;
import org.notepad.ui.delete;
import org.notepad.ui.settings.settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.notepad.Main.*;

public class MainUI {
    protected static final Logger log = LogManager.getLogger(Main.class);
    public static JButton SearchButton;
    public static JFrame frame;
    private static String[][] data;
    private static JPanel panel;
    public static ActionListener SearchStopListener;
    public static ActionListener SearchStartListener;
    private JPanel jp;
    private JList list;
    private JScrollPane listjsp;
    private JButton 删除Button;
    private JButton 添加Button;
    private JTextField 搜索TextField;
    private JPanel listjp;
    private JList TimeList;
    private JLabel 内容Label;
    private JLabel 时间Label;
    private JButton 搜索Button;
    private JButton 设置Button;
    private JLabel info;

    public MainUI() {
        panel = jp;
        SearchButton = 搜索Button;
        jp.setBackground(UiBackGroundColor);
        list.setSelectionBackground(UiSelectedColor);
        TimeList.setSelectionBackground(UiSelectedColor);
        list.setListData(data[0]);
        TimeList.setListData(data[1]);

        删除Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] DeleteList = list.getSelectedValues();
                delete.main(DeleteList);
            }
        });

        添加Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add.main();
            }
        });

        SearchStopListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search.stop = true;
                log.info("[搜索] 搜索被终止");
            }
        };

        SearchStartListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchStart.main(搜索TextField.getText(), list, TimeList, info);
                搜索Button.setText("停止搜索");
                搜索Button.removeActionListener(this);
                搜索Button.addActionListener(SearchStopListener);
            }
        };

        搜索Button.addActionListener(SearchStartListener);

        设置Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.main();
            }
        });
    }

    public static JPanel main(String[][] data) {
        MainUI.data = data;
        new MainUI();
        return panel;
    }
}