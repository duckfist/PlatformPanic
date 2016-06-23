package pp.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

import pp.utilities.Float2;
import pp.utilities.Timer;

public class ParticleSystem {
	
    public Float2 system_pos;
    public boolean system_is_active;
    public boolean system_is_visible;
    public boolean gravity;
    public float system_duration;
    public float particle_hz;
    public Timer system_age;
    public Timer particle_hz_timer;
    public int system_type;
    public Vector<Particle> particle_vector;

    public ParticleSystem(Float2 system_pos, float particle_hz, float system_duration, boolean gravity, int system_type) {
        this.particle_hz = particle_hz;
        this.system_duration = system_duration;
        this.gravity = gravity;
        this.system_pos = system_pos.getCopy();
        this.system_type = system_type;
        this.system_is_active = true;
        this.system_is_visible = true;
        this.particle_vector = new Vector<Particle>();
        this.system_age = new Timer();
        this.particle_hz_timer = new Timer();
    }

    public ParticleSystem(Float2 system_pos, float particle_hz, boolean gravity, int system_type) {
        this(system_pos, particle_hz, -1.0f, gravity, system_type);
    }

    public void translateSystem(Float2 dydx) {
        this.system_pos.translate(dydx);
        for (Particle particle : this.particle_vector) {
            particle.f2_pos.translate(dydx);
        }
    }

    public void runSystem() {
        if (this.system_is_active) {
            this.particle_hz_timer.update();
            this.system_age.update();
        }
        if (this.system_duration > 0.0f && this.system_age.getTt() > this.system_duration) {
            this.system_is_active = false;
        }
        if (this.particle_vector.isEmpty() && !this.system_is_active) {
            this.system_is_visible = false;
            return;
        }
        if (this.particle_hz_timer.getTt() > 1.0f / this.particle_hz) {
            Particle new_particle;
            float xvel = (float)(Math.random() * 3.0 - 1.5);
            float yvel = (float)(Math.random() * 1.5);
            Float2 startpos = this.system_pos.getCopy();
            int red_value = (int)((this.system_duration - this.system_age.getTt()) * 255.0f / this.system_duration);
            int green_value = (int)(this.system_age.getTt() * 255.0f / this.system_duration);
            if (red_value < 0) {
                red_value = 0;
            }
            if (green_value > 255) {
                green_value = 255;
            }
            switch (this.system_type) {
                case 0: {
                    new_particle = new Particle(startpos, new Float2(xvel, yvel), this.gravity, 0.5f, new Color(red_value, green_value, 16));
                    break;
                }
                case 1: {
                    new_particle = new RingParticle(startpos, new Float2(xvel, yvel), this.gravity, 0.5f, new Color(red_value, 16, 224));
                    break;
                }
                default: {
                    new_particle = new Particle(startpos, new Float2(xvel, yvel), this.gravity, 0.5f, new Color(red_value, green_value, 16));
                }
            }
            this.particle_vector.add(new_particle);
            this.particle_hz_timer.initTimer();
        }
        for (int i = 0; i < this.particle_vector.size(); ++i) {
            Particle particle = this.particle_vector.elementAt(i);
            particle.moveParticle();
            if (!particle.delete_flag) continue;
            this.particle_vector.removeElementAt(i);
        }
    }

    public void paintSystem(Graphics2D g2d) {
        for (Particle particles : this.particle_vector) {
            particles.paintParticle(g2d);
        }
    }
}

