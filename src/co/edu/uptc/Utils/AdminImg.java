package co.edu.uptc.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;

public class AdminImg {
    public String routeProperties = "config\\pathImgs.properties";
    public HashMap<String, ImageIcon> images = new HashMap<>();

    public AdminImg(){
        Properties propertiesFile = new Properties();
        try {
            propertiesFile.load(new FileReader(routeProperties));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Map.Entry<Object, Object> entry : propertiesFile.entrySet()) {
            images.put((String) entry.getKey(), new ImageIcon(getClass().getResource((String)entry.getValue())));
        }
    }

    public ImageIcon getImage(String name){
        return images.get(name);
    }
}
