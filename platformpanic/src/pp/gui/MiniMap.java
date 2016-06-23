package pp.gui;

import java.awt.Color;
import java.awt.Graphics2D;

import pp.entities.LevelElement;
import pp.entities.Platform;
import pp.game.GamePanel;
import pp.levels.Level;

public class MiniMap {
    public static int SCREEN_X = 384;
    public static int SCREEN_Y = 334;
    public static final int WIDTH = 128;
    public static final int HEIGHT = 112;
    public static final int SCALE = 8;
    public static final int SCREEN_CENTER_X = SCREEN_X + 64;
    public static final int SCREEN_CENTER_Y = SCREEN_Y + 56;
    private Color bg_color_level;
    private Color bg_color_oob;
    private Level current_level;
    private int array_width;
    private int array_height;
    private LevelElement[][] collision_array;

    public MiniMap(LevelElement[][] collision_array, Level current_level) {
        this.current_level = current_level;
        this.collision_array = collision_array;
        try {
            this.array_height = collision_array.length;
            this.array_width = collision_array[0].length;
        }
        catch (Exception e) {
            this.array_height = 1;
            this.array_width = 1;
            System.out.println("MiniMap Error: Bad collision array.");
        }
        this.bg_color_level = new Color(48, 48, 48, 128);
        this.bg_color_oob = new Color(24, 24, 24, 128);
        System.out.println("" + this.array_width + " cols, " + this.array_height + " rows");
    }

    public void paintMiniMap(Graphics2D g2d) {
        int draw_h;
        int draw_x;
        int draw_w;
        int draw_y;
        int player_level_x = Math.round((this.current_level.left_level_border + this.current_level.player.f2_pos.x) / 8.0f);
        int player_level_y = Math.round((this.current_level.up_level_border + this.current_level.player.f2_pos.y) / 8.0f);
        g2d.setColor(this.bg_color_oob);
        g2d.fillRect(SCREEN_X, SCREEN_Y, 128, 112);
        int draw_bg_x = SCREEN_CENTER_X - player_level_x;
        int draw_bg_y = SCREEN_CENTER_Y - player_level_y;
        int draw_bg_w = this.array_width * 4;
        int draw_bg_h = this.array_height * 4;
        if (draw_bg_x + draw_bg_w > SCREEN_X + 128) {
            draw_bg_w = SCREEN_X + 128 - draw_bg_x;
        }
        if (draw_bg_x < SCREEN_X) {
            draw_bg_w -= SCREEN_X - draw_bg_x;
            draw_bg_x = SCREEN_X;
        }
        if (draw_bg_y + draw_bg_h > SCREEN_Y + 112) {
            draw_bg_h = SCREEN_Y + 112 - draw_bg_y;
        }
        if (draw_bg_y < SCREEN_Y) {
            draw_bg_h -= SCREEN_Y - draw_bg_y;
            draw_bg_y = SCREEN_Y;
        }
        g2d.setColor(this.bg_color_level);
        g2d.fillRect(draw_bg_x, draw_bg_y, draw_bg_w, draw_bg_h);
        for (int i = 0; i < this.array_width; ++i) {
            for (int j = 0; j < this.array_height; ++j) {
                try {
                    if (this.collision_array[j][i] == null) continue;
                    draw_x = SCREEN_CENTER_X + i * 4 - player_level_x;
                    draw_y = SCREEN_CENTER_Y + j * 4 - player_level_y;
                    draw_w = 4;
                    draw_h = 4;
                    if (draw_x + draw_w > SCREEN_X + 128) {
                        draw_w = SCREEN_X + 128 - draw_x;
                        if (draw_w <= 0 || draw_x > SCREEN_X + 128) {
                            draw_w = 0;
                            continue;
                        }
                    } else if (draw_x < SCREEN_X && (draw_w -= SCREEN_X - (draw_x = SCREEN_X)) <= 0) {
                        draw_w = 0;
                        continue;
                    }
                    if (draw_y + draw_h > SCREEN_Y + 112) {
                        draw_h = SCREEN_Y + 112 - draw_y;
                        if (draw_h <= 0 || draw_y > SCREEN_Y + 112) {
                            draw_h = 0;
                            continue;
                        }
                    } else if (draw_y < SCREEN_Y && (draw_h -= SCREEN_Y - (draw_y = SCREEN_Y)) <= 0) {
                        draw_h = 0;
                        continue;
                    }
                    g2d.setColor(Color.gray);
                    g2d.fillRect(draw_x, draw_y, draw_w, draw_h);
                    continue;
                }
                catch (Exception e) {
                    System.out.println("Exception: Array has a problem at + " + i + ", " + j);
                }
            }
        }
        for (Platform platform : this.current_level.platform_vector) {
            draw_x = Math.round((float)SCREEN_CENTER_X + (platform.f2_pos.x + this.current_level.left_level_border) / 8.0f - (float)player_level_x);
            draw_y = Math.round((float)SCREEN_CENTER_Y + (platform.f2_pos.y + this.current_level.up_level_border) / 8.0f - (float)player_level_y);
            draw_w = Math.round(platform.width / 8.0f);
            draw_h = 4;
            if (draw_x + draw_w > SCREEN_X + 128) {
                draw_w = SCREEN_X + 128 - draw_x;
                if (draw_w <= 0 || draw_x > SCREEN_X + 128) {
                    draw_w = 0;
                    continue;
                }
            } else if (draw_x < SCREEN_X && (draw_w -= SCREEN_X - (draw_x = SCREEN_X)) <= 0) {
                draw_w = 0;
                continue;
            }
            if (draw_y + draw_h > SCREEN_Y + 112) {
                draw_h = SCREEN_Y + 112 - draw_y;
                if (draw_h <= 0 || draw_y > SCREEN_Y + 112) {
                    draw_h = 0;
                    continue;
                }
            } else if (draw_y < SCREEN_Y && (draw_h -= SCREEN_Y - (draw_y = SCREEN_Y)) <= 0) {
                draw_h = 0;
                continue;
            }
            Color platform_color = platform.getActivated() ? Color.GREEN : new Color(190, 16, 16);
            g2d.setColor(platform_color);
            g2d.fillRect(draw_x, draw_y, draw_w, draw_h);
        }
        g2d.setColor(Color.RED);
        g2d.fillRect(SCREEN_CENTER_X, SCREEN_CENTER_Y, 3, 5);
        if (GamePanel.time.getTt() % 0.2f > 0.1f) {
            g2d.drawOval(SCREEN_CENTER_X - 3, SCREEN_CENTER_Y - 3, 8, 9);
        }
        g2d.setColor(Color.BLUE);
        g2d.drawRect(SCREEN_CENTER_X - (int)(this.current_level.player.f2_pos.x / 8.0f), SCREEN_CENTER_Y - (int)(this.current_level.player.f2_pos.y / 8.0f), 64, 56);
    }
}

