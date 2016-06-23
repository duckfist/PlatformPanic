package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level02 extends Level {
    //private int current_room = 0;
    private static final String row1 = "ggggggggggggggggggggggggggggggggggggggggggg";
    private static final String row2 = "gg                                       gg";
    private static final String row3 = "g                                         g";
    private static final String row4 = "g                                         g";
    private static final String row5 = "g                                         g";
    private static final String row6 = "g             B                           g";
    private static final String row7 = "g        B         B                 A    g";
    private static final String row8 = "g                                         g";
    private static final String row9 = "gg     ggg         gggg                 B g";
    private static final String row10 = "gg     gggg         gg                    g";
    private static final String row11 = " g     g                                  g";
    private static final String row12 = " gs   sg                                  g";
    private static final String row13 = "  g   g                                   g";
    private static final String row14 = "    B                                    gg";
    private static final String row15 = "                                    B    gg";
    private static final String row16 = "                                        sgg";
    private static final String row17 = "                                        ggg";
    private static final String row18 = "                                 B      ggg";
    private static final String row19 = "                           W           gggg";
    private static final String row20 = "                          gsg          gggg";
    private static final String row21 = "  sssss                   ggg          gggg";
    private static final String row22 = "  ggggg       sssss        g         sggggg";
    private static final String row23 = "   ggg        ggggg      ggg        ggggggg";
    private static final String row24 = "    g         ggggg      g         gggggggg";
    private static final String row25 = "    g           gg       ggg       gggggggg";
    private static final String row26 = "    ggg         gg         ggs     gggggggg";
    private static final String row27 = "      g         ggg         gg     gggggggg";
    private static final String row28 = "  sgggg          gggs        g  !  gggggggg";
    private static final String row29 = "  gg              ggg ! sggggg     gggggggg";
    private static final String row30 = "  g          sgg  ggg   gg        sgggggggg";
    private static final String row31 = "  gg         ggggggg    g   gggs  ggggggggg";
    private static final String row32 = "   ggggggs   gg ggg     ggg g gg   gggggggg";
    private static final String row33 = "        gg ! gg           ggg  g   gggggggg";
    private static final String row34 = "         g    ggg              g   gggggggg";
    public static final String level_name = "Level 1-3";
    private Color[] colors;

    public Level02(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 1-3", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(Level02.getMap(), tile_map);
        this.spawn_pos.setLoc(160.0f, 936.0f);
        
        MP3Player.PlayBGM(BGMTracks.WORLD1);
    }

    @Override
    public String[] getTileMap() { return getMap(); }
    public static String[] getMap() {
        String[] definitions = new String[] {
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

