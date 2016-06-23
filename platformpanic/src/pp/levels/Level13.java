package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level13 extends Level {
    //private int current_room = 0;
    private static final String row1 = "gggggggggggggggg                        g";
    private static final String row2 = "g   gg        gg                        g";
    private static final String row3 = "g   gg        gg                        g";
    private static final String row4 = "g   gg        gg                        g";
    private static final String row5 = "g   gggggggggggg                        g";
    private static final String row6 = "g              g                        g";
    private static final String row7 = "g              g       2  E  G  2       g";
    private static final String row8 = "g F E H G F E  g                        g";
    private static final String row9 = "gxxxxxxxxxxxg  g                        g";
    private static final String row10 = "ggggggggggggg  g       2  G  E  2       g";
    private static final String row11 = "g           g  g                        g";
    private static final String row12 = "g           g  gsss                  sssg";
    private static final String row13 = "g  H E F G  g  gggg       H  F       gggg";
    private static final String row14 = "g     xx       gggg                  gggg";
    private static final String row15 = "g   x xx       gggg]                [gggg";
    private static final String row16 = "g   gx       A ggggg      F  H      ggggg";
    private static final String row17 = "g   gxxxxxxxxxxg      ss        ss      g";
    private static final String row18 = "g   gggggggggggg      gg        gg      g";
    private static final String row19 = "g                                       g";
    private static final String row20 = "g                                       g";
    private static final String row21 = "g                                       g";
    private static final String row22 = "g      G  E  G  E  G  E    ss           g";
    private static final String row23 = "g                          gg           g";
    private static final String row24 = "g D                                     g";
    private static final String row25 = "g                                       g";
    private static final String row26 = "g        H       H       H              g";
    private static final String row27 = "g    F       F       F                  g";
    private static final String row28 = "g                                       g";
    private static final String row29 = "g                                       g";
    private static final String row30 = "g                                       g";
    private static final String row31 = "g                                       g";
    public static final String level_name = "Level 3-4";
    private Color[] colors;

    public Level13(Player player, Camera camera, int tile_map, int level_number) {
        super(level_name, player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), this.ground_tileset);
        this.spawn_pos.setLoc(64.0f, 64.0f);
        
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
        		row26,
        		row27,
        		row28,
        		row29,
        		row30,
        		row31,
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

