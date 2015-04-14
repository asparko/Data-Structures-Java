/**
 * Factory used for creating nodes
 */
public final class NodeFactory {

    public static Node create(String name) {
    	MyNode.test++;						//increment static variable so each node has unique ID
        return new MyNode(name);
    }

}
