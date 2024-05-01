package co.edu.uptc.view.DashBoard.ViewUtils;

public class Sounds {
    private Sound soundBackground;
    private Sound soundShoot;
    private Sound soundBurst;

    public Sounds(){
        soundBackground = new Sound(SoundFiles.loadClip("/co/edu/uptc/view/DashBoard/sound/background.wav"));
    }

    public void stopSoundBackground(){
        soundBackground.stop();
    }

    public void playSoundShoot(){
        soundShoot = new Sound(SoundFiles.loadClip("/co/edu/uptc/view/DashBoard/sound/boom.wav"));
        soundShoot.play();
        soundShoot.changeVolume(-30);
    }

    public void playSoundBurst(){
        soundBurst = new Sound(SoundFiles.loadClip("/co/edu/uptc/view/DashBoard/sound/explosion.wav"));
        soundBurst.play();
        soundBurst.changeVolume(6);
    }

    public void playSoundBackground() {
        soundBackground.loop();
        soundBackground.changeVolume(-20);
    }

    
}
