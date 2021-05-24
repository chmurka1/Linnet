package code.filters;

import code.controls.NodeControl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.function.BiFunction;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class FilterParametrized3x3 implements Filter {

    BiFunction<ArrayList<Color>,Integer,Color> colorFunction;
    Integer coefficient;

    FilterParametrized3x3(BiFunction<ArrayList<Color>,Integer,Color> colorFunction, Integer coefficient){
        this.colorFunction = colorFunction;
        this.coefficient = coefficient;
    }

    @Override
    public void apply(NodeControl node) {

        int width = node.in1.getWidth();
        int height = node.in1.getHeight();

        node.out1 = new BufferedImage(width,height,TYPE_INT_RGB);
        ArrayList<Color> upRow = null;
        ArrayList<Color> curRow = null;
        ArrayList<Color> downRow;
//        System.out.println("Hi");

        for (int y = 0; y < height; y++) {
            downRow = new ArrayList<>(width);
            int downY = y+1;
            if(downY < height) {
                for (int x = 0; x < width; x++) {
                    downRow.add(new Color(node.in1.getRGB(x,downY)));
                }
            }else {
                for (int x = 0; x < width; x++) {
                    node.out1.setRGB(x,y,node.in1.getRGB(x,y));
                }
                break;
            }
            if(y == 0) {
                curRow = new ArrayList<>(width);
                for (int x = 0; x < width; x++) {
                    curRow.add(new Color(node.in1.getRGB(x,y)));
                }
                for (int x = 0; x < width; x++) {
                    node.out1.setRGB(x,y,node.in1.getRGB(x,y));
                }
            }else {
                node.out1.setRGB(0,y,curRow.get(0).getRGB());
                for (int x = 1; x < width - 1; x++) {
                    ArrayList<Color> matrix = new ArrayList<>(9);
                    matrix.add(upRow.get(x-1));
                    matrix.add(upRow.get(x));
                    matrix.add(upRow.get(x+1));
                    matrix.add(curRow.get(x-1));
                    matrix.add(curRow.get(x));
                    matrix.add(curRow.get(x+1));
                    matrix.add(downRow.get(x-1));
                    matrix.add(downRow.get(x));
                    matrix.add(downRow.get(x+1));
                    node.out1.setRGB(x,y,colorFunction.apply(matrix,coefficient).getRGB());
                }
                node.out1.setRGB(width - 1,y,curRow.get(width - 1).getRGB());
            }
            upRow = curRow;
            curRow = downRow;
            downRow = null;
        }
    }

    @Override
    public boolean checkInput(NodeControl node) {
        return node.in1!=null;
    }
}
