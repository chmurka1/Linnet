package code.controls;

import code.files.FileWrite;
import code.graph.Compute;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class OutputNode extends AbstractNode {
    TargetSocket input;
    private final FileChooser fileChooser = new FileChooser();

    public OutputNode(Canvas canvas) {
        super(canvas);
        input = new TargetSocket(this);
        input.setName("Input");
        addInputSocket(input);

        Button button = new Button();
        button.setText("Save");
        button.setOnAction(
                e -> {
                    if (input.getContent() == null) {
                        System.out.println("no input");
                    }
                    //later probably else
                    File file = fileChooser.showSaveDialog(null);
                    if (file != null) {
                        try {
                            FileWrite.write(input.getContent(), "jpg", file);
                            //maybe later allow to choose file format
                        } catch (IOException exc) {
                            System.out.println("cannot write");
                        }
                    }
                });
        Button view = new Button();
        view.setText("View");
        view.setOnAction(
                e -> {
                    Compute.compute(canvas);
                    if (input.getContent() == null) {
                        System.out.println("no input");
                    }
                    ViewportWindow.showImage(AbstractNode.convertToFxImage(this.input.getContent()));
                //    this.canvas.controller.viewport.setImage(AbstractNode.convertToFxImage(this.input.getContent()));
                });
        buttons.getChildren().add(button);
        buttons.getChildren().add(view);

        title.setText("Image output");
        topPane.setStyle("-fx-background-color: #fff8dc;");
    }

    public void colorTitlePane(){
        topPane.setStyle("-fx-background-color: #ffd700;");
    }

    @Override
    public void compute() {
        if(input.getContent() != null) {
            System.out.println("output ready");
            colorTitlePane();
        }
        if ( input.getContent() == null ) {
            System.out.println("no input");
        }
    }

    @Override()
    public boolean checkInput(){
        return input.getContent()!=null;
    }

    @Override
    public void clear() {
        ready = false;
        topPane.setStyle("-fx-background-color: #fff8dc;");
    }
}
