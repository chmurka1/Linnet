package code.controls;

        import code.controls.AbstractNode;
        import code.controls.Canvas;
        import code.controls.SourceSocket;
        import code.files.FileFormatException;
        import code.files.FileRead;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.control.Button;
        import javafx.scene.layout.Pane;
        import javafx.stage.FileChooser;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.IOException;

public class InputNode extends AbstractNode {
    @FXML
    Pane titlePane;
    @FXML
    public SourceSocket s1out;
    @FXML
    Button button;
    @FXML
    Button view;

    final FileChooser fileChooser = new FileChooser();


    public InputNode(Canvas canvas) {
        super(canvas);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InputNode.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }

        s1out.node=this; s1out.setName("out1");

        button.setOnAction(
                e -> {
                    File file = fileChooser.showOpenDialog(null);
                    if(file!=null){
                        try{
                            ready = true;
                            out1= FileRead.read(file);
                            s1out.setContent(out1);
                        //    System.out.println(out1.getColorModel());
                            titlePane.setStyle("-fx-background-color: #90ee90;");
                        }catch (FileFormatException exc){
                            clear();
                            System.out.println("not an image");
                            titlePane.setStyle("-fx-background-color: #ff0000;");
                        }catch (FileNotFoundException exc){
                            clear();
                            System.out.println("cannot read");
                            titlePane.setStyle("-fx-background-color: #ff0000;");
                        }
                    }
                });

        view.setOnAction(
                e -> {
                    if (out1 == null) {
                        System.out.println("no image loaded");
                    }
                    this.canvas.controller.viewport.setImage(AbstractNode.convertToFxImage(this.out1));
                });
    }

    @Override
    public void compute() {
        //do nothing
    }

    @Override
    public void clear() {
        ready = false;
        out1 = null;
        s1out.setContent(null);
        titlePane.setStyle("-fx-background-color: #e0ffff");
    }
}
