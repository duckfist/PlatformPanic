package pp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Vector;

import pp.entities.Effects;
import pp.entities.SoundClip;
import pp.levels.*;

public class MainMenu {
	
    private Color[] colors = new Color[255];
    public MenuPage current_menu;
    public MenuPage menu_level_select;
    
    MenuElement me_home_gamestart;
    MenuElement[] me_level = new MenuElement[25];	// Number of stages to display on stage select
    MenuElement me_back;
    Vector<MenuElement> elements_main;
    Vector<MenuElement> elements_level_select;
    MenuPage menu_home;
    
    public MainMenu() {
    	for (int i = 0; i < 255; ++i) {
            this.colors[i] = new Color(0, 64 - i / 4, 128 - i / 2);
        }
        this.initMenu();
        
    }

    public void initMenu() {
    	
        this.me_home_gamestart = new MenuElement(212, 256, MenuElementName.GAMESTART);
        for (int i = 0; i < this.me_level.length; ++i) {
            this.me_level[i] = new LevelToken(i / 5 * 36 + 64, i % 5 * 28 + 260, i);
        }
        this.me_back = new MenuElement(32, 416, MenuElementName.BACK);
        this.elements_main = new Vector<MenuElement>();
        this.elements_main.addElement(this.me_home_gamestart);
        this.elements_level_select = new Vector<MenuElement>();
        for (MenuElement element : this.me_level) {
            this.elements_level_select.addElement(element);
        }
        this.elements_level_select.addElement(this.me_back);
        this.menu_home = new MenuPage(null, this.elements_main);
        this.menu_level_select = new MenuPage(this.menu_home, this.elements_level_select);
        this.me_home_gamestart.setChildMenu(this.menu_level_select);
        this.current_menu = this.menu_home;
        this.loadMapImage();
    }

    public void cursorMoveUp() {
        this.current_menu.cursorMoveUp();
        this.loadMapImage();
        SoundClip.MENU_NAV.play();
    }

    public void cursorMoveDown() {
        this.current_menu.cursorMoveDown();
        this.loadMapImage();
        SoundClip.MENU_NAV.play();
    }

    public MenuElement mousePressed(Point p) {
        MenuElement selected_button = null;
        if (this.current_menu == this.menu_home) {
            Rectangle hit_area = this.me_home_gamestart.getHitArea();
            if (hit_area.contains(p)) {
                this.select();
            }
        } else if (this.current_menu == this.menu_level_select) {
            for (MenuElement button : this.me_level) {
                Rectangle hit_area = button.getHitArea();
                if (!hit_area.contains(p)) continue;
                if (button.currently_selected) {
                    this.select();
                    selected_button = button;
                    continue;
                }
                this.loadMapImage();
                this.current_menu.changeSelect(((LevelToken)button).getLevelNum());
            }
        }
        return selected_button;
    }

    public MenuElement select() {
        MenuElement temp_selection = this.current_menu.getSelect();
        SoundClip soundtoplay = SoundClip.MENU_SELECT;
        if (temp_selection.have_child_menu) {
            this.current_menu = temp_selection.child_menu;
            this.current_menu.getSelect().currently_selected = true;
            this.loadMapImage();
        } else if (!this.current_menu.isRootMenu() && temp_selection.getType() == MenuElementName.BACK) {
            temp_selection.currently_selected = false;
            this.current_menu = this.current_menu.getParentMenu();
            soundtoplay = SoundClip.MENU_BACK;
        }
        soundtoplay.play();
        return temp_selection;
    }

    public void goBack() {
        if (this.current_menu.isRootMenu()) {
            return;
        }
        this.current_menu.getSelect().currently_selected = false;
        this.current_menu = this.current_menu.getParentMenu();
        SoundClip.MENU_BACK.play();
    }

    public void loadMapImage() {
    	LevelPicture.is_available = false;
        if (this.current_menu.getSelect() instanceof LevelToken) {
            LevelToken token = (LevelToken)this.current_menu.getSelect();
            if (!token.getFinished()) {
                LevelPicture.is_available = false;
                return;
            } else {
            	LevelPicture.is_available = true;
            }
            
            int level = token.getLevelNum();
            switch (level) {
                case 0: {
                    LevelPicture.setElements(Level00.getMap());
                    break;
                }
                case 1: {
                    LevelPicture.setElements(Level01.getMap());
                    break;
                }
                case 2: {
                    LevelPicture.setElements(Level02.getMap());
                    break;
                }
                case 3: {
                    LevelPicture.setElements(Level03.getMap());
                    break;
                }
                case 4: {
                    LevelPicture.setElements(Level04.getMap());
                    break;
                }
                case 5: {
                    LevelPicture.setElements(Level05.getMap());
                    break;
                }
                case 6: {
                    LevelPicture.setElements(Level06.getMap());
                    break;
                }
                case 7: {
                    LevelPicture.setElements(Level07.getMap());
                    break;
                }
                case 8: {
                    LevelPicture.setElements(Level08.getMap());
                    break;
                }
                case 9: {
                    LevelPicture.setElements(Level09.getMap());
                    break;
                }
                case 10: {
                    LevelPicture.setElements(Level10.getMap());
                    break;
                }
                case 11: {
                    LevelPicture.setElements(Level11.getMap());
                    break;
                }
                case 12: {
                    LevelPicture.setElements(Level12.getMap());
                    break;
                }
                case 13: {
                    LevelPicture.setElements(Level13.getMap());
                    break;
                }
                case 14: {
                    LevelPicture.setElements(Level14.getMap());
                    break;
                }
                case 15: {
                    LevelPicture.setElements(Level15.getMap());
                    break;
                }
                case 16: {
                    LevelPicture.setElements(Level16.getMap());
                    break;
                }
                case 17: {
                    LevelPicture.setElements(Level17.getMap());
                    break;
                }
                case 18: {
                    LevelPicture.setElements(Level18.getMap());
                    break;
                }
                case 19: {
                    LevelPicture.setElements(Level19.getMap());
                    break;
                }
                case 20: {
                    LevelPicture.setElements(Level20.getMap());
                    break;
                }
                case 21: {
                    LevelPicture.setElements(Level21.getMap());
                    break;
                }
                case 22: {
                    LevelPicture.setElements(Level22.getMap());
                    break;
                }
                case 23: {
                    LevelPicture.setElements(Level23.getMap());
                    break;
                }
                case 24: {
                    LevelPicture.setElements(Level24.getMap());
                    break;
                }
//                case 25: {
//                    LevelPicture.setElements(Level25.getMap());
//                    break;
//                }
//                case 26: {
//                    LevelPicture.setElements(Level26.getMap());
//                    break;
//                }
//                case 27: {
//                    LevelPicture.setElements(Level27.getMap());
//                    break;
//                }
//                case 28: {
//                    LevelPicture.setElements(Level28.getMap());
//                    break;
//                }
//                case 29: {
//                    LevelPicture.setElements(Level29.getMap());
//                    break;
//                }
            }
        } else {
            LevelPicture.m_elements = null;
            return;
        }
    }

    public void displayMenu(Graphics2D g2d) {
        for (int i = 0; i < 255; ++i) {
            g2d.setColor(this.colors[i]);
            g2d.fillRect(0, i * 2, 512, 2);
            g2d.drawLine(0, i * 2, 512, i);
        }
        int[] x_pts = new int[]{124, 512, 512};
        int[] y_pts = new int[]{448, 254, 448};
        Polygon triangle = new Polygon(x_pts, y_pts, 3);
        g2d.fill(triangle);
        g2d.setColor(Color.WHITE);
        this.current_menu.displayMenu(g2d);
        if (this.current_menu == this.menu_home) {
            Effects.drawStringShadow(g2d, "Platform Panic", new Font("monospaced", 1, 32), Color.WHITE, 128, 192);
        }
        if (this.current_menu == this.menu_level_select) {
            LevelPicture.paintComponent(g2d);
        }
    }
}

