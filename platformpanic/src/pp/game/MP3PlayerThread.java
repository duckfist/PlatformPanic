package pp.game;

public class MP3PlayerThread extends Thread {
	
	MP3Player mp3test;
	
    public void run(){
    	mp3test = new MP3Player();
    	MP3Player.Begin();
     }
}
