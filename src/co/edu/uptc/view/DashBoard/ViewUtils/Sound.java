package co.edu.uptc.view.DashBoard.ViewUtils;

import javax.sound.sampled.Clip;

public class Sound {
    private Clip clip;

    public Sound(Clip clip) {
        this.clip = clip;
    }
    public void play(){
        clip.stop();
        clip.start();
    }
    public void loop(){
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
