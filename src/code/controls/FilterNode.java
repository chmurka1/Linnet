package code.controls;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import java.awt.image.BufferedImage;

/***
 * Standard node for applying one-to-one filters with no additional parameters
 */
public class FilterNode extends AbstractNode {
    public TargetSocket input;
    public SourceSocket output;

    OneToOneFilter filter;

    public FilterNode(Canvas canvas) {
        super(canvas);
        input = new TargetSocket(this);
        input.setName("Input");
        addInputSocket(input);
        output = new SourceSocket(this);
        output.setName("Output");
        addOutputSocket(output);

        String[] listOfFilters ={"empty filter","brighten image","darken image", "black and white","more colorful"};
        ComboBox<String> comboBox= new ComboBox<>(FXCollections.observableArrayList(listOfFilters));
        comboBox.setOnAction(e -> {
            if(comboBox.getValue().equals("empty filter")){
                ;
            }
            if(comboBox.getValue().equals("brighten image")){

            }
            if(comboBox.getValue().equals("darken image")){
            //    FilterNode.this.filter= Filters.darkenImage;
            }
            if(comboBox.getValue().equals("black and white")) filter = blackAndWhite;
            if(comboBox.getValue().equals("more colorful")) filter = saturate;
        });
        topPane.getChildren().add(comboBox);

        title.setText("Filter");
    }

    @Override
    public void compute() {
        ready = true;
        output.setContent(filter.filter(input.getContent()));
    }

    @Override
    public boolean checkInput(){
        return input.getContent()!=null;
    }

    public interface OneToOneFilter {
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

    public static OneToOneFilter blackAndWhite = p -> {
        int res = (((p & 0x00ff0000) >> 16) + ((p & 0x0000ff00) >> 8) + (p & 0x000000ff)) / 3;
        return res << 16 | res << 8 | res;
    };

    public static OneToOneFilter saturate = p -> {
        int i = (((p & 0x00ff0000) >> 16) + ((p & 0x0000ff00) >> 8) + (p & 0x000000ff)) / 3;
        int r = Math.max(Math.min(2 * ((p & 0x00ff0000) >> 16) - i, 255), 0);
        int g = Math.max(Math.min(2 * ((p & 0x0000ff00) >> 8) - i, 255), 0);
        int b = Math.max(Math.min(2 * (p & 0x000000ff) - i, 255), 0);
        return r << 16 | g << 8 | b;
    };
}
