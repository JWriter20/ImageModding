package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Notifiable {

  private String command;
  AdvancedController controller;


  public void update(String command) {
    this.command = command;
    controller.parseCommand(command);

  }

  public void runScript(String script) {
    String[] commands = script.split("\n");
    int i = 0;
    while (!commands[i].equalsIgnoreCase("quit") && i < commands.length) {
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
