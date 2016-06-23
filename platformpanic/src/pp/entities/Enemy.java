package pp.entities;

import java.awt.Graphics2D;

import pp.levels.Level;
import pp.utilities.Float2;
import pp.utilities.ImageLoader;

public abstract class Enemy {
	
    public Float2 pos;
    public Float2 size;
    public boolean is_harmful;
    public boolean affected_by_gravity;
    public boolean inSight = true;
    public ImageLoader image_loader;

    public Enemy(float x, float y, boolean is_harmful) {
        this.pos = new Float2(x, y);
        this.scrollEnemy(0.0f, 0.0f);
        this.is_harmful = is_harmful;
        this.image_loader = new ImageLoader();
    }

    public void scrollEnemy(float x_speed, float y_speed) {
        this.pos.translate(x_speed, y_speed);
        this.inSight = this.pos.x >= -24.0f && this.pos.x + this.size.x <= 536.0f && this.pos.y >= -24.0f && this.pos.y + this.size.y <= 472.0f;
    }

    public abstract void loadFiles(ImageLoader var1);

    public abstract void update(Player var1, Level var2);

    public abstract void paint(Graphics2D var1);
}

