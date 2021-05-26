package code.filters;

import code.controls.AbstractNode;

public interface Filter {

    //applies filter to node
    void apply(AbstractNode node);

    //check if input is ready to apply filter
    boolean checkInput(AbstractNode node);
}
