package pp.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pp.game.GamePanel;
import pp.levels.Level;
import pp.utilities.Float2;
import pp.utilities.FloatRect;
import pp.utilities.ImageLoader;

public abstract class Platform {
	
    public Float2 f2_pos = new Float2();
    public float width;
    public FloatRect area;
    protected boolean activated = false;
    protected boolean deactivatable;
    protected boolean player_standing = false;
    protected static boolean static_standing = false;
    protected boolean in_sight = false;
    protected boolean solid = true;
    public static int num_platforms = 0;
    protected static BufferedImage platform_image;
    protected static final int IMAGE_WIDTH = 80;
    protected static final int IMAGE_HEIGHT = 28;
    private static ImageLoader image_loader;
    protected static Level current_level;

    public Platform(Float2 position, float width, boolean deactivatable) {
        this.f2_pos = position;
        this.width = width;
        this.area = new FloatRect(this.f2_pos, width, 6.0f);
        this.deactivatable = deactivatable;
        ++num_platforms;
    }

    public boolean getActivated() {
        return this.activated;
    }

    public boolean getPlayerStanding() {
        return this.player_standing;
    }

    public boolean getInSight() {
        return this.in_sight;
    }

    public static void setStaticStanding(boolean value) {
        static_standing = value;
    }

    public void setStanding(boolean value) {
        this.player_standing = value;
    }

    public int activatePlatform() {
        if (this.activated && this.deactivatable) {
            this.activated = false;
            return -1;
        }
        if (this.activated) {
            return 0;
        }
        this.activated = true;
        Float2 systempos = this.f2_pos.getCopy();
        systempos.translate(this.width / 2.0f, 0.0f);
        ParticleSystem platform_spark = new ParticleSystem(systempos, 200.0f, 0.3f, true, 0);
        Platform.current_level.particle_systems.add(platform_spark);
        ++GamePanel.saveData.total_platforms_activated;
        return 1;
    }

    public void resetPlatform(Level level) {
        this.activated = false;
        this.solid = true;
        this.player_standing = false;
    }

    public boolean testPlayerCollision(Player player, Level level) {
        
        if (!static_standing && this.player_standing) {
            this.player_standing = false;
        }
        if (player.getHitArea().intersects(this.area) && this.solid) {
            return true;
        }
        return false;
    }

    public void scroll(float x_speed, float y_speed) {
        this.f2_pos.translate(- x_speed, - y_speed);
        this.area.translate(- x_speed, - y_speed);
        this.in_sight = this.f2_pos.x >= -48.0f && this.f2_pos.x <= 560.0f && this.f2_pos.y <= 472.0f && this.f2_pos.y >= -24.0f;
    }

    public void paintComponent(Graphics g) {
    }

    public static void loadImages() {
        platform_image = image_loader.loadImage("/Graphics/platform_map.png");
    }

    public static void setLevel(Level level) {
        current_level = level;
    }

    public abstract void runPlatform(Player var1, Level var2);

    static {
        image_loader = new ImageLoader();
    }
}

