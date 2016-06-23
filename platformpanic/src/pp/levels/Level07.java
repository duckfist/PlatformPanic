package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level07 extends Level {
    //private int current_room = 0;
    private static final String row1  = "g        xgx      xgx      xgx     ggggggggg";
    private static final String row2  = "g        xgx      xgx      xgx             g";
    private static final String row3  = "g        xgx      xgx      xgx             g";
    private static final String row4  = "g         x        x        x              g";
    private static final String row5  = "g                                          g";
    private static final String row6  = "g                                          g";
    private static final String row7  = "g                                          g";
    private static final String row8  = "g                                    ggggggg";
    private static final String row9  = "g                                 ggggxxxxxg";
    private static final String row10 = "g        2      2          3   gggg   xggggg";
    private static final String row11 = "g  gg                ggg              xggggg";
    private static final String row12 = "g  g xxxxxxxxxxxxxxxx x xxxxxxxxxxxxxx ggggg";
    private static final String row13 = "g    ggggggggggggggggggggggggggggggggggggggg";
    private static final String row14 = "g                         ggg            ggg";
    private static final String row15 = "g                         xxx            ggg";
    private static final String row16 = "g                                        ggg";
    private static final String row17 = "g                                        ggg";
    private static final String row18 = "g                                        ggg";
    private static final String row19 = "g                                        ggg";
    private static final String row20 = "g      ggg     B                gggggg   ggg";
    private static final String row21 = "gggggggggg    g                ggggggg   ggg";
    private static final String row22 = "g        gx  xgx  B            xxxx gg   ggg";
    private static final String row23 = "gg       gx  xgx                   xgg   ggg";
    private static final String row24 = "gg       gx  xgx                   xgg   ggg";
    private static final String row25 = "gg       gx  xgx    B      S       xgg   ggg";
    private static final String row26 = "gg       gx  xgx                   xgg   ggg";
    private static final String row27 = "gg       gx  xgx               B   xgg   ggg";
    private static final String row28 = "gg       gx  xgx   B               xgg   ggg";
    private static final String row29 = "gg       gx  xgx                   xgg   ggg";
    private static final String row30 = "gg       gx  xgx       B   gg    1 xgg   ggg";
    private static final String row31 = "gg       gx  xgx                   xgg   ggg";
    private static final String row32 = "ggg      g xx g xxxxxxxxxxxxxxxxxxx gg   ggg";
    private static final String row33 = "ggg      ggggggggggggggggggggggggggggg   ggg";
    private static final String row34 = " gg      ggggggggggggggggggggggggggggg   ggg";
    private static final String row35 = " gg      ggggggggggggggggggggggggggggg   ggg";
    private static final String row36 = " ggg     ggggggggggggggggggggggggggggg   ggg";
    private static final String row37 = " ggg     ggggggggggggggggggggggggggggg   ggg";
    private static final String row38 = "  gg     ggggggggggggggggggggggggggggg   ggg";
    private static final String row39 = "  gg              gggggggggggggggg   g   ggg";
    private static final String row40 = "  ggg             gggggggggggggggg       ggg";
    private static final String row41 = "  ggg             gggggggggggg   g       ggg";
    private static final String row42 = "   gg             gggggggggggg     W ggggggg";
    private static final String row43 = "   gg             gggggggg   g       ggggggg";
    private static final String row44 = "   ggg            gggggggg     W g   ggggggg";
    private static final String row45 = "   ggg            gggg   g        xxx    ggg";
    private static final String row46 = "    gg            gggg     W g    xxx    ggg";
    private static final String row47 = "    gg            gggg        xxx xxx    ggg";
    private static final String row48 = "    gg            gggg W g    xxx xxx    ggg";
    private static final String row49 = "    gg            ggg  1  xxx xxx xxx    ggg";
    public static final String level_name = "Level 2-3";
    private Color[] colors;

    public Level07(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 2-3", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), tile_map);
        this.spawn_pos.setLoc(1268.0f, 128.0f);
        
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
        		row42,
        		row43,
        		row44,
        		row45,
        		row46,
        		row47,
        		row48,
        		row49,
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

