package com.ismailakbari.server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class PropWriter {

    public static boolean writeProps(String filePath, String data) throws IOException {
        //convert back data to Map
        Map<String,String> propertiesMap = new HashMap<>();
        String[] entry = data.split("=");                   //split the pairs to get key and value
        propertiesMap.put(entry[0].trim(), entry[1].trim());          //add them to the hashmap and trim whitespaces

        Properties properties = new Properties();

        try {
            for (Map.Entry<String, String> entry2 : propertiesMap.entrySet()) {
                properties.setProperty(entry2.getKey(), entry2.getValue());
            }
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            System.out.println( "prop size=" + properties.entrySet().size() );
            properties.store(fileOutputStream, null);
            return true;
        } catch (IOException e) {
            // Handle exception, e.g., log or throw it further
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeProps2(String filePath, String data) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data);
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
