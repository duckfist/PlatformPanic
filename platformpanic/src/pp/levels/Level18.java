package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level18 extends Level {
    //private int current_room = 0;
    private static final String row1 =  "gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg";
    private static final String row2 =  "gg   gggg        uuuuu               uuuuugggggggggggguuuuuuuguuuuuuuxgx         uuuug";
    private static final String row3 =  "g     gg                                  uuuugggggggg       x       xgx             g";
    private static final String row4 =  "g     gg                                      uuuugggg               xgx             g";
    private static final String row5 =  "g                                                 uuuu               xgx    D        g";
    private static final String row6 =  "g                sssss                                                x    xxxxxx    g";
    private static final String row7 =  "g                ggggg               sssss                                 gggggg    g";
    private static final String row8 =  "g                gg                  gggggssss                             gggggg    g";
    private static final String row9 =  "g                gg                     xgggggss                   D       gggggg    g";
    private static final String row10 = "g                ggg           x           xggggss       x       x         gggggg    g";
    private static final String row11 = "g        ssss    ggg          xgx           ggggggsssssssgsssssssgsssssssssgggggg   sg";
    private static final String row12 = "gssssssssggggssssggg]        xggx           gguuuuuuuuuuuuuuuuuuuuuuuuuuuuu     g gggg";
    private static final String row13 = "ggggggggggggggggggggg   ssss  xgx           gg                                  g  ggg";
    private static final String row14 = "guuuuuuuuuuuuuuuuuuuu   gggg   x            gg                                  g    g";
    private static final String row15 = "g                       gggg                gg                                  gg   g";
    private static final String row16 = "g                        gg        S        gg           ]                       gg  g";
    private static final String row17 = "g                        gg                 gg           g]                          g";
    private static final String row18 = "g   B       x                               gg           gg]                         g";
    private static final String row19 = "g          xgx                              gg            gg                 x     ggg";
    private static final String row20 = "g         xgggx  ssss                       gg                               x     ggg";
    private static final String row21 = "g          xgx   gggg   B         ggg   ssssgg 2                            gxg  [gggg";
    private static final String row22 = "g           x     gg                    gggggg                            [ggxg  ggg g";
    private static final String row23 = "g                 gg                    gggggg                           [ggggg  ggg g";
    private static final String row24 = "g    ss                                 gggggg                                       g";
    private static final String row25 = "g    gg                      g          gggggg                                       g";
    private static final String row26 = "g   sgg                    A g          gggggg                [                      g";
    private static final String row27 = "gsssgggssssssssssssssssssssssgsss       gggggg               [g                      g";
    private static final String row28 = "ggggggggggggggggggggggggggggggggg       gggggg]             [gg                ss    g";
    private static final String row29 = "ggggggggggggggggggggggggggggggggg       ggggggg]                               gg    g";
    private static final String row30 = "                               ggsssssssgggggggg]                              gg    g";
    private static final String row31 = "                               gggggggggggggggggg]                             gg    g";
    private static final String row32 = "                               gggggggggggggggggggsssssssssssssssssssssssssssssggssssg";
    private static final String row33 = "                                        gggggggggggggggggggggggggggggggggggggggggggggg";
    private static final String row34 = "                                                gggggggggggggggggggggggggggggggggggggg";
    private static final String row35 = "                                                                                     g";
    private static final String row36 = "                                                                                     g";
    private static final String row37 = "                                                                                     g";
    private static final String row38 = "                                                                                     g";
    private static final String row39 = "                                                                                     g";
    public static final String level_name = "Level 4-4";
    private Color[] colors;

    public Level18(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 4-4", player, camera, tile_map, level_number);
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

