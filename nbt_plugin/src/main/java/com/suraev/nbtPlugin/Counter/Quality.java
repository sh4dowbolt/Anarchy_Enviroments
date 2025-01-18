package com.suraev.nbtPlugin.Counter;

public enum Quality {
    uncommon, rare, legendary, uniq, chaos, jetton, currency;

    public static boolean isAllowed(String word) {
        for(Quality quality: Quality.values()) {
            if(quality.name().equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }
}
