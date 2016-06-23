package pp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pp.entities.Effects;
import pp.game.GamePanel;
import pp.utilities.Timer;

public class LevelToken
extends MenuElement {
    private static final Rectangle INFOPANEL_RECT = new Rectangle(45, 20, 422, 210);
    private static final Color INFOPANEL_BGCOLOR = new Color(35, 70, 175);
    private static final Font INFOPANEL_FONT1 = new Font("monospaced", 1, 28);
    private static final Color INFOPANEL_FONT1COLOR = new Color(255, 255, 255);
    private static final int INFOPANEL_FONT1X = LevelToken.INFOPANEL_RECT.x + 30 + LevelToken.INFOPANEL_RECT.width / 2;
    private static final int INFOPANEL_FONT1Y = LevelToken.INFOPANEL_RECT.y + 40;
    private static final Font INFOPANEL_FONT2 = new Font("sanserif", 1, 16);
    private static final int INFOPANEL_FONT2X = INFOPANEL_FONT1X;
    private static final int INFOPANEL_FONT2Y = INFOPANEL_FONT1Y + 28;
    private static final Font INFOPANEL_FONT3 = new Font("monospaced", 3, 22);
    private static final int INFOPANEL_FONT3X = LevelToken.INFOPANEL_RECT.x + 90;
    private static final int INFOPANEL_FONT3Y = LevelToken.INFOPANEL_RECT.y + 110;
    private static final Color INFOPANEL_FONT3COLOR = new Color(225, 225, 100);
    private int level_number;
    public boolean locked;
    public boolean finished;
    public boolean completed;
    public boolean level_exists;
//    private long high_score_num;
//    private String high_score_string;

    public LevelToken(int left_x, int up_y, int level_number) {
        this.x_pos = left_x;
        this.y_pos = up_y;
        this.level_number = level_number;
        this.element_name = MenuElementName.LEVELTOKEN;
        this.flash_time = new Timer();
        this.flash_start = false;
        this.currently_selected = false;
        this.level_exists = true;
        if (LevelClock.getBronzeTime(level_number) == 0) {
            this.level_exists = false;
        }
        this.hit_area = new Rectangle(left_x - 8, up_y - 8, 16, 16);
        try {
            this.locked = GamePanel.saveData.levels[level_number].locked;
            this.finished = GamePanel.saveData.levels[level_number].finished;
            this.completed = GamePanel.saveData.levels[level_number].completed;
        }
        catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("ERROR: " + level_number + " doesn't exist in the dataset.");
        }
        switch (this.level_number) {
            case 0: {
                this.element_text = "Level 1-1";
                break;
            }
            case 1: {
                this.element_text = "Level 1-2";
                break;
            }
            case 2: {
                this.element_text = "Level 1-3";
                break;
            }
            case 3: {
                this.element_text = "Level 1-4";
                break;
            }
            case 4: {
                this.element_text = "Level 1-5";
                break;
            }
            case 5: {
                this.element_text = "Level 2-1";
                break;
            }
            case 6: {
                this.element_text = "Level 2-2";
                break;
            }
            case 7: {
                this.element_text = "Level 2-3";
                break;
            }
            case 8: {
                this.element_text = "Level 2-4";
                break;
            }
            case 9: {
                this.element_text = "Level 2-5";
                break;
            }
            case 10: {
                this.element_text = "Level 3-1";
                break;
            }
            case 11: {
                this.element_text = "Level 3-2";
                break;
            }
            case 12: {
                this.element_text = "Level 3-3";
                break;
            }
            case 13: {
                this.element_text = "Level 3-4";
                break;
            }
            case 14: {
                this.element_text = "Level 3-5";
                break;
            }
            case 15: {
                this.element_text = "Level 4-1";
                break;
            }
            case 16: {
                this.element_text = "Level 4-2";
                break;
            }
            case 17: {
                this.element_text = "Level 4-3";
                break;
            }
            case 18: {
                this.element_text = "Level 4-4";
                break;
            }
            case 19: {
                this.element_text = "Level 4-5";
                break;
            }
            case 20: {
                this.element_text = "Level 5-1";
                break;
            }
            case 21: {
                this.element_text = "Level 5-2";
                break;
            }
            case 22: {
                this.element_text = "Level 5-3";
                break;
            }
            case 23: {
                this.element_text = "Level 5-4";
                break;
            }
            case 24: {
                this.element_text = "Level 5-5";
                break;
            }
//            case 25: {
//                this.element_text = "Level 6-1";
//                break;
//            }
//            case 26: {
//                this.element_text = "Level 6-2";
//                break;
//            }
//            case 27: {
//                this.element_text = "Level 6-3";
//                break;
//            }
//            case 28: {
//                this.element_text = "Level 6-4";
//                break;
//            }
//            case 29: {
//                this.element_text = "Level 6-5";
//                break;
//            }
            default: {
                this.element_text = "DEFAULT";
            }
        }
    }

    public boolean getLocked() {
        return this.locked;
    }

    public boolean getFinished() {
        return this.finished;
    }

    public int getLevelNum() {
        return this.level_number;
    }

    public void lockLevel(boolean value) {
        this.locked = value;
    }

//    public void setHighScore(long completion_time) {
//        this.high_score_num = completion_time;
//    }

    @Override
    public void paintMenuElement(Graphics2D g2d) {
        Color color1;
        Color color2;
        if (this.locked) {
            color1 = new Color(96, 96, 96);
            color2 = new Color(48, 48, 48);
        } else if (this.completed) {
            color1 = new Color(24, 216, 24);
            color2 = Color.GREEN;
        } else if (this.finished) {
            color1 = Color.YELLOW;
            color2 = new Color(212, 128, 0);
        } else {
            color1 = new Color(232, 64, 0);
            color2 = Color.RED;
        }
        if (this.currently_selected) {
            this.paintInfoPanel(g2d, color1, color2);
            g2d.setColor(color2);
            g2d.fillOval(this.x_pos - 10, this.y_pos - 10, 20, 20);
            g2d.setColor(color1);
            g2d.fillOval(this.x_pos - 8, this.y_pos - 8, 16, 16);
        } else {
            g2d.setColor(color2);
            g2d.fillOval(this.x_pos - 5, this.y_pos - 5, 10, 10);
            g2d.setColor(color1);
            g2d.fillOval(this.x_pos - 4, this.y_pos - 4, 8, 8);
        }
        if (!this.level_exists) {
            g2d.setColor(Color.RED);
            int[] x_pts1 = new int[]{this.x_pos - 8, this.x_pos - 6, this.x_pos + 10, this.x_pos + 8};
            int[] y_pts1 = new int[]{this.y_pos - 7, this.y_pos - 9, this.y_pos + 7, this.y_pos + 9};
            g2d.fillPolygon(x_pts1, y_pts1, 4);
            int[] x_pts2 = new int[]{this.x_pos + 10, this.x_pos + 8, this.x_pos - 8, this.x_pos - 6};
            int[] y_pts2 = new int[]{this.y_pos - 7, this.y_pos - 9, this.y_pos + 7, this.y_pos + 9};
            g2d.fillPolygon(x_pts2, y_pts2, 4);
        }
    }

    public void paintInfoPanel(Graphics2D g2d, Color color1, Color color2) {
        g2d.setColor(INFOPANEL_BGCOLOR);
        g2d.fill3DRect(LevelToken.INFOPANEL_RECT.x, LevelToken.INFOPANEL_RECT.y, LevelToken.INFOPANEL_RECT.width, LevelToken.INFOPANEL_RECT.height, true);
        Effects.drawStringShadow(g2d, this.element_text, INFOPANEL_FONT1, INFOPANEL_FONT1COLOR, INFOPANEL_FONT1X, INFOPANEL_FONT1Y);
        g2d.setFont(INFOPANEL_FONT2);
        g2d.setColor(color1);
        String completion = this.locked ? "Unavailable" : (this.completed ? "Completed" : (this.finished ? "Finished..." : "Incomplete"));
        Effects.drawStringShadow(g2d, completion, INFOPANEL_FONT2, color1, INFOPANEL_FONT2X, INFOPANEL_FONT2Y);
        g2d.setColor(new Color(200, 200, 200));
        g2d.setFont(new Font("sanserif", 1, 12));
        g2d.drawString("Best time:", LevelToken.INFOPANEL_RECT.x + 12, LevelToken.INFOPANEL_RECT.y + 16);
        Effects.drawStringShadow(g2d, GamePanel.saveData.getRecordTime(this.level_number), new Font("monospaced", 0, 24), INFOPANEL_FONT1COLOR, LevelToken.INFOPANEL_RECT.x + 32, LevelToken.INFOPANEL_RECT.y + 28 + 12);
        g2d.setColor(new Color(32, 32, 128));
        g2d.fill3DRect(LevelToken.INFOPANEL_RECT.x + 10, LevelToken.INFOPANEL_RECT.y + 64, 216, 136, true);
        g2d.setColor(new Color(200, 160, 50));
        g2d.setFont(new Font("sanserif", 1, 12));
        g2d.drawString("Target Times", LevelToken.INFOPANEL_RECT.x + 16, LevelToken.INFOPANEL_RECT.y + 80);
        g2d.setFont(INFOPANEL_FONT3);
        g2d.setColor(INFOPANEL_FONT3COLOR);
        Effects.drawStringShadow(g2d, LevelClock.getClockTimeMs(LevelClock.getBronzeTime(this.level_number)), INFOPANEL_FONT3, INFOPANEL_FONT3COLOR, INFOPANEL_FONT3X, INFOPANEL_FONT3Y);
        if (GamePanel.saveData.getBronze(this.level_number)) {
            g2d.drawImage(LevelCleared.bronze_star_image, INFOPANEL_FONT3X - 24, INFOPANEL_FONT3Y - 22, null);
        }
        Effects.drawStringShadow(g2d, LevelClock.getClockTimeMs(LevelClock.getSilverTime(this.level_number)), INFOPANEL_FONT3, INFOPANEL_FONT3COLOR, INFOPANEL_FONT3X, INFOPANEL_FONT3Y + (INFOPANEL_FONT3.getSize() + 4));
        if (GamePanel.saveData.getSilver(this.level_number)) {
            g2d.drawImage(LevelCleared.silver_star_image, INFOPANEL_FONT3X - 24, INFOPANEL_FONT3Y + (INFOPANEL_FONT3.getSize() + 4) - 22, null);
        }
        Effects.drawStringShadow(g2d, LevelClock.getClockTimeMs(LevelClock.getGoldTime(this.level_number)), INFOPANEL_FONT3, INFOPANEL_FONT3COLOR, INFOPANEL_FONT3X, INFOPANEL_FONT3Y + (INFOPANEL_FONT3.getSize() + 4) * 2);
        if (GamePanel.saveData.getGold(this.level_number)) {
            g2d.drawImage(LevelCleared.gold_star_image, INFOPANEL_FONT3X - 24, INFOPANEL_FONT3Y + (INFOPANEL_FONT3.getSize() + 4) * 2 - 22, null);
        }
        if (!this.flash_start) {
            this.flash_start = true;
            this.flash_time.initTimer();
        }
        this.flash_time.update();
        if (this.flash_time.getTt() < 0.1f) {
            return;
        }
        if (this.flash_time.getTt() > 0.2f) {
            this.flash_time.initTimer();
        }
    }
}

