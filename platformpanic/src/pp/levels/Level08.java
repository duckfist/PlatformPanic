package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level08 extends Level {
    //private int current_room = 0;
    private static final String row1  = "                                                ggggggggggggggggggggggggggggggg xxxxxxxxxxxxxxxxx";
    private static final String row2  = "                                                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                  ";
    private static final String row3  = "                          s                                                                     g";
    private static final String row4  = "                          g                                                                     g";
    private static final String row5  = "                     x    u              2                                                      g";
    private static final String row6  = "                     x                   g              x                 2 s 2 s 2 s 2 s 2  gggg";
    private static final String row7  = "                     x                                  x   x   x           g   g   g   g        ";
    private static final String row8  = "                     x                                2 x 2 x 2 x 2 s 2 s 2 g   g   g   g        ";
    private static final String row9  = "                     x                            xxxxxxxxxxxxxxxxxxxxxxxxx g   g   g   g        ";
    private static final String row10 = "                     x    s                   s                             g   g   g   g        ";
    private static final String row11 = "               2     x    g                   g                             g   g   g   g        ";
    private static final String row12 = "                     x           s                                          g   g   g   g        ";
    private static final String row13 = "                     x           g                                          g   g   g   g        ";
    private static final String row14 = "                                                                            g   g   g   g        ";
    public static final String level_name = "Level 2-4";
    private Color[] colors;

    public Level08(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 2-4", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), this.ground_tileset);
        this.spawn_pos.setLoc(32.0f * 95.0f, 92.0f);
        
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

