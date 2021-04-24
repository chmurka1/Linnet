package code.controls;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class Socket extends Pane {
    public Socket() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Socket.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }
    }
}
