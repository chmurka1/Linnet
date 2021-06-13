package code.descriptions;

import code.Main;
import code.controls.FilterNode;
import code.controls.MergeNode;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
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
    //    TreeItem<String> emptyFilterItem = new TreeItem<> ("empty filter");
    //    filterNodeItem.getChildren().add(emptyFilterItem);
        TreeItem<String> brightenImageItem = new TreeItem<> (FilterNode.NamesOfFilters.BRIGHTEN.displayName);
        filterNodeItem.getChildren().add(brightenImageItem);
        TreeItem<String> darkenImageItem = new TreeItem<> (FilterNode.NamesOfFilters.DARKEN.displayName);
        filterNodeItem.getChildren().add(darkenImageItem);
    //    TreeItem<String> blackAndWhiteItem = new TreeItem<> ("black and white");
    //    filterNodeItem.getChildren().add(blackAndWhiteItem);
        TreeItem<String> sharpenItem = new TreeItem<> (FilterNode.NamesOfFilters.SHARPEN.displayName);
        filterNodeItem.getChildren().add(sharpenItem);
        TreeItem<String> contrastItem = new TreeItem<> (FilterNode.NamesOfFilters.CONTRAST.displayName);
        filterNodeItem.getChildren().add(contrastItem);
        TreeItem<String> saturateItem = new TreeItem<> (FilterNode.NamesOfFilters.SATURATION.displayName);
        filterNodeItem.getChildren().add(saturateItem);
        TreeItem<String> horizontalBlurItem = new TreeItem<> (FilterNode.NamesOfFilters.HORIZONTAL.displayName);
        filterNodeItem.getChildren().add(horizontalBlurItem);
        TreeItem<String> verticalBlurItem = new TreeItem<> (FilterNode.NamesOfFilters.VERTICAL.displayName);
        filterNodeItem.getChildren().add(verticalBlurItem);
        TreeItem<String> gaussianBlurItem = new TreeItem<> (FilterNode.NamesOfFilters.GAUSSIAN.displayName);
        filterNodeItem.getChildren().add(gaussianBlurItem);
        TreeItem<String> trimTopItem = new TreeItem<> (FilterNode.NamesOfFilters.TOP.displayName);
        filterNodeItem.getChildren().add(trimTopItem);
        TreeItem<String> setWidthItem = new TreeItem<> (FilterNode.NamesOfFilters.WIDTH.displayName);
        filterNodeItem.getChildren().add(setWidthItem);
        TreeItem<String> setHeightItem = new TreeItem<> (FilterNode.NamesOfFilters.HEIGHT.displayName);
        filterNodeItem.getChildren().add(setHeightItem);

        //mergeNode
        TreeItem<String> transferBrightnessItem = new TreeItem<> (MergeNode.NamesOfMergeFilters.TRANSFER.displayName);
        mergeNodeItem.getChildren().add(transferBrightnessItem);
        TreeItem<String> blendByBrightnessItem = new TreeItem<> (MergeNode.NamesOfMergeFilters.BRIGHTNESS.displayName);
        mergeNodeItem.getChildren().add(blendByBrightnessItem);
        TreeItem<String> blendByDarknessItem = new TreeItem<> (MergeNode.NamesOfMergeFilters.DARKNESS.displayName);
        mergeNodeItem.getChildren().add(blendByDarknessItem);
        TreeItem<String> blendBySaturationItem = new TreeItem<> (MergeNode.NamesOfMergeFilters.SATURATION.displayName);
        mergeNodeItem.getChildren().add(blendBySaturationItem);
        TreeItem<String> ignoreGreenItem = new TreeItem<> (MergeNode.NamesOfMergeFilters.GREENSCREEN.displayName);
        mergeNodeItem.getChildren().add(ignoreGreenItem);
        TreeItem<String> multiplyBlendItem = new TreeItem<> (MergeNode.NamesOfMergeFilters.MULTIPLY.displayName);
        mergeNodeItem.getChildren().add(multiplyBlendItem);
        TreeItem<String> subtractBlendItem = new TreeItem<> (MergeNode.NamesOfMergeFilters.SUBTRACT.displayName);
        mergeNodeItem.getChildren().add(subtractBlendItem);
        TreeItem<String> addBlendItem = new TreeItem<> (MergeNode.NamesOfMergeFilters.ADD.displayName);
        mergeNodeItem.getChildren().add(addBlendItem);

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
        TreeItem<String> borderItem = new TreeItem<> ("border");
        generatorNodeItem.getChildren().add(borderItem);
        TreeItem<String> noiseItem = new TreeItem<> ("noise");
        generatorNodeItem.getChildren().add(noiseItem);
        TreeItem<String> vignetteItem = new TreeItem<> ("vignette");
        generatorNodeItem.getChildren().add(vignetteItem);

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
                if(item.getValue().equals(FilterNode.NamesOfFilters.EMPTY.displayName)){
                    textArea.setText("Just does nothing :)");
                }
                if(item.getValue().equals(FilterNode.NamesOfFilters.BRIGHTEN.displayName)){
                    textArea.setText("Brightens image.");
                }
                if(item.getValue().equals(FilterNode.NamesOfFilters.DARKEN.displayName)){
                    textArea.setText("Darkens image.");
                }
                if(item.getValue().equals(FilterNode.NamesOfFilters.BLACKNWHITE.displayName)){
                    textArea.setText("Transforms image into black and white image.");
                }
                if(item.getValue().equals(FilterNode.NamesOfFilters.SHARPEN.displayName)){
                    textArea.setText("Sharpens an image. Takes one parameter which tells how much image will be sharpened, " +
                            "advised range is from -200 to 200.");
                }
                if(item.getValue().equals(FilterNode.NamesOfFilters.CONTRAST.displayName)){
                    textArea.setText("Changes contrast of an image. Takes one parameter which tells how much contrast will be changed, " +
                            "advised range is from -100 to 200, but even much higher values produce valid images.");
                }
                if(item.getValue().equals(FilterNode.NamesOfFilters.SATURATION.displayName)){
                    textArea.setText("Changes saturation of an image. Takes one parameter which tells how much saturation will be changed, " +
                            "advised range is from -100 to 200.");
                }
                if(item.getValue().equals(FilterNode.NamesOfFilters.HORIZONTAL.displayName)){
                    textArea.setText("Blurs image horizontally. Takes one parameter which tells how much in mille " +
                            " image will be blurred (e.g. 60 blurs image in 6% range).");
                }
                if(item.getValue().equals(FilterNode.NamesOfFilters.VERTICAL.displayName)){
                    textArea.setText("Blurs image vertically. Takes one parameter which tells how much in mille " +
                            " image will be blurred (e.g. 60 blurs image in 6% range).");
                }
                if(item.getValue().equals(FilterNode.NamesOfFilters.GAUSSIAN.displayName)){
                    textArea.setText("Reduces details of an image, applies gaussian blur. Takes one parameter " +
                            "which tells how much in pixels image will be blurred.");
                }
                if(item.getValue().equals(FilterNode.NamesOfFilters.TOP.displayName)){
                    textArea.setText("Erases top part of an image and stretches the rest. " +
                            "Takes one parameter which tells how many percents of image will be trimmed.");
                }
                if(item.getValue().equals(FilterNode.NamesOfFilters.WIDTH.displayName)){
                    textArea.setText("Sets new width of image. " +
                            "Takes one parameter which tells new width in pixels.");
                }
                if(item.getValue().equals(FilterNode.NamesOfFilters.HEIGHT.displayName)){
                    textArea.setText("Sets new width of image. " +
                            "Takes one parameter which tells new height in pixels.");
                }


                //mergeNode
                if(item.getValue().equals(MergeNode.NamesOfMergeFilters.TRANSFER.displayName)){
                    textArea.setText("Swaps brightness between two pictures.");
                }
                if(item.getValue().equals(MergeNode.NamesOfMergeFilters.BRIGHTNESS.displayName)){
                    textArea.setText("Blends images with the following rules, first image is foreground, second one is background: " +
                            "if pixel of foreground is bright then output pixel will be almost the foreground one, " +
                            "if foreground pixel is dark then output pixel will be almost the background one, " +
                            "otherwise output pixel is in between the foreground one and background one.");
                }
                if(item.getValue().equals((MergeNode.NamesOfMergeFilters.DARKNESS.displayName))){
                    textArea.setText("Blends images with the following rules, first image is foreground, second one is background: " +
                            "if pixel of foreground is dark then output pixel will be almost the foreground one, " +
                            "if foreground pixel is bright then output pixel will be almost the background one, " +
                            "otherwise output pixel is in between the foreground one and background one.");
                }
                if(item.getValue().equals((MergeNode.NamesOfMergeFilters.SATURATION.displayName))){
                    textArea.setText("Blends images with the following rules, first image is foreground, second one is background: " +
                            "if pixel of foreground is highly saturated then output pixel will be almost the foreground one, " +
                            "if foreground pixel is lowly saturated then output pixel will be almost the background one, " +
                            "otherwise output pixel is in between the foreground one and background one.");
                }
                if(item.getValue().equals((MergeNode.NamesOfMergeFilters.GREENSCREEN.displayName))){
                    textArea.setText("Blends images with the following rules, first image is foreground, second one is background: " +
                            "if pixel of foreground is close to green and is highly saturated then output pixel will be almost the background one, " +
                            "otherwise output pixel will be almost the foreground one.");
                }
                if(item.getValue().equals((MergeNode.NamesOfMergeFilters.MULTIPLY.displayName))){
                    textArea.setText("Multiplies values of pixels componentwise, and then normalize them. " +
                            "I. e. (r1,g1,b1) * (r2,g2,b2) = (r1*r2/256,g1*g2/256,b1*b2/256)");
                }
                if(item.getValue().equals((MergeNode.NamesOfMergeFilters.GREENSCREEN.displayName))){
                    textArea.setText("Adds values of pixels componentwise, clamping the resulting components to stay between 0 and 256" );
                }
                if(item.getValue().equals((MergeNode.NamesOfMergeFilters.GREENSCREEN.displayName))){
                    textArea.setText("Subtracts values of pixels componentwise, clamping the resulting components to stay between 0 and 256" );
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
                    textArea.setText("Generates plain picture of given color and resolution, e.g. #FF0000 is red.");
                }
                if(item.getValue().equals("border")){
                    textArea.setText("Generates picture of given resolution with border of given color, e.g. #FF0000 is red. " +
                            "Area inside the border is black.");
                }
                if(item.getValue().equals("noise")){
                    textArea.setText("Generates picture of given resolution with noise of given color, e.g. #FF0000 is red.");
                }
                if(item.getValue().equals("vignette")){
                    textArea.setText("Generates picture of given resolution with vignette of given color, e.g. #FF0000 is red.");
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
