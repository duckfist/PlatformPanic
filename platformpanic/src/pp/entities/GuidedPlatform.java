package pp.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pp.levels.Level;
import pp.utilities.Float2;
//import pp.utilities.Timer;

public class GuidedPlatform extends Platform {
	
    private static BufferedImage guided_platform_image;
    public static final float SPEED = 1.0f;
    public Float2 f2_vel;
    public GuideRail attached_rail;
    public int current_direction;
    public Float2 initial_position = new Float2();
    public GuideRail initial_rail;
    //private Timer animation_timer;

    public GuidedPlatform(Float2 position) {
        super(position, 32.0f, false);
        this.initial_position = position.getCopy();
        this.f2_vel = new Float2(0.0f, 1.0f);
        this.current_direction = 3;
    }

    public static void loadImage() {
        try {
            guided_platform_image = platform_image.getSubimage(0, 252, 32, 32);
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
        this.area.x = this.f2_pos.x;
        this.area.y = this.f2_pos.y;
        this.current_direction = 3;
        this.f2_vel = new Float2(0.0f, 1.0f);
        this.initial_rail = null;
    }

    @Override
    public void runPlatform(Player player, Level level) {
        if (this.activated) {
            if (this.attached_rail == null) {
                return;
            }
            Float2 old_pos = this.f2_pos.getCopy();
            switch (this.attached_rail.m_railtype) {
                case 0: {
                    if (this.current_direction == 1) {
                        if (this.f2_pos.y >= this.attached_rail.m_pos.y) break;
                        this.attached_rail.attached = false;
                        this.attached_rail = this.getRailAtPos(this.f2_pos.x + 16.0f, this.f2_pos.y - 16.0f, level);
                        this.attached_rail.attached = true;
                        break;
                    }
                    if (this.f2_pos.y <= this.attached_rail.m_pos.y) break;
                    this.attached_rail.attached = false;
                    this.attached_rail = this.getRailAtPos(this.f2_pos.x + 16.0f, this.f2_pos.y + 48.0f, level);
                    this.attached_rail.attached = true;
                    break;
                }
                case 1: {
                    if (this.current_direction == 0) {
                        if (this.f2_pos.x <= this.attached_rail.m_pos.x) break;
                        this.attached_rail.attached = false;
                        this.attached_rail = this.getRailAtPos(this.f2_pos.x + 48.0f, this.f2_pos.y + 16.0f, level);
                        this.attached_rail.attached = true;
                        break;
                    }
                    if (this.f2_pos.x >= this.attached_rail.m_pos.x) break;
                    this.attached_rail.attached = false;
                    this.attached_rail = this.getRailAtPos(this.f2_pos.x - 16.0f, this.f2_pos.y + 16.0f, level);
                    this.attached_rail.attached = true;
                    break;
                }
                case 2: {
                    if (this.current_direction == 0 || this.current_direction == 3) {
                        if (this.f2_vel.x != 0.0f) {
                            if (this.f2_pos.x <= this.attached_rail.m_pos.x) break;
                            this.f2_pos.x = this.attached_rail.m_pos.x;
                            this.attached_rail.attached = false;
                            this.attached_rail = this.getRailAtPos(this.f2_pos.x + 16.0f, this.f2_pos.y + 48.0f, level);
                            this.attached_rail.attached = true;
                            this.f2_vel = new Float2(this.f2_vel.y, this.f2_vel.x);
                            this.current_direction = 3;
                            break;
                        }
                        if (this.f2_pos.y <= this.attached_rail.m_pos.y) break;
                        this.f2_pos.y = this.attached_rail.m_pos.y;
                        this.attached_rail.attached = false;
                        this.attached_rail = this.getRailAtPos(this.f2_pos.x + 48.0f, this.f2_pos.y + 16.0f, level);
                        this.attached_rail.attached = true;
                        this.f2_vel = new Float2(this.f2_vel.y, this.f2_vel.x);
                        this.current_direction = 0;
                        break;
                    }
                    if (this.f2_vel.x != 0.0f) {
                        if (this.f2_pos.x >= this.attached_rail.m_pos.x) break;
                        this.f2_pos.x = this.attached_rail.m_pos.x;
                        this.attached_rail.attached = false;
                        this.attached_rail = this.getRailAtPos(this.f2_pos.x + 16.0f, this.f2_pos.y - 16.0f, level);
                        this.attached_rail.attached = true;
                        this.f2_vel = new Float2(this.f2_vel.y, this.f2_vel.x);
                        this.current_direction = 1;
                        break;
                    }
                    if (this.f2_pos.y >= this.attached_rail.m_pos.y) break;
                    this.f2_pos.y = this.attached_rail.m_pos.y;
                    this.attached_rail.attached = false;
                    this.attached_rail = this.getRailAtPos(this.f2_pos.x - 16.0f, this.f2_pos.y + 16.0f, level);
                    this.attached_rail.attached = true;
                    this.f2_vel = new Float2(this.f2_vel.y, this.f2_vel.x);
                    this.current_direction = 2;
                    break;
                }
                case 3: {
                    if (this.current_direction == 0 || this.current_direction == 1) {
                        if (this.f2_vel.x != 0.0f) {
                            if (this.f2_pos.x <= this.attached_rail.m_pos.x) break;
                            this.f2_pos.x = this.attached_rail.m_pos.x;
                            this.attached_rail.attached = false;
                            this.attached_rail = this.getRailAtPos(this.f2_pos.x + 16.0f, this.f2_pos.y - 16.0f, level);
                            this.attached_rail.attached = true;
                            this.f2_vel = new Float2(0.0f, - this.f2_vel.x);
                            this.current_direction = 1;
                            break;
                        }
                        if (this.f2_pos.y >= this.attached_rail.m_pos.y) break;
                        this.f2_pos.y = this.attached_rail.m_pos.y;
                        this.attached_rail.attached = false;
                        this.attached_rail = this.getRailAtPos(this.f2_pos.x + 48.0f, this.f2_pos.y + 16.0f, level);
                        this.attached_rail.attached = true;
                        this.f2_vel = new Float2(- this.f2_vel.y, 0.0f);
                        this.current_direction = 0;
                        break;
                    }
                    if (this.f2_vel.x != 0.0f) {
                        if (this.f2_pos.x >= this.attached_rail.m_pos.x) break;
                        this.f2_pos.x = this.attached_rail.m_pos.x;
                        this.attached_rail.attached = false;
                        this.attached_rail = this.getRailAtPos(this.f2_pos.x + 16.0f, this.f2_pos.y + 48.0f, level);
                        this.attached_rail.attached = true;
                        this.f2_vel = new Float2(0.0f, - this.f2_vel.x);
                        this.current_direction = 3;
                        break;
                    }
                    if (this.f2_pos.y <= this.attached_rail.m_pos.y) break;
                    this.f2_pos.y = this.attached_rail.m_pos.y;
                    this.attached_rail.attached = false;
                    this.attached_rail = this.getRailAtPos(this.f2_pos.x - 16.0f, this.f2_pos.y + 16.0f, level);
                    this.attached_rail.attached = true;
                    this.f2_vel = new Float2(- this.f2_vel.y, 0.0f);
                    this.current_direction = 2;
                    break;
                }
                case 4: {
                    if (this.f2_pos.y < this.attached_rail.m_pos.y) {
                        this.f2_pos.y = this.attached_rail.m_pos.y;
                        this.attached_rail.attached = false;
                        this.attached_rail = this.getRailAtPos(this.f2_pos.x + 16.0f, this.f2_pos.y + 48.0f, level);
                        this.attached_rail.attached = true;
                        this.f2_vel = new Float2(0.0f, 1.0f);
                        this.current_direction = 3;
                        break;
                    }
                    this.f2_vel = new Float2(0.0f, -1.0f);
                    break;
                }
                case 5: {
                    if (this.f2_pos.x < this.attached_rail.m_pos.x) {
                        this.f2_pos.x = this.attached_rail.m_pos.x;
                        this.attached_rail.attached = false;
                        this.attached_rail = this.getRailAtPos(this.f2_pos.x + 48.0f, this.f2_pos.y + 16.0f, level);
                        this.attached_rail.attached = true;
                        this.f2_vel = new Float2(1.0f, 0.0f);
                        this.current_direction = 0;
                        break;
                    }
                    this.f2_vel = new Float2(-1.0f, 0.0f);
                    break;
                }
                case 6: {
                    if (this.f2_pos.y > this.attached_rail.m_pos.y) {
                        this.f2_pos.y = this.attached_rail.m_pos.y;
                        this.attached_rail.attached = false;
                        this.attached_rail = this.getRailAtPos(this.f2_pos.x + 16.0f, this.f2_pos.y - 16.0f, level);
                        this.attached_rail.attached = true;
                        this.f2_vel = new Float2(0.0f, -1.0f);
                        this.current_direction = 1;
                        break;
                    }
                    this.f2_vel = new Float2(0.0f, 1.0f);
                    break;
                }
                case 7: {
                    if (this.f2_pos.x > this.attached_rail.m_pos.x) {
                        this.f2_pos.x = this.attached_rail.m_pos.x;
                        this.attached_rail.attached = false;
                        this.attached_rail = this.getRailAtPos(this.f2_pos.x - 16.0f, this.f2_pos.y + 16.0f, level);
                        this.attached_rail.attached = true;
                        this.f2_vel = new Float2(-1.0f, 0.0f);
                        this.current_direction = 2;
                        break;
                    }
                    this.f2_vel = new Float2(1.0f, 0.0f);
                    break;
                }
            }
            this.f2_pos.translate(this.f2_vel);
            this.area.x = this.f2_pos.x;
            this.area.y = this.f2_pos.y;
            if (this.player_standing) {
                Float2 translation = new Float2();
                translation.x = this.f2_pos.x - old_pos.x;
                translation.y = this.f2_pos.y - old_pos.y;
                player.f2_pos.translate(translation);
                Float2 test_point = player.f2_pos.getCopy();
                test_point.translate(level.left_level_border, level.up_level_border);
                if (translation.x < 0.0f) {
                    test_point.translate(-1.0f, 0.0f);
                    LevelElement left_element = level.testCollisionPoint(test_point, false);
                    if (left_element == null) {
                        test_point.translate(0.0f, 24.0f);
                        left_element = level.testCollisionPoint(test_point, false);
                    }
                    if (left_element != null) {
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
                    }
                }
            }
        } else if (this.initial_rail == null) {
            try {
                int row = (int)((this.f2_pos.y + level.up_level_border + 48.0f) / 32.0f);
                int col = (int)((this.f2_pos.x + level.left_level_border + 16.0f) / 32.0f);
                this.initial_rail = this.attached_rail = (GuideRail)level.oo_2d_array[row][col];
                this.attached_rail.attached = true;
                if (this.initial_rail == null) {
                    System.out.println("ERROR: GuideRail placed incorrectly.");
                } else {
                    this.f2_pos.translate(0.0f, 32.0f);
                    this.area.translate(0.0f, 32.0f);
                }
            }
            catch (Exception e) {
                System.out.println("ERROR: GuideRail placed incorrectly.");
            }
        }
    }

    public GuideRail getRailAtPos(float x, float y, Level level) {
        try {
            int row = (int)((y + level.up_level_border) / 32.0f);
            int col = (int)((x + level.left_level_border) / 32.0f);
            GuideRail rail = (GuideRail)level.oo_2d_array[row][col];
            return rail;
        }
        catch (Exception e) {
            System.out.println("ERROR: Bad array position in getRailAtPos.");
            return null;
        }
    }

    public void movePlatform(Player player, Level level) {
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
        g2d.fillRect(image_x + 10, image_y + 5, 12, 6);
        g2d.drawImage(guided_platform_image, image_x, image_y, null);
    }
}

