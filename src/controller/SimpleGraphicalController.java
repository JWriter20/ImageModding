package controller;

import model.LayeredModel;
import view.GraphicalView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGraphicalController implements GraphicalController, ActionListener {
  private LayeredModel model;
  private GraphicalView view;

  public SimpleGraphicalController(LayeredModel model, GraphicalView view) {
    this.model = model;
    this.view = view;
    view.setListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Modification Button": {

      }
    }
  }
}
