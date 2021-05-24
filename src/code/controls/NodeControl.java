package code.controls;

import code.filters.BlenderOfTwo;
import code.filters.BrightnessAdjustor;
import code.filters.EmptyFilter;
import code.filters.Filters;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;

import java.io.IOException;


public class NodeControl extends AbstractNode {

    public TargetSocket input1;
    public TargetSocket input2;
    public SourceSocket output1;
    public SourceSocket output2;

    public NodeControl(Canvas canvas) {
        super(canvas);
        input1 = new TargetSocket(this);
        input2 = new TargetSocket(this);
        input1.setName("in1");
        input2.setName("in2");
        addInputSocket(input1);
        addInputSocket(input2);
        output1 = new SourceSocket(this);
        output2 = new SourceSocket(this);
        output1.setName("out1");
        output2.setName("out2");
        addOutputSocket(output1);
        addOutputSocket(output2);

        this.filter = new EmptyFilter();

        String[] listOfFilters ={"empty filter","brighten image","darken image","transfer brightness","blend pictures"};
        ComboBox<String> comboBox= new ComboBox<>(FXCollections.observableArrayList(listOfFilters));
        comboBox.setOnAction(
                e -> {
                    if(comboBox.getValue().equals("empty filter")){
                        NodeControl.this.filter = new EmptyFilter();
                    }
                    if(comboBox.getValue().equals("brighten image")){
                        NodeControl.this.filter = Filters.brightenImage;
                    }
                    if(comboBox.getValue().equals("darken image")){
                        NodeControl.this.filter = Filters.darkenImage;
                    }
                    if(comboBox.getValue().equals("transfer brightness")){
                        NodeControl.this.filter = new BrightnessAdjustor();
                    }
                    if(comboBox.getValue().equals("blend pictures")){
                        NodeControl.this.filter = new BlenderOfTwo();
                        // the pictures from input must have the same height and width

                        // takes first picture as foreground,
                        //      if pixel of foreground is bright, output pixel will be almost the foreground one
                        //      if foreground pixel is dark output pixel will be almost the background one
                        //      otherwise output pixel is sth in between the foreground one and background one
                    }
                }
        );
        topPane.getChildren().add(comboBox);

        title.setText("Node");
    }

    @Override
    public void compute() {
        if( ready ) return;
        if(filter.checkInput(this)){
            filter.apply(this);
            ready = true;
        }
        else throw new RuntimeException();
    }

}
