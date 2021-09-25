package com.denghb.km.utils;

import com.denghb.km.App;
import com.denghb.km.Km;
import com.denghb.km.stat.StatService;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 记录日志
 *
 * @author denghb
 * @since 2021/09/10 09:40
 */
public class LogUtils {

    private static Map<String, Long> COUNT_MAP = new HashMap<>();

    public static String getFilePath(Km km, String day) {
        String filePath = String.format("%slogs%s%s%s.log", App.WORKSPACE, File.separator, km.getCode(), day);
        return filePath;
    }

    public static void log(String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = sdf.format(new Date());
        String day = date.substring(0, 10);

        String filePath = String.format("%slogs%s%s.log", App.WORKSPACE, File.separator, day);
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (FileWriter fileWriter = new FileWriter(file, true)) {
            message = String.format("%s %s\n", date, message);
            System.out.print(message);
            fileWriter.write(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void log(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);

        log(pw.toString());
    }

    public static void log(Km km, String content) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = sdf.format(new Date());
        content = String.format("%s\u0001%s\n", date, content);

        System.out.print(content);

        String day = date.substring(0, 10);
        String filePath = getFilePath(km, day);
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(content);

            Long count = COUNT_MAP.get(filePath);
            if (null == count) {
                count = Files.lines(Paths.get(filePath)).count();
            }
            count++;
            COUNT_MAP.put(filePath, count);

            MessageUtils.showTip(km, count);

        } catch (Exception e) {
            log(e);
        }
    }
}
