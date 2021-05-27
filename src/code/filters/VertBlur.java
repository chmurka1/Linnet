package code.filters;

import code.controls.AbstractNode;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class VertBlur implements Filter{

    int sizeInPerMille;
    int size;//pixels

    public VertBlur(Integer coefficient) {
        this.sizeInPerMille = Math.abs(coefficient);
    }

    @Override
    public void apply(AbstractNode node) {

        long start = System.currentTimeMillis();
        int width = node.input1.getContent().getWidth();
        int height = node.input1.getContent().getHeight();
        size = height*sizeInPerMille/1000;

        node.output1.setContent(new BufferedImage(width,height,TYPE_INT_RGB));

        int [][] colors = new int [width+2*size][height+2*size];

        for (int y = 0; y < height + 2*size; y++) {
            for (int x = 0; x < width + 2*size; x++) {
                if(x<size || x>=width+size || y<size || y>=height+size) {
                    colors[x][y] = 0x808080;
                }
                else {
                    colors[x][y] = node.input1.getContent().getRGB(x-size,y-size);
                }
            }
        }
        int div = 2*size+1;
        for (int x = 0; x < width; x++) {
//            boolean inCentre = false;
            int sumR = 128*size;
            int sumG = 128*size;
            int sumB = 128*size;
            for(int i=0;i<=size;i++) {
                sumR += (colors[x+size][size+i] & 0x00ff0000) >> 16;
                sumG += (colors[x+size][size+i] & 0x0000ff00) >> 8;
                sumB += (colors[x+size][size+i] & 0x000000ff);
            }
            int avgR = sumR/div;
            int avgG = sumG/div;
            int avgB = sumB/div;
            node.output1.getContent().setRGB(x,0,avgR << 16 | avgG << 8 | avgB);
            for (int y = 1; y < height; y++) {

                sumR = sumR + ((colors[x+size][y+2*size] & 0x00ff0000) >> 16) - ((colors[x+size][y-1] & 0x00ff0000) >> 16);
                sumG = sumG + ((colors[x+size][y+2*size] & 0x0000ff00) >> 8) - ((colors[x+size][y-1] & 0x0000ff00) >> 8);
                sumB = sumB + (colors[x+size][y+2*size] & 0x000000ff) - (colors[x+size][y-1] & 0x000000ff);
                avgR = sumR/div;
                avgG = sumG/div;
                avgB = sumB/div;
//                if(y == 0) {
//                    System.out.println("sum " + sumR + " avg " + "min " + ((colors[x-1][y+size] & 0x00ff0000) >> 16) + " plus " + ((colors[x+2*size][y+size] & 0x00ff0000) >> 16));
//                }
                node.output1.getContent().setRGB(x,y,avgR << 16 | avgG << 8 | avgB);
            }
        }
        System.out.println(this.getClass() + " " + (System.currentTimeMillis()-start));
    }

    @Override
    public boolean checkInput(AbstractNode node) {
        return node.input1.getContent()!=null;
    }
}
