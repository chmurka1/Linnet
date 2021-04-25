package code.filters;

import code.controls.NodeControl;

public interface Filter {

    //applies filter to node
    void apply(NodeControl node);

    //check if input is ready to apply filter
    boolean checkInput(NodeControl node);
}
