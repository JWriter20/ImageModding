package src.Model.ImageClasses;


import java.awt.Color;
import src.Model.Modifications.Blurry;
import src.Model.Modifications.Greyscale;
import src.Model.Modifications.IModifyImage;
import src.Model.Modifications.Sepia;
import src.Model.Modifications.Sharpen;

public class ImageMainMethod {
  public static void main(String[] args) {
    Image koala = new PPMImage("/Users/Gabe/Desktop/OOD/Assignment5/Pictures/Koala.ppm");

    Image checkerboard = new CustomCheckerBoard(100, 5, 5,
        new Color(255, 10, 10), new Color(89, 0, 26));

    Image checkerboard2 = new CustomCheckerBoard(25, 20, 20,
        new Color(26, 255, 9), new Color(255,25,0));
    Image rainbow = new RainbowImage();
    IModifyImage greyscale = new Greyscale();
    IModifyImage sepia = new Sepia();
    IModifyImage blurry = new Blurry();
    IModifyImage sharpen = new Sharpen();

    koala.displayImage();
    blurry.apply(koala);
    koala.displayImage();
  }
}
