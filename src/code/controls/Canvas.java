package code.controls;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class Canvas extends ScrollPane {

    @FXML
    NodeControl node1;
    @FXML
    NodeControl node2;

    public Canvas() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Canvas.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(new MenuItem("1"),new MenuItem("1"),new MenuItem("1"));
        this.setContextMenu(contextMenu);
        this.addLink(node1,node2,node1.s7,node2.s2);
    }

    public void removeLink(Link link) {
        this.getChildren().remove(link);
    }

    public void addLink(NodeControl sourceNode, NodeControl targetNode, Socket s, Socket t) {
        this.getChildren().add(new Link(this,sourceNode,targetNode,s,t));
    }
}
