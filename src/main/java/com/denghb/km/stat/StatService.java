package com.denghb.km.stat;

import com.denghb.km.Km;
import com.denghb.km.utils.LogUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 分析
 *
 * @author denghb
 * @since 2021/09/10 15:17
 */
public class StatService {

    /**
     * 统计
     *
     * @param km
     * @param req
     * @return
     */
    public static Map<String, Object> stat(Km km, StatReqModel req) {
        Map<String, Object> result = new HashMap<>();
        result.put("total", 0);

        String day = req.getDay();

        // 只有最近xxx秒和指定日期时间返回
        Date start = null, end = new Date();
        if (null != req.getLastTime()) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, -req.getLastTime());
            start = calendar.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            day = sdf.format(new Date());
        } else {
            start = format(String.format("%s %s:00.000", day, req.getTimeStart()));
            end = format(String.format("%s %s:00.000", day, req.getTimeEnd()));
        }
        String filePath = LogUtils.getFilePath(km, day);
        File file = new File(filePath);
        if (!file.exists()) {
            return result;
        }
        List<String> strings = readList(file, start, end);
        result.put("total", new DecimalFormat("#,###").format(strings.size()));
        Map<String, Integer> stat = new HashMap<>();

        if (Km.KEYBOARD == km) {
            strings.forEach(item -> {
                String key = item.substring(24);
                int count = stat.getOrDefault(key, 0);
                stat.put(key, ++count);
            });
        } else if (Km.MOUSE == km) {
            strings.forEach(item -> {
                String key = item.substring(24, 25);
                switch (key) {
                    case "1":
                        key = "left";
                        break;
                    case "2":
                        key = "right";
                        break;
                    case "3":
                        key = "mid";
                        break;
                }
                int count = stat.getOrDefault(key, 0);
                stat.put(key, ++count);
            });
        }

        result.put("stat", stat);
        return result;
    }

    /**
     * 读取列表
     *
     * @param file
     * @param start
     * @param end
     * @return
     */
    private static List<String> readList(File file, Date start, Date end) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String d = line.substring(0, 23);
                Date time = format(d);
                if (time.getTime() > start.getTime() && time.getTime() < end.getTime()) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static Date format(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
