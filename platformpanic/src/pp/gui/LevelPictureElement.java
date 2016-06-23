package pp.gui;

import java.awt.Color;
import java.awt.Graphics2D;

public class LevelPictureElement {
    public static int m_size = 3;
    public int m_col;
    public int m_row;
    public int m_type;

    public LevelPictureElement(int col, int row, int type) {
        this.m_col = col;
        this.m_row = row;
        this.m_type = type;
    }

    public void paintElement(Graphics2D g2d) {
        int screen_x = this.m_col * m_size + LevelPicture.m_xpos;
        int screen_y = this.m_row * m_size + LevelPicture.m_ypos;
        switch (this.m_type) {
            case 0: {
                g2d.setColor(Color.GRAY);
                break;
            }
            case 1: {
                g2d.setColor(Color.RED);
                break;
            }
            case 2: {
                g2d.setColor(Color.BLUE);
                break;
            }
            case 3: {
                g2d.setColor(Color.GREEN);
                break;
            }
        }
        g2d.fillRect(screen_x, screen_y, m_size, m_size);
    }
}

