package code.controls;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.shape.Line;

/**
 * Class handling connections between source and target sockets
 * it provides methods for making new connection and deleting the existing one
 * must be attached to some canvas
 */
public class Link extends Line {

    final private Canvas canvas;
    final private ObjectBinding<Bounds> canvasSourceBoundsBinding;
    final private ObjectBinding<Bounds> canvasTargetBoundsBinding;
    final SourceSocket source;
    final TargetSocket target;

    /***
     * Creates link and connects source and target sockets if they are in the same canvas but in different nodes
     * Automatically determines a source and a target
     * @throws Exception info why sockets haven't been connected
     */
    public Link(Socket socket1, Socket socket2 )  throws Exception  {
        super();

        if( socket1.node.canvas != socket2.node.canvas ) throw new Exception("Linked sockets cannot belong to different canvases");
        if( socket1.node == socket2.node ) throw new Exception("Linked sockets cannot belong to the same node");
        if( socket1 instanceof SourceSocket && socket2 instanceof TargetSocket ) {
            this.source = (SourceSocket) socket1; this.target = (TargetSocket) socket2;
        }
        else {
            if ( socket2 instanceof SourceSocket && socket1 instanceof TargetSocket ) {
                this.source = (SourceSocket) socket2; this.target = (TargetSocket) socket1;
            }
            else throw new Exception("Linking is only possible for one source and one target");
        }
        this.canvas = socket1.node.canvas;

        this.source.bind(this);
        this.target.bind(this);
        canvasSourceBoundsBinding = Bindings.createObjectBinding(() -> {
                    Bounds localBound = source.button.getBoundsInLocal();
                    Bounds sceneBound = source.button.localToScene(localBound);
                    return source.node.sceneToLocal(sceneBound);
                }, source.button.boundsInLocalProperty(), source.button.localToSceneTransformProperty(),
                source.node.localToSceneTransformProperty());
        canvasTargetBoundsBinding = Bindings.createObjectBinding(() -> {
                    Bounds localBound = target.button.getBoundsInLocal();
                    Bounds sceneBound = target.button.localToScene(localBound);
                    return target.node.sceneToLocal(sceneBound);
                }, target.button.boundsInLocalProperty(), target.button.localToSceneTransformProperty(),
                target.node.localToSceneTransformProperty());
        this.setStrokeWidth(3.0);
        this.startXProperty().bind(
                Bindings.add(source.node.layoutXProperty(),
                            Bindings.createDoubleBinding(
                                () ->  canvasSourceBoundsBinding.get().getCenterX(),
                                    canvasSourceBoundsBinding)));
        this.startYProperty().bind(
                Bindings.add(source.node.layoutYProperty(),
                            Bindings.createDoubleBinding(
                                () ->  canvasSourceBoundsBinding.get().getCenterY(),
                                canvasSourceBoundsBinding)));
        this.endXProperty().bind(
                Bindings.add(target.node.layoutXProperty(),
                        Bindings.createDoubleBinding(
                        () ->  canvasTargetBoundsBinding.get().getCenterX(),
                        canvasTargetBoundsBinding)));
        this.endYProperty().bind(
                Bindings.add(target.node.layoutYProperty(),
                        Bindings.createDoubleBinding(
                                () ->  canvasTargetBoundsBinding.get().getCenterY(),
                                canvasTargetBoundsBinding)));
        this.setOnMousePressed( me -> remove() );
        this.setOnMouseEntered( me -> this.setCursor(Cursor.HAND));

        source.node.consumers.add(target.node);
        target.node.dependencies.add(source.node);
    }

    public void remove() {
        source.unbind(this);
        target.node.ready = false;
        target.unbind(this);
        canvas.removeLink(this);
    }
}
