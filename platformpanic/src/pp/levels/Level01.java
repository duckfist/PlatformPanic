package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level01 extends Level {
    //private int current_room = 0;
    private static final String row1 =  "ggggggggg                                                 g";
    private static final String row2 =  "ggggggggg                                                 g";
    private static final String row3 =  "ggggggggg                                                 g";
    private static final String row4 =  "ggggggggg                                                 g";
    private static final String row5 =  "ggggggggg                                                 g";
    private static final String row6 =  "ggggggggg                                                 g";
    private static final String row7 =  "ggggggggg                                                 g";
    private static final String row8 =  "ggggggggg                                                 g";
    private static final String row9 =  "ggggggggg                           ggg                   g";
    private static final String row10 = "ggggggggg       D                                         g";
    private static final String row11 = "ggggggggg  B                    B         B               g";
    private static final String row12 = "ggggggggg                                    g            g";
    private static final String row13 = "gggggggggggg                                 g       gggggg";
    private static final String row14 = "gggggggggggg                                 ggg       gggg";
    private static final String row15 = "ggggggggggggggg                               gg        ggg";
    private static final String row16 = "ggggggggggggggg                               gg         gg";
    private static final String row17 = "gggggggggggggggggg                            gggg       gg";
    private static final String row18 = "ggggg                                         gggggg     gg";
    private static final String row19 = "ggg                  B                                   gg";
    private static final String row20 = "gg                                B                    B gg";
    private static final String row21 = "g                        gggg                            gg";
    private static final String row22 = "g                                                         g";
    private static final String row23 = "g                                   B              ggg  ! g";
    private static final String row24 = "gg                                                  g     g";
    private static final String row25 = "gg                                                  g     g";
    private static final String row26 = "gg                                   B           B  ggg   g";
    private static final String row27 = "gg                             ggg                    g   g";
    private static final String row28 = "gg         B   B    ss  ggggg   g            ggg   gggg   g";
    private static final String row29 = "gg                 gggg   gg  ! gg            g  ! g      g";
    private static final String row30 = "gggggggggggggg      gg     g     g           gg    ggg    g";
    private static final String row31 = "gggggggggggggggg    gg     gg   gggg         g       g    g";
    private static final String row32 = "gggggggggggggggg !  gg      g   g  g       gggg      g    g";
    private static final String row33 = "gggggggggggggggg    gg      g   gg g    ss g  g     gg    g";
    private static final String row34 = "gggggggggggggggg    gg    ggg    g ggggggggg gg     g     g";
    private static final String row35 = "gggggggggggggggg    gg    g      g   g   g   g      gg    g";
    private static final String row36 = "gggggggggggggggg    gg    g      g   g   g   g       g    g";
    private static final String row37 = "gggggggggggggggg    gg    gg     g   ggggg   g       g    g";
    private static final String row38 = "gggggggggggggggg    gg     g     g    g g    g       g    g";
    public static final String level_name = "Level 1-2";
    private Color[] colors;

    public Level01(Player player, Camera camera, int id_tileset, int level_number) {
        super("Level 1-2", player, camera, id_tileset, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(Level01.getMap(), this.ground_tileset);
        this.spawn_pos.setLoc(160.0f, 800.0f);
        
        MP3Player.PlayBGM(BGMTracks.WORLD1);
    }

    @Override
    public String[] getTileMap() { return getMap(); }
    public static String[] getMap() {

    	String[] definitions = new String[]{row1, 
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
    			row32,
    			row33,
    			row34,
    			row35,
    			row36,
    			row37,
    			row38};
    	
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

