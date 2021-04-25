package code;

import code.controls.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        Controller controller = new Controller();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(controller, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
