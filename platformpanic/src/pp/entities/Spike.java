package pp.entities;

import java.awt.Graphics;
import java.awt.Image;

import pp.levels.Level;

// TODO: Figure out what the point of this class was.  A Spike should be simple enought to
// function as a LevelElement without needing its own implementation.
public class Spike extends LevelElement {
    //private boolean interior_tile;

    public void setInterior(boolean value) {
        //this.interior_tile = value;
    }

    public Spike() {
    }

    public Spike(int x, int y, Image[] image, int element_id, Level level) {
        super(x, y, image, element_id, level);
        //this.interior_tile = false;
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    }

    @Override
    public void moveElement() {
    }
}

