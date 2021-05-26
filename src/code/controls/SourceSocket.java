package code.controls;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/***
 * Class implementing socket which actually store cached data (received from external sources)
 * it can provide those data to several other, "target" sockets
 * ideal for output sockets
 */
public class SourceSocket extends Socket {
    private BufferedImage content;
    ArrayList<Link> links = new ArrayList<>();

    public SourceSocket() {
        this(null);
    }

    public SourceSocket( AbstractNode node ) {
        super(node,"sourceSocket.fxml");
    }

    @Override
    public BufferedImage getContent() {
        return this.content;
    }

    @Override
    void bind(Link link) throws Exception {
        if( link != null && link.source == this ) {
            this.links.add(link);
        }
        else throw new Exception("Error during linking");
    }

    @Override
    void unbind(Link link) {
        if( link != null && this.links.contains(link) ) {
            this.links.remove(link);
            link.remove();
        }
    }

    @Override
    public void unbindAll() {
        while( !links.isEmpty()) links.get(0).remove();
    }

    public void setContent( BufferedImage buffer ) {
        this.content = buffer;
        for( Link l : links )   l.target.node.ready = false;
    }

    @Override
    public void clear() {
        this.content = null;
    }
}
