package pp.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import pp.utilities.Float2;

public class RingParticle extends Particle {
	
    public RingParticle(Float2 pos, Float2 vel, boolean gravity, float life_in_seconds, Color color) {
        super(pos, vel, gravity, life_in_seconds, color);
        this.width = 10.0f;
        this.height = 10.0f;
    }

    @Override
    public void paintParticle(Graphics2D g2d) {
        int display_x = (int)(this.f2_pos.x - this.width / 2.0f);
        int display_y = (int)(this.f2_pos.y - this.height / 2.0f);
        g2d.setColor(this.particle_color);
        g2d.drawOval(display_x, display_y, (int)this.width, (int)this.height);
        g2d.drawOval(display_x + 2, display_y + 2, (int)this.width - 4, (int)this.height - 4);
    }
}

