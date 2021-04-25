package code.graph;

import code.controls.*;

import java.awt.image.BufferedImage;

public class Compute {
    public void compute(Canvas canvas){
        for(InputNode inputNode:canvas.listOfInputNodes){
            if(inputNode.in1!=null)transferImage(inputNode.s1out);
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
                    flag=true;
                }
            }
        }

        for(OutputNode outputNode: canvas.listOfOutputNodes){
            if(outputNode.in1!=null)System.out.println("output ready");
        }
    }

    private void transferImage(Socket s){
        if(s.nextSocket==null)return;

        BufferedImage img=null;
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
}
