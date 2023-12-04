import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author Xavier Akers
 * 
 * @author Zoe Hite
 * 
 * @version Last Updated 2023-16-11
 */
public class GraphTest extends TestCase {

    /**
     * Adding some records
     * 
     * @throws IOException
     */
    public void testInsertSimpleGraph() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testInsertSimpleGraph.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertSimpleGraph_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Adding some records
     * 
     * @throws IOException
     */
    public void testInsertBasicGraph() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testInsertBasicGraph.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertBasicGraph_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Expansion of Hash table with tombstones
     * 
     * @throws IOException
     */
    public void testInsertComplexGraph() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testInsertComplexGraph.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertComplexGraph_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * 
     * @throws IOException
     */
    public void testInsertComplex2Graph() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testInsertComplex2.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertComplex2_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * 
     * @throws IOException
     */
    public void testDeleteSimpleGraph() throws IOException {
        String[] args = new String[] { "5",
            "testInputFiles/testDeleteSimpleGraph.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testDeleteSimpleGraph_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Seeing how removeEdge works
     */
    public void testGraph() {
        GraphL graph = new GraphL();
        graph.init(8);
        graph.analyzeComponents();
        System.out.printf("# of components %d%n", graph
            .getNumConnectedComponents());
        System.out.printf("size of largest componenet %d%n", graph
            .getBiggestComponentCount());
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 0, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(2, 0, 1);
        graph.addEdge(0, 3, 1);
        graph.addEdge(3, 0, 1);

        graph.analyzeComponents();
        System.out.printf("# of components %d%n", graph
            .getNumConnectedComponents());
        System.out.printf("size of largest componenet %d%n", graph
            .getBiggestComponentCount());
        graph.printAdjList();
        graph.removeNode(1);

        graph.printAdjList();
        graph.analyzeComponents();
        System.out.printf("# of components %d%n", graph
            .getNumConnectedComponents());
        System.out.printf("size of largest componenet %d%n", graph
            .getBiggestComponentCount());

        System.out.println("here");
        graph.addEdge(0, graph.addNode(), 1);

        graph.printAdjList();

        System.out.printf("# of components %d%n", graph
            .getNumConnectedComponents());
        System.out.printf("size of largest componenet %d%n", graph
            .getBiggestComponentCount());

    }
}
