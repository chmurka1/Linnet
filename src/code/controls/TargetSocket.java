package code.controls;

import java.awt.image.BufferedImage;

/***
 * Class implementing socket which gets its data from other place
 * it can be connected to one "source" socket to get data from
 * ideal for input sockets
 */
public class TargetSocket extends Socket {
    Link link;

    public TargetSocket() { this(null); }

    public TargetSocket( AbstractNode node )
    {
        super(node,"targetSocket.fxml");
    }

    @Override
    public BufferedImage getContent() {
        if( link != null )
            return link.source.getContent();
        else return null;
    }

    @Override
    void bind(Link link) throws Exception {
        if( this.link == null && link != null && link.target == this ) {
            this.link = link;
        }
        else throw new Exception("Error during linking");
    }

    @Override
    void unbind(Link link) {
        if( link != null && this.link == link ) {
            this.link = null;
            link.remove();
        }
}

    @Override
    public void unbindAll() { if( link != null ) this.link.remove(); }
}
