package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level04 extends Level {
    //private int current_room = 0;
    private static final String row1 = "ggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg";
    private static final String row2 = "g                                                                     g";
    private static final String row3 = "g                                                                     g";
    private static final String row4 = "g                                                                     g";
    private static final String row5 = "g                                                                     g";
    private static final String row6 = "g                                   gggg                              g";
    private static final String row7 = "g            g     D                           gg                     g";
    private static final String row8 = "g                                   gggg                              g";
    private static final String row9 = "gg                   ggg                               A              g";
    private static final String row10 = "ggg          B       gggg           gggg                   B         gg";
    private static final String row11 = "g           gg        gggg           ggg                  ggg         g";
    private static final String row12 = "g      gg   ggg B      ggg           ggg                  gggggg      g";
    private static final String row13 = "g      gg   ggg        ggg           gg                    ggggg      g";
    private static final String row14 = "g    gggg     g        gggg          g                     gggggg B   g";
    private static final String row15 = "g     ggg            gggggg          g                     gggggg     g";
    private static final String row16 = "g B                    gggg                                  gggg     g";
    private static final String row17 = "g                       gggg                  gg             ggggg    g";
    private static final String row18 = "g                B        gg                  gg              ggg     g";
    private static final String row19 = "g    B                    gg                  gggggg            g    gg";
    private static final String row20 = "g         B               gg                   ggggg            g    gg";
    private static final String row21 = "g                B        gggggggggggg     g   ggg      B       g    gg";
    private static final String row22 = "gg                     B  gggggggggggg  B  g   gg      ggg  B       ggg";
    private static final String row23 = "g                         ggggg                gg     gggg  !       ggg";
    private static final String row24 = "g                         ggg                       gggggg         gggg";
    private static final String row25 = "g                      B  ggg                       gggggg     gggggggg";
    private static final String row26 = "g                B   gggg ggg            B         gggggg      ggg  ggg";
    private static final String row27 = "g         B    ggggggg    ggg              gggggggggggggg      ggg  ggg";
    private static final String row28 = "g                         gg         B     gggggggggggggg      ggg  ggg";
    private static final String row29 = "g                         gg    ggg    !  ggggggggggg          ggg  ggg";
    private static final String row30 = "g     B                    g    ggg       gggggggg             ggg  ggg";
    private static final String row31 = "g                          g    g        ggg                   ggg  ggg";
    private static final String row32 = "g                          g    g        gg                    ggg  ggg";
    private static final String row33 = "g B                                                            ggg  ggg";
    private static final String row34 = "g                                    B                         ggg  ggg";
    private static final String row35 = "g                                                              ggg  ggg";
    private static final String row36 = "g    B                 gggggggggggg                            ggg  ggg";
    private static final String row37 = "g                      gg   ggg                                ggg  ggg";
    private static final String row38 = "g   !   ggggg   gggg        ggg                                ggg  ggg";
    private static final String row39 = "g        ggg  ! gggg        ggg                                ggg  ggg";
    private static final String row40 = "g        ggg     gg         ggg                                ggg  ggg";
    private static final String row41 = "g        ggg     gg         ggg                                ggg  ggg";
    public static final String level_name = "Level 1-5";
    private Color[] colors;

    public Level04(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 1-5", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(Level04.getMap(), tile_map);
        this.spawn_pos.setLoc(1056.0f, 512.0f);
        
        MP3Player.PlayBGM(BGMTracks.WORLD1);
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

