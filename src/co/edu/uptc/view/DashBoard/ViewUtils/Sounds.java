package co.edu.uptc.view.DashBoard.ViewUtils;

import co.edu.uptc.Utils.Values;

public class Sounds {
    private Sound soundBackground;
    private SoundFiles soundFilesBackground;
    private Sound soundShoot;
    private Sound soundBurst;

    public Sounds(){
        soundFilesBackground = new SoundFiles(Values.pathSoundBackground);
        soundBackground = new Sound(soundFilesBackground.getClip());
    }

    public void stopSoundBackground(){
        soundBackground.stop();
    }

    public void playSoundShoot(){
        soundShoot = new Sound(new SoundFiles(Values.pathSoundShoot).getClip());
        soundShoot.play();
    }

    public void playSoundBurst(){
        soundBurst = new Sound(new SoundFiles(Values.pathSoundBurst).getClip());
        soundBurst.play();
    }

    public void playSoundBackground() {
        soundBackground.loop();
    }

    
}
