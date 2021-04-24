package code.files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileWrite {
    public static void write(BufferedImage img, String format, File file)
    throws IOException{
        if(img==null){
            System.out.println("no input in write");
            return;
        }
        try {
            ImageIO.write(img, format, file);
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
