package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level09 extends Level {
    //private int current_room = 0;
    private static final String row1 = "ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg";
    private static final String row2 = "g                                                                  xggg";
    private static final String row3 = "g                                                                   ggg";
    private static final String row4 = "g                                              A g                  ggg";
    private static final String row5 = "g                             2 g     g      x       g              ggg";
    private static final String row6 = "g                         2     g  1  g      x       g              ggg";
    private static final String row7 = "g                     2         gx   xg            A                ggg";
    private static final String row8 = "g                 2             gx   xg          x                  ggg";
    private static final String row9 = "g                             sggx   xggs              A      ggg   ggg";
    private static final String row10 = "g            2               gg         gg                    ggg   ggg";
    private static final String row11 = "g           ggg              ggx       xgg                ggggggg   ggg";
    private static final String row12 = "g      gg   ggg              ggx       xgg     s          ggggggg   ggg";
    private static final String row13 = "g      gg   ggggggggggggg    ggx       xgg     g           ggggggsssggg";
    private static final String row14 = "g      gg     xxxxxxxxx ggs  ggx       xggs    g            ggggg   ggg";
    private static final String row15 = "g     ggg               gggggggx       xgggggggggggggg     gggggg   ggg";
    private static final String row16 = "g  S  ggg                xxxxxx         xxxxxxxxxxxxx        gggg   ggg";
    private static final String row17 = "gg    ggg                                                    gggg   ggg";
    private static final String row18 = "gg    gggs                                                    ggg   ggg";
    private static final String row19 = "gg    gggg                                                    gggsssggg";
    private static final String row20 = "gg    ggg                                                     ggg   ggg";
    private static final String row21 = "gg    gggx      x                       B         B           ggg   ggg";
    private static final String row22 = "gg   sgggx      gx       B   B               B         gg           ggg";
    private static final String row23 = "g    gggg       gx       xxxxxx  g   g  sssssssssssss gggg          ggg";
    private static final String row24 = "g    gggggx     gx      gggggggx   B   xgggggggggggggggggg          ggg";
    private static final String row25 = "g    gggggx ss  gx  ss xgggggggx       xg           gggggg         sggg";
    private static final String row26 = "g    gggggx gg  gx  gg xgggggggx       xg          gggggg          gggg";
    private static final String row27 = "g    gggggx     gx     xgggggggx       xg  gggggggggggggg          gggg";
    private static final String row28 = "g    ggggg xxxxxggxxxxx gggggggx       xg  gggggggggggggg          gggg";
    private static final String row29 = "gs    gggggggggggggggggggggggggx  sss  xg ggggggggggg   gg         gggg";
    private static final String row30 = "gg    ggg                   gggx       xg gggggggg       gg  B   sggggg";
    private static final String row31 = "g      g                    gggx       xggggx             gg   gggggggg";
    private static final String row32 = "g      g                    gggx         ggx               g   gggggggg";
    private static final String row33 = "g      g                                              gg   g   g   xggg";
    private static final String row34 = "g                                ss                   gg 1     g    ggg";
    private static final String row35 = "g             W  W  W  W         gg                   gg       g    ggg";
    private static final String row36 = "gs                                                     ggggg   g    ggg";
    private static final String row37 = "gg           ggggggggggg                  B                gsssg    ggg";
    private static final String row38 = "g xxx        gg       gg    gggggggg          B            ggggg    ggg";
    private static final String row39 = "g ggg      gggg       gg    gg    gggggggggggggg                    ggg";
    private static final String row40 = "g      s   gg         gg    gg                gg   s                ggg";
    private static final String row41 = "g      g   gg         gg    gg                gg   g                ggg";
    private static final String row42 = "g      g   gg         gg    gg                gg   g                ggg";
    private static final String row43 = "g      g   gg         gg    gg                gg   g                ggg";
    private static final String row44 = "g      g   gg         gg    gg                gg   g                ggg";
    private static final String row45 = "g      g   gg         gg    gg                gg   g                ggg";
    private static final String row46 = "g      g   gg         gg    gg                gg   g                ggg";
    private static final String row47 = "g      g   gg         gg    gg                gg   g                ggg";
    private static final String row48 = "g      g   gg         gg    gg                gg   g                ggg";
    private static final String row49 = "g      g   gg         gg    gg                gg   g                ggg";
    public static final String level_name = "Level 2-5";
    private Color[] colors;

    public Level09(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 2-5", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), tile_map);
        this.spawn_pos.setLoc(1120.0f, 256.0f);
        
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

