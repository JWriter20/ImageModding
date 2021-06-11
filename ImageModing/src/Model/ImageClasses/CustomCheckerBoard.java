package Model.ImageClasses;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Creates a checkerboard like image based on the input provided.
 */
public class CustomCheckerBoard extends AbstractImage {
  private int squareLength;
  private int width;
  private int height;
  private Color c1;
  private Color c2;
  /**
   * Creates a CustomCheckBoard object. Initializes this.squareLength, this.width, and this.height
   * to the values provided in the constructor. This.width and this.height are dimensions in
   * squares, not in pixels. Initializes this.img to a new BufferedImage() object with the correct
   * width and height. Then colors in the pixes of this.img to form a checkerboard object.
   * @param squareLength
   * @param width
   * @param height
   */
  public CustomCheckerBoard(int squareLength, int width, int height, Color c1, Color c2) {
    this.squareLength = squareLength;
    this.width = width;
    this.height = height;
    this.img = new BufferedImage(this.squareLength * this.width,
        this.squareLength * this.height, BufferedImage.TYPE_INT_RGB);
    this.c1 = c1;
    this.c2 = c2;
    this.makeBoard();
  }

  /**
   * Sets the color of the pixels in this.img to values that form a checkerboard. There are
   * squares, each of length this.squareLength, of alternating colors. The number of squares
   * are determined by this.width and this.height.
   */
  private void makeBoard() {
    for (int x = 0; x < this.width; x++) {
      for (int y = 0; y < this.height; y++) {
        if ((x + y) % 2 == 0) {
          this.makeSquare(this.squareLength * x, this.squareLength * y, this.c1);
        } else {
          this.makeSquare(this.squareLength * x, this.squareLength * y, this.c2);
        }
      }
    }
  }

  /**
   * Helper method for makeBoard(). This method changes the RGB values of pixels in a square,
   * starting at (startXPos, startYPos), to the given Color. The size of the square is determined
   * by the squareLength variable initialized in the constructor.
   * @param startXPos The starting X position
   * @param startYPos The starting Y position
   * @param color The color to set the pixels to
   */
  private void makeSquare(int startXPos, int startYPos, Color color) {
    for (int x = startXPos; x < startXPos + this.squareLength; x++) {
      for (int y = startYPos; y < startYPos + this.squareLength; y++) {
        this.img.setRGB(x, y, color.getRGB());
      }
    }
  }
}
