package pp.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import pp.game.GamePanel;
import pp.utilities.Float2;
import pp.utilities.Timer;

public class Effects {
	
    public static String message = "";

    public static void drawStringShadow(Graphics2D g2d, String string, Font font, Color color, int x, int y) {
        g2d.setFont(font);
        g2d.setColor(new Color(25, 25, 25));
        g2d.drawString(string, x + font.getSize() / 10, y - font.getSize() / 10);
        g2d.setColor(color);
        g2d.drawString(string, x, y);
    }

    public static void messageInBox(Graphics g, int y_pos) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(24, 16, 16));
        g2d.fillRect(256 - message.length() * 6 - 16, y_pos, message.length() * 12 + 32, 54);
        g2d.setColor(new Color(72, 32, 32));
        g2d.fillRect(256 - message.length() * 6 - 8, y_pos + 8, message.length() * 12 + 16, 38);
        g2d.setColor(new Color(196, 196, 224));
        g2d.setFont(new Font("monospaced", 1, 20));
        g2d.drawString(message, 256 - message.length() * 6, y_pos + 32);
    }

    public static void imageInBox(Graphics g, BufferedImage image) {
        int height = 144;
        Effects.borderRect(g, 124, height, 264, 200, 4, Color.BLACK, new Color(192, 152, 162));
        ((Graphics2D)g).drawImage(image, 128, height + 4, null);
    }

    public static void borderRect(Graphics g, int x, int y, int width, int height, int thickness, Color c_border, Color c_interior) {
        if (2 * thickness > height || 2 * thickness > width) {
            return;
        }
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(c_border);
        g2d.fillRect(x, y, width, height);
        g2d.setColor(c_interior);
        g2d.fillRect(x + thickness, y + thickness, width - thickness * 2, height - thickness * 2);
    }

    public static void borderRect(Graphics g, int width, int height, int thickness, Color c_border, Color c_interior) {
        int x = Math.round(256.0f - (float)width / 2.0f);
        int y = Math.round(224.0f - (float)height / 2.0f);
        Effects.borderRect(g, x, y, thickness, c_border, c_interior);
    }

    public class CircleFlash {
        public float repeat_rate;
        public float radius;
        public Float2 pos;
        public Float2 vel;
        public Timer timer;

        public CircleFlash(Float2 pos, Float2 vel, float repeat_rate, float radius) {
            this.repeat_rate = repeat_rate;
            if (radius < 3.0f) {
                radius = 3.0f;
            }
            this.radius = radius;
            this.pos = pos;
            this.vel = vel;
            this.timer = new Timer();
        }

        public void paintComponent(Graphics2D g2d) {
            this.pos.translate(this.vel.x * GamePanel.time.getDt(), this.vel.y * GamePanel.time.getDt());
            this.timer.update();
            if (this.timer.getTt() > this.repeat_rate) {
                this.timer.initTimer();
            }
            int radiusint = Math.round(this.radius * this.repeat_rate / this.timer.getTt());
            int halfradius = Math.round((float)radiusint / 2.0f);
            g2d.setColor(Color.WHITE);
            g2d.drawOval((int)this.pos.x - halfradius, (int)this.pos.y - halfradius, radiusint, radiusint);
            g2d.fillOval((int)this.pos.x - (halfradius - 2), (int)this.pos.y - (halfradius - 2), radiusint - 2, radiusint - 2);
        }
    }

}

