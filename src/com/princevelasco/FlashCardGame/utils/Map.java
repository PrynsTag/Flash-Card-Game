package com.princevelasco.FlashCardGame.utils;

import java.util.Objects;
import java.util.stream.Collectors;

public class Map {
    public static String getKeysByValue(java.util.Map<String, String> map, String value) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(java.util.Map.Entry::getKey)
                .collect(Collectors.toSet()).stream().findFirst().orElse(null);
    }
}
