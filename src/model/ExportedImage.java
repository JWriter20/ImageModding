package model;

import model.imageclasses.AbstractImage;
import model.imageclasses.ImageTypes;

/**
 * An image class that is used to export images.
 */
public class ExportedImage extends AbstractImage {
  /**
   * Exports this image to the given path and saves the image as the given type.
   *
   * @param path The given path
   * @param type The type to save the image as
   */
  public ExportedImage(String path, ImageTypes type) {
    exportImageAs("res/" + path, type);
  }
}
