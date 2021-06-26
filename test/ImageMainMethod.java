import controller.AdvancedController;
import controller.AdvancedGUIController;
import controller.ImageModController;
import controller.LayeredImageModController;
import model.LayeredImageModel;
import model.LayeredModel;
import view.AdvancedImageView;
import view.Display;
import view.ImageView;

import java.io.InputStreamReader;
import java.io.StringReader;


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
    LayeredModel model = new LayeredImageModel();

    if (args[0].equalsIgnoreCase("-script")) {
      Readable rd = new StringReader("c\n" + args[1]);
      ImageView view = new Display(System.out);
      ImageModController controller = new LayeredImageModController(model, view, rd);
      controller.start();

    } else if (args[0].equals("-text")) {
      ImageView view = new Display(System.out);
      ImageModController controller = new LayeredImageModController(model, view,
              new InputStreamReader(System.in));
      controller.start();
    } else if (args[0].equals("-interactive")) {
      AdvancedImageView.setDefaultLookAndFeelDecorated(false);
      AdvancedController controller = new AdvancedGUIController(model);
      controller.start();
    } else {
      throw new IllegalArgumentException("Invalid command line arguments");
    }
  }
}


    /*
    LayeredModel model = new LayeredImageModel();
    ImageView view = new Display(System.out);
    //Tests for the controller
    ImageModController controller = new LayeredImageModController(model, view,
            new InputStreamReader(System.in));
    String input =
            "m\n" +
            "create layer first\n" +
            "current first\n" +
            "load face.png\n" +
            "blur\n" +
            "save face-blur.jpeg\n" +
            "quit\n";

    ImageModController readableController =
            new LayeredImageModController(model, view, new StringReader(input));
    controller.go();

    //readableController.go();

    //String input2 =
            //create layer first
    //create layer second
    //    current first
    //    load manhattan.png #load it in layer first
    //    blur
    //    save manhattan-blur.ppm
    //    current second #make another layer, this is topmost and visible
    //    load manhattan.ppm #load it in layer second
    //    sepia
    //    save manhattan-sepia.jpg #save second layer because it is topmost visible
    //    invisible second #make second layer (topmost) invisible
    //    save manhattan-blur-2.jpg #save first layer because it is the topmost visible"


    //controller.go();


    // Image koala = new PPMImage("./Pictures/Koala.ppm");
    // Image snail = new PPMImage("./Pictures/snail.ppm");
    // Image graph = new PPMImage("./Pictures/graph.ppm");
    /*Image checkerboard = new CustomCheckerBoard(3, 3, 3,
        new Color(255, 10, 10), new Color(89, 0, 26));
    Image checkerboard2 = new CustomCheckerBoard(25, 20, 20,
        new Color(26, 255, 9), new Color(255,25,0));
    Image rainbow = new RainbowImage();
    IModifyImage greyscale = new Greyscale();
    IModifyImage sepia = new Sepia();
    IModifyImage sharpen = new Sharpen();
    IModifyImage blur = new Blurry();*/

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

    }
  }
    */

