package co.edu.uptc.pojos;

import co.edu.uptc.config.AdminProperties;

public class Values {
    public static int widthWindow = Integer.valueOf(AdminProperties.read("widthWindow"));
    public static int heightWindow = Integer.valueOf(AdminProperties.read("heightWindow"));
    public static int maxSpeedTime = Integer.valueOf(AdminProperties.read("maxSpeedTime"));
    public static int minSpeedTime = Integer.valueOf(AdminProperties.read("minSpeedTime"));
    public static int maxSpeedTimeAdd = Integer.valueOf(AdminProperties.read("maxSpeedTimeAdd"));
    public static int minSpeedTimeAdd = Integer.valueOf(AdminProperties.read("minSpeedTimeAdd"));
}
