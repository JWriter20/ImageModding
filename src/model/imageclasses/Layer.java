package model.imageclasses;

import model.modifications.IModifyImage;

public class Layer {
    private boolean isVisible;
    private String name;
    private Image img;

    public Layer(Image img, String name) {
        this.img = img;
        this.name = name;
        this.isVisible = true;
    }

    public Layer(Image img, String name, boolean isVisible) {
        this.img = img;
        this.name = name;
        this.isVisible = isVisible;
    }

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

    public void modifyImage(IModifyImage mod) {
        mod.apply(this.img);
    }

    public String getName() {
        return this.name;
    }

    public void makeVisible() {
        this.isVisible = true;
    }

    public void makeInvisible() {
        this.isVisible = false;
    }

    public void setImage(Image img) {
        this.img = img;
    }
    public void exportLayer(String pathToFolder, ImageTypes type) {
        this.img.exportImageAs(pathToFolder, type);
    }

    @Override
    public String toString() {
        String s = "";
        s += "name: " + this.name + ",";
        if (this.img == null) {
            s += "null image";
        } else {
            s += "non-null image";
        }
        return s;
    }

}
