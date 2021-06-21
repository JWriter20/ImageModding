import model.imageclasses.*;
import org.junit.Test;
import model.modifications.Blurry;
import model.modifications.Sepia;
import model.modifications.Greyscale;
import model.modifications.Sharpen;
import model.modifications.IModifyImage;

import java.awt.Color;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


/**
 * The main testing class that contains all of the individual tests.
 */
public class IModifyImageTests {
  //Image koala = new PPMImage("./Pictures/Koala.ppm");
  Image jpeg = new LoadedImage("./Pictures/meme.jpeg");
  Image png = new LoadedImage("./Pictures/face.png");
  Image checkerboard = new CustomCheckerBoard(3, 3, 3,
      new Color(255, 10, 10), new Color(89, 0, 26));
  Image checkerboard2 = new CustomCheckerBoard(25, 20, 20,
      new Color(26, 255, 9), new Color(255,25,0));
  Image rainbow = new RainbowImage();
  Image nullImage;
  IModifyImage greyscale = new Greyscale();
  IModifyImage sepia = new Sepia();
  IModifyImage sharpen = new Sharpen();
  IModifyImage blur = new Blurry();

  private void initData() {
    //koala = new PPMImage("./Pictures/Koala.ppm");
    checkerboard = new CustomCheckerBoard(3, 3, 3,
        new Color(255, 10, 10), new Color(89, 0, 26));
    checkerboard2 = new CustomCheckerBoard(25, 20, 20,
        new Color(26, 255, 9), new Color(255,25,0));
    rainbow = new RainbowImage();
    greyscale = new Greyscale();
    sepia = new Sepia();
    sharpen = new Sharpen();
    blur = new Blurry();
  }


  private String getColors(Image image, IModifyImage mod, boolean apply) {
    try {
      Appendable moddedRes = new StringBuilder();
      if (apply) {
        mod.apply(image);
      }
      for (int i = 0; i < checkerboard.getWidth(); i++) {
        for (int j = 0; j < checkerboard.getHeight(); j++) {
          moddedRes.append(checkerboard.getColorAt(i, j).toString()).append("\n");
        }
      }

      return moddedRes.toString();
    } catch (IOException e) {
      throw new IllegalArgumentException("bad appendable");
    }
  }



  //Use hash with correct answer
  @Test
  public void testBlurry() {
    initData();
    String correctBlur = "java.awt.Color[r=140,g=4,b=4]\n" +
        "java.awt.Color[r=172,g=4,b=4]\n" +
        "java.awt.Color[r=146,g=3,b=7]\n" +
        "java.awt.Color[r=82,g=0,b=13]\n" +
        "java.awt.Color[r=64,g=0,b=15]\n" +
        "java.awt.Color[r=92,g=1,b=12]\n" +
        "java.awt.Color[r=156,g=4,b=6]\n" +
        "java.awt.Color[r=174,g=4,b=4]\n" +
        "java.awt.Color[r=130,g=3,b=3]\n" +
        "java.awt.Color[r=167,g=4,b=4]\n" +
        "java.awt.Color[r=213,g=4,b=4]\n" +
        "java.awt.Color[r=184,g=3,b=7]\n" +
        "java.awt.Color[r=110,g=0,b=14]\n" +
        "java.awt.Color[r=85,g=0,b=16]\n" +
        "java.awt.Color[r=118,g=1,b=14]\n" +
        "java.awt.Color[r=193,g=4,b=6]\n" +
        "java.awt.Color[r=217,g=4,b=4]\n" +
        "java.awt.Color[r=162,g=3,b=3]\n" +
        "java.awt.Color[r=143,g=3,b=7]\n" +
        "java.awt.Color[r=179,g=3,b=8]\n" +
        "java.awt.Color[r=169,g=2,b=10]\n" +
        "java.awt.Color[r=134,g=1,b=14]\n" +
        "java.awt.Color[r=133,g=1,b=13]\n" +
        "java.awt.Color[r=151,g=2,b=12]\n" +
        "java.awt.Color[r=187,g=3,b=8]\n" +
        "java.awt.Color[r=187,g=3,b=9]\n" +
        "java.awt.Color[r=135,g=2,b=7]\n" +
        "java.awt.Color[r=77,g=0,b=13]\n" +
        "java.awt.Color[r=103,g=0,b=16]\n" +
        "java.awt.Color[r=136,g=1,b=14]\n" +
        "java.awt.Color[r=196,g=4,b=7]\n" +
        "java.awt.Color[r=212,g=4,b=5]\n" +
        "java.awt.Color[r=188,g=3,b=8]\n" +
        "java.awt.Color[r=130,g=0,b=15]\n" +
        "java.awt.Color[r=112,g=0,b=16]\n" +
        "java.awt.Color[r=79,g=0,b=12]\n" +
        "java.awt.Color[r=64,g=0,b=15]\n" +
        "java.awt.Color[r=86,g=0,b=17]\n" +
        "java.awt.Color[r=129,g=1,b=15]\n" +
        "java.awt.Color[r=206,g=4,b=6]\n" +
        "java.awt.Color[r=229,g=4,b=4]\n" +
        "java.awt.Color[r=197,g=3,b=8]\n" +
        "java.awt.Color[r=122,g=0,b=16]\n" +
        "java.awt.Color[r=95,g=0,b=18]\n" +
        "java.awt.Color[r=65,g=0,b=14]\n" +
        "java.awt.Color[r=92,g=1,b=12]\n" +
        "java.awt.Color[r=127,g=1,b=13]\n" +
        "java.awt.Color[r=152,g=2,b=12]\n" +
        "java.awt.Color[r=191,g=3,b=8]\n" +
        "java.awt.Color[r=190,g=3,b=9]\n" +
        "java.awt.Color[r=173,g=2,b=12]\n" +
        "java.awt.Color[r=137,g=1,b=15]\n" +
        "java.awt.Color[r=133,g=1,b=14]\n" +
        "java.awt.Color[r=97,g=1,b=10]\n" +
        "java.awt.Color[r=158,g=4,b=5]\n" +
        "java.awt.Color[r=203,g=4,b=5]\n" +
        "java.awt.Color[r=187,g=3,b=8]\n" +
        "java.awt.Color[r=130,g=0,b=15]\n" +
        "java.awt.Color[r=114,g=0,b=16]\n" +
        "java.awt.Color[r=138,g=1,b=14]\n" +
        "java.awt.Color[r=197,g=4,b=7]\n" +
        "java.awt.Color[r=209,g=4,b=5]\n" +
        "java.awt.Color[r=155,g=3,b=4]\n" +
        "java.awt.Color[r=171,g=4,b=4]\n" +
        "java.awt.Color[r=221,g=4,b=4]\n" +
        "java.awt.Color[r=195,g=3,b=8]\n" +
        "java.awt.Color[r=122,g=0,b=16]\n" +
        "java.awt.Color[r=99,g=0,b=18]\n" +
        "java.awt.Color[r=132,g=1,b=15]\n" +
        "java.awt.Color[r=206,g=4,b=6]\n" +
        "java.awt.Color[r=227,g=4,b=4]\n" +
        "java.awt.Color[r=169,g=3,b=3]\n" +
        "java.awt.Color[r=128,g=3,b=3]\n" +
        "java.awt.Color[r=159,g=3,b=3]\n" +
        "java.awt.Color[r=137,g=2,b=7]\n" +
        "java.awt.Color[r=83,g=0,b=12]\n" +
        "java.awt.Color[r=70,g=0,b=13]\n" +
        "java.awt.Color[r=95,g=1,b=10]\n" +
        "java.awt.Color[r=152,g=3,b=4]\n" +
        "java.awt.Color[r=163,g=3,b=3]\n" +
        "java.awt.Color[r=118,g=2,b=2]\n";

    assertEquals(correctBlur, getColors(checkerboard, blur, true));
  }

  @Test
  public void testGreyScale() {
    initData();
    String correctBlur = "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=21,g=21,b=21]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n" +
        "java.awt.Color[r=62,g=62,b=62]\n";

    assertEquals(correctBlur, getColors(checkerboard, greyscale, true));
  }

  @Test
  public void testSepia() {
    initData();
    String correctBlur = "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=40,g=35,b=28]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n" +
        "java.awt.Color[r=110,g=98,b=76]\n";

    assertEquals(correctBlur, getColors(checkerboard, sepia, true));
  }

  @Test
  public void testSharpen() {
    initData();
    String correctBlur = "java.awt.Color[r=255,g=6,b=6]\n" +
        "java.awt.Color[r=255,g=13,b=1]\n" +
        "java.awt.Color[r=255,g=9,b=2]\n" +
        "java.awt.Color[r=94,g=0,b=20]\n" +
        "java.awt.Color[r=0,g=0,b=33]\n" +
        "java.awt.Color[r=72,g=0,b=24]\n" +
        "java.awt.Color[r=255,g=6,b=2]\n" +
        "java.awt.Color[r=255,g=13,b=2]\n" +
        "java.awt.Color[r=255,g=9,b=6]\n" +
        "java.awt.Color[r=255,g=14,b=0]\n" +
        "java.awt.Color[r=255,g=25,b=0]\n" +
        "java.awt.Color[r=255,g=17,b=0]\n" +
        "java.awt.Color[r=119,g=0,b=32]\n" +
        "java.awt.Color[r=0,g=0,b=56]\n" +
        "java.awt.Color[r=86,g=0,b=41]\n" +
        "java.awt.Color[r=255,g=10,b=2]\n" +
        "java.awt.Color[r=255,g=24,b=0]\n" +
        "java.awt.Color[r=255,g=21,b=0]\n" +
        "java.awt.Color[r=255,g=14,b=6]\n" +
        "java.awt.Color[r=255,g=22,b=6]\n" +
        "java.awt.Color[r=255,g=16,b=1]\n" +
        "java.awt.Color[r=86,g=0,b=24]\n" +
        "java.awt.Color[r=25,g=0,b=41]\n" +
        "java.awt.Color[r=60,g=0,b=34]\n" +
        "java.awt.Color[r=255,g=9,b=3]\n" +
        "java.awt.Color[r=255,g=21,b=4]\n" +
        "java.awt.Color[r=255,g=21,b=7]\n" +
        "java.awt.Color[r=97,g=1,b=25]\n" +
        "java.awt.Color[r=141,g=1,b=35]\n" +
        "java.awt.Color[r=113,g=0,b=17]\n" +
        "java.awt.Color[r=251,g=3,b=0]\n" +
        "java.awt.Color[r=255,g=7,b=0]\n" +
        "java.awt.Color[r=241,g=5,b=2]\n" +
        "java.awt.Color[r=95,g=0,b=15]\n" +
        "java.awt.Color[r=149,g=2,b=33]\n" +
        "java.awt.Color[r=114,g=4,b=30]\n" +
        "java.awt.Color[r=0,g=0,b=41]\n" +
        "java.awt.Color[r=0,g=0,b=61]\n" +
        "java.awt.Color[r=41,g=0,b=32]\n" +
        "java.awt.Color[r=255,g=7,b=0]\n" +
        "java.awt.Color[r=255,g=16,b=0]\n" +
        "java.awt.Color[r=255,g=12,b=0]\n" +
        "java.awt.Color[r=49,g=0,b=22]\n" +
        "java.awt.Color[r=0,g=0,b=58]\n" +
        "java.awt.Color[r=0,g=0,b=53]\n" +
        "java.awt.Color[r=49,g=0,b=36]\n" +
        "java.awt.Color[r=68,g=0,b=55]\n" +
        "java.awt.Color[r=54,g=0,b=33]\n" +
        "java.awt.Color[r=239,g=9,b=0]\n" +
        "java.awt.Color[r=255,g=14,b=0]\n" +
        "java.awt.Color[r=251,g=14,b=0]\n" +
        "java.awt.Color[r=57,g=0,b=23]\n" +
        "java.awt.Color[r=74,g=0,b=54]\n" +
        "java.awt.Color[r=50,g=0,b=49]\n" +
        "java.awt.Color[r=255,g=6,b=7]\n" +
        "java.awt.Color[r=255,g=12,b=15]\n" +
        "java.awt.Color[r=255,g=8,b=6]\n" +
        "java.awt.Color[r=94,g=0,b=13]\n" +
        "java.awt.Color[r=39,g=0,b=20]\n" +
        "java.awt.Color[r=84,g=0,b=13]\n" +
        "java.awt.Color[r=255,g=6,b=0]\n" +
        "java.awt.Color[r=255,g=12,b=18]\n" +
        "java.awt.Color[r=255,g=9,b=19]\n" +
        "java.awt.Color[r=255,g=15,b=4]\n" +
        "java.awt.Color[r=255,g=26,b=4]\n" +
        "java.awt.Color[r=255,g=19,b=11]\n" +
        "java.awt.Color[r=147,g=1,b=37]\n" +
        "java.awt.Color[r=0,g=0,b=57]\n" +
        "java.awt.Color[r=111,g=0,b=42]\n" +
        "java.awt.Color[r=255,g=13,b=9]\n" +
        "java.awt.Color[r=255,g=26,b=7]\n" +
        "java.awt.Color[r=255,g=21,b=9]\n" +
        "java.awt.Color[r=255,g=14,b=10]\n" +
        "java.awt.Color[r=255,g=25,b=8]\n" +
        "java.awt.Color[r=255,g=21,b=14]\n" +
        "java.awt.Color[r=101,g=2,b=45]\n" +
        "java.awt.Color[r=0,g=0,b=65]\n" +
        "java.awt.Color[r=64,g=0,b=51]\n" +
        "java.awt.Color[r=255,g=14,b=14]\n" +
        "java.awt.Color[r=255,g=27,b=8]\n" +
        "java.awt.Color[r=255,g=22,b=11]\n";

    assertEquals(correctBlur, getColors(checkerboard, sharpen, true));
  }

  @Test
  public void testCreateCheckerboard() {
    Image checkerboard = new CustomCheckerBoard(3, 2, 2,
            Color.RED, Color.WHITE);
    String correctColors = "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=89,g=0,b=26]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n" +
            "java.awt.Color[r=255,g=10,b=10]\n";
    assertEquals(correctColors, getColors(checkerboard, sharpen, false));
  }

  @Test(expected = NullPointerException.class)
  public void testNullImage() {
    AbstractImage nullImage = null;
    nullImage.getWidth();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPixelOver255() {
    Image over255Pixel = new CustomCheckerBoard(3, 3, 3,
        new Color(265, 10, 10), new Color(89, 0, 26));
    over255Pixel.getHeight();

  }

  @Test(expected = IllegalArgumentException.class)
  public void testPixelUnder0() {
    Image under0Pixel = new CustomCheckerBoard(3, 3, 3,
        new Color(-20, 10, -1), new Color(-2, 0, 26));
    under0Pixel.getHeight();
  }

  /* This test passes if we have the Sharp_Koala photo, however we removed it
  in order to allow our project to submit.

  @Test
  public void exportImage() {
    initData();
    sharpen.apply(koala);
    String exportName = "Sharp_Koala";
    koala.exportImageAsPPM(exportName);
    Image savedKoala = new PPMImage("./res/" + exportName + ".ppm");
    // Make sure all of the values are the same
    for (int i = 0; i < koala.getWidth(); i++) {
      for (int j = 0; j < koala.getHeight(); j++) {
        assertEquals(koala.getColorAt(i, j), savedKoala.getColorAt(i, j));
      }
    }
  }

  */

  @Test(expected = IllegalArgumentException.class)
  public void importBadPPM() {
    String importName = "non-existent-name";
    Image garbage = new PPMImage("./out/production/Assignment5/res/" + importName + ".ppm");
    garbage.getHeight();

  }

  @Test(expected = IllegalStateException.class)
  public void badExport() {
    String exportName = "fakeFolder/badImage";
    rainbow.exportImageAsPPM(exportName);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadSetCoord() {
    rainbow.setColorAt(-2, -4, new Color(30, 40, 59));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadSetColor() {
    rainbow.setColorAt(0, 0, new Color(266, -4, 59));
  }

  @Test
  public void testGetColorAt() {
    Color c = rainbow.getColorAt(5,5);
    assertEquals(c, new Color(255, 0, 10));
  }

  @Test(expected = NullPointerException.class)
  public void testBadGetColorAtNullImage() {
    nullImage.getColorAt(5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadGetColorAtIndiciesOutOfBounds() {
    rainbow.getColorAt(-1, -12);
  }

  @Test
  public void testGetWidth() {
    assertEquals(500, rainbow.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(300, rainbow.getHeight());
  }

}
