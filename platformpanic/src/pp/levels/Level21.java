package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.entities.SpikeFill;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level21 extends Level {
    //private int current_room = 0;
    private static final String row1 =  "gggggggggggggggg";
    private static final String row2 =  "g           g  g";
    private static final String row3 =  "g           g  g";
    private static final String row4 =  "g      ggg  g  g";
    private static final String row5 =  "g      gXg* g  g";
    private static final String row6 =  "g      g g* g  g";
    private static final String row7 =  "g      g g* g  g";
    private static final String row8 =  "g      g g*  2 g";
    private static final String row9 =  "g g    g g*R   g";
    private static final String row10 = "g g    g g**   g";
    private static final String row11 = "g g    g g  1  g";
    private static final String row12 = "g g    g g     g";
    private static final String row13 = "g gxxxxg gxxxxxg";
    private static final String row14 = "g gggggg ggggggg";
    private static final String row15 = "g g            g";
    private static final String row16 = "g g            g";
    private static final String row17 = "g g            g";
    private static final String row18 = "g g       gggg g";
    private static final String row19 = "g g      x     g";
    private static final String row20 = "g    B  x      g";
    private static final String row21 = "g      xg      g";
    private static final String row22 = "gggggg xg      g";
    private static final String row23 = "g          x   g";
    private static final String row24 = "g          x   g";
    private static final String row25 = "g   g      x   g";
    private static final String row26 = "g 5 g      x   g";
    private static final String row27 = "g   g      x   g";
    private static final String row28 = "g   gggggggg 3 g";
    private static final String row29 = "g   gX g   g s g";
    private static final String row30 = "g    g   g g   g";
    private static final String row31 = "g    ggggg gx xg";
    private static final String row32 = "g     g    g   g";
    private static final String row33 = "g     g gggg   g";
    private static final String row34 = "g     g g    2 g";
    private static final String row35 = "g     g g      g";
    private static final String row36 = "g     g g      g";
    private static final String row37 = "g     g g    1 g";
    private static final String row38 = "g     g g      g";
    private static final String row39 = "g              g";
    private static final String row40 = "g            1 g";
    private static final String row41 = "g              g";
    private static final String row42 = "g              g";
    private static final String row43 = "g           1  g";
    private static final String row44 = "g              g";
    private static final String row45 = "g         1    g";
    private static final String row46 = "g   ggg gggggggg";
    private static final String row47 = "g 8 ggg ggg    g";
    private static final String row48 = "g   g       x gg";
    private static final String row49 = "g ggg gxxxxxx gg";
    private static final String row50 = "g g   g        g";
    private static final String row51 = "g g   g        g";
    private static final String row52 = "g g g g      B g";
    private static final String row53 = "g g g g      x g";
    private static final String row54 = "g gRg g *******g";
    private static final String row55 = "g g*g g      g*g";
    private static final String row56 = "g g*g    g   g*g";
    private static final String row57 = "g g*** ****  g*g";
    private static final String row58 = "g   g**  x*  g*g";
    private static final String row59 = "g   g *****  g*g";
    private static final String row60 = "ggg gR       g*g";
    private static final String row61 = "gggs ******* g*g";
    private static final String row62 = "ggggxxxxxx ****g";
    private static final String row63 = "gggggggggggggggg";
    public static final String level_name = "Level 7-2";
    private Color[] colors;

    public Level21(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 5-2", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), this.ground_tileset);
        this.spawn_pos.setLoc(448.0f, 48.0f);
        SpikeFill.tick_delay = 60;
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

