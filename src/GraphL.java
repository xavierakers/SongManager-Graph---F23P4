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

    GraphL() {

    }


    public void init(int n) {
        nodeArray = new Edge[n];
        for (int i = 0; i < n; i++) {
            nodeArray[i] = new Edge(-1, -1, null, null);
        }
        nodeValues = new Object[n];
        numEdge = 0;
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

}
