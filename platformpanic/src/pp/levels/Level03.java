package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level03 extends Level {
    //private int current_room = 0;
    private static final String row1  = "ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg";
    private static final String row2  = "gggggggg         xggggggx        xgggx     xgggx                             ";
    private static final String row3  = "ggggg              gggg            g        ggg                              ";
    private static final String row4  = "ggg                 gg                      ggg                              ";
    private static final String row5  = "gg                                           g        B                      ";
    private static final String row6  = "g                                       B    g              B                ";
    private static final String row7  = "g                             B              g    B                          ";
    private static final String row8  = "g                                    B                        B              ";
    private static final String row9  = "g               B           g                      B                         ";
    private static final String row10 = "gg          B               g  B                                             ";
    private static final String row11 = "ggggggggggggg               g                                                ";
    private static final String row12 = "g g g g g g ggg             g                                                ";
    private static final String row13 = "ggggggggggggg ggggggg s     g    s     !     s     !     B  s  B   !         ";
    private static final String row14 = "g g g g g g g g g g ggg  !  B    g           g              g                ";
    public static final String level_name = "Level 1-4";
    private Color[] colors;

    public Level03(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 1-4", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(Level03.getMap(), this.ground_tileset);
        this.spawn_pos.setLoc(96.0f, 256.0f);
        
        MP3Player.PlayBGM(BGMTracks.WORLD1);
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

