package model.imageclasses;

import model.modifications.IModifyImage;
import model.modifications.Mosaic;

import java.awt.image.BufferedImage;

/**
 * Interface representing a multi layered image. A multi layered image is a collection
 * of Layer objects, with the topmost visible layer being the one that the user would see and
 * interact with.
 */
public interface MultiImage {
  /**
   * Adds a layer to the MultiImage. This layer by default goes behind all the other layers
   * so it is not visible unless the user brings it to the front or sets the other
   * layers to invisible.
   *
   * @param layer The layer to be added
   */
  void addLayer(Layer layer);

  /**
   * Brings a specified layer to the front of the MultiImage.
   *
   * @param name The name of the layer to be brought to the front
   * @throws IllegalArgumentException If the given name is not the name of a layer in the list
   */
  void bringToFront(String name) throws IllegalArgumentException;

  /**
   * Applies the given modification to the topmost, visible layer. If the top layer is not visible,
   * the modification will be applied to the next visible layer.
   *
   * @param mod The modification to be applied
   */
  void applyToFirst(IModifyImage mod);

  /**
   * Applies the given Mosaic effect to the topmost, visible layer.
   * @param m The Mosaic to apply
   */
  void applyMosaicToFirst(Mosaic m);

  /**
   * Sets the image in the topmost visible layer to the given Image.
   *
   * @param img The given Image.
   */
  void setFirstLayerImage(Image img);

  /**
   * Exports this multi layered image to a folder based on the given path, and saves the images
   * as the given type.
   *
   * @param pathToFolder The path to the desired folder
   * @param type         The type to save the images as
   */
  void exportMultiLayer(String pathToFolder, ImageTypes type);

  /**
   * Exports the MultiImage to the given folder and saves it as the given type.
   *
   * @param pathToFolder The path to the folder
   * @param type The type to save it as
   */
  void exportFist(String pathToFolder, ImageTypes type);

  /**
   * Sets the layer with the given name to invisible.
   *
   * @param layerName The name of the layer to be set to invisible
   * @throws IllegalArgumentException If the given name is not the name of a layer
   */
  void setGivenToInvisible(String layerName) throws IllegalArgumentException;

  /**
   * Sets the layer with the given name to visible.
   *
   * @param layerName The name of the layer to be set to visible
   * @throws IllegalArgumentException If the given name is not the name of a layer
   */
  void setGivenToVisible(String layerName) throws IllegalArgumentException;

  /**
   * Gets the bufferedImage so it can be used in view.
   */
  BufferedImage getFirstVisImg();

  /**
   * Downsizes all the images to match the new width and height.
   * @param newWidth The new width of the images
   * @param newHeight The new height of the images
   */
  void downsizeAll(int newWidth, int newHeight);
}
