package pp.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Vector;

import pp.game.GameFrame;
import pp.game.GamePanel;

public class MenuPage {
    public Vector<MenuElement> menu_elements;
    private int current_cursor;
    private MenuPage parent_menu;
    //private boolean current_menu;

    public MenuPage(MenuPage parent_menu, Vector<MenuElement> menu_elements) {
        this.menu_elements = menu_elements;
        this.parent_menu = parent_menu;
        //this.current_menu = false;
        this.current_cursor = 0;
        menu_elements.elementAt((int)this.current_cursor).currently_selected = true;
    }

    public boolean isRootMenu() {
        if (this.parent_menu == null) {
            return true;
        }
        return false;
    }

    public void cursorMoveUp() {
    	MenuElement currentSelection = this.menu_elements.elementAt((int)this.current_cursor);
    	currentSelection.currently_selected = false;
    	
    	// Keep moving cursor until next available slot
    	boolean done = false;
    	while (!done) {
    		// Wrap cursor
    		--this.current_cursor;
            if (this.current_cursor < 0) {
                this.current_cursor = this.menu_elements.size() - 1;
            }
            
            MenuElement nextSelection = this.menu_elements.elementAt((int)this.current_cursor);
        	// Cursor moved to "Back" button, don't need to check
        	if (!(nextSelection instanceof LevelToken)) {
        		done = true;
        	}
        	else {
        		// Check if level has been added yet
        		if (((LevelToken)nextSelection).level_exists) {
        			done = true;
        		}
        	}
    	}
        
        
        this.menu_elements.elementAt((int)this.current_cursor).currently_selected = true;
    }

    public void cursorMoveDown() {
    	MenuElement currentSelection = this.menu_elements.elementAt((int)this.current_cursor);
        currentSelection.currently_selected = false;
           
        // Keep moving cursor until next available slot
        boolean done = false;
        while (!done) {
        	
        	// Wrap cursor
        	++this.current_cursor;
            if (this.current_cursor >= this.menu_elements.size()) {
                this.current_cursor = 0;
            }
            
        	MenuElement nextSelection = this.menu_elements.elementAt((int)this.current_cursor);
        	// Cursor moved to "Back" button, don't need to check
        	if (!(nextSelection instanceof LevelToken)) {
        		done = true;
        	}
        	else {
        		// Check if level has been added yet
        		if (((LevelToken)nextSelection).level_exists) {
        			done = true;
        		}
        	}
        }
        
        this.menu_elements.elementAt((int)this.current_cursor).currently_selected = true;
    }

    public void resetCursor() {
        this.menu_elements.elementAt((int)this.current_cursor).currently_selected = false;
        this.current_cursor = 0;
        this.menu_elements.elementAt((int)this.current_cursor).currently_selected = true;
    }

    public MenuElement getSelect() {
        return this.menu_elements.elementAt(this.current_cursor);
    }

    public void changeSelect(int index) {
        this.menu_elements.elementAt((int)this.current_cursor).currently_selected = false;
        this.menu_elements.elementAt((int)index).currently_selected = true;
        this.current_cursor = index;
    }

    public MenuPage getParentMenu() {
        if (this.parent_menu == null) {
            return this;
        }
        return this.parent_menu;
    }

    public void displayMenu(Graphics2D g2d) {
        for (int i = 0; i < this.menu_elements.size(); ++i) {
            this.menu_elements.elementAt(i).paintMenuElement(g2d);
        }
        g2d.setColor(Color.white);
        g2d.setFont(new Font("sanserif", 0, 10));
        g2d.drawString("version " + GameFrame.GameVersion, 8 , 14);
//        if (GamePanel.saveData.username == "") {
//            g2d.drawString("You are not logged in.", 8, 14);
//        } else {
//            g2d.drawString("Logged in as " + GamePanel.saveData.username + ".", 8, 14);
//        }
    }
}

