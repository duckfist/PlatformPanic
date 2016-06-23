package pp.entities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// TODO: This class is unused because music playback mysteriously cuts off after a few seconds
public class MusicClip {

	public MusicClip(String filename) {
		
		Media m = new Media(getClass().getClassLoader().getResource(filename).toExternalForm());
		MediaPlayer mediaPlayer = new MediaPlayer(m);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.play();
	}
	
}
