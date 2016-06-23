package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level10 extends Level {
    //private int current_room = 0;
    private static final String row1  = "                                      gggggg";
    private static final String row2  = "   ggggg                                   g";
    private static final String row3  = " ggggg                        G            g";
    private static final String row4  = "ggg                   F    1      H        g";
    private static final String row5  = "gg                G      1                 g";
    private static final String row6  = "gg            G        1                   g";
    private static final String row7  = "gg        H          1            F        g";
    private static final String row8  = "gg    E            1                       g";
    private static final String row9  = "gg              1  ggg             ggg     g";
    private static final String row10 = "gggggg       1     gggg            ggg     g";
    private static final String row11 = "gggggg   1       gggggg            ggg     g";
    private static final String row12 = "gggggggggggggggggggggg            gggg     g";
    private static final String row13 = "g                ggggg            gggg     g";
    private static final String row14 = "g                                 gggg     g";
    private static final String row15 = "g                                ggggg     g";
    private static final String row16 = "g         G gg                   ggggg     g";
    private static final String row17 = "g     H     gg                     ggg     g";
    private static final String row18 = "g E         gggg                           g";
    private static final String row19 = "g           gggg                           g";
    private static final String row20 = "g           ggggg      F                   g";
    private static final String row21 = "g F         ggggg                          g";
    private static final String row22 = "g           gggggggg       E            W  g";
    private static final String row23 = "g           gggggggg                G   g  g";
    private static final String row24 = "gggggggggggggggggggg          H            g";
    private static final String row25 = "gggggggggggggggggggg                       g";
    public static final String level_name = "Level 3-1";
    private Color[] colors;

    public Level10(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 3-1", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), tile_map);
        this.spawn_pos.setLoc(144.0f, 672.0f);
        
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

