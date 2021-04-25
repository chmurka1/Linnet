package code.controls;

import code.graph.Compute;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class Controller extends GridPane {
    @FXML
    Canvas canvas;

    @FXML
    MenuItem saveOption;

    public Controller() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }

        saveOption.setOnAction( e-> {
            Compute.compute(canvas);
        });
    }
}
