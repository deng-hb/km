package com.denghb.km.keyboard;

import java.util.HashMap;
import java.util.Map;

/**
 * mac keyboard
 *
 * @author denghb
 * @since 2021/09/14 20:49
 */
public class MacKeyboardMap {
    private static Map<Integer, String> DATA = new HashMap<>();

    static {
        DATA.put(18, "1!");
        DATA.put(19, "2@");
        DATA.put(20, "3#");
        DATA.put(21, "4$");
        DATA.put(23, "5%");
        DATA.put(22, "6^");
        DATA.put(28, "8");
        DATA.put(26, "7&");
        DATA.put(25, "9(");
        DATA.put(29, "0)");
        DATA.put(27, "-_");
        DATA.put(24, "=+");
        DATA.put(30, "}]");
        DATA.put(33, "{[");
        DATA.put(35, "P");
        DATA.put(31, "O");
        DATA.put(34, "I");
        DATA.put(32, "U");
        DATA.put(16, "Y");
        DATA.put(17, "T");
        DATA.put(15, "R");
        DATA.put(14, "E");
        DATA.put(13, "W");
        DATA.put(12, "Q");
        DATA.put(0, "A");
        DATA.put(1, "S");
        DATA.put(2, "D");
        DATA.put(3, "F");
        DATA.put(5, "G");
        DATA.put(4, "H");
        DATA.put(38, "J");
        DATA.put(40, "K");
        DATA.put(37, "L");
        DATA.put(41, ";:");
        DATA.put(39, "'\"");
        DATA.put(42, "\\|");
        DATA.put(6, "Z");
        DATA.put(7, "X");
        DATA.put(8, "C");
        DATA.put(9, "V");
        DATA.put(11, "B");
        DATA.put(45, "N");
        DATA.put(46, "M");
        DATA.put(43, ",<");
        DATA.put(47, ".>");
        DATA.put(44, "/?");
        DATA.put(48, "[tab]");
        DATA.put(49, "[ ]");
        DATA.put(50, "`~");
        DATA.put(51, "[delete]");
        DATA.put(54, "[right command]");
        DATA.put(55, "[command]");
        DATA.put(56, "[shift]");
        DATA.put(57, "[caps lock]");
        DATA.put(58, "[option]");
        DATA.put(59, "[control]");
        DATA.put(60, "[right shift]");
        DATA.put(61, "[right option]");
        DATA.put(123, "[left arrow]");
        DATA.put(124, "[right arrow]");
        DATA.put(125, "[down arrow]");
        DATA.put(126, "[up arrow]");
        DATA.put(179, "[fn]");
        DATA.put(36, "[return]");
        DATA.put(122, "[F1]");
        DATA.put(120, "[F2]");
        DATA.put(99, "[F3]");
        DATA.put(118, "[F4]");
        DATA.put(96, "[F5]");
        DATA.put(97, "[F6]");
        DATA.put(98, "[F7]");
        DATA.put(100, "[F8]");
        DATA.put(101, "[F9]");
        DATA.put(109, "[F10]");
        DATA.put(103, "[F11]");
        DATA.put(111, "[F12]");
        DATA.put(53, "[esc]");
        DATA.put(131, "[launchpad]");
        DATA.put(160, "[mission control]");
        DATA.put(242, "[rewind]");
        DATA.put(240, "[play pause]");
        DATA.put(241, "[fast forward]");
        DATA.put(74, "[mute]");
        DATA.put(73, "[volume decrement]");
        DATA.put(72, "[volume increment]");

    }

    public static String get(int code, boolean isCapsLock, boolean isShift) {
        String key = DATA.get(code);
        if (null == key) {
            return String.format("keyboard %d unknown", code);
        }
        if (isShift) {
            if (key.length() == 1) {
                return isCapsLock ? key.toLowerCase() : key.toUpperCase();
            }
            if (key.length() == 2) {
                return key.substring(1, 2);
            }
        } else {
            if (key.length() == 1) {
                return isCapsLock ? key.toUpperCase() : key.toLowerCase();
            }
            if (key.length() == 2) {
                return key.substring(0, 1);
            }
        }
        return key;
    }
}
