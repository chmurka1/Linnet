package code.filters;

import code.controls.AbstractNode;

import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static java.lang.Math.sqrt;

public class GaussianBlur implements Filter{

    int size;

    public GaussianBlur(Integer coefficient) {
        this.size = Math.abs(coefficient);
    }

    @Override
    public void apply(AbstractNode node) {
        int width = node.input1.getContent().getWidth();
        int height = node.input1.getContent().getHeight();

        BufferedImage input = node.input1.getContent();

        double [] coeffs = new double[2*size+1];
        double twoRadiusSquaredRecip = 1.0 / (2.0 * size * size );
        double sqrtTwoPiTimesRadiusRecip = 1.0 / (sqrt(2.0 * Math.PI) * size);
        double radiusModifier = 1.0;

        int r = -size;
        double div = 0;
        for (int i = 0; i < coeffs.length; i++)
        {
            double x = r * radiusModifier;
            x *= x;
            coeffs[i] = sqrtTwoPiTimesRadiusRecip * Math.exp(-x * twoRadiusSquaredRecip);
            div += coeffs[i];
            r++;
        }

        BufferedImage res = new BufferedImage(width,height,TYPE_INT_RGB);

        int [][] inputmap =  new int [width][height];
        int [][] subres = new int [width][height];

        for( int i = 0; i < width; i++ ) for( int j = 0; j < height; j++ )
            inputmap[i][j] = input.getRGB(i,j);
        double R, G, B, Div;
        for( int i = 0; i < width; i++ )
            for( int j = 0; j < height; j++ ) {
                R = 0;
                G = 0;
                B = 0;
                Div = 0;
                for( int x = i - size; x <= i+size; x++ ) if( x >= 0 && x < width ) {
                    R += (coeffs[x-i+size])*((inputmap[x][j] & ( 0x00ff0000 )) >> 16);
                    G += (coeffs[x-i+size])*((inputmap[x][j] & ( 0x0000ff00 )) >> 8);
                    B += (coeffs[x-i+size])*(inputmap[x][j] & ( 0x000000ff ));
                    Div += coeffs[x-i+size];
                }
                R = R / Div;
                G = G / Div;
                B = B / Div;
                subres[i][j] = (int)R << 16 | (int)G << 8 | (int)B;
            }
        System.out.println("OK");
        for( int i = 0; i < width; i++ )
            for( int j = 0; j < height; j++ ) {
                R = 0;
                G = 0;
                B = 0;
                Div = 0;
                for( int y = j - size; y <= j+size; y++ ) if( y >= 0 && y < height ) {
                    R += (coeffs[y-j+size])*((subres[i][y] & ( 0x00ff0000 )) >> 16);
                    G += (coeffs[y-j+size])*((subres[i][y] & ( 0x0000ff00 )) >> 8);
                    B += (coeffs[y-j+size])*(subres[i][y] & ( 0x000000ff ));
                    Div += coeffs[y-j+size];
                }
                R = R / Div;
                G = G / Div;
                B = B / Div;
                res.setRGB(i,j, (int)R << 16 | (int)G << 8 | (int)B);
            }

        node.output1.setContent(res);
    }

    @Override
    public boolean checkInput(AbstractNode node) {
        return node.input1.getContent()!=null;
    }
}
