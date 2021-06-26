package view;


import javax.swing.JMenuBar;
import java.awt.image.BufferedImage;

/**
 * Interface for a creating graphical views.
 */
public interface AdvancedView {

  /**
   * Creates the JMenuBar that the user can interact with.
   * @return The JMenuBar
   */
  JMenuBar menuBar();

  /**
   * Repaints the image that is shown whenever it needs to be.
   * @param image The image to repaint with
   */
  void repaint(BufferedImage image);


}
