package model;

import model.imageclasses.Image;
import model.imageclasses.MultiImage;
import model.imageclasses.MultiLayerImage;
import model.imageclasses.ImageTypes;
import model.imageclasses.Layer;
import model.modifications.Blurry;
import model.modifications.Greyscale;
import model.modifications.Sepia;
import model.modifications.Sharpen;

import java.awt.image.BufferedImage;

/**
 * Class used to represent a layered image. Provides functionality to modify the image
 * in the top layer, create a new layer, bring a specified layer to the front
 * of the image, and set the image in the first layer.
 */
public class LayeredImageModel extends SimpleImageModel implements LayeredModel {
  private MultiImage img = new MultiLayerImage();

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
  public BufferedImage getViewImage() {
    return img.getFirstVisImg();
  }

  @Override
  public void addLayer(String name) {
    this.img.addLayer(new Layer(name));
  }

  @Override
  public void bringToFront(String name) {
    this.img.bringToFront(name);
  }

  @Override
  public void setFirstLayerImage(Image image) {
    this.img.setFirstLayerImage(image);
  }

  @Override
  public void exportFirst(String pathToFolder, ImageTypes exportType) {
    img.exportFist(pathToFolder, exportType);
  }

  @Override
  public void setLayerToInvisible(String layerName) {
    this.img.setGivenToInvisible(layerName);
  }

  @Override
  public void setLayerToVisible(String layerName) {
    this.img.setGivenToVisible(layerName);
  }

  @Override
  public void exportMultiImage(String pathToFolder, ImageTypes type) {
    img.exportMultiLayer(pathToFolder, type);
  }

  @Override
  public String toString() {
    return this.img.toString();
  }

}
