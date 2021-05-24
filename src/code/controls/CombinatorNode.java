package code.controls;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.awt.image.BufferedImage;
import java.io.IOException;

/***
 * Standard node for combining separated channels into one image -- e. g. red, green and blue channels in RGB model
 */
public class CombinatorNode extends AbstractNode {
    @FXML
    public VBox inputs;
    @FXML
    public VBox outputs;

    public TargetSocket inputX;
    public TargetSocket inputY;
    public TargetSocket inputZ;
    public SourceSocket output;

    Combinator combinator;

    @FXML
    Pane topPane;

    public CombinatorNode(Canvas canvas) {
        super(canvas);
        inputX = new TargetSocket(this);
        inputY = new TargetSocket(this);
        inputZ = new TargetSocket(this);
        inputX.setName("X");
        inputY.setName("Y");
        inputZ.setName("Z");
        addInputSocket(inputX);
        addInputSocket(inputY);
        addInputSocket(inputZ);
        output = new SourceSocket(this);
        output.setName("Combined");
        addOutputSocket(output);

        String[] listOfFilters ={"RGB","HSV"};
        ComboBox<String> comboBox= new ComboBox<>(FXCollections.observableArrayList(listOfFilters));
        comboBox.setOnAction(e -> {
            if(comboBox.getValue().equals("RGB")){
                combinator = RGBCombinator;
                inputX.setName("R");
                inputY.setName("G");
                inputZ.setName("B");
            }
            if(comboBox.getValue().equals("HSV")){
                combinator = HSVCombinator;
                inputX.setName("H");
                inputY.setName("S");
                inputZ.setName("V");
            }
        });
        topPane.getChildren().add(comboBox);

        title.setText("Combine");
    }

    @Override
    public void compute() {
        ready = true;
        output.setContent(combinator.combine(inputX.getContent(),inputY.getContent(),inputZ.getContent()));
    }

    /***
     * Abstract interface for splitting image into components
     */
    public interface Combinator {
        default BufferedImage combine( BufferedImage X, BufferedImage Y, BufferedImage Z) {
            BufferedImage res = new BufferedImage(X.getWidth(),X.getHeight(),X.getType());
            for (int y = 0; y < X.getHeight(); y++) {
                for (int x = 0; x < X.getWidth(); x++) {
                    res.setRGB(x,y,get(X.getRGB(x,y),Y.getRGB(x,y),Z.getRGB(x,y)));
                }
            }
            return res;
        }
        int get(int x, int y, int z);
    }

    public static Combinator RGBCombinator = (r, g, b) -> {
        int R = (((r & 0x00ff0000) >> 16) + ((r & 0x0000ff00) >> 8) +  (r & 0x000000ff))/3;
        int G = (((g & 0x00ff0000) >> 16) + ((g & 0x0000ff00) >> 8) +  (g & 0x000000ff))/3;
        int B = (((b & 0x00ff0000) >> 16) + ((b & 0x0000ff00) >> 8) +  (b & 0x000000ff))/3;
        return R << 16 | G << 8 | B;
    };

    public static Combinator HSVCombinator = (h, s, v) -> {
        int H = (((h & 0x00ff0000) >> 16) + ((h & 0x0000ff00) >> 8) +  (h & 0x000000ff))/3;
        int S = (((s & 0x00ff0000) >> 16) + ((s & 0x0000ff00) >> 8) +  (s & 0x000000ff))/3;
        int V = (((v & 0x00ff0000) >> 16) + ((v & 0x0000ff00) >> 8) +  (v & 0x000000ff))/3;
        if( V == 0 ) return 0x00000000;
        int c = (V*S)/255;
        double x = H/42.5;
        while( x >= 2 ) x = x - 2; x = x - 1; if( x < 0 ) x = -x;
        x = c*(1 - x);
        int m = V - c;
        int r = 0;
        int g = 0;
        int b = 0;
        if( H < 42.5 ) { r = c; g = (int)x; b = 0; }
        if( 42.5 <= H && H < 85 ) { r = (int)x; g = c; b = 0; }
        if( 85 <= H && H < 127.5 ) { r = 0; g = c; b = (int)x; }
        if( 127.5 <= H && H < 170 ) { r = 0; g = (int)x; b = c; }
        if( 170 <= H && H < 212.5 ) { r = (int)x; g = 0; b = c; }
        if( 212.5 <= H && H < 255 ) { r = c; g = 0; b = (int)x; }
        return (r+m) << 16 | (g+m) << 8 | (b+m);
    };

}