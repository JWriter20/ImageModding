package Model.Modifications;

import java.awt.Color;
import Model.ImageClasses.Image;

/**
 * Superclass for any modification to an image that can be represented as applying a kernel to
 * each pixel in a picture.
 */
public abstract class Filter implements IModifyImage {
  protected double[][] kernel;

  @Override
  public void apply(Image img) {
    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = 0; y < img.getHeight(); y++) {
        img.setColorAt(x, y, this.applyKernel(img, x, y));
      }
    }
  }

  /**
   * Applies the kernel to a single pixel at position x,y in the image img. Returns the new color
   * of the pixel.
   * @param img
   * @param x
   * @param y
   * @return
   */
  private Color applyKernel(Image img, int x, int y) {
    int offSet = kernel[0].length / 2;
    int r = 0;
    int g = 0;
    int b = 0;
    for (int kernelX = 0; kernelX < kernel.length; kernelX++) {
      for (int kernelY = 0; kernelY < kernel[0].length; kernelY++) {
        int imgX = kernelX + x - offSet;
        int imgY = kernelY + y - offSet;
        if (imgX >= 0 && imgX < img.getWidth()
            && imgY >= 0 && imgY < img.getHeight()) {
          r += (this.kernel[kernelX][kernelY] * img.getColorAt(imgX, imgY).getRed());
          g += (this.kernel[kernelX][kernelY] * img.getColorAt(imgX, imgY).getGreen());
          b += (this.kernel[kernelX][kernelY] * img.getColorAt(imgX, imgY).getBlue());
        }
      }
    }
    if (r < 0) {
      r = 0;
    } else if (r > 255) {
      r = 255;
    }

    if (g < 0) {
      g = 0;
    } else if (g > 255) {
      g = 255;
    }
    if (b < 0) {
      b = 0;
    } else if (b > 255) {
      b = 255;
    }
    return new Color(r, g, b);
  }

}
