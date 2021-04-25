package code.controls;

import code.files.FileWrite;
import code.graph.Compute;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class OutputNode extends AbstractNode {

    @FXML
    Pane titlePane;
    @FXML
    Socket s1in;
    @FXML
    Button button;
    @FXML
    Button view;

    final FileChooser fileChooser = new FileChooser();

    public OutputNode(Canvas canvas) {
        super(canvas);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OutputNode.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }

        s1in.node=this; s1in.id="in1";

        button.setOnAction(
                e -> {
                    if (in1 == null) {
                        System.out.println("no input");
                    }
                    //later probably else
                    File file = fileChooser.showSaveDialog(null);
                    if (file != null) {
                        try {
                            FileWrite.write(in1, "jpg", file);
                            //maybe later allow to choose file format
                        } catch (IOException exc) {
                            System.out.println("cannot write");
                        }
                    }
                });

        view.setOnAction(
                e -> {
                    Compute.compute(canvas);
                    if (in1 == null) {
                        System.out.println("no input");
                    }
                    this.canvas.controller.viewport.setImage(AbstractNode.convertToFxImage(this.in1));
                });
    }

    public void colorTitlePane(){
        titlePane.setStyle("-fx-background-color: #ffd700;");
    }

    public void uncolorTitlePane(){
        titlePane.setStyle("-fx-background-color: #fff8dc;");
    }
}
