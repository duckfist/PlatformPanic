package pp.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pp.game.GamePanel;
import pp.levels.Level;
import pp.utilities.ImageLoader;

public class GuideRail extends OtherObject {
	
    private static ImageLoader m_image_loader = new ImageLoader();
    //private static BufferedImage[] m_spring_images = new BufferedImage[4];
    //private static BufferedImage[] m_upside_down = new BufferedImage[4];
    private static BufferedImage[] guiderail_tiles = new BufferedImage[211];
    private BufferedImage m_rail_image;
    public GuideRail[] adjacent_rails = new GuideRail[4];
    public int m_railtype;
    public boolean attached;

    public GuideRail(float x, float y) {
        super(x, y, 32.0f, 32.0f);
        this.m_in_sight = true;
        this.attached = false;
    }

    public void assignAdjacentRails(GuideRail[] adjacent_rails) {
        this.adjacent_rails = adjacent_rails;
        int array_counter = 1;
        if (adjacent_rails[0] == null) {
            array_counter *= 2;
        }
        if (adjacent_rails[1] == null) {
            array_counter *= 3;
        }
        if (adjacent_rails[2] == null) {
            array_counter *= 5;
        }
        if (adjacent_rails[3] == null) {
            array_counter *= 7;
        }
        this.m_rail_image = guiderail_tiles[array_counter];
        if (array_counter == 14 || array_counter == 15) {
            this.m_railtype = 3;
        }
        if (array_counter == 35 || array_counter == 6) {
            this.m_railtype = 2;
        }
        if (array_counter == 21) {
            this.m_railtype = 1;
        }
        if (array_counter == 10) {
            this.m_railtype = 0;
        }
        if (array_counter == 105) {
            this.m_railtype = 5;
        }
        if (array_counter == 30) {
            this.m_railtype = 4;
        }
        if (array_counter == 42) {
            this.m_railtype = 7;
        }
        if (array_counter == 70) {
            this.m_railtype = 6;
        }
        System.out.println(array_counter);
    }

    @Override
    public void objectRun(Player player, Level level) {
    }

    @Override
    public void resetObject(Level level) {
    }

    @Override
    public void paintObject(Graphics g) {
        int image_x = Math.round(this.m_pos.x);
        int image_y = Math.round(this.m_pos.y);
        //int image_w = Math.round(this.m_widthheight.x);
        //int image_h = Math.round(this.m_widthheight.y);
        if (this.m_rail_image != null) {
            g.drawImage(this.m_rail_image, image_x, image_y, null);
            if (this.attached && GamePanel.debugMode) {
                g.setColor(Color.BLUE);
                g.drawRect(image_x, image_y, 32, 32);
            }
        }
    }

    public static void loadImages() {
        BufferedImage rail_tilemap = m_image_loader.loadImage("/Graphics/GuideRailMap.png");
        GuideRail.guiderail_tiles[1] = rail_tilemap.getSubimage(0, 0, 32, 32);
        GuideRail.guiderail_tiles[2] = rail_tilemap.getSubimage(0, 32, 32, 32);
        GuideRail.guiderail_tiles[3] = rail_tilemap.getSubimage(0, 64, 32, 32);
        GuideRail.guiderail_tiles[5] = rail_tilemap.getSubimage(0, 96, 32, 32);
        GuideRail.guiderail_tiles[6] = rail_tilemap.getSubimage(32, 0, 32, 32);
        GuideRail.guiderail_tiles[7] = rail_tilemap.getSubimage(32, 32, 32, 32);
        GuideRail.guiderail_tiles[10] = rail_tilemap.getSubimage(32, 64, 32, 32);
        GuideRail.guiderail_tiles[14] = rail_tilemap.getSubimage(32, 96, 32, 32);
        GuideRail.guiderail_tiles[15] = rail_tilemap.getSubimage(64, 0, 32, 32);
        GuideRail.guiderail_tiles[21] = rail_tilemap.getSubimage(64, 32, 32, 32);
        GuideRail.guiderail_tiles[30] = rail_tilemap.getSubimage(64, 64, 32, 32);
        GuideRail.guiderail_tiles[35] = rail_tilemap.getSubimage(64, 96, 32, 32);
        GuideRail.guiderail_tiles[42] = rail_tilemap.getSubimage(96, 0, 32, 32);
        GuideRail.guiderail_tiles[70] = rail_tilemap.getSubimage(96, 32, 32, 32);
        GuideRail.guiderail_tiles[105] = rail_tilemap.getSubimage(96, 64, 32, 32);
        GuideRail.guiderail_tiles[210] = rail_tilemap.getSubimage(96, 96, 32, 32);
    }
}

