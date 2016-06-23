package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level16 extends Level {
    //private int current_room = 0;
    private static final String row1 = "       xg                                                                            g";
    private static final String row2 = "        g x                             1                                            g";
    private static final String row3 = "g     xgg gx    x                                                                    g";
    private static final String row4 = "g     xg  x    xgx       D                                                           g";
    private static final String row5 = "g      g        x                                                                    g";
    private static final String row6 = "gg   xgg                          s                                                  g";
    private static final String row7 = " g   xg                         ggg                                                x g";
    private static final String row8 = " g    g                         g         [                                        g g";
    private static final String row9 = "  g                            sg         g                                  x     g g";
    private static final String row10 = "  g                           ggg                                            g     g g";
    private static final String row11 = "  g      xxx                  g                                        x     g     g g";
    private static final String row12 = "  g     xgggxxx               g     x                                  g     g     g g";
    private static final String row13 = "  g    xgggggggx              g     g                            x     g     gsssssg g";
    private static final String row14 = "  g    gggggggggg]            g                            x     g     g     ggggggg g";
    private static final String row15 = "  g]   gg       gggggggggggg  g]            gg       x     g     g     gsssssggggggg g";
    private static final String row16 = "  gg   gg              xxxx   gg            gg       g     g     g     ggggggggggggg g";
    private static final String row17 = "   g   g                                    gg       g     g     gsssssg          gg g";
    private static final String row18 = "   g   g                                    gg       g     g     ggggggg          gg g";
    private static final String row19 = "   gg                                       ggsssssssgsssssgsssssg                gg g";
    private static final String row20 = "    g                                      sgg gggggggggggggggggg                 gg g";
    private static final String row21 = "    g                                      ggg                                    gg g";
    private static final String row22 = "    g          xxxxxx                     [ggg                                    gg g";
    private static final String row23 = "    gg        xggggggx                  gggggg                                    gg g";
    private static final String row24 = "     g       xgg  gggx                  ggg                              g           g";
    private static final String row25 = "     g ]          xgggx                 ggg                              g           g";
    private static final String row26 = "     ggg          xgggx                 ggg                              g           g";
    private static final String row27 = "                  xgggx     ssssss      ggg                              g           g";
    private static final String row28 = "                  xggg xxxx gggggg   A                                  ggg     gggggg";
    private static final String row29 = "                  xgggggggg ggggggssssssggg              B          B     gssss g    g";
    private static final String row30 = "                  xgggggggg ggggggggggggggg                               gggggsg    g";
    private static final String row31 = "                  xgggggggg ggggggggggggggg                                g  ggg    g";
    private static final String row32 = "                  xgggggggg ggggggggggggggg                                g         g";
    private static final String row33 = "                  xgggggggg ggggggggggggggg                                g         g";
    private static final String row34 = "                  xgggggggg ggggggggggggggg                                g         g";
    private static final String row35 = "                  xgggggggg ggggggggggggggg                                g         g";
    private static final String row36 = "                  xgggggggg ggggggggggggggg                                g         g";
    private static final String row37 = "                  xgggggggg ggggggggggggggg                                g         g";
    private static final String row38 = "                  xgggggggg ggggggggggggggg                                g         g";
    private static final String row39 = "                  xgggggggg ggggggggggggggg                                g         g";
    public static final String level_name = "Level 4-2";
    private Color[] colors;

    public Level16(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 4-2", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), tile_map);
        this.spawn_pos.setLoc(96.0f, 64.0f);

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
        		row39,
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

