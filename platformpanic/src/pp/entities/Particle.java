package pp.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import pp.game.GamePanel;
import pp.utilities.Float2;
import pp.utilities.Timer;

public class Particle {
	
    public Float2 f2_pos;
    public Float2 f2_vel;
    public Float2 f2_acc;
    public int animation_frame;
    public boolean delete_flag;
    public float total_lifetime;
    public Timer timer_age;
    public Timer timer_animation;
    protected float width = 6.0f;
    protected float height = 6.0f;
    protected Color particle_color;

    public Particle(Float2 pos, Float2 vel, boolean gravity, float life_in_seconds, Color color) {
        this.total_lifetime = life_in_seconds;
        this.f2_pos = new Float2(pos);
        this.f2_vel = new Float2(vel);
        this.f2_acc = new Float2();
        this.animation_frame = 0;
        this.particle_color = color;
        this.timer_age = new Timer();
        this.timer_animation = new Timer();
        this.delete_flag = false;
        if (gravity) {
            this.f2_acc.y = 0.1f;
        }
    }

    public void moveParticle() {
        Float2 dt_vel = this.f2_vel.getCopy();
        Float2 dt_acc = this.f2_acc.getCopy();
        float scalar = GamePanel.time.getDtScaled();
        dt_vel.scale(scalar);
        dt_acc.scale(scalar);
        this.f2_pos.translate(this.f2_vel);
        this.f2_vel.translate(this.f2_acc);
        this.timer_age.update();
        this.timer_animation.update();
        if (this.timer_animation.getTt() > 0.2f) {
            this.timer_animation.initTimer();
            ++this.animation_frame;
            if (this.animation_frame > 3) {
                this.animation_frame = 0;
            }
        }
        if (this.timer_age.getTt() > this.total_lifetime || this.f2_pos.x < 0.0f || this.f2_pos.x > 512.0f || this.f2_pos.y < 0.0f || this.f2_pos.y > 448.0f) {
            this.delete_flag = true;
        }
    }

    public void paintParticle(Graphics2D g2d) {
        int display_x = (int)(this.f2_pos.x - this.width / 2.0f);
        int display_y = (int)(this.f2_pos.y - this.height / 2.0f);
        g2d.setColor(this.particle_color);
        g2d.fillOval(display_x, display_y, (int)this.width, (int)this.height);
    }
}

