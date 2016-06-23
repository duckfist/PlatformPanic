package pp.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pp.levels.Level;
import pp.utilities.FloatRect;
import pp.utilities.ImageLoader;
import pp.utilities.Timer;

public class AngledSpringBoard extends OtherObject {
	
    private Timer m_timer;
    private boolean facing_left;
    public float m_jumppower;
    public float m_xvelocity;
    private static ImageLoader m_image_loader = new ImageLoader();
    private static BufferedImage[] m_spring_images = new BufferedImage[4];
    private BufferedImage image1;
    private BufferedImage image2;

    public AngledSpringBoard(float x, float y, float jumppower, float xvelocity) {
        super(x, y, 32.0f, 32.0f);
        this.m_jumppower = jumppower;
        this.m_xvelocity = xvelocity;
        this.m_in_sight = true;
        this.facing_left = this.m_xvelocity <= 0.0f;
        if (this.facing_left) {
            this.image1 = m_spring_images[0];
            this.image2 = m_spring_images[1];
        } else {
            this.image1 = m_spring_images[2];
            this.image2 = m_spring_images[3];
        }
    }

    @Override
    public void objectRun(Player player, Level level) {
        FloatRect hitbox = new FloatRect(this.m_pos, this.m_widthheight.x, this.m_widthheight.y);
        if (player.getHitArea().intersects(hitbox) && player.falling) {
            player.f2_pos.setLoc(this.m_pos);
            float dx = this.m_pos.x - player.f2_pos.x;
            float dy = player.f2_pos.y - this.m_pos.y - 24.0f;
            player.f2_pos.translate(dx, dy);
            player.springBoardAngledLaunch(this.m_jumppower, this.m_xvelocity);
            SoundClip.SPRING.play();
            this.m_timer = new Timer();
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
        if (!this.m_in_sight) {
            return;
        }
        int image_x = Math.round(this.m_pos.x);
        int image_y = Math.round(this.m_pos.y);
//        int image_w = Math.round(this.m_widthheight.x);
//        int image_h = Math.round(this.m_widthheight.y);
        if (this.m_timer != null) {
            g.drawImage(this.image2, image_x, image_y, null);
        } else {
            g.drawImage(this.image1, image_x, image_y, null);
        }
    }

    public static void loadImages() {
        BufferedImage spring_map = m_image_loader.loadImage("/Graphics/AngledSpringMap.png");
        AngledSpringBoard.m_spring_images[0] = spring_map.getSubimage(0, 0, 32, 32);
        AngledSpringBoard.m_spring_images[1] = spring_map.getSubimage(0, 32, 32, 32);
        AngledSpringBoard.m_spring_images[2] = spring_map.getSubimage(0, 64, 32, 32);
        AngledSpringBoard.m_spring_images[3] = spring_map.getSubimage(0, 96, 32, 32);
    }
}

