package pp.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pp.levels.Level;
import pp.utilities.Float2;
import pp.utilities.Timer;

public class DisappearingPlatform extends Platform {
	
    private static BufferedImage[] disappearing_platform_images = new BufferedImage[3];
    public static final int NUM_PHASES = 4;
    public static final float INTERVAL = 1.0f;
    public static final float FLASH_DISPLAY = 0.1f;
    public static Timer phase_timer = new Timer();
    public static int current_phase = 0;
    public int phase_issued;
    public boolean appeared;
    public static boolean[] phases_visible = new boolean[]{false, false, false, false};

    public DisappearingPlatform(Float2 position, int phase_issued) {
        super(position, 64.0f, false);
        this.phase_issued = phase_issued;
        this.appeared = true;
    }

    public static void loadImage() {
        try {
            DisappearingPlatform.disappearing_platform_images[1] = platform_image.getSubimage(0, 168, 64, 28);
            DisappearingPlatform.disappearing_platform_images[0] = platform_image.getSubimage(0, 196, 64, 28);
            DisappearingPlatform.disappearing_platform_images[2] = platform_image.getSubimage(0, 224, 64, 28);
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR: Platform image hasn't been loaded.");
        }
    }

    public static void operateDisappearingPlatforms() {
        phase_timer.update();
        if (phase_timer.getTt() > 4.0f) {
            phase_timer.initTimer();
        }
        if (current_phase != (int)(phase_timer.getTt() / 1.0f)) {
            current_phase = (int)(phase_timer.getTt() / 1.0f);
            int next_phase = (current_phase + 1) % 4;
            if (next_phase == 0 && phases_visible[next_phase]) {
                SoundClip.DISAPPEAR0.play();
            }
            if (next_phase == 1 && phases_visible[next_phase]) {
                SoundClip.DISAPPEAR1.play();
            }
            if (next_phase == 2 && phases_visible[next_phase]) {
                SoundClip.DISAPPEAR2.play();
            }
            if (next_phase == 3 && phases_visible[next_phase]) {
                SoundClip.DISAPPEAR3.play();
            }
        }
    }

    @Override
    public void resetPlatform(Level level) {
        super.resetPlatform(level);
        this.appeared = true;
        phase_timer.initTimer();
    }

    @Override
    public boolean testPlayerCollision(Player player, Level level) {
        if (!this.appeared) {
            return false;
        }
        boolean return_value = super.testPlayerCollision(player, level);
        return return_value;
    }

    @Override
    public void runPlatform(Player player, Level level) {
        if (this.phase_issued != current_phase && this.phase_issued != (current_phase + 1) % 4) {
            this.appeared = false;
            this.solid = false;
        } else {
            this.appeared = true;
            this.solid = true;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        int image_x = Math.round(this.f2_pos.x);
        int image_y = Math.round(this.f2_pos.y);
        if (this.appeared) {
            if (!this.activated) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.GREEN);
            }
            g2d.fillRect(image_x + 2, image_y + 11, 60, 6);
            if (this.phase_issued != current_phase && phase_timer.getTt() % 1.0f < 0.1f) {
                g2d.drawImage(disappearing_platform_images[0], image_x, image_y, null);
            } else {
                g2d.drawImage(disappearing_platform_images[1], image_x, image_y, null);
            }
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("monospaced", 0, 14));
            g2d.drawString("" + (this.phase_issued + 1), image_x + 28, image_y + 19);
        } else if (this.activated) {
            g2d.drawImage(disappearing_platform_images[2], image_x, image_y, null);
        }
    }
}

