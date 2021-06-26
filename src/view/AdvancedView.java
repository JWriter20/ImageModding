package view;

import controller.Notifiable;

import javax.swing.*;
import java.awt.image.BufferedImage;

public interface AdvancedView extends Notifier {

  @Override
  void setNotifiable(Notifiable v);

  JMenuBar menuBar();

  void repaint(BufferedImage image);


}
