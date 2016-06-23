package pp.entities;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum SoundClip {
	
    MENU_SELECT("Sounds/menu_select.wav"),
    MENU_NAV("Sounds/menu_nav.wav"),
    MENU_BACK("Sounds/menu_back.wav"),
    DEATH("Sounds/death.wav"),
    SPRING("Sounds/spring.wav"),
    LAND("Sounds/land.wav"),
    PLAND("Sounds/pland.wav"),
    DISAPPEAR0("Sounds/disappear_4.wav"),
    DISAPPEAR1("Sounds/disappear_3.wav"),
    DISAPPEAR2("Sounds/disappear_2.wav"),
    DISAPPEAR3("Sounds/disappear_1.wav");
    
    public static Volume volume;
    private Clip clip;

    private SoundClip(String soundFileName) {
    	
        try {
			URL url = this.getClass().getClassLoader().getResource(soundFileName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
        	AudioFormat baseFormat = ais.getFormat();
        	AudioFormat decodeFormat = new AudioFormat(
        		AudioFormat.Encoding.PCM_SIGNED,
        		baseFormat.getSampleRate(),
        		16,
        		baseFormat.getChannels(),
        		baseFormat.getChannels() * 2,
        		baseFormat.getSampleRate(),
        		false
        		);
        	
        	AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
        	
        	clip = AudioSystem.getClip();
        	clip.open(dais);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void loadAll() {
    }

    public void play() {
    	if (this.clip == null) return;
    	
        if (volume != Volume.MUTE) {
            this.clip.stop();
            this.clip.setFramePosition(0);
            this.clip.start();
        }
    }
    
    public void stop() {
    	if (clip.isRunning()) clip.stop();
    }

    public static void init() {
        SoundClip.values();
    }

    static {
        volume = Volume.LOW;
    }

    public static enum Volume {
        MUTE,
        LOW,
        MEDIUM,
        HIGH;
        

        private Volume() {
        }
    }

}

