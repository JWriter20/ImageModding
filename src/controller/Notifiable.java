package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class used to Notify the other classes when an action is taken.
 */
public class Notifiable {

  private AdvancedController controller;

  /**
   * Creates a new Notifiable object and initializes the controller to the given one.
   * @param controller The given controller
   */
  public Notifiable(AdvancedController controller) {
    this.controller = controller;
  }

  /**
   * Parses the given command by calling the appropriate method in the controller.
   * @param command The given command
   */
  public void update(String command) {
    System.out.println(command);
    controller.parseCommand(command);

  }

  /**
   * Runs the given script by parsing all the commands in it.
   * @param script The given script
   */
  public void runScript(String script) {
    String[] commands = script.split("\n");
    int i = 0;
    while (i < commands.length && !commands[i].equalsIgnoreCase("quit")) {
      System.out.println(commands[i]);
      controller.parseCommand(commands[i]);
      i++;
    }
  }

  /**
   * Runs a script that is stored in a file.
   * @param file The file that the script is store in
   */
  public void runFileScript(String file) {
    File script = new File(file);
    Scanner fileInput;
    try {
      fileInput = new Scanner(script);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Unable to read file");
    }

    while (fileInput.hasNextLine()) {
      String input = fileInput.nextLine();
      controller.parseCommand(input);
    }
  }

}
