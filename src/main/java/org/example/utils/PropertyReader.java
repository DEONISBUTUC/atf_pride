package org.example.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertyReader is a simple facade intended to provide read-only access to
 * configuration values for tests. In a typical setup it would load key/value
 * pairs from a properties source available on the classpath (for example,
 * src/test/resources/application.properties) and expose them via helper
 * methods such as getProperty(String).
 * <p>
 * Note: The implementation is intentionally left out in this skeleton project
 * and should be completed to fit the needs of your test framework (e.g.,
 * caching values, supporting multiple sources, or environment overrides).
 */
public class PropertyReader {

    private static final String DEFAULT_FILE = "application.properties";
    private static final String ENV_KEY = "env";
    private static final String PROFILES_PATTERN = "profiles/%s-env/application-%s.properties";

    private static final PropertyReader INSTANCE = new PropertyReader();
    private final Properties properties = new Properties();

    private PropertyReader() {
        loadFromClasspath(DEFAULT_FILE);

        String environment = properties.getProperty(ENV_KEY);
        if (environment == null || environment.isBlank()) {
            throw new IllegalStateException("Missing 'env' key in " + DEFAULT_FILE);
        }

        String environmentFilePath = String.format(PROFILES_PATTERN, environment, environment);
        loadFromClasspath(environmentFilePath);


    }

    public static PropertyReader getInstance() {
        return INSTANCE;
    }

    public String getProperty(String name) {
        String value = properties.getProperty(name);
        if (value == null) {
            throw new IllegalArgumentException("Missing property in configuration file: " + name);
        }
        return value.trim();
    }

    public int getIntProperty(String name) {
        String value = getProperty(name);
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid integer value for property '" + name + "': " + value);
        }
    }

    public boolean getBooleanProperty(String name) {
        String value = getProperty(name).trim().toLowerCase();
        if (!value.equals("true") && !value.equals("false")) {
            throw new IllegalArgumentException("Invalid boolean for property '" + name + "': " + value);
        }
        return Boolean.parseBoolean(value);
    }

    private void loadFromClasspath(String filePath) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                throw new IllegalStateException("Configuration file not found on classpath: " + filePath);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error reading configuration file: " + filePath, e);
        }
    }

}
