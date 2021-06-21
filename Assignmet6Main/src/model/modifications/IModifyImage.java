package model.modifications;

import model.imageclasses.Image;

/**
 * Interface that will be implemented in order to modify images. Any class that is used to
 * modify images should implement this interface and its apply method.
 */
public interface IModifyImage {

  /**
   * Applies the modification to the given Image.
   * @param img The Image that will be modified
   */
  void apply(Image img);
}
