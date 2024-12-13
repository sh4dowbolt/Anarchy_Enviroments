package com.suraev.nbtPlugin.Command.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class CustomEnchantmentHandler {

    public static final String UNBREAKABLE= "unbreakable";

    private static final Set<String> allowedChars = new HashSet<>();

    static {
        Field[] declaredFields = CustomEnchantmentHandler.class.getDeclaredFields();
        for(Field field:declaredFields) {
            if(Modifier.isStatic(field.getModifiers())) {
                try {
                    allowedChars.add(String.valueOf(field.get(null)));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static Set<String> values() {
        return allowedChars;
    }




}
