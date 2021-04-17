package sample.files;

import java.io.IOException;

public class FileFormatException extends IOException {
    public FileFormatException(){
    }

    public FileFormatException(String s) {
        super(s);
    }

    public FileFormatException(String s, Throwable t) {
        super(s, t);
    }

    public FileFormatException(Throwable t) {
        super(t);
    }
}
