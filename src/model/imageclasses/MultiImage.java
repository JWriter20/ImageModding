package model.imageclasses;

import model.modifications.IModifyImage;

public interface MultiImage {

    void addLayer(Layer layer);

    void bringToFront(String name);

    void applyToFirst(IModifyImage mod);

    void exportMultiLayer(String pathToFolder, ImageTypes type);

    void loadMultiLayer();

}
