package model.imageclasses;

import model.modifications.IModifyImage;

import java.util.List;

public interface MultiImage {

    void addLayer(Layer layer);

    void bringToFront(String name);

    void applyToFirst(IModifyImage mod);

    void setFirstLayerImage(Image img);

    void exportMultiLayer(String pathToFolder, ImageTypes type);

    void exportFist(String pathToFolder, ImageTypes type);


}
