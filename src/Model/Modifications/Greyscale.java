package Model.Modifications;

/**
 * Class used to apply a greyscale affect to an image. The resulting image will only contain
 * shades of grey.
 */
public class Greyscale extends Transform {

  /**
   * Initializes the matrix to the values that, when applied to each pixel, turn that pixel
   * into a shade a grey.
   */
  public Greyscale() {
    this.matrix = new double[][] {
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
    };
  }
}
