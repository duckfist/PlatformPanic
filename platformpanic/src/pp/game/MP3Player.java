package pp.game;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaMarkerEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MP3Player extends Application {
	
	public static MP3Player Instance;
	static MediaPlayer mediaPlayer;
	public static BGM CurrentBGM;
	public static Map<BGMTracks, BGM> BGMList;
	
	public enum BGMTracks {
		WORLD1,
		WORLD2,
		WORLD3,
		WORLD4,
		WORLD5,
		WORLD6,
		FANFARE;
	}
	
	public static void Begin()
	{
		launch(new String());
	}
	
	public MP3Player(){
    	Instance = this;
    	
    	BGMList = new HashMap<BGMTracks, BGM>();
//    	BGMList.put(BGMTracks.WORLD1, new BGM("/Music/World1 (Journey to Silius Stage 3)_edit.mp3", 51711, 102840));
    	BGMList.put(BGMTracks.WORLD1, new BGM("/Music/World1 (Journey to Silius Stage 3).mp3", 0, 51266));
    	BGMList.put(BGMTracks.WORLD2, new BGM("/Music/World2 (Rescue Rangers Zone J).mp3", 4104, 32377));
    	BGMList.put(BGMTracks.WORLD3, new BGM("/Music/World3 (Ninja Gaiden 3 Stage 4-1).mp3", 1313, 55731));
    	BGMList.put(BGMTracks.WORLD4, new BGM("/Music/World4 (Mitsume ga Tooru Stage 2-2).mp3", 0, 42955));
    	BGMList.put(BGMTracks.WORLD5, new BGM("/Music/World5 (Gradius 2 Stage 2).mp3", 100, 35103));
    	BGMList.put(BGMTracks.FANFARE, new BGM("/Music/Fanfare (SMB3 King Restored).mp3"));
    	//BGMList.put(BGMTracks.WORLD6, new BGM("/Music/World6 (Little Nemo Nightmare Land).mp3"));
    }
    
    @Override
    public void start(Stage primaryStage) {
//    	final URL resource = getClass().getResource("world2.mp3");
//        final Media media = new Media(resource.toString());
//        mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();
    	//PlayBGM(BGMTracks.WORLD1);
    }
    
    public static void PlayBGM(BGMTracks track) {
    	BGM bgm = BGMList.get(track);
    	if (CurrentBGM == bgm) return;
    	CurrentBGM = bgm;
    	
    	if (mediaPlayer != null){
			mediaPlayer.stop();
			mediaPlayer.dispose();
    	}
    	
    	Media media;
    	try {
    		URL resourcename = MP3Player.class.getResource(bgm.filename);
			media = new Media(resourcename.toURI().toString());

	        mediaPlayer = new MediaPlayer(media);
	        
	        // Set up a loop if BGM has one, else play once
	        if (bgm.loopEnd > 0){
	        
		        final ObservableMap<String,Duration> markers = media.getMarkers();
		        markers.put("World1Start", Duration.millis(bgm.loopEnd));
		        
		        mediaPlayer.setOnMarker(new EventHandler<MediaMarkerEvent>(){
		        	
					@Override
					public void handle(MediaMarkerEvent arg0) {
						mediaPlayer.seek(Duration.millis(bgm.loopStart));
					}});
	        }

	        mediaPlayer.play();
        
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
