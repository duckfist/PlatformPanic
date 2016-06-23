package pp.entities;

import java.awt.Graphics;
import java.awt.Image;

import pp.levels.Level;
import pp.utilities.ImageLoader;

public abstract class LevelElement {
	
	protected Level level;
    protected boolean fake_element;
    public int x_index;
    public int y_index;
    public float x_pos_left;
    public float y_pos_up;
    public float x_pos_right;
    public float y_pos_down;
    protected int element_id;
    protected boolean inSight;
    protected Image[] element_images;
    protected static ImageLoader m_image_loader = new ImageLoader();
    //private Component parent;
    protected int animation_frame;

    public LevelElement() {
    }

    public LevelElement(int x, int y, Image[] images, int element_id, Level level) {
    	x_index = x;
    	y_index = y;
        this.x_pos_left = x * 32.0f;
        this.y_pos_up = y * 32.0f;
        this.x_pos_right = x * 32.0f + 32.0f;
        this.y_pos_down = y * 32.0f + 32.0f;
        this.element_images = new Image[images.length];
        this.element_images = images;
        this.animation_frame = 0;
        this.element_id = element_id;
        this.inSight = true;
        this.level = level;
    }

    public void scrollElement(float x_speed, float y_speed) {
        this.x_pos_left -= x_speed;
        this.x_pos_right -= x_speed;
        this.y_pos_up -= y_speed;
        this.y_pos_down -= y_speed;
        this.inSight = this.x_pos_left >= -24.0f && this.x_pos_right <= 536.0f && this.y_pos_up >= -24.0f && this.y_pos_down <= 472.0f;
    }

    public void setElementImages(Image[] images) {
        this.element_images = images;
    }

    public int getElementID() {
        return this.element_id;
    }

    public boolean getInSight() {
        return this.inSight;
    }

    public float getXPos() {
        return this.x_pos_left;
    }

    public float getYPos() {
        return this.y_pos_up;
    }

    public void paintComponent(Graphics g) {
        int imagex = Math.round(this.x_pos_left);
        int imagey = Math.round(this.y_pos_up);
        //int width = Math.round(32.0f);
        //int height = Math.round(32.0f);
        if (this.inSight) {
            g.drawImage(this.element_images[this.animation_frame], imagex, imagey, null);
        }
    }

    public abstract void moveElement();
}

