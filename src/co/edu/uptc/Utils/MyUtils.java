package co.edu.uptc.Utils;

public class MyUtils {
    public static void sleep(int duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
