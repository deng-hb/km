package com.denghb.km.keyboard;

import com.denghb.km.Km;
import com.denghb.km.utils.LogUtils;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 键盘监听器
 *
 * @author denghb
 * @since 2021/09/10 10:30
 */
public class MacKeyboardListener implements NativeKeyListener {

    private boolean isShift = false;
    private boolean isCapsLock;
    private boolean flag = false;

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        int code = nativeKeyEvent.getRawCode();
        // Caps Lock
        isCapsLock = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
        if (code == 56 || code == 60) {// Left Shift || Right Shift
            isShift = !isShift;
        }
        String content = MacKeyboardMap.get(code, isCapsLock, isShift);
        LogUtils.log(Km.KEYBOARD, content);

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        int code = nativeKeyEvent.getRawCode();
        if (code == 56 || code == 60) {
            isShift = !isShift;
        }
        if (!flag) {
            switch (code) {
                case 57:
                case 74:
                case 73:
                case 72:
                case 242:
                case 241:
                case 240: {
                    String content = MacKeyboardMap.get(code, isCapsLock, isShift);
                    LogUtils.log(Km.KEYBOARD, content);
                    break;
                }
            }
        }
        flag = !flag;
    }
}
