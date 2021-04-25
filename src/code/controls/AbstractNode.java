package code.controls;

import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public abstract class AbstractNode extends AnchorPane {

    Canvas canvas;

    double relativeX;
    double relativeY;


    BufferedImage in1;
    BufferedImage in2;
    BufferedImage out1;
    BufferedImage out2;

    //some filter type

    public AbstractNode(Canvas canvas){
        super();
        this.canvas=canvas;
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
    }
}
