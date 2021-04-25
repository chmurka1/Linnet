package code.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Socket extends VBox {

    @FXML
    Button button;
    //parent node
    public AbstractNode node;
    //in1, in2 for input, out1, out2 for output
    public String id;
    //socket that this socket is connected to
    public Socket nextSocket;
    public Socket prevSocket;

    public Socket() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Socket.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try { fxmlLoader.load(); } catch (IOException exception) { throw new RuntimeException(exception); }

        button.setStyle(
                "-fx-background-radius: 10em; " +
                        "-fx-min-width: 10px; " +
                        "-fx-min-height: 10px; " +
                        "-fx-max-width: 10px; " +
                        "-fx-max-height: 10px;"
        );

        /*  clicking the same node resets clicked socked
            at first you can only click output socked
            clicked output socket only accepts input socked
            clicking clicked socket does nothing
        */
        button.setOnAction(
                e -> {
                    if(Socket.this.id.equals("in1")||Socket.this.id.equals("in2")){
                        if(Socket.this.prevSocket!=null)return;
                    }
                    if(Socket.this.id.equals("out1")||Socket.this.id.equals("out2")){
                        if(Socket.this.nextSocket!=null)return;
                    }

                    if(node.canvas.clickedSocket==null){
                        if(Socket.this.id.equals("in1")||Socket.this.id.equals("in2"))return;
                        node.canvas.clickedSocket=Socket.this;
                    }
                    else if(node.canvas.clickedSocket.node==Socket.this.node){
                        node.canvas.clickedSocket=null;
                    }
                    else{
                        if(Socket.this.id.equals("out1")||Socket.this.id.equals("out2"))return;
                        node.canvas.addLink(node.canvas.clickedSocket.node,Socket.this.node,
                                node.canvas.clickedSocket,Socket.this);
                        node.canvas.clickedSocket=null;
                    }
                });

    }
}
