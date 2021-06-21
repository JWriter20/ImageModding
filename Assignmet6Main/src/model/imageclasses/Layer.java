package model.imageclasses;

import model.modifications.IModifyImage;

/**
 * Represents one layer of a possibly multi-layered image. A layer has an image that can be modified,
 * a name to identify the layer, and the abiltiy to set the layer to invisible.
 */
public class Layer {
    private boolean isVisible;
    private String name;
    private Image img;

    /**
     * Creates a new Layer with the specified name. The image is null, and the visibility
     * is set to true.
     * @param name The name of the layer
     */
    public Layer(String name) {
        this.name = name;
        this.isVisible = true;
    }

    /**
     * Determines if the given name matches this name.
     * @param given The given name
     * @return True if the given name matches this name, false otherwise
     */
    public boolean checkName(String given) {
        return this.name.equals(given);
    }

    /**
     * Applies the given IModifyImage to the image in this layer.
     * @param mod The modification to be applied
     */
    public void modifyImage(IModifyImage mod) {
        mod.apply(this.img);
    }

    /**
     * Gets the name of the layer.
     * @return The name of the layer
     */
    public String getName() {
        return this.name;
    }

    /**
     * Modifies the visibility of the layer to the given value.
     * @param visible The given value to set the layer to
     */
    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }

    /**
     * Determines if the layer is visible.
     * @return Returns true if the layer is visible, false otherwise
     */
    public boolean isVisible() {
        return this.isVisible;
    }

    /**
     * Sets the image in this layer to the given Image.
     * @param img The given Image.
     */
    public void setImage(Image img) {
        this.img = img;
    }

    /**
     * Exports the image in this layer based on the supplied path and type.
     * @param pathToFolder The path to the desired folder
     * @param type The type to save the image as
     */
    public void exportLayer(String pathToFolder, ImageTypes type) {
        this.img.exportImageAs(pathToFolder, type);
    }

    @Override
    public String toString() {
        String s = "";
        s += "name: " + this.name + ", ";
        if (this.img == null) {
            s += "null image";
        } else {
            s += "non-null image";
        }
        if (this.isVisible) {
            s += ", visible layer";
        } else {
            s += ", invisible layer";
        }
        return s;
    }

}