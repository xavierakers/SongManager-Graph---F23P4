import student.TestCase;

/**
 * 
 * @author Xavier Akers
 * @author Zoe Hite
 * 
 * @version Last Updated 11-5-2023
 *          Direct hash table tests
 */
public class HashTableTest extends TestCase {
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
}
