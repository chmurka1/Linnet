package code.controls;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Canvas extends ScrollPane {

    @FXML
    Pane pane;

    public List<InputNode> listOfInputNodes=new LinkedList<>();
    public List<OutputNode> listOfOutputNodes=new LinkedList<>();
    public List<NodeControl> listOfNodeControls=new LinkedList<>();
    public List<Link> listOfLinks=new LinkedList<>();

    Socket clickedSocket;

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
        MenuItem menuItem1 = new MenuItem("add input node");
        menuItem1.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        addInputNode();
                    }
                }
        );
        MenuItem menuItem2 = new MenuItem("add node control");
        menuItem2.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        addNodeControl();
                    }
                }
        );
        MenuItem menuItem3 = new MenuItem("add output node");
        menuItem3.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        addOutputNode();
                    }
                }
        );
        contextMenu.getItems().addAll(menuItem1,menuItem2,menuItem3);
        this.setContextMenu(contextMenu);

    }

    public void addInputNode(){
        InputNode tempNode=new InputNode(this);
        listOfInputNodes.add(tempNode);
        pane.getChildren().add(tempNode);
    }

    public void addOutputNode(){
        OutputNode tempNode=new OutputNode(this);
        listOfOutputNodes.add(tempNode);
        pane.getChildren().add(tempNode);
    }

    public void addNodeControl(){
        NodeControl tempNode=new NodeControl(this);
        listOfNodeControls.add(tempNode);
        pane.getChildren().add(tempNode);
    }

    public void removeLink(Link link) {
        this.pane.getChildren().remove(link);
    }

    public void addLink(AbstractNode sourceNode, AbstractNode targetNode, Socket s, Socket t) {
        t.prevSocket=s;
        s.nextSocket=t;
        Link tempLink=new Link(this,sourceNode,targetNode,s,t);
        listOfLinks.add(tempLink);
        this.pane.getChildren().add(tempLink);
    }
}
