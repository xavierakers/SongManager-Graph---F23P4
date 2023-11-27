/**
 * @author Xavier Akers
 * 
 * @version Last Updated 
 * 
 * @since 11-17-2023
 * 
 * 
 * 
 */
public class Graph {
    private GraphNode[] list;
    private int size;

    public Graph(int size) {
        this.size = size;
        list = new GraphNode[size];
    }
}
