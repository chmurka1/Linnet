package code.controls;

import code.filters.Filter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/***
 * Abstract class serving as a blackbox for transforming given input into output
 * it is in some sense the inverse of a link, because it sends data from target sockets to source sockets
 * must be attached to some canvas
 */
public abstract class AbstractNode extends AnchorPane {
    @FXML VBox buttons;
    @FXML private VBox inputs;
    @FXML private VBox outputs;
    @FXML Pane topPane;
    @FXML public Label title;
    @FXML Label close;

    public Canvas canvas;
    public boolean ready = false;

    private double relativeX;
    private double relativeY;
    private final ArrayList<TargetSocket> ins = new ArrayList<>();
    private final ArrayList<SourceSocket> outs = new ArrayList<>();

    public Filter filter;

    public AbstractNode(Canvas can){
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("abstractNode.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }
        canvas = can;
        setLayoutX(canvas.mouseX);
        setLayoutY(canvas.mouseY);
        setOnMousePressed(me -> { relativeX = getLayoutX() - me.getSceneX();relativeY = getLayoutY() - me.getSceneY(); setCursor(Cursor.MOVE); });
        setOnMouseDragged( me -> { setLayoutX(me.getSceneX()+relativeX);setLayoutY(me.getSceneY()+relativeY); });
        setOnMouseEntered( me -> setCursor(Cursor.HAND));
        setOnMouseReleased( me -> setCursor(Cursor.HAND));
        close.setOnMousePressed( me -> remove() );
    }

    /***
     * Computes the output -- it is not implemented lazily,
     * responsibility for calling it effectively is shifted to a caller
     */
    public abstract void compute();

    public abstract boolean checkInput();

    public void clear() {
        ready = false;
        for( TargetSocket s : ins ) s.clear();
        for( SourceSocket t : outs ) t.clear();
    }

    public void remove()    {
        for( TargetSocket s : ins ) s.unbindAll();
        for( SourceSocket t : outs ) t.unbindAll();
        canvas.removeNode(this);
    }

    public final void addInputSocket( TargetSocket t ) {
        ins.add(t);
        inputs.getChildren().add(t);
    }

    public final void addOutputSocket( SourceSocket t ) {
        outs.add(t);
        outputs.getChildren().add(t);
    }

    public static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }
        return new ImageView(wr).getImage();
    }
}
