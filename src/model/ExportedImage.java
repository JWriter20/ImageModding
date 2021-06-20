package model;

import model.imageclasses.AbstractImage;
import model.imageclasses.ImageTypes;

public class ExportedImage extends AbstractImage {
    public ExportedImage(String path, ImageTypes type) {
        exportImageAs("res/" + path, type);
    }
}
