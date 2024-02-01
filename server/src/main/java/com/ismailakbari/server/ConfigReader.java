package com.ismailakbari.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {

    public static Map<String, String> readProps(String filePath) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
        }

        Map<String, String> config = new HashMap<>();

        for (String key : properties.stringPropertyNames())
                config.put(key, properties.getProperty(key));

        return config;
    }
}
