package controller;

import model.LayeredModel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A simple GUI that can be used to interact with the program.
 */
public class SimpleGraphicalController implements GraphicalController, ActionListener {
  private LayeredModel model;


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Modification Button": {

      }
    }
  }
}
