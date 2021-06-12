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
    /***
     * Box to be used in inherited classes for storing custom controls added by programmer
     * Note that inputs and outputs boxes are designed only to store sockets!
     */
    @FXML VBox buttons;

    @FXML private VBox inputs;
    @FXML private VBox outputs;

    public int id;

    @FXML Pane topPane;
    @FXML public Label title;
    @FXML Label close;

    public Canvas canvas;
    public boolean ready = false;

    private double relativeX;
    private double relativeY;
    private final ArrayList<TargetSocket> ins = new ArrayList<>();
    private final ArrayList<SourceSocket> outs = new ArrayList<>();
    final ArrayList<AbstractNode> dependencies = new ArrayList<>();
    final ArrayList<AbstractNode> consumers = new ArrayList<>();

    public Filter filter;
    public TargetSocket input1;
    public SourceSocket output1;
    public TargetSocket input2;

    /***
     * Constructs canvas in a given context
     * In implementation, it ought to be called before specialized initialization
     * @param can parent canvas
     */
    AbstractNode(Canvas can){
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

    /***
     * Checks if input is ready
     */
    public abstract boolean checkInput();

    public void clear() {
        ready = false;
        for( TargetSocket s : ins ) s.clear();
        for( SourceSocket t : outs ) t.clear();
    }

    /***
     * Safely deletes node from a canvas along with all its dependencies
     */
    public final void remove()    {
        for( TargetSocket s : ins ) s.unbindAll();
        for( SourceSocket t : outs ) t.unbindAll();
        canvas.removeNode(this);
    }

    /***
     * Adds new input socket
     * it should be used instead of inputs.addChildren().add(), as it makes data in array consistent
     * @param t node to be added
     */
    public final void addInputSocket( TargetSocket t ) {
        ins.add(t);
        inputs.getChildren().add(t);
    }

    /***
     * Adds new output socket
     * it should be used instead of outputs.addChildren().add(), as it makes data in array consistent
     * @param s node to be added
     */
    public final void addOutputSocket( SourceSocket s ) {
        outs.add(s);
        outputs.getChildren().add(s);
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

    /***
     * Returns a list of all dependencies of the node
     * The order of dependencies is not specified
     */
    public ArrayList<AbstractNode> getDependencies() { return dependencies; }

    /***
     * Returns a list of all dependencies of the node
     * The order of dependencies is not specified
     */
    public ArrayList<AbstractNode> getConsumers() { return consumers; }


    /***
     * Set position of a node
     */
    public void setPosition( double x, double y)
    {
        setLayoutX(x);
        setLayoutY(y);
    }

    /***
     * Get X position of a node
     */
    public double getXPosition()   {   return getLayoutX(); }

    /***
     * Get Y position of a node
     */
    public double getYPosition()    {   return getLayoutY();    }

    /***
     * Get socket of a node with given name
     */
    public Socket getSocketByName( String name )
    {
        for( TargetSocket in : ins )    if( in.label.getText() == name ) return in;
        for( SourceSocket out : outs )  if( out.label.getText() == name ) return out;
        return null;
    }
}
