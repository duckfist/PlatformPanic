package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level14 extends Level {
    //private int current_room = 0;
    private static final String row1 = "                                                                               g";
    private static final String row2 = "xxxxx                                                                          g";
    private static final String row3 = "gggggx                           5                                             g";
    private static final String row4 = "g   gx                g                                                      A g";
    private static final String row5 = "g   gx                         xxxxx                                        gggg";
    private static final String row6 = "g   gx                        xgggggx        A                              gggg";
    private static final String row7 = "g   gx                         x g x         x                             ggggg";
    private static final String row8 = "gggggx                           x          xgx                      A   ggg ggg";
    private static final String row9 = "xxxxx                 x                                                ggg   ggg";
    private static final String row10 = "g                                                              A  s    ggggggggg";
    private static final String row11 = "g                                                                 ggggggggxgxggg";
    private static final String row12 = "g          xxxxxxxxxxxx                                  s  xxxxxx ggggggxgxgggg";
    private static final String row13 = "g              x      xxxxxx                           xxxxxx x    ggggggggggggg";
    private static final String row14 = "g          g  xgx          xxxxxx               xxxxxxxx     xgx             ggg";
    private static final String row15 = "g          g   x          x     xxxxxx       xxx        x     x              ggg";
    private static final String row16 = "g  1  1  1 g       x     xgx         xxx   xxx  gg     xgx                   ggg";
    private static final String row17 = "g          g      xgx     x                     xgggg   x                    ggg";
    private static final String row18 = "g          g       x                               xgggg                      gg";
    private static final String row19 = "g 1  1  1  g                                          xgggg                    g";
    private static final String row20 = "g                                          ggg           xgggg            gggggg";
    private static final String row21 = " xxx     xxx                         gggggggggx 1           xgggg        ggxxxgg";
    private static final String row22 = " xxx     xxx                             xg  gx    1           xgggg    ggx   xg";
    private static final String row23 = " xxx     xxx                           xggggggx   x   1           xggggggx     x";
    private static final String row24 = " xxx     x        A                      xg  gx  xgx     1         xgggx        ";
    private static final String row25 = " xxx      xx                           xggggggx   x     x   1      xggx     B   ";
    private static final String row26 = " xxx                               A     xg  gx        xgx     4  xxggx     x   ";
    private static final String row27 = " xxx   D                               xggggggx         x     x   xggx     xgx  ";
    private static final String row28 = " xxx                                     xg  gx              xgx xggx      xgx  ";
    private static final String row29 = " xxx D                                 xggggggx             3 x   xx       xggx ";
    private static final String row30 = " xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxg    gx         2                  xgggx";
    private static final String row31 = "ggggggggggggggggggggggggggggggggggggggggg    gx   x                        xgggx";
    private static final String row32 = "                                             gx  xgx        1              xgggx";
    private static final String row33 = "                                             gx  xgx    x      1           xgggx";
    private static final String row34 = "                                             gx  xgx   xgx        1        xgggx";
    private static final String row35 = "                                             gx  xgx   xgx    x      1     xgggx";
    private static final String row36 = "                                             gx  xgx   xgx   xgx        1  xgggx";
    private static final String row37 = "                                             gx  xgx   xgx   xgx    x      xgggx";
    private static final String row38 = "                                             gx  xgx   xgx   xgx   xgx     xgggx";
    private static final String row39 = "                                             gx  xgx   xgx   xgx   xgx     xgggx";
    private static final String row40 = "                                             gx  xgx   xgx   xgx   xgx     xgggx";
    private static final String row41 = "                                             gx  xgx   xgx   xgx   xgx  W  xgggx";
    private static final String row42 = "                                             gx  xgx   xgx   xgx   xgx     xgggx";
    private static final String row43 = "                                             gx  xgx   xgx   xgx   xgx     xgggx";
    private static final String row44 = "                                             gx  xgx   xgx   xgx   xgx     xgggx";
    private static final String row45 = "                                             gx  xgx   xgx   xgx   xgx     xgggx";
    private static final String row46 = "                                             gx  xgx   xgx   xgx   xgx     xgggx";
    public static final String level_name = "Level 3-5";
    private Color[] colors;

    public Level14(Player player, Camera camera, int tile_map, int level_number) {
        super(level_name, player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), tile_map);
        this.spawn_pos.setLoc(2324.0f, 128.0f);

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

