package model;

import model.imageclasses.Image;
import model.modifications.*;

public class SimpleImageModel implements ImageModel {
    IModifyImage mod;
    Image img;

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
