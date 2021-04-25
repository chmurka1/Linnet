package code.filters;

import code.controls.NodeControl;

public class EmptyFilter implements Filter{
    @Override
    public void apply(NodeControl node) {
        node.out1=node.in1;
    }

    @Override
    public boolean checkInput(NodeControl node) {
        return (node.in1!=null);
    }
}
