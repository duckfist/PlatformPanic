package pp.game;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import javafx.embed.swing.JFXPanel;

public class GameFrame extends JFrame {
	
	private static final long serialVersionUID = 6916867857736108028L;
	private static final int DEFAULT_FPS = 60;
    public static boolean loading = true;
    private GamePanel gp;
    JFXPanel jfx;
    MP3Player mp3player;
    
    public GameFrame() {
    	// NOTE: Remnants from conversion of JApplet to JFrame.  Left in for reference.
    	//       No longer used since not hosted as applet on a website.
        //this.loadFromHTMLParams();
    	
    	// Start JavaFX and media player for MP3 playback
    	jfx = new JFXPanel();
    	MP3PlayerThread mp3Thread = new MP3PlayerThread();
    	mp3Thread.start();
    	
    	// Window settings
    	super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	super.setTitle("Platform Panic");
    	super.setResizable(false);
    	
        long period = 1000 / DEFAULT_FPS;
        this.makeGUI(period);
        this.gp.startGame();
    }
    
    public static void main(String[] args) {
    	GameFrame f = new GameFrame ();
    	f.setSize(532,488);
    	f.setVisible(true);
    	f.setLayout(new FlowLayout());
    	
    	// NOTE: Remnants from conversion of JApplet to JFrame.  Left in for reference.
    	/*
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	GameApplet ex = new GameApplet();
                ex.setVisible(true);
            }
        });
        */
    }

    private void makeGUI(long period) {
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());
        this.gp = new GamePanel(this, period * 1000000);
        c.add((Component)this.gp, "Center");
    }

// NOTE: Remnants from conversion of JApplet to JFrame.  Left in for reference.
    
//    @Override
//    public void start() {
//        this.gp.resumeGame();
//    }
//
//    @Override
//    public void stop() {
//        this.gp.pauseGame();
//    }
//
//    @Override
//    public void destroy() {
//        // Data.saveToPHP();
//        this.gp.stopGame();
//    }

//    private void loadFromHTMLParams() {
//        int total_played_int;
//        int total_platforms_activated_int;
//        int total_jumps_int;
//        int total_time_played_int;
//        String[] record_time_params = new String[40];
//        for (int i = 0; i < 40; ++i) {
//            record_time_params[i] = this.getParameter("time_level" + i);
//            if (record_time_params[i] == null) continue;
//            Data.player_record_array[i] = Integer.parseInt(record_time_params[i]);
//        }
//        Data.username = this.getParameter("username");
//        String total_jumps = this.getParameter("jump_total");
//        String total_platforms_activated = this.getParameter("platform_total");
//        String total_time_played = this.getParameter("time_total");
//        String total_played = this.getParameter("played_total");
//        try {
//            total_jumps_int = Integer.parseInt(total_jumps);
//            total_platforms_activated_int = Integer.parseInt(total_platforms_activated);
//            total_time_played_int = Integer.parseInt(total_time_played);
//            total_played_int = Integer.parseInt(total_played);
//        }
//        catch (Exception e) {
//            System.out.println(e);
//            total_jumps_int = 0;
//            total_platforms_activated_int = 0;
//            total_time_played_int = 0;
//            total_played_int = 0;
//        }
//        Data.total_jumps = total_jumps_int;
//        Data.total_platforms_activated = total_platforms_activated_int;
//        Data.total_time_played = total_time_played_int;
//        Data.total_played = total_played_int;
//    }
}

