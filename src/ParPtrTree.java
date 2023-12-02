public class ParPtrTree {
    private int[] array; // Node array
    private int[] weights;

    ParPtrTree(int size) {
        array = new int[size]; // Create node array
        weights = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = -1; // Each node is its own root to start
            weights[i] = 0;
        }
    }


    public void UNION(int a, int b) {
        int root1 = FIND(a); // Find root of node a
        int root2 = FIND(b); // Find root of node b
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


    // Return the root of curr's tree
    public int FIND(int curr) {
        while (array[curr] != -1) {
            curr = array[curr];
        }
        return curr; // Now at root
    }
}
