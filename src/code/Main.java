package code;

import code.controls.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private Image icon;

    public Main() { icon = new Image(Main.class.getResource("images/linnet64.png").toExternalForm()); }

    @Override
    public void start(Stage primaryStage){
        Controller controller = new Controller();
        primaryStage.setTitle("Linnet");
        primaryStage.setScene(new Scene(controller, 800, 500));
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
