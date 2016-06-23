package pp.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pp.game.GamePanel;
import pp.levels.Level;
import pp.utilities.Float2;

public class TimedPlatform extends Platform {
	
    private static BufferedImage[] timed_platform_images = new BufferedImage[3];
    private float countdown_time;
    private float current_time;
    private boolean exploding = false;
    //private boolean finished;
    //private Timer explode_timer;

    public TimedPlatform(Float2 position, float countdown_time) {
        super(position, 80.0f, false);
        this.current_time = this.countdown_time = countdown_time / 2.0f;
    }

    public TimedPlatform(Float2 position, int countdown_time) {
        this(position, (float)countdown_time);
    }

    @Override
    public void resetPlatform(Level level) {
        super.resetPlatform(level);
        this.exploding = false;
        //this.finished = false;
        this.current_time = this.countdown_time;
    }

    @Override
    public int activatePlatform() {
        int return_value = super.activatePlatform();
        return return_value;
    }

    @Override
    public void runPlatform(Player player, Level level) {
        if (this.activated && !this.exploding) {
            this.current_time -= GamePanel.time.getDt();
            if (this.current_time < 0.0f) {
                this.solid = false;
                this.exploding = true;
                //this.explode_timer = new Timer();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        int image_x = Math.round(this.f2_pos.x);
        int image_y = Math.round(this.f2_pos.y);
        if (!this.activated) {
            g2d.setColor(Color.RED);
            g2d.fillRect(image_x + 29, image_y + 2, 36, 4);
        }
        if (!this.exploding) {
            if (this.current_time < 0.5f) {
                if (this.current_time % 0.1f > 0.05f) {
                    g2d.drawImage(timed_platform_images[1], image_x, image_y, null);
                } else {
                    g2d.drawImage(timed_platform_images[2], image_x, image_y, null);
                }
            } else {
                g2d.drawImage(timed_platform_images[0], image_x, image_y, null);
            }
            int display_number = (int)(this.current_time * 2.0f);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("monospaced", 1, 18));
            g2d.drawString("" + display_number, image_x + 35, image_y + 20);
            g2d.setColor(Color.GREEN);
            int rect_w = 2;
            int rect_h = 4;
            int rect_x = image_x + 29;
            int rect_y = image_y + 2;
            int x = 5;
            
            // 1/12
            if (this.current_time % 0.5f > 0.0833f) {
                g2d.fillRect(rect_x, rect_y, rect_w, rect_h);
            }
            // 1/6
            if (this.current_time % 0.5f > 0.1666f) {
                g2d.fillRect(rect_x + x, rect_y, rect_w, rect_h);
            }
            // 1/4
            if (this.current_time % 0.5f > 0.24990001f) {
                g2d.fillRect(rect_x + 2 * x, rect_y, rect_w, rect_h);
            }
            // 1/3
            if (this.current_time % 0.5f > 0.3332f) {
                g2d.fillRect(rect_x + 3 * x, rect_y, rect_w, rect_h);
            }
            // 6/13 huh???
            if (this.current_time % 0.5f > 0.4165f) {
                g2d.fillRect(rect_x + 4 * x, rect_y, rect_w, rect_h);
            }
        }
    }

    public static void loadImage() {
        try {
            TimedPlatform.timed_platform_images[0] = platform_image.getSubimage(0, 28, 80, 28);
            TimedPlatform.timed_platform_images[1] = platform_image.getSubimage(0, 56, 80, 28);
            TimedPlatform.timed_platform_images[2] = platform_image.getSubimage(0, 84, 80, 28);
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR: Platform image hasn't been loaded.");
        }
    }
}

