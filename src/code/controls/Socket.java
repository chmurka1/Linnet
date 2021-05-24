package code.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.awt.image.BufferedImage;
import java.io.IOException;

/***
 * Abstract class which provides basic socket interface
 * socket is a gadget which gives access to cached content
 * must be attached to a node
 */
public abstract class Socket extends VBox {
    @FXML
    Button button;

    @FXML
    Label label;

    public AbstractNode node;

    public Socket( AbstractNode parent, String path ) {
        super();

        this.node = parent;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }

        /*  clicking the same node resets clicked socket
            at first you can only click output socket
            clicked output socket only accepts input socket
            clicking clicked socket does nothing
        */
        button.setOnAction(
                e -> {
                    if( this instanceof TargetSocket ) this.unbind(((TargetSocket) this).link);

                    if(node.canvas.clickedSocket==null) node.canvas.clickedSocket=Socket.this;
                    else if(node.canvas.clickedSocket.node==Socket.this.node)   node.canvas.clickedSocket=null;
                    else{
                        node.canvas.addLink(node.canvas.clickedSocket,Socket.this);
                        node.canvas.clickedSocket=null;
                    }
                });

    }

    public void setName( String name ) {
        this.label.setText(name);
    }

    public String getName() {
        return this.label.getText();
    }

    public BufferedImage getContent() { return null; }
    void bind(Link link) throws Exception {}
    void unbind(Link link)   {}
    public void unbindAll() {}
    public void clear() {}
}
