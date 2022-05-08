package com.princevelasco.FlashCardGame.utils;

import java.util.Objects;
import java.util.stream.Collectors;

public class Map {
    /**
     * Get the key based on the value
     *
     * @param map   The map to search for the key.
     * @param value The value to search for.
     * @return The key if found, null otherwise.
     */
    public static String getKeysByValue(java.util.Map<String, String> map, String value) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(java.util.Map.Entry::getKey)
                .collect(Collectors.toSet()).stream().findFirst().orElse(null);
    }
}
