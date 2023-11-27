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
    private GraphNode prev;
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
    public int getData() {
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


    /**
     * @return the prev
     */
    public GraphNode getPrev() {
        return prev;
    }


    /**
     * @param prev the prev to set
     */
    public void setPrev(GraphNode prev) {
        this.prev = prev;
    }

}
