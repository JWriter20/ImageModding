import controller.ImageModController;
import controller.LayeredImageModController;
import model.LayeredImageModel;
import model.LayeredModel;
import org.junit.Test;
import view.Display;
import view.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class MultiLayerTests {
    LayeredModel layeredModel;
    ImageView stringView;
    ImageModController controller;

    private void initController(String input) {
        this.layeredModel = new LayeredImageModel();
        this.stringView = new Display(new StringBuilder());
        this.controller = new LayeredImageModController(this.layeredModel,
                this.stringView, new StringReader(input));
        controller.go();
    }

    //Test creating a layer
    @Test
    public void testCreateLayer() {
        String input = "m\ncreate layer first\nquit";
        this.initController(input);
        assertEquals("name: first, null image, visible layer\n", this.layeredModel.toString());
    }

    //Test creating multiple layers
    @Test
    public void testCreateMultipleLayers() {
        String input = "m\ncreate layer first\ncreate layer second\ncreate layer third\nquit";
        this.initController(input);
        assertEquals("name: first, null image, visible layer\n" +
                "name: second, null image, visible layer\n" +
                "name: third, null image, visible layer\n", this.layeredModel.toString());
    }

    //Tests error thrown with invalid create parameters
    @Test (expected = IllegalArgumentException.class)
    public void testInvalidCreateParams() {
        String input = "m\ncreate layer first second\nquit";
        this.initController(input);
    }

    //Tests error thrown with misspelled create params
    @Test (expected = IllegalArgumentException.class)
    public void testMisspelledCreateParams() {
        String input = "m\ncreate laeyr name";
        this.initController(input);
    }

    //Tests that a layer can be set as the current one
    @Test
    public void testSetLayerToCurrent() {
        String input = "m\ncreate layer firstLayer\ncreate layer " +
                "secondLayer\ncurrent secondLayer\nquit";
        this.initController(input);
        assertEquals("name: secondLayer, null image, visible layer\n" +
                "name: firstLayer, null image, visible layer\n", this.layeredModel.toString());
    }

    //Tests an error is thrown if the name of the layer to set to the current one
    //is not there
    @Test (expected = IllegalArgumentException.class)
    public void testInvalidLayerNameSetToCurrent() {
        String input = "m\ncreate layer first\ncreate layer second\ncurrent third\nquit";
        this.initController(input);
    }

    //Tests an error is thrown if there are too many current args
    @Test (expected = IllegalArgumentException.class)
    public void testTooManyCurrentArgs() {
        String input = "m\ncreate layer first\ncreate layer second\ncurrent second layer\nquit";
        this.initController(input);
    }

    //Tests that the user can load an image to the first layer
    @Test
    public void testLoadImageToFirstLayer() {
        String input = "m\ncreate layer first\ncreate layer second\nload face.png\nquit";
        this.initController(input);
        assertEquals("name: first, non-null image, visible layer\n" +
                "name: second, null image, visible layer\n", this.layeredModel.toString());
    }

    //Tests that the user can set a different layer to the first and then load an image
    @Test
    public void testLoadImageToLayerThatWasSetToCurrent() {
        String input = "m\ncreate layer first\ncreate layer second\n" +
                "create layer third\ncurrent second\nload face.png\nquit";
        this.initController(input);
        assertEquals("name: second, non-null image, visible layer\n" +
                "name: first, null image, visible layer\n" +
                "name: third, null image, visible layer\n", this.layeredModel.toString());
    }

    //Tests that if the topmost layer is invisible the image is loaded to the next non invisible
    //layer
    @Test
    public void testLoadImageToFirstNonInvisibleLayer() {
        String input = "m\ncreate layer first\ncreate layer second\ninvisible first\nload face.png\n" +
                "quit";
        this.initController(input);
        assertEquals("name: first, null image, invisible layer\n" +
                "name: second, non-null image, visible layer\n", this.layeredModel.toString());
    }

    //Tests that a layer can be made invisible
    @Test
    public void testMakeLayerInvisible() {
        String input = "m\ncreate layer first\ninvisible first\nquit";
        this.initController(input);
        assertEquals("name: first, null image, invisible layer\n", this.layeredModel.toString());
    }

    //Tests that a layer can be made invisible and then visible again
    @Test
    public void testMakeLayerInvisibleThenVisible() {
        String input = "m\ncreate layer first\ninvisible first\nvisible first\nquit";
        this.initController(input);
        assertEquals("name: first, null image, visible layer\n",
                this.layeredModel.toString());
    }

    //Tests that an error is thrown if the name of the provided layer does not match one of the layer
    //when making a layer invisible
    @Test (expected = IllegalArgumentException.class)
    public void testMakeLayerInvisibleInvalidName() {
        String input = "m\ncreate layer first\ninvisible randomLayer\nquit";
        this.initController(input);
    }

    @Test
    public void testMutliExportFromFile() {
        String input = "f\nexportMultiple";
        this.initController(input);
        try {
            Scanner s = new Scanner(new File("./res/mutliTest/MultiImageFile.txt"));
            assertEquals(s.nextLine(), "res/mutliTest/second.PNG");
            assertEquals(s.nextLine(), "res/mutliTest/first.PNG");

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }

    @Test
    public void testMultiLoadAndMutliSaveFromFileScript() {
        String input = "f\nloadMultiple";
        this.initController(input);
        // proves that the two images were loaded
        assertEquals("name: second, non-null image, visible layer\n" +
                "name: first, non-null image, visible layer\n", this.layeredModel.toString());
        try {
            Scanner s = new Scanner(new File("./res/changedToGreyscale/MultiImageFile.txt"));
            assertEquals(s.nextLine(), "res/changedToGreyscale/second.JPG");
            assertEquals(s.nextLine(), "res/changedToGreyscale/first.JPG");

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
    }
    
}
