package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level15 extends Level {
    //private int current_room = 0;
    private static final String row1 = "                                                                                                 ggx       xggxxxxxxxgg   ggggggggxx           gxxxxxxxx";
    private static final String row2 = "                                                                                                 ggx       xgg       gg   g   xx ggg           g       x";
    private static final String row3 = "g                                                                                                ggx       xgg       gggggg        gg   gg   xgg       x";
    private static final String row4 = "g                                                                                                ggx       xgg       ggxxxg        gg   gg   xgg       x";
    private static final String row5 = "g                                                                                                ggx       xgg   g   gg     D                          x";
    private static final String row6 = "gg                                                   1    4    1                               g ggx       xgg B g 1 gg       xx   gg   gg             x";
    private static final String row7 = "gg                              ggg                                                            g ggx       xgg   g   gg   g   gg   gg   gg        g   gx";
    private static final String row8 = " g                              gggggg                                        xx               g ggx       xgg 1 g        g   gg   gg   gg        g S gx";
    private static final String row9 = "gg                             sgg ggggg]                                    xggx              g ggxxxxxxxxxgg   g        g   gg   gg   gg        g   gx";
    private static final String row10 = "ggg                           gggg    ggg                                   xggggx             g ggggggggggggg 1 g        g   gg        gg        g   gx";
    private static final String row11 = " gg                          ggg       gg             s  s  s  s   gg      xggggggx            g ggggggggggggg   g   gg   g   gg        gg        g   gx";
    private static final String row12 = " gg] ggg          [ ggggggggggg        gg             gggggggggg   gg]    xggggggggx    gg     g               1 g   gg S g s ggggg  sssgg        g   gx";
    private static final String row13 = "  ggggggg         ggggggggggggg        ggg                         ggg   xgggggggggggx  ggsssssg                 g 1 gg   gxgxgggggxxgggggxxx sss g 1 gx";
    private static final String row14 = "  ggggggg         gg                    gg                              xgggggggggggggx ggggggg 1  2  2  2  2  2 gxxxggxxxggggggggggggggggggg ggg g   gx";
    public static final String level_name = "Level 4-1";
    private Color[] colors;

    public Level15(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 4-1", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), tile_map);
        this.spawn_pos.setLoc(224.0f, 256.0f);

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

