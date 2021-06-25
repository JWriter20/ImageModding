package model.imageclasses;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;

/**
 * Abstract class that will be extended by any class representing an Image. Many of these
 * methods rely on the BufferedImage field so they can be abstract here.
 */

public abstract class AbstractImage implements Image {
  BufferedImage img;

  @Override
  public int getWidth() {
    return this.img.getWidth();
  }

  @Override
  public int getHeight() {
    return this.img.getHeight();
  }

  @Override
  public Color getColorAt(int x, int y) throws IllegalArgumentException {
    try {
      return new Color(this.img.getRGB(x, y), true);
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("The supplied indices are out of bounds.");
    }
  }

  @Override
  public void setColorAt(int x, int y, Color color) throws IllegalArgumentException {
    try {
      this.img.setRGB(x, y, color.getRGB());
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("The supplied indices are out of bounds.");
    }
  }

  @Override
  public void exportImageAsPPM(String path) throws IllegalStateException {
    try {
      File output = new File(path);
      Appendable toWrite = new StringBuilder();
      String header = String.format("P3\n%d %d\n255\n",
          img.getWidth(), img.getHeight());

      toWrite.append(header);

      for (int y = 0; y < img.getHeight(); y++) {
        for (int x = 0; x < img.getWidth(); x++) {
          Color c = new Color(img.getRGB(x, y));
          toWrite.append("" + c.getRed()).append(" ");
          toWrite.append("" + c.getGreen()).append(" ");
          toWrite.append("" + c.getBlue()).append(" ");
        }
      }

      FileWriter out = new FileWriter(output);
      out.write(toWrite.toString());
      out.close();
    } catch (IOException e) {
      throw new IllegalStateException("Writing failed.");
    }
    catch (NullPointerException e) {
      throw new IllegalStateException("Null Img.");
    }
  }

  @Override
  public void exportImageAs(String path, ImageTypes type) throws IllegalArgumentException {
    String imgType = "";
    switch (type) {
      case JPG:
        imgType = "JPG";
        break;
      case PNG:
        imgType = "PNG";
        break;
      case PPM:
        exportImageAsPPM(path);
        return;
      default:
        throw new IllegalArgumentException("Invalid type");
    }

    File output = new File(path);
    try {
      ImageIO.write(img, imgType, output);
    } catch (IOException e) {
      throw new IllegalArgumentException("Bad IO");
    }
  }

  @Override
  public void display() {
    JFrame frame;
    JLabel label;
    frame = new JFrame();
    frame.setTitle("stained_image");
    frame.setSize(img.getWidth(), img.getHeight());
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    label = new JLabel();
    label.setIcon(new ImageIcon(img));
    frame.getContentPane().add(label, BorderLayout.CENTER);
    frame.setLocationRelativeTo(null);
    frame.pack();
    frame.setVisible(true);
  }


}
