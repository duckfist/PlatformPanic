package pp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import pp.entities.SoundClip;
import pp.game.GamePanel;
import pp.levels.Level;
import pp.utilities.ImageLoader;
import pp.utilities.Timer;

public class LevelCleared {
    public static BufferedImage bronze_star_image;
    public static BufferedImage silver_star_image;
    public static BufferedImage gold_star_image;
    private static ImageLoader image_loader;
    public static boolean show_menu;
    public static boolean is_record;
    public boolean failed;
    public static Timer start_timer;
    public static LevelClearedButton btn_retry;
    public static LevelClearedButton btn_next;
    public static LevelClearedButton btn_main;
    public static LevelClearedButton btn_replay;
    public static LevelClearedButton[] buttons;
    public int current_selection;
    private static boolean[] medals_have;
    private static boolean[] medals_obtained;
    private Level current_level;

    public LevelCleared(Level level, boolean is_record, boolean[] medals_have, boolean[] medals_obtained) {
        start_timer.initTimer();
        show_menu = false;
        this.failed = false;
        this.current_selection = 1;
        this.current_level = level;
        LevelCleared.is_record = is_record;
        LevelCleared.medals_have = medals_have;
        LevelCleared.medals_obtained = medals_obtained;
        System.out.println("medals have = " + medals_have[0] + ", " + medals_have[1] + ", " + medals_have[2]);
        System.out.println("medals obtained = " + medals_obtained[0] + ", " + medals_obtained[1] + ", " + medals_obtained[2]);
        
        GamePanel.SaveData();
    }

    public LevelCleared(Level level, boolean[] medals_have) {
        this.failed = true;
        show_menu = false;
        start_timer.initTimer();
        this.skipIntro();
        this.current_selection = 0;
        this.current_level = level;
        is_record = false;
        LevelCleared.medals_have = medals_have;
        for (int i = 0; i < medals_obtained.length; ++i) {
            LevelCleared.medals_obtained[i] = false;
        }
        this.showMenu();
        if (GamePanel.saveData.getNotFinished(this.current_level.level_number + 1)) {
            LevelCleared.buttons[1].disabled = true;
        }
        
        GamePanel.SaveData();
    }

    public static void loadImages() {
        bronze_star_image = image_loader.loadImage("/Graphics/starbronze.png");
        silver_star_image = image_loader.loadImage("/Graphics/starsilver.png");
        gold_star_image = image_loader.loadImage("/Graphics/stargold.png");
    }

    public void showMenu() {
        show_menu = true;
        int x_pos = 80;
        int y_start = 224;
        int y_spacing = 26;
        LevelCleared.buttons[0] = LevelCleared.btn_retry = new LevelClearedButton(x_pos, y_start, "Retry");
        LevelCleared.buttons[1] = LevelCleared.btn_next = new LevelClearedButton(x_pos, y_start + y_spacing, "Next");
        LevelCleared.buttons[2] = LevelCleared.btn_main = new LevelClearedButton(x_pos, y_start + 2 * y_spacing, "Main Menu");
        LevelCleared.buttons[3] = LevelCleared.btn_replay = new LevelClearedButton(x_pos, y_start + 3 * y_spacing, "Replay");
        LevelCleared.buttons[this.current_selection].selected = true;
    }

    public void cursorDown() {
        LevelCleared.buttons[this.current_selection].selected = false;
        ++this.current_selection;
        boolean disabled = true;
        while (disabled) {
            if (this.current_selection >= buttons.length) {
                this.current_selection = 0;
            }
            if (!LevelCleared.buttons[this.current_selection].disabled) {
                disabled = false;
                continue;
            }
            ++this.current_selection;
        }
        LevelCleared.buttons[this.current_selection].selected = true;
        SoundClip.MENU_NAV.play();
    }

    public void cursorUp() {
        LevelCleared.buttons[this.current_selection].selected = false;
        --this.current_selection;
        boolean disabled = true;
        while (disabled) {
            if (this.current_selection < 0) {
                this.current_selection = buttons.length - 1;
            }
            if (!LevelCleared.buttons[this.current_selection].disabled) {
                disabled = false;
                continue;
            }
            --this.current_selection;
        }
        LevelCleared.buttons[this.current_selection].selected = true;
        SoundClip.MENU_NAV.play();
    }

    public boolean mousePressed(Point p) {
        boolean choice_made = false;
        for (int i = 0; i < buttons.length; ++i) {
            Rectangle button_area = buttons[i].getHitArea();
            if (!button_area.contains(p) || LevelCleared.buttons[i].disabled) continue;
            if (this.current_selection == i) {
                choice_made = true;
                break;
            }
            LevelCleared.buttons[this.current_selection].selected = false;
            this.current_selection = i;
            LevelCleared.buttons[i].selected = true;
            SoundClip.MENU_NAV.play();
            break;
        }
        return choice_made;
    }

    public void skipIntro() {
        if (!show_menu) {
            this.showMenu();
            start_timer.setTtFloat(3.3f);
        }
    }

    public void paintComponent(Graphics g) {
        start_timer.update();
        Graphics2D g2d = (Graphics2D)g;
        if (start_timer.getTt() < 0.5f) {
            int time_scale = Math.round(start_timer.getTt() * 512.0f * 2.0f);
            g2d.setColor(new Color(25, 25, 140));
            g2d.fillRect(0, 215, time_scale, 14);
            g2d.setColor(new Color(50, 65, 240));
            g2d.fillOval(time_scale - 15, 204, 32, 32);
        } else if (start_timer.getTt() < 1.0f) {
            int time_scale = Math.round((start_timer.getTt() - 0.5f) * 128.0f);
            g2d.setColor(Color.GRAY);
            g2d.fillRect(0, 216 - time_scale, 512, time_scale * 2);
            g2d.setColor(new Color(25, 25, 140));
            g2d.fillRect(0, 215 - time_scale, 512, 14);
            g2d.fillRect(0, 215 + time_scale, 512, 14);
        } else if (start_timer.getTt() < 2.0f) {
            g2d.setColor(Color.GRAY);
            g2d.fillRect(0, 152, 512, 128);
            g2d.setColor(new Color(25, 25, 140));
            g2d.fillRect(0, 151, 512, 14);
            g2d.fillRect(0, 279, 512, 14);
            int colorvalue = 128 - Math.round((start_timer.getTt() - 1.0f) * 128.0f * 4.0f);
            if (colorvalue < 0) {
                colorvalue = 0;
            }
            g2d.setColor(new Color(colorvalue, colorvalue, colorvalue));
            g2d.setFont(new Font("serif", 1, 42));
            g2d.drawString("LEVEL CLEAR", 112, 238);
        } else if (start_timer.getTt() < 2.5f) {
            int time_scale = Math.round((start_timer.getTt() - 2.5f) * 128.0f);
            g2d.setColor(Color.GRAY);
            int x = 0;
            int y = 216 + time_scale;
            int w = 512;
            int h = 128 - (64 + time_scale) * 2;
            g2d.fillRect(x, y, w, h);
            if (start_timer.getTt() < 2.4f) {
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("serif", 1, 42));
                g2d.drawString("LEVEL CLEAR", 112, 238);
            }
            g2d.setColor(new Color(25, 25, 140));
            g2d.fillRect(0, 215 - time_scale, 512, 14);
            g2d.fillRect(0, 215 + time_scale, 512, 14);
        } else if (start_timer.getTt() < 2.8f) {
            float time_scale = (start_timer.getTt() - 2.5f) * 3.3333333f;
            g2d.setColor(new Color(25, 25, 140));
            g2d.fillRect((int)(64.0f * time_scale), 215, 512 - (int)(128.0f * time_scale), 14);
        } else if (start_timer.getTt() < 3.3f) {
            float time_scale = (start_timer.getTt() - 2.8f) * 2.0f;
            int colorvalue = 128 - (int)(time_scale * 84.0f);
            g2d.setColor(new Color(colorvalue, colorvalue, colorvalue));
            g2d.fillRect(64, 216 - (int)(time_scale * 144.0f), 384, (int)(time_scale * 288.0f));
            g2d.setColor(new Color(25, 25, 140 + (int)(time_scale * 32.0f)));
            g2d.fillRect(64, 216 - (int)(time_scale * 144.0f), 12, (int)(time_scale * 288.0f));
            g2d.fillRect(436, 216 - (int)(time_scale * 144.0f), 12, (int)(time_scale * 288.0f));
            g2d.fillRect(64, 215 - (int)(time_scale * 144.0f), 384, 14);
            g2d.fillRect(64, 216 + (int)(time_scale * 144.0f), 384, 14);
        } else {
            if (!show_menu) {
                show_menu = true;
                this.showMenu();
            }
            g2d.setColor(new Color(48, 48, 48));
            g2d.fillRect(64, 72, 384, 288);
            g2d.setColor(new Color(25, 25, 140));
            g2d.fillRect(64, 72, 12, 288);
            g2d.fillRect(436, 72, 12, 288);
            g2d.fillRect(64, 71, 384, 14);
            g2d.fillRect(64, 360, 384, 14);
            for (LevelClearedButton abutton : buttons) {
                abutton.paintComponent(g2d);
            }
            this.paintStats(g2d);
        }
    }

    private void paintStats(Graphics2D g2d) {
        int x_pos = 216;
        int y_pos = 196;
        int width = 216;
        int height = 180;
        int fontheight = 20;
        int spacing = fontheight + 12;
        int level = this.current_level.level_number;
        int x_tab_offset = 96;
        int y_times_offset = 64;
        g2d.setColor(new Color(20, 45, 110));
        g2d.fill3DRect(x_pos, y_pos - spacing, width, height, true);
        g2d.setColor(new Color(220, 220, 175));
        g2d.setFont(new Font("monospaced", 1, fontheight + 4));
        g2d.drawString(this.current_level.level_name, x_pos - 132, y_pos - 84);
        g2d.setFont(new Font("serif", 2, fontheight));
        if (this.failed) {
            g2d.setColor(Color.RED);
            g2d.drawString("FAILED", x_pos - 132, y_pos - 84 + fontheight);
        } else {
            g2d.drawString("CLEAR!", x_pos - 132, y_pos - 84 + fontheight);
        }
        g2d.setFont(new Font("monospaced", 1, fontheight + 10));
        String time = new String(LevelClock.getClockTime());
        g2d.setColor(new Color(25, 25, 25));
        g2d.drawString(time, x_pos + 40, y_pos - 64);
        g2d.setColor(new Color(240, 240, 240));
        g2d.drawString(time, x_pos + 43, y_pos - 61);
        g2d.setFont(new Font("serif", 0, fontheight - 6));
        g2d.drawString("Best time:", x_pos + 12, y_pos + fontheight - 16);
        if (is_record && !this.failed) {
            g2d.setColor(new Color(255, 215, 120));
            g2d.setFont(new Font("serif", 2, fontheight - 4));
            g2d.drawString("New Record!", x_pos + x_tab_offset, y_pos + fontheight);
            g2d.setColor(new Color(240, 175, 40));
            g2d.setFont(new Font("monospaced", 1, fontheight));
        } else {
            g2d.setColor(new Color(180, 180, 180));
            g2d.setFont(new Font("monospaced", 0, fontheight));
        }
        g2d.drawString(GamePanel.saveData.getRecordTime(level), x_pos + x_tab_offset - 12, y_pos + fontheight - 16);
        String bronze = LevelClock.getClockTimeMs(LevelClock.getBronzeTime(level));
        String silver = LevelClock.getClockTimeMs(LevelClock.getSilverTime(level));
        String gold = LevelClock.getClockTimeMs(LevelClock.getGoldTime(level));
        if (!this.failed) {
            g2d.setColor(new Color(210, 140, 20));
            g2d.setFont(new Font("sanserif", 2, 11));
            if (medals_obtained[0]) {
                g2d.drawString("New!", x_pos + x_tab_offset - 40, y_pos + y_times_offset - 2);
            }
            if (medals_obtained[1]) {
                g2d.drawString("New!", x_pos + x_tab_offset - 40, y_pos + y_times_offset + spacing - 2);
            }
            if (medals_obtained[2]) {
                g2d.drawString("New!", x_pos + x_tab_offset - 40, y_pos + y_times_offset + spacing * 2 - 2);
            }
        }
        g2d.setColor(new Color(235, 235, 150));
        if (medals_have[0] || medals_obtained[0]) {
            g2d.setFont(new Font("monospaced", 3, fontheight - 4));
            g2d.drawImage(bronze_star_image, x_pos + x_tab_offset + 80, y_pos + y_times_offset - 20, null);
        } else {
            g2d.setFont(new Font("monospaced", 2, fontheight - 4));
        }
        g2d.drawString(bronze, x_pos + x_tab_offset - 14, y_pos + y_times_offset);
        if (medals_have[1] || medals_obtained[1]) {
            g2d.setFont(new Font("monospaced", 3, fontheight - 4));
            g2d.drawImage(silver_star_image, x_pos + x_tab_offset + 80, y_pos + y_times_offset + spacing - 20, null);
        } else {
            g2d.setFont(new Font("monospaced", 2, fontheight - 4));
        }
        g2d.drawString(silver, x_pos + x_tab_offset - 14, y_pos + y_times_offset + spacing);
        if (medals_have[2] || medals_obtained[2]) {
            g2d.setFont(new Font("monospaced", 3, fontheight - 4));
            g2d.drawImage(gold_star_image, x_pos + x_tab_offset + 80, y_pos + y_times_offset + spacing * 2 - 20, null);
        } else {
            g2d.setFont(new Font("monospaced", 2, fontheight - 4));
        }
        g2d.drawString(gold, x_pos + x_tab_offset - 14, y_pos + y_times_offset + spacing * 2);
    }

    static {
        image_loader = new ImageLoader();
        show_menu = false;
        is_record = false;
        start_timer = new Timer();
        buttons = new LevelClearedButton[4];
        medals_have = new boolean[3];
        medals_obtained = new boolean[3];
    }

    public class LevelClearedButton {
        public int x;
        public int y;
        public int width;
        public int height;
        public int fontsize;
        private String text;
        public boolean selected;
        public boolean disabled;

        public LevelClearedButton(int x, int y, String text) {
            this.width = 100;
            this.height = 20;
            this.fontsize = 14;
            this.x = x;
            this.y = y;
            this.text = text;
            this.selected = false;
            this.disabled = false;
        }

        public Rectangle getHitArea() {
            return new Rectangle(this.x, this.y, this.width, this.height);
        }

        public void paintComponent(Graphics g2d) {
            g2d.setFont(new Font("monospaced", 1, this.fontsize));
            if (this.selected) {
                if (GamePanel.time.getTt() % 0.4f > 0.2f) {
                    g2d.setColor(new Color(70, 70, 70));
                    g2d.fillRect(this.x + 2, this.y + 2, this.width + 32, this.height - 4);
                    g2d.setColor(new Color(120, 120, 120));
                    g2d.fillRect(this.x - 4, this.y - 4, this.width + 24, this.height + 8);
                    g2d.setColor(new Color(150, 150, 90));
                    g2d.fillRect(this.x, this.y, this.width + 16, this.height);
                } else {
                    g2d.setColor(new Color(20, 20, 95));
                    g2d.fillRect(this.x, this.y, this.width, this.height);
                }
                g2d.setColor(new Color(250, 0, 0));
            } else if (this.disabled) {
                g2d.setColor(new Color(80, 80, 80));
                g2d.fillRect(this.x, this.y, this.width, this.height);
                g2d.setColor(new Color(160, 160, 160));
                g2d.fillRoundRect(this.x + 4, this.y + 4, this.width - 8, this.height - 8, 6, 6);
                g2d.setColor(new Color(35, 35, 35));
            } else {
                g2d.setColor(new Color(20, 20, 95));
                g2d.fillRect(this.x, this.y, this.width, this.height);
                g2d.setColor(new Color(60, 60, 140));
                g2d.fillRoundRect(this.x + 4, this.y + 4, this.width - 8, this.height - 8, 6, 6);
                g2d.setColor(new Color(230, 100, 100));
            }
            int offset = this.height / 2 + this.fontsize / 2 - 3;
            g2d.drawString(this.text, this.x + 10, this.y + offset);
        }
    }

}

