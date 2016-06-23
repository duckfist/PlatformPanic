package pp.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.util.Iterator;
import java.util.Vector;

import pp.game.GamePanel;
import pp.levels.Level;
import pp.utilities.Float2;
import pp.utilities.FloatRect;
import pp.utilities.ImageLoader;
import pp.utilities.Timer;

public class Player {
	
    public static final float GRAVITY = 0.3f;
    public static final float JUMP_VEL = -8.0f;
    public static final float Y_VEL_MAX = 10.0f;
    public static final float X_VEL_MAX = 3.0f;
    public static final float WIDTH = 12.0f;
    public static final float HEIGHT = 36.0f;
    public Float2 f2_pos;
    public Float2 f2_vel;
    public Float2 f2_acc;
    public boolean jumping;
    public boolean falling;
    public boolean face_left;
    public boolean walking;
    public boolean on_platform;
    public boolean spawning;
    public boolean dead;
    public boolean autojump;
    public boolean can_double_jump;
    public boolean has_double_jumped;
    public boolean has_spring_jumped;
    public boolean has_angled_spring_jumped;
    
    private boolean pressing_left;
    private boolean pressing_right;
    //private boolean pressing_jump;
    public ImageLoader image_loader;
    private BufferedImage player_stand_left;
    private BufferedImage player_walk_left1;
    private BufferedImage player_walk_left2;
    private BufferedImage player_jump_left;
    private BufferedImage player_fall_left1;
    private BufferedImage player_fall_left2;
    private BufferedImage player_stand_right;
    private BufferedImage player_walk_right1;
    private BufferedImage player_walk_right2;
    private BufferedImage player_jump_right;
    private BufferedImage player_fall_right1;
    private BufferedImage player_fall_right2;
    private Timer frame_timer = new Timer();
    private Timer spawn_timer = new Timer(false);

    public Player(float x, float y) {
        this.f2_pos = new Float2(x, y);
        this.f2_vel = new Float2();
        this.f2_acc = new Float2(0.0f, 0.3f);
        this.face_left = false;
        this.jumping = false;
        this.falling = false;
        this.walking = false;
        this.on_platform = false;
        this.spawning = false;
        this.dead = false;
        this.autojump = false;
        this.has_double_jumped = false;
        this.has_spring_jumped = false;
        this.has_angled_spring_jumped = false;
        this.image_loader = new ImageLoader();
        this.loadImages();
    }

    public FloatRect getHitArea() {
        return new FloatRect(this.f2_pos, 28.0f, 44.0f);
    }

    public void loadImages() {
        int w = 32;
        int h = 48;
        BufferedImage player_map = this.image_loader.loadImage("/Graphics/player_map1.png");
        try {
            this.player_stand_left = player_map.getSubimage(0, 0, w, h);
            this.player_walk_left1 = player_map.getSubimage(0, h, w, h);
            this.player_walk_left2 = player_map.getSubimage(0, 2 * h, w, h);
            this.player_jump_left = player_map.getSubimage(0, 3 * h, w, h);
            this.player_fall_left1 = player_map.getSubimage(w, 0, w, h);
            this.player_fall_left2 = player_map.getSubimage(w, h, w, h);
            this.player_stand_right = player_map.getSubimage(2 * w, 0, w, h);
            this.player_walk_right1 = player_map.getSubimage(2 * w, h, w, h);
            this.player_walk_right2 = player_map.getSubimage(2 * w, 2 * h, w, h);
            this.player_jump_right = player_map.getSubimage(2 * w, 3 * h, w, h);
            this.player_fall_right1 = player_map.getSubimage(3 * w, 0, w, h);
            this.player_fall_right2 = player_map.getSubimage(3 * w, h, w, h);
        }
        catch (RasterFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void resetPlayer() {
        this.dead = false;
        this.autojump = false;
        this.has_double_jumped = false;
        this.has_spring_jumped = false;
        this.has_angled_spring_jumped = false;
    }

    public void spawnPlayer() {
        this.spawn_timer.initTimer();
        this.spawning = true;
        this.dead = false;
        this.f2_vel = new Float2();
    }

    public void keyLeftIsDown(boolean value) {
        this.pressing_left = value;
        if (this.has_angled_spring_jumped) {
            return;
        }
        this.walking = value;
        if (value) {
            this.face_left = true;
            this.f2_vel.x = -3.0f;
        } else {
            this.walking = this.pressing_right;
            if (this.pressing_right) {
                this.face_left = false;
                this.f2_vel.x = 3.0f;
            }
        }
    }

    public void keyRightIsDown(boolean value) {
        this.pressing_right = value;
        if (this.has_angled_spring_jumped) {
            return;
        }
        this.walking = value;
        if (value) {
            this.face_left = false;
            this.f2_vel.x = 3.0f;
        } else {
            this.walking = this.pressing_left;
            if (this.pressing_left) {
                this.face_left = true;
                this.f2_vel.x = -3.0f;
            }
        }
    }

    public void keyJumpIsDown(boolean value) {
        //this.pressing_jump = value;
        if (value) {
            if (!this.jumping && !this.falling) {
                this.jumping = true;
                this.can_double_jump = true;
                this.f2_vel.y = -8.0f;
                ++GamePanel.saveData.total_jumps;
            } else if (GamePanel.saveData.unlocked_double_jump && this.can_double_jump) {
                this.jumping = true;
                this.falling = false;
                this.can_double_jump = false;
                this.has_double_jumped = true;
                this.f2_vel.y = -6.4f;
                ++GamePanel.saveData.total_jumps;
            }
        } else if (this.jumping && !this.has_spring_jumped) {
            this.playerFall(true);
        }
    }

    public void springBoardLaunch(float power) {
        this.jumping = true;
        this.can_double_jump = true;
        this.has_double_jumped = false;
        this.has_spring_jumped = true;
        this.springBoardAngledLand();
        this.falling = false;
        this.f2_vel.y = -8.0f * power;
    }

    public void springBoardUpsideDown() {
        this.jumping = false;
        this.has_spring_jumped = true;
        this.springBoardAngledLand();
        this.falling = true;
        this.f2_vel.y = 10.0f;
    }

    public void springBoardAngledLaunch(float jump_power, float x_velocity) {
        this.jumping = true;
        this.walking = true;
        this.can_double_jump = true;
        this.has_double_jumped = false;
        this.has_spring_jumped = true;
        this.has_angled_spring_jumped = true;
        this.falling = false;
        this.f2_vel.y = -8.0f * jump_power;
        this.f2_vel.x = x_velocity;
        this.face_left = x_velocity < 0.0f;
    }

    public void springBoardAngledLand() {
    	this.walking = false;
        this.has_angled_spring_jumped = false;
        this.f2_vel.x = 0.0f;
        if (GamePanel.key_left) {
            this.keyLeftIsDown(true);
        } else if (GamePanel.key_right) {
            this.keyRightIsDown(true);
        }
    }

    public void playerFall(boolean value) {
        this.jumping = false;
        if (value) {
            if (!this.falling && !this.has_double_jumped) {
                this.can_double_jump = true;
            }
        } else {
            this.has_double_jumped = false;
            this.has_spring_jumped = false;
            if (this.has_angled_spring_jumped) {
                this.springBoardAngledLand();
            }
        }
        this.falling = value;
        if (this.f2_vel.y < 0.0f || !this.falling) {
            this.f2_vel.y = 0.0f;
        }
    }

    public void jumpToPoint(Float2 dest) {
        if (!this.autojump) {
            this.autojump = true;
            //float t = GamePanel.time.getDt();
            this.f2_vel.y = -10.0f;
        }
        if (this.f2_vel.y < 0.0f) {
            this.jumping = true;
        } else {
            this.jumping = false;
            this.falling = true;
        }
        if (this.f2_pos.y < 448.0f) {
            this.f2_pos.y += this.f2_vel.y * GamePanel.time.getDtScaled();
            this.f2_vel.y += 0.3f * GamePanel.time.getDtScaled();
            this.f2_pos.x += this.f2_vel.x * GamePanel.time.getDtScaled();
        }
    }

    // TODO: Clean this method up.  The disassembling process replaced some flow
    // control statements with goto.
    public void playerMove(Level level) {
        LevelElement test_left;
        LevelElement test_down;
        LevelElement test_up;
        LevelElement test_right;
        block56 : {
            LevelElement[] tiles_right;
            LevelElement[] tiles_left;
            if (this.spawning) {
                this.spawn_timer.update();
                if (this.spawn_timer.getTt() > 1.5f) {
                    this.spawning = false;
                    level.camera.player_anchor = true;
                    level.camera.speedDefault();
                }
                return;
            }
            if (this.dead) {
                return;
            }
            Float2 old_pos = this.f2_pos.getCopy();
            LevelElement[] tiles_up = new LevelElement[2];
            LevelElement[] tiles_down = new LevelElement[2];
            tiles_left = new LevelElement[3];
            tiles_right = new LevelElement[3];
            test_up = null;
            test_down = null;
            test_left = null;
            test_right = null;
            if (this.jumping || this.falling) {
                float y_displacement = this.f2_vel.y * GamePanel.time.getDtScaled();
                if (y_displacement > 44.0f) {
                    y_displacement = 44.0f;
                }
                this.f2_pos.y += y_displacement;
                this.f2_vel.y += this.f2_acc.y * GamePanel.time.getDtScaled();
                if (this.f2_vel.y > 10.0f) {
                    this.f2_vel.y = 10.0f;
                }
            }
            tiles_up = level.testCollisionVert(new Float2(this.f2_pos.x + level.left_level_border, this.f2_pos.y + level.up_level_border), false);
            tiles_down = level.testCollisionVert(new Float2(this.f2_pos.x + level.left_level_border, this.f2_pos.y + level.up_level_border + 44.0f), false);
            if (tiles_up[0] != null) {
                test_up = tiles_up[0];
            } else if (tiles_up[1] != null) {
                test_up = tiles_up[1];
            }
            if (tiles_down[0] != null) {
                test_down = tiles_down[0];
            } else if (tiles_down[1] != null) {
                test_down = tiles_down[1];
            }
            Vector<Platform> platforms = level.testCollisionPlatform(this);
            for (int i = 0; i < platforms.size(); ++i) {
                Platform platform_to_test = platforms.elementAt(i);
                if (this.falling && old_pos.y + 36.0f < platform_to_test.f2_pos.y) {
                    if (!this.on_platform) {
                        level.platforms_activated += platform_to_test.activatePlatform();
                    }
                    this.on_platform = true;
                    platform_to_test.setStanding(true);
                    Platform.setStaticStanding(true);
                    continue;
                }
                if (old_pos.y + 36.0f >= platform_to_test.f2_pos.y + 6.0f) continue;
                if (!(this.on_platform || this.jumping || this.falling)) {
                    level.platforms_activated += platform_to_test.activatePlatform();
                    this.on_platform = true;
                    platform_to_test.setStanding(true);
                    Platform.setStaticStanding(true);
                    this.f2_pos.y = platform_to_test.f2_pos.y - 44.0f;
                    continue;
                }
                if (!this.on_platform || platform_to_test.getPlayerStanding()) continue;
                level.platforms_activated += platform_to_test.activatePlatform();
                platform_to_test.setStanding(true);
                Platform.setStaticStanding(true);
                this.f2_pos.y = platform_to_test.f2_pos.y - 44.0f;
            }
            if (platforms.isEmpty()) {
                this.on_platform = false;
                Platform.setStaticStanding(false);
            } else {
                //boolean standing_flag = false;
                for (int i2 = 0; i2 < platforms.size(); ++i2) {
                    Platform p = platforms.elementAt(i2);
                    if (!p.getPlayerStanding()) continue;
                    //standing_flag = true;
                }
                
                
                
            }
            if (!this.jumping && !this.falling) {
                if (test_down == null && !this.on_platform) {
                    this.playerFall(true);
                } else {
                    if (this.on_platform && test_down != null) {
                        this.playerFall(false);
                        this.f2_pos.translate(0.0f, test_down.y_pos_up - (this.f2_pos.y + 44.0f) + 1.0f);
                        this.on_platform = false;
                        platforms.clear();
                        Platform.setStaticStanding(false);
                        return;
                    }
                    if (this.on_platform && test_up != null) {
                        Platform.setStaticStanding(false);
                        Iterator<Platform> i$ = platforms.iterator();
                        do {
                            if (!i$.hasNext()) {
                                this.on_platform = false;
                                this.f2_pos.translate(0.0f, 12.0f);
                                System.out.println("or here?");
                                this.playerFall(true);
                                return;
                            }
                            Platform p = i$.next();
                            p.setStanding(false);
                        } while (true);
                    }
                }
            } else if (this.falling) {
                if (test_down != null) {
                    SoundClip.LAND.play();
                    this.playerFall(false);
                    this.f2_pos.translate(0.0f, test_down.y_pos_up - (this.f2_pos.y + 44.0f) + 1.0f);
                    if (this.has_angled_spring_jumped == true) {
                    	this.has_spring_jumped = false;
                    	this.walking = false;
                    }
                } else if (this.on_platform) {
                    this.playerFall(false);
                    try {
                        for (Platform p : platforms) {
                            if (!p.getPlayerStanding()) continue;
                            SoundClip.PLAND.play();
                            this.f2_pos.translate(0.0f, p.f2_pos.y - (this.f2_pos.y + 44.0f) + 1.0f);
//                            this.has_angled_spring_jumped = false;
//                            this.has_spring_jumped = false;
                        }
                    }
                    catch (NullPointerException e) {
                        System.out.println("ERROR: on_platform = true, but platform doesn't exist.");
                    }
                }
            } else if (this.jumping) {
                if (test_up != null) {
                    this.playerFall(true);
                    this.f2_pos.translate(0.0f, test_up.y_pos_down - this.f2_pos.y);
                }
                if (this.f2_vel.y > 0.0f) {
                    this.playerFall(true);
                }
            }
            if (this.walking) {
                float x_displacement = this.f2_vel.x * GamePanel.time.getDtScaled();
                if (x_displacement > 28.0f) {
                    x_displacement = 28.0f;
                }
                this.f2_pos.x += x_displacement;
            }
            tiles_left = level.testCollisionSide(new Float2(this.f2_pos.x + level.left_level_border, this.f2_pos.y + level.up_level_border), false);
            tiles_right = level.testCollisionSide(new Float2(this.f2_pos.x + level.left_level_border + 28.0f, this.f2_pos.y + level.up_level_border), false);
            if (tiles_left[0] != null) {
                test_left = tiles_left[0];
            } else if (tiles_left[1] != null) {
                test_left = tiles_left[1];
            } else if (tiles_left[2] != null) {
                test_left = tiles_left[2];
            }
            if (tiles_right[0] != null) {
                test_right = tiles_right[0];
            } else if (tiles_right[1] != null) {
                test_right = tiles_right[1];
            } else if (tiles_right[2] != null) {
                test_right = tiles_right[2];
            }
            boolean wall_collision = false;
            if (test_left != null) {
                this.f2_pos.translate(test_left.x_pos_right - this.f2_pos.x, 0.0f);
                wall_collision = true;
            }
            if (test_right != null) {
                this.f2_pos.translate(test_right.x_pos_left - (this.f2_pos.x + 28.0f), 0.0f);
                wall_collision = true;
                if (!wall_collision) break block56;
            }
            //this.springBoardAngledLand();
            
 
        }
        if (this.f2_pos.y + level.up_level_border > level.level_height && !this.dead) {
            this.dead = true;
        }
        if (test_up instanceof Spike || test_down instanceof Spike || test_left instanceof Spike || test_right instanceof Spike) {
            this.dead = true;
        }
    }

    public void scrollPlayer(float x_speed, float y_speed) {
        this.f2_pos.translate(- x_speed, - y_speed);
    }

    public void paintComponent(Graphics g) {
        int int_x = Math.round(this.f2_pos.x);
        int int_y = Math.round(this.f2_pos.y);
        Graphics2D g2d = (Graphics2D)g;
        if (this.spawning) {
            int radius = Math.round(30.0f / (this.spawn_timer.getTt() + 0.2f)) + 10;
            int halfradius = Math.round((float)radius / 2.0f);
            g2d.setColor(Color.WHITE);
            g2d.drawOval(int_x - halfradius + 8, int_y - halfradius + 8, radius, radius);
            return;
        }
        if (this.dead) {
            return;
        }
        if (this.jumping) {
            if (this.face_left) {
                g2d.drawImage(this.player_jump_left, int_x, int_y, null);
            } else {
                g2d.drawImage(this.player_jump_right, int_x, int_y, null);
            }
        } else if (this.falling) {
            this.frame_timer.update();
            if (this.frame_timer.getTt() > 0.2f) {
                this.frame_timer.initTimer();
            }
            if (this.frame_timer.getTt() > 0.1f) {
                if (this.face_left) {
                    g2d.drawImage(this.player_fall_left1, int_x, int_y, null);
                } else {
                    g2d.drawImage(this.player_fall_right1, int_x, int_y, null);
                }
            } else if (this.frame_timer.getTt() >= 0.0f) {
                if (this.face_left) {
                    g2d.drawImage(this.player_fall_left2, int_x, int_y, null);
                } else {
                    g2d.drawImage(this.player_fall_right2, int_x, int_y, null);
                }
            }
        } else if (this.walking) {
            this.frame_timer.update();
            if (this.frame_timer.getTt() > 0.4f) {
                this.frame_timer.initTimer();
            }
            if (this.frame_timer.getTt() > 0.3f) {
                if (this.face_left) {
                    g2d.drawImage(this.player_stand_left, int_x, int_y, null);
                } else {
                    g2d.drawImage(this.player_stand_right, int_x, int_y, null);
                }
            } else if (this.frame_timer.getTt() > 0.2f) {
                if (this.face_left) {
                    g2d.drawImage(this.player_walk_left2, int_x, int_y, null);
                } else {
                    g2d.drawImage(this.player_walk_right2, int_x, int_y, null);
                }
            } else if (this.frame_timer.getTt() > 0.1f) {
                if (this.face_left) {
                    g2d.drawImage(this.player_stand_left, int_x, int_y, null);
                } else {
                    g2d.drawImage(this.player_stand_right, int_x, int_y, null);
                }
            } else if (this.frame_timer.getTt() >= 0.0f) {
                if (this.face_left) {
                    g2d.drawImage(this.player_walk_left1, int_x, int_y, null);
                } else {
                    g2d.drawImage(this.player_walk_right1, int_x, int_y, null);
                }
            }
        } else if (this.face_left) {
            g2d.drawImage(this.player_stand_left, int_x, int_y, null);
        } else {
            g2d.drawImage(this.player_stand_right, int_x, int_y, null);
        }
        g2d.setColor(Color.red);
    }
}

