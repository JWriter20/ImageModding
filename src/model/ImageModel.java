package model;

import model.imageclasses.Image;

/**
 * Interface representing our Image modification model. Provides methods to apply different
 * affects to a single image.
 */
public interface ImageModel {
    /**
     * Apply the blur modification to the image.
     */
    void blurImage();

    /**
     * Applies the sepia modification to the image.
     */
    void sepiaImage();

    /**
     * Turns the image into a greyscale version of itself.
     */
    void grayImage();

    /**
     * Sharpens the image by increasing contrast in some parts of the image.
     */
    void sharpImage();

    /**
     * Sets the image that is being worked on to the given one.
     * @param img The new image
     */
    void setImage(Image img);
}
