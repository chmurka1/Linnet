package code.controls;

import code.files.FileFormatException;
import code.files.FileRead;
import code.files.FileWrite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OutputNode extends AnchorPane {
    @FXML
    private Label title;

    @FXML
    Socket s1;

    @FXML
    Button button;

    double relativeX;
    double relativeY;

    final FileChooser fileChooser = new FileChooser();

    BufferedImage img=null;

    public OutputNode() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OutputNode.fxml"));
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

        button.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        if(img==null){
                            System.out.println("no input");
                        }
                        //later probably else
                        File file = fileChooser.showSaveDialog(null);
                        if(file!=null){
                            try{
                                FileWrite.write(img,"jpg",file);
                                //maybe later allow to choose file format
                            }catch (IOException exc){
                                System.out.println("cannot write");
                            }
                        }
                    }
                });
    }
}
