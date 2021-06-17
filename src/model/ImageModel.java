package model;

import model.imageclasses.Image;

public interface ImageModel {
    void blurImage();
    void sepiaImage();
    void grayImage();
    void sharpImage();
    void addLayer(String name);
    void bringToFront(String name);
}
