package code.controls;

import code.generators.BorderGenerator;
import code.generators.NoiseGenerator;
import code.generators.PlainGenerator;
import code.generators.VignetteGenerator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;


public class GeneratorNode extends AbstractNode {
    public SourceSocket output;
    Button setButton;
    Button view;
    TextField textFieldX;
    TextField textFieldY;
    TextField textFieldColor;
    ComboBox<String> comboBox;

    public GeneratorNode(Canvas canvas) {
        super(canvas);
        output = new SourceSocket(this);
        output.setName("Output");
        addOutputSocket(output);

        String[] listOfOptions={"plain", "border","noise","vignette"};

        comboBox= new ComboBox<>(FXCollections.observableArrayList(listOfOptions));
        topPane.getChildren().add(comboBox);

        GridPane colorPane=new GridPane();
        colorPane.add(new Label("color in hex:"),0,1);
        textFieldColor=new TextField();
        textFieldColor.setMaxWidth(70);
        colorPane.add(textFieldColor,1,1);
        topPane.getChildren().add(colorPane);

        GridPane resolutionPane=new GridPane();
        resolutionPane.add(new Label("width:"),0,1);
        resolutionPane.add(new Label("height:"),0,2);
        textFieldX=new TextField();
        textFieldX.setMaxWidth(100);
        textFieldY=new TextField();
        textFieldY.setMaxWidth(100);
        resolutionPane.add(textFieldX,1,1);
        resolutionPane.add(textFieldY,1,2);
        topPane.getChildren().add(resolutionPane);

        setButton=new Button("Set");
        setButton.setOnAction(e ->setGenerator(comboBox.getValue()));
        buttons.getChildren().add(setButton);

        view = new Button();
        view.setText("View");
        view.setOnAction(
                e -> {
                    if (output.getContent() == null) {
                        System.out.println("no image loaded");
                    }
                    ViewportWindow.showImage(AbstractNode.convertToFxImage(output.getContent()));
                });
        buttons.getChildren().add(view);

        title.setText("Generator");
    }

    public GeneratorNode(Canvas canvas, String string ) throws Exception {
        this(canvas);
        String [] arr = string.split("\\|");
        textFieldColor.setText(arr[1]);
        textFieldX.setText(arr[2]);
        textFieldY.setText(arr[3]);
        setGenerator(arr[0]);
        comboBox.setValue(arr[0]);
    }

    @Override
    public String toString() {
        return comboBox.getValue() + "|" +
                textFieldColor.getText() +
                "|" + textFieldX.getText() +
                "|" + textFieldY.getText();
    }

    private int getX(){
        CharSequence seq=textFieldX.getCharacters();
        int coefficient=0;
        try{
            coefficient=Integer.parseInt(seq.toString());
        }catch (NumberFormatException exc){
            System.out.println("not a number");
        }
        return coefficient;
    }

    private int getY(){
        CharSequence seq=textFieldY.getCharacters();
        int coefficient=0;
        try{
            coefficient=Integer.parseInt(seq.toString());
        }catch (NumberFormatException exc){
            System.out.println("not a number");
        }
        return coefficient;
    }

    @Override
    public void compute() {
        ready=true;
        //do nothing
    }

    @Override
    public boolean checkInput(){
        //do nothing
        return true;
    }

    @Override
    public void clear() {
        //do nothing
    //    ready = false;
    //    output.setContent(null);
    }

    public void setGenerator( String name ) {
        if (name.equals("plain")) {
            new PlainGenerator(getX(), getY()).generate(this, textFieldColor.getText());
        }
        if (name.equals("border")) {
            new BorderGenerator(getX(), getY()).generate(this, textFieldColor.getText());
        }
        if (name.equals("noise")) {
            new NoiseGenerator(getX(), getY()).generate(this, textFieldColor.getText());
        }
        if (name.equals("vignette")) {
            new VignetteGenerator(getX(), getY()).generate(this, textFieldColor.getText());
        }
    }
}
