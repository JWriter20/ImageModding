package Model.Modifications;

import java.awt.Color;
import Model.ImageClasses.Image;

/**
 * Superclass for any classes that intend on performing a color transformation on a picture.
 * The matrix is the 3x3 array of values used to determine how much to change the color of each
 * pixel.
 */
public abstract class Transform implements IModifyImage {
  protected double[][] matrix;

  @Override
  public void apply(Image img) {
    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = 0; y < img.getHeight(); y++) {
        Color currentColor = img.getColorAt(x, y);
        img.setColorAt(x, y, this.getModifiedColor(currentColor));
      }
    }
  }

  private Color getModifiedColor(Color currentColor) {
    int currentRed = currentColor.getRed();
    int currentGreen = currentColor.getGreen();
    int currentBlue = currentColor.getBlue();

    int r = (int)Math.round(matrix[0][0] * currentRed +
        matrix[0][1] * currentGreen +
        matrix[0][2] * currentBlue);
    r = clampColor(r);

    int g = (int)Math.round(matrix[1][0] * currentRed +
        matrix[1][1] * currentGreen +
        matrix[1][2] * currentBlue);
    g = clampColor(g);

    int b = (int)Math.round(matrix[2][0] * currentRed +
        matrix[2][1] * currentGreen +
        matrix[2][2] * currentBlue);
    b = clampColor(b);

    return new Color(r, g, b);
  }

  private int clampColor(int input) {
    if (input > 255) {
      return 255;
    } else if(input < 0) {
      return 0;
    }
    return input;
  }
}
