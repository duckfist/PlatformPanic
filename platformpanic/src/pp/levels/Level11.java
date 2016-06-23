package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level11 extends Level {
    //private int current_room = 0;
    private static final String row1 = " ggggggggggggggg";
    private static final String row2 = " g   g         g";
    private static final String row3 = " g   g         g";
    private static final String row4 = " g   g B       g";
    private static final String row5 = " g S g       A g";
    private static final String row6 = " g   g         g";
    private static final String row7 = " g   g         g";
    private static final String row8 = " g   g       H g";
    private static final String row9 = " g   g         g";
    private static final String row10 = " g   g F       g";
    private static final String row11 = "gg   g         g";
    private static final String row12 = "g    g         g";
    private static final String row13 = "g    gg        g";
    private static final String row14 = "g    gg    F   g";
    private static final String row15 = "g    gg        g";
    private static final String row16 = "g    gg        g";
    private static final String row17 = "g    gg     G  g";
    private static final String row18 = "g    gg        g";
    private static final String row19 = "g  gggg H      g";
    private static final String row20 = "g  g           g";
    private static final String row21 = "g  g        A  g";
    private static final String row22 = "g              g";
    private static final String row23 = "g              g";
    private static final String row24 = "g D           gg";
    private static final String row25 = "g             gg";
    public static final String level_name = "Level 3-2";
    private Color[] colors;

    public Level11(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 3-2", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), this.ground_tileset);
        this.spawn_pos.setLoc(128.0f, 64.0f);
        
        MP3Player.PlayBGM(BGMTracks.WORLD3);
    }

    @Override
    public String[] getTileMap() { return getMap(); }
    public static String[] getMap() {
    	String[] definitions = new String[]{
        		row1 ,
        		row2 ,
        		row3 ,
        		row4 ,
        		row5 ,
        		row6 ,
        		row7 ,
        		row8 ,
        		row9 ,
        		row10,
        		row11,
        		row12,
        		row13,
        		row14,
        		row15,
        		row16,
        		row17,
        		row18,
        		row19,
        		row20,
        		row21,
        		row22,
        		row23,
        		row24,
        		row25,
        };
        return definitions;
    }

    private void initializeColorArray() {
        this.colors = new Color[255];
        for (int i = 0; i < 255; ++i) {
            this.colors[i] = new Color(112 - i / 3, 112 - i / 3, 76 - i / 6);
        }
    }

    @Override
    public void paintLevel(Graphics g, Player player) {
        super.paintLevel(g, player);
    }
}

