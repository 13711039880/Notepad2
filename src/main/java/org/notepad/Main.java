package org.notepad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.notepad.load.load;
import org.util.file.NewFile;
import org.util.properties.ReadPro;
import org.util.properties.SetPro;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    protected static final Logger log = LogManager.getLogger(Main.class);
    public static String[] parameter;
    public static String file;
    public static Color UiBackGroundColor;
    public static Color UiSelectedColor;

    public static void main(String[] parameter) {
        log.info("""
                  
                  _   _           _                                _\s
                 | \\ | |   ___   | |_    ___   _ __     __ _    __| |
                 |  \\| |  / _ \\  | __|  / _ \\ | '_ \\   / _` |  / _` |
                 | |\\  | | (_) | | |_  |  __/ | |_) | | (_| | | (_| |
                 |_| \\_|  \\___/   \\__|  \\___| | .__/   \\__,_|  \\__,_|
                                              |_|
                                              
                """);

        if (!Files.exists(Path.of(".\\thing.dat"))) {
            log.warn("没有 thing.dat ,创建");
            NewFile.NewFile(".\\thing.dat");
        }

        if (!Files.exists(Path.of(".\\set.properties"))) {
            log.warn("没有 set.properties ,创建");
            NewFile.NewFile(".\\set.properties");
            SetPro.SetPro(".\\set.properties", "ui.BackGroundColor", "white", "notepad");
            SetPro.SetPro(".\\set.properties", "ui.SelectedColor", "default", "notepad");
        }

        if (parameter.length == 0) {
            file = ".\\thing.dat";
            log.info("事情文件: .\\thing.dat");
        } else {
            log.info("自定义事情文件: " + parameter[0]);
            file = parameter[0];
        }

        log.info("[设置] 获取设置(.\\set.properties)...");
        long GetSetStartTime = System.currentTimeMillis();
        GetSet();
        log.info("[设置] 完成(" + (System.currentTimeMillis() - GetSetStartTime) + "ms)");

        try {
            log.info("UI样式: " + UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        Main.parameter = parameter;
        load.main();
    }

    private static void GetSet() {
        String UiBackGroundColor = ReadPro.ReadPro(".\\set.properties", "ui.BackGroundColor");
        log.info("[设置] ui.BackGroundColor=" + UiBackGroundColor);
        switch (UiBackGroundColor) {
            case "white" -> Main.UiBackGroundColor = Color.WHITE;
            case "red" -> Main.UiBackGroundColor = Color.RED;
            case "blue" -> Main.UiBackGroundColor = Color.BLUE;
            case "green" -> Main.UiBackGroundColor = Color.GREEN;
            case "yellow" -> Main.UiBackGroundColor = Color.YELLOW;
            default -> {
                Main.UiBackGroundColor = Color.WHITE;
                JOptionPane.showMessageDialog(null, "无效颜色(ui.BackGroundColor)");
            }
        }

        String UiSelectedColor = ReadPro.ReadPro(".\\set.properties", "ui.SelectedColor");
        log.info("[设置] ui.UiSelectedColor=" + UiSelectedColor);
        switch (UiSelectedColor) {
            case "white" -> Main.UiSelectedColor = Color.WHITE;
            case "red" -> Main.UiSelectedColor = Color.RED;
            case "blue" -> Main.UiSelectedColor = Color.BLUE;
            case "green" -> Main.UiSelectedColor = Color.GREEN;
            case "yellow" -> Main.UiSelectedColor = Color.YELLOW;
            case "default" -> Main.UiSelectedColor = new Color(0, 120, 215);
            default -> {
                Main.UiSelectedColor = Color.WHITE;
                JOptionPane.showMessageDialog(null, "无效颜色(ui.SelectedColor)");
            }
        }
    }
}
