package ru.job4j.grabber.service;

import org.apache.log4j.Logger;
import java.util.Properties;

public class Config {
    private static final Logger LOGGER = Logger.getLogger(Config.class);
    private final Properties properties = new Properties();

    public void load(String file) {
        try (var input = getClass().getClassLoader().getResourceAsStream(file)) {
            if (input == null) {
                throw new IllegalArgumentException("File not found " + file);
            }
            properties.load(input);
        } catch (Exception e) {
            LOGGER.error(String.format("When load file : %s", file), e);
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}