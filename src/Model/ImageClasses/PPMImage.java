package src.Model.ImageClasses;

/**
 * Represents a .ppm image. This class implements Image so that different modifications can be
 * made to it.
 */
public class PPMImage extends AbstractImage {
    private String path;
    private ImageUtil imgUtil;

  /**
   * Creates a new PPMImage object based on the supplied path to an image.
   * @param path The path to the .ppm picture.
   */
  public PPMImage(String path) {
    this.path = path;
    this.imgUtil = new ImageUtil();
    this.img = imgUtil.getBufferedImage(this.path);
  }
}
