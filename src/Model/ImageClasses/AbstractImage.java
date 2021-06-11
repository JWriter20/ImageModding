package src.Model.ImageClasses;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

/**
 * Abstract class that will be extended by any class representing an Image. Many of these
 * methods rely on the BufferedImage field so they can be abstract here.
 */
public abstract class AbstractImage implements Image {
  BufferedImage img;



  @Override
  public int getWidth() throws IllegalArgumentException {
    return this.img.getWidth();
  }

  @Override
  public int getHeight() throws IllegalArgumentException {
    return this.img.getHeight();
  }

  @Override
  public Color getColorAt(int x, int y) throws IllegalArgumentException {
    return new Color(this.img.getRGB(x, y), true);
  }

  @Override
  public void setColorAt(int x, int y, Color color) throws IllegalArgumentException {
    this.img.setRGB(x, y, color.getRGB());
  }

  @Override
  public void displayImage() throws IllegalArgumentException {
    ImageIcon icon = new ImageIcon(this.img);
    JFrame frame = new JFrame();
    frame.setLayout(new FlowLayout());
    frame.setSize(this.getWidth(),this.getHeight() + 25);
    JLabel lbl=new JLabel();
    lbl.setIcon(icon);
    frame.add(lbl);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void exportImage(String imageName) {
    File output = new File("./out/production/Assignment5/res/" + imageName + ".ppm");
    Appendable toWrite = new StringBuilder();
    try {
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
}
