package code.filters;

import code.controls.NodeControl;

public class EmptyFilter implements Filter{
    @Override
    public void apply(NodeControl node) {
        node.output1.setContent(node.input1.getContent());
    }

    @Override
    public boolean checkInput(NodeControl node) {
        return ( node.input1.getContent() != null );
    }
}
