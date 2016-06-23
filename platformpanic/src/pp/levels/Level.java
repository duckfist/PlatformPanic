package pp.levels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Vector;

import pp.entities.AngledSpringBoard;
import pp.entities.BasicPlatform;
import pp.entities.Camera;
import pp.entities.ControllablePlatform;
import pp.entities.DeathExplosion;
import pp.entities.DisappearingPlatform;
import pp.entities.Ground;
import pp.entities.GuideRail;
import pp.entities.GuidedPlatform;
import pp.entities.LevelElement;
import pp.entities.MovingPlatform;
import pp.entities.OtherObject;
import pp.entities.ParticleSystem;
import pp.entities.Platform;
import pp.entities.Player;
import pp.entities.SoundClip;
import pp.entities.Spike;
import pp.entities.SpikeFill;
import pp.entities.SpringBoard;
import pp.entities.TimedPlatform;
import pp.entities.WarningSign;
import pp.game.GamePanel;
import pp.gui.LevelCleared;
import pp.gui.LevelClock;
import pp.gui.MiniMap;
import pp.utilities.Float2;
import pp.utilities.ImageLoader;
import pp.utilities.Timer;

public abstract class Level {

    public int elements_counter;
    public String level_name;
    public float left_level_border;
    public float right_level_border;
    public float up_level_border;
    public float down_level_border;
    public float level_width;
    public float level_height;
    private Color[] colors;
    public ImageLoader image_loader;
    protected int ground_tileset;
    public BufferedImage[] ground_tiles = new BufferedImage[211];
    public BufferedImage[] spike_tiles = new BufferedImage[211];
    public BufferedImage[] spikefill_tile = new BufferedImage[1];
    protected Float2 spawn_pos = new Float2();
    public Player player;
    public Camera camera;
    public LevelCleared level_clear_screen;
    private MiniMap mini_map;
    public boolean level_ended;
    
    public Vector<LevelElement> elements;
    public LevelElement[][] collision_array;
    public OtherObject[][] oo_2d_array;
    public Vector<Platform> platform_vector;
    public Vector<ParticleSystem> particle_systems;
    public Vector<OtherObject> otherobject_vector;
    public int platforms_activated = 0;
    public int level_number;
    private boolean has_disappearing_platforms = false;
    private boolean has_guided_platforms = false;
    private Timer timer_player_died = new Timer(false);

    public Level(String level_name, Player player, Camera camera, int tile_map, int level_number) {
        this.level_name = level_name;
        this.image_loader = new ImageLoader();
        this.player = player;
        this.level_number = level_number;
        this.camera = camera;
        this.elements_counter = 0;
        this.ground_tileset = tile_map;
        this.loadImageFiles();
        Platform.setLevel(this);
    }

    public void startLevel(Camera camera) {
        this.camera = camera;
        this.resetLevel();
        Float2 offset = this.spawn_pos.getCopy();
        offset.translate(-256.0f, -224.0f);
        camera.setCameraPos(offset, this.player);
    }

    public void resetLevel() {
        this.level_ended = false;
        this.player.resetPlayer();
        Float2 camera_offset = this.spawn_pos.getCopy();
        camera_offset.translate(- this.left_level_border, - this.up_level_border);
        this.camera.player_anchor = false;
        this.player.f2_pos = camera_offset;
        this.player.spawnPlayer();
        this.camera.setDestination(this.spawn_pos);
        this.camera.speedIncrease();
        this.timer_player_died = new Timer(false);
        LevelClock.initClock(-1.5f);
        this.platforms_activated = 0;
        
        for (Platform platform : this.platform_vector) {
            platform.resetPlatform(this);
        }
        for (OtherObject oo : this.otherobject_vector) {
            oo.resetObject(this);
        }
        
        System.out.println("Resetting level " + this.level_number + ".");
    }

    public void loadImageFiles() {
        BufferedImage ground_tilemap = this.image_loader.loadImage("/Graphics/GroundTileMap" + this.ground_tileset + ".png");
        BufferedImage spike_tilemap = this.image_loader.loadImage("/Graphics/SpikeTileMap.png");
        
        this.ground_tiles[1] = ground_tilemap.getSubimage(0, 0, 32, 32);
        this.ground_tiles[2] = ground_tilemap.getSubimage(0, 32, 32, 32);
        this.ground_tiles[3] = ground_tilemap.getSubimage(0, 64, 32, 32);
        this.ground_tiles[5] = ground_tilemap.getSubimage(0, 96, 32, 32);
        this.ground_tiles[6] = ground_tilemap.getSubimage(32, 0, 32, 32);
        this.ground_tiles[7] = ground_tilemap.getSubimage(32, 32, 32, 32);
        this.ground_tiles[10] = ground_tilemap.getSubimage(32, 64, 32, 32);
        this.ground_tiles[14] = ground_tilemap.getSubimage(32, 96, 32, 32);
        this.ground_tiles[15] = ground_tilemap.getSubimage(64, 0, 32, 32);
        this.ground_tiles[21] = ground_tilemap.getSubimage(64, 32, 32, 32);
        this.ground_tiles[30] = ground_tilemap.getSubimage(64, 64, 32, 32);
        this.ground_tiles[35] = ground_tilemap.getSubimage(64, 96, 32, 32);
        this.ground_tiles[42] = ground_tilemap.getSubimage(96, 0, 32, 32);
        this.ground_tiles[70] = ground_tilemap.getSubimage(96, 32, 32, 32);
        this.ground_tiles[105] = ground_tilemap.getSubimage(96, 64, 32, 32);
        this.ground_tiles[210] = ground_tilemap.getSubimage(96, 96, 32, 32);
        this.spikefill_tile[0] = spike_tilemap.getSubimage(0, 0, 32, 32);
        this.spike_tiles[1] = spike_tilemap.getSubimage(0, 0, 32, 32);
        this.spike_tiles[2] = spike_tilemap.getSubimage(0, 32, 32, 32);
        this.spike_tiles[3] = spike_tilemap.getSubimage(0, 64, 32, 32);
        this.spike_tiles[5] = spike_tilemap.getSubimage(0, 96, 32, 32);
        this.spike_tiles[6] = spike_tilemap.getSubimage(32, 0, 32, 32);
        this.spike_tiles[7] = spike_tilemap.getSubimage(32, 32, 32, 32);
        this.spike_tiles[10] = spike_tilemap.getSubimage(32, 64, 32, 32);
        this.spike_tiles[14] = spike_tilemap.getSubimage(32, 96, 32, 32);
        this.spike_tiles[15] = spike_tilemap.getSubimage(64, 0, 32, 32);
        this.spike_tiles[21] = spike_tilemap.getSubimage(64, 32, 32, 32);
        this.spike_tiles[30] = spike_tilemap.getSubimage(64, 64, 32, 32);
        this.spike_tiles[35] = spike_tilemap.getSubimage(64, 96, 32, 32);
        this.spike_tiles[42] = spike_tilemap.getSubimage(96, 0, 32, 32);
        this.spike_tiles[70] = spike_tilemap.getSubimage(96, 32, 32, 32);
        this.spike_tiles[105] = spike_tilemap.getSubimage(96, 64, 32, 32);
        this.spike_tiles[210] = spike_tilemap.getSubimage(96, 96, 32, 32);
        Platform.loadImages();
        BasicPlatform.loadImage();
        TimedPlatform.loadImage();
        DisappearingPlatform.loadImage();
        MovingPlatform.loadImage();
        GuidedPlatform.loadImage();
        WarningSign.loadImage();
        SpringBoard.loadImages();
        AngledSpringBoard.loadImages();
        GuideRail.loadImages();
        LevelCleared.loadImages();
        SpikeFill.loadImages();
    }

    public void setColorArray(Color[] colors) {
        this.colors = colors;
    }

    public void initializeLevel(){
    	initializeLevel(getTileMap(), 0);
    }
    
    
    // Create level from char array tilemap
    public void initializeLevel(String[] definitions, int tileset) {
        int j;
        int i;
        this.ground_tileset = tileset;
        this.collision_array = new LevelElement[definitions.length][definitions[0].length()];
        this.oo_2d_array = new OtherObject[definitions.length][definitions[0].length()];
        this.platform_vector = new Vector<Platform>();
        this.otherobject_vector = new Vector<OtherObject>();
        this.particle_systems = new Vector<ParticleSystem>();
        this.level_width = (float)definitions[0].length() * 32.0f;
        this.level_height = (float)definitions.length * 32.0f;
        this.left_level_border = 0.0f;
        this.right_level_border = 512.0f;
        this.up_level_border = 0.0f;
        this.down_level_border = 448.0f;
        
        // Create tiles, platforms, and other objects
        for (int i2 = 0; i2 < definitions.length; ++i2) {
            char[] definition_line = definitions[i2].toCharArray();
            for (j = 0; j < definition_line.length; ++j) {
                if (definition_line[j] == ' ') {
                    this.collision_array[i2][j] = null;
                    continue;
                }
                if (definition_line[j] == 'g') {
                    this.collision_array[i2][j] = new Ground(j, i2, this.ground_tiles, 1, 1, this);
                    ++this.elements_counter;
                    continue;
                }
                if (definition_line[j] == 'x') {
                    this.collision_array[i2][j] = new Spike(j, i2, this.spike_tiles, 1, this);
                    ++this.elements_counter;
                    continue;
                }
                if (definition_line[j] == 'X') {
                    this.collision_array[i2][j] = new SpikeFill(j, i2, this.spikefill_tile, 1, this, true);
                    ++this.elements_counter;
                    continue;
                }
                if (definition_line[j] == 'B') {
                    BasicPlatform platform = new BasicPlatform(new Float2((float)j * 32.0f - 24.0f, (float)i2 * 32.0f));
                    this.platform_vector.addElement(platform);
                    continue;
                }
                if (definition_line[j] >= '0' && definition_line[j] <= '9') {
                    int time_value = Character.getNumericValue(definition_line[j]);
                    TimedPlatform platform2 = new TimedPlatform(new Float2((float)j * 32.0f - 24.0f, (float)i2 * 32.0f), time_value);
                    this.platform_vector.addElement(platform2);
                    continue;
                }
                if (definition_line[j] >= 'E' && definition_line[j] <= 'H') {
                    int phase = 72 - definition_line[j];
                    DisappearingPlatform platform = new DisappearingPlatform(new Float2((float)j * 32.0f - 24.0f, (float)i2 * 32.0f), phase);
                    this.platform_vector.addElement(platform);
                    this.has_disappearing_platforms = true;
                    continue;
                }
                if (definition_line[j] == 'S') {
                    MovingPlatform platform = new MovingPlatform(new Float2((float)j * 32.0f - 24.0f, (float)i2 * 32.0f), 1, true, false);
                    this.platform_vector.addElement(platform);
                    continue;
                }
                if (definition_line[j] == 'W') {
                    MovingPlatform platform = new MovingPlatform(new Float2((float)j * 32.0f - 24.0f, (float)i2 * 32.0f), 3, true, false);
                    this.platform_vector.addElement(platform);
                    continue;
                }
                if (definition_line[j] == 'A') {
                    MovingPlatform platform = new MovingPlatform(new Float2((float)j * 32.0f - 24.0f, (float)i2 * 32.0f), 2, true, false);
                    this.platform_vector.addElement(platform);
                    continue;
                }
                if (definition_line[j] == 'D') {
                    MovingPlatform platform = new MovingPlatform(new Float2((float)j * 32.0f - 24.0f, (float)i2 * 32.0f), 0, true, false);
                    this.platform_vector.addElement(platform);
                    continue;
                }
                if (definition_line[j] == 'C') {
                    ControllablePlatform platform = new ControllablePlatform(new Float2((float)j * 32.0f - 24.0f, (float)i2 * 32.0f));
                    this.platform_vector.addElement(platform);
                    continue;
                }
                if (definition_line[j] == 's') {
                    SpringBoard sb = new SpringBoard((float)j * 32.0f, (float)i2 * 32.0f, 1.5f, false);
                    this.otherobject_vector.addElement(sb);
                    continue;
                }
                if (definition_line[j] == 'u') {
                    SpringBoard sb = new SpringBoard((float)j * 32.0f, (float)i2 * 32.0f, 1.5f, true);
                    this.otherobject_vector.addElement(sb);
                    continue;
                }
                if (definition_line[j] == '[') {
                    AngledSpringBoard sb = new AngledSpringBoard((float)j * 32.0f, (float)i2 * 32.0f, 1.5f, -8.0f);
                    this.otherobject_vector.addElement(sb);
                    continue;
                }
                if (definition_line[j] == ']') {
                    AngledSpringBoard sb = new AngledSpringBoard((float)j * 32.0f, (float)i2 * 32.0f, 1.5f, 8.0f);
                    this.otherobject_vector.addElement(sb);
                    continue;
                }
                if (definition_line[j] == '*') {
                    GuideRail rail = new GuideRail((float)j * 32.0f, (float)i2 * 32.0f);
                    this.has_guided_platforms = true;
                    this.oo_2d_array[i2][j] = rail;
                    this.otherobject_vector.addElement(rail);
                    continue;
                }
                if (definition_line[j] == 'R') {
                    GuidedPlatform platform = new GuidedPlatform(new Float2((float)j * 32.0f, (float)i2 * 32.0f));
                    this.platform_vector.addElement(platform);
                    continue;
                }
                if (definition_line[j] == '!') {
                    WarningSign sign = new WarningSign((float)j * 32.0f, (float)i2 * 32.0f);
                    this.otherobject_vector.addElement(sign);
                    continue;
                }
                this.collision_array[i2][j] = null;
            }
        }
        
        // Connect tiles together so they use the right graphic
        this.elements = new Vector<LevelElement>();
        this.mini_map = new MiniMap(this.collision_array, this);
        for (i = 0; i < this.collision_array.length; ++i) {
            for (j = 0; j < this.collision_array[i].length; ++j) {
                LevelElement[] adjacent_tiles;
                boolean interior;
                Image[] newimage;
                int array_counter;
                if (this.collision_array[i][j] == null) continue;
                elements.add(this.collision_array[i][j]);
                if (this.collision_array[i][j] instanceof Ground) {
                    LevelElement groundElement = this.collision_array[i][j];
                    interior = true;
                    adjacent_tiles = new LevelElement[4];
                    adjacent_tiles = this.getAdjacentElements(groundElement);
                    array_counter = 1;
                    if (adjacent_tiles[0] == null) {
                        array_counter *= 2;
                    }
                    if (adjacent_tiles[1] == null) {
                        array_counter *= 3;
                    }
                    if (adjacent_tiles[2] == null) {
                        array_counter *= 5;
                    }
                    if (adjacent_tiles[3] == null) {
                        array_counter *= 7;
                    }
                    if (array_counter == 1) {
                        ((Ground)groundElement).setInterior(interior);
                    }
                    newimage = new Image[]{this.ground_tiles[array_counter]};
                    groundElement.setElementImages(newimage);
                    continue;
                }
                
                // Connect the spike tiles with adjacent ground tiles, choosing the proper graphic
                if (!(this.collision_array[i][j] instanceof Spike) || (this.collision_array[i][j] instanceof SpikeFill))
            	{
                	// Only apply the following to a Spike type, not including children of Spike (SpikeFill)
                	continue;
            	}
                LevelElement spikeElement = this.collision_array[i][j];
                interior = true;
                adjacent_tiles = new LevelElement[4];
                adjacent_tiles = this.getAdjacentElements(spikeElement);
                array_counter = 1;
                if (adjacent_tiles[0] == null) {
                    array_counter *= 2;
                }
                if (adjacent_tiles[1] == null) {
                    array_counter *= 3;
                }
                if (adjacent_tiles[2] == null) {
                    array_counter *= 5;
                }
                if (adjacent_tiles[3] == null) {
                    array_counter *= 7;
                }
                newimage = new Image[]{this.spike_tiles[array_counter]};
                spikeElement.setElementImages(newimage);
            }
        }
        // Connect the rail tiles for guided platforms
        if (this.has_guided_platforms) {
            for (i = 0; i < this.oo_2d_array.length; ++i) {
                for (j = 0; j < this.oo_2d_array[i].length; ++j) {
                    GuideRail above_element;
                    GuideRail right_element;
                    GuideRail below_element;
                    GuideRail left_element;
                    if (this.oo_2d_array[i][j] == null || !(this.oo_2d_array[i][j] instanceof GuideRail)) continue;
                    GuideRail rail = (GuideRail)this.oo_2d_array[i][j];
                    GuideRail[] adjacents = new GuideRail[4];
                    try {
                        above_element = (GuideRail)this.oo_2d_array[i - 1][j];
                    }
                    catch (Exception e) {
                        above_element = null;
                    }
                    try {
                        left_element = (GuideRail)this.oo_2d_array[i][j - 1];
                    }
                    catch (Exception e) {
                        left_element = null;
                    }
                    try {
                        below_element = (GuideRail)this.oo_2d_array[i + 1][j];
                    }
                    catch (Exception e) {
                        below_element = null;
                    }
                    try {
                        right_element = (GuideRail)this.oo_2d_array[i][j + 1];
                    }
                    catch (Exception e) {
                        right_element = null;
                    }
                    adjacents[0] = right_element;
                    adjacents[1] = above_element;
                    adjacents[2] = left_element;
                    adjacents[3] = below_element;
                    rail.assignAdjacentRails(adjacents);
                }
            }
        }
    }

    // Get a list of platforms that the player is currently colliding with
    public Vector<Platform> testCollisionPlatform(Player player) {
        Vector<Platform> platforms_colliding_with = new Vector<Platform>();
        for (Platform platform : this.platform_vector) {
            if (platform.f2_pos.x <= 0.0f || platform.f2_pos.x >= 512.0f || platform.f2_pos.y <= -224.0f || platform.f2_pos.y >= 672.0f || !platform.testPlayerCollision(player, this)) continue;
            platforms_colliding_with.addElement(platform);
        }
        return platforms_colliding_with;
    }

    // Get the tile at a given pixel coordinate in the level
    public LevelElement testCollisionPoint(Float2 test_pt, boolean care_about_fake_elements) {
        int column = (int)(test_pt.x / 32.0f);
        int row = (int)(test_pt.y / 32.0f);
        try {
            if (this.collision_array[row][column] != null) {
                return this.collision_array[row][column];
            }
            return null;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (care_about_fake_elements) {
                Ground fake_element = new Ground();
                return fake_element;
            }
            return null;
        }
    }

    public LevelElement[] testCollisionSide(Float2 player_side, boolean care_about_fake_elements) {
        int col = (int)(player_side.x / 32.0f);
        int row1 = (int)((player_side.y + 5.0f) / 32.0f);
        int row2 = (int)((player_side.y + 44.0f - 5.0f) / 32.0f);
        int row3 = -1;
        if (row2 - row1 == 2) {
            row3 = row1 + 1;
        }
        LevelElement[] collisions = new LevelElement[]{null, null, null};
        try {
            if (this.collision_array[row1][col] != null) {
                collisions[0] = this.collision_array[row1][col];
            }
            if (this.collision_array[row2][col] != null) {
                collisions[1] = this.collision_array[row2][col];
            }
            if (row3 != -1 && this.collision_array[row3][col] != null) {
                collisions[2] = this.collision_array[row3][col];
            }
            return collisions;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (care_about_fake_elements) {
//                for (LevelElement element : collisions) {
//                    element = new Ground();
//                }
            	
            	// TODO implement proper error handling/figure out what i was trying to do here
            }
            return collisions;
        }
    }

    public LevelElement[] testCollisionVert(Float2 player_left, boolean care_about_fake_elements) {
        int row = (int)(player_left.y / 32.0f);
        int col1 = (int)((player_left.x + 1.0f) / 32.0f);
        int col2 = (int)((player_left.x + 28.0f - 1.0f) / 32.0f);
        LevelElement[] collisions = new LevelElement[]{null, null};
        try {
            if (this.collision_array[row][col1] != null) {
                collisions[0] = this.collision_array[row][col1];
            }
            if (this.collision_array[row][col2] != null) {
                collisions[1] = this.collision_array[row][col2];
            }
            return collisions;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (care_about_fake_elements) {
//              for (LevelElement element : collisions) {
//              	element = new Ground();
//          	}
      	
      	// TODO implement proper error handling/figure out what i was trying to do here
            }
            return collisions;
        }
    }

    // Get the tiles adjacent to the given tile
    public LevelElement[] getAdjacentElements(LevelElement center_tile) {
        if (center_tile == null) {
            return null;
        }
        float center_x = center_tile.getXPos();
        float center_y = center_tile.getYPos();
        float tile_width = 32.0f;
        float tile_height = 32.0f;
        LevelElement right_tile = this.testCollisionPoint(new Float2(center_x + tile_width, center_y), true);
        LevelElement up_tile = this.testCollisionPoint(new Float2(center_x, center_y - tile_height), true);
        LevelElement left_tile = this.testCollisionPoint(new Float2(center_x - tile_width, center_y), true);
        LevelElement down_tile = this.testCollisionPoint(new Float2(center_x, center_y + tile_height), true);
        LevelElement[] adjacent_elements = new LevelElement[4];
        if (!(right_tile instanceof Ground)) {
            right_tile = null;
        }
        if (!(up_tile instanceof Ground)) {
            up_tile = null;
        }
        if (!(left_tile instanceof Ground)) {
            left_tile = null;
        }
        if (!(down_tile instanceof Ground)) {
            down_tile = null;
        }
        adjacent_elements[0] = right_tile;
        adjacent_elements[1] = up_tile;
        adjacent_elements[2] = left_tile;
        adjacent_elements[3] = down_tile;
        return adjacent_elements;
    }

    public void scrollLevel(float x_speed, float y_speed) {
        Float2 translation_vector = new Float2(- x_speed, - y_speed);
        float old_left_border = this.left_level_border;
        float old_up_border = this.up_level_border;
        this.left_level_border += x_speed;
        this.right_level_border += x_speed;
        this.up_level_border += y_speed;
        this.down_level_border += y_speed;
        if (this.left_level_border < 0.0f) {
            this.left_level_border = 0.0f;
            this.right_level_border = 512.0f;
            x_speed = old_left_border;
        } else if (this.left_level_border > this.level_width - 512.0f) {
            this.left_level_border = this.level_width - 512.0f;
            this.right_level_border = this.level_width;
            x_speed = old_left_border - this.left_level_border;
        }
        if (this.up_level_border < 0.0f) {
            this.up_level_border = 0.0f;
            this.down_level_border = 448.0f;
            y_speed = old_up_border;
        } else if (this.up_level_border > this.level_height - 448.0f) {
            this.up_level_border = this.level_height - 448.0f;
            this.down_level_border = this.level_width;
            y_speed = old_up_border - this.up_level_border;
        }
        int i = 0;
        for(i = 0; i < elements.size(); ++i) {
        	if (elements.elementAt(i) != null)
        	{
        		elements.elementAt(i).scrollElement(x_speed, y_speed);
        	}
        }
        for (i = 0; i < platform_vector.size(); i++){
        	platform_vector.elementAt(i).scroll(x_speed, y_speed);
        }
        for (i = 0; i < particle_systems.size(); i++){
        	particle_systems.elementAt(i).translateSystem(new Float2(- x_speed, - y_speed));
        }
        for (i = 0; i < otherobject_vector.size(); i++){
        	otherobject_vector.elementAt(i).scrollObject(translation_vector);
        }
    }

    public boolean canScrollLeft() {
        if (this.left_level_border <= 0.0f) {
            return false;
        }
        return true;
    }

    public boolean canScrollRight() {
        if (this.right_level_border >= this.level_width) {
            return false;
        }
        return true;
    }

    public void runObjects() {
        int i;
        for(i = 0; i < elements.size(); ++i) {
        	if (elements.elementAt(i) != null)
        	{
        		elements.elementAt(i).moveElement();
        	}
        }
        boolean[] disappearing_platforms_visible = new boolean[]{false, false, false, false};
        if (this.has_disappearing_platforms) {
            DisappearingPlatform.operateDisappearingPlatforms();
        }
        for (i = 0; i < this.platform_vector.size(); ++i) {
            Platform platform = this.platform_vector.elementAt(i);
            platform.runPlatform(this.player, this);
            if (!(platform instanceof DisappearingPlatform) || !platform.getInSight()) continue;
            disappearing_platforms_visible[((DisappearingPlatform)platform).phase_issued] = true;
        }
        DisappearingPlatform.phases_visible = disappearing_platforms_visible;
        for (i = 0; i < this.otherobject_vector.size(); ++i) {
            OtherObject oo = this.otherobject_vector.elementAt(i);
            oo.objectRun(this.player, this);
            if (oo.m_active) continue;
            this.otherobject_vector.removeElementAt(i);
        }
    }

    public void testLevelEnd() {
        if (this.level_ended) {
            return;
        }
        if (this.player.dead) {
            if (this.timer_player_died.getDt() == 0.0f) {
                LevelClock.pauseClock(true);
                GamePanel.saveData.total_time_played = (int)((long)GamePanel.saveData.total_time_played + LevelClock.getClockTimeMs());
                this.timer_player_died.initTimer();
                for (int i = 0; i < 3; ++i) {
                    for (int j = 0; j < 10; ++j) {
                        DeathExplosion explosion = new DeathExplosion(this.player.f2_pos.x + 12.0f, this.player.f2_pos.y + 36.0f, (float)((double)j * 3.141592653589793 / 5.0), 2 + i);
                        this.otherobject_vector.addElement(explosion);
                    }
                }
                SoundClip.DEATH.play();
            }
            this.timer_player_died.update();
            if (this.timer_player_died.getTt() > 1.0f) {
                this.endLevelInFailure();
            }
        } else if (this.platforms_activated >= this.platform_vector.size()) {
            this.level_ended = true;
            LevelClock.pauseClock(true);
            GamePanel.saveData.total_time_played = (int)((long)GamePanel.saveData.total_time_played + LevelClock.getClockTimeMs());
            boolean is_record = GamePanel.saveData.attemptSetRecord(this.level_number, LevelClock.getClockTimeMs());
            boolean[] medals_have = new boolean[]{false, false, false};
            boolean[] medals_obtained = new boolean[]{false, false, false};
            if (!GamePanel.saveData.levels[this.level_number].gold) {
                medals_obtained[2] = GamePanel.saveData.attemptBeatGold(this.level_number);
            } else {
                medals_have[2] = true;
            }
            if (!GamePanel.saveData.levels[this.level_number].silver) {
                medals_obtained[1] = GamePanel.saveData.attemptBeatSilver(this.level_number);
            } else {
                medals_have[1] = true;
            }
            if (!GamePanel.saveData.levels[this.level_number].bronze) {
                medals_obtained[0] = GamePanel.saveData.attemptBeatBronze(this.level_number);
            } else {
                medals_have[0] = true;
            }
            if (GamePanel.saveData.getLocked(this.level_number + 1)) {
            	GamePanel.saveData.unlockLevel(this.level_number + 1);
            }
            if (GamePanel.saveData.getBronze(this.level_number) &&
            		GamePanel.saveData.getSilver(this.level_number) && 
            		GamePanel.saveData.getGold(this.level_number)) {
            	GamePanel.saveData.completeLevel(this.level_number);
            } else {
            	GamePanel.saveData.finishLevel(this.level_number);
            }
            this.level_clear_screen = new LevelCleared(this, is_record, medals_have, medals_obtained);
        }
    }

    public void endLevelInFailure() {
        this.level_ended = true;
        LevelClock.pauseClock(true);
        boolean[] medals_have = new boolean[]{false, false, false};
        if (GamePanel.saveData.levels[this.level_number].bronze) {
            medals_have[0] = true;
        }
        if (GamePanel.saveData.levels[this.level_number].silver) {
            medals_have[1] = true;
        }
        if (GamePanel.saveData.levels[this.level_number].gold) {
            medals_have[2] = true;
        }
        this.level_clear_screen = new LevelCleared(this, medals_have);
        this.player.dead = true;
    }

    public boolean hasEnded() {
        return this.level_ended;
    }

    public void paintLevel(Graphics g, Player player) {
        try {
            int i;
            for (i = 0; i < 255; i += 2) {
                g.setColor(this.colors[i]);
                g.drawLine(0, 448 - (i + 200), 512, 448 - (i + 200));
                g.fillRect(0, 2 * i, 512, 4);
            } 
            for(i = 0; i < elements.size(); ++i) {
            	if (elements.elementAt(i) != null)
            	{
            		elements.elementAt(i).paintComponent(g);
            	}
            }
            for (OtherObject oo : this.otherobject_vector) {
                if (!oo.m_in_sight) continue;
                oo.paintObject(g);
            }
            for (Platform platform : this.platform_vector) {
                if (platform.f2_pos.x >= 512.0f || platform.f2_pos.x + platform.width <= 0.0f || platform.f2_pos.y + 32.0f <= 0.0f || platform.f2_pos.y >= 448.0f) continue;
                platform.paintComponent(g);
            }
            for (int i2 = 0; i2 < this.particle_systems.size(); ++i2) {
                ParticleSystem system = this.particle_systems.elementAt(i2);
                if (!system.system_is_visible) {
                    this.particle_systems.removeElementAt(i2);
                    continue;
                }
                system.runSystem();
                system.paintSystem((Graphics2D)g);
            }
            if (this.mini_map != null) {
                this.mini_map.paintMiniMap((Graphics2D)g);
            }
            if (this.level_ended) {
                this.level_clear_screen.paintComponent(g);
                return;
            }
            g.setColor(new Color(16, 16, 16));
            g.fillRoundRect(8, 8, 88, 32, 24, 24);
            g.setColor(new Color(32, 32, 32));
            g.fillRoundRect(11, 11, 82, 26, 20, 20);
            g.setColor(Color.WHITE);
            g.setFont(new Font("sanserif", 0, 18));
            g.drawString(LevelClock.getClockTime(), 14, 30);
            if (player.spawning) {
                if (GamePanel.time.getTt() % 0.15f > 0.075f) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("monospaced", 1, 32));
                    g.drawString("GET READY!", 180, 211);
                    g.setFont(new Font("monospaced", 1, 32));
                    g.setColor(Color.WHITE);
                    g.drawString("GET READY!", 176, 208);
                }
                return;
            }
            int fillwidth = ((int)Math.log10(this.platforms_activated) + (int)Math.log10(this.platform_vector.size())) * 20 + 60;
            int hud_x = 4;
            int hud_y = 400;
            g.setColor(new Color(60, 72, 170));
            g.fillRoundRect(hud_x, hud_y, fillwidth, 44, 24, 24);
            g.setColor(new Color(32, 32, 32));
            g.fillRoundRect(hud_x + 4, hud_y + 4, fillwidth - 8, 36, 20, 20);
            g.setColor(Color.WHITE);
            g.setFont(new Font("sanserif", 1, 32));
            g.drawString("" + this.platforms_activated + "/" + this.platform_vector.size(), hud_x + 8, hud_y + 32);
        }
        catch (Exception ex) {
            // empty catch block
        }
    }

    public abstract String[] getTileMap();
}

