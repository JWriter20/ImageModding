package controller;

/**
 * The interface for the Controller.
 */
public interface ImageModController {
  /**
   * Method that starts the program. This will prompt the user to either enter commands
   * manually, or through a script. This method will continue to run until the whole
   * file is parsed and executed, there is an error in the file, there is an error
   * in the manually entered commands, or the user enters the "quit" command.
   */
  void go();
}
