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

        //mergeNode
        TreeItem<String> transferBrightnessItem = new TreeItem<> ("transfer brightness");
        mergeNodeItem.getChildren().add(transferBrightnessItem);
        TreeItem<String> blendByBrightnessItem = new TreeItem<> ("blend by brightness");
        mergeNodeItem.getChildren().add(blendByBrightnessItem);
        TreeItem<String> blendByDarknessItem = new TreeItem<> ("blend by darkness");
        mergeNodeItem.getChildren().add(blendByDarknessItem);
        TreeItem<String> blendBySaturationItem = new TreeItem<> ("blend by saturation");
        mergeNodeItem.getChildren().add(blendBySaturationItem);
        TreeItem<String> ignoreGreenItem = new TreeItem<> ("ignore green");
        mergeNodeItem.getChildren().add(ignoreGreenItem);

        //separatorNode
        TreeItem<String> rgbSeparatorItem = new TreeItem<> ("RGB separate");
        separatorNodeItem.getChildren().add(rgbSeparatorItem);
        TreeItem<String> hsvSeparatorItem = new TreeItem<> ("HSV separate");
        separatorNodeItem.getChildren().add(hsvSeparatorItem);

        //combinatorNode
        TreeItem<String> rgbCombinatorItem = new TreeItem<> ("RGB combine");
        combinatorNodeItem.getChildren().add(rgbCombinatorItem);
        TreeItem<String> hsvCombinatorItem = new TreeItem<> ("HSV combine");
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

        TextArea textArea=new TextArea("double click on filter name to see description");
        textArea.setWrapText(true);
        textArea.setStyle("-fx-font-size: 16");

        treeView.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2)
            {
                TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();

                //filterNode
                if(item.getValue().equals("empty filter")){
                    textArea.setText("Just does nothing :)");
                }
                if(item.getValue().equals("brighten image")){
                    textArea.setText("Brightens image.");
                }
                if(item.getValue().equals("darken image")){
                    textArea.setText("Darkens image.");
                }
                if(item.getValue().equals("black and white")){
                    textArea.setText("Transforms image into black and white image.");
                }
                if(item.getValue().equals("horizontal blur")){
                    textArea.setText("Blurs image horizontally. Takes one parameter which tells how much in mille " +
                            " image will be blurred (eg. 60 blurs image in 6% range).");
                }
                if(item.getValue().equals("vertical blur")){
                    textArea.setText("Blurs image vertically. Takes one parameter which tells how much in mille " +
                            " image will be blurred (eg. 60 blurs image in 6% range).");
                }
                if(item.getValue().equals("gaussian blur")){
                    textArea.setText("Reduces details of an image, applies gaussian blur. Takes one parameter " +
                            "which tells how much in percent image will be blurred.");
                }
                if(item.getValue().equals("trim top")){
                    textArea.setText("Erases top part of an image and stretches the rest. " +
                            "Takes one parameter which tells how many percents of image will be trimmed.");
                }

                //mergeNode
                if(item.getValue().equals("transfer brightness")){
                    textArea.setText("Swaps brightness between two pictures.");
                }

                //separatorNode
                if(item.getValue().equals("RGB separate")){
                    textArea.setText("Takes on input one image which is split into three channels: red (R), green (G), blue (B). " +
                            "In each of these outputs there is a image where all RGB channels are set to value of this output's color.");
                }
                if(item.getValue().equals("HSV separate")){
                    textArea.setText("Takes on input one image which is split into three channels: hue (H), saturation (S), value and brightness (V). " +
                            "In each of these outputs there is a image where all HSV channels are set to value of this output's channel.");
                }

                //combinatorNode
                if(item.getValue().equals("RGB combine")){
                    textArea.setText("Takes on input three images and generates output image, in which " +
                            "red (R), green (G), blue (B) components are taken from respective input images.");
                }
                if(item.getValue().equals("HSV combine")){
                    textArea.setText("Takes on input three images and generates output image, in which " +
                            "hue (H), saturation (S), value and brightness (V) components are taken from respective input images.");
                }


                //generatorNode
                if(item.getValue().equals("plain")){
                    textArea.setText("Generates plain picture of given color and resolution.");
                }

            }
        });


        GridPane root = new GridPane();
        root.add(treeView,0,1);
        root.add(textArea,0,2);
        stage.setScene(new Scene(root, 400, 600));
        stage.show();
    }
}