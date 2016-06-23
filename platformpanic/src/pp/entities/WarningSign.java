package pp.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pp.levels.Level;
import pp.utilities.ImageLoader;

public class WarningSign extends OtherObject {
	
    private static ImageLoader m_image_loader = new ImageLoader();
    private static BufferedImage m_image;
    public static final float WIDTH = 32.0f;
    public static final float HEIGHT = 32.0f;

    public WarningSign(float x, float y) {
        super(x, y, 32.0f, 32.0f);
    }

    @Override
    public void objectRun(Player player, Level level) {
    }

    @Override
    public void resetObject(Level level) {
    }

    @Override
    public void paintObject(Graphics g) {
        g.drawImage(m_image, (int)this.m_pos.x, (int)this.m_pos.y, null);
    }

    public static void loadImage() {
        m_image = m_image_loader.loadImage("/Graphics/WarningSign.png");
    }
}

