package com.suraev.Utils;

public enum Kit {
    start, base, tools, dtools, notch, color, firework;

    public static boolean isExisted(String input) {
        for(Kit kit: Kit.values()) {
            if(kit.name().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}
