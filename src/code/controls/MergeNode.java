package code.controls;

import code.filters.BlenderOfTwo;
import code.filters.BlendingRatioFunctions;
import code.filters.BrightnessAdjustor;
import code.filters.EmptyFilter;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.util.Arrays;

/***
 * Standard node for merging two images using a filter
 */
public class MergeNode extends AbstractNode {


    ComboBox<String> comboBox;

    public MergeNode(Canvas canvas) {
        super(canvas);
        input1 = new TargetSocket(this);
        input2 = new TargetSocket(this);
        input1.setName("in1");
        input2.setName("in2");
        addInputSocket(input1);
        addInputSocket(input2);
        output1 = new SourceSocket(this);
        output1.setName("out1");
        addOutputSocket(output1);

        this.filter = new EmptyFilter();

        String[] listOfFilters = Arrays.stream(NamesOfMergeFilters.values()).map(x -> x.displayName).toArray(String[]::new);

        comboBox= new ComboBox<>(FXCollections.observableArrayList(listOfFilters));
        comboBox.setOnAction(e -> setMerge(comboBox.getValue()));
        topPane.getChildren().add(comboBox);

        title.setText("Merge");
    }

    public MergeNode(Canvas canvas, String string ) throws Exception {
        this(canvas);
        String [] arr = string.split("\\|");
        setMerge(arr[0]);
        comboBox.setValue(arr[0]);
    }

    @Override
    public String toString() {
        return comboBox.getValue();
    }

    @Override
    public void compute() {
        filter.apply(this);
        ready = true;
    }

    @Override
    public boolean checkInput(){
        return filter.checkInput(this);
    }


    public enum NamesOfMergeFilters {
        TRANSFER("transfer brightness"),
        BRIGHTNESS("blend by brightness"),
        DARKNESS("blend by darkness"),
        SATURATION("blend by saturation"),
        GREENSCREEN("green screen");

        public String displayName;
        NamesOfMergeFilters(String displayName) {
            this.displayName = displayName;
        }
    }

    public void setMerge(String name) {
        if(name.equals(NamesOfMergeFilters.TRANSFER.displayName)){
            filter = new BrightnessAdjustor();
        }
        if(name.equals(NamesOfMergeFilters.BRIGHTNESS.displayName)){
            filter = new BlenderOfTwo(BlendingRatioFunctions.blendByBrightness);
            // the pictures from input must have the same height and width

            // takes first picture as foreground,
            //      if pixel of foreground is bright, output pixel will be almost the foreground one
            //      if foreground pixel is dark output pixel will be almost the background one
            //      otherwise output pixel is sth in between the foreground one and background one
        }
        if(name.equals(NamesOfMergeFilters.DARKNESS.displayName)){
            filter = new BlenderOfTwo(BlendingRatioFunctions.blendByDarkness);
        }
        if(name.equals(NamesOfMergeFilters.SATURATION.displayName)){
            filter = new BlenderOfTwo(BlendingRatioFunctions.blendBySaturation);
        }
        if(name.equals(NamesOfMergeFilters.GREENSCREEN.displayName)){
            filter = new BlenderOfTwo(BlendingRatioFunctions.greenScreen);
        }
    }
}
