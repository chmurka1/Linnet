package code.controls;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NodeControl extends AbstractNode {
    @FXML
    private Label title;

    @FXML
    Socket s1;
    @FXML
    Socket s2;
    @FXML
    Socket s3;
    @FXML
    Socket s4;
    @FXML
    Socket s5;
    @FXML
    Socket s6;
    @FXML
    Socket s7;
    @FXML
    Pane topPane;

    public NodeControl(Canvas canvas) {
        super(canvas);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NodeControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }
        s1.node=this; s2.node=this; s3.node=this; s4.node=this;
        s5.node=this; s6.node=this; s7.node=this;

        String listOfFilters[]={"filter1","filter2","filter3"};
        ComboBox comboBox= new ComboBox(FXCollections.observableArrayList(listOfFilters));
        comboBox.setOnAction(
                new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(final ActionEvent e){
                        //TODO
                        //set filter to appropriate type
                        //comboBox.getValue()
                    }
                }
        );
        topPane.getChildren().add(comboBox);
    }
}
