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
     * Code coverage
     */
    public void testMain() {
        String[] args = new String[] { "8", "testInput.txt" };
        GraphProject.main(args);
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
            "testInputFiles/testInsertSimple.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertSimple_output.txt")));

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

}
