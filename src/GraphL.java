import java.util.Arrays;

/**
 * @author Xavier Akers
 * @author Zoe Hite
 * 
 * @version Last Updated
 * 
 * @since 11-17-2023
 * 
 * 
 * 
 */
public class GraphL {
    private class Edge {
        private int vertex;
        private int weight;
        private Edge prev;
        private Edge next;

        Edge(int vertex, int weight, Edge prev, Edge next) {
            this.vertex = vertex;
            this.weight = weight;
            this.prev = prev;
            this.next = next;
        }

    }

    private Edge[] nodeArray;
    private Object[] nodeValues;
    private int numEdge;

    private int numOfComponents;
    private int biggestDiameter;
    private int biggestConnectedComp;
    private int listPos;

    /**
     * Empty Constructor
     */
    GraphL() {

    }


    /**
     * Initializes the Adjacency list
     * 
     * @param n
     *            Size of the adjacency list
     */
    public void init(int n) {
        nodeArray = new Edge[n];
        for (int i = 0; i < n; i++) {
            nodeArray[i] = new Edge(-1, -1, null, null);
        }
        nodeValues = new Object[n];
        numEdge = 0;
        biggestConnectedComp = 0;
        biggestDiameter = 0;
        listPos = 0;
    }


    /**
     * @return Number of Nodes
     */
    public int nodeCount() {
        return numEdge;
    }


    /**
     * @return Number of Edges
     */
    public int edgeCount() {
        return numEdge;
    }


    /**
     * @param v
     *            Index to retrieve
     * @return The node value
     */
    public Object getValue(int v) {
        return nodeValues[v];
    }


    /**
     * @param v
     *            Index to add
     * @param val
     *            Value to store
     */
    public void setValue(int v, Object val) {
        nodeValues[v] = val;
    }


    /**
     * Resizes the adjacency list
     */
    private void resize() {
        int n = nodeArray.length * 2;
        Edge[] newArray = new Edge[n];

        for (int i = 0; i < n; i++) {
            newArray[i] = new Edge(-1, -1, null, null);
        }
        for (int i = 0; i < nodeArray.length; i++) {
            newArray[i] = nodeArray[i];
        }

        nodeArray = newArray;

    }


    /**
     * Finds a node
     * 
     * @param v
     *            Vertex index
     * @param w
     *            Vertex
     * @return The specific node
     */
    private Edge find(int v, int w) {

        Edge curr = nodeArray[v];
        while ((curr.next != null) && (curr.next.vertex < w)) {
            curr = curr.next;
        }
        return curr;
    }


    /**
     * @return The index of the next open space
     */
    public int addNode() {
        for (int i = 0; i < nodeArray.length; i++) {
            if (nodeArray[i].next == null) {
                return i;
            }
        }
        return 0;
    }


    /**
     * @param v
     *            The Vertex (location in the array)
     * @param w
     *            Node to add to the Vertex (different location in array)
     * @param wgt
     *            Weight
     */
    public void addEdge(int v, int w, int wgt) {
        if (numEdge >= nodeArray.length) {
            resize();
        }

        if (wgt == 0) {
            return;
        }
        Edge curr = find(v, w);
        if ((curr.next != null) && (curr.next.vertex == w)) {
            curr.next.weight = wgt;
        }
        else {
            curr.next = new Edge(w, wgt, curr, curr.next);
            numEdge++;
            listPos++;
            if (curr.next.next != null) {
                curr.next.next.prev = curr.next;
            }
        }

    }


    /**
     * Weight should always be 1 in our case
     * 
     * @param v
     *            The Vertex (location in the array)
     * @param w
     *            The Node to add to the vertex
     * @return The weight of a node
     */
    public int weight(int v, int w) {
        Edge curr = find(v, w);
        if ((curr.next == null) || (curr.next.vertex != w)) {
            return 0;
        }
        else {
            return curr.next.weight;
        }
    }


    public void removeNode(int v) {
        int neighbors[] = neighbors(v);
        for (int i = 0; i < neighbors.length; i++) {
            removeEdge(v, neighbors[i]);
            removeEdge(neighbors[i], v);
        }

        nodeArray[v] = new Edge(-1, -1, null, null);
    }


    /**
     * Removes the respective edge
     * 
     * @param v
     *            The index of the vertex
     * @param w
     *            The weight of the edge
     */
    public void removeEdge(int v, int w) {
        Edge curr = find(v, w);
        if ((curr.next == null) || curr.next.vertex != w) {
            return;
        }
        else {
            curr.next = curr.next.next;
            if (curr.next != null) {
                curr.next.prev = curr;
            }
        }
        numEdge--;
    }


    /**
     * 
     * @param v
     *            Node 1
     * @param w
     *            Node 2
     * @return True if edge exists
     */
    public boolean hasEdge(int v, int w) {
        return weight(v, w) != 0;
    }


    /**
     * Returns an integer array containing the indexes of a nodes neighbors
     * 
     * @param v
     *            The vertex index
     * @return Integer array of node indexes
     */
    public int[] neighbors(int v) {
        int cnt = 0;
        Edge curr;
        for (curr = nodeArray[v].next; curr != null; curr = curr.next) {
            cnt++;
        }
        int[] temp = new int[cnt];
        cnt = 0;
        for (curr = nodeArray[v].next; curr != null; curr = curr.next) {
            temp[cnt++] = curr.vertex;
        }
        return temp;
    }


    /**
     * Collects graph information
     */
    public void analyzeGraph() {
        int root = analyzeComponents();
        analyzeDiameter(root);
    }


    /**
     * Loads information about the largest components
     * 
     * @return Index of the root of the largest component
     */
    public int analyzeComponents() {
        int[] parents = new int[nodeArray.length];
        int[] counts = new int[nodeArray.length];
        for (int i = 0; i < nodeArray.length; i++) {
            if (nodeArray[i].next == null) {
                parents[i] = -2;
                counts[i] = 0;
            }
            else {
                parents[i] = -1;
                counts[i] = 1;
            }
        }

        for (int i = 0; i < nodeArray.length; i++) {
            if (parents[i] == -1) {
                int[] neighbors = neighbors(i);
                for (int k = 0; k < neighbors.length; k++) {
                    union(i, neighbors[k], parents, counts);
                }
            }
        }

        numOfComponents = 0;
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == -1) {
                numOfComponents++;
            }
        }

        biggestConnectedComp = 0;
        int largestIndex = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > biggestConnectedComp) {
                biggestConnectedComp = counts[i];
                largestIndex = i;
            }
        }
        return largestIndex;

    }


    /**
     * Finds components
     * 
     * @param a
     *            Vertex 1
     * @param b
     *            Vertex 2
     * @param array
     *            Array of parents
     * @param weights
     *            Array of counters
     */
    public void union(int a, int b, int[] array, int[] weights) {
        int root1 = find(a, array); // Find root of node a
        int root2 = find(b, array); // Find root of node b

        if (root1 != root2) { // Merge with weighted union
            if (weights[root2] > weights[root1]) {
                array[root1] = root2;
                weights[root2] += weights[root1];
            }
            else {
                array[root2] = root1;
                weights[root1] += weights[root2];
            }
        }

    }


    /**
     * Return the root of curr's tree
     * 
     * @param curr
     *            The current node
     * @param array
     *            The parent array
     * @return The index of the root
     */
    public int find(int curr, int[] array) {
        while (array[curr] != -1) {
            curr = array[curr];
        }
        return curr; // Now at root
    }


    /**
     * Finds the diameter of the largest component
     */
    public void analyzeDiameter(int root) {
        int[][] distances = new int[biggestConnectedComp][biggestConnectedComp];

        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances.length; j++) {
                if (i != j) {
                    distances[i][j] = Integer.MAX_VALUE;
                }
                else {
                    distances[i][j] = 0;
                }
            }
        }

        // Crashing here after removing
        for (int i = 0; i < distances.length; i++) {
            Edge curr = null;
            if (nodeArray[i].next != null) {
                curr = nodeArray[i];
            }
            while ((curr != null) && (curr.next != null)) {

                curr = curr.next;
                distances[i][curr.vertex] = 1;

            }

        }

        floyds(distances);
        biggestDiameter = 0;
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances.length; j++) {
                if (distances[i][j] > biggestDiameter) {
                    biggestDiameter = distances[i][j];
                }
            }
        }
    }


    /**
     * Floyds distance algorithm
     * 
     * @param distances
     *            Matrix of Weights
     */
    private void floyds(int[][] distances) {
        for (int k = 0; k < distances.length; k++) { // Compute all k paths
            for (int i = 0; i < distances.length; i++) {
                for (int j = 0; j < distances.length; j++) {
                    if ((distances[i][k] != Integer.MAX_VALUE)
                        && (distances[k][j] != Integer.MAX_VALUE)
                        && (distances[i][j] > (distances[i][k]
                            + distances[k][j]))) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
    }


    /**
     * @return Number of components
     */
    public int getNumConnectedComponents() {
        return numOfComponents;
    }


    /**
     * @return The size of the largest component
     */
    public int getBiggestComponentCount() {
        return biggestConnectedComp;
    }


    /**
     * @return The diameter of the largest component
     */
    public int getBiggestComponentDiameter() {
        return biggestDiameter;
    }

    /**
     * Temporary method check
     */
    public void printAdjList() {
        System.out.println("PRINTING ADJ LIST");
        for (int i = 0; i < nodeArray.length; i++) {
            Edge curr = nodeArray[i];
            System.out.print(curr.vertex + " ");
            while (curr.next != null) {
                curr = curr.next;
                System.out.print(curr.vertex + " ");
            }
            System.out.println();
        }
    }
}
