package code.filters;

import code.controls.AbstractNode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.function.BiFunction;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class FilterParametrized3x3 implements Filter {

    private final BiFunction<ArrayList<Color>,Integer,Color> colorFunction;
    private final Integer coefficient;

    FilterParametrized3x3(BiFunction<ArrayList<Color>,Integer,Color> colorFunction, Integer coefficient){
        this.colorFunction = colorFunction;
        this.coefficient = coefficient;
    }

    @Override
    public void apply(AbstractNode node) {
        long start = System.currentTimeMillis();
        int width = node.input1.getContent().getWidth();
        int height = node.input1.getContent().getHeight();

        node.output1.setContent(new BufferedImage(width,height,TYPE_INT_RGB));
        ArrayList<Color> upRow = null;
        ArrayList<Color> curRow = null;
        ArrayList<Color> downRow;

        for (int y = 0; y < height; y++) {
            downRow = new ArrayList<>(width);
            int downY = y+1;
            if(downY < height) {
                for (int x = 0; x < width; x++) {
                    downRow.add(new Color(node.input1.getContent().getRGB(x,downY)));
                }
            }else {
                for (int x = 0; x < width; x++) {
                    node.output1.getContent().setRGB(x,y,node.input1.getContent().getRGB(x,y));
                }
                break;
            }
            if(y == 0) {
                curRow = new ArrayList<>(width);
                for (int x = 0; x < width; x++) {
                    curRow.add(new Color(node.input1.getContent().getRGB(x,y)));
                }
                for (int x = 0; x < width; x++) {
                    node.output1.getContent().setRGB(x,y,node.input1.getContent().getRGB(x,y));
                }
            }else {
                node.output1.getContent().setRGB(0,y,curRow.get(0).getRGB());
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
                    node.output1.getContent().setRGB(x,y,colorFunction.apply(matrix,coefficient).getRGB());
                }
                node.output1.getContent().setRGB(width - 1,y,curRow.get(width - 1).getRGB());
            }
            upRow = curRow;
            curRow = downRow;
        }
        System.out.println(this.getClass() + " " + (System.currentTimeMillis()-start));
    }

    @Override
    public boolean checkInput(AbstractNode node) {
        return node.input1.getContent()!=null;
    }
}
