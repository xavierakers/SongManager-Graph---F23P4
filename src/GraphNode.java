/**
 * @author Xavier Akers
 * 
 * @version Last Updated 11-5-2023
 * 
 * @since 11-5-2023
 * 
 *        Graph Node
 * 
 */
public class GraphNode {
    private int index;
    private GraphNode next;

    /**
     * Constructor
     * 
     * @param index
     *            Index in hash table
     */
    public GraphNode(int index) {
        this.index = index;
        this.next = null;
    }


    /**
     * @return Node Data
     */
    public int getIndex() {
        return index;
    }


    /**
     * @return Connected Node
     */
    public GraphNode getNext() {
        return next;
    }


    /**
     * @param node
     *            Set connected node
     */
    public void setNext(GraphNode node) {
        this.next = node;
    }

}
