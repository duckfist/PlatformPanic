package pp.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pp.levels.Level;
import pp.utilities.FloatRect;
import pp.utilities.ImageLoader;
import pp.utilities.Timer;

public class SpringBoard extends OtherObject {
	
    private Timer m_timer;
    public float m_power;
    public boolean m_upsidedown;
    private static ImageLoader m_image_loader = new ImageLoader();
    private static BufferedImage[] m_spring_images = new BufferedImage[4];
    private static BufferedImage[] m_upside_down = new BufferedImage[4];

    public SpringBoard(float x, float y, float power, boolean ceiling) {
        super(x, y, 32.0f, 32.0f);
        this.m_power = power;
        this.m_upsidedown = ceiling;
        this.m_in_sight = true;
    }

    @Override
    public void objectRun(Player player, Level level) {
        FloatRect hitbox = new FloatRect(this.m_pos, this.m_widthheight.x, this.m_widthheight.y);
        if (player.getHitArea().intersects(hitbox)) {
            float translation = 0.0f;
            if (!this.m_upsidedown && player.falling) {
                translation = this.m_pos.y - (player.f2_pos.y + 44.0f);
                player.f2_pos.translate(0.0f, translation);
                player.springBoardLaunch(this.m_power);
                SoundClip.SPRING.play();
                this.m_timer = new Timer();
            } else if (this.m_upsidedown && player.jumping) {
                translation = this.m_pos.y + this.m_widthheight.y - player.f2_pos.y;
                player.f2_pos.translate(0.0f, translation);
                player.springBoardUpsideDown();
                SoundClip.SPRING.play();
                this.m_timer = new Timer();
            }
        }
        if (this.m_timer != null) {
            this.m_timer.update();
            if (this.m_timer.getTt() > 1.4f) {
                this.m_timer = null;
            }
        }
    }

    @Override
    public void resetObject(Level level) {
    }

    @Override
    public void paintObject(Graphics g) {
        int image_x = Math.round(this.m_pos.x);
        int image_y = Math.round(this.m_pos.y);
        //int image_w = Math.round(this.m_widthheight.x);
        //int image_h = Math.round(this.m_widthheight.y);
        if (this.m_timer != null) {
            if (this.m_timer.getTt() < 0.08f) {
                if (this.m_upsidedown) {
                    g.drawImage(m_upside_down[1], image_x, image_y, null);
                } else {
                    g.drawImage(m_spring_images[1], image_x, image_y, null);
                }
            } else if (this.m_timer.getTt() < 0.16f) {
                if (this.m_upsidedown) {
                    g.drawImage(m_upside_down[2], image_x, image_y, null);
                } else {
                    g.drawImage(m_spring_images[2], image_x, image_y, null);
                }
            } else if (this.m_timer.getTt() < 0.26f) {
                if (this.m_upsidedown) {
                    g.drawImage(m_upside_down[3], image_x, image_y, null);
                } else {
                    g.drawImage(m_spring_images[3], image_x, image_y, null);
                }
            } else if (this.m_timer.getTt() < 0.4f) {
                if (this.m_upsidedown) {
                    g.drawImage(m_upside_down[1], image_x, image_y, null);
                } else {
                    g.drawImage(m_spring_images[1], image_x, image_y, null);
                }
            } else if (this.m_timer.getTt() < 0.58f) {
                if (this.m_upsidedown) {
                    g.drawImage(m_upside_down[2], image_x, image_y, null);
                } else {
                    g.drawImage(m_spring_images[2], image_x, image_y, null);
                }
            } else if (this.m_timer.getTt() < 0.8f) {
                if (this.m_upsidedown) {
                    g.drawImage(m_upside_down[3], image_x, image_y, null);
                } else {
                    g.drawImage(m_spring_images[3], image_x, image_y, null);
                }
            } else if (this.m_timer.getTt() < 1.06f) {
                if (this.m_upsidedown) {
                    g.drawImage(m_upside_down[2], image_x, image_y, null);
                } else {
                    g.drawImage(m_spring_images[2], image_x, image_y, null);
                }
            } else if (this.m_upsidedown) {
                g.drawImage(m_upside_down[2], image_x, image_y, null);
            } else {
                g.drawImage(m_spring_images[2], image_x, image_y, null);
            }
        } else if (this.m_upsidedown) {
            g.drawImage(m_upside_down[0], image_x, image_y, null);
        } else {
            g.drawImage(m_spring_images[0], image_x, image_y, null);
        }
    }

    public static void loadImages() {
        BufferedImage spring_map = m_image_loader.loadImage("/Graphics/SpringMap.png");
        SpringBoard.m_spring_images[0] = spring_map.getSubimage(0, 0, 32, 32);
        SpringBoard.m_spring_images[1] = spring_map.getSubimage(0, 32, 32, 32);
        SpringBoard.m_spring_images[2] = spring_map.getSubimage(0, 64, 32, 32);
        SpringBoard.m_spring_images[3] = spring_map.getSubimage(0, 96, 32, 32);
        SpringBoard.m_upside_down[0] = spring_map.getSubimage(32, 0, 32, 32);
        SpringBoard.m_upside_down[1] = spring_map.getSubimage(32, 32, 32, 32);
        SpringBoard.m_upside_down[2] = spring_map.getSubimage(32, 64, 32, 32);
        SpringBoard.m_upside_down[3] = spring_map.getSubimage(32, 96, 32, 32);
    }
}

