package com.suraev.nbtPlugin.Counter;

public enum Quality {
    uncommon, rare, legendary, uniq, chaos;

    public static boolean isAllowed(String word) {
        for(Quality quality: Quality.values()) {
            if(quality.name().equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }
}
