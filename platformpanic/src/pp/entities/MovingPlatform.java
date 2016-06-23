package pp.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pp.game.GamePanel;
import pp.levels.Level;
import pp.utilities.Float2;

public class MovingPlatform extends Platform {
	
    private static BufferedImage moving_platform_image;
    private static BufferedImage controllable_platform_image;
    private static BufferedImage[] arrow_images;
    public boolean move_on_activate;
    protected boolean controllable;
    public float vel_max;
    public float vel_current;
    public int current_direction;
    public int initial_direction;
    public Float2 initial_position = new Float2();
    //private Timer animation_timer;

    public MovingPlatform(Float2 position, int initial_direction, boolean move_on_activate, boolean deactivatable) {
        super(position, 80.0f, deactivatable);
        this.move_on_activate = move_on_activate;
        if (initial_direction > 3) {
            initial_direction = 3;
        } else if (initial_direction < 0) {
            initial_direction = 0;
        }
        this.initial_direction = initial_direction;
        this.current_direction = initial_direction;
        this.initial_position = position.getCopy();
        this.vel_current = initial_direction <= 1 ? 1.0f : -1.0f;
        this.controllable = false;
    }

    public static void loadImage() {
        try {
            moving_platform_image = platform_image.getSubimage(0, 112, 80, 28);
            controllable_platform_image = platform_image.getSubimage(0, 284, 80, 28);
            MovingPlatform.arrow_images[0] = platform_image.getSubimage(0, 140, 14, 14);
            MovingPlatform.arrow_images[1] = platform_image.getSubimage(0, 154, 14, 14);
            MovingPlatform.arrow_images[2] = platform_image.getSubimage(14, 140, 14, 14);
            MovingPlatform.arrow_images[3] = platform_image.getSubimage(14, 154, 14, 14);
            MovingPlatform.arrow_images[4] = platform_image.getSubimage(28, 140, 14, 14);
            MovingPlatform.arrow_images[5] = platform_image.getSubimage(28, 154, 14, 14);
            MovingPlatform.arrow_images[6] = platform_image.getSubimage(42, 140, 14, 14);
            MovingPlatform.arrow_images[7] = platform_image.getSubimage(42, 154, 14, 14);
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR: Platform image hasn't been loaded.");
        }
    }

    @Override
    public void resetPlatform(Level level) {
        super.resetPlatform(level);
        this.f2_pos = this.initial_position.getCopy();
        this.f2_pos.translate(- level.left_level_border, - level.up_level_border);
        this.area.x = this.initial_position.x;
        this.area.y = this.initial_position.y;
        this.area.translate(- level.left_level_border, - level.up_level_border);
        this.current_direction = this.initial_direction;
        this.vel_current = this.initial_direction <= 1 ? 1.0f : -1.0f;
    }

    @Override
    public void runPlatform(Player player, Level level) {
        if (this.move_on_activate) {
            if (this.activated) {
                this.movePlatform(player, level);
            }
        } else {
            this.movePlatform(player, level);
        }
    }

    public void movePlatform(Player player, Level level) {
        Float2 test_point = this.f2_pos.getCopy();
        Float2 old_pos = this.f2_pos.getCopy();
        test_point.translate(level.left_level_border, level.up_level_border);

        LevelElement collided = null;
        
        // Move the platform, reverse direction if it hit a tile
        if (this.current_direction % 2 == 0) {
            this.f2_pos.translate(this.vel_current * GamePanel.time.getDtScaled(), 0.0f);
            if (this.vel_current < 0.0f) {
                test_point.translate(0.0f, 16.0f);
            } else if (this.vel_current > 0.0f) {
                test_point.translate(this.width, 16.0f);
            }
        } else {
            this.f2_pos.translate(0.0f, this.vel_current * GamePanel.time.getDtScaled());
            if (this.vel_current < 0.0f) {
                test_point.translate(this.width / 2.0f, 0.0f);
            } else if (this.vel_current > 0.0f) {
                test_point.translate(this.width / 2.0f, 8.0f);
            }
        }
        
        // Check if it hit a tile and reverse its direction
        collided = level.testCollisionPoint(test_point, true);
        if (collided != null && !(collided instanceof SpikeFill)) {
            this.f2_pos = old_pos;
            this.vel_current *= -1.0f;
            this.current_direction += 2;
            this.current_direction %= 4;
        }
        this.area.x = this.f2_pos.x;
        this.area.y = this.f2_pos.y;
        
    	// Move the player with the platform
        if (this.player_standing) {
            Float2 translation = new Float2();
            translation.x = this.f2_pos.x - old_pos.x;
            translation.y = this.f2_pos.y - old_pos.y;
            player.f2_pos.translate(translation);
            test_point = player.f2_pos.getCopy();
            test_point.translate(level.left_level_border, level.up_level_border);
            if (translation.x < 0.0f) {
            	// Test tiles next to player for collision to push player
                test_point.translate(-1.0f, 0.0f);
                LevelElement left_element = level.testCollisionPoint(test_point, false);
                if (left_element == null) {
                    test_point.translate(0.0f, 24.0f);
                    left_element = level.testCollisionPoint(test_point, false);
                }
                if (left_element != null) {
                	// Pushed into spikes, die
                	if (left_element instanceof Spike) { 
                		player.dead = true; 
                		return;
                	}
                	// Push player backwards
                    player.f2_pos.translate(left_element.getXPos() + 32.0f - player.f2_pos.x, 0.0f);
                }
            } else if (translation.x > 0.0f) {
                test_point.translate(33.0f, 0.0f);
                LevelElement right_element = level.testCollisionPoint(test_point, false);
                if (right_element == null) {
                    test_point.translate(0.0f, 24.0f);
                    right_element = level.testCollisionPoint(test_point, false);
                }
                if (right_element != null) {
                    player.f2_pos.translate(right_element.getXPos() - (32.0f + player.f2_pos.x), 0.0f);
                    if (right_element instanceof Spike) { 
                		player.dead = true; 
                		return;
                	}
                }
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
        } else {
            g2d.setColor(Color.GREEN);
        }
        g2d.fillRect(image_x + 26, image_y + 2, 28, 8);
        if (this.controllable) {
            g2d.drawImage(controllable_platform_image, image_x, image_y, null);
        } else {
            g2d.drawImage(moving_platform_image, image_x, image_y, null);
        }
        if (this.activated || !this.move_on_activate) {
            if (GamePanel.time.getTt() % 0.2f < 0.1f) {
                g2d.drawImage(arrow_images[this.current_direction * 2], image_x + 33, image_y + 1, null);
            } else {
                g2d.drawImage(arrow_images[this.current_direction * 2 + 1], image_x + 33, image_y + 1, null);
            }
        } else {
            g2d.drawImage(arrow_images[this.current_direction * 2], image_x + 33, image_y + 1, null);
        }
    }

    static {
        arrow_images = new BufferedImage[8];
    }
}

