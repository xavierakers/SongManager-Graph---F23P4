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
 *          Temporary test for mutation coverage
 */
public class MasterTest extends TestCase {
    /**
     * Check out the sfold method
     *
     * @throws Exception
     *             either a IOException or FileNotFoundException
     */
    public void testSfold() throws Exception {
        assertTrue(Hash.h("a", 10000) == 97);
        assertTrue(Hash.h("b", 10000) == 98);
        assertTrue(Hash.h("aaaa", 10000) == 1873);
        assertTrue(Hash.h("aaab", 10000) == 9089);
        assertTrue(Hash.h("baaa", 10000) == 1874);
        assertTrue(Hash.h("aaaaaaa", 10000) == 3794);
        assertTrue(Hash.h("Long Lonesome Blues", 10000) == 4635);
        assertTrue(Hash.h("Long   Lonesome Blues", 10000) == 4159);
        assertTrue(Hash.h("long Lonesome Blues", 10000) == 4667);
    }


    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Set up the tests that follow.
     */
    public void setUp() { // Nothing needed yet

    }


    /**
     * This method is simply to get code coverage of the class declaration.
     */
    public void testQInit() {
        GraphProject it = new GraphProject();
        assertNotNull(it);
    }

    // Creating SeminarDB to handle function calls
    private Controller controller = new Controller(128);
    // Creating the commandPrcoessor to test
    private CommandProcessor cp = new CommandProcessor(controller);

    /**
     * Testing hasNextLine() mutation
     * Reads in two different command files
     * 
     * @throws IOException
     */
    public void testHasNextLine() throws IOException {
        cp.readCommands("testInputFiles/testHasNextLine.txt");

        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testHasNextLine_output.txt")));
        assertEquals(expected, systemOut().getHistory());

        cp.readCommands("testInputFiles/testHasNextLine.txt");

        expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testHasNextLine_output2.txt")));
        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Code coverage
     * 
     * @throws IOException
     */
    public void testMain() throws IOException {
        String[] args = new String[] { "8", "testInput.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testInput_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Testing non repeated entries, not duplicates
     * 
     * @throws IOException
     */
    public void testDuplicateArtist() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testDuplicateArtist.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testDuplicateArtist_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Adding some records
     * 
     * @throws IOException
     */
    public void testInsertSimple() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testInsertSimple.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertSimple_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Inserts cause expansion of hash table
     * 
     * @throws IOException
     */
    public void testInsertBasic() throws IOException {
        String[] args = new String[] { "4",
            "testInputFiles/testInsertBasic.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertBasic_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Expansion of Hash table with tombstones
     * 
     * @throws IOException
     */
    public void testInsertComplex() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testInsertComplex.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertComplex_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Testing simple removal
     * 
     * @throws IOException
     */
    public void testRemoveSimple() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testRemoveSimple.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testRemoveSimple_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Delete records, expand, delete records again
     * 
     * @throws IOException
     */
    public void testRemoveBasic() throws IOException {
        String[] args = new String[] { "4",
            "testInputFiles/testRemoveBasic.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testRemoveBasic_output.txt")));

        assertEquals(expected, systemOut().getHistory());

    }


    /**
     * More complex testing
     * 
     * @throws IOException
     */
    public void testSampleInput() throws IOException {
        String[] args = new String[] { "10", "P4sampleInput.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "P4sampleOutput.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * More complex testing
     * 
     * @throws IOException
     */
    public void testDuplicateInsert() throws IOException {
        String[] args = new String[] { "10",
            "testInputFiles/testDuplicateInsert.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testDuplicateInsert_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    /**
     * Simple hash table insertion
     */
    public void testInsertAndSeach1() {
        Hash table = new Hash(4);
        Record record1 = new Record("record1", -1);
        assertTrue(table.insert(record1));
        assertEquals(table.search(record1.getKey()), record1);

    }


    /**
     * Test HashTable insert with keys in and out of range
     * 1. Insert Record1 with large key
     * 2. Insert Record2 with small key
     * 3. Search Record1
     * 4. Search Record2 -- Fail
     */
    public void testInsertDuplicateAndSearch() {
        Hash table = new Hash(8);
        Record record1 = new Record("record1", -1);
        Record record2 = new Record("record1", -1);

        assertTrue(table.insert(record1));
        assertFalse(table.insert(record2));
        assertEquals(table.search(record1.getKey()), record1);
        assertNotSame(table.search(record2.getKey()), record2);

    }


    /**
     * Search empty hash table
     */
    public void testNullSeach() {
        Hash table = new Hash(4);
        assertNull(table.search("1"));
    }


    /**
     * Basic test for deletions
     * 1. Insert Records
     * 2. Delete Records
     * 3. Search Records
     */
    public void testDelete() {
        Hash table = new Hash(256);
        Record record1 = new Record("record1", -1);
        Record record2 = new Record("record2", -1);
        Record record3 = new Record("record3", -1);

        assertTrue(table.insert(record1));
        assertTrue(table.insert(record2));
        assertTrue(table.insert(record3));

        assertEquals(table.delete(record2.getKey()), record2);
        assertEquals(table.delete(record1.getKey()), record1);
        assertEquals(table.search(record2.getKey()), null);
        assertNull(table.search(record2.getKey()));
        assertNull(table.search(record1.getKey()));
    }


    /**
     * Test for resize
     * Fill hashtable to 50%
     * Double size of hashtable and rehash
     */
    public void testResizeSimple() {
        Hash table = new Hash(4);
        Record record1 = new Record("record1", -1);
        Record record2 = new Record("record2", -1);
        Record record3 = new Record("record3", -1);
        // capacity = 4
        // threshold = 2
        assertTrue(table.insert(record1));
        assertTrue(table.insert(record2));
        // count = 2
        // count == threshold so resize
        assertTrue(table.insert(record3));
    }


    /**
     * Test deletions
     */
    public void testDelete1() {
        Hash table = new Hash(8);
        Record record1 = new Record("A", -1);
        Record record2 = new Record("I", -1);
        Record record3 = new Record("Q", -1);

        assertTrue(table.insert(record1));
        assertTrue(table.insert(record2));
        assertTrue(table.insert(record3));
        assertEquals(table.getCount(), 3);
        table.printContents();

        assertEquals(table.search(record1.getKey()), record1);
        assertEquals(table.delete(record1.getKey()), record1);
        assertEquals(table.search(record2.getKey()), record2);
        assertEquals(table.delete(record2.getKey()), record2);
        assertEquals(table.search(record3.getKey()), record3);
        assertEquals(table.delete(record3.getKey()), record3);
        table.printContents();
        assertEquals(table.getCount(), 0);
    }


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
        String[] args = new String[] { "10",
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
