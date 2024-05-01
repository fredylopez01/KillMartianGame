package co.edu.uptc.view.DashBoard.ViewUtils;

import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundFiles {
    private static Clip clip;

    public SoundFiles(String route){
        loadClip(route);
    }

    public static Clip loadClip(String route){
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(SoundFiles.class.getResource(route)));
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        return clip;
    }

    public void play(){
        if(clip!=null){
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void stop(){
        clip.stop();
    }
}
