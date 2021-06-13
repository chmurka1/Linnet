package code.controls;

import code.filters.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Arrays;

/***
 * Standard node for applying a simple filter on an image
 */
public class FilterNode extends AbstractNode {

    Filter filter = new EmptyFilter();

    @FXML
    Pane topPane;

    boolean isExtended;
    TextField textField;
    Button setButton;
    GridPane gridPane;
    ComboBox<String> comboBox;

    public FilterNode(Canvas canvas) {
        super(canvas);
        input1 = new TargetSocket(this);
        input1.setName("Input");
        addInputSocket(input1);
        output1 = new SourceSocket(this);
        output1.setName("Output");
        addOutputSocket(output1);

        isExtended=false;
        textField=new TextField();
        textField.setMaxWidth(100);
        setButton=new Button("Set");
        gridPane=new GridPane();
        gridPane.add(textField,0,0);
        gridPane.add(setButton,1,0);



        String[] listOfFilters = Arrays.stream(NamesOfFilters.values()).map(x -> x.displayName).toArray(String[]::new);

        comboBox= new ComboBox<>(FXCollections.observableArrayList(listOfFilters));
        comboBox.setOnAction(e -> { try{ setFilter(comboBox.getValue()); } catch(IOException exc) {/*this shouldn't happen*/} });
        topPane.getChildren().add(comboBox);

        title.setText("Filter");
    }

    public FilterNode(Canvas canvas, String string ) throws Exception {
        this(canvas);
        String [] arr = string.split("\\|");
        setFilter(arr[0]);
        comboBox.setValue(arr[0]);
        if( isExtended ) {
            textField.setText(arr[1]);
            setButton.getOnAction().handle(new ActionEvent());
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(comboBox.getValue());
        if( isExtended ) {
            s.append("|");
            s.append(textField.getText());
        }
        return s.toString();
    }

    @Override
    public void compute() {
        filter.apply(this);
        ready = true;
    }

    @Override
    public boolean checkInput(){
        return input1.getContent()!=null;
    }

    private void extend(){
        textField.clear();
        if(!isExtended) {
            topPane.getChildren().add(gridPane);
            isExtended = true;
        }
    }

    private void shrink(){
        if(isExtended){
            topPane.getChildren().remove(gridPane);
            isExtended=false;
        }
    }

    private int getCoefficient(){
        CharSequence seq=textField.getCharacters();
        int coefficient=0;
        try{
            coefficient=Integer.parseInt(seq.toString());
        }catch (NumberFormatException exc){
            System.out.println("not a number");
        }
        return coefficient;
    }

    public enum NamesOfFilters {
        EMPTY("empty filter"),
        BRIGHTEN("brighten image"),
        DARKEN("darken image"),
        BLACKNWHITE("black and white"),
        SHARPEN("sharpen image"),
        CONTRAST("change contrast"),
        SATURATION("change saturation"),
        HORIZONTAL("horizontal blur"),
        VERTICAL("vertical blur"),
        GAUSSIAN("gaussian blur"),
        TOP("trim top"),
        WIDTH("set new width"),
        HEIGHT("set new height");

        public final String displayName;
        NamesOfFilters(String displayName) { this.displayName = displayName; }
    }

    public void setFilter( String name ) throws IOException {
        if(name.equals(NamesOfFilters.EMPTY.displayName)){
            shrink();
            filter=Filters.empty;
        }
        else if(name.equals(NamesOfFilters.BRIGHTEN.displayName)){
            shrink();
            filter=Filters.brightenImage;
        }
        else if(name.equals(NamesOfFilters.DARKEN.displayName)){
            shrink();
            filter= Filters.darkenImage;
        }
        else if(name.equals(NamesOfFilters.BLACKNWHITE.displayName)){
            shrink();
            filter = Filters.saturate(-100);
        }
        // advised range from -100 to 100
        else if(name.equals(NamesOfFilters.SHARPEN.displayName)){
            extend();
            setButton.setOnAction(ee -> filter = Filters.sharpen(getCoefficient()) );
        }
        // advised range from -100 to +100
        else if(name.equals(NamesOfFilters.CONTRAST.displayName)){
            extend();
            setButton.setOnAction(ee -> filter = Filters.contrast(getCoefficient()) );
        }
        // advised range from -100 to +100
        else if(name.equals(NamesOfFilters.SATURATION.displayName)){
            extend();
            setButton.setOnAction(ee -> filter = Filters.saturate(getCoefficient()) );
        }
        //coefficient in per mille
        // advised range from -100 to +100. If negative, takes abs value
        else if(name.equals(NamesOfFilters.HORIZONTAL.displayName)){
            extend();
            setButton.setOnAction(ee -> filter = new HorizontalBlur(getCoefficient()) );
        }
        else if(name.equals(NamesOfFilters.VERTICAL.displayName)){
            extend();
            setButton.setOnAction(ee -> filter = new VerticalBlur(getCoefficient()) );
        }
        //coefficient in %
        else if(name.equals(NamesOfFilters.TOP.displayName)){
            extend();
            setButton.setOnAction(ee -> filter = new TrimTop(getCoefficient()) );
        }
        else if(name.equals(NamesOfFilters.GAUSSIAN.displayName)){
            extend();
            setButton.setOnAction(ee -> filter = new GaussianBlur(getCoefficient()) );
        }
        else if(name.equals(NamesOfFilters.WIDTH.displayName)){
            extend();
            setButton.setOnAction(ee -> filter = new ResizeWidth(getCoefficient()) );
        }
        else if(name.equals(NamesOfFilters.HEIGHT.displayName)){
            extend();
            setButton.setOnAction(ee -> filter = new ResizeHeight(getCoefficient()) );
        }
        else throw new IOException();
    }
}
