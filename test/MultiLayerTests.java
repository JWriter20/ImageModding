import controller.ImageModController;
import controller.LayeredImageModController;
import model.LayeredImageModel;
import model.LayeredModel;
import org.junit.Test;
import view.Display;
import view.ImageView;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class MultiLayerTests {
    LayeredModel layeredModel;
    ImageView stringView;
    ImageModController controller;

    private void initController(Appendable ap, Readable rd) {
        this.layeredModel = new LayeredImageModel();
        this.stringView = new Display(ap);
        this.controller = new LayeredImageModController(this.layeredModel, this.stringView, rd);
        controller.go();
    }

    @Test
    public void testRunGame() {
        Appendable ap = new StringBuilder();
        Readable rd = new StringReader("m\ncreate layer first\nquit");
        this.initController(ap, rd);
        assertEquals("", this.layeredModel.toString());
    }
}
