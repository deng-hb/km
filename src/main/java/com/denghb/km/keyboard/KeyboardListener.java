package com.denghb.km.keyboard;

import com.denghb.km.App;
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
public class KeyboardListener implements NativeKeyListener {

    private boolean isShift = false;
    private boolean isCapsLock = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        int code = nativeKeyEvent.getRawCode();
        if (code == 20) {// Caps Lock
            isCapsLock = !isCapsLock;
        } else if (code == 160 || code == 161) {// Left Shift || Right Shift
            isShift = !isShift;
        }
        String content = KeyboardMap.get(code, isCapsLock, isShift);
        LogUtils.log(Km.KEYBOARD, content);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        int code = nativeKeyEvent.getRawCode();
        if (code == 160 || code == 161) {
            isShift = !isShift;
        }
    }
}
