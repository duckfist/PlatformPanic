package pp.entities;

import java.awt.Image;

import pp.levels.Level;

// TODO: Figure out what the purpose of this class was
public class Ground extends LevelElement {
    //private boolean interior_tile;

    public void setInterior(boolean value) {
        //this.interior_tile = value;
    }

    public Ground() {
    }

    public Ground(int x, int y, Image[] image, int tileset, int element_id, Level level) {
        super(x, y, image, element_id, level);
        //this.interior_tile = false;
    }

    @Override
    public void moveElement() {
    }
}

