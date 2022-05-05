package com.princevelasco.FlashCardGame.utils;

import java.util.Objects;

public class Map {
    public static <T, E> String getKeysByValue(java.util.Map<T, E> map, E value) {
        return String.valueOf(map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(java.util.Map.Entry::getKey)
                .findFirst());
    }
}
