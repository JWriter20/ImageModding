package model.imageclasses;

import java.awt.Color;

/**
 * Interface representing a image that can be processed and modified.
 */
public interface Image {

  /**
   * Determines the width of the image in pixels.
   * @return The width of the image, in pixels.
   */
  int getWidth();

  /**
   * Determines the height of the image in pixels.
   * @return The height of the image, in pixels
   */
  int getHeight();

  /**
   * Creates a new Color object based on the red green and blue values of the pixel at the
   * specified coordinates. The origin is at the top left of the image.
   * @param x The x-coordinate of the pixel
   * @param y The y-coordinate of the pixel
   * @return A new Color object with the same red green and blue values of the specified pixel
   */
  Color getColorAt(int x, int y);

  /**
   * Sets the color of the pixel at the position (x,y) to the given color. The origin is at the top
   * left of the image.
   * @param x The x-coordinate of the pixel
   * @param y The y-coordinate of the pixel
   * @param color The Color that the pixel will be changed to
   */
  void setColorAt(int x, int y, Color color);

  /**
   * Exports a file containing the image to the out folder. If a file with the same imageName
   * already exists the old file will be overridden by the new one.
   */
  void exportImage(String imageName);
}
