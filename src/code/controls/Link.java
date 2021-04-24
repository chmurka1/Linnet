package code.controls;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.geometry.Bounds;
import javafx.scene.shape.Line;

public class Link extends Line {

    private ObjectBinding<Bounds> canvasSourceBoundsBinding;
    private ObjectBinding<Bounds> canvasTargetBoundsBinding;

    public Link( Canvas canvas, NodeControl sourceNode, NodeControl targetNode, Socket source, Socket target)   {
        super();
        canvasSourceBoundsBinding = Bindings.createObjectBinding(() -> {
                    Bounds localBound = source.getBoundsInLocal();
                    Bounds sceneBound = source.localToScene(localBound);
                    Bounds canvasBound = canvas.sceneToLocal(sceneBound);
                    return canvasBound;
                }, source.boundsInLocalProperty(), source.localToSceneTransformProperty(),
                canvas.localToSceneTransformProperty());
        canvasTargetBoundsBinding = Bindings.createObjectBinding(() -> {
                    Bounds localBound = source.getBoundsInLocal();
                    Bounds sceneBound = source.localToScene(localBound);
                    Bounds canvasBound = canvas.sceneToLocal(sceneBound);
                    return canvasBound;
                }, source.boundsInLocalProperty(), source.localToSceneTransformProperty(),
                canvas.localToSceneTransformProperty());
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
    }
}
