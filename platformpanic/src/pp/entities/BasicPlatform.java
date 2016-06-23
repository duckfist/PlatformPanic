package pp.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pp.levels.Level;
import pp.utilities.Float2;

public class BasicPlatform extends Platform {
	
    private static BufferedImage basic_platform_image;

    public BasicPlatform(Float2 position) {
        super(position, 80.0f, false);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        int image_x = Math.round(this.f2_pos.x);
        int image_y = Math.round(this.f2_pos.y);
        g2d.drawImage(basic_platform_image, image_x, image_y, null);
        if (this.activated) {
            g2d.setColor(Color.GREEN);
        } else {
            g2d.setColor(Color.RED);
        }
        g2d.fillRect(image_x + 36, image_y + 2, 8, 8);
    }

    public static void loadImage() {
        try {
            basic_platform_image = platform_image.getSubimage(0, 0, 80, 28);
        }
        catch (Exception e) {
            System.out.println(e);
            System.out.println("ERROR: Platform image hasn't been loaded.");
        }
    }

    @Override
    public int activatePlatform() {
        int return_value = super.activatePlatform();
        return return_value;
    }

    @Override
    public void runPlatform(Player player, Level level) {
    }
}

