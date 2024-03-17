package org.notepad.search;

import javax.swing.*;

public class SearchStart {
    private static JLabel info;
    public static String text;
    public static JList list;
    public static JList TimeList;

    public static void main(String text, JList list, JList TimeList, JLabel info) {
        SearchStart.info = info;
        SearchStart.text = text;
        SearchStart.list = list;
        SearchStart.TimeList = TimeList;

        new SetInfo().start();
        new search().start();
    }

    static class SetInfo extends Thread {
        public static boolean stop = false;

        @Override
        public void run() {
            info.setText("搜索");
            sleep();

            while (true) {
                info.setText("搜索.");
                sleep();
                if (stop) break;

                info.setText("搜索..");
                sleep();
                if (stop) break;

                info.setText("搜索...");
                sleep();
                if (stop) break;
            }

            info.setText("");
        }

        private static void sleep() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
