package co.edu.uptc.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AdminProperties {
    public static String routeProperties = "config\\config.properties";

    public static String read(String property){
        Properties propertiesFile = new Properties();
        try {
            propertiesFile.load(new FileReader(routeProperties));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesFile.getProperty(property);
    }

    public static void write(String property, String value){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(routeProperties);
            properties.load(fileInputStream);
            fileInputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream(routeProperties);
            properties.setProperty(property, value);
            properties.store(fileOutputStream, null);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
