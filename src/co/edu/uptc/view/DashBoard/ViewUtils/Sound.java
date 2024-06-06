package co.edu.uptc.view.DashBoard.ViewUtils;

import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    private Clip clip;

    public Sound(String route){
        loadClip(route);
    }

    private void loadClip(String route){
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound.class.getResource(route)));
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void play(){
        clip.stop();
        if(clip!=null){
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void loop(){
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
