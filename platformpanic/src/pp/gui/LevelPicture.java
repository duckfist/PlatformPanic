package pp.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class LevelPicture {
    public static final int XMIDPOINT = 368;
    public static final int YMIDPOINT = 324;
    public static int m_xpos = 368;
    public static int m_ypos = 324;
    public static int width = 0;
    public static int height = 0;
    public static ArrayList<LevelPictureElement> m_elements;
    public static boolean is_available = false;
    private static volatile boolean is_rendering = false;
    
    public static void setElements(String[] definitions) {
    	
    	// Wait to change m_elements in case it's currently being painted. Must wait
    	// because this method is called by key/mouse handlers, which may be a separate thread.
    	while (is_rendering) {
    		System.out.println("Tried to setElements while painting");
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	// Build the level map from the provided level array
        m_elements = new ArrayList<LevelPictureElement>();
        for (int j = 0; j < definitions.length; ++j) {
            for (int i = 0; i < definitions[0].length(); ++i) {
                char element = definitions[j].charAt(i);
                LevelPictureElement graphic;
                switch (element) {
                    case 'g': {
                        graphic = new LevelPictureElement(i, j, 0);
                        m_elements.add(graphic);
                        break;
                    }
                    case 'x': {
                        graphic = new LevelPictureElement(i, j, 1);
                        m_elements.add(graphic);
                        break;
                    }
                    case 's': {
                        graphic = new LevelPictureElement(i, j, 2);
                        m_elements.add(graphic);
                        break;
                    }
                    case 'u': {
                        graphic = new LevelPictureElement(i, j, 2);
                        m_elements.add(graphic);
                        break;
                    }
                    case ' ': {
                    	break;
                    }
                    case '!': {
                    	break;
                    }
                    case '*': {
                    	break;
                    }
                    default: {
                        graphic = new LevelPictureElement(i, j, 3);
                        m_elements.add(graphic);
                        break;
                    }
                }
            }
        }
        
        // Set the scaled size of the picture based on the dimensions of the level
        int longer_side = definitions.length > definitions[0].length() ? definitions.length : definitions[0].length();
        LevelPictureElement.m_size = 120 / longer_side + 1;
        m_xpos = 368 - definitions[0].length() / 2 * LevelPictureElement.m_size;
        m_ypos = 324 - definitions.length / 2 * LevelPictureElement.m_size;
        width = definitions[0].length() * LevelPictureElement.m_size;
        height = definitions.length * LevelPictureElement.m_size;
    }

    public static void paintComponent(Graphics2D g2d) {
    	
    	// Don't draw the map if it isn't unlocked yet
    	if (!is_available) { return; }
    	
    	// Catch possible race condition with input event handlers modifying m_elements
    	is_rendering = true;
        if (m_elements == null) {
        	System.out.println("Tried to paint while m_elements == null");
        	is_rendering = false;
            return;
        }
        
        // Draw the map
        g2d.setColor(Color.BLACK);
        g2d.fillRect(m_xpos, m_ypos, width, height);
        for (int i = 0; i < m_elements.size(); i++) {
        	m_elements.get(i).paintElement(g2d);
        }
//        for (LevelPictureElement element : m_elements) {
//        	
//            element.paintElement(g2d);
//        }
        is_rendering = false;
    }
}

