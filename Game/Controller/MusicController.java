package Game.Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


/**
 * This class contains all the code for the music functions in the SettingsGUI
 *
 * @author Yasir Kakar
 * @version 4.0
 */
public class MusicController {
    private static Clip clip;
    private boolean mute = false;
    private MusicController mc;

    /**
     * This method plays the music chosen for the background music in a loop
     */
    public void playMusic(String filename) {
    	if(mute == false) {
	        try {
	            File file = new File(filename);
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
	            clip = AudioSystem.getClip();
	            clip.open(audioInputStream);
	            clip.start();
	            clip.loop(clip.LOOP_CONTINUOUSLY);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
    }

    /**
     * This class allows the user to turn off the music either through
     * the settingsGUI or by closing the program
     */
    public void stopMusic() {
        clip.stop();
    }
    public void muteMusic() {
    	this.mute = true;
    	System.out.println("music mute " + mute);
    	clip.stop();
    }
    public void statMusic() {
    	this.mute = false;
    }
    public boolean checkMute() {
    	return this.mute;
    }
    public MusicController getController() {
    	return this.mc;
    }

}
