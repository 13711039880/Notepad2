package org.notepad.load;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.notepad.Main;
import org.notepad.MainUI;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class load {
    protected static final Logger log = LogManager.getLogger(load.class);
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel project;
    private static JLabel schedule;
    public static JLabel animation;
    private JPanel jp;
    private JLabel 项目Label;
    private JLabel 进度Label;
    private JLabel 加载圈Label;

    public load() {
        panel = jp;
        project = 项目Label;
        schedule = 进度Label;
        animation = 加载圈Label;
    }

    public static void main() {
        new load();

        project.setText("/");
        ScheduleSet("/");

        frame = new JFrame("记事本");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new LoadRound().start();
        load();
    }

    private static void load() {
        log.info("[load] loading...");
        long LoadStartTime = System.currentTimeMillis();

        log.info("[load] 获取行数...");
        project.setText("获取行数");
        int LineCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(Main.file));
            String line;

            while ((line = reader.readLine()) != null) {
                LineCount++;
                ScheduleSet(String.valueOf(LineCount));
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[][] data = new String[2][LineCount];
        int datai = 0;
        int timei = 0;

        log.info("[load] 获取内容...");
        project.setText("获取内容");
        ScheduleSet("0/" + LineCount);

        try (Scanner sc = new Scanner(new FileReader(Main.file))) {
            while (sc.hasNextLine()) {
                ScheduleSet(datai + "/" + LineCount);
                if (sc.hasNextLine()) data[0][datai] = sc.nextLine().split(",")[0];
                datai++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        project.setText("获取时间");
        log.info("[load] 获取时间...");
        ScheduleSet("0/" + LineCount);

        try (Scanner sc = new Scanner(new FileReader(Main.file))) {
            while (sc.hasNextLine()) {
                ScheduleSet(timei + "/" + LineCount);
                if (sc.hasNextLine()) data[1][timei] = sc.nextLine().split(",")[1];
                timei++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ScheduleSet("/");
        log.info("[load] 等待主窗口出现...");
        project.setText("等待主窗口出现");
        JFrame MainUIFrame = new JFrame("记事本");
        MainUIFrame.setContentPane(MainUI.main(data));
        MainUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainUIFrame.setSize(600, 400);

        MainUIFrame.setLocationRelativeTo(null);
        MainUIFrame.setVisible(true);

        log.info("[load] 完成(" + (System.currentTimeMillis() - LoadStartTime) + "ms)");
        MainUI.frame = MainUIFrame;
        LoadRound.stop = true;
        frame.dispose();

    }

    private static void ScheduleSet(String str) {
        schedule.setText("第 " + str + " 个");
    }

    static class LoadRound extends Thread {
        public static boolean stop;

        @Override
        public void run() {
            while (!stop) {
                animation.setText("--");
                sleep(1000);

                animation.setText("\\");
                sleep(1000);

                animation.setText("|");
                sleep(1000);

                animation.setText("/");
                sleep(1000);
            }
        }

        private static void sleep(int ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
