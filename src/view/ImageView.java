package view;

/**
 * Interface representing a way to view the program. Needs a way to render messages, as well
 * as the prompt explaining how to use the program.
 */
public interface ImageView {

  /**
   * Renders the given message through the view.
   *
   * @param message The given message
   */
  void renderMessage(String message);

  /**
   * Renders the script prompt which explains the syntax and how to use the program.
   */
  void renderScriptPrompt();
}
