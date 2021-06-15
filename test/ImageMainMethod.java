import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import model.imageclasses.CustomCheckerBoard;
import model.imageclasses.Image;
import model.imageclasses.PPMImage;
import model.imageclasses.RainbowImage;
import model.modifications.Sharpen;
import model.modifications.Greyscale;
import model.modifications.Sepia;
import model.modifications.Blurry;
import model.modifications.IModifyImage;
import javax.imageio.ImageIO;


/**
 * The class that creates the main method.
 */
public class ImageMainMethod {
  /**
   * Main method used in both testing and to create the photos in the res folder.
   *
   * @param args Standard main argument.
   */
  public static void main(String[] args) {

    Image koala = new PPMImage("./Pictures/Koala.ppm");
    Image snail = new PPMImage("./Pictures/snail.ppm");
    Image graph = new PPMImage("./Pictures/graph.ppm");
    Image checkerboard = new CustomCheckerBoard(3, 3, 3,
        new Color(255, 10, 10), new Color(89, 0, 26));
    Image checkerboard2 = new CustomCheckerBoard(25, 20, 20,
        new Color(26, 255, 9), new Color(255,25,0));
    Image rainbow = new RainbowImage();
    IModifyImage greyscale = new Greyscale();
    IModifyImage sepia = new Sepia();
    IModifyImage sharpen = new Sharpen();
    IModifyImage blur = new Blurry();

    /*

    blur.apply(snail);
    blur.apply(graph);
    graph.exportImageAsPPM("BlurredGraph");
    snail.exportImageAsPPM("BlurredSnail");
    snail = new PPMImage("./Pictures/snail.ppm");
    graph = new PPMImage("./Pictures/graph.ppm");
    sharpen.apply(snail);
    sharpen.apply(graph);
    graph.exportImageAsPPM("SharpGraph");
    snail.exportImageAsPPM("SharpSnail");
    snail = new PPMImage("./Pictures/snail.ppm");
    graph = new PPMImage("./Pictures/graph.ppm");
    sepia.apply(snail);
    sepia.apply(graph);
    graph.exportImageAsPPM("SepiaGraph");
    snail.exportImageAsPPM("SepiaSnail");
    snail = new PPMImage("./Pictures/snail.ppm");
    graph = new PPMImage("./Pictures/graph.ppm");
    greyscale.apply(snail);
    greyscale.apply(graph);
    graph.exportImageAsPPM("GreyscaleGraph");
    snail.exportImageAsPPM("GreyscaleSnail");

    */
  }
}
