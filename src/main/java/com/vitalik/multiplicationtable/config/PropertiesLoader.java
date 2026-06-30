package com.vitalik.multiplicationtable.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class PropertiesLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesLoader.class);

    private PropertiesLoader() {
    }

    public static Properties load(String fileName) {
        Path path = Path.of(fileName);

        if (Files.isRegularFile(path)) {
            return loadFromFile(path);
        }

        return loadFromResources(fileName);
    }

    private static Properties loadFromFile(Path path) {
        Properties properties = new Properties();

        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            LOGGER.info("Loading properties from file: {}", path.toAbsolutePath());
            properties.load(reader);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load properties from file: " + path.toAbsolutePath(), e);
        }
    }

    private static Properties loadFromResources(String fileName) {
        Properties properties = new Properties();

        try (InputStream inputStream = PropertiesLoader.class
                .getClassLoader()
                .getResourceAsStream(fileName)) {

            if (inputStream == null) {
                LOGGER.warn("Properties resource not found: {}", fileName);
                return properties;
            }

            try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                LOGGER.info("Loading properties from resource: {}", fileName);
                properties.load(reader);
                return properties;
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load properties from resource: " + fileName, e);
        }
    }
}
