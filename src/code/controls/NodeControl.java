package code.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class NodeControl extends AnchorPane {
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


    double relativeX;
    double relativeY;

    public NodeControl() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NodeControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }
        this.setOnMousePressed(me -> {
            this.relativeX = this.getLayoutX() - me.getSceneX();
            this.relativeY = this.getLayoutY() - me.getSceneY();
            this.setCursor(Cursor.MOVE);
        });
        this.setOnMouseDragged( me -> {
            this.setLayoutX(me.getSceneX()+relativeX);
            this.setLayoutY(me.getSceneY()+relativeY);
        });
        this.setOnMouseEntered( me -> this.setCursor(Cursor.HAND));
        this.setOnMouseReleased( me -> this.setCursor(Cursor.HAND));
    }
}
