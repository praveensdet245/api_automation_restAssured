package dev.restfil.api.common.utils;

import java.util.HashMap;

public class GlobalMap{
    private static HashMap<String, Object> internalMap = new HashMap<>();

    private GlobalMap() {}

    // Static method to add a key-value pair
    public static void put(String key, Object value) {
        internalMap.put(key, value);
    }

    // Static method to get a value by key
    public static Object get(String key) {
        return internalMap.get(key);
    }

    // Static method to remove a key-value pair
    public static Object remove(String key) {
        return internalMap.remove(key);
    }

    // Static method to check if a key exists
    public static boolean containsKey(String key) {
        return internalMap.containsKey(key);
    }


    // Static method to get the size of the map
    public static int size() {
        return internalMap.size();
    }

    // Static method to clear all entries
    public static void clear() {
        internalMap.clear();
    }

    // Static method to print the contents of the global map
    public static String toStringMap() {
        return internalMap.toString();
    }

}
