package model.imageclasses;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Class used to create a simple white image with the desired width and height.
 */
public class SimpleWhiteImage extends AbstractImage {
  /**
   * Creates a rectangular white image with the given width and height.
   * @param width The width of the image
   * @param height The height of the image
   */
  public SimpleWhiteImage(int width, int height) {
    this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        this.img.setRGB(x, y, Color.WHITE.getRGB());
      }
    }
  }

}
