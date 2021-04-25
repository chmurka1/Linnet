package code.graph;

import code.controls.*;

import java.awt.image.BufferedImage;

public class Compute {
    public static void compute(Canvas canvas){
        innerClear(canvas);

        for(InputNode inputNode:canvas.listOfInputNodes){
            if(inputNode.out1!=null)
            {
            //    System.out.println("loading images from disk");
                transferImage(inputNode.s1out);
            }
        }

        boolean flag=true;
        while(flag){
            flag=false;

            for(NodeControl node:canvas.listOfNodeControls){
                if(node.isFilterApplied)continue;

                if(node.filter.checkInput(node)){
                    node.filter.apply(node);
                    if(node.out1!=null)transferImage(node.s1out);
                    if(node.out2!=null)transferImage(node.s2out);
                    node.isFilterApplied=true;
                    flag=true;
                }
            }
        }

        for(OutputNode outputNode: canvas.listOfOutputNodes){
            if(outputNode.in1!=null) {
                System.out.println("output ready");
                outputNode.colorTitlePane();
            }
        }
    }

    private static void transferImage(Socket s){
        if(s.nextSocket==null)
        {
            System.out.println("transfer failed");
            return;
        }

        BufferedImage img;
        if(s.id.equals("out1")){
            img=s.node.out1;
        }
        else{
            img=s.node.out2;
        }

        if(s.nextSocket.id.equals("in1")){
            s.nextSocket.node.in1=img;
        }
        else{
            s.nextSocket.node.in2=img;
        }
    }

    private static void innerClear(Canvas canvas){
        canvas.clickedSocket=null;
        for(NodeControl node:canvas.listOfNodeControls){
            node.isFilterApplied=false;
            node.in1=null; node.in2=null;
            node.out1=null; node.out2=null;
        }
        for(OutputNode outputNode:canvas.listOfOutputNodes){
            outputNode.in1=null;
        }
    }

    public static void fullClear(Canvas canvas){
        for(Link link:canvas.listOfLinks){
            link.removeLinkFromCanvas();
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
