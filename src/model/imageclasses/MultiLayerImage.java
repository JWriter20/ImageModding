package model.imageclasses;

import model.modifications.IModifyImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A MultiLayerImage is an image represented by a list of Layer object. The first layer is the
 * most current layer, and the order of the list represents the ordering of the Layers.
 */
public class MultiLayerImage implements MultiImage {
    private List<Layer> list;

    /**
     * Creates a new MultiLayeredImage object. Sets the list of layers to an ArrayList so
     * more layers can be added easily.
     */
    public MultiLayerImage() {
        this.list = new ArrayList<Layer>();
    }

    /**
     * Adds a new Layer to the end of the List.
     * @param layer The layer to be added.
     */
    @Override
    public void addLayer(Layer layer) {
        list.add(layer);
    }

    /**
     * Brings the layer with the specified name to the front of the list. If there is no layer
     * with the specified name, error is thrown.
     * @param name The name of the layer to be brought to the front of the list
     * @throws IllegalArgumentException If the given name is not the name of a layer in the list
     */
    @Override
    public void bringToFront(String name) throws IllegalArgumentException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).checkName(name)) {
                list.add(0, list.remove(i));
                return;
            }
        }
        throw new IllegalArgumentException("No layer with the given name found");
    }

    @Override
    public void applyToFirst(IModifyImage mod) {
        for (Layer l: list) {
            if (l.isVisible()) {
                l.modifyImage(mod);
                return;
            }
        }
    }

    @Override
    public void setFirstLayerImage(Image img) {
        for (Layer l: list) {
            if (l.isVisible()) {
                l.setImage(img);
                return;
            }
        }
    }

    @Override
    public void exportMultiLayer(String pathToFolder, ImageTypes type) {
        String pathToTextFile = pathToFolder +
                pathToFolder.substring(pathToFolder.lastIndexOf("/") + 1) + "MultiImageFile.txt";


        File dir = new File(pathToFolder);
        if (!dir.exists()){
            dir.mkdir();
        }

        try {
            FileWriter writer = new FileWriter(pathToTextFile);
            for (Layer l: list) {
                l.exportLayer(pathToFolder + l.getName() + type.toString(), type);
                writer.append(pathToFolder + l.getName() + type.toString()).append("\n");

            }
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Bad File");
        }

    }

    @Override
    public void exportFist(String pathToFolder, ImageTypes type) {
        this.list.get(0).exportLayer(pathToFolder, type);
    }

    /**
     * Sets the layer with the given name to visible or invisible based on setToVisible
     * @param layerName The name of the layer
     * @param setToVisible True to set the layer to visible, false to set it to invisible
     */
    private void abstractVisible(String layerName, boolean setToVisible) {
        for (Layer l : list) {
            if (l.checkName(layerName)) {
                l.setVisible(setToVisible);
                return;
            }
        }
        throw new IllegalArgumentException("Unable to find specified layer");
    }

    @Override
    public void setGivenToInvisible(String layerName) throws IllegalArgumentException {
        this.abstractVisible(layerName, false);
    }

    @Override
    public void setGivenToVisible(String layerName) throws IllegalArgumentException {
        this.abstractVisible(layerName, true);
    }

    @Override
    public BufferedImage getFirstVisImg() throws IllegalArgumentException {
        for (Layer l : list) {
            if (l.isVisible()) {
                return l.getViewImg();
            }
        }
        throw new IllegalArgumentException("No Visible Image");
    }

    @Override
    public String toString() {
        String s = "";
        for (Layer l : list) {
            s += l.toString() + "\n";
        }
        return s;
    }

}
