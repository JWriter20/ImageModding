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

    /**
     * Exports the multi layered image to the folder based on the supplied path
     * and type to save the images as
     * @param pathToFolder The path to the folder
     * @param type The type to save the images as
     */
    void exportMultiImage(String pathToFolder, ImageTypes type);

    /**
     * IDK THIS ONE
     * @param pathToFolder
     * @param type
     */
    void exportFirst(String pathToFolder, ImageTypes type);

    /**
     * Sets the given layer to invisible
     * @param layerName The name of the given layer
     */
    void setLayerToInvisible(String layerName);

    /**
     * Sets the given layer to visible
     * @param layerName The name of the given layer
     */
    void setLayerToVisible(String layerName);


}
