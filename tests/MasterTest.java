import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

public class MasterTest extends TestCase {
    public void testInsertAndSeach1() {
        Hash table = new Hash(4);
        Record record1 = new Record("record1", null);
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
        Record record1 = new Record("record1", null);
        Record record2 = new Record("record1", null);

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
        Record record1 = new Record("record1", null);
        Record record2 = new Record("record2", null);
        Record record3 = new Record("record3", null);

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
        Record record1 = new Record("record1", null);
        Record record2 = new Record("record2", null);
        Record record3 = new Record("record3", null);
        Record record4 = new Record("record4", null);

        // capacity = 4
        // threshold = 2
        assertTrue(table.insert(record1));
        assertTrue(table.insert(record2));
        // count = 2
        // count == threshold so resize
        assertTrue(table.insert(record3));
    }


    public void testMain() {
        String[] args = new String[] { "8", "testInput.txt" };
        GraphProject.main(args);
    }


    public void testDuplicateArtist() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testDuplicateArtist.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testDuplicateArtist_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


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


    public void testInsertSimple() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testInsertSimple.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertSimple_output.txt")));

        assertEquals(expected, systemOut().getHistory());
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


    public void testRemoveSimple() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testRemoveSimple.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testRemoveSimple_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    public void testDelete1() {
        Hash table = new Hash(8);
        Record record1 = new Record("A", null);
        Record record2 = new Record("I", null);
        Record record3 = new Record("Q", null);

        assertTrue(table.insert(record1));
        assertTrue(table.insert(record2));
        assertTrue(table.insert(record3));
        assertEquals(table.getCount(), 3);

        assertEquals(table.search(record1.getKey()), record1);
        assertEquals(table.delete(record1.getKey()), record1);
        assertEquals(table.search(record2.getKey()), record2);
        assertEquals(table.delete(record2.getKey()), record2);
        assertEquals(table.search(record3.getKey()), record3);
        assertEquals(table.delete(record3.getKey()), record3);
        assertEquals(table.getCount(), 0);
    }
}
