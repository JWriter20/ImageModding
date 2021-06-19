package controller;


import model.ExportedImage;
import model.LayeredModel;
import model.imageclasses.*;
import view.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SimpleImageModController implements ImageModController {
    LayeredModel model;
    ImageView view;

    public SimpleImageModController(LayeredModel model, ImageView view) {
        this.model = model;
        this.view = view;
    }

    public void go() {
        Scanner sc = new Scanner(System.in);
        this.view.renderMessage("Will you enter commands manually or through a file?\n" +
                "Please enter \"m\" for manual or \"f\" for file");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("m")) {
            this.manualControl();
        } else if (answer.equalsIgnoreCase("f")) {
            this.parseFile();
        } else {
            throw new IllegalArgumentException("Invalid answer, please respond with \"m\"" +
                    " or \"f\".");
            //might change this later
        }
    }

    public void manualControl() {
        Scanner sc = new Scanner(System.in);
        this.view.renderScriptPrompt();
        this.view.renderMessage("Begin typing commands:\n");

        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("quit")) {
            this.parseCommand(input);

            input = sc.nextLine();
        }
    }

    public void parseFile() {

    }

    /*
        Note migrate load and export methods to the controller.
        Note - possibly make loading and exporting methods private so it can only be
        accessed through the script.
     */

    private Layer loadLayer(String path) {
        String[] data = path.substring(path.lastIndexOf('/') + 1).split("#");

        return new Layer(new LoadedImage(path), data[0], Boolean.parseBoolean(data[1]));
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
            multiImage.addLayer(loadLayer(sc.nextLine()));
        }

        return multiImage;
    }

    private void exportLayer(String path, ImageTypes type) throws IllegalArgumentException {
        ExportedImage temp = new ExportedImage(path, type);
    }

    private void exportMultiLayeredImage(String folderName) {


    }

    private void parseCommand(String command) {
        String[] commandParts = command.split(" ");
        switch(commandParts[0]) {
            case "create": {
                if (commandParts.length != 3
                        || !commandParts[1].equalsIgnoreCase("layer")) {
                    throw new IllegalArgumentException("Invalid \"create\" parameters");
                } else {
                    this.model.addLayer(commandParts[3]);
                }
            }
            case "current": {
                if (commandParts.length != 2) {
                    throw new IllegalArgumentException("Invalid \"current\" parameters");
                } else {
                    this.model.bringToFront(commandParts[1]);
                }
            }
            case "load": {

            }
            case "save": {

            }
            case "sepia": {
                if (commandParts.length != 1) {
                    throw new IllegalArgumentException("Invalid \"sepia\" commands");
                } else {
                    this.model.sepiaImage();
                }
            }
            case "blur": {
                if (commandParts.length != 1) {
                    throw new IllegalArgumentException("Invalid \"blur\" commands");
                } else {
                    this.model.blurImage();
                }
            }
            case "grayscale": {
                if (commandParts.length != 1) {
                    throw new IllegalArgumentException("Invalid \"gray\" commands");
                } else {
                    this.model.grayImage();
                }
            }
            case "sharp": {
                if (commandParts.length != 1) {
                    throw new IllegalArgumentException("Invalid \"sharp\" commands");
                } else {
                    this.model.sharpImage();
                }
            }
            default: {

            }
        }
    }
}
