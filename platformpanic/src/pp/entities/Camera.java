package pp.entities;
import pp.levels.Level;
import pp.utilities.Float2;
import pp.utilities.Timer;

public class Camera {
	
    public float x_pos;
    public float y_pos;
    public float x_pos_center;
    public float y_pos_center;
    public boolean has_destination = false;
    public boolean player_anchor = true;
    public float x_dest;
    public float y_dest;
    public float dist_to_dest = 0.0f;
    public float x_velocity;
    public float y_velocity;
    public float velocity = 0.0f;
    public float max_velocity = 15.0f;
    public float acceleration = 0.5f;
    public final float DEFAULT_MAX_VEL = 15.0f;
    public final float DEFAULT_MAX_ACC = 0.5f;
//    public float max_velocity = 12.0f;
//    public float acceleration = 0.45f;
//    public final float DEFAULT_MAX_VEL = 12.0f;
//    public final float DEFAULT_MAX_ACC = 0.45f;
    
    private Level level;
    private Timer seek_time = new Timer();

    public Camera(Level current_level) {
        this.level = current_level;
    }

    public void moveCamera(Player player) {
        this.seek_time.update();
        this.x_pos = this.level.left_level_border;
        this.y_pos = this.level.up_level_border;
        this.x_pos_center = this.level.left_level_border + 256.0f;
        this.y_pos_center = this.level.up_level_border + 224.0f;
        if (this.has_destination) {
            float y_unit_component;
            float x_unit_component;
            float distance = (float)Math.sqrt((this.x_dest - this.x_pos_center) * (this.x_dest - this.x_pos_center) + (this.y_dest - this.y_pos_center) * (this.y_dest - this.y_pos_center));
            if (Float.isNaN(distance) || distance == 0.0f) {
                distance = 0.0f;
                x_unit_component = 0.0f;
                y_unit_component = 0.0f;
            } else {
                x_unit_component = (this.x_dest - this.x_pos_center) / distance;
                y_unit_component = (this.y_dest - this.y_pos_center) / distance;
            }
            if (distance > 80.0f) {
                if (this.velocity < this.max_velocity) {
                    this.velocity += this.acceleration * this.seek_time.getDtScaled();
                }
                if (this.velocity > this.max_velocity) {
                    this.velocity = this.max_velocity;
                }
            } else if (distance < 80.0f) {
                if (distance > 25.0f) {
                    this.velocity = this.velocity > 2.0f ? (float)((double)this.velocity - (double)this.acceleration * 0.6 * (double)this.seek_time.getDtScaled()) : 2.0f;
                } else {
                    if (this.velocity > 3.0f) {
                        this.velocity = 3.0f;
                    }
                    this.velocity = this.velocity > 0.4f ? (float)((double)this.velocity - (double)this.acceleration * 0.6 * (double)this.seek_time.getDtScaled()) : 0.4f;
                }
            }
            this.x_velocity = x_unit_component * this.velocity;
            this.y_velocity = y_unit_component * this.velocity;
            this.setCameraPos(this.x_pos + this.x_velocity, this.y_pos + this.y_velocity, player);
            if (distance < 1.5f) {
                this.setCameraPos(this.x_dest - 256.0f, this.y_dest - 224.0f, player);
                this.velocity = 0.0f;
                this.x_velocity = 0.0f;
                this.y_velocity = 0.0f;
                this.has_destination = false;
            }
        }
        if (this.player_anchor) {
            this.x_velocity = 0.0f;
            this.setCameraPos(player.f2_pos.x + 14.0f + this.level.left_level_border - 256.0f, this.y_pos, player);
            this.setDestination(this.y_pos_center, player.f2_pos.y + this.level.up_level_border);
            if (player.f2_pos.y > 324.0f) {
                this.setDestination(this.y_pos_center, this.level.down_level_border + 512.0f);
            }
        }
    }

    public void speedIncrease() {
        this.max_velocity = 30.0f;
        this.acceleration = 2.0f;
    }

    public void speedDefault() {
        this.max_velocity = DEFAULT_MAX_VEL;
        this.acceleration = DEFAULT_MAX_ACC;
    }

    public void setDestination(float x_center, float y_center) {
        this.y_dest = y_center;
        this.x_dest = x_center;
        this.has_destination = true;
        if (this.y_dest < 224.0f) {
            this.y_dest = 224.0f;
        } else if (this.y_dest > this.level.level_height - 224.0f) {
            this.y_dest = this.level.level_height - 224.0f;
        }
        if (this.x_dest < 256.0f) {
            this.x_dest = 256.0f;
        } else if (this.x_dest > this.level.level_width - 256.0f) {
            this.x_dest = this.level.level_width - 256.0f;
        }
    }

    public void setDestination(Float2 f2_pos) {
        this.setDestination(f2_pos.x, f2_pos.y);
    }

    public void setCameraPos(float x, float y, Player player) {
        if (x < 0.0f) {
            x = 0.0f;
        } else if (x > this.level.level_width - 512.0f) {
            x = this.level.level_width - 512.0f;
        }
        if (y < 0.0f) {
            y = 0.0f;
        } else if (y > this.level.level_height - 448.0f) {
            y = this.level.level_height - 448.0f;
        }
        this.x_pos = x;
        this.y_pos = y;
        this.x_pos_center = x + 256.0f;
        this.y_pos_center = y + 224.0f;
        float scroll_x = this.x_pos - this.level.left_level_border;
        float scroll_y = this.y_pos - this.level.up_level_border;
        this.level.scrollLevel(scroll_x, scroll_y);
        player.scrollPlayer(scroll_x, scroll_y);
    }

    public void setCameraPos(Float2 pos, Player player) {
        this.setCameraPos(pos.x, pos.y, player);
    }
}

