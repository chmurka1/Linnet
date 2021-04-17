package starting;

import javafx.scene.image.Image;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.Function;

public class FeatureExtractionNode extends Node {

    /**
    Czy FeatureExtractionNode moze miec inne FeatureExtractionNode w input (?)
     */

    Feature output;

    int minSizeOfInputNodes;
    int maxSizeOfInputNodes;

    ArrayList<FilteringNode> inputNodes;
    boolean[] finishedInputNodes;

    ArrayList<Image> input;



    private final Extractor<?> evaluatingFunction;


    FeatureExtractionNode(Extractor<? extends Feature> evaluatingFunction) throws IllegalAccessException, InstantiationException {
        this.evaluatingFunction = evaluatingFunction;
        Class<? extends Feature> featureType = this.evaluatingFunction.getType();
        output = featureType.newInstance();


        inputNodes = new ArrayList<>();
        //TODO dodajemy elementy, w momentcie, gdy user tworzy krawedz wchodzaca do tego noda ?
        // cos jak: inputNodes.add()

    }


    public Feature getOutput(){
        if(output == null) {
            throw new NullPointerException(); //TODO w sumie nie wiem czy chcemy wyrzucac ten wyjatek?
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
        if(inputNodes.size() < minSizeOfInputNodes || input.size() > maxSizeOfInputNodes){
            throw new UnsupportedOperationException(); //TODO maybe some other Exception
        }


        input = new ArrayList<>();
        for(FilteringNode node : inputNodes){
            input.add(node.getOutput());
        }
        output.setFeatureValue(evaluatingFunction.apply(input));


        // TODO nie wiem czy chcemy sie bawic w watki,
        //  a jesli tak, to w jak to bedziemy realizowac?
        //  nizej napisalem (dosyc slaby) szkic z flagami


//        finishedInputNodes = new boolean[inputNodes.size()];

//        boolean mayIStart = false;
//        while (!mayIStart){
//            boolean check = true;
//            for(boolean flag : finishedInputNodes){
//                if(!flag){
//                    check = false;
//                }
//            }
//            if(check){
//                mayIStart = true;
//            }
//         // tu gdzies takis wait(); ?
//        }
    }
}
