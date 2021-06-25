package view;

import java.io.IOException;

/**
 * A simple way to interact with the program. The user is greeted with text representing
 * the possible commands, and will recieve information if they enter a command wrong.
 */
public class Display implements ImageView {
  private Appendable out;

  /**
   * Creates a new Display object. Sets the Appendable to the given one.
   *
   * @param out The Appendable used for output
   */
  public Display(Appendable out) {
    this.out = out;
  }

  @Override
  public void renderMessage(String message) {
    try {
      this.out.append(message);
    } catch (IOException e) {
      throw new IllegalArgumentException("Bad IO");
    }
  }

  @Override
  public void renderScriptPrompt() {
    try {
      out.append(
          "Please input your series of image modifications in one of the following formats:\n"
              + "create layer *name - creates a layer of name *name\n"
              + "current *savedName - sets the layer named *name as the one to be modified\n"
              + "load *filename - loads file into current\n"
              + "save *filename - saves *filename with its modifications\n"
              + "*modification - modifies current with the given modification type\n"
              + "save-multi *filename *type - saves all the layers in a folder as type *type\n"
              + "load-multi *filename - loads in all of the layers using a text file\n"
              + "invisible *name - makes the layer with name *name invisible\n"
              + "visible *name - makes the layer with name *name visible\n"
              + "Argument Descriptions:\n"
              + "*name: Any String\n"
              + "*type: one of \"ppm\", \"png\", or \"jpg\" (jpeg also accepted)\n"
              + "*savedName: represents the name of a *type that has already been created\n"
              + "*filename: the name of a file\n"
              + "*modification: a type of modification, one of: blur, sharp, sepia, greyscale\n"
      );
    } catch (IOException e) {
      throw new IllegalArgumentException("Bad IO");
    }
  }

}
