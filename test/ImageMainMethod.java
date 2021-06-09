package test;


import java.awt.Color;

import java.io.*;
import src.Model.ImageClasses.CustomCheckerBoard;
import src.Model.ImageClasses.Image;
import src.Model.ImageClasses.PPMImage;
import src.Model.ImageClasses.RainbowImage;
import src.Model.Modifications.*;

public class ImageMainMethod {
  public static void main(String[] args) {
    Image koala = new PPMImage("./Pictures/Koala.ppm");
    Image checkerboard = new CustomCheckerBoard(100, 5, 5,
        new Color(255, 10, 10), new Color(89, 0, 26));
    Image checkerboard2 = new CustomCheckerBoard(25, 20, 20,
        new Color(26, 255, 9), new Color(255,25,0));
    Image rainbow = new RainbowImage();
    IModifyImage greyscale = new Greyscale();
    IModifyImage sepia = new Sepia();
    IModifyImage sharpen = new Sharpen();
    IModifyImage blur = new Blurry();


    koala.displayImage();
    sharpen.apply(koala);
    koala.displayImage();
    koala.exportImage("SharpKoala");
    //koala.displayImage();
    /*sepia.apply(koala);
    koala.displayImage();
    checkerboard.displayImage();
    checkerboard.displayImage();
    sepia.apply(checkerboard);
    checkerboard.displayImage();
    sepia.apply(rainbow);
    rainbow.displayImage();*/
  }
}
