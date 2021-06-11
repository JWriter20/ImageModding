package Model.Modifications;

/**
 * Represents a change to an image that makes it more sharp. It increases the contrast in the image
 * to achieve this.
 */
public class Sharpen extends Filter{

  /**
   * Creates a new Sharpen object. Initializes the kernel to values that will make every pixel
   * appear more sharp compared to the pixels around it.
   */
  public Sharpen() {
    this.kernel = new double[][]{
        {-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125},
    };
  }

}
