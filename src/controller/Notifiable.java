package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Notifiable {

  AdvancedController controller;

  public Notifiable(AdvancedController controller) {
    this.controller = controller;
  }

  public void update(String command) {
    controller.parseCommand(command);

  }

  public void runScript(String script) {
    String[] commands = script.split("\n");
    int i = 0;
    while (i < commands.length && !commands[i].equalsIgnoreCase("quit")) {
      System.out.println(commands[i]);
      controller.parseCommand(commands[i]);
      i++;
    }
  }

  public void runFileScript(String file){
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
