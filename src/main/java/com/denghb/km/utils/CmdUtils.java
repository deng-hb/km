package com.denghb.km.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * TODO description
 *
 * @author denghb
 * @since 2021/09/13 11:47
 */
public class CmdUtils {

    public static int findPid(int port) {
        //查找进程号
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("cmd /c netstat -ano | findstr \"" + port + "\"");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains(String.format("0.0.0.0:%d ", port))) {
                    int offset = line.lastIndexOf(" ");
                    String pid = line.substring(offset + 1);
                    return Integer.parseInt(pid);
                }
            }
        } catch (IOException e) {
            LogUtils.log(e);
        }

        return -1;
    }

    public static void killPid(int pid) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("taskkill /F /pid " + pid + "");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            LogUtils.log(e);
        }
    }

    public static void killWithPort(int port) {
        int pid = findPid(port);
        if (-1 != pid) {
            killPid(pid);
        }
    }
}
