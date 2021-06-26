package model.modifications;

import model.imageclasses.Image;
import model.imageclasses.SimpleWhiteImage;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * Class used to downscale an image to the desired dimensions.
 */
public class DownScale {

  /**
   * Creates a downscaled version of the given image. The resulting image will
   * have the given width and height.
   * @param img The image to downscale
   * @param newWidth The width of the new image
   * @param newHeight The height of the new image
   * @return A new Image with the given width and height, made from downscaling the given image
   * @throws IllegalArgumentException If the provided width or height are greater than or equal
   *      to the width or height of the given Image
   */
  public Image apply(Image img, int newWidth, int newHeight) throws IllegalArgumentException {
    if (img.getWidth() <= newWidth) {
      throw new IllegalArgumentException("The supplied image is not wide enough");
    } else if (img.getHeight() <= newHeight) {
      throw new IllegalArgumentException("The supplied image is not tall enough");
    }

    Image newImage = new SimpleWhiteImage(newWidth, newHeight);
    Map<Point2D, Color> colorMap = this.mapPixelsToColor(newImage, img);

    for (int x = 0; x < newWidth; x++) {
      for (int y = 0; y < newHeight; y++) {
        newImage.setColorAt(x, y, colorMap.get(new Point(x,y)));
      }
    }
    return newImage;
  }


  /**
   * Creates a map from pixels in the smaller image to a Color used when downscaling.
   * The Color is determined by the pixels in the larger Image that
   * are at the same proportion to the edge of the Image.
   * @param smaller The smaller Image (new)
   * @param larger The larger Image (original)
   * @return A Map from Point2D objects to Color objects that is can be used for downscaling
   */
  private Map<Point2D, Color> mapPixelsToColor(Image smaller, Image larger) {
    Map<Point2D, Color> smallerToLarger = new HashMap<Point2D, Color>();
    int smallerWidth = smaller.getWidth();
    int smallerHeight = smaller.getHeight();

    int largerWidth = larger.getWidth();
    int largerHeight = larger.getHeight();

    for (int x = 0; x < smaller.getWidth(); x++) {
      for (int y = 0; y < smaller.getHeight(); y++) {
        Point2D currentPoint = new Point(x, y);

        double xProportion = (double)x / smallerWidth;
        double yProportion = (double)y / smallerHeight;

        double newX = xProportion * largerWidth;
        double newY = yProportion * largerHeight;

        Color mappedColor = this.calcColor(newX, newY, larger);
        if (newX % 1 == 0 || newY % 1 == 0) {
          smallerToLarger.put(currentPoint, larger.getColorAt((int)newX, (int)newY));
        } else {
          smallerToLarger.put(currentPoint, mappedColor);
        }
      }
    }
    return smallerToLarger;
  }

  /**
   * Calculates the Color to use based on the pixels surrounding a likely floating point location.
   * @param x The x-coordinate of the location
   * @param y The y-coordinate of the location
   * @param img The Image
   * @return A Color created by combining the RGB values of pixels surrounding a
   *     floating point location
   */
  private Color calcColor(double x, double y, Image img) {
    Color a = img.getColorAt((int)Math.floor(x), (int)Math.floor(y));
    Color b = img.getColorAt((int)Math.ceil(x), (int)Math.floor(y));
    Color c = img.getColorAt((int)Math.floor(x), (int)Math.ceil(y));
    Color d = img.getColorAt((int)Math.ceil(x), (int)Math.ceil(y));

    double redComp;
    double greenComp;
    double blueComp;

    double mRed = b.getRed() * (x - Math.floor(x)) + a.getRed() * (Math.ceil(x) - x);
    double nRed = d.getRed() * (x - Math.floor(x)) + c.getRed() * (Math.ceil(x) - x);
    redComp = nRed * (y - Math.floor(y)) + mRed * (Math.ceil(y) - y);

    double mGreen = b.getGreen() * (x - Math.floor(x)) + a.getGreen() * (Math.ceil(x) - x);
    double nGreen = d.getGreen() * (x - Math.floor(x)) + c.getGreen() * (Math.ceil(x) - x);
    greenComp = nGreen * (y - Math.floor(y)) + mGreen * (Math.ceil(y) - y);

    double mBlue = b.getBlue() * (x - Math.floor(x)) + a.getBlue() * (Math.ceil(x) - x);
    double nBlue = d.getBlue() * (x - Math.floor(x)) + c.getBlue() * (Math.ceil(x) - x);
    blueComp = nBlue * (y - Math.floor(y)) + mBlue * (Math.ceil(y) - y);

    return new Color((int)redComp, (int)greenComp, (int)blueComp);
  }

}
