package code.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Canvas extends ScrollPane {
    int counter = 0;
    Controller controller;
    @FXML Pane pane;

    public List<InputNode> listOfInputNodes = new LinkedList<>();
    public List<OutputNode> listOfOutputNodes = new LinkedList<>();
    public List<AbstractNode> listOfNodes = new LinkedList<>();
    public List<Link> listOfLinks = new LinkedList<>();

    public Socket clickedSocket;

    public double mouseX;
    public double mouseY;

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
        menuItem1.setOnAction(actionEvent -> addInputNode());
        MenuItem menuItem4 = new MenuItem("add merge node");
        menuItem4.setOnAction(actionEvent -> addNode(new MergeNode(this)));
        MenuItem menuItem2 = new MenuItem("add output node");
        menuItem2.setOnAction(actionEvent -> addOutputNode());
        MenuItem menuItem5 = new MenuItem("add separator node");
        menuItem5.setOnAction(actionEvent -> addNode(new SeparatorNode(this)));
        MenuItem menuItem6 = new MenuItem("add combinator node");
        menuItem6.setOnAction(actionEvent -> addNode(new CombinatorNode(this)));
        MenuItem menuItem3 = new MenuItem("add filter node");
        menuItem3.setOnAction(actionEvent -> addNode(new FilterNode(this)));
        MenuItem menuItem7 = new MenuItem("add generator node");
        menuItem7.setOnAction(actionEvent -> addNode(new GeneratorNode(this)));
        contextMenu.getItems().addAll(menuItem1,menuItem2,menuItem3,menuItem4,menuItem5,menuItem6,menuItem7);
        this.setContextMenu(contextMenu);

        this.setOnMouseClicked(me-> {
            this.mouseX = me.getSceneX();
            this.mouseY = me.getSceneY();
        });

    }
    public void removeInputNode(InputNode inputNode){
        pane.getChildren().remove(inputNode);
        listOfInputNodes.remove(inputNode);
    }
    public void addInputNode(){
        InputNode tempNode=new InputNode(this);
        counter++;
        tempNode.id = counter;
        listOfInputNodes.add(tempNode);
        pane.getChildren().add(tempNode);
    }

    public void removeOutputNode(OutputNode outputNode){
        pane.getChildren().remove(outputNode);
        listOfOutputNodes.remove(outputNode);
    }
    public void addOutputNode(){
        OutputNode tempNode=new OutputNode(this);
        counter++;
        tempNode.id = counter;
        listOfOutputNodes.add(tempNode);
        pane.getChildren().add(tempNode);
    }

    public void addNode( AbstractNode node )    {
        counter++;
        node.id = counter;
        listOfNodes.add(node);
        pane.getChildren().add(node);
    }

    public void removeNode( AbstractNode node ) {
        pane.getChildren().remove(node);
        listOfNodes.remove(node);
    }

    public void addLink(Socket s, Socket t) {
        try {
            Link tempLink = new Link(s, t);
            this.listOfLinks.add(tempLink);
            this.pane.getChildren().add(tempLink);
        }
        catch( Exception e ) {
            System.out.println(e.getMessage());
        }
    }

    public void removeLink( Link link ) {
        pane.getChildren().remove(link);
        listOfLinks.remove(link);
    }

    public void clear() {
        while( !listOfLinks.isEmpty()){
            listOfLinks.get(0).remove();
        }
        while( !listOfInputNodes.isEmpty()){
            removeInputNode(listOfInputNodes.get(0));
        }
        while( !listOfNodes.isEmpty()){
            removeNode(listOfNodes.get(0));
        }
        while( !listOfOutputNodes.isEmpty()){
            removeOutputNode(listOfOutputNodes.get(0));
        }
        counter = 0;
        clickedSocket=null;
        pane.getChildren().clear();
    }
}
