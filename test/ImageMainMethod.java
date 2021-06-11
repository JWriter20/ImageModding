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
    Image snail = new PPMImage("./Pictures/snail.ppm");
    Image stars = new PPMImage("./Pictures/stars.ppm");
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
    blur.apply(stars);
    stars.exportImage("BlurredStars");
    snail.exportImage("BlurredSnail");
    snail = new PPMImage("./Pictures/snail.ppm");
    stars = new PPMImage("./Pictures/stars.ppm");
    sharpen.apply(snail);
    sharpen.apply(stars);
    stars.exportImage("SharpStars");
    snail.exportImage("SharpSnail");
    snail = new PPMImage("./Pictures/snail.ppm");
    stars = new PPMImage("./Pictures/stars.ppm");
    sepia.apply(snail);
    sepia.apply(stars);
    stars.exportImage("SepiaStars");
    snail.exportImage("SepiaSnail");
    snail = new PPMImage("./Pictures/snail.ppm");
    stars = new PPMImage("./Pictures/stars.ppm");
    greyscale.apply(snail);
    greyscale.apply(stars);
    stars.exportImage("GreyscaleStars");
    snail.exportImage("GreyscaleSnail");



    //koala.exportImage("SharpKoala");
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
