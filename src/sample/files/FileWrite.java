package sample.files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileWrite {
    public static boolean write(BufferedImage img, String format, String path)
    throws IOException{
        File file=null;
        try{
            file=new File(path);
        }catch (NullPointerException exc){//most likely never happening
            System.out.println("null path");
            return false;
        }

        try {
            ImageIO.write(img, format, file);
        } catch (IOException e) {
            System.out.println("cannot write"); //TODO
            throw new IOException();
        }
        return true;
    }
}
