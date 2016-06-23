package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level19 extends Level {
    //private int current_room = 0;
    private static final String row1  = "gg                       gx     xg                        g";
    private static final String row2  = "gg                       gx     xg      [[                g";
    private static final String row3  = "gg                       gx     xg                        g";
    private static final String row4  = "gg            E    B     gx     xg             2          g";
    private static final String row5  = "gg       E               gx     xg  D                     g";
    private static final String row6  = "gg                       gx     xg                        g";
    private static final String row7  = "gg   E                   gx     xg ssss                   g";
    private static final String row8  = "gg                       gx     xg gggg                   g";
    private static final String row9  = "g x                      gx     xg                        g";
    private static final String row10 = "g gx  F                  g       g                        g";
    private static final String row11 = "g ggx                                                  [  g";
    private static final String row12 = "gxxggx   G                                          [[[g  g";
    private static final String row13 = "ggxxggx                                             gggg  g";
    private static final String row14 = "gggxxggx   H             g       g                        g";
    private static final String row15 = "ggggxxggx                gx     xg                        g";
    private static final String row16 = "ggggg xggx   5           gx     xg]]]                     g";
    private static final String row17 = "g   g             1      gx     xgggg]    B               g";
    private static final String row18 = "g   g                  1 gx     xg  gg                    g";
    private static final String row19 = "g 1                      gx     xg                        g";
    private static final String row20 = "g                        gx     xg                        g";
    private static final String row21 = "g                    1   g       g                        g";
    private static final String row22 = "g 4                                                       g";
    private static final String row23 = "g                1                                     [[[g";
    private static final String row24 = "g            1                                         gggg";
    private static final String row25 = "g   6   8                                                 g";
    private static final String row26 = "g                                                         g";
    private static final String row27 = "g        !               [[  5  ]]                 sss    g";
    private static final String row28 = "g                        gg  !  gg                 ggg    g";
    private static final String row29 = "g                        gg     gg                 g      g";
    private static final String row30 = "g                                                  ggg    g";
    private static final String row31 = "g                                                    g    g";
    private static final String row32 = "g                                                    g    g";
    private static final String row33 = "g                                                   gg    g";
    private static final String row34 = "g                                                   g     g";
    private static final String row35 = "g                                                   gg    g";
    private static final String row36 = "g                                                    g    g";
    private static final String row37 = "g                                                    g    g";
    private static final String row38 = "g                                                    g    g";
    public static final String level_name = "Level 4-5";
    private Color[] colors;

    public Level19(Player player, Camera camera, int tile_map, int level_number) {
        super(level_name, player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), this.ground_tileset);
        this.spawn_pos.setLoc(928.0f, 64.0f);

        MP3Player.PlayBGM(BGMTracks.WORLD4);
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

