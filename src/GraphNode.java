
public class GraphNode {
    private int index;
    private GraphNode next;

    public GraphNode(int index) {
        this.index = index;
        this.next = null;
    }


    public int getIndex() {
        return index;
    }


    public GraphNode getNext() {
        return next;
    }


    public void setNext(GraphNode node) {
        this.next = node;
    }

}
