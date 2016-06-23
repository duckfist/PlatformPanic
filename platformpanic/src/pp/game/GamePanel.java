package pp.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import javax.swing.JPanel;

import pp.entities.Camera;
import pp.entities.Effects;
import pp.entities.Player;
import pp.entities.SoundClip;
import pp.game.MP3Player.BGMTracks;
import pp.gui.LevelCleared;
import pp.gui.LevelClock;
import pp.gui.LevelToken;
import pp.gui.MainMenu;
import pp.gui.MenuElement;
import pp.levels.*;
import pp.utilities.Float2;
import pp.utilities.ImageLoader;
import pp.utilities.Timer;

public class GamePanel extends JPanel
		implements Runnable,
			MouseListener,
			KeyListener {
	
	private static final long serialVersionUID = -466105191402442475L;
    private static long MAX_STATS_INTERVAL = 1000000000;
    private static int MAX_FRAME_SKIPS = 5;
    private static int NUM_FPS = 10;
    private long statsInterval = 0;
    private long prevStatsTime;
    private long totalElapsedTime = 0;
    private long gameStartTime;
    private int timeSpentInGame = 0;
    private long frameCount = 0;
    private double[] fpsStore;
    private long statsCount = 0;
    private double averageFPS = 0.0;
    private long framesSkipped = 0;
    private long totalFramesSkipped = 0;
    private double[] upsStore;
    private double averageUPS = 0.0;
    private DecimalFormat df = new DecimalFormat("0.##");
    //private DecimalFormat timedf = new DecimalFormat("0.####");
    private Thread animator;
    private volatile boolean running = false;
    private volatile boolean isPaused = false;
    public long period;
    public static Timer time;
    public static boolean key_left;
    public static boolean key_right;
    public static boolean key_up;
    public static boolean key_down;
    public static boolean key_a;
    public static boolean key_q;
    public static boolean key_s;
    public static boolean key_w;
    public static boolean key_d;
    public static boolean key_f;
    public static boolean key_r;
    public static boolean key_esc;
    public static boolean key_enter;
    public static boolean message_box;
    //private GameApplet parent;
    public Level current_level;
    private MainMenu main_menu;
    public Player player;
    public static Data saveData;
    public Camera camera;
    private boolean finishedOff = false;
    public static boolean loading;
    public static boolean level_loading;
    public static int next_level;
    public static boolean debugMode;
    private Image dbImage = null;
    private Graphics dbg;
    public ImageLoader image_loader;
    public BufferedImage menuimg_gamecomplete;
    private int current_game_state = 0;

    public GamePanel(GameFrame applet, long period) {
    	
        //this.parent = applet;
        this.period = period;
        
        saveData = new Data(); 
        
        this.player = new Player(0.0f, 0.0f);
        
        //Data.readTimeFromServer();
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(512, 448));
        this.addKeyListener(this);
        this.setFocusable(true);
        this.addMouseListener(this);
        time = new Timer();
        LevelClock.initClock();
        this.resetKeys();
        this.running = false;
        this.fpsStore = new double[NUM_FPS];
        this.upsStore = new double[NUM_FPS];
        for (int i = 0; i < NUM_FPS; ++i) {
            this.fpsStore[i] = 0.0;
            this.upsStore[i] = 0.0;
        }
        switch (this.current_game_state) {
            case 0: {
                this.initMainMenu();
                break;
            }
            case 2: {
                this.initStage(1);
                break;
            }
        }
        this.loadImages();
        SoundClip.init();
        loading = false;
        
    }
    
    // TODO: Remove since redundant?
    public static void SaveData() {
    	saveData.saveToFile();
    }

    public void loadImages() {
        this.image_loader = new ImageLoader();
        try {
            this.menuimg_gamecomplete = this.image_loader.loadImage("/Graphics/gamecomplete.png");
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("Exception: Couldn't load an image. Check file name.");
        }
    }

    public void startGame() {
        if (this.animator == null || !this.running) {
            this.animator = new Thread(this);
            this.animator.start();
        }
        
    }

    public void resumeGame() {
        this.isPaused = false;
    }

    public void pauseGame() {
        this.isPaused = true;
    }

    public void stopGame() {
        this.running = false;
        this.finishOff();
    }

    public void initMainMenu() {
        this.current_game_state = 0;
        this.resetKeys();
        this.main_menu = new MainMenu();
        this.current_level = null;
    }

    public void initStage(int stage) {
        this.resetKeys();
        switch (stage) {
            case 0: {
                this.current_level = new Level00(this.player, this.camera, 0, stage);
                break;
            }
            case 1: {
                this.current_level = new Level01(this.player, this.camera, 0, stage);
                break;
            }
            case 2: {
                this.current_level = new Level02(this.player, this.camera, 0, stage);
                break;
            }
            case 3: {
                this.current_level = new Level03(this.player, this.camera, 0, stage);
                break;
            }
            case 4: {
                this.current_level = new Level04(this.player, this.camera, 0, stage);
                break;
            }
            case 5: {
                this.current_level = new Level05(this.player, this.camera, 1, stage);
                break;
            }
            case 6: {
                this.current_level = new Level06(this.player, this.camera, 1, stage);
                break;
            }
            case 7: {
                this.current_level = new Level07(this.player, this.camera, 1, stage);
                break;
            }
            case 8: {
                this.current_level = new Level08(this.player, this.camera, 1, stage);
                break;
            }
            case 9: {
                this.current_level = new Level09(this.player, this.camera, 1, stage);
                break;
            }
            case 10: {
                this.current_level = new Level10(this.player, this.camera, 2, stage);
                break;
            }
            case 11: {
                this.current_level = new Level11(this.player, this.camera, 2, stage);
                break;
            }
            case 12: {
                this.current_level = new Level12(this.player, this.camera, 2, stage);
                break;
            }
            case 13: {
                this.current_level = new Level13(this.player, this.camera, 2, stage);
                break;
            }
            case 14: {
                this.current_level = new Level14(this.player, this.camera, 2, stage);
                break;
            }
            case 15: {
                this.current_level = new Level15(this.player, this.camera, 3, stage);
                break;
            }
            case 16: {
                this.current_level = new Level16(this.player, this.camera, 3, stage);
                break;
            }
            case 17: {
                this.current_level = new Level17(this.player, this.camera, 3, stage);
                break;
            }
            case 18: {
                this.current_level = new Level18(this.player, this.camera, 3, stage);
                break;
            }
            case 19: {
                this.current_level = new Level19(this.player, this.camera, 3, stage);
                break;
            }
            case 20: {
                this.current_level = new Level20(this.player, this.camera, 4, stage);
                break;
            }
            case 21: {
                this.current_level = new Level21(this.player, this.camera, 4, stage);
                break;
            }
            case 22: {
                this.current_level = new Level22(this.player, this.camera, 4, stage);
                break;
            }
            case 23: {
                this.current_level = new Level23(this.player, this.camera, 4, stage);
                break;
            }
            case 24: {
                this.current_level = new Level24(this.player, this.camera, 4, stage);
                break;
            }
//            case 25: {
//                this.current_level = new Level25(this.player, this.camera, 2, stage);
//                break;
//            }
//            case 26: {
//                this.current_level = new Level26(this.player, this.camera, 2, stage);
//                break;
//            }
//            case 27: {
//                this.current_level = new Level27(this.player, this.camera, 2, stage);
//                break;
//            }
//            case 28: {
//                this.current_level = new Level28(this.player, this.camera, 2, stage);
//                break;
//            }
//            case 29: {
//                this.current_level = new Level29(this.player, this.camera, 2, stage);
//                break;
//            }
            default: {
                this.current_level = new Level00(this.player, this.camera, 1, stage);
            }
        }
        this.camera = new Camera(this.current_level);
        this.current_level.startLevel(this.camera);
        this.current_game_state = 2;
        SoundClip.init();
        SoundClip.volume = SoundClip.Volume.LOW;
        loading = false;
    }

    public void resetKeys() {
        key_left = false;
        key_right = false;
        key_up = false;
        key_down = false;
        key_a = false;
        key_q = false;
        key_s = false;
        key_w = false;
        key_d = false;
        key_r = false;
        key_f = false;
        key_enter = false;
        key_esc = false;
    }

    @Override
    public void run() {
        long overSleepTime = 0;
        int noDelays = 0;
        long excess = 0;
        this.prevStatsTime = this.gameStartTime = System.nanoTime();
        long beforeTime = this.gameStartTime;
        this.running = true;
        while (this.running) {
            int skips;
            this.gameRender();
            this.paintScreen();
            this.gameUpdate();
            long afterTime = System.nanoTime();
            long timeDiff = afterTime - beforeTime;
            long sleepTime = this.period - timeDiff - overSleepTime;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000);
                }
                catch (InterruptedException ex) {
                    // empty catch block
                }
                overSleepTime = System.nanoTime() - afterTime - sleepTime;
            } else {
                excess -= sleepTime;
                overSleepTime = 0;
                if (++noDelays >= 16) {
                    Thread.yield();
                    noDelays = 0;
                }
            }
            beforeTime = System.nanoTime();
            for (skips = 0; excess > this.period && skips < MAX_FRAME_SKIPS; excess -= this.period, ++skips) {
                this.gameUpdate();
            }
            this.framesSkipped += (long)skips;
            this.storeStats();
        }
        this.finishOff();
    }

    private void gameUpdate() {
        if (!this.isPaused) {
            time.update();
            switch (this.current_game_state) {
                case 0: {
                    break;
                }
                case 2: {
                    if (this.player == null) break;
                    if (this.current_level.hasEnded() && !this.player.dead) {
                        this.player.jumpToPoint(new Float2(256.0f, 10.0f));
                        break;
                    }
                    LevelClock.update();
                    this.current_level.runObjects();
                    this.current_level.testLevelEnd();
                    this.current_level.testCollisionPlatform(this.player);
                    this.player.playerMove(this.current_level);
                    this.camera.moveCamera(this.player);
                    break;
                }
            }
        }
        if (level_loading) {
            level_loading = false;
            this.initStage(next_level);
        }
    }

    private void gameRender() {
        if (this.dbImage == null) {
            this.dbImage = this.createImage(512, 448);
            if (this.dbImage == null) {
            	// TODO figure out what I was doing here
                //System.out.println("dbImage is null");
                return;
            }
            this.dbg = this.dbImage.getGraphics();
        }
        this.dbg.setColor(Color.white);
        this.dbg.fillRect(0, 0, 512, 448);
        this.paintGame(this.dbg);
        this.printOnScreen(this.dbg);
    }

    private void paintScreen() {
        try {
            Graphics g = this.getGraphics();
            if (g != null && this.dbImage != null) {
                g.drawImage(this.dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }
        catch (Exception e) {
        	// TODO figure this stuff out again
            //System.out.println("Graphics Context error: " + e);
        }
    }

    private void paintGame(Graphics g) {
        switch (this.current_game_state) {
            case 0: {
                this.main_menu.displayMenu((Graphics2D)g);
                break;
            }
            case 2: {
                this.current_level.paintLevel(g, this.player);
                this.player.paintComponent(g);
                break;
            }
        }
        if (message_box) {
            Effects.messageInBox(g, 88);
            Effects.imageInBox(g, this.menuimg_gamecomplete);
        }
        if (loading) {
            Effects.borderRect(g, 190, 198, 132, 36, 2, Color.DARK_GRAY, Color.BLACK);
            g.setColor(Color.GREEN);
            g.setFont(new Font("monospaced", 1, 28));
            g.drawString("LOADING...", 198, 224);
        }
    }

    private void storeStats() {
        ++this.frameCount;
        this.statsInterval += this.period;
        if (this.statsInterval >= MAX_STATS_INTERVAL) {
            long timeNow = System.nanoTime();
            long realElapsedTime = timeNow - this.prevStatsTime;
            this.totalElapsedTime += realElapsedTime;
            //double timingError = (double)(realElapsedTime - this.statsInterval) / (double)this.statsInterval * 100.0;
            this.totalFramesSkipped += this.framesSkipped;
            double actualFPS = 0.0;
            double actualUPS = 0.0;
            if (this.totalElapsedTime > 0) {
                actualFPS = (double)this.frameCount / (double)this.totalElapsedTime * 1.0E9;
                actualUPS = (double)(this.frameCount + this.totalFramesSkipped) / (double)this.totalElapsedTime * 1.0E9;
            }
            this.fpsStore[(int)this.statsCount % GamePanel.NUM_FPS] = actualFPS;
            this.upsStore[(int)this.statsCount % GamePanel.NUM_FPS] = actualUPS;
            ++this.statsCount;
            double totalFPS = 0.0;
            double totalUPS = 0.0;
            for (int i = 0; i < NUM_FPS; ++i) {
                totalFPS += this.fpsStore[i];
                totalUPS += this.upsStore[i];
            }
            if (this.statsCount < (long)NUM_FPS) {
                this.averageFPS = totalFPS / (double)this.statsCount;
                this.averageUPS = totalUPS / (double)this.statsCount;
            } else {
                this.averageFPS = totalFPS / (double)NUM_FPS;
                this.averageUPS = totalUPS / (double)NUM_FPS;
            }
            this.framesSkipped = 0;
            this.prevStatsTime = timeNow;
            this.statsInterval = 0;
        }
    }

    private void finishOff() {
        if (!this.finishedOff) {
            this.finishedOff = true;
            this.printStats();
        }
    }

    private void printOnScreen(Graphics dbg) {
        dbg.setColor(new Color(32, 244, 32));
        dbg.setFont(new Font("SansSerif", 1, 12));
        dbg.setColor(Color.black);
    }

    private void printStats() {
        System.out.println("Frame Count/Loss: " + this.frameCount + " / " + this.totalFramesSkipped);
        System.out.println("Average FPS: " + this.df.format(this.averageFPS));
        System.out.println("Average UPS: " + this.df.format(this.averageUPS));
        System.out.println("Time Spent: " + this.timeSpentInGame + " secs");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point mouse = e.getPoint();
        if (this.current_game_state == 0) {
            if (e.getButton() == 1) {
                MenuElement selected = this.main_menu.mousePressed(mouse);
                if (selected == null) {
                    return;
                }
                if (this.main_menu.current_menu == this.main_menu.menu_level_select) {
                    level_loading = true;
                    loading = true;
                    next_level = ((LevelToken)selected).getLevelNum();
                }
            }
        } else if (this.current_game_state == 2 && LevelCleared.show_menu && e.getButton() == 1 && this.current_level.level_clear_screen.mousePressed(mouse)) {
            this.makeLevelClearedMenuSelection();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (message_box) {
            message_box = false;
            return;
        }
        block0 : switch (e.getKeyCode()) {
            case 37: {
                if (key_left) break;
                key_left = true;
                switch (this.current_game_state) {
                    case 0: {
                        this.main_menu.cursorMoveUp();
                        this.main_menu.cursorMoveUp();
                        this.main_menu.cursorMoveUp();
                        this.main_menu.cursorMoveUp();
                        this.main_menu.cursorMoveUp();
                        break block0;
                    }
                    case 2: {
                        this.player.keyLeftIsDown(true);
                        break block0;
                    }
                }
                break;
            }
            case 38: {
                if (key_up) break;
                key_up = true;
                switch (this.current_game_state) {
                    case 0: {
                        this.main_menu.cursorMoveUp();
                        break block0;
                    }
                    case 2: {
                        if (!this.current_level.hasEnded() || !LevelCleared.show_menu) break block0;
                        this.current_level.level_clear_screen.cursorUp();
                        break block0;
                    }
                }
                break;
            }
            case 39: {
                if (key_right) break;
                key_right = true;
                switch (this.current_game_state) {
                    case 0: {
                        this.main_menu.cursorMoveDown();
                        this.main_menu.cursorMoveDown();
                        this.main_menu.cursorMoveDown();
                        this.main_menu.cursorMoveDown();
                        this.main_menu.cursorMoveDown();
                        break block0;
                    }
                    case 2: {
                        this.player.keyRightIsDown(true);
                        break block0;
                    }
                }
                break;
            }
            case 40: {
                if (key_down) break;
                key_down = true;
                switch (this.current_game_state) {
                    case 0: {
                        this.main_menu.cursorMoveDown();
                        break block0;
                    }
                    case 2: {
                        if (!this.current_level.hasEnded() || !LevelCleared.show_menu) break block0;
                        this.current_level.level_clear_screen.cursorDown();
                        break block0;
                    }
                }
                break;
            }
            case 65: {
                if (key_a) break;
                key_a = true;
                switch (this.current_game_state) {
                    case 0: {
                        MenuElement temp_element = this.main_menu.select();
                        switch (temp_element.getType()) {
                            case GAMESTART: {
                                ++saveData.total_played;
                                break block0;
                            }
                            case LEVELTOKEN: {
                                level_loading = true;
                                loading = true;
                                next_level = ((LevelToken)temp_element).getLevelNum();
                                break block0;
                            }
                            case QUIT: {
                                break block0;
                            }
							default:
								break;
                        }
                        break block0;
                    }
                    case 2: {
                        if (this.current_level.hasEnded()) {
                            this.makeLevelClearedMenuSelection();
                            break block0;
                        }
                        this.player.keyJumpIsDown(true);
                        break block0;
                    }
                }
                break;
            }
            case 83: {
                key_s = true;
                switch (this.current_game_state) {
                    case 0: {
                        this.main_menu.goBack();
                        break block0;
                    }
                }
                break;
            }
            case 68: {
                key_d = true;
                break;
            }
            case 81: {
                key_q = true;
                break;
            }
            case 87: {
                key_w = true;
                break;
            }
            case 82: {
                key_r = true;
                break;
            }
            case 70: {
                key_f = true;
                break;
            }
            case 27: {
                key_esc = true;
                if (this.current_game_state != 2 || this.current_level.level_ended) break;
                this.current_level.endLevelInFailure();
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        block0 : switch (e.getKeyCode()) {
            case 37: {
                key_left = false;
                switch (this.current_game_state) {
                    case 2: {
                        this.player.keyLeftIsDown(false);
                        break block0;
                    }
                }
                break;
            }
            case 38: {
                key_up = false;
                switch (this.current_game_state) {
                    case 2: {
                        break block0;
                    }
                }
                break;
            }
            case 39: {
                key_right = false;
                switch (this.current_game_state) {
                    case 2: {
                        this.player.keyRightIsDown(false);
                        break block0;
                    }
                }
                break;
            }
            case 40: {
                key_down = false;
                switch (this.current_game_state) {
                    case 2: {
                        break block0;
                    }
                }
                break;
            }
            case 65: {
                key_a = false;
                switch (this.current_game_state) {
                    case 2: {
                        this.player.keyJumpIsDown(false);
                        break block0;
                    }
                }
                break;
            }
            case 83: {
                key_s = false;
                break;
            }
            case 68: {
                key_d = false;
                break;
            }
            case 81: {
                key_q = false;
                break;
            }
            case 87: {
                key_w = false;
                break;
            }
            case 82: {
                key_r = false;
                break;
            }
            case 70: {
                key_f = false;
                break;
            }
            case 27: {
                key_esc = false;
                break;
            }
        }
    }

    public void makeLevelClearedMenuSelection() {
        if (LevelCleared.show_menu) {
            SoundClip.MENU_SELECT.play();
            int cursor = this.current_level.level_clear_screen.current_selection;
            if (cursor == 0) {
            	this.current_level.initializeLevel();
                this.current_level.resetLevel();
            } else if (cursor == 1) {
                this.nextLevel();
            } else if (cursor == 2) {
                int old_level = this.current_level.level_number;
                this.initMainMenu();
                this.main_menu.current_menu = this.main_menu.menu_level_select;
                this.main_menu.current_menu.changeSelect(old_level);
                this.main_menu.loadMapImage();
            }
        } else {
            this.current_level.level_clear_screen.skipIntro();
        }
    }

    public void nextLevel() {
// NOTE: Old code to unlock double jump upgrade after World 1. Left in for reference.
//        if (this.current_level.level_number == 4 && !saveData.unlocked_double_jump) {
//            saveData.unlocked_double_jump = true;
//            message_box = true;
//            Effects.message = "Unlocked Double Jump!";
//            this.initMainMenu();
//            this.main_menu.current_menu = this.main_menu.menu_level_select;
//            return;
//        }
    	
    	if (this.current_level.level_number == 24)
    	{
        	// Play fanfare and goto stage select after beating the final level
		    message_box = true;
		    Effects.message = "Game Complete!";
		    MP3Player.PlayBGM(BGMTracks.FANFARE);
		    this.initMainMenu();
		    this.main_menu.current_menu = this.main_menu.menu_level_select;
		    return;
    	} else {
    		// Load next level
	        int nextlevel = this.current_level.level_number + 1;
	        //System.out.println("Unloading level " + this.current_level.level_number + ". Loading level " + nextlevel + ".");
	        this.initStage(nextlevel);
    	}
    }

    static {
        message_box = false;
        loading = true;
        level_loading = false;
        next_level = -1;
        debugMode = false;
    }

}

