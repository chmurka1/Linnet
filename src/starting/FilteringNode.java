package starting;


import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class FilteringNode extends Node {


    public ArrayList<Node> inputNodes;

    Filter filter;
    BufferedImage output;

    FilteringNode(Filter filter){
        this.filter = filter;
        inputNodes = new ArrayList<>();
        //TODO jak dodajemy nody ?
    }
    void AddMeToFilteringNodeInput(FilteringNode childNode){
        //TODO czy chcemy by mozna bylo miec tylko 1 rodzica typu FilteringNode
        for(Node element : childNode.inputNodes){
            if(element instanceof FilteringNode){
                throw new UnsupportedOperationException();
            }
        }
        childNode.inputNodes.add(this);
    }

    //@Override
    public void run() {
        //TODO


    }
    public BufferedImage getOutput(){
        if(output == null) {
            throw new NullPointerException(); //TODO w sumie nie wiem czy tak to chcemy robic
        }
        return output;
    }
}
