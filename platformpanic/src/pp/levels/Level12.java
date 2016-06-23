package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level12
extends Level {
    //private int current_room = 0;
    private static final String row1  = "gg ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg                                                                                              gg";
    private static final String row2  = "gg ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg                                                                                              gg";
    private static final String row3  = "gg                                                                                                                                                                            gg";
    private static final String row4  = "gg                                                                                                  E  E     E  E  H                          G                               gg";
    private static final String row5  = "gg                                                   g                                                                                     H                                  gg";
    private static final String row6  = "gg                         g                       G g                                                                G  F  E  H  G     E              G     F                gg";
    private static final String row7  = "gg                       F g                         g                                        E  H  F     H  G                       F                    G                   gg";
    private static final String row8  = "gg                  g      g               g         g        g                       gggggg                                                                                  gg";
    private static final String row9  = "gg                  g      g               g       H g        g                   gggggggggg                                                     F  E  H                      gg";
    private static final String row10 = "gg                H g    G g              E          g       E                                                                             s                                  gg";
    private static final String row11 = "gg                  g      g               g         g        g                                                g                           g                           1      gg";
    private static final String row12 = "ggggggggggggggggggggg   gggg    ggggggg    gggggg    ggggg    gggg  gggg  ggggggggggggggggggggggggggggggggggggggxxxxxxxxxxxxxxxxxxxxxxxxxxxg                    ggggggggggg   gg";
    private static final String row13 = "ggggggggggggggggggggg   gggg    ggggggg    gggggg    ggggg    gggg  gggg  gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg                    ggggggggggg   gg";
    private static final String row14 = "ggggggggggggggggggggg   gggg    ggggggg    gggggg    ggggg    gggg  gggg  gggggggggggggggggggggggggggggggggggggg                                                ggggggggggg   gg";
    public static final String level_name = "Level 3-3";
    private Color[] colors;

    public Level12(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 3-3", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), this.ground_tileset);
        this.spawn_pos.setLoc(128.0f, 128.0f);
        
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

