package com.skytech.restassured.utils;

import java.util.UUID;

public final class TestDataUtils {

    private TestDataUtils() {
    }

    public static String uniqueId() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8);
    }

    public static String uniqueEmail(String email) {

        validateNotBlank(email, "Email");

        String[] parts = email.split("@", 2);

        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Invalid email: " + email);
        }

        return parts[0]
                + "_"
                + uniqueId()
                + "@"
                + parts[1];
    }

    public static String uniqueValue(String prefix) {

        validateNotBlank(prefix, "Prefix");

        return prefix + "_" + uniqueId();
    }

    private static void validateNotBlank(
            String value,
            String fieldName) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " cannot be null or empty");
        }
    }
}