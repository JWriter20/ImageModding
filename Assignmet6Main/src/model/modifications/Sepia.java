package model.modifications;

/**
 * Class used to apply a reddish brown tone, known as sepia, to an image.
 */
public class Sepia extends Transform {

  /**
   * Initializes the matrix to values that will change each pixel to ones in the sepia tone.
   */
  public Sepia() {
    this.matrix = new double[][] {
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.543, 0.131},
    };
  }


}
