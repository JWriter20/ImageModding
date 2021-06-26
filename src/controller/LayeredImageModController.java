package controller;

import model.LayeredModel;
import model.imageclasses.*;
import view.ImageView;

import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * A controller that takes in a LayeredModel and a ImageView and runs the program.
 */
public class LayeredImageModController implements ImageModController {
    private LayeredModel model;
    private ImageView view;
    private Readable rd;

    /**
     * Sets the model, view, and readable to the given objects.
     * @param model The model, in this case a LayeredModel
     * @param view The view object
     * @param rd The readable object
     */
    public LayeredImageModController(LayeredModel model, ImageView view, Readable rd) {
        this.model = model;
        this.view = view;
        this.rd = rd;
    }


    @Override
    public void go() {
        Scanner sc = new Scanner(this.rd);
        this.view.renderMessage("Will you enter commands manually or through a file?\n" +
                "Please enter \"m\" for manual or \"f\" for file: ");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("m")) {
            this.manualControl(sc);
        } else if (answer.equalsIgnoreCase("f")) {
            this.view.renderMessage("Please provide the name of the script you would like to use: ");
            String name = sc.nextLine();
            this.parseFile(name);
        } else {
            throw new IllegalArgumentException("Invalid answer, please respond with \"m\"" +
                    " or \"f\".");
            //might change this later
        }
    }

    /**
     * Provides the user will the ability to manually enter commands. The program will
     * stop when an invalid command is entered or "quit" is entered.
     */
    private void manualControl(Scanner sc) {

        this.view.renderScriptPrompt();
        this.view.renderMessage("Begin typing commands:\n");

        String input;
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if (input.equals("quit")) {
                break;
            }
            this.parseCommand(input);
        }
    }

    /**
     * Parses the command in a txt file. This will run through each line in the file, and execute
     * that command until there are no more lines left. The file does not need to have the "quit"
     * command. An error is thrown if any of the commands are invalid.
     * @param name The name of the file, needs to be located in src/scripts/
     */
    private void parseFile(String name) {
        File file = new File("./src/scripts/"
                + name + ".txt");
        Scanner fileInput;
        try {
            fileInput = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to read file");
        }

        while (fileInput.hasNextLine()) {
            String input = fileInput.nextLine();
            this.parseCommand(input);
        }
    }


    /**
     * Loads an Image from the given path.
     * @param path The path to locate the image
     * @return A new image object based on the supplied path
     */
    private Image loadImage(String path) {
        return new LoadedImage(path);
    }

    /**
     * Loads in a multi-layered image based on a provided txt file. This txt file
     * contains information that can be used to build up a multi-layered image.
     * @param pathToTxtFile The path to the txt file
     * @return A new MultiImage object based on the information in the txt file.
     */
    private MultiImage loadMultiLayeredImage(String pathToTxtFile) {
        MultiImage multiImage = new MultiLayerImage();
        Scanner sc;
        try {
            sc = new Scanner(new FileReader(pathToTxtFile));
        }
        catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found!");
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String name = line.substring(line.lastIndexOf("/") + 1, line.lastIndexOf("."));
            this.model.addLayer(name);
            this.model.bringToFront(name);
            this.model.setFirstLayerImage(loadImage(line));
        }

        return multiImage;
    }

    /**
     * Saves the layer as the inputted type using the given path.
     *
     * @param name The name of the file - ex. face-blur.jpeg
     * @param type The ImageTypes object that represents the file type.
     */
    private void exportLayer(String name, ImageTypes type) {
        this.model.exportFirst(name, type);
    }

    /**
     * Exports multiple files to the given folder using their layer names as the file name.
     * Also exports a text file into the folder that lists all of the files contained in the
     * MultiImage.
     *
     * @param folderName The path to the text file from ./res/ - ex. mutliTest/MultiImageFile.txt
     * @param type The type that all of the images will be saved as.
     */
    private void exportMultiLayeredImage(String folderName, ImageTypes type) {
        String path = folderName + "/";
        this.model.exportMultiImage(path, type);
    }

    /**
     * Takes in a string and either executes the command if the command is valid, or throws an error.
     * @param command The command to be parsed and executed
     * @throws IllegalArgumentException If the command is not valid based on the syntax outlined in the
     * USEME.md file
     */
    private void parseCommand(String command) throws IllegalArgumentException {
        String[] commandParts = command.split(" ");
        switch(commandParts[0]) {
            case "create": {
                if (commandParts.length != 3
                        || !commandParts[1].equalsIgnoreCase("layer")) {
                    throw new IllegalArgumentException("Invalid \"create\" parameters");
                } else {
                    this.model.addLayer(commandParts[2]);
                }
                break;
            }
            case "current": {
                if (commandParts.length != 2) {
                    throw new IllegalArgumentException("Invalid \"current\" parameters");
                } else {
                    this.model.bringToFront(commandParts[1]);
                }
                break;
            }
            case "load": {
                if (commandParts.length != 2) {
                    throw new IllegalArgumentException("Invalid \"load\" parameters");
                } else {
                    this.model.setFirstLayerImage(loadImage("Pictures/" + commandParts[1]));
                }
                break;
            }
            case "invisible": {
                if (commandParts.length != 2) {
                    throw new IllegalArgumentException("Invalid \"invisible\" parameters");
                } else {
                    this.model.setLayerToInvisible(commandParts[1]);
                }
                break;
            }
            case "visible": {
                if (commandParts.length != 2) {
                    throw new IllegalArgumentException("Invalid \"visible\" parameters");
                } else {
                    this.model.setLayerToVisible(commandParts[1]);
                }
                break;
            }

            case "load-multi": {
                loadMultiLayeredImage("res/" + commandParts[1]);
                break;
            }

            case "save": {
                exportLayer("res/" + commandParts[1], ImageTypes.PNG);
                break;
            }

            case "save-multi": {

                switch(commandParts[2].toLowerCase()) {
                    case "png":
                        exportMultiLayeredImage("res/" + commandParts[1], ImageTypes.PNG);
                        break;
                    case "jpeg":
                        exportMultiLayeredImage("res/" + commandParts[1], ImageTypes.JPG);
                        break;
                    case "jpg":
                        exportMultiLayeredImage("res/" + commandParts[1], ImageTypes.JPG);
                        break;
                    case "ppm":
                        exportMultiLayeredImage("res/" + commandParts[1], ImageTypes.PPM);
                        break;
                    default:
                        throw new IllegalArgumentException("Bad Inputted type");
                }
                break;
            }

            case "sepia": {
                if (commandParts.length != 1) {
                    throw new IllegalArgumentException("Invalid \"sepia\" commands");
                } else {
                    this.model.sepiaImage();
                }
                break;
            }
            case "blur": {
                if (commandParts.length != 1) {
                    throw new IllegalArgumentException("Invalid \"blur\" commands");
                } else {
                    this.model.blurImage();
                }
                break;
            }
            case "grayscale": {
                if (commandParts.length != 1) {
                    throw new IllegalArgumentException("Invalid \"gray\" commands");
                } else {
                    this.model.grayImage();
                }
                break;
            }
            case "sharp": {
                if (commandParts.length != 1) {
                    throw new IllegalArgumentException("Invalid \"sharp\" commands");
                } else {
                    this.model.sharpImage();
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid command, please refer to the " +
                        "documentation for a list of valid commands.");
            }
        }
    }
}
