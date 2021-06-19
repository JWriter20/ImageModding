package view;

import java.io.IOException;

public class Display implements ImageView {
    private Appendable out;

    public Display(Appendable out) {
        this.out = out;
    }

    public void renderMessage(String message) {
        try {
            this.out.append(message);
        } catch(IOException e) {
            throw new IllegalArgumentException("Bad IO");
        }

    }

    public void renderScriptPrompt() {
        try {
            out.append(
              "Please input your series of image modifications in one of the following formats:\n"
            + "create *type *name - creates a *type of name *name\n"
            + "current *savedName - sets the *type named *name as the one to be modified\n"
            + "load *filename - loads file into current\n"
            + "save *filename - saves *filename with its modifications\n"
            + "*modification - modifies current with the given modification type\n"
            + "Argument Descriptions:\n"
            + "*name: Any String\n"
            + "*type: Layer (more types possibly added in future)\n"
            + "*savedName: represents the name of a *type that has already been created\n"
            + "*filename: the name of a file\n"
            + "*modification: a type of modification, one of: blur, sharp, sepia, greyscale\n"
            );
        } catch (IOException e) {
            throw new IllegalArgumentException("Bad IO");
        }
    }

}
