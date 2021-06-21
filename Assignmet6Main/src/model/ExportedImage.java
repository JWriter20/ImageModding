package model;

import model.imageclasses.AbstractImage;
import model.imageclasses.ImageTypes;

/**
 * JAKE DO THIS ONE
 */
public class ExportedImage extends AbstractImage {
    /**
     * AND THIS ONE PLZ
     * @param path
     * @param type
     */
    public ExportedImage(String path, ImageTypes type) {
        exportImageAs("res/" + path, type);
    }
}
