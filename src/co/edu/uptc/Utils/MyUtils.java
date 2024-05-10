package co.edu.uptc.Utils;

public class MyUtils {

    public static void sleep(int duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static  int positionBullet(int idBullet, int x, int typePacecraft){
        int position = 0;
        if(typePacecraft == 0 || typePacecraft == 1){
            if(idBullet == 0) position = x+4;
            else position = x+65;
        } else if(typePacecraft == 2 || typePacecraft == 3){
            position= x+35;
        }
        return position;
    }

}
