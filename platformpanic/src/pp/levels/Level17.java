package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level17 extends Level {
    //private int current_room = 0;
    private static final String row1  = "g              g";
    private static final String row2  = "g              g";
    private static final String row3  = "g              g";
    private static final String row4  = "g              g";
    private static final String row5  = "g              g";
    private static final String row6  = "g              g";
    private static final String row7  = "g 1            g";
    private static final String row8  = "g          ssssg";
    private static final String row9  = "g          uuuug";
    private static final String row10 = "g              g";
    private static final String row11 = "g              g";
    private static final String row12 = "g              g";
    private static final String row13 = "g              g";
    private static final String row14 = "g              g";
    private static final String row15 = "g              g";
    private static final String row16 = "g          ssssg";
    private static final String row17 = "g          uuuug";
    private static final String row18 = "g              g";
    private static final String row19 = "g              g";
    private static final String row20 = "g              g";
    private static final String row21 = "g              g";
    private static final String row22 = "g              g";
    private static final String row23 = "g              g";
    private static final String row24 = "g          ssssg";
    private static final String row25 = "g          uuuug";
    private static final String row26 = "g              g";
    private static final String row27 = "g              g";
    private static final String row28 = "g              g";
    private static final String row29 = "gssss          g";
    private static final String row30 = " xxxx          g";
    private static final String row31 = " xxxx    2     g";
    private static final String row32 = " xxxxxxxxxxx   g";
    private static final String row33 = "g          x   g";
    private static final String row34 = "g          xsssg";
    private static final String row35 = "g          x   g";
    private static final String row36 = "g          x   g";
    private static final String row37 = "g    x     x   g";
    private static final String row38 = "g    x     x   g";
    private static final String row39 = "g    x     x   g";
    private static final String row40 = "g    x     xsssg";
    private static final String row41 = "g    x     x   g";
    private static final String row42 = "g    x     x   g";
    private static final String row43 = "gs   xss   x   g";
    private static final String row44 = "g    x     x   g";
    private static final String row45 = "g    x     x   g";
    private static final String row46 = "g    x     x   g";
    private static final String row47 = "g    x     xsssg";
    private static final String row48 = "g  ssx     x   g";
    private static final String row49 = "g    x   ssx   g";
    private static final String row50 = "g    x     x   g";
    private static final String row51 = "g    x     x   g";
    private static final String row52 = "g          x   g";
    private static final String row53 = "g  ssg     x   g";
    private static final String row54 = "g    gss   xsssg";
    private static final String row55 = "g    g         g";
    private static final String row56 = "g B  g         g";
    private static final String row57 = "g g  g         g";
    private static final String row58 = "g g D          g";
    private static final String row59 = " 2    x    x   g";
    private static final String row60 = "g g   xss  x 1 g";
    private static final String row61 = "g g   x    x s g";
    private static final String row62 = " 2    x    x   g";
    private static final String row63 = "g     x    x   g";
    private static final String row64 = "g       A  x   g";
    private static final String row65 = "g    x     x   g";
    private static final String row66 = "g    x     x   g";
    private static final String row67 = "g    x D   x   g";
    private static final String row68 = "g   x      x   g";
    private static final String row69 = "g   x      x   g";
    private static final String row70 = "g   x   3  x   g";
    private static final String row71 = "g   x      x   g";
    private static final String row72 = "g   x      x   g";
    public static final String level_name = "Level 4-3";
    private Color[] colors;

    public Level17(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 4-3", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), this.ground_tileset);
        this.spawn_pos.setLoc(32.0f * 9.0f, 32.0f * 67.0f);

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
        		row68,
        		row69,
        		row70,
        		row71,
        		row72,
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

