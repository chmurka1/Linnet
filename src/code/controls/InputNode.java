package code.controls;

 import code.files.FileFormatException;
 import code.files.FileRead;
 import javafx.fxml.FXML;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.control.Button;
 import javafx.scene.control.Label;
 import javafx.scene.layout.Pane;
 import javafx.stage.FileChooser;

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.IOException;

public class InputNode extends AbstractNode {
    public SourceSocket output;
    Button button;
    Button view;

    final FileChooser fileChooser = new FileChooser();


    public InputNode(Canvas canvas) {
        super(canvas);
        output = new SourceSocket(this);
        output.setName("Output");
        addOutputSocket(output);

        button = new Button();
        button.setText("Select image");
        button.setOnAction( e -> {
                    File file = fileChooser.showOpenDialog(null);
                    if(file!=null){
                        try{
                            ready = true;
                            output.setContent(FileRead.read(file));
                            topPane.setStyle("-fx-background-color: #90ee90;");
                        }
                        catch (FileFormatException exc) {
                            clear();
                            System.out.println("not an image");
                            topPane.setStyle("-fx-background-color: #ff0000;");
                        }
                        catch (FileNotFoundException exc) {
                            clear();
                            System.out.println("cannot read");
                            topPane.setStyle("-fx-background-color: #ff0000;");
                        }
                    }
                });
        view = new Button();
        view.setText("View");
        view.setOnAction(
                e -> {
                    if (output.getContent() == null) {
                        System.out.println("no image loaded");
                    }
                    canvas.controller.viewport.setImage(AbstractNode.convertToFxImage(output.getContent()));
                });

        buttons.getChildren().add(button);
        buttons.getChildren().add(view);

        title.setText("Image input");
    }

    @Override
    public void compute() {
        //do nothing
    }

    @Override
    public void clear() {
        ready = false;
        output.setContent(null);
        topPane.setStyle("-fx-background-color: #e0ffff");
    }
}
