package model;

import model.imageclasses.Layer;
import model.imageclasses.MultiImage;
import model.imageclasses.MultiLayerImage;
import model.modifications.*;

public class SimpleImageModel implements ImageModel {
    IModifyImage mod;
    MultiImage img = new MultiLayerImage();
    @Override
    public void blurImage() {
        this.mod = new Blurry();
        this.img.applyToFirst(this.mod);
    }

    @Override
    public void sepiaImage() {
        this.mod = new Sepia();
        this.img.applyToFirst(this.mod);
    }

    @Override
    public void grayImage() {
        this.mod = new Greyscale();
        this.img.applyToFirst(this.mod);
    }

    @Override
    public void sharpImage() {
        this.mod = new Sharpen();
        this.img.applyToFirst(this.mod);
    }

    @Override
    public void addLayer(String name) {
        this.img.addLayer(new Layer(name));
    }

    @Override
    public void bringToFront(String name) {
        this.img.bringToFront(name);
    }
}
