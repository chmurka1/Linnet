package code.controls;

import code.filters.BlenderOfTwo;
import code.filters.BrightnessAdjustor;
import code.filters.EmptyFilter;
import code.filters.Filters;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class NodeControl extends AbstractNode {
    @FXML
    public VBox inputs;
    @FXML
    public VBox outputs;

    public TargetSocket s1in;
    public TargetSocket s2in;
    public SourceSocket s1out;
    public SourceSocket s2out;

    @FXML
    Pane topPane;

    public NodeControl(Canvas canvas) {
        super(canvas);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("abstractNode.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }
        s1in = new TargetSocket(this);
        s2in = new TargetSocket(this);
        s1in.setName("in1");
        s2in.setName("in2");
        inputs.getChildren().add(s1in);
        inputs.getChildren().add(s2in);
        s1out = new SourceSocket(this);
        s2out = new SourceSocket(this);
        s1out.setName("out1");
        s2out.setName("out2");
        outputs.getChildren().add(s1out);
        outputs.getChildren().add(s2out);

        this.filter = new EmptyFilter();

        String[] listOfFilters ={"empty filter","brighten image","darken image","transfer brightness",
                                    "black and white","blend pictures","more colorful"};
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
                    if(comboBox.getValue().equals("black and white")){
                        NodeControl.this.filter = Filters.blackAndWhite;
                    }
                    if(comboBox.getValue().equals("blend pictures")){
                        NodeControl.this.filter = new BlenderOfTwo();
                        // the pictures from input must have the same height and width

                        // takes first picture as foreground,
                        //      if pixel of foreground is bright, output pixel will be almost the foreground one
                        //      if foreground pixel is dark output pixel will be almost the background one
                        //      otherwise output pixel is sth in between the foreground one and background one
                    }
                    if(comboBox.getValue().equals("more colorful")){
                        NodeControl.this.filter = Filters.saturate;
                    }
                }
        );
        topPane.getChildren().add(comboBox);
    }

    public void load() {
        in1 = s1in.getContent();
        in2 = s2in.getContent();
    }

    public void transfer() {
        s1out.setContent(out1);
        s2out.setContent(out2);
    }

    @Override
    public void compute() {
        if( ready ) return;
        load();
        if(filter.checkInput(this)){
            filter.apply(this);
            transfer();
            ready = true;
        }
        else throw new RuntimeException();
    }

    @Override
    public void clear() {
        ready=false;
        s1in.clear(); s2in.clear();
        s1out.clear(); s2out.clear();
    }
}
