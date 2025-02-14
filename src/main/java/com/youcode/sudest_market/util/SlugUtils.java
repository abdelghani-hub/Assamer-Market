package com.youcode.sudest_market.util;

public class SlugUtils {
    
    public static String generateSlug(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "";
        }

        return name.toLowerCase()
            .trim()
            .replaceAll("\\s+", "-")
            .replaceAll("[^a-z0-9-]", "")
            .replaceAll("-+", "-")
            .replaceAll("^-|-$", "");
    }
}