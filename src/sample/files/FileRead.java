package sample.files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileRead {

    public static BufferedImage read(String path)
    throws FileNotFoundException, FileFormatException{
        BufferedImage img = null;
        File file=null;

        try{
            file=new File(path);
        }catch (NullPointerException exc){//most likely never happening
            System.out.println("null path");
            return null;
        }
        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            System.out.println("cannot read");
            throw new FileNotFoundException();
        }
        if(img==null) {
            System.out.println("wrong format");
            throw new FileFormatException();
        }

        return img;
    }

}
