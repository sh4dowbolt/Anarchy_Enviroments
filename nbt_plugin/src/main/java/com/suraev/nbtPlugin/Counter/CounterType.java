package com.suraev.nbtPlugin.Counter;

public enum CounterType {
    dragon, player, animal, monster;

    public static boolean isAllowed(String word) {
        for(CounterType counterType: CounterType.values()) {
            if(counterType.name().equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }
}
