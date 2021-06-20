package model.imageclasses;

import model.modifications.IModifyImage;

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
            System.out.println(name);
            System.out.println(list.get(i));
            if (list.get(i).checkName(name)) {
                list.add(0, list.remove(i));
                return;
            }
        }
        throw new IllegalArgumentException("No layer with the given name found");
    }

    @Override
    public void applyToFirst(IModifyImage mod) {
        list.get(0).modifyImage(mod);
    }

    @Override
    public void setFirstLayerImage(Image img) {
        this.list.get(0).setImage(img);
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
            // pathToFolder - res/mutliTest/
            // pathToTextFile - res/mutliTest/MultiImageFile.txt
            FileWriter writer = new FileWriter(pathToTextFile);
            System.out.println(list.size());
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


    @Override
    public String toString() {
        String s = "";
        for (Layer l : list) {
            s += l.toString() + "\n";
        }
        return s;
    }

}
