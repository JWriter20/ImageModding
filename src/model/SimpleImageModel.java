package model;

import model.imageclasses.Image;
import model.modifications.*;

/**
 * A simple implementation of the ImageModel interface. Provides the ability to modify an
 * image by calling any of the provided methods, as well as setting the image to a new one
 * if needed.
 */
public class SimpleImageModel implements ImageModel {
    protected IModifyImage mod;
    private Image img;

    private void applyToType(IModifyImage m) {
        this.mod = m;
        this.mod.apply(this.img);
    }

    @Override
    public void blurImage() {
        applyToType(new Blurry());
    }

    @Override
    public void sepiaImage() {
        applyToType(new Sepia());
    }

    @Override
    public void grayImage() {
        applyToType(new Greyscale());
    }

    @Override
    public void sharpImage() {
        applyToType(new Sharpen());
    }

    @Override
    public void setImage(Image img) {
        this.img = img;
    }

}
