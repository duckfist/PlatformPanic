package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level20 extends Level {
    //private int current_room = 0;
    private static final String row1 =  "gg                                   xgggggg";
    private static final String row2 =  "gg                                   xgggggg";
    private static final String row3 =  "gg                         xx        xgggggg";
    private static final String row4 =  "gg             gg        2 gg        xgggggg";
    private static final String row5 =  "gg            ggggg      x gg  ss    xgggggg";
    private static final String row6 =  "gg           gggggg    2 ggggxxxx    xgggggg";
    private static final String row7 =  "gg          ggggggg     xgggg        xgggggg";
    private static final String row8 =  "gg        xggggggggg    xgggg        xgggggg";
    private static final String row9 =  "gg        xggggg        xgggg D      xgggggg";
    private static final String row10 = "gg        xgggg         xgggg         xxxggg";
    private static final String row11 = "gg        xggggx      2  gggg            ggg";
    private static final String row12 = "gg        xggggx      ggggggg     D      ggg";
    private static final String row13 = "gg        xggggx      g     g            ggg";
    private static final String row14 = "gg        xgggx     2 g     g            ggg";
    private static final String row15 = "gg        xgggx      gg B g g            ggg";
    private static final String row16 = "gg        xgggx      gg   g gg           ggg";
    private static final String row17 = "gg        xgggx      gg   g g            ggg";
    private static final String row18 = "gg        xgg     2 gg  B g g            ggg";
    private static final String row19 = "gg        xgg       gg    g      gg      ggg";
    private static final String row20 = "gg        xgg       gg    g      ggggg   ggg";
    private static final String row21 = "gg        xgg  2    gg  B ggg A  ggggg   ggg";
    private static final String row22 = "gg        xg        gg     gg       gg   ggg";
    private static final String row23 = "gg        xg        gg     gg       gg   ggg";
    private static final String row24 = "gg        xg  A            gg     D gg   ggg";
    private static final String row25 = "gg        xg        gg     gg       gg   ggg";
    private static final String row26 = "gg        xgg       gg     gg       gg   ggg";
    private static final String row27 = "gg        xgg   D          gg    1  gg   ggg";
    private static final String row28 = "gg        xgg       gg     gg       gg   ggg";
    private static final String row29 = "gg        xgg       gg     gg            ggg";
    private static final String row30 = "gg        xggg     D       gg   1        ggg";
    private static final String row31 = "gg        xggg      gg     gg       gg   ggg";
    private static final String row32 = "gg          gg      gg    ggg       gg   ggg";
    private static final String row33 = "gg         xggg           gggggggggggg   ggg";
    private static final String row34 = "gg         xgggg        gggggggggggggg   ggg";
    private static final String row35 = "gg             ggg      gggggggggggg     ggg";
    private static final String row36 = "gg               ggggggggggggggggggg     ggg";
    private static final String row37 = "gg                                       ggg";
    private static final String row38 = "gg                                     W ggg";
    private static final String row39 = "gg                       2             ggggg";
    private static final String row40 = "gg                               2       ggg";
    private static final String row41 = "gg               2                       ggg";
    private static final String row42 = "gg                                       ggg";
    private static final String row43 = "gg                                       ggg";
    private static final String row44 = "gg]                                      ggg";
    private static final String row45 = "ggg]                                 ss  ggg";
    private static final String row46 = "gggg]                               [gg  ggg";
    private static final String row47 = "ggggg]                              gg   ggg";
    private static final String row48 = "gggggg]                                  ggg";
    private static final String row49 = "    ggg]    2                            ggg";
    private static final String row50 = "    gggg                                 ggg";
    private static final String row51 = "    gggg                               ssggg";
    private static final String row52 = "    gggg                             ggggggg";
    private static final String row53 = "    ggggss                2          ggggggg";
    private static final String row54 = "    gggggg          ]                ggggggg";
    private static final String row55 = "    gggggg          g]  xxxx     ssssggggggg";
    private static final String row56 = "    ggggggssss      gg xggggx    ggggggggggg";
    private static final String row57 = "    gggggggggg  xx  gg xggggx    ggggggggggg";
    private static final String row58 = "    gggggg  gg  xx  gg xgggggx  sggggggggggg";
    private static final String row59 = "    gggggg  gg  xx  gg xgggggx  gggggggggggg";
    private static final String row60 = "    gggggg  gg  xx  gg xgggggx   ggggggggggg";
    private static final String row61 = "    gggggg  gg  xx  gg xgggggx   ggggggggggg";
    private static final String row62 = "    ggggggx  gg xx ggg xgggggx   ggggggggggg";
    private static final String row63 = "    gg xggx  gg xx ggg xgggggx   ggggggggggg";
    private static final String row64 = "    gg xggx  gg xx ggg xgggggx   ggggggggggg";
    private static final String row65 = "    gg xggx  gg xx ggg xgggggx   ggggggggggg";
    private static final String row66 = "    gg xggx  gg xx ggg xgggggx   ggggggggggg";
    private static final String row67 = "    gg xggx  gg xx ggg xgggggx   ggggggggggg";
    public static final String level_name = "Level 5-1";
    private Color[] colors;

    public Level20(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 5-1", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), tile_map);
        this.spawn_pos.setLoc(192.0f, 334.0f);

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
        		row41,
        		row42,
        		row43,
        		row44,
        		row45,
        		row46,
        		row47,
        		row48,
        		row49,
        		row50,
        		row51,
        		row52,
        		row53,
        		row54,
        		row55,
        		row56,
        		row57,
        		row58,
        		row59,
        		row60,
        		row61,
        		row62,
        		row63,
        		row64,
        		row65,
        		row66,
        		row67,
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

