package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level00 extends Level {
    //private int current_room = 0;
    private static final String row1 = "gg                  gggggggggg";
    private static final String row2 = "gg                          gg";
    private static final String row3 = "g                            g";
    private static final String row4 = "g                            g";
    private static final String row5 = "g            gg         B    g";
    private static final String row6 = "g  !   !          *          g";
    private static final String row7 = "g                 *  g       g";
    private static final String row8 = "g gg   gg         *  g     B g";
    private static final String row9 = "ggggggggggg     R *  g       g";
    private static final String row10 = "gg ggggg gg     ***  g      gg";
    private static final String row11 = "gggggggggggg         g       g";
    private static final String row12 = "g       gggggggggggggg  B    g";
    private static final String row13 = "g       gggggggggggggg       g";
    private static final String row14 = "g        ggggggggggggg      gg";
    private static final String row15 = "g          gggggggggg       gg";
    private static final String row16 = "g   B      ggggggggg       sgg";
    private static final String row17 = "g           ggg        B   ggg";
    private static final String row18 = "g                          ggg";
    private static final String row19 = "g                         gggg";
    private static final String row20 = "g                         gggg";
    private static final String row21 = "g        B                gggg";
    private static final String row22 = "g  sss  ggg        B     ggggg";
    private static final String row23 = "g  ggg   g    B   ggg  ggggggg";
    private static final String row24 = "g   g    g   ggg  ggg gggggggg";
    private static final String row25 = "gggggggggggggggggggggggggggggg";
    public static final String level_name = "Level 1-1";
    private Color[] colors;
    
    public Level00(Player player, Camera camera, int id_tileset, int level_number) {
        super("Level 1-1", player, camera, id_tileset, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), id_tileset);
        this.spawn_pos.setLoc(160.0f, 128.0f);
        
        MP3Player.PlayBGM(BGMTracks.WORLD1);
    }

    public static String[] getMap() {
    	
        return new String[]{row1,row2,row3,row4,row5,row6,row7,row8,row9,row10,row11,row12,row13,row14,row15,row16,row17,row18,row19,row20,row21,row22,row23,row24,row25};
    }
    
    @Override
    public String[] getTileMap() { return getMap(); }

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

