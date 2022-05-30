package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav"); // Music
		soundURL[1] = getClass().getResource("/sound/coin.wav"); // SFX
		soundURL[2] = getClass().getResource("/sound/powerup.wav"); // SFX
		soundURL[3] = getClass().getResource("/sound/unlock.wav"); // SFX
		soundURL[4] = getClass().getResource("/sound/fanfare.wav"); // SFX
		
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		} catch(Exception e) {
			
		}		
	}
	public void play() {
		
		clip.start(); // Starts playing a sound effect
		
	}
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY); // Loops a sound effect (good for theme music)
		
	}
	public void stop() {
		
		clip.stop(); // Stops playing a sound effect
		
	}
}
