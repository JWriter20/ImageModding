package controller;

import model.LayeredModel;
import model.imageclasses.Image;
import model.imageclasses.LoadedImage;
import model.imageclasses.MultiImage;
import model.imageclasses.MultiLayerImage;
import model.imageclasses.ImageTypes;
import view.AdvancedImageView;
import view.AdvancedView;

import javax.imageio.ImageIO;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Controller used to control the program through a graphical user interface.
 */
public class AdvancedGUIController implements AdvancedController {
  private LayeredModel model;
  private AdvancedView view;


  /**
   * Sets the model, view, and readable to the given objects.
   * @param model The model, in this case a LayeredModel
   */
  public AdvancedGUIController(LayeredModel model) {
    this.model = model;
    this.view = new AdvancedImageView(new Notifiable(this));
  }

  @Override
  public void start() {
    AdvancedImageView.setDefaultLookAndFeelDecorated(false);

    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());

      //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      //    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
      //    {
      //       if ("Nimbus".equals(info.getName())) {
      //          UIManager.setLookAndFeel(info.getClassName());
      //         break;
      //    }
      // }
    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    }
  }


  /**
   * Loads an Image from the given path.
   * @param path The path to locate the image
   * @return A new image object based on the supplied path
   */
  private Image loadImage(String path) {
    return new LoadedImage(path);
  }

  /**
   * Loads in a multi-layered image based on a provided txt file. This txt file
   * contains information that can be used to build up a multi-layered image.
   * @param pathToTxtFile The path to the txt file
   * @return A new MultiImage object based on the information in the txt file.
   */
  private MultiImage loadMultiLayeredImage(String pathToTxtFile) {
    MultiImage multiImage = new MultiLayerImage();
    Scanner sc;
    try {
      sc = new Scanner(new FileReader(pathToTxtFile));
    }
    catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found!");
    }

    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      System.out.println(line);
      String name = line.split("/")[1];
      System.out.println(name);
      System.out.println(line);
      this.model.addLayer(name);
      this.model.bringToFront(name);
      this.model.setFirstLayerImage(loadImage(line));
    }

    return multiImage;
  }

  /**
   * Saves the layer as the inputted type using the given path.
   *
   * @param name The name of the file - ex. face-blur.jpeg
   * @param type The ImageTypes object that represents the file type.
   */
  private void exportLayer(String name, ImageTypes type) {
    this.model.exportFirst(name, type);
  }

  /**
   * Exports multiple files to the given folder using their layer names as the file name.
   * Also exports a text file into the folder that lists all of the files contained in the
   * MultiImage.
   *
   * @param folderName The path to the text file from ./res/ - ex. mutliTest/MultiImageFile.txt
   * @param type The type that all of the images will be saved as.
   */
  private void exportMultiLayeredImage(String folderName, ImageTypes type) {
    String path = folderName + "/";
    this.model.exportMultiImage(path, type);
  }

  /**
   * Takes in a string and either executes the command if the command is valid, or throws an error.
   * @param command The command to be parsed and executed
   * @throws IllegalArgumentException If the command is not valid based on the syntax
   *       outlined in the USEME.md file
   */
  public void parseCommand(String command) throws IllegalArgumentException {
    String[] commandParts = command.split(" ");
    switch (commandParts[0].toLowerCase()) {
      case "create": {
        if (commandParts.length != 3
            || !commandParts[1].equalsIgnoreCase("layer")) {
          throw new IllegalArgumentException("Invalid \"create\" parameters");
        } else {
          this.view.addLayer(commandParts[2]);
          this.model.addLayer(commandParts[2]);
        }
        break;
      }
      case "current": {
        if (commandParts.length != 2) {
          throw new IllegalArgumentException("Invalid \"current\" parameters");
        } else {
          this.model.bringToFront(commandParts[1]);
          this.view.repaint(this.model.getViewImage());
        }
        break;
      }
      case "load": {
        if (commandParts.length != 2) {
          throw new IllegalArgumentException("Invalid \"load\" parameters");
        } else {
          this.model.setFirstLayerImage(loadImage("Pictures/" + commandParts[1]));
          this.view.repaint(this.model.getViewImage());
        }
        break;
      }
      case "invisible": {
        if (commandParts.length != 2) {
          throw new IllegalArgumentException("Invalid \"invisible\" parameters");
        } else {
          this.model.setLayerToInvisible(commandParts[1]);
          this.view.repaint(this.model.getViewImage());
        }
        break;
      }
      case "visible": {
        if (commandParts.length != 2) {
          throw new IllegalArgumentException("Invalid \"visible\" parameters");
        } else {
          this.model.setLayerToVisible(commandParts[1]);
          this.view.repaint(this.model.getViewImage());
        }
        break;
      }

      case "load-multi": {
        System.out.println(commandParts[1]);
        loadMultiLayeredImage("res/" + commandParts[1]);
        this.view.repaint(this.model.getViewImage());
        break;
      }

      case "save": {
        exportLayer("res/" + commandParts[1], ImageTypes.PNG);
        break;
      }

      case "save-multi": {

        switch (commandParts[2].toLowerCase()) {
          case "png":
            exportMultiLayeredImage("res/" + commandParts[1], ImageTypes.PNG);
            break;
          case "jpeg":
            exportMultiLayeredImage("res/" + commandParts[1], ImageTypes.JPG);
            break;
          case "jpg":
            exportMultiLayeredImage("res/" + commandParts[1], ImageTypes.JPG);
            break;
          case "ppm":
            exportMultiLayeredImage("res/" + commandParts[1], ImageTypes.PPM);
            break;
          default:
            throw new IllegalArgumentException("Bad Inputted type");
        }
        break;
      }

      case "sepia": {
        if (commandParts.length != 1) {
          throw new IllegalArgumentException("Invalid \"sepia\" commands");
        } else {
          this.model.sepiaImage();
          this.view.repaint(this.model.getViewImage());
        }
        break;
      }
      case "blur": {
        if (commandParts.length != 1) {
          throw new IllegalArgumentException("Invalid \"blur\" commands");
        } else {
          this.model.blurImage();
          this.view.repaint(this.model.getViewImage());
        }
        break;
      }
      case "grayscale": {
        if (commandParts.length != 1) {
          throw new IllegalArgumentException("Invalid \"gray\" commands");
        } else {
          this.model.grayImage();
          this.view.repaint(this.model.getViewImage());
        }
        break;
      }
      case "sharp": {
        if (commandParts.length != 1) {
          throw new IllegalArgumentException("Invalid \"sharp\" commands");
        } else {
          this.model.sharpImage();
          this.view.repaint(this.model.getViewImage());
        }
        break;
      }
      case "shrink": {
        if (commandParts.length != 3) {
          throw new IllegalArgumentException("Invalid \"shrink\" commands");
        } else {
          this.model.downsizeAll(Integer.parseInt(commandParts[1]),
              Integer.parseInt(commandParts[2]));
          this.view.repaint(this.model.getViewImage());
        }
        break;
      }
      case "mosaic": {
        if (commandParts.length != 2) {
          throw new IllegalArgumentException("Invalid \"mosaic\" commands");
        } else {
          this.model.mosaicFirst(Integer.parseInt(commandParts[1]));
          this.view.repaint(this.model.getViewImage());
        }
        break;
      }
      default: {
        throw new IllegalArgumentException("Invalid command, please refer to the " +
            "documentation for a list of valid commands.");
      }
    }
  }

}
