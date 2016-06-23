package pp.entities;

import java.awt.Color;
import java.awt.Graphics;

import pp.levels.Level;
import pp.utilities.Float2;
import pp.utilities.Timer;

public class DeathExplosion extends OtherObject {
	
    private Float2 m_vel = new Float2();
    private Timer m_flash_timer;

    public DeathExplosion(float x, float y, float angle, float velocity) {
        super(x, y, 16.0f, 16.0f);
        this.m_vel.x = velocity * (float)Math.cos(angle);
        this.m_vel.y = velocity * (- (float)Math.sin(angle));
        this.m_in_sight = true;
        this.m_flash_timer = new Timer();
    }

    @Override
    public void objectRun(Player player, Level level) {
        this.m_flash_timer.update();
        this.m_pos.translate(this.m_vel.x * this.m_flash_timer.getDtScaled(), this.m_vel.y * this.m_flash_timer.getDtScaled());
    }

    @Override
    public void resetObject(Level level) {
        this.m_active = false;
    }

    @Override
    public void paintObject(Graphics g) {
        int image_x = Math.round(this.m_pos.x);
        int image_y = Math.round(this.m_pos.y);
        Color old_color = g.getColor();
        g.setColor(Color.WHITE);
        if (this.m_flash_timer.getTt() < 0.05f) {
            g.drawOval(image_x - 13, image_y - 13, 26, 26);
            g.fillOval(image_x - 10, image_y - 10, 20, 20);
        } else if (this.m_flash_timer.getTt() < 0.1f) {
            g.drawOval(image_x - 10, image_y - 10, 20, 20);
            g.fillOval(image_x - 7, image_y - 7, 14, 14);
        } else if (this.m_flash_timer.getTt() < 0.15f) {
            g.drawOval(image_x - 7, image_y - 7, 14, 14);
        } else if (this.m_flash_timer.getTt() < 0.2f) {
            g.drawOval(image_x - 10, image_y - 10, 20, 20);
            g.fillOval(image_x - 7, image_y - 7, 14, 14);
        } else {
            this.m_flash_timer.initTimer();
        }
        g.setColor(old_color);
    }
}

