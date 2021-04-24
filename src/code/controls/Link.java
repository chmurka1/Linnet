package code.controls;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Link extends Line {

    Canvas canvas;
    private void removeLinkFromCanvas() {
            canvas.removeLink(this);
    }

    final private ObjectBinding<Bounds> canvasSourceBoundsBinding;
    final private ObjectBinding<Bounds> canvasTargetBoundsBinding;

    public Link(Canvas canvas, AbstractNode sourceNode, AbstractNode targetNode, Socket source, Socket target)   {
        super();
        this.canvas = canvas;
        canvasSourceBoundsBinding = Bindings.createObjectBinding(() -> {
                    Bounds localBound = source.getBoundsInLocal();
                    Bounds sceneBound = source.localToScene(localBound);
                    return sourceNode.sceneToLocal(sceneBound);
                }, source.boundsInLocalProperty(), source.localToSceneTransformProperty(),
                sourceNode.localToSceneTransformProperty());
        canvasTargetBoundsBinding = Bindings.createObjectBinding(() -> {
                    Bounds localBound = target.getBoundsInLocal();
                    Bounds sceneBound = target.localToScene(localBound);
                    return targetNode.sceneToLocal(sceneBound);
                }, target.boundsInLocalProperty(), target.localToSceneTransformProperty(),
                targetNode.localToSceneTransformProperty());
        this.setStrokeWidth(3.0);
        this.startXProperty().bind(
                Bindings.add(sourceNode.layoutXProperty(),
                            Bindings.createDoubleBinding(
                                () ->  canvasSourceBoundsBinding.get().getCenterX(),
                                    canvasSourceBoundsBinding)));
        this.startYProperty().bind(
                Bindings.add(sourceNode.layoutYProperty(),
                            Bindings.createDoubleBinding(
                                () ->  canvasSourceBoundsBinding.get().getCenterY(),
                                canvasSourceBoundsBinding)));
        this.endXProperty().bind(
                Bindings.add(targetNode.layoutXProperty(),
                        Bindings.createDoubleBinding(
                        () ->  canvasTargetBoundsBinding.get().getCenterX(),
                        canvasTargetBoundsBinding)));
        this.endYProperty().bind(
                Bindings.add(targetNode.layoutYProperty(),
                        Bindings.createDoubleBinding(
                                () ->  canvasTargetBoundsBinding.get().getCenterY(),
                                canvasTargetBoundsBinding)));
        this.setOnMousePressed( me -> removeLinkFromCanvas() );
        this.setOnMouseEntered( me -> this.setCursor(Cursor.HAND));
    }
}
