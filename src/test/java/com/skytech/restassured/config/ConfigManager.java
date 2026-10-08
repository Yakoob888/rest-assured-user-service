package com.skytech.restassured.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties properties =
            new Properties();

    static {

        // Load common configuration
        loadProperties("config.properties");

        // Load local configuration
        loadProperties("config.local.properties");
    }

    private ConfigManager() {
    }

    private static void loadProperties(
            String fileName) {

        try (InputStream inputStream =
                     ConfigManager.class
                             .getClassLoader()
                             .getResourceAsStream(fileName)) {

            if (inputStream == null) {

                throw new RuntimeException(
                        fileName + " not found in "
                        + "src/test/resources");
            }

            properties.load(inputStream);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load configuration: "
                    + fileName,
                    e);
        }
    }

    public static String get(String key) {

        String value =
                properties.getProperty(key);

        if (value == null || value.isBlank()) {

            throw new RuntimeException(
                    "Configuration property not found: "
                    + key);
        }

        return value;
    }
}