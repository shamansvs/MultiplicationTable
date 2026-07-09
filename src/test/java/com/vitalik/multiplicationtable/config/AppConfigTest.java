package com.vitalik.multiplicationtable.config;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppConfigTest {
    @Test
    void shouldReadRequiredProperties() {
        Properties properties = new Properties();
        properties.setProperty("min", "1");
        properties.setProperty("max", "10");
        properties.setProperty("increment", "2");

        AppConfig config = new AppConfig(properties);

        assertEquals("1", config.getMin());
        assertEquals("10", config.getMax());
        assertEquals("2", config.getIncrement());
    }

    @Test
    void shouldThrowExceptionWhenPropertyIsMissing() {
        Properties properties = new Properties();
        properties.setProperty("min", "1");
        properties.setProperty("increment", "2");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new AppConfig(properties)
        );

        assertEquals("Required property 'max' is missing in application.properties", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPropertyIsBlank() {
        Properties properties = new Properties();
        properties.setProperty("min", "1");
        properties.setProperty("max", "10");
        properties.setProperty("increment", " ");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new AppConfig(properties)
        );

        assertEquals("Required property 'increment' must not be blank", exception.getMessage());
    }
}
