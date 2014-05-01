package Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundEffect {
	 public static synchronized void playSoundEffect(final String url) {
			
		  new Thread(new Runnable() {
		
		    public void run() {
		      try {
		    	  Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		          Sound.class.getResourceAsStream("/" + url));
		        clip.open(inputStream);
		        
		        FloatControl gainControl = 
		        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		        
		        gainControl.setValue(-4.0f); // Reduce volume by 10 decibels.
		        		        
		        clip.start(); 
		        
		       
		        
		        
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
	 public static synchronized void playSoundEffectLower(final String url) {
			
		  new Thread(new Runnable() {
		
		    public void run() {
		      try {
		    	  Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		          Sound.class.getResourceAsStream("/" + url));
		        clip.open(inputStream);
		        
		        FloatControl gainControl = 
		        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		        
		        gainControl.setValue(-17.0f); // Reduce volume by 10 decibels.
		        		        
		        clip.start(); 
		        
		       
		        
		        
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
	 public static synchronized void playSoundEffectHigher(final String url) {
			
		  new Thread(new Runnable() {
		
		    public void run() {
		      try {
		    	  Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		          Sound.class.getResourceAsStream("/" + url));
		        clip.open(inputStream);
		        
		        FloatControl gainControl = 
		        (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		        
		        gainControl.setValue(+5f); // Reduce volume by 10 decibels.
		        		        
		        clip.start(); 
		        
		       
		        
		        
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
}
