package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.entities.SpikeFill;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level24 extends Level {
    //private int current_room = 0;
    private static final String row1  = "g                                                      gXg";
    private static final String row2  = "g                                                      g g";
    private static final String row3  = "g                                                      g g";
    private static final String row4  = "g]                                                     g g";
    private static final String row5  = "gg                     ]                               g g";
    private static final String row6  = "g    1                 g                               g g";
    private static final String row7  = "g                                     ggg              g g";
    private static final String row8  = "g                                     ggg              g g";
    private static final String row9  = "g        g       A                    ggg              g g";
    private static final String row10 = "g        g                            ggg              g g";
    private static final String row11 = "g        u                            ggg   1          g g";
    private static final String row12 = "g                       A      gggggggggg              g g";
    private static final String row13 = "g                                       g              g g";
    private static final String row14 = "g                              gggggggg g   1          g g";
    private static final String row15 = "g        ]          gggggggggggggggg    g              g g";
    private static final String row16 = "g                   g   g          g gggg              g g";
    private static final String row17 = "g                   g g g ggg  ggg g    g   1          g g";
    private static final String row18 = "g               ggggg g g g      g gggg g          1   g g";
    private static final String row19 = "g               g     g  Xg      gX     g   1          g g";
    private static final String row20 = "g        s     gg ggggggggg      gggggg g          1   g g";
    private static final String row21 = "gs       g        gggg                g g   1          g g";
    private static final String row22 = "gg             ggggggg                g g              g g";
    private static final String row23 = "g               ggguuu                  g              g g";
    private static final String row24 = "g               ggg                     g        1     g g";
    private static final String row25 = "g 1             ggg          gg       g g              gXg";
    private static final String row26 = "g               ggg                   g g              g g";
    private static final String row27 = "g               ggg   ggggg      gggggg ggggggg  ggggggg g";
    private static final String row28 = "g               ggg 3 g  Xg      gX     ggggggg  ggggggg g";
    private static final String row29 = "g               ggg   g g g      g gggggggggggg  ggggggg g";
    private static final String row30 = "g               ggg   g g g 1    g g    ggggggg  ggggggg g";
    private static final String row31 = "g              [ggg   g g g      g g gg ggggggg  ggggggg g";
    private static final String row32 = "g              gggg   g g g      g g                   g g";
    private static final String row33 = "g               x     g g g    1 g gggg ggg ggggg gggggg g";
    private static final String row34 = "g                     g g gss  ssg g          g        g g";
    private static final String row35 = "g                   A g g ggg  ggg g gggg ggg gg gggg gg g";
    private static final String row36 = "g]                      g          g          g        g g";
    private static final String row37 = "gg                    gggggggggg g ggggggggg gg ggg gg g g";
    private static final String row38 = "gg               [    g        g g           g         g g";
    private static final String row39 = "Xggggggg g       g    g        g gggggggggggggggggg gggg g";
    private static final String row40 = "         g       g    g        g                         g";
    public static final String level_name = "Level 5-5";
    private Color[] colors;

    public Level24(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 5-5", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), tile_map);
//        this.spawn_pos.setLoc(44f * 32f - 16f, 20f * 32f);
        this.spawn_pos.setLoc(30f * 32f - 16f, 22f * 32f);
        SpikeFill.tick_delay = 30;
        MP3Player.PlayBGM(BGMTracks.WORLD5);
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

