package starting;

import javafx.scene.image.Image;

import java.util.AbstractList;
import java.util.ArrayList;

public abstract class FilteringNode extends Node {

    ArrayList<Node> inputNodes;

    Image output;
    Filter filter;
    public Image getOutput(){
        if(output == null) {
            throw new NullPointerException(); //TODO w sumie nie wiem czy tak to chcemy robic
        }
        return output;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        //TODO


    }
}
