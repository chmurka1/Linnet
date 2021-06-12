package code.descriptions;

import code.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class DescriptionWindow {

    public void showDescriptionWindow(){
        Stage stage=new Stage();
        stage.setTitle("Filters descriptions");
        Image icon = new Image(Main.class.getResource("images/linnet64.png").toExternalForm());
        stage.getIcons().add(icon);

        TreeItem<String> nodesItem = new TreeItem<> ("Nodes");
        nodesItem.setExpanded(true);

        TreeItem<String> filterNodeItem = new TreeItem<> ("Filter node");
        TreeItem<String> mergeNodeItem = new TreeItem<> ("Merge node");
        TreeItem<String> separatorNodeItem = new TreeItem<> ("Separator node");
        TreeItem<String> combinatorNodeItem = new TreeItem<> ("Combinator node");
        TreeItem<String> generatorNodeItem = new TreeItem<> ("Generator node");

        //filterNode
        TreeItem<String> emptyFilterItem = new TreeItem<> ("empty filter");
        filterNodeItem.getChildren().add(emptyFilterItem);
        TreeItem<String> brightenImageItem = new TreeItem<> ("brighten image");
        filterNodeItem.getChildren().add(brightenImageItem);
        TreeItem<String> darkenImageItem = new TreeItem<> ("darken image");
        filterNodeItem.getChildren().add(darkenImageItem);
        TreeItem<String> blackAndWhiteItem = new TreeItem<> ("black and white");
        filterNodeItem.getChildren().add(blackAndWhiteItem);
        TreeItem<String> sharpenItem = new TreeItem<> ("sharpen");
        filterNodeItem.getChildren().add(sharpenItem);
        TreeItem<String> contrastItem = new TreeItem<> ("contrast");
        filterNodeItem.getChildren().add(contrastItem);
        TreeItem<String> saturateItem = new TreeItem<> ("saturate");
        filterNodeItem.getChildren().add(saturateItem);
        TreeItem<String> horizontalBlurItem = new TreeItem<> ("horizontal blur");
        filterNodeItem.getChildren().add(horizontalBlurItem);
        TreeItem<String> verticalBlurItem = new TreeItem<> ("vertical blur");
        filterNodeItem.getChildren().add(verticalBlurItem);
        TreeItem<String> gaussianBlurItem = new TreeItem<> ("gaussian blur");
        filterNodeItem.getChildren().add(gaussianBlurItem);
        TreeItem<String> trimTopItem = new TreeItem<> ("trim top");
        filterNodeItem.getChildren().add(trimTopItem);

        //megreNode
        TreeItem<String> transferBrightnessItem = new TreeItem<> ("transfer brightness");
        mergeNodeItem.getChildren().add(transferBrightnessItem);
        TreeItem<String> blendPicturesItem = new TreeItem<> ("blend pictures");
        mergeNodeItem.getChildren().add(blendPicturesItem);

        //separatorNode
        TreeItem<String> rgbSeparatorItem = new TreeItem<> ("RGB");
        separatorNodeItem.getChildren().add(rgbSeparatorItem);
        TreeItem<String> hsvSeparatorItem = new TreeItem<> ("HSV");
        separatorNodeItem.getChildren().add(hsvSeparatorItem);

        //combinatorNode
        TreeItem<String> rgbCombinatorItem = new TreeItem<> ("RGB");
        combinatorNodeItem.getChildren().add(rgbCombinatorItem);
        TreeItem<String> hsvCombinatorItem = new TreeItem<> ("HSV");
        combinatorNodeItem.getChildren().add(hsvCombinatorItem);

        //generatorNode
        TreeItem<String> plainItem = new TreeItem<> ("plain");
        generatorNodeItem.getChildren().add(plainItem);

        nodesItem.getChildren().add(filterNodeItem);
        nodesItem.getChildren().add(mergeNodeItem);
        nodesItem.getChildren().add(separatorNodeItem);
        nodesItem.getChildren().add(combinatorNodeItem);
        nodesItem.getChildren().add(generatorNodeItem);

        TreeView<String> treeView = new TreeView<> (nodesItem);

        TextArea textArea=new TextArea("Just does nothing :) xxxxxx xxxxx xxxxx xxxxx xxxxx xxxx xxxx vvvv vvvv vhyrgd ddsb fvfv");
        textArea.setWrapText(true);

        treeView.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2)
            {
                TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
                if(item.getValue().equals("empty filter")){
                    textArea.setText("Just does nothing :)");
                }
                System.out.println("Selected Text : " + item.getValue());

            }
        });


        GridPane root = new GridPane();
        root.add(treeView,0,1);
        root.add(textArea,0,2);
        stage.setScene(new Scene(root, 400, 600));
        stage.show();
    }
}
