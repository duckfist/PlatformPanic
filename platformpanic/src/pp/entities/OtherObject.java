package pp.entities;

import java.awt.Graphics;

import pp.levels.Level;
import pp.utilities.Float2;

public abstract class OtherObject {
	
    public Float2 m_pos;
    public Float2 m_widthheight;
    public boolean m_in_sight;
    public boolean m_active;

    public OtherObject(float x, float y, float w, float h) {
        this.m_pos = new Float2(x, y);
        this.m_widthheight = new Float2(w, h);
        this.m_active = true;
    }

    public void scrollObject(Float2 translation_vector) {
        this.m_pos.translate(translation_vector);
        this.m_in_sight = this.m_pos.x >= 512.0f ? false : (this.m_pos.x + this.m_widthheight.x < 0.0f ? false : (this.m_pos.y >= 448.0f ? false : this.m_pos.y + this.m_widthheight.y >= 0.0f));
    }

    public abstract void objectRun(Player var1, Level var2);

    public abstract void resetObject(Level var1);

    public abstract void paintObject(Graphics var1);
}

