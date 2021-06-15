package controller;


import model.ImageModel;
import view.ImageView;

import java.util.Scanner;

public class SimpleImageModController implements ImageModController {
    ImageModel model;
    ImageView view;

    public SimpleImageModController(ImageModel model, ImageView view) {
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
