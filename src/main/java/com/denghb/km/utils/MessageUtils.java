package com.denghb.km.utils;

import com.denghb.km.Km;

import java.awt.*;

/**
 * 通知服务
 *
 * @author denghb
 * @since 2021/09/10 09:41
 */
public class MessageUtils {

    public static void showTip(Km km, long count) {
        if (km == Km.KEYBOARD) {
            if (count > 0 && count % 1000 == 0) {
                tray("您今天已经敲击键盘" + count + "次了。\n请注意休息！😀");
            }
        } else if (km == Km.MOUSE) {

            if (count > 0 && count % 500 == 0) {
                tray("您今天已经点击鼠标" + count + "次了。\n请注意休息！😀");
            }
        }

    }

    private static void tray(String message) {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            try {
                Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

                TrayIcon trayIcon = new TrayIcon(image);
                trayIcon.setImageAutoSize(true);
                tray.add(trayIcon);
                trayIcon.displayMessage("温馨提示", message, TrayIcon.MessageType.INFO);
            } catch (AWTException e) {
                e.printStackTrace();
            }


        }
    }
}
