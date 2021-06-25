package model.imageclasses;

/**
 * Class used to load in images and save them as an Image.
 */
public class LoadedImage extends AbstractImage {
  /**
   * Creates a new Load object based on the supplied path to an image.
   *
   * @param path The path to the .ppm, jpeg, or png picture.
   */
  public LoadedImage(String path) {
    ImageUtil imgUtil = new ImageUtil();
    if (path.substring(path.length() - 4).equalsIgnoreCase(".ppm")) {
      this.img = imgUtil.getBufferedImageFromPPM(path);
    } else {
      this.img = imgUtil.getBufferedJPEGorBufferedPNG(path);
    }
  }
}
