package src.Model.ImageClasses;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Class used to create a Rainbow image. This has a predefined width, height, and set of colors.
 * Displays those colors in a way similar to a rainbow.
 */
public class RainbowImage extends AbstractImage {
  private int width;
  private int height;

  /**
   * Creates a RainbowImage object. Sets the width to 500, height to 300, and img to a new
   * BufferedImage object. It then applies the colors to the img so that it can display a rainbow.
   */
  public RainbowImage() {
    this.width = 500;
    this.height = 300;
    this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    this.makeRainbow();
  }

  private void makeRainbow() {
    for (int x = 0; x < this.width; x++) {
      for (int y = 0; y < this.height; y++) {
        if (y < 50) {
          this.img.setRGB(x, y, new Color(255, 0, 10).getRGB());
        } else if (y < 100) {
          this.img.setRGB(x, y, new Color(255, 150, 0).getRGB());
        } else if (y < 150) {
          this.img.setRGB(x, y, new Color(255, 250, 0).getRGB());
        } else if (y < 200) {
          this.img.setRGB(x, y, new Color(0, 255, 0).getRGB());
        } else if (y < 250) {
          this.img.setRGB(x, y, new Color(46, 55, 255).getRGB());
        } else {
          this.img.setRGB(x, y, new Color(255, 0, 240).getRGB());
        }
      }
    }
  }
}
