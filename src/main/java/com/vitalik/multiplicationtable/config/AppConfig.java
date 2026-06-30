package com.vitalik.multiplicationtable.config;

import java.util.Properties;

public class AppConfig {
    private final String min;
    private final String max;
    private final String increment;

    public AppConfig(Properties properties) {
        this.min = getRequiredProperty(properties, "min");
        this.max = getRequiredProperty(properties, "max");
        this.increment = getRequiredProperty(properties, "increment");
    }

    private String getRequiredProperty(Properties properties, String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Required property is missing: " + key);
        }
        return value;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    public String getIncrement() {
        return increment;
    }
}
