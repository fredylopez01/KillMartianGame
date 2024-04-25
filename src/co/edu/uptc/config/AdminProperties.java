package co.edu.uptc.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AdminProperties {
    public static String routeProperties = "src\\co\\edu\\uptc\\config\\config.properties";

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
}
