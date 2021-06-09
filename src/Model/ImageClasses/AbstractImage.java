package src.Model.ImageClasses;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.io.BufferedOutputStream;
import java.nio.charset.StandardCharsets;

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
  public Color getColorAt(int x, int y) {
    return new Color(this.img.getRGB(x, y), true);
  }

  @Override
  public void setColorAt(int x, int y, Color color) {
    this.img.setRGB(x, y, color.getRGB());
  }

  @Override
  public void displayImage() {
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
    File output = new File("./out/production/Assignment5/Pictures/" + imageName + ".ppm");
    try {
      FileOutputStream fileStream = new FileOutputStream(output, true);
      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(output));
      String header = String.format("P6\n%d %d\n255\n",
          img.getWidth(), img.getHeight());

      out.write(header.getBytes(StandardCharsets.US_ASCII));

      for (int y = 0; y < img.getHeight(); y++) {
        for (int x = 0; x < img.getWidth(); x++) {
          Color c = new Color(img.getRGB(x, y));
          out.write(c.getRed());
          out.write(c.getGreen());
          out.write(c.getBlue());
        }
      }
      out.close();

    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
