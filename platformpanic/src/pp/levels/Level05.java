package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level05
extends Level {
    //private int current_room = 0;
    private static final String row1 = "gggggggggggggggggggggggggggggg";
    private static final String row2 = " g   g                      gg";
    private static final String row3 = " g   g                       g";
    private static final String row4 = " g   g                       g";
    private static final String row5 = " g   g    B         B        g";
    private static final String row6 = " g   g                       g";
    private static final String row7 = " g   g                   B   g";
    private static final String row8 = " g 4 gg        5             g";
    private static final String row9 = " g   g                       g";
    private static final String row10 = " g   g                     ggg";
    private static final String row11 = " g   g    gggg  2  g         g";
    private static final String row12 = "g        gggggg   gg      1  g";
    private static final String row13 = "g     !  gggggg   gg  B      g";
    private static final String row14 = "g       ggggggg 2 gg      1  g";
    private static final String row15 = "g         ggggg   gg  B      g";
    private static final String row16 = "g             g   gg      1  g";
    private static final String row17 = "g               2 gg  B      g";
    private static final String row18 = "g                 gg      1  g";
    private static final String row19 = "g             gggg           g";
    private static final String row20 = "g                          ggg";
    private static final String row21 = "g                          ggg";
    private static final String row22 = "gg D                       ggg";
    private static final String row23 = "g                    gg !!    ";
    private static final String row24 = "g    !!                       ";
    private static final String row25 = "g          g    1             ";
    private static final String row26 = "g          g   g              ";
    private static final String row27 = "g          g   g              ";
    private static final String row28 = "g          g   g              ";
    private static final String row29 = "g          g   g              ";
    private static final String row30 = "g          g   g              ";
    private static final String row31 = "g          g   g              ";
    private static final String row32 = "g            2                ";
    private static final String row33 = "g                             ";
    private static final String row34 = "g                             ";
    private static final String row35 = "g            2                ";
    private static final String row36 = "g                             ";
    private static final String row37 = "g                             ";
    private static final String row38 = "g            1                ";
    private static final String row39 = "g                             ";
    private static final String row40 = "g                             ";
    private static final String row41 = "g                             ";
    public static final String level_name = "Level 2-1";
    private Color[] colors;

    public Level05(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 2-1", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), tile_map);
        this.spawn_pos.setLoc(96.0f, 128.0f);
        
        MP3Player.PlayBGM(BGMTracks.WORLD2);
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
        		row32,
        		row33,
        		row34,
        		row35,
        		row36,
        		row37,
        		row38,
        		row39,
        		row40,
        		row41,
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

