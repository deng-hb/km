package com.denghb.km;

/**
 * TODO description
 *
 * @author denghb
 * @since 2021/09/10 11:31
 */
public enum Km {

    KEYBOARD("k"),
    MOUSE("m");

    String code;

    Km(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
