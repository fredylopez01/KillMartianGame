package co.edu.uptc.view.DashBoard.ViewUtils;

import co.edu.uptc.Utils.Values;

public class Sounds {
    private Sound soundBackground;
    private Sound soundShoot;
    private Sound soundBurst;

    public Sounds(){
        soundBackground = new Sound(Values.pathSoundBackground);
        soundShoot = new Sound(Values.pathSoundShoot);
        soundBurst = new Sound(Values.pathSoundBurst);
    }

    public void stopSoundBackground(){
        soundBackground.stop();
    }

    public void playSoundShoot(){
        soundShoot.stop();
        soundShoot.play();
    }

    public void stopSoundShoot(){
        soundShoot.stop();
    }

    public void playSoundBurst(){
        soundBurst.stop();
        soundBurst.play();
    }

    public void stopSoundBurst(){
        soundBurst.stop();
    }

    public void playSoundBackground() {
        soundBackground.loop();
    }
    
}
