package pp.utilities;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageLoader {

    public ImageLoader() {
    }

    public BufferedImage loadImage(String fnm) {
        try {
        	BufferedImage im = ImageIO.read(getClass().getResource(fnm));
            return im;
        }
        catch (Exception e) {
            System.out.println("Load Image error for " + fnm + ":\n" + e);
            return null;
        }
    }
}

