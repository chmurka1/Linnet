package code.controls;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Canvas extends Pane {

    @FXML
    NodeControl node1;
    @FXML
    NodeControl node2;

    public Canvas() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Canvas.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.getChildren().add(new Link(this,node1,node2,node1.socket,node2.socket));
    }
}
