package model;

import model.imageclasses.Image;

public interface ImageModel {
    void blurImage();
    void sepiaImage();
    void grayImage();
    void sharpImage();
    void setImage(Image img);
}
