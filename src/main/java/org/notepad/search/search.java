package org.notepad.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.notepad.Main;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.notepad.Main.*;
import static org.notepad.MainUI.*;
import static org.notepad.search.SearchStart.*;

public class search extends Thread {
    public static boolean stop;
    protected static final Logger log = LogManager.getLogger(search.class);

    @Override
    public void run() {
        int LineCount = 0;
        String LineStr;
        int i = 0;
        int[] selected;
        int LineNumber = 1;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(Main.file));
            String line;

            while ((line = reader.readLine()) != null) {
                LineCount++;
                if (stop) break;
                if (stop) SetInfo.stop = true;
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        selected = new int[LineCount];

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file), StandardCharsets.UTF_8)) {
            while ((LineStr = reader.readLine()) != null) {
                if (LineStr.contains(text)) {
                    log.info("[搜索] 搜索到: line" + LineNumber);
                    selected[i] = LineNumber - 1;
                    list.setSelectedIndices(selected);
                    TimeList.setSelectedIndices(selected);
                    if (stop) break;
                    if (stop) SetInfo.stop = true;
                    i++;
                }

                LineNumber++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SearchButton.setText("\uD83D\uDD0D");
        SearchButton.removeActionListener(SearchStopListener);
        SearchButton.addActionListener(SearchStartListener);

        LineCount = 0;
        LineStr = null;
        i = 0;
        selected = new int[0];
        LineNumber = 1;

        SetInfo.stop = true;
        new SetColor().start();
    }

    static class SetColor extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                list.setSelectionBackground(Color.RED);
                TimeList.setSelectionBackground(Color.RED);
                sleep(500);

                list.setSelectionBackground(Color.WHITE);
                TimeList.setSelectionBackground(Color.WHITE);
                sleep(500);
            }

            list.setSelectionBackground(UiSelectedColor);
            TimeList.setSelectionBackground(UiSelectedColor);
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
