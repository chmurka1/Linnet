package code.filters;

import code.controls.AbstractNode;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class TrimTop implements Filter {
    int coefficient;
    public TrimTop(int coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public void apply(AbstractNode node) {
        long start = System.currentTimeMillis();
        int width = node.input1.getContent().getWidth();
        int height = node.input1.getContent().getHeight();

        int cut = coefficient*height/100;

        BufferedImage out = new BufferedImage(width,height-cut,TYPE_INT_RGB);
        for (int y = cut; y < height; y++) {
            for (int x = 0; x < width; x++) {
                out.setRGB(x,y-cut,node.input1.getContent().getRGB(x,y));
            }
        }
        node.output1.setContent(out);
        System.out.println(this.getClass() + " " + (System.currentTimeMillis()-start));
    }

    @Override
    public boolean checkInput(AbstractNode node) {
        return node.input1.getContent()!=null;
    }
}
