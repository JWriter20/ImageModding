import java.awt.Color;

import Model.ImageClasses.CustomCheckerBoard;
import Model.ImageClasses.Image;
import Model.ImageClasses.PPMImage;
import Model.ImageClasses.RainbowImage;
import Model.Modifications.*;

public class ImageMainMethod {
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


    blur.apply(snail);
    blur.apply(graph);
    graph.exportImage("BlurredGraph");
    snail.exportImage("BlurredSnail");
    snail = new PPMImage("./Pictures/snail.ppm");
    graph = new PPMImage("./Pictures/graph.ppm");
    sharpen.apply(snail);
    sharpen.apply(graph);
    graph.exportImage("SharpGraph");
    snail.exportImage("SharpSnail");
    snail = new PPMImage("./Pictures/snail.ppm");
    graph = new PPMImage("./Pictures/graph.ppm");
    sepia.apply(snail);
    sepia.apply(graph);
    graph.exportImage("SepiaGraph");
    snail.exportImage("SepiaSnail");
    snail = new PPMImage("./Pictures/snail.ppm");
    graph = new PPMImage("./Pictures/graph.ppm");
    greyscale.apply(snail);
    greyscale.apply(graph);
    graph.exportImage("GreyscaleGraph");
    snail.exportImage("GreyscaleSnail");



    //koala.exportImage("SharpKoala");
    //koala.displayImage();
    //sepia.apply(koala);
    /*checkerboard.displayImage();
    checkerboard.displayImage();
    sepia.apply(checkerboard);
    checkerboard.displayImage();
    sepia.apply(rainbow);
    rainbow.displayImage();*/
  }
}
