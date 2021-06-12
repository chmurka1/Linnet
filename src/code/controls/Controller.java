package code.controls;

import code.descriptions.DescriptionWindow;
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
    MenuItem computeOutput;
    @FXML
    MenuItem clearCanvas;
    @FXML
    MenuItem descriptions;

    public Controller() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }

        canvas.controller = this;

        computeOutput.setOnAction( e-> Compute.compute(canvas));

        clearCanvas.setOnAction( e-> Compute.fullClear(canvas));

        descriptions.setOnAction(e -> new DescriptionWindow().showDescriptionWindow());
    }
}
