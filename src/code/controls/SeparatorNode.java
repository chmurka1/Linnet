package code.controls;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import java.awt.image.BufferedImage;

import static java.lang.Math.max;
import static java.lang.Math.min;

/***
 * Standard node for splitting image into separate components -- e. g. red ,green and blue channels in RGB model
 */
public class SeparatorNode extends AbstractNode {
    public TargetSocket input;
    public SourceSocket outputX;
    public SourceSocket outputY;
    public SourceSocket outputZ;

    Separator separator;

    public SeparatorNode(Canvas canvas) {
        super(canvas);
        input = new TargetSocket(this);
        input.setName("Combined");
        addInputSocket(input);
        outputX = new SourceSocket(this);
        outputY = new SourceSocket(this);
        outputZ = new SourceSocket(this);
        outputX.setName("X");
        outputY.setName("Y");
        outputZ.setName("Z");
        addOutputSocket(outputX);
        addOutputSocket(outputY);
        addOutputSocket(outputZ);

        String[] listOfFilters ={"RGB","HSV"};
        ComboBox<String> comboBox= new ComboBox<>(FXCollections.observableArrayList(listOfFilters));
        comboBox.setOnAction(e -> {
                    if(comboBox.getValue().equals("RGB")){
                        separator = RGBSeparator;
                        outputX.setName("R");
                        outputY.setName("G");
                        outputZ.setName("B");
                    }
                    if(comboBox.getValue().equals("HSV")){
                        separator = HSVSeparator;
                        outputX.setName("H");
                        outputY.setName("S");
                        outputZ.setName("V");
                    }
                });
        topPane.getChildren().add(comboBox);

        title.setText("Separate");
    }

    @Override
    public void compute() {
        ready = true;
        Components c = separator.separate(input.getContent());
        outputX.setContent(c.X);
        outputY.setContent(c.Y);
        outputZ.setContent(c.Z);
    }

    @Override
    public boolean checkInput(){
        return input.getContent()!=null;
    }

    public static class Components { public BufferedImage X, Y, Z; }

    /***
     * Abstract interface for splitting image into components
     */
    public interface Separator {
        default Components separate( BufferedImage in ) {
            Components res = new Components();
            res.X = new BufferedImage(in.getWidth(),in.getHeight(),in.getType());
            res.Y = new BufferedImage(in.getWidth(),in.getHeight(),in.getType());
            res.Z = new BufferedImage(in.getWidth(),in.getHeight(),in.getType());
            for (int y = 0; y < in.getHeight(); y++) {
                for (int x = 0; x < in.getWidth(); x++) {
                    res.X.setRGB(x,y,getX(in.getRGB(x,y)));
                    res.Y.setRGB(x,y,getY(in.getRGB(x,y)));
                    res.Z.setRGB(x,y,getZ(in.getRGB(x,y)));
                }
            }
            return res;
        }
        int getX(int col);
        int getY(int col);
        int getZ(int col);
    }

    public static Separator RGBSeparator = new Separator() {
        @Override public int getX(int col) {
            int res = col & 0x00ff0000;
            return res | res >> 8 | res >> 16;
        }
        @Override public int getY(int col) {
            int res = col & 0x0000ff00;
            return res << 8 | res  | res >> 8;
        }
        @Override public int getZ(int col) {
            int res = col & 0x000000ff;
            return res << 16 | res << 8 | res;
        }
    };

    public static Separator HSVSeparator = new Separator() {
        @Override public int getX(int col) {
            int r = (col & 0x00ff0000) >> 16;
            int g = (col & 0x0000ff00) >> 8;
            int b = (col & 0x000000ff);
            int v = max(r,max(g,b));
            int c = v - min(r,min(g,b));
            int res = (int)((c == 0)? 0 : (v==r)? 42.5*(g-b)/c: (v==g)? 85+42.5*(b-r)/c : 170+42.5*(r-g)/c);
            if( res < 0 ) res = res + 255;
            return res << 16 | res << 8 | res;
        }
        @Override public int getY(int col) {
            int r = (col & 0x00ff0000) >> 16;
            int g = (col & 0x0000ff00) >> 8;
            int b = (col & 0x000000ff);
            int v = max(r,max(g,b));
            int c = v - min(r,min(g,b));
            int res = ( v == 0 )? 0 : c*255/v;
            return res << 16 | res << 8 | res;
        }
        @Override public int getZ(int col) {
            int r = (col & 0x00ff0000) >> 16;
            int g = (col & 0x0000ff00) >> 8;
            int b = (col & 0x000000ff);
            int res = max(r,max(g,b));
            return res << 16 | res << 8 | res;
        }
    };

}
