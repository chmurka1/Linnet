package code.files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileRead {

    public static BufferedImage read(File file)
    throws FileNotFoundException, FileFormatException{
        BufferedImage img = null;

        try {
            img = ImageIO.read(file);
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
        if(img==null) {
            throw new FileFormatException();
        }

        return img;
    }

}
