package com.denghb.km.keyboard;

import java.util.HashMap;
import java.util.Map;

/**
 * 108 keyboard
 *
 * @author denghb
 * @since 2021/09/09 16:17
 */
public class KeyboardMap {
    private static Map<Integer, String> KEY_MAP = new HashMap<>();

    static {
        KEY_MAP.put(8, "[BackSpace]");
        KEY_MAP.put(9, "[Tab]");
        KEY_MAP.put(12, "[Clear]");
        KEY_MAP.put(13, "[Enter]");
        KEY_MAP.put(16, "[Shift]");
        KEY_MAP.put(17, "[Ctrl]");
        KEY_MAP.put(18, "[Alt]");
        KEY_MAP.put(19, "[Pause]");
        KEY_MAP.put(20, "[Caps Lock]");
        KEY_MAP.put(27, "[Esc]");
        KEY_MAP.put(32, "[ ]");
        KEY_MAP.put(33, "[Page Up]");
        KEY_MAP.put(34, "[Page Down]");
        KEY_MAP.put(35, "[End]");
        KEY_MAP.put(36, "[Home]");
        KEY_MAP.put(37, "[Left Arrow]");
        KEY_MAP.put(38, "[Up Arrow]");
        KEY_MAP.put(39, "[Right Arrow]");
        KEY_MAP.put(40, "[Down Arrow]");
        KEY_MAP.put(44, "[Print Screen]");
        KEY_MAP.put(45, "[Insert]");
        KEY_MAP.put(46, "[Delete]");
        KEY_MAP.put(48, "0)");
        KEY_MAP.put(49, "1!");
        KEY_MAP.put(50, "2@");
        KEY_MAP.put(51, "3#");
        KEY_MAP.put(52, "4$");
        KEY_MAP.put(53, "5%");
        KEY_MAP.put(54, "6^");
        KEY_MAP.put(55, "7&");
        KEY_MAP.put(56, "8*");
        KEY_MAP.put(57, "9(");
        KEY_MAP.put(65, "A");
        KEY_MAP.put(66, "B");
        KEY_MAP.put(67, "C");
        KEY_MAP.put(68, "D");
        KEY_MAP.put(69, "E");
        KEY_MAP.put(70, "F");
        KEY_MAP.put(71, "G");
        KEY_MAP.put(72, "H");
        KEY_MAP.put(73, "I");
        KEY_MAP.put(74, "J");
        KEY_MAP.put(75, "K");
        KEY_MAP.put(76, "L");
        KEY_MAP.put(77, "M");
        KEY_MAP.put(78, "N");
        KEY_MAP.put(79, "O");
        KEY_MAP.put(80, "P");
        KEY_MAP.put(81, "Q");
        KEY_MAP.put(82, "R");
        KEY_MAP.put(83, "S");
        KEY_MAP.put(84, "T");
        KEY_MAP.put(85, "U");
        KEY_MAP.put(86, "V");
        KEY_MAP.put(87, "W");
        KEY_MAP.put(88, "X");
        KEY_MAP.put(89, "Y");
        KEY_MAP.put(90, "Z");
        KEY_MAP.put(91, "[Win]");
        KEY_MAP.put(92, "[Right Win]");
        KEY_MAP.put(93, "[Menu]");
        KEY_MAP.put(96, "[Num 0]");
        KEY_MAP.put(97, "[Num 1]");
        KEY_MAP.put(98, "[Num 2]");
        KEY_MAP.put(99, "[Num 3]");
        KEY_MAP.put(100, "[Num 4]");
        KEY_MAP.put(101, "[Num 5]");
        KEY_MAP.put(102, "[Num 6]");
        KEY_MAP.put(103, "[Num 7]");
        KEY_MAP.put(104, "[Num 8]");
        KEY_MAP.put(105, "[Num 9]");
        KEY_MAP.put(106, "[Num *]");
        KEY_MAP.put(107, "[Num +]");
        KEY_MAP.put(108, "[Num Enter]");
        KEY_MAP.put(109, "[Num -]");
        KEY_MAP.put(110, "[Num .]");
        KEY_MAP.put(111, "[Num /]");
        KEY_MAP.put(112, "[F1]");
        KEY_MAP.put(113, "[F2]");
        KEY_MAP.put(114, "[F3]");
        KEY_MAP.put(115, "[F4]");
        KEY_MAP.put(116, "[F5]");
        KEY_MAP.put(117, "[F6]");
        KEY_MAP.put(118, "[F7]");
        KEY_MAP.put(119, "[F8]");
        KEY_MAP.put(120, "[F9]");
        KEY_MAP.put(121, "[F10]");
        KEY_MAP.put(122, "[F11]");
        KEY_MAP.put(123, "[F12]");
        KEY_MAP.put(144, "[Num Lock]");
        KEY_MAP.put(145, "[Scroll Lock]");
        KEY_MAP.put(160, "[Shift]");
        KEY_MAP.put(161, "[Right Shift]");
        KEY_MAP.put(162, "[Ctrl]");
        KEY_MAP.put(163, "[Right Ctrl]");
        KEY_MAP.put(164, "[Alt]");
        KEY_MAP.put(165, "[Right Alt]");
        KEY_MAP.put(186, ";:");
        KEY_MAP.put(187, "=+");
        KEY_MAP.put(188, ",<");
        KEY_MAP.put(189, "-_");
        KEY_MAP.put(190, ".>");
        KEY_MAP.put(191, "/?");
        KEY_MAP.put(192, "`~");
        KEY_MAP.put(219, "[{");
        KEY_MAP.put(220, "\\|");
        KEY_MAP.put(221, "]}");
        KEY_MAP.put(222, "'\"");

    }

    public static String get(int code, boolean isCapsLock, boolean isShift) {
        String key = KEY_MAP.get(code);
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

    public static void main(String[] args) {
        for (Integer key : KEY_MAP.keySet()) {
            System.out.println(String.format("\"%d\": \"%s\",", key, KEY_MAP.get(key)));
        }
    }
}
