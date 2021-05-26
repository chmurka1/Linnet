package code.controls;

import code.filters.HorizontalBlur;
import code.filters.Filter;
import code.filters.Filters;
import code.filters.TrimTop;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

/***
 * Standard node for applying a simple filter on an image
 */
public class FilterNode extends AbstractNode {

    Filter filter;

    @FXML
    Pane topPane;

    public FilterNode(Canvas canvas) {
        super(canvas);
        input1 = new TargetSocket(this);
        input1.setName("Input");
        addInputSocket(input1);
        output1 = new SourceSocket(this);
        output1.setName("Output");
        addOutputSocket(output1);

        String[] listOfFilters ={"empty filter","brighten image","darken image", "black and white","more colorful","sharpen","contrast","saturate","horizontal blur","trim top"};
        ComboBox<String> comboBox= new ComboBox<>(FXCollections.observableArrayList(listOfFilters));
        comboBox.setOnAction(e -> {
            if(comboBox.getValue().equals("empty filter")){
                filter=Filters.empty;
            }
            if(comboBox.getValue().equals("brighten image")){
                filter=Filters.brightenImage;
            }
            if(comboBox.getValue().equals("darken image")){
                filter= Filters.darkenImage;
            }
            if(comboBox.getValue().equals("black and white")){}// filter = blackAndWhite;
            if(comboBox.getValue().equals("more colorful")){}// filter = saturate;

            // advised range from -32 to +256
            if(comboBox.getValue().equals("sharpen")){
                int coefficient = -32;
                //coefficient = ...
                filter = Filters.sharpen(coefficient);
            }
            // advised range from -128 to +128
            if(comboBox.getValue().equals("contrast")){
                int coefficient = 128;
                //coefficient = ...
                filter = Filters.contrast(coefficient);
            }
            // advised range from -128 to +128
            if(comboBox.getValue().equals("saturate")){
                int coefficient = 128;
                //coefficient = ...
                filter = Filters.saturate(coefficient);
            }
            //advised range: 5-10% of image width
            if(comboBox.getValue().equals("horizontal blur")){
                int coefficient = 100;//in pixels
                //coefficient = ...

                filter = new HorizontalBlur(coefficient);
            }
            if(comboBox.getValue().equals("trim top")){
                int coefficient = 10;//in %
                //coefficient = ...
                filter = new TrimTop(coefficient);
            }
        });
        topPane.getChildren().add(comboBox);

        title.setText("Filter");
    }

    @Override
    public void compute() {
        filter.apply(this);
        ready = true;
    }

    @Override
    public boolean checkInput(){
        return input1.getContent()!=null;
    }

    /*public interface Filter {
        default BufferedImage filter( BufferedImage in ) {
            BufferedImage res = new BufferedImage(in.getWidth(),in.getHeight(),in.getType());
            for (int y = 0; y < in.getHeight(); y++) {
                for (int x = 0; x < in.getWidth(); x++) {
                    res.setRGB(x,y,get(in.getRGB(x,y)));
                }
            }
            return res;
        }
        int get(int p);
    }

    public static Filter blackAndWhite = p -> {
        int res = (((p & 0x00ff0000) >> 16) + ((p & 0x0000ff00) >> 8) + (p & 0x000000ff)) / 3;
        return res << 16 | res << 8 | res;
    };

    public static Filter saturate = p -> {
        int i = (((p & 0x00ff0000) >> 16) + ((p & 0x0000ff00) >> 8) + (p & 0x000000ff)) / 3;
        int r = Math.max(Math.min(2 * ((p & 0x00ff0000) >> 16) - i, 255), 0);
        int g = Math.max(Math.min(2 * ((p & 0x0000ff00) >> 8) - i, 255), 0);
        int b = Math.max(Math.min(2 * (p & 0x000000ff) - i, 255), 0);
        return r << 16 | g << 8 | b;
    };*/
}
