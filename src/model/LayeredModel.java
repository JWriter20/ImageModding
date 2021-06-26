package model;

import model.imageclasses.Image;
import model.imageclasses.ImageTypes;
import java.awt.image.BufferedImage;

/**
 * A model that adds support for a multi-layered image. Retains the ability to modify
 * single pictures, but has methods to add a layer or bring a layer to the front.
 */
public interface LayeredModel extends ImageModel {

  /**
   * Adds a new layer to the image. This layer will be put at the "bottom" of the multi-layered
   * image.
   *
   * @param name The name of the layer to be created and added
   */
  void addLayer(String name);

  /**
   * Brings a specified layer to the "top" of the image where it can be viewed and modified more
   * easily.
   *
   * @param name The name of the layer to be brought to the front of the image
   * @throws IllegalArgumentException If the name provided does not match any layer in the image
   */
  void bringToFront(String name) throws IllegalArgumentException;

  /**
   * Sets the first layer's image to the given image.
   *
   * @param img The given image
   */
  void setFirstLayerImage(Image img);

  /**
   * Gets the image to be shown in the view.
   */
  BufferedImage getViewImage();

  /**
   * Exports the multi layered image to the folder based on the supplied path
   * and type to save the images as.
   *
   * @param pathToFolder The path to the folder
   * @param type         The type to save the images as
   */
  void exportMultiImage(String pathToFolder, ImageTypes type);

  /**
   * Exports the layer to the given folder and saves the image as the given image.
   *
   * @param pathToFolder The path to the folder
   * @param type The type to save the image as
   */
  void exportFirst(String pathToFolder, ImageTypes type);

  /**
   * Sets the given layer to invisible.
   *
   * @param layerName The name of the given layer
   */
  void setLayerToInvisible(String layerName);

  /**
   * Sets the given layer to visible.
   *
   * @param layerName The name of the given layer
   */
  void setLayerToVisible(String layerName);

  /**
   * Applies a mosaic effect to the image in the topmost, visible layer.
   * @param numSeeds The number of seeds the mosaic will have
   */
  void mosaicFirst(int numSeeds);

  /**
   * Downsizes all the images in the layers to the given width and heights.
   * @param newWidth The new width
   * @param newHeight The new height
   */
  void downsizeAll(int newWidth, int newHeight);
}
