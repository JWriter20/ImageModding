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
            out.append("""
            Please input your series of image modifications in one of the following formats:
            create *type *name - creates a *type of name *name
            current *savedName - sets the *type named *name as the one to be modified
            load *filename - loads file into current
            save *filename - saves *filename with its modifications
            *modification - modifies current with the given modification type
            
            Argument Descriptions:
            *name: Any String
            *type: Layer (more types possibly added in future)
            *savedName: represents the name of a *type that has already been created
            *filename: the name of a file
            *modification: a type of modification, one of: blur, sharp, sepia, greyscale
            """);
        } catch (IOException e) {
            throw new IllegalArgumentException("Bad IO");
        }
    }

}
