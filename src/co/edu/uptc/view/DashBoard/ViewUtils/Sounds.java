package co.edu.uptc.view.DashBoard.ViewUtils;

import co.edu.uptc.Utils.Values;

public class Sounds {
    private Sound soundBackground;
    private Sound soundShoot;
    private Sound soundBurst;

    public Sounds(){
        soundBackground = new Sound(SoundFiles.loadClip(Values.pathSoundBackground));
    }

    public void stopSoundBackground(){
        soundBackground.stop();
    }

    public void playSoundShoot(){
        soundShoot = new Sound(SoundFiles.loadClip(Values.pathSoundShoot));
        soundShoot.play();
    }

    public void playSoundBurst(){
        soundBurst = new Sound(SoundFiles.loadClip(Values.pathSoundBurst));
        soundBurst.play();
    }

    public void playSoundBackground() {
        soundBackground.loop();
    }

    
}
