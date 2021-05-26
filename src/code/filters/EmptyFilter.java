package code.filters;

import code.controls.AbstractNode;

public class EmptyFilter implements Filter{
    @Override
    public void apply(AbstractNode node) {
        node.output1.setContent(node.input1.getContent());
    }

    @Override
    public boolean checkInput(AbstractNode node) {
        return ( node.input1.getContent() != null );
    }
}
