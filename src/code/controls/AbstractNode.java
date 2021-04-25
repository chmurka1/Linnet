package code.controls;

import code.filters.Filter;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;

import java.awt.image.BufferedImage;

public abstract class AbstractNode extends AnchorPane {

    Canvas canvas;

    double relativeX;
    double relativeY;


    public BufferedImage in1;
    public BufferedImage in2;
    public BufferedImage out1;
    public BufferedImage out2;

    public Filter filter;
    public boolean isFilterApplied=false;

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
