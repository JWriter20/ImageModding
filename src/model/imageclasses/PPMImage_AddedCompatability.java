package model.imageclasses;

public class PPMImage_AddedCompatability extends AbstractImage {
    /**
     * Creates a new PPMImage object based on the supplied path to an image.
     *
     * @param path The path to the .ppm, jpeg, or png picture.
     */
    public PPMImage_AddedCompatability(String path) {
        ImageUtil imgUtil = new ImageUtil();
        if (path.substring(path.length() - 4).equalsIgnoreCase(".ppm")) {
            this.img = imgUtil.getBufferedImageFromPPM(path);
        } else {
            this.img = imgUtil.getBufferedJPEGorBufferedPNG(path);
        }
    }
}
