package code.controls;
import code.files.FileFormatException;
import code.files.FileRead;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Socket extends VBox {

    @FXML
    Button button;
    AbstractNode node;

    public Socket() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Socket.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }

        button.setStyle(
                "-fx-background-radius: 10em; " +
                        "-fx-min-width: 10px; " +
                        "-fx-min-height: 10px; " +
                        "-fx-max-width: 10px; " +
                        "-fx-max-height: 10px;"
        );

        button.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        if(node.canvas.clickedSocket==null){
                            node.canvas.clickedSocket=Socket.this;
                        }
                        else{
                            node.canvas.addLink(node.canvas.clickedSocket.node,Socket.this.node,
                                    node.canvas.clickedSocket,Socket.this);
                            node.canvas.clickedSocket=null;
                        }
                    }
                });

    }
}
