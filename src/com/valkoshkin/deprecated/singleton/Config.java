package com.valkoshkin.deprecated.singleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Config instance;
    private static final String DEFAULT_PROPERTIES_PATH = "src/com/valkoshkin/resources/config.properties";
    private Properties properties;

    private Config(String path) {
        try (var fis = new FileInputStream(path)) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getConfig() {
        return properties;
    }

    public static synchronized Config getInstance() {
        if (instance == null) {
            instance = new Config(DEFAULT_PROPERTIES_PATH);
        }
        return instance;
    }

    public static synchronized Config getInstance(String path) {
        if (instance == null) {
            instance = new Config(path);
        }
        return instance;
    }
}
