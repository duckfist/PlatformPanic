package pp.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import pp.levels.Level;

public class SpikeFill extends Spike {
	
    private static BufferedImage[][] spikefill_images_0 = new BufferedImage[4][4];
    private static BufferedImage[][] spikefill_images_1 = new BufferedImage[4][4];
	
	private int tick_timer = 0;
	public static int tick_delay = 30; 
    
	boolean spaceup = false;
	boolean spacedown = false;
	boolean spaceleft = false;
	boolean spaceright = false;
    
    private int imgarray_i;
    private int imgarray_j;
    
    public boolean is_origin = false;
    
    public SpikeFill() {
    }

    public SpikeFill(int x, int y, Image[] image, int element_id, Level level) {
        super(x, y, image, element_id, level);
        checkSurroundingTiles();
    }
    
    public SpikeFill(int x, int y, Image[] image, int element_id, Level level, boolean is_origin) {
    	this(x, y, image, element_id, level);
    	this.is_origin = is_origin;
    }
    
    public static void loadImages() {
        BufferedImage spikefill_tileset0 = m_image_loader.loadImage("/Graphics/spikefill1.png");
        BufferedImage spikefill_tileset1 = m_image_loader.loadImage("/Graphics/spikefill2.png");
        for (int i = 0; i < 4; i ++) {
        	for (int j = 0; j < 4; j ++) {
        		spikefill_images_0[i][j] = spikefill_tileset0.getSubimage(i * 32, j * 32, 32, 32);
        		spikefill_images_1[i][j] = spikefill_tileset1.getSubimage(i * 32, j * 32, 32, 32);
        	}
        }
    }
    
    public void checkSurroundingTiles(){
    	
    	spaceleft = false;
    	spaceup = false;
    	spaceright = false;
    	spacedown = false;
    	
    	LevelElement t;    	
    	if (y_index > 0) {
    		t = level.collision_array[y_index-1][x_index];
    		if (t == null) {
    			spaceup = true;
    		}
    	} else {
    		spaceup = false;
    	}
    	if (y_index < level.collision_array.length - 1) {
    		t = level.collision_array[y_index+1][x_index];
    		if (t == null) {
    			spacedown = true;
    		}
    	} else {
    		spacedown = false;
    	}
    	
    	if (x_index > 0) {
    		t = level.collision_array[y_index][x_index-1];
    		if (t == null) {
    			spaceleft = true;
    		}
    	} else {
    		spaceleft = false;
    	}
    	
    	if (x_index < level.collision_array[0].length - 1) {
    		t = level.collision_array[y_index][x_index+1];
    		if (t == null) {
    			spaceright = true;
    		}
    	} else {
    		spaceright = false;
    	}

    	if (spaceleft) {
    		if (spaceup) {
    			if (spaceright) {
    				if (spacedown) {
    					imgarray_i = 3; imgarray_j = 3;
    				} else {
    					imgarray_i = 2; imgarray_j = 2;
    				}
    			} else {
    				if (spacedown) {
    					imgarray_i = 3; imgarray_j = 2;
    				} else {
    					imgarray_i = 2; imgarray_j = 0;
    				}
    			}
    		}
    		else {
    			if (spaceright) {
    				if (spacedown) {
    					imgarray_i = 3; imgarray_j = 1;
    				}
    				else {
    					imgarray_i = 1; imgarray_j = 2;
    				}
    			}
    			else {
    				if (spacedown) {
    					imgarray_i = 2; imgarray_j = 3;
    				}
    				else {
    					imgarray_i = 0; imgarray_j = 3;
    				}
    			}
    		}
    	} else {
    		if (spaceup) {
    			if (spaceright) {
    				if (spacedown) {
    					imgarray_i = 3; imgarray_j = 0;
    				} else {
    					imgarray_i = 1; imgarray_j = 0;
    				}
    			} else {
    				if (spacedown) {
    					imgarray_i = 2; imgarray_j = 1;
    				} else {
    					imgarray_i = 0; imgarray_j = 2;
    				}
    			}
    		} else {
    			if (spaceright) {
    				if (spacedown) {
    					imgarray_i = 1; imgarray_j = 3;
    				} else {
    					imgarray_i = 0; imgarray_j = 1;
    				}
    			} else {
    				if (spacedown) {
    					imgarray_i = 1; imgarray_j = 1;
    				} else {
    					imgarray_i = 0; imgarray_j = 0;
    				}
    			}
    		}
    	}
    }

    public void paintComponent(Graphics g) {
        int imagex = Math.round(this.x_pos_left);
        int imagey = Math.round(this.y_pos_up);
        
        if (this.inSight) {
        	switch (animation_frame) {
	        	case 0: {
	        		g.drawImage(spikefill_images_0[imgarray_i][imgarray_j], imagex, imagey, null);
	        		break;
	        	}
	        	case 1: {
	        		g.drawImage(spikefill_images_1[imgarray_i][imgarray_j], imagex, imagey, null);
	        		break;
	        	}
	        	default: break;
        	}
            
        }
    	
    }
    
    @Override
    public void moveElement() {
    	
    	if (++tick_timer >= tick_delay)
    	{    	
    		tick_timer = 1;
    		if (++animation_frame > 1) animation_frame = 0;
    		
    		if (!spaceleft && !spaceup && !spaceright && !spacedown) return;
    		
    		checkSurroundingTiles();
    		
	    	// Create new spikefills at adjacent empty tiles
	    	if (spaceup){
    			// space found above
    			SpikeFill s = new SpikeFill(x_index, y_index-1, level.spikefill_tile, 1, level);
    			s.scrollElement(level.camera.x_pos, level.camera.y_pos);
    			level.collision_array[y_index-1][x_index] = s;
    			level.elements.add(s);
                ++level.elements_counter;
	    	}
	    	if (spacedown) {
    			// space found below
    			SpikeFill s = new SpikeFill(x_index, y_index+1, level.spikefill_tile, 1, level);
    			level.collision_array[y_index+1][x_index] = s;
    			level.elements.add(s);
                ++level.elements_counter;
                s.scrollElement(level.camera.x_pos, level.camera.y_pos);
	    	}
	    	if (spaceleft) {
    			// space found to the left
    			SpikeFill s = new SpikeFill(x_index-1, y_index, level.spikefill_tile, 1, level);
    			level.collision_array[y_index][x_index-1] = s;
    			level.elements.add(s);
                ++level.elements_counter;
                s.scrollElement(level.camera.x_pos, level.camera.y_pos);
	    	}
	    	if (spaceright) {
    			// space found to the right
    			SpikeFill s = new SpikeFill(x_index+1, y_index, level.spikefill_tile, 1, level);
    			level.collision_array[y_index][x_index+1] = s;
    			level.elements.add(s);
                ++level.elements_counter;
                s.scrollElement(level.camera.x_pos, level.camera.y_pos);
	    	}
	    	
	    	checkSurroundingTiles();
    	}
    }
}
