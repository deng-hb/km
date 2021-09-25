package com.denghb.km;

import com.denghb.km.http.WebHttpHandler;
import com.denghb.km.http.StatHttpHandler;
import com.denghb.km.keyboard.KeyboardListener;
import com.denghb.km.keyboard.MacKeyboardListener;
import com.denghb.km.mouse.MouseListener;
import com.denghb.km.utils.CmdUtils;
import com.denghb.km.utils.LogUtils;
import com.sun.net.httpserver.HttpServer;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {
    public static String OS_NAME = System.getProperty("os.name");
    public static boolean WIN = OS_NAME.startsWith("Win");
    public static boolean MAC = OS_NAME.startsWith("Mac");
    public static String WORKSPACE = String.format("%s%s.km%s", System.getProperty("user.home"), File.separator, File.separator);
    public static int PORT = 9999;

    public static void main(String[] args) {
        LogUtils.log(WORKSPACE);

        try {
            if (WIN) {
                CmdUtils.killWithPort(PORT);
            }

            enableKMListener();
            enableHttpServer();
        } catch (Exception e) {
            LogUtils.log(e);
            System.exit(1);
        }
    }

    private static void enableKMListener() throws NativeHookException {

        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeMouseListener(new MouseListener());
        if (WIN) {
            GlobalScreen.addNativeKeyListener(new KeyboardListener());
        } else if (MAC) {
            GlobalScreen.addNativeKeyListener(new MacKeyboardListener());
        }
    }

    private static void enableHttpServer() throws IOException {

        LogUtils.log(String.format("http://localhost:%d", PORT));

        // http server
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.setExecutor(Executors.newCachedThreadPool());
        server.createContext("/km", new StatHttpHandler());
        server.createContext("/", new WebHttpHandler());
        server.start();

    }
}
