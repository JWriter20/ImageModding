package model;

import model.imageclasses.Image;
import model.imageclasses.ImageTypes;
import model.imageclasses.Layer;

/**
 * A model that adds support for a multi-layered image. Retains the ability to modify
 * single pictures, but has methods to add a layer or bring a layer to the front.
 */
public interface LayeredModel extends ImageModel {

    /**
     * Adds a new layer to the image. This layer will be put at the "bottom" of the multi-layered
     * image.
     * @param name The name of the layer to be created and added
     */
    void addLayer(String name);

    /**
     * Brings a specified layer to the "top" of the image where it can be viewed and modified more
     * easily.
     * @param name The name of the layer to be brought to the front of the image
     * @throws IllegalArgumentException If the name provided does not match any layer in the image
     */
    void bringToFront(String name) throws IllegalArgumentException;

    /**
     * Sets the first layer's image to the given image.
     * @param img The given image
     */
    void setFirstLayerImage(Image img);

    void exportMultiImage(String pathToFolder, ImageTypes type);

    void exportFirst(String pathToFolder, ImageTypes type);


}
