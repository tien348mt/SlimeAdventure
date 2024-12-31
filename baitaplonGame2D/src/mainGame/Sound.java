package mainGame;


import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	FloatControl fc;
	int volumeScale = 3;
	float volume;
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/gameplaysound.wav");
		soundURL[1] = getClass().getResource("/sound/end.wav");
		soundURL[2] = getClass().getResource("/sound/fireball.wav");
		soundURL[3] = getClass().getResource("/sound/coin.wav");
		soundURL[4] = getClass().getResource("/sound/gameover2.wav");
		soundURL[5] = getClass().getResource("/sound/random.wav");
		soundURL[6] = getClass().getResource("/sound/tele.wav");
		soundURL[7] = getClass().getResource("/sound/bum.wav");
		soundURL[8] = getClass().getResource("/sound/openChest.wav");
	}
	
	public void setFile(int i ) {
		try {
			AudioInputStream ais  = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play() {
		
		clip.start();
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		
		clip.stop();
	}
	public void checkVolume() {
		switch(volumeScale) {
		case 0:volume = -80f; break;
		case 1:volume = -20f; break; 
		case 2:volume = -12f; break; 
		case 3:volume = -5f; break; 
		case 4:volume = 1f; break; 
		}
		fc.setValue(volume);
	}
}
