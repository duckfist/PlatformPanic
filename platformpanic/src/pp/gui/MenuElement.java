package pp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pp.utilities.Timer;

public class MenuElement {
    protected int x_pos;
    protected int y_pos;
    public boolean currently_selected;
    public boolean have_child_menu;
    public MenuPage child_menu;
    protected String element_text;
    protected String element_description;
    protected MenuElementName element_name;
    protected Timer flash_time;
    protected boolean flash_start;
    protected Rectangle hit_area;

    public MenuElement() {
    }

    public MenuElement(int left_x, int up_y, MenuElementName element_name) {
        this.x_pos = left_x;
        this.y_pos = up_y;
        this.element_name = element_name;
        this.flash_time = new Timer();
        this.flash_start = false;
        switch (element_name) {
            case GAMESTART: {
                this.element_text = "Game Start";
                this.element_description = "";
                this.hit_area = new Rectangle(left_x, up_y - 20, 72, 20);
                break;
            }
            case BACK: {
                this.element_text = "Back";
                this.element_description = "Go back to the previous menu.";
                this.hit_area = new Rectangle(left_x, up_y, 32, 14);
                break;
            }
            default: {
                this.element_text = "DEFAULT";
                this.element_description = "DEFAULT";
                this.hit_area = new Rectangle();
            }
        }
        this.currently_selected = false;
    }

    public MenuElementName getType() {
        return this.element_name;
    }

    public Rectangle getHitArea() {
        return this.hit_area;
    }

    public void setCurrentCursor(boolean currently_selected) {
        this.currently_selected = currently_selected;
        if (!currently_selected) {
            this.flash_start = false;
        }
    }

    public void setChildMenu(MenuPage child_menu) {
        this.child_menu = child_menu;
        this.have_child_menu = child_menu != null;
    }

    public void paintMenuElement(Graphics2D g2d) {
        if (this.currently_selected) {
            Font old_font = g2d.getFont();
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("sanserif", 0, 12));
            g2d.drawString(this.element_description, 8, 440);
            g2d.setFont(old_font);
            if (!this.flash_start) {
                this.flash_start = true;
                this.flash_time.initTimer();
            }
            this.flash_time.update();
            if (this.flash_time.getTt() < 0.1f) {
                return;
            }
            if (this.flash_time.getTt() > 0.2f) {
                this.flash_time.initTimer();
            }
        }
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("sanserif", 1, 14));
        g2d.drawString(this.element_text, this.x_pos, this.y_pos);
    }

}

