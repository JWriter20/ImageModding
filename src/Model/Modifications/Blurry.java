package Model.Modifications;

/**
 * Represents a change to an image that makes it appear more blurry.
 */
public class Blurry extends Filter {

  /**
   * Initializes the kernel to values that, when applied to every pixel, make the image
   * appear more blurry.
   */
  public Blurry() {
    this.kernel = new double[][]{
        {0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}
    };
  }
}
