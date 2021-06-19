package model;


public interface LayeredModel extends ImageModel {

    void addLayer(String name);
    void bringToFront(String name);
}
