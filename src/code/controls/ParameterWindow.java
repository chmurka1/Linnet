package code.controls;

import code.filters.Filter;
import code.filters.Filters;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ParameterWindow {
    /*String description;
    ParameterWindow(String description){
        this.description=description;
    }*/
    public static void showWindow(String description, Filter filter) throws InterruptedException {
        Stage stage=new Stage();
        AtomicInteger res= new AtomicInteger(0);
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setMaxWidth(300);
        pane.setMaxHeight(200);
        Text text=new Text(description);
        pane.add(text,0,0);
        TextField textField = new TextField();
        pane.add(textField,0,1);
        Button button=new Button("Set");
        pane.add(button,0,2);

        button.setOnAction(e -> {
            CharSequence seq=textField.getCharacters();
            int value=0;
            try{
                value=Integer.parseInt(seq.toString());
            }catch (NumberFormatException exc){
                System.out.println("not a number");
            }
            System.out.println(value);
            stage.close();
            //how to send this value to node???
        });
        //Stage stage=new Stage();
        stage.setScene(new Scene(pane));
        stage.setWidth(300);
        stage.setHeight(200);
        stage.show();
    }
}
