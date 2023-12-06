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
    public void testInsertAdvancedGraph() throws IOException {
        String[] args = new String[] { "10",
            "testInputFiles/testInsertAdvancedGraph.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertAdvancedGraph_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * 
     * @throws IOException
     */
    public void testRemoveBasicGraph() throws IOException {
        String[] args = new String[] { "9",
            "testInputFiles/testRemoveBasicGraph.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testRemoveBasicGraph_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * 
     * @throws IOException
     */
    public void testDeleteAdvancedGraph() throws IOException {
        String[] args = new String[] { "10",
            "testInputFiles/testDeleteAdvancedGraph.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testDeleteAdvancedGraph_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * 
     * @throws IOException
     */
    public void testAdvancedPrintGraph() throws IOException {
        String[] args = new String[] { "10",
            "testInputFiles/testAdvancedPrintGraph.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testAdvancedPrintGraph_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }
}
