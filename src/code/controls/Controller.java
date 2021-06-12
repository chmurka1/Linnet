package code.controls;

import code.descriptions.DescriptionWindow;
import code.files.FileFormatException;
import code.files.FileRead;
import code.files.FileWrite;
import code.graph.Compute;
import code.projectifiles.Loader;
import code.projectifiles.Saver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
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
    @FXML
    MenuItem saveProject;
    @FXML
    MenuItem loadProject;

    private final FileChooser fileChooser = new FileChooser();

    public Controller() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layout.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }

        canvas.controller = this;

        computeOutput.setOnAction( e-> Compute.compute(canvas));

        clearCanvas.setOnAction( e-> Compute.fullClear(canvas));

        descriptions.setOnAction(e -> new DescriptionWindow().showDescriptionWindow());

        saveProject.setOnAction( e-> {
                File file = fileChooser.showSaveDialog(null);
                if (file != null) {
                    try {
                        Saver.save(canvas,file);
                    } catch (IOException exc) {
                        System.out.println("cannot write");
                    }
                }
            });

        loadProject.setOnAction( e-> {
            File file = fileChooser.showOpenDialog(null);
            if(file!=null){
                try{
                    Loader.load(canvas,file);
                } catch (Exception exc) {
                    canvas.clear();
                    System.out.println("cannot load");
                }
            }
        });
    }
}
