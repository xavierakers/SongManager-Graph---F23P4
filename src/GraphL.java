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
public class GraphL {
    private class Edge {
        int vertex, weight;
        Edge prev, next;

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
    private int biggestConnectedComp;
    private int diameterBiggestComp;
    private int[] parents;
    private int[] counts;

    GraphL() {

    }


    public void init(int n) {
        nodeArray = new Edge[n];
        for (int i = 0; i < n; i++) {
            nodeArray[i] = new Edge(-1, -1, null, null);
        }
        nodeValues = new Object[n];
        numEdge = 0;
        biggestConnectedComp = 0;
        diameterBiggestComp = 0;
    }


    public int nodeCount() {
        return nodeArray.length;
    }


    public int edgeCount() {
        return numEdge;
    }


    public Object getValue(int v) {
        return nodeValues[v];
    }


    public void setValue(int v, Object val) {
        nodeValues[v] = val;
    }


    private Edge find(int v, int w) {
        Edge curr = nodeArray[v];
        while ((curr.next != null) && (curr.next.vertex < w)) {
            curr = curr.next;
        }
        return curr;
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
     * @return
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


    public boolean hasEdge(int v, int w) {
        return weight(v, w) != 0;
    }


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


    public void printGraph() {
        parents = new int[nodeArray.length];
        counts = new int[nodeArray.length];
        ParPtrTree tree = new ParPtrTree(nodeArray.length);

        for (int i = 0; i < nodeArray.length; i++) {
            System.out.print(i);
            if (nodeArray[i] == null) {
                parents[i] = -2;
                counts[i] = 0;
            }
            else {
                parents[i] = -1;
                counts[i] = 1;

                int[] neighbors = neighbors(i);
                for (int k = 0; i < neighbors.length; k++) {
                    tree.UNION(i, neighbors[k]);
                    counts[i]++;
                }

            }
        }
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > biggestConnectedComp) {
                biggestConnectedComp = counts[i];
            }
        }
        if (counts.length > 0) {
            for (int i = 0; i < counts.length; i++) {
                diameter(tree, counts[i]);
            }
        }
    }


    public int map(ParPtrTree tree, int root, int vertex) {
        for (int i = 0; i < counts[root]; i++) {
            if (vertex == tree.FIND(i)) {
                return i;
            }
        }

        return -1;
    }


    public void diameter(ParPtrTree tree, int root) {
        int[] componentVertices = new int[counts[root]];
        int componentIndex = 0;

        for (int i = 0; i < nodeArray.length; i++) {
            if (tree.FIND(i) == root) {
                componentVertices[componentIndex++] = i;
            }
        }

        int[][] D = new int[counts[root]][counts[root]];
        for (int[] row : D) {
            for (int i = 0; i < row.length; i++) {
                row[i] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < counts[root]; i++) {
            D[i][i] = 0;
            for (int neighbor : neighbors(componentVertices[i])) {
                D[i][map(tree, root, neighbor)] = 1;
            }
        }

        for (int k = 0; k < nodeCount(); k++) { // Compute all k paths
            for (int i = 0; i < nodeCount(); i++) {
                for (int j = 0; j < nodeCount(); j++) {
                    if ((D[i][k] != Integer.MAX_VALUE)
                        && (D[k][j] != Integer.MAX_VALUE) && (D[i][j] > (D[i][k]
                            + D[k][j]))) {
                        D[i][j] = D[i][k] + D[k][j];
                    }
                }
            }
        }

        int diameter = 0;
        for (int i = 0; i < counts[root]; i++) {
            for (int j = 0; j < counts[root]; j++) {
                if (D[i][j] != Integer.MAX_VALUE && D[i][j] > diameter) {
                    diameter = D[i][j];
                }
            }
        }

        if (diameter > diameterBiggestComp) {
            diameterBiggestComp = diameter;
        }
    }


    public int getNumConnectedComponents() {
        return counts.length;
    }


    public int getBiggestComponentCount() {
        return biggestConnectedComp;
    }


    public int getBiggestComponentDiameter() {
        return diameterBiggestComp;
    }


    static void Floyd(GraphL G, int[][] D) {
        for (int i = 0; i < G.nodeCount(); i++) { // Initialize D with weights
            for (int j = 0; j < G.nodeCount(); j++) {
                if (G.weight(i, j) != 0) {
                    D[i][j] = G.weight(i, j);
                }
            }
        }
        for (int k = 0; k < G.nodeCount(); k++) { // Compute all k paths
            for (int i = 0; i < G.nodeCount(); i++) {
                for (int j = 0; j < G.nodeCount(); j++) {
                    if ((D[i][k] != Integer.MAX_VALUE)
                        && (D[k][j] != Integer.MAX_VALUE) && (D[i][j] > (D[i][k]
                            + D[k][j]))) {
                        D[i][j] = D[i][k] + D[k][j];
                    }
                }
            }
        }
    }
}
