package pp.levels;

import java.awt.Color;
import java.awt.Graphics;

import pp.entities.Camera;
import pp.entities.Player;
import pp.game.MP3Player;
import pp.game.MP3Player.BGMTracks;

public class Level23 extends Level {
    //private int current_room = 0;
//    private static final String row1  = "                                                                         xgg         gg                                                                              1                 xxxxx";
//    private static final String row2  = "                                                                         xgg         gg                                                                         1               1      xxxxx";
//    private static final String row3  = "                                                                          ggggggggggggg               gg    gg                        B   B   B              *******************************";
//    private static final String row4  = "                                                                    2     ggggggggggggg             xxxxxxxxxxxxx                  ggggggggggggggg       *****             ssss   gg   xxxxx";
//    private static final String row5  = "                                                                          *************     *************************              g*************g   *****            1    gggg   uu   xxxxx";
//    private static final String row6  = "                                   R        ***  ***   ********************           *     *       xxxxx           **             g*ggggggggggg******  1      1           gggg        xxxxx";
//    private static final String row7  = "g            uuuuuuuuuuuuuuu       ******   * *  * *  **                     *******  *   ***  ********************* **          ggg*g*********ggg                                     xxxxx";
//    private static final String row8  = "g                                       ***** **** ****   2  ***             *     *  *** *    *      xxxxxxxxxx   ** ***************g*ggggggg**********************************************";
//    private static final String row9  = "g                                                            * *********   ***     ***  ***  *** *** xggggggggggx   **         ggggggg*g*****ggggg                   sss               xxxxx";
//    private static final String row10 = "g                  ]]]]]]]]]***********                      *         *   *   ***   *  xxx  *   * * xgxggggggxgx    ******************g*ggg**********      1  s     ggg               xxxxx";
//    private static final String row11 = "g         ]]]]]]]]]]]]]]]]]]          ************************         *   *   * *   *********   * * xggggggggggx            ggggggggggg*g           *****     g  1  ggg   1   sss  1  xxxxx";
//    private static final String row12 = "g                                               2                      ***** *** ***           *** *  xxxxxxxxxx      *******************g          1    *****       ggg       ggg     xxxxx";
//    private static final String row13 = "gggggggggggggg   *******************         ********      ********          *     *           *   ********************    ggggggggggggggg                   *******************************";
//    private static final String row14 = "                                   ***********      ********      ************     *************                                                                                       xxxxx";
	
	//private int current_room = 0;
    private static final String row1  = "                                                                           gggggggg                                       g uuuuuuu gggggg                  ggg";
    private static final String row2  = "                                                                           ggggggg                        gg              g         ggggg                   ggg";
    private static final String row3  = "                                                                           gggggg                         gg              g         ggggg                   ggg";
    private static final String row4  = "                                                xxxxxx                     ggggg                                          g         gggg                     gg";
    private static final String row5  = "                                               xggggggx                    gggg                          gg               g         gggg                     gg";
    private static final String row6  = "                                               xggggggx                     gg                           gg               g     1   ggg                       g";
    private static final String row7  = "                                               xgg  ggx                     g             g]            xggx              g         ggg                       g";
    private static final String row8  = "                                               xgg  ggx           D         g            ggggg          xggx  *           gg  s   1 gg                        g";
    private static final String row9  = "                                               xggggggx        B      xx                 ggggg          xggx  * R         gg  gx   xgg                        g";
    private static final String row10 = "              g   ggg                          xggggggx              xggx                    g    ***** xggx  * ********* gg *gg R ggg              R   R  x  g";
    private static final String row11 = "              g   ggggg                         xxxxxx               xggx      g             g    *xg * xggx  *        g* gg *gg * ggg       ****   **  ** x  g";
    private static final String row12 = "              g D                                                     xx       g       R     ggg **xg * xggx  *        g*    *gg * ggg   x   *             x  g";
    private static final String row13 = "              g   ggggggggg                           xggx  D                          ****  ggg * xg * xggx  *        g*    *gg * ggg   g R *  xxxxxxxxx  x  g";
    private static final String row14 = "             gg D                                                              g     xxxx *  ggg * xg * xggx  *      xxg*    *gg *     * g * * xgggggggggx x  g";
    private static final String row15 = "              g   ggggggggg    x   x   x                                       g    xgggg *      * xg *********    xxggg******gg *     * g ***  xxxxxxxxx  x  g";
    private static final String row16 = "              g   ggggggggg   xgx xgx xgx                            xxxx      gsss xgggg ******** xg             xgggg xxxxxxgg ******* g      *******    x  g";
    private static final String row17 = "             gg D                                                   xggggx     gggg xgggg   3   B  xgg            xg  ggggggggggxxxxxxxx g  xx  *     *        ";
    private static final String row18 = "              g   ggggggggg                                         xg  gx              g xxxxxxxxx ggg          xgg          gggggggggggg xggx *   R *        ";
    private static final String row19 = "              g   ggggggggg                                         xg  gx              ggggggggggggggg          xgg             guuug     xggx * * * *        ";
    private static final String row20 = "             gg D                                         s         xggggx               g          gggg     sss xgg             g   g      xx  * * * *      5 ";
    private static final String row21 = "              g                                           g          xxxx                g          ggggggggggggggg              g   g          * * * *  R     ";
    private static final String row22 = "xxxxx         g 2                                         g                              g              ggggg                    g B g          * *** *  *     ";
    private static final String row23 = "gggggxxxxx        ggggggg                                                                g           xxxg                                       *     ****     ";
    private static final String row24 = "    ggggggxxxx       gx                                                                  g]        xxggg                                 ********              ";
    private static final String row25 = "         gggggxxxx ggg      2                                                            gg]      xggg                             s    **                     ";
    private static final String row26 = "            ggggggxg                                                                      gg]    xgg                                    *                      ";
    private static final String row27 = "             ggggggggxxx                                                                   gg   xgg                                  ****                      ";
    private static final String row28 = "                  ggggggxx   2                                                              gxxxgg                                                             ";
    private static final String row29 = "                      ggggxx                                                                 gggg                                                              ";
    private static final String row30 = "                        ggggxx   2                                                                                                                             ";
    private static final String row31 = "                           gggxxx     2                                                                                                                        ";
    private static final String row32 = "                             ggggxxx                                                                                                                           ";
    private static final String row33 = "                                ggggxxx   2    2                                                                                                               ";
    private static final String row34 = "                                   ggggxxxx         2    2                                                                                                     ";
    private static final String row35 = "                                     ggggggxxxx               3                                                                                                ";
    private static final String row36 = "                                          ggggg xxxx              4                                                                                            ";
    private static final String row37 = "                                             ggggggg xxxx                                                                                                      ";
    private static final String row38 = "                                                  ggggggg xxxxx                                                                                                ";
    private static final String row39 = "                                                        ggggggg xxxxx                                                                                          ";
    private static final String row40 = "                                                              gggggggx                                                                                         ";
    public static final String level_name = "Level 5-4";
    private Color[] colors;

    public Level23(Player player, Camera camera, int tile_map, int level_number) {
        super("Level 5-4", player, camera, tile_map, level_number);
        this.initializeColorArray();
        super.setColorArray(this.colors);
        this.initializeLevel(getMap(), this.ground_tileset);
        this.spawn_pos.setLoc(2128.0f, 1072.0f);

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

