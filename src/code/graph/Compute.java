package code.graph;

import code.controls.*;

public class Compute {
    public static void compute(Canvas canvas){
        innerClear(canvas);

        for(InputNode inputNode : canvas.listOfInputNodes)
            if(inputNode.s1out.getContent()!=null) System.out.println("loading images from disk");


        boolean flag=true;
        while(flag){
            flag=false;

            for(NodeControl node : canvas.listOfNodeControls)   {
                if(node.isFilterApplied)    continue;

                node.load();
                if(node.filter.checkInput(node)){
                    node.filter.apply(node);
                    node.transfer();
                    node.isFilterApplied=true;
                    flag=true;
                }
            }
        }

        for(OutputNode outputNode: canvas.listOfOutputNodes){
            if(outputNode.s1in.getContent() != null) {
                System.out.println("output ready");
                outputNode.colorTitlePane();
            }
        }
    }

    private static void innerClear(Canvas canvas){
        canvas.clickedSocket=null;
        for(NodeControl node:canvas.listOfNodeControls){
            node.isFilterApplied=false;
            node.s1in.clear(); node.s2in.clear();
            node.s1out.clear(); node.s2out.clear();
        }
        for(OutputNode outputNode:canvas.listOfOutputNodes){
            //outputNode.clear();
            outputNode.uncolorTitlePane();
        }
    }

    public static void fullClear(Canvas canvas){
        for(Link link:canvas.listOfLinks){
            link.remove();
        }
        canvas.listOfLinks.clear();
        for(InputNode inputNode:canvas.listOfInputNodes){
            canvas.removeInputNode(inputNode);
        }
        canvas.listOfInputNodes.clear();
        for(OutputNode outputNode:canvas.listOfOutputNodes){
            canvas.removeOutputNode(outputNode);
        }
        canvas.listOfOutputNodes.clear();
        for(NodeControl node: canvas.listOfNodeControls){
            canvas.removeNodeControl(node);
        }
        canvas.listOfNodeControls.clear();

        canvas.clickedSocket=null;
    }
}
