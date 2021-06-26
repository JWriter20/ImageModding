package view;

import controller.Notifiable;

import javax.swing.*;
import java.awt.image.BufferedImage;

public interface AdvancedView {

  JMenuBar menuBar();

  void repaint(BufferedImage image);


}
