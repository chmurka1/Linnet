package code.controls;

import code.Main;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;


public class ViewportWindow {
    public static void showImage(Image img){
        Pane pane = new Pane();
        pane.setMaxWidth(1000);
        pane.setMaxHeight(600);
        ImageView imageview = new ImageView();
        imageview.setImage(img);
        imageview.setFitWidth(1000);
        imageview.setFitHeight(600);
        pane.getChildren().add(imageview);

        Image icon = new Image(Main.class.getResource("images/linnet64.png").toExternalForm());

        Stage stage=new Stage();
        stage.setScene(new Scene(pane));
        stage.getIcons().add(icon);
        stage.show();

    }
}
