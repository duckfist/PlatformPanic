package pp.entities;

import pp.game.GamePanel;
import pp.levels.Level;
import pp.utilities.Float2;

public class ControllablePlatform extends MovingPlatform {
	
    public ControllablePlatform(Float2 position) {
        super(position, 0, true, false);
        this.controllable = true;
        this.vel_current = 1.5f;
    }

    @Override
    public void resetPlatform(Level level) {
        super.resetPlatform(level);
        this.vel_current *= 1.5f;
    }

    @Override
    public int activatePlatform() {
        if (this.activated) {
            ++this.current_direction;
            this.current_direction %= 4;
            this.vel_current = this.current_direction <= 1 ? 1.5f : -1.5f;
            Float2 systempos = this.f2_pos.getCopy();
            systempos.translate(this.width / 2.0f, 0.0f);
            ParticleSystem platform_spark = new ParticleSystem(systempos, 80.0f, 0.3f, false, 1);
            ControllablePlatform.current_level.particle_systems.add(platform_spark);
            return 0;
        }
        this.activated = true;
        ++GamePanel.saveData.total_platforms_activated;
        Float2 systempos = this.f2_pos.getCopy();
        systempos.translate(this.width / 2.0f, 0.0f);
        ParticleSystem platform_spark = new ParticleSystem(systempos, 200.0f, 0.3f, true, 0);
        ControllablePlatform.current_level.particle_systems.add(platform_spark);
        return 1;
    }
}

