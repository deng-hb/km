package com.denghb.km.mouse;

import com.denghb.km.Km;
import com.denghb.km.utils.LogUtils;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

/**
 * 鼠标监听
 *
 * @author denghb
 * @since 2021/09/10 09:38
 */
public class MouseListener implements NativeMouseListener {

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        String content = nativeMouseEvent.getButton() + ";" + nativeMouseEvent.getX() + ":" + nativeMouseEvent.getY();
        LogUtils.log(Km.MOUSE, content);
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {

    }
}
