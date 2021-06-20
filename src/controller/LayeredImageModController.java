package controller;

import model.LayeredModel;
import model.imageclasses.*;
import view.ImageView;

import java.io.*;
import java.util.Scanner;

/**
 * A controller that takes in a LayeredModel and a ImageView and runs the program.
 */
public class LayeredImageModController implements ImageModController {
    private LayeredModel model;
    private ImageView view;
    private Readable rd;

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
            this.manualControl();
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
    private void manualControl() {
        Scanner sc = new Scanner(this.rd);
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

    /*
        Note migrate load and export methods to the controller.
        Note - possibly make loading and exporting methods private so it can only be
        accessed through the script.
     */

    private Image loadImage(String path) {
        return new LoadedImage(path);
    }

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
            System.out.println(name);
            this.model.addLayer(name);
            this.model.bringToFront(name);
            this.model.setFirstLayerImage(loadImage(line));
        }

        return multiImage;
    }

    private void exportLayer(String path, ImageTypes type) throws IllegalArgumentException {
        this.model.exportFirst(path, type);
    }

    private void exportMultiLayeredImage(String folderName, ImageTypes type) {
        String path = folderName + "/";
        this.model.exportMultiImage(path, type);


    }

    private void parseCommand(String command) {
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

            case "load-multi": {
                loadMultiLayeredImage(commandParts[1]);
                break;
            }

            case "save": {
                exportLayer("res/" + commandParts[1], ImageTypes.PNG);
                break;
            }

            case "save-multi": {
                exportMultiLayeredImage("res/" + commandParts[1], ImageTypes.PNG);
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
