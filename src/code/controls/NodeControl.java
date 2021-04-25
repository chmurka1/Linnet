package code.controls;

import code.filters.BrightnessAdjustor;
import code.filters.EmptyFilter;
import code.filters.Filters;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class NodeControl extends AbstractNode {

    @FXML
    public Socket s1in;
    @FXML
    public Socket s2in;
    @FXML
    public Socket s1out;
    @FXML
    public Socket s2out;
    @FXML
    Pane topPane;

    public NodeControl(Canvas canvas) {
        super(canvas);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NodeControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }
        s1in.node=this; s2in.node=this;
        s1in.id="in1"; s2in.id="in2";
        s1out.node=this; s2out.node=this;
        s1out.id="out1"; s2out.id="out2";

        String[] listOfFilters ={"empty filter","brighten image","darken image","transfer brightness"};
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
                }
        );
        topPane.getChildren().add(comboBox);
    }
}
